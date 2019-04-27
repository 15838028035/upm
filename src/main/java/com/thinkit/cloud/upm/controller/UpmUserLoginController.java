package com.thinkit.cloud.upm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkit.cloud.upm.bean.UpmUser;
import com.thinkit.cloud.upm.service.UpmUserService;
import com.thinkit.cloud.upm.util.JwtUtil;
import com.thinkit.cloud.upm.util.Md5Util;
import com.zhongkexinli.micro.serv.common.bean.RestAPIResult2;
import com.zhongkexinli.micro.serv.common.pagination.Query;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 管理
 */
@Api(value = "服务", tags = "服务接口")
@RestController()
public class UpmUserLoginController extends BaseController {

	/**
	 * token超时时间
	 */
	@Value("${jtwTokenTimeOut}")
	private Long jtwTokenTimeOut;
	
	@Autowired
	private UpmUserService upmUserService;

	/**
	 * 用户登录
	 * 
	 * @return
	 */
	@ApiOperation(value = "登录")
	@RequestMapping(value = "/api/UpmUser/login", method = RequestMethod.POST)
	public RestAPIResult2 login( UpmUser upmUser, HttpServletRequest request, RedirectAttributes attributes) {

		RestAPIResult2 restAPIResult = new RestAPIResult2();
		restAPIResult.setRespCode(1);
		restAPIResult.setRespMsg("登录成功");

		if (upmUser.getUserNo() == null || "".equals(upmUser.getUserNo()) || upmUser.getUserPass() == null
				|| "".equals(upmUser.getUserPass())) {
			restAPIResult.setRespCode(0);
			restAPIResult.setRespMsg("参数为空");
			return restAPIResult;
		}
		
		Query query = new Query().putFilter("userNo", upmUser.getUserNo())
								 .putFilter("userPass", Md5Util.md5(upmUser.getUserPass()));

		List<UpmUser> list = upmUserService.selectByExample(query);
		if (list.isEmpty()) {
			restAPIResult.setRespCode(0);
			restAPIResult.setRespMsg("用户名或密码输入错误");
		} else {
			restAPIResult.setRespData(list.get(0));

			String token = JwtUtil.generateToken(list.get(0).getUserNo(), String.valueOf(list.get(0).getId()),
					jtwTokenTimeOut);
			restAPIResult.setToken(token);
		}

		return restAPIResult;
	}

}

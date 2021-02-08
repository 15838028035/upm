package com.thinkit.cloud.upm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkit.cloud.upm.bean.UpmUser;
import com.thinkit.cloud.upm.config.JwtUtil;
import com.thinkit.cloud.upm.service.UpmUserService;
import com.thinkit.cloud.upm.util.Md5Util;
import com.zhongkexinli.micro.serv.common.bean.RestApiResult2;
import com.zhongkexinli.micro.serv.common.pagination.Query;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 管理
 */
@Api(value = "服务", tags = "服务接口")
@RestController()
public class UpmUserLoginController extends BaseController {

	@Autowired
	private JwtUtil jwtUtil;
	/**
	 * token超时时间
	 */
	@Value("${jtwTokenTimeOut:60000}")
	private Long jtwTokenTimeOut;
	
	@Autowired
	private UpmUserService UpmUserService;

	/**
	 * 用户登录
	 * 
	 * @return
	 */
	@ApiOperation(value = "登录")
	@PostMapping(value = "/api/UpmUser/login")
	public RestApiResult2 login( UpmUser UpmUser, HttpServletRequest request, RedirectAttributes attributes) {

	  RestApiResult2 restAPIResult = new RestApiResult2();
		restAPIResult.setRespCode(1);
		restAPIResult.setRespMsg("登录成功");

		if (UpmUser.getUserNo() == null || "".equals(UpmUser.getUserNo()) || UpmUser.getUserPass() == null
				|| "".equals(UpmUser.getUserPass())) {
			restAPIResult.setRespCode(0);
			restAPIResult.setRespMsg("参数为空");
			return restAPIResult;
		}
		
		Query query = new Query().putFilter("userNo", UpmUser.getUserNo())
								 .putFilter("userPass", Md5Util.md5(UpmUser.getUserPass()));

		List<UpmUser> list = UpmUserService.selectByExample(query);
		if (list.isEmpty()) {
			restAPIResult.setRespCode(0);
			restAPIResult.setRespMsg("用户名或密码输入错误");
		} else {
			restAPIResult.setRespData(list.get(0));

			String token = jwtUtil.generateToken(list.get(0).getUserNo(), String.valueOf(list.get(0).getId()),
					jtwTokenTimeOut);
			restAPIResult.setToken(token);
			restAPIResult.setDataCode(String.valueOf(jtwTokenTimeOut));
		}

		return restAPIResult;
	}

}

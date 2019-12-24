package com.thinkit.cloud.upm.config.performance;


import java.util.Properties;

import org.apache.ibatis.plugin.Interceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class PerformanceConfig {
	
    /**
	 * mybatis 是否格式化SQL语句, 默认值:true
	 */
	@Value("${mybatis.formatSql:true}")
	private Boolean mybatisFormatSql;
		
    /**
     * SQL执行效率插件
     */
    @Bean
    @Profile({"dev", "test"})// 设置 dev test 环境开启
    public Interceptor performanceInterceptor() {

        Interceptor sqlCostInterceptor = new SqlStatementInterceptor();
        Properties properties = new Properties();
        properties.put("mybatisFormatSql",mybatisFormatSql);
        sqlCostInterceptor.setProperties(properties);
        return sqlCostInterceptor;
    }
}
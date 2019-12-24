package com.thinkit.cloud.upm.config.performance;


import java.util.Properties;

import org.apache.ibatis.plugin.Interceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class PerformanceConfig {
    /**
     * SQL执行效率插件
     */
    @Bean
    @Profile({"dev", "test"})// 设置 dev test 环境开启
    public Interceptor performanceInterceptor() {

        Interceptor sqlCostInterceptor = new SqlStatementInterceptor();
        Properties properties = new Properties();
        sqlCostInterceptor.setProperties(properties);
        return sqlCostInterceptor;
    }
}
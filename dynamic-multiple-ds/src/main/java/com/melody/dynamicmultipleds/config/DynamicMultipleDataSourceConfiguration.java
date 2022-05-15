package com.melody.dynamicmultipleds.config;

import com.alibaba.druid.spring.boot.autoconfigure.properties.DruidStatProperties;
import com.alibaba.druid.util.Utils;
import com.melody.dynamicmultipleds.mydatasource.DynamicMultipleDataSource;
import com.melody.dynamicmultipleds.provider.YmlMultipleDataSourceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import java.io.IOException;

@Configuration
public class DynamicMultipleDataSourceConfiguration {
    @Autowired
    private YmlMultipleDataSourceProvider provider;

    @Bean
    public DynamicMultipleDataSource dynamicMultipleDataSource(){
        return new DynamicMultipleDataSource(provider);
    }

    //去除广告
    @Bean
    @ConditionalOnProperty(name = {"spring.datasource.druid.stat-view-servlet.enabled"},havingValue = "true")
    public FilterRegistrationBean removeAdFilterRegistrationBean(DruidStatProperties druidStatProperties){
        //获取Web监控页面的参数
        DruidStatProperties.StatViewServlet statViewServlet = druidStatProperties.getStatViewServlet();
        //提取common.js的配置路径
        String urlPattern = statViewServlet.getUrlPattern() != null? statViewServlet.getUrlPattern():"/druid/*";
        String commonJsPattern = urlPattern.replaceAll("\\*","js/common.js");
        //定义过滤器
        Filter filter = new Filter() {
            @Override
            public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
                String content = Utils.readFromResource("support/http/resources/js/common.js");
                content =  content.replace("this.buildFooter();","");
                servletResponse.getWriter().write(content);
            }
        };
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(filter);
        registrationBean.addUrlPatterns(commonJsPattern);
        return registrationBean;
    }
}
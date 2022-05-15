package com.melody.druidsql.filter;

import com.alibaba.druid.util.Utils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//@WebFilter(urlPatterns ="/druid/js/common.js")
//public class DeleteADFilter implements Filter {
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        servletResponse.resetBuffer();
//        String content =  Utils.readFromResource("support/http/resources/js/common.js");
//        content =  content.replace("this.buildFooter();","");
//        servletResponse.getWriter().write(content);
//    }
//}

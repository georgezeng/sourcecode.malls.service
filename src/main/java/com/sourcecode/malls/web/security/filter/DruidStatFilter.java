package com.sourcecode.malls.web.security.filter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import com.alibaba.druid.support.http.WebStatFilter;


@WebFilter(
        urlPatterns = "/**",
        initParams = {
                @WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.png,*.css,*.ico,*.html,/druid/*")
        }
)
public class DruidStatFilter extends WebStatFilter {
}

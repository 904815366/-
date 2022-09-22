package com.woniuxy.purchase.filter;

import com.woniuxy.purchase.interceptor.RequestReaderHttpServletRequestWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class HttpServletRequestReplacedFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            ServletRequest requestWrapper = new RequestReaderHttpServletRequestWrapper((HttpServletRequest) request);
            //获取请求中的流，将取出来的字符串，再次转换成流，然后把它放入到新request对象中。
            // 在chain.doFiler方法中传递新的request对象
            chain.doFilter(requestWrapper, response);
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

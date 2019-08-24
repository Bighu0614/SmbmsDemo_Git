package com.bdqn.servlets;


import javax.servlet.*;
import java.io.IOException;

public class Filter implements javax.servlet.Filter {
    private String encode;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        if (this.encode==null){
            this.encode = filterConfig.getInitParameter("Encoding");
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest.getCharacterEncoding()==null){
            servletRequest.setCharacterEncoding(encode);
        }
        if (servletRequest.getCharacterEncoding()==null){
            servletResponse.setCharacterEncoding(encode);
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        this.encode = null;
    }
}

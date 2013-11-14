package com.dy.webmark.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.util.NestedServletException;

import com.duanbn.validation.exception.CheckFailureException;
import com.dy.webmark.common.ErrorCode;
import com.dy.webmark.common.WebConst;
import com.dy.webmark.exception.BizException;

public class CommonFilter implements Filter {

    public static final Logger LOG = Logger.getLogger(CommonFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        try {
            chain.doFilter(request, response);
        } catch (Exception e) {
            if (e instanceof NestedServletException) {
                LOG.error(e);
                if (e.getCause() instanceof CheckFailureException) {
                    ErrorCode ec = ErrorCode.BIZ5001;
                    ec.setMessage(e.getMessage());
                    req.setAttribute(WebConst.SESSION_ERRORINFO, ec);
                } else if (e instanceof BizException) {
                    ErrorCode ec = ((BizException) e).getEc();
                    req.setAttribute(WebConst.SESSION_ERRORINFO, ec);
                }
            }
            req.getRequestDispatcher("failure").forward(request, response);
        }

    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

}

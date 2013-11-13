package com.dy.webmark.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        HttpServletResponse resp = (HttpServletResponse) response;

        String inputUrl = req.getRequestURL().toString();
        HttpSession session = req.getSession();
        session.removeAttribute(WebConst.SESSION_ERRORINFO);

        try {
            chain.doFilter(request, response);
        } catch (Exception e) {
            if (e instanceof NestedServletException) {
                LOG.error(e);
                if (e.getCause() instanceof CheckFailureException) {
                    ErrorCode ec = ErrorCode.BIZ5001;
                    ec.setMessage(e.getMessage());
                    session.setAttribute(WebConst.SESSION_ERRORINFO, ec);
                }
            } else if (e instanceof BizException) {
                ErrorCode ec = ((BizException) e).getEc();
                session.setAttribute(WebConst.SESSION_ERRORINFO, ec);
                LOG.error(e.getMessage(), e);
            } else {
                session.setAttribute(WebConst.SESSION_ERRORINFO, ErrorCode.BIZ5002);
                LOG.error(e.getMessage(), e);
            }
            resp.sendRedirect(inputUrl);
        }

    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

}

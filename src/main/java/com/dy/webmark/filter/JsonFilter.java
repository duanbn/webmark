package com.dy.webmark.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.duanbn.validation.exception.CheckFailureException;
import com.dy.webmark.common.ErrorCode;
import com.dy.webmark.common.WebConst;
import com.dy.webmark.exception.BizException;

public class JsonFilter implements Filter {

    public static final Logger LOG = Logger.getLogger(JsonFilter.class);

    private static final ObjectMapper jsonMapper = new ObjectMapper();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            chain.doFilter(request, response);

            Object output = request.getAttribute(WebConst.OUTPUT_DATA);
            map.put("status", "ok");
            if (output != null)
                map.put("data", output);

        } catch (Exception e) {
            LOG.error(e);
            ErrorCode ec = null;
            if (e.getCause() instanceof CheckFailureException) {
                ec = ErrorCode.BIZ5001;
                ec.setMessage(e.getMessage());
            } else if (e.getCause() instanceof BizException) {
                ec = ((BizException) e.getCause()).getEc();
            }
            map.put("status", "error");
            map.put("code", ec.getCode());
            map.put("message", ec.getMessage());
        }

        String json = jsonMapper.writeValueAsString(map);
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(json);
        writer.flush();
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

}

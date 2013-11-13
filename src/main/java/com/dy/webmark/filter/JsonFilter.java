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

import org.codehaus.jackson.map.ObjectMapper;

import com.duanbn.validation.exception.CheckFailureException;
import com.dy.webmark.common.ErrorCode;
import com.dy.webmark.common.WebConst;
import com.dy.webmark.exception.BizException;

public class JsonFilter implements Filter {

    private static final ObjectMapper jsonMapper = new ObjectMapper();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        String json = "";

        try {
            chain.doFilter(request, response);
            Object output = request.getAttribute(WebConst.OUTPUT_JSON);
            json = jsonMapper.writeValueAsString(output);
        } catch (CheckFailureException e) {
            json = jsonMapper.writeValueAsString(e.getMessage());
        } catch (Exception e) {
            Map<String, String> errorMap = new HashMap<String, String>();
            if (e instanceof BizException) {
                ErrorCode ec = ((BizException) e).getEc();
                errorMap.put(ec.getCode(), ec.getMessage());
                json = jsonMapper.writeValueAsString(errorMap);
            }
        }

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

package com.hhnz.filter;

import com.hhnz.util.WebSystemContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by 杨 on 2017/1/10.
 */
public class PageFilter implements Filter {
    @Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest=(HttpServletRequest)request;

        WebSystemContext.setOffSet(getOffSet(httpRequest));
        WebSystemContext.setLimit(getLimit(httpRequest));

        try {
            chain.doFilter(request, response);
        }
        //使用完Threadlocal，将其删除。使用finally确保一定将其删除
        finally{
            WebSystemContext.removeOffSet();
            WebSystemContext.removeLimit();
        }
    }
    /**
     * 获得pager.offset参数的值
     * @param request
     * @return
     */
    protected int getOffSet(HttpServletRequest request) {
        int offSet = 0;
        try {
            String pageOff = request.getParameter("offset");
            if (pageOff != null) {
                offSet =Integer.parseInt(pageOff);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return offSet;
    }

    /**
     * 获得pager.offset参数的值
     * @param request
     * @return
     */
    protected int getLimit(HttpServletRequest request) {
        int limit = 0;
        try {
            String limitStr = request.getParameter("limit");
            if (limitStr != null) {
                limit =Integer.parseInt(limitStr);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return limit;
    }
    @Override
    public void init(FilterConfig arg0) throws ServletException {}
}


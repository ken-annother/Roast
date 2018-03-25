package xyz.soongkun.roast.common.fillter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StyleFilePlaceHolderFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(StyleFilePlaceHolderFilter.class);

    private FilterConfig filterConfig;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        MyHttpServletResponseWrapper responseWrapper = new MyHttpServletResponseWrapper((HttpServletResponse) response);
        chain.doFilter(request,responseWrapper);

        String textContent = responseWrapper.getTextContent();
        String newTextContent = textContent.replace("${ctxStatic}", ((HttpServletRequest)request).getContextPath() + "/static");
        response.setContentLength(newTextContent.length());
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(newTextContent);
    }

    @Override
    public void destroy() {

    }
}

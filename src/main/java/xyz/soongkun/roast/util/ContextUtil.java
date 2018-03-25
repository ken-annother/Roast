package xyz.soongkun.roast.util;

import org.apache.struts2.ServletActionContext;

public class ContextUtil {
    public ContextUtil() {
    }

    public static boolean isPostRequest() {
        return "POST".equalsIgnoreCase(ServletActionContext.getRequest().getMethod());
    }

    public static boolean isGetRequest() {
        return "GET".equalsIgnoreCase(ServletActionContext.getRequest().getMethod());
    }
}

package xyz.soongkun.roast.common.model;

import java.util.List;
import org.apache.struts2.ServletActionContext;

public class PageBean<T> {
    private Integer page;
    private Integer pageMaxNumber;
    private Integer totalItem;
    private Integer count;
    private List<T> data;

    public PageBean() {
    }

    public Integer getPage() {
        return this.page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageMaxNumber() {
        return this.pageMaxNumber;
    }

    public void setPageMaxNumber(Integer pageMaxNumber) {
        this.pageMaxNumber = pageMaxNumber;
    }

    public Integer getTotalItem() {
        return this.totalItem;
    }

    public void setTotalItem(Integer totalItem) {
        this.totalItem = totalItem;
    }

    public Integer getCount() {
        return this.count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<T> getData() {
        return this.data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public String toString() {
        String contextPath = ServletActionContext.getRequest().getContextPath();
        StringBuilder builder = new StringBuilder();

        builder.append("<link rel=\"stylesheet\" href=\"" + contextPath + "/static/pagehelper/pagehelper.css\">");

        builder.append("<div class=\"pagefynav\"><div class=\"tcdPageCode\">");
        builder.append("<a href=\"" + contextPath + "/" + "index" + "\" class=\"homePage layui-hide-xs\">首&nbsp;&nbsp;&nbsp;页</a>");
        if (this.page == 0) {
            builder.append("<a href=\"javascript:;\" class=\"prevPage disabled\">上一页</a>");
        } else {
            builder.append("<a href=\"" + contextPath + "/" + "index?page=" + (this.page - 1) + "\" class=\"prevPage\">上一页</a>");
        }

        int totalPageCount = this.totalItem / this.pageMaxNumber;
        if (this.totalItem % this.pageMaxNumber != 0) {
            ++totalPageCount;
        }

        int startpage = 1;
        int endPage = 1;

        if (totalPageCount <= 6) {
            startpage = 1;
            endPage = totalPageCount;
        } else if (totalPageCount - this.page < 5) {
            endPage = totalPageCount;
            startpage = totalPageCount - 5;
        } else {
            startpage = this.page - 1 < 1 ? 1 : this.page - 1;
            endPage = startpage + 5;
        }

        for(int i = startpage; i <= endPage; ++i) {
            if (this.page + 1 == i) {
                builder.append("<a href=\"javascript:;\" class=\"current tcdNumber layui-hide-xs\">" + i + "</span>");
            } else {
                builder.append("<a href=\"" + contextPath + "/" + "index?page=" + i + "\" class=\"tcdNumber layui-hide-xs\">" + i + "</a>");
            }
        }

        if (this.page + 1 == totalPageCount) {
            builder.append("<a href=\"javascript:;\" class=\"nextPage disabled\">下一页</a>");
        } else {
            builder.append("<a href=\"" + contextPath + "/" + "index?page=" + (this.page + 2) + "\" class=\"nextPage\">下一页</a>");
        }

        builder.append("<a href=\"" + contextPath + "/" + "index?page=" + totalPageCount + "\" class=\"endPage layui-hide-xs\">尾&nbsp;&nbsp;&nbsp;页</a>");
        builder.append("<div class=\"pagejump\">共<span id=\"totalpagecount\">" +  totalPageCount + "</span>页   跳转到<input id=\"pageJumpNumInput\" type=\"number\" value=\"" + (this.page + 1) + "\" class=\"layui-input-inline\" min=\"1\" max=\"" + totalPageCount + "\"/>页 <button class=\"layui-btn okjump\" onclick=\"jumpPage()\">确定</button></div>");

        builder.append("</div></div>");
        builder.append("<script src=\"" + contextPath + "/static/pagehelper/pagehelper.js\"></script>");

        return builder.toString();
    }
}

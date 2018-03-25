package xyz.soongkun.roast.module.t_poster.model;

import java.io.Serializable;
import java.util.Date;
import xyz.soongkun.roast.module.t_user.model.User;

public class Poster implements Serializable {
    private Integer id;
    private String content;
    private String picUrls;
    private Date postDate;
    private User postUser;

    public Poster() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicUrls() {
        return this.picUrls;
    }

    public void setPicUrls(String picUrls) {
        this.picUrls = picUrls;
    }

    public Date getPostDate() {
        return this.postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public User getPostUser() {
        return this.postUser;
    }

    public void setPostUser(User postUser) {
        this.postUser = postUser;
    }
}

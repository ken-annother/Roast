package xyz.soongkun.roast.module.t_user.model;

import java.io.Serializable;
import java.util.Set;
import xyz.soongkun.roast.module.t_poster.model.Poster;

public class User implements Serializable {
    private Integer id;
    private String nickname;
    private String email;
    private String password;
    private String code;
    private Set<Poster> posterlist;

    public User() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set<Poster> getPosterlist() {
        return this.posterlist;
    }

    public void setPosterlist(Set<Poster> posterlist) {
        this.posterlist = posterlist;
    }

    public String toString() {
        return "User{id=" + this.id + ", nickname='" + this.nickname + '\'' + ", email='" + this.email + '\'' + ", password='" + this.password + '\'' + ", code='" + this.code + '\'' + '}';
    }
}

package xyz.soongkun.roast.module.t_user.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import xyz.soongkun.roast.module.t_user.model.User;
import xyz.soongkun.roast.util.ContextUtil;

@Controller
@Scope("prototype")
public class LoginAction extends ActionSupport implements ModelDriven<User> {
    private static final Logger logger = LoggerFactory.getLogger(LoginAction.class);
    private User user = new User();
    private Map<String, Object> data;
    private String redirect_from;

    public LoginAction() {
    }

    public Map<String, Object> getData() {
        return this.data;
    }

    public String execute() {
        Subject currentUser = SecurityUtils.getSubject();
        if (ContextUtil.isGetRequest()) {
            return currentUser.isAuthenticated() ? "toIndex" : "success";
        } else {
            if (this.hasActionErrors()) {
                this.data.put("code", 400);
                this.data.put("msg", this.getActionErrors());
            }

            AuthenticationToken token = new UsernamePasswordToken(this.user.getEmail(), this.user.getPassword(), true);
            this.data = new HashMap();

            try {
                currentUser.login(token);
            } catch (UnknownAccountException var4) {
                this.addActionError("用户名不存在");
            } catch (IncorrectCredentialsException var5) {
                this.addActionError("密码不正确");
            } catch (LockedAccountException var6) {
                this.addActionError("用户被锁定");
            } catch (AuthenticationException var7) {
                this.addActionError("出现了未知错误");
            }

            if (this.hasActionErrors()) {
                this.data.put("code", 400);
                this.data.put("msg", (new ArrayList(this.getActionErrors())).get(0));
            } else {
                this.data.put("code", 200);
                this.data.put("redirect_from",redirect_from);
                logger.info("redirect_from," + redirect_from);
                this.data.put("msg", "登录成功");
            }

            return "pt";
        }
    }

    public User getModel() {
        return this.user;
    }

    public void validate() {
        if (ContextUtil.isPostRequest()) {
            if (StringUtils.isBlank(this.user.getEmail())) {
                this.addActionError("email不能为空");
            } else if (StringUtils.isBlank(this.user.getPassword())) {
                this.addActionError("密码不能为空");
            }
        }

    }

    public String getRedirect_from() {
        return redirect_from;
    }

    public void setRedirect_from(String redirect_from) {
        this.redirect_from = redirect_from;
    }
}

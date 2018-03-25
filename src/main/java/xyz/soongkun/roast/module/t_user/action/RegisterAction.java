package xyz.soongkun.roast.module.t_user.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import xyz.soongkun.roast.module.t_user.model.User;
import xyz.soongkun.roast.module.t_user.service.UserService;
import xyz.soongkun.roast.module.t_user.service.ValidationCodeService;
import xyz.soongkun.roast.util.ContextUtil;

@Controller
@Scope("prototype")
public class RegisterAction extends ActionSupport implements ModelDriven<User> {
    private static final Logger logger = LoggerFactory.getLogger(RegisterAction.class);
    @Autowired
    private UserService userService;
    @Autowired
    private ValidationCodeService validationCodeService;
    private User user = new User();

    public RegisterAction() {
    }

    public String execute() {
        if (ContextUtil.isGetRequest()) {
            Subject currentUser = SecurityUtils.getSubject();
            return currentUser.isAuthenticated() ? "toIndex" : "success";
        } else {
            try {
                String code = this.validationCodeService.getValideCodeAndCache(this.user.getEmail());
                if (!StringUtils.isBlank(code) && code.equals(this.user.getCode())) {
                    this.userService.registerByEmail(this.user);
                    return "register_success";
                } else {
                    this.addActionError("验证码不正确");
                    return "register_failure";
                }
            } catch (Exception var2) {
                this.addActionError(var2.getMessage());
                return "register_failure";
            }
        }
    }

    public User getModel() {
        return this.user;
    }
}

package xyz.soongkun.roast.module.t_user.action;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import xyz.soongkun.roast.common.action.BaseJsonActionSupport;
import xyz.soongkun.roast.module.t_user.service.ValidationCodeService;
import xyz.soongkun.roast.util.TextUtil;

@Controller
@Scope("prototype")
public class ValidationCodeAction extends BaseJsonActionSupport {
    private static final Logger logger = LoggerFactory.getLogger(ValidationCodeAction.class);
    private String type;
    private String content;
    @Autowired
    private ValidationCodeService validationCodeService;

    public ValidationCodeAction() {
    }

    public String execute() throws Exception {
        if (this.hasFieldErrors()) {
            this.setJsonResult(4001, (String)((List)this.getFieldErrors().get("msg")).get(0));
        }

        if ("email".equals(this.type)) {
            try {
                this.validationCodeService.removeValideCodeFromCache(this.content);
                String newCode = this.validationCodeService.getValideCodeAndCache(this.content);
                this.validationCodeService.sendValidateCode(this.content, newCode);
                this.setJsonResult(200, "发送验证码成功");
            } catch (Exception var2) {
                logger.error(var2.getMessage());
                this.setJsonResult(400, var2.getMessage());
            }
        }

        return "success";
    }

    public void validate() {
        if ("email".equals(this.type)) {
            if (!TextUtil.isEmail(this.content)) {
                this.addFieldError("msg", "email地址不正确");
            }
        } else {
            this.addFieldError("msg", "还不支持其他类型");
        }

    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

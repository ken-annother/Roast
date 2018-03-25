package xyz.soongkun.roast.module.t_user.service;

import java.io.UnsupportedEncodingException;
import javax.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import xyz.soongkun.roast.common.model.MailBean;
import xyz.soongkun.roast.common.service.MailSenderService;
import xyz.soongkun.roast.util.TextUtil;

@Service("validationCodeService")
public class ValidationCodeServiceImpl implements ValidationCodeService {
    private static final Logger logger = LoggerFactory.getLogger(ValidationCodeServiceImpl.class);
    @Autowired
    private MailSenderService mailSenderService;

    public ValidationCodeServiceImpl() {
    }

    public void sendValidateCode(String email, String code) throws UnsupportedEncodingException, MessagingException {
        MailBean mailBean = new MailBean();
        mailBean.setSubject("注册验证码");
        mailBean.setToEmails(new String[]{email});
        mailBean.setContext("您的验证码是：" + code + ",请勿向他人泄漏");
        this.mailSenderService.sendMail(mailBean);
    }

    @Cacheable(
            value = {"ValidateCodeCache"},
            key = "#email"
    )
    public String getValideCodeAndCache(String email) {
        String randNum = TextUtil.getRandNum(6);
        logger.info(randNum);
        return randNum;
    }

    @CacheEvict(
            value = {"ValidateCodeCache"},
            key = "#email"
    )
    public void removeValideCodeFromCache(String email) {
        logger.info("removed:" + email);
    }
}

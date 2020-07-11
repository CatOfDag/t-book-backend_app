package com.huse.controller;

import com.google.gson.Gson;
import com.huse.Service.AccountService;
import com.huse.pojo.Account;
import com.huse.util.C;
import com.huse.util.EmailSend;
import com.huse.util.JsonResult;
import com.huse.util.JwtToken;
import com.sun.mail.smtp.SMTPAddressFailedException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import net.sf.json.JSONObject;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.security.GeneralSecurityException;
import java.util.UUID;

@Controller
@RequestMapping("/basic")
public class BasicController {

    @Autowired
    private SessionDAO sessionDAO;
    @Autowired
    private AccountService accountService;
    @Autowired
    private JsonResult jsonResult;
    @Resource(name = "gson")
    private Gson gson;

    @RequestMapping("/register")
    @ResponseBody
    public String register(@RequestBody Account a){
        System.out.println(a);
        if (1 == accountService.insertSelective(a)){
            jsonResult.setCode(C.CODE_Register_SUCCESS);
        } else {
            jsonResult.setCode(C.CODE_Register_FALSE);
        }

        return gson.toJson(jsonResult);
    }

    @RequestMapping("/login")
    @ResponseBody
    public String login(@RequestBody Account s){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(s.getAccount(),s.getPassword());
        try {
            subject.getSession().setAttribute(s.getAccount(),subject.getSession());
            subject.login(token);

            String secretKey = JwtToken.sign(token.getUsername(),String.valueOf(token.getPassword()));//登陆后获得的token

            jsonResult.setCode("510");
            jsonResult.setInfo(secretKey);
            Gson gson = new Gson();
            return gson.toJson(jsonResult);
        }catch (Exception e){
            jsonResult.setCode("101");//code ==> 101 account or password error
            jsonResult.setInfo("账户或密码错误");
            return gson.toJson(jsonResult);
        }
    }

    @RequestMapping("/verification_code")
    @ResponseBody
    public String VerificationCode(@RequestBody String s){

        JSONObject json = JSONObject.fromObject(s);
        String code = UUID.randomUUID().toString().substring(0,5);
        try {
            EmailSend.emailSend(json.get("email").toString(), code);
            jsonResult.setCode(C.CODE_MAIL_SUCCESS);
            jsonResult.setInfo(code);/*发送验证码到客户端进行验证*/
        } catch (SMTPAddressFailedException e){
            System.out.println(e);
            jsonResult.setCode(C.CODE_MAIL_FALSE);
            jsonResult.setInfo("邮件发送失败/邮箱地址错误");
        } catch (MessagingException | GeneralSecurityException e) {
            System.out.println(e);
            jsonResult.setCode(C.CODE_MAIL_FALSE);
            jsonResult.setInfo("邮件发送失败/邮箱地址错误");
        }
        return gson.toJson(jsonResult);
    }


    @RequestMapping("/error")
    @ResponseBody
    public String returnError(){
        jsonResult.setCode("505");// code ==> 505 验证失败 登录过期
        jsonResult.setInfo("406");
        return gson.toJson(jsonResult);
    }

}

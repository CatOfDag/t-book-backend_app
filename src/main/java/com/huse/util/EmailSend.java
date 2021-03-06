package com.huse.util;


import com.sun.mail.smtp.SMTPAddressFailedException;
import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;

public class EmailSend {

    private static String mailFrom = "XXX@XX.com";
    private static String authorization_code = "XXXXXXXXXX";
    private static String mailSubject = "Contacts-注册码";
    private static String mailHost = "smtp.qq.com";
    public static void emailSend(String internetAddress,String message) throws  GeneralSecurityException,MessagingException {

        //创建一个配置文件并保存
        Properties properties = new Properties();

        properties.setProperty("mail.host",mailHost);

        properties.setProperty("mail.transport.protocol","smtp");

        properties.setProperty("mail.smtp.auth","true");


        //QQ存在一个特性设置SSL加密
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", sf);

        //创建一个session对象
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EmailSend.mailFrom,EmailSend.authorization_code);
            }
        });

        //开启debug模式
        session.setDebug(true);

        //获取连接对象
        Transport transport = session.getTransport();

        //连接服务器
        transport.connect(mailHost,mailFrom,authorization_code);

        //创建邮件对象
        MimeMessage mimeMessage = new MimeMessage(session);

        //邮件发送人
        mimeMessage.setFrom(new InternetAddress(mailFrom));

        //邮件接收人
        mimeMessage.setRecipient(Message.RecipientType.TO,new InternetAddress(internetAddress));

        //邮件标题
        mimeMessage.setSubject(mailSubject);

        //邮件内容
        mimeMessage.setContent(message,"text/html;charset=UTF-8");

        //发送邮件
        transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());

        //关闭连接
        transport.close();
    }
}


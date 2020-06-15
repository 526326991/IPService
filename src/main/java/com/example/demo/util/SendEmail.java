package com.example.demo.util;

import com.alibaba.fastjson.JSON;
import com.example.demo.ip.IpUtil;
import com.example.demo.vo.IpVo;
import com.example.demo.vo.MailAuthenticator;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

@Component
@PropertySource(value = "classpath:info.properties")
public class SendEmail {


    @Value("${e.semail}")
    private String semail;
    @Value("${e.sqm}")
    private String sqm;
    @Value("${e.host}")
    private String host;
    @Value("${e.remail}")
    private String remail;
    @Value("${e.sender}")
    private String sender;
    @Value("${e.subject}")
    private String subject;

    /**
     * 调用 发送邮箱 方法
     */
    public void send(){
        SendEmail operation = new SendEmail();
        String user = semail;//你的邮箱地址
        String password = sqm;//你的邮箱授权码
        String from = sender;//发件人
        String to = remail;// 收件人
        //邮箱内容
        StringBuffer sb = new StringBuffer();
        sb.append(getIPInfo());
        try {
            String res = operation.sendMail(user, password, host, from, to,
                    subject, sb.toString());
            System.out.println(res);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 获取本机公网IP
     * @return
     */
    private String getIPInfo(){
        IpVo ipVo = IpUtil.getIpVo(null);
        String s = JSON.toJSONString(ipVo);
        return s;
    }


    /**
     * 发送邮件
     * @param user 发件人邮箱
     * @param password 授权码（注意不是邮箱登录密码）
     * @param host
     * @param from 发件人
     * @param to 接收者邮箱
     * @param subject 邮件主题
     * @param content 邮件内容
     * @return success 发送成功 failure 发送失败
     * @throws Exception
     */
    private String sendMail(String user, String password, String host,
                           String from, String to, String subject, String content)
            throws Exception {
        if (to != null){
            Properties props = System.getProperties();
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.auth", "true");
            MailAuthenticator auth = new MailAuthenticator();
            MailAuthenticator.USERNAME = user;
            MailAuthenticator.PASSWORD = password;
            Session session = Session.getInstance(props, auth);
            session.setDebug(true);
            try {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(from));
                if (!to.trim().equals(""))
                    message.addRecipient(Message.RecipientType.TO,
                            new InternetAddress(to.trim()));
                message.setSubject(subject);
                MimeBodyPart mbp1 = new MimeBodyPart(); // 正文
                mbp1.setContent(content, "text/html;charset=utf-8");
                Multipart mp = new MimeMultipart(); // 整个邮件：正文+附件
                mp.addBodyPart(mbp1);
                // mp.addBodyPart(mbp2);
                message.setContent(mp);
                message.setSentDate(new Date());
                message.saveChanges();
                Transport trans = session.getTransport("smtp");
                trans.send(message);
                System.out.println(message.toString());
            } catch (Exception e){
                e.printStackTrace();
                return "failure";
            }
            return "success";
        }else{
            return "failure";
        }
    }



}
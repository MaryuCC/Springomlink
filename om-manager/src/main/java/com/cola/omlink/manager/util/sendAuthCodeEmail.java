package com.cola.omlink.manager.util;

import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.UserCredentials;
import com.sun.mail.smtp.SMTPTransport;
import jakarta.annotation.PostConstruct;
import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

@Component
public class sendAuthCodeEmail {

    private static final String GMAIL_SMTP_SERVER = "smtp.gmail.com";
    private static final String FROM_EMAIL = "teamdatacola@gmail.com";
    private static final String OAUTH_USER = "teamdatacola@gmail.com";
    private static final String CREDENTIALS_FILE_PATH = "classpath:/credentials.json"; // path to your credentials.json

    @Autowired
    private ResourceLoader resourceLoader; // 依赖注入的 ResourceLoader

    private static ResourceLoader staticResourceLoader; // 静态 ResourceLoader

    // 使用 @PostConstruct 初始化静态字段
    @PostConstruct
    public void init() {
        staticResourceLoader = this.resourceLoader;
    }

    // 生成 OAuth 2.0 token
    private static String generateOAuth2Token() throws IOException {

        // 使用静态 resourceLoader 加载 credentials.json
        Resource resource = staticResourceLoader.getResource("classpath:/credentials.json");
        InputStream credentialsStream = resource.getInputStream();

        GoogleCredentials credentials = UserCredentials.fromStream(credentialsStream);
        credentials.refreshIfExpired();
        AccessToken token = credentials.getAccessToken();
        return token.getTokenValue();
    }


    public static void sendAuthCodeEmail(String email, String validateCode){
        try {
            // 配置邮件属性
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", GMAIL_SMTP_SERVER);
            props.put("mail.smtp.port", "587");

            // 创建邮件会话
            Session session = Session.getInstance(props);
            Message message = new MimeMessage(session);

            // 设置发件人、收件人、主题和邮件正文
            message.setFrom(new InternetAddress(FROM_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
            message.setSubject("Your Verification Code");
            message.setText("Your verification code is: " + validateCode);
            message.setSentDate(new Date());

            // 通过 Gmail 服务器发送邮件
            SMTPTransport transport = (SMTPTransport) session.getTransport("smtp");
            transport.connect(GMAIL_SMTP_SERVER, OAUTH_USER, generateOAuth2Token());
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

        } catch (AddressException e) {
            // 捕获 AddressException

        } catch (Exception e) {
            // 捕获其他异常
            e.printStackTrace();

        }

    }


}

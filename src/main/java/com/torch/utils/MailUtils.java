package com.torch.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

/**
 * @author: Torch
 * @Date: 2023/06/03 09:26
 * @Description:
 */
@Component
public class MailUtils {

    // 如果要使用邮箱，那么需要调用JAVA提供的邮箱配置
    @Autowired
    private JavaMailSenderImpl mailSender;

    // 发送邮件 需要发送人，接收人 邮件标题，邮件的主体信息

    // springboot读取配置恩建的信息、数据
    @Value("${spring.mail.username}")
    private String mailfrom;

    /**
     * 发送邮件
     * @param mailto 接收人
     * @param title 标题
     * @param content 内容
     */
    public void sendEmail(String mailto, String title, String content) {
        // 定制邮件发送内容
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(mailfrom);
        message.setTo(mailto);
        // 设置邮件标题
        message.setSubject(title);
        // 设置邮件主体信息
        message.setText(content);

        // 发送邮件
        mailSender.send(message);

    }
}

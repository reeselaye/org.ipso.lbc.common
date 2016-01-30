/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.utils;

import org.ipso.lbc.common.exception.AppUnCheckException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 李倍存 创建于 2015-04-04 16:10。电邮 1174751315@qq.com。
 */
public class LogMailingUtils {

    public static MimeMessage createMimeMessage(JavaMailSenderImpl sender, String from, String to, String subjectPrefix) throws AppUnCheckException {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(to);
            helper.setFrom(from);
            helper.setSubject(subjectPrefix + "  " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

            String dir= ResourcePathHelper.getAbsolutePath("../../../../log/");
            FileSystemResource err = new FileSystemResource(new File(dir+"lpu_error.log"));
            FileSystemResource inf = new FileSystemResource(new File(dir+"lpu_info.log"));


            helper.addAttachment("信息.txt", inf);
            helper.addAttachment("错误.txt", err);


        } catch (MessagingException e) {
            e.printStackTrace();
            throw new AppUnCheckException("创建邮件时发生异常" + e.getMessage());
        }
        return message;
    }

    public static MimeMessage createMimeMessage(JavaMailSenderImpl sender) throws AppUnCheckException {
        return createMimeMessage(sender, "2229184705@qq.com", "2229184705@qq.com", "负荷预测日志");
    }

    public static MimeMessage createMimeMessage(JavaMailSenderImpl sender, String subjectPrefix) throws AppUnCheckException {
        return createMimeMessage(sender, "2229184705@qq.com", "2229184705@qq.com", subjectPrefix);
    }

    public static JavaMailSenderImpl getDefaultSender() {
        ApplicationContext actx = new ClassPathXmlApplicationContext("spring-mail-appcontext.xml");
        return (JavaMailSenderImpl) actx.getBean("mailSender");
    }
}

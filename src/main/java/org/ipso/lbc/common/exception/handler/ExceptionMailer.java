/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.exception.handler;

import org.ipso.lbc.common.utils.LogMailingUtils;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.internet.MimeMessage;

/**
 * Created by LBC on 2015-04-05.
 */
public class ExceptionMailer extends ExceptionHandlerDecorator {

    public ExceptionMailer(ExceptionHandler handler) {
        this(handler, "");
    }

    private String prefix;

    public ExceptionMailer(ExceptionHandler handler, String subjectPrefix) {
        super(handler);
        this.prefix = subjectPrefix;

    }

    @Override
    public void handle(Throwable e) {
        handleWithMailing(e, prefix);
        super.handle(e);
    }

    private void handleWithMailing(Throwable ex, String prefix) {
        StackTraceElement[] traceElements = ex.getStackTrace();

        String text = "负荷预测过程发生异常。\n";
        text += prefix + "\n";
        text += ex.getMessage() + "\n";
        for (int i = 0; i < traceElements.length; i++) {
            text += traceElements[i].toString() + "\n";
        }
        JavaMailSenderImpl sender = LogMailingUtils.getDefaultSender();
        try {
            MimeMessage message = LogMailingUtils.createMimeMessage(sender, "异常报告");
            message.setText(text);
            sender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

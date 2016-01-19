/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.frameworks.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 李倍存 创建于 2015-05-09 12:09。电邮 1174751315@qq.com。
 */
public class MyFormAuthenticationFilter extends FormAuthenticationFilter {
    public MyFormAuthenticationFilter() {
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        try {
            WebUtils.issueRedirect(request,response,"/error.jsp");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return false;
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        PrintWriter writer= response.getWriter();
        writer.print("正在重定向至  ");

        if (subject.hasRole("adm")){
            writer.println("管理员主页");
            WebUtils.issueRedirect(request, response, "/adm_home.jsp");
        }

        if (subject.hasRole("user")){
            writer.println("普通用户主页");
            WebUtils.issueRedirect(request,response,"/index.jsp");
        }

        return false;
    }
}

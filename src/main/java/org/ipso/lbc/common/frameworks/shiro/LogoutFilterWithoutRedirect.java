/*
 * 版权�?�? (c) 2015 �? 李�?�存 （iPso）�??
 * �?有�?�对该文件所包含的代码的正确性�?�执行效率等任何方面不作任何保证�?
 * �?有个人和组织均可不受约束地将该文件所包含的代码用于非商业用�?��?�若�?要将其用于商业软件的�?发，请首先联系所有�?�以取得许可�?
 */

package org.ipso.lbc.common.shiro;

import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * 信息：李倍存 创建�? 2015/11/3 21:21。电�? 1174751315@qq.com�?<br>
 * 说明�?
 */
public class LogoutFilterWithoutRedirect extends LogoutFilter {
    public LogoutFilterWithoutRedirect() {
    }

    /**
     * Acquires the currently executing {@link #getSubject(javax.servlet.ServletRequest, javax.servlet.ServletResponse) subject},
     * a potentially Subject or request-specific
     * {@link #getRedirectUrl(javax.servlet.ServletRequest, javax.servlet.ServletResponse, org.apache.shiro.subject.Subject) redirectUrl},
     * and redirects the end-user to that redirect url.
     *
     * @param request  the incoming ServletRequest
     * @param response the outgoing ServletResponse
     * @return {@code false} always as typically no further interaction should be done after user logout.
     * @throws Exception if there is any error.
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        response.flushBuffer();
        return super.preHandle(request,response);
    }
}

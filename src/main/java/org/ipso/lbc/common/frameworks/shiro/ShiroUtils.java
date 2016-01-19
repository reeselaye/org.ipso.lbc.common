/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.frameworks.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * 李倍存 创建于 2015-05-27 21:10。电邮 1174751315@qq.com。
 */
public class ShiroUtils {
    public static String getCurrentUserName(){

        Subject subject= null;
        String notFound="UNKNOWN";
        try {
            subject = SecurityUtils.getSubject();
        }
// catch (UnavailableSecurityManagerException e) {
//
//        }
        catch (Exception e){
            return notFound;
        }
        if (subject==null)
            return notFound;
        Object principle=subject.getPrincipal();
        if (principle==null)
            return notFound;
        return principle.toString();
    }
}

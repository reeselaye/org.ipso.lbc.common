/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.frameworks.ucanaccess;

import net.ucanaccess.jdbc.UcanaccessDataSource;
import org.ipso.lbc.common.resource.CommonPaths;
import org.ipso.lbc.common.utils.ResourcePathHelper;

/**
 * 信息：李倍存 创建于 2015-07-05 11:35。电邮 1174751315@qq.com。<br>
 * 说明：
 */
public class MyDataSource extends UcanaccessDataSource{
    public MyDataSource() {
    }


    private String accessName;

    public String getAccessName() {
        return accessName;
    }

    public void setAccessName(String accessName) {
        this.accessName = accessName;
        setAccessPath(CommonPaths.getContextData() + accessName);
    }

    @Override
    public void setAccessPath(String accessPath) {
        String realpath=accessPath;
        if (accessPath.length() >10 && accessPath.substring(0,10).equals("classpath:")){
            String classPath= ResourcePathHelper.getAbsolutePath("");
            realpath=classPath+accessPath.substring(10);
        } else if (accessPath.length()>5 && accessPath.substring(0,5).equals("data:")){
            realpath = CommonPaths.getContextData()+accessPath.substring(5);
        }

        super.setAccessPath(realpath);
    }
}

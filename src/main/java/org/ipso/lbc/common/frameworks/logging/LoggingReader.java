/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.frameworks.logging;

import org.ipso.lbc.common.exception.FileAccessException;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * 信息：李倍存 创建于 2016/01/11 11:09。电邮 1174751315@qq.com。<br>
 * 说明：
 */
public class LoggingReader {
    public LoggingReader() {
    }

    public static Object[] getLines(String path){
        List<String> lines = new LinkedList<String>();
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(new File(path)), "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str;
            while((str = bufferedReader.readLine()) != null) {
                lines.add(str);
            }
            return lines.toArray();
        } catch (FileNotFoundException e) {
            throw new FileAccessException("找不到日志文件：" + path, e);
        } catch (Exception e){
            throw new FileAccessException(e);
        }
    }
}

/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.utils.file;

import org.ipso.lbc.common.exception.AppUnCheckException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 信息：李倍存 创建于 2016/01/09 21:26。电邮 1174751315@qq.com。<br>
 * 说明：
 */
public class FileSystemAndResourceUtils {
        public static List<File> getAllFilesIn(String dir){
            File file = new File(dir);
            File[] entities = file.listFiles();
            List<File> files = new LinkedList<File>();
            for (int i = 0; i < entities.length; i++) {
                File f = entities[i];
                if (f.isFile()){
                    files.add(f);
                }
            }
            return files;
        }
        public static void deleteAllFilesIn(String dir){
            List<File> files = getAllFilesIn(dir);
            for (int i = 0; i < files.size(); i++) {
                files.get(i).delete();
            }
        }

        public static List<InputStream> getAllResources(String url){
            Enumeration<URL> urls = null;
            List<InputStream> iss = new LinkedList<InputStream>();
            try {
                urls = FileSystemAndResourceUtils.class.getClassLoader().getResources(url);
                while (urls.hasMoreElements()){
                    iss.add(urls.nextElement().openStream());
                }
            } catch (IOException e) {
                throw new AppUnCheckException(e);
            }
            return iss;
        }
        public static void closeAll(List<InputStream> iss){
            try {
                for (int i = 0; i < iss.size(); i++) {
                    iss.get(i).close();
                }
            } catch (IOException e) {
                throw new AppUnCheckException(e);
            }
        }
}

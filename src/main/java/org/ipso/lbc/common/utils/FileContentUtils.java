/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.utils;

import java.io.*;
import java.util.Date;
import java.util.Random;

/**
 * 李倍存 创建于 2015/3/17 20:12。电邮 1174751315@qq.com。
 */
public class FileContentUtils {
    public static String toWebContentFilePath(String path) {
        Integer i = path.indexOf("web");
        return path.substring(i + 3);
    }

    public static String toFileSystemFilePath(String path) {
        return null;
    }

    public static String autoFileName(String prefix, String ext) {
        return autoFileName(prefix, ext, 0);

    }

    public static String autoFileName(String prefix, String ext, Integer randomTextLength) {
        String rand = "" + (Math.abs(new Random(new Date().getTime()).nextInt()));
        rand = rand.substring(0, randomTextLength);
        return prefix + rand.substring(0) + ext;
    }


    public static void copyFromFileSystem2WebContent(String filePath, String WebContent) {
        String webContentRoot = "D:\\Apache Software Foundation\\Tomcat 7.0\\webapps\\TEST_war";

        String fileName = getFileNameFromPath(filePath);
        String outputPath = webContentRoot + WebContent + fileName;

        File in = new File(filePath);
        File out = new File(outputPath);
        if (!in.exists()) {
            System.out.println(in.getAbsolutePath() + "源文件路径错误！！！");
            return;
        } else {
            System.out.println("源文件路径" + in.getAbsolutePath());
            System.out.println("目标路径" + out.getAbsolutePath());
        }

        FileInputStream fin = null;
        FileOutputStream fout = null;
        try {
            fin = new FileInputStream(in);
            fout = new FileOutputStream(out);

            int c;
            byte[] b = new byte[1024 * 5];
            while ((c = fin.read(b)) != -1) {
                fout.write(b, 0, c);
            }
            fin.close();
            fout.flush();
            fout.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getFileNameFromPath(String path) {
        Integer i = path.lastIndexOf("/");
        return path.substring(i + 1);
    }
}

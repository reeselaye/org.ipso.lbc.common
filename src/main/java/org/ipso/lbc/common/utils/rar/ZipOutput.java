/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.utils.rar;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 李倍存 创建于 2015-05-01 20:35。电邮 1174751315@qq.com。
 */
public class ZipOutput {
    public ZipOutput(String zipFilePath) {
        try {
            zipOutputStream=new ZipOutputStream(new FileOutputStream(zipFilePath));
            zipOutputStream.setEncoding("GBK");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    ZipOutputStream zipOutputStream;
    public void addFileThenDeleteIt(String filePath){
        try {
            File file=new File(filePath);
            zip(file,zipOutputStream);
            file.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void deleteFile(String path){
    File file=new File(path);
    file.deleteOnExit();
}
    public void close(){
        try {
            zipOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private  void zip(File inputFile, ZipOutputStream out) throws IOException {
            out.putNextEntry(new ZipEntry(inputFile.getName()));
            FileInputStream in = new FileInputStream(inputFile);
            try {
                int c;
                byte[] by = new byte[4096];
                while ((c = in.read(by)) != -1) {
                    out.write(by, 0, c);
                }
            } catch (IOException e) {
                throw e;
            } finally {
                in.close();
            }
        }
    }


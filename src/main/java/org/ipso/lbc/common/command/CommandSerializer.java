/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.command;

import org.ipso.lbc.common.exception.AppUnCheckException;
import org.ipso.lbc.common.resource.CommonPaths;
import org.ipso.lbc.common.utils.file.FileSystemUtils;

import java.io.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.ipso.lbc.common.frameworks.logging.LoggingFacade.warn;
/**
 * 信息：李倍存 创建于 2016/01/09 21:06。电邮 1174751315@qq.com。<br>
 * 说明：
 */
public class CommandSerializer {
    private boolean needUpdate = true;
    private String workingDir = CommonPaths.WEB_CONTENT_CACHES();
    public static CommandSerializer INSTANCE = new CommandSerializer();
    private static Date lastErrorTime = new Date();

    private CommandSerializer() {
        //FileSystemUtils.deleteAllFilesIn(workingDir);
    }

    List<BasicCommand> commands = new LinkedList<BasicCommand>();
    public List<BasicCommand> getAllSerializedCommands(){
        if (needUpdate){
            commands.clear();
            List<File> files = FileSystemUtils.getAllFilesIn(workingDir);
            for (int i = 0; i < files.size(); i++) {
                File file = files.get(i);
                try {
                    commands.add(readCommand(file));
                } catch (Exception e) {
                    warn("Command failure is deleted from {" + file.getName()+"}");
                    file.delete();
                }
            }
            needUpdate = false;
        }
        return commands;

    }
    public void serialize(BasicCommand command){
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(workingDir + new Date().getTime() + command.hashCode()));
            objectOutputStream.writeObject(command);
            objectOutputStream.close();
            needUpdate = true;
        } catch (IOException e) {
            throw new AppUnCheckException("无法保存序列化命令。");
        }

    }
    public void delete(BasicCommand command){
        List<File> files = FileSystemUtils.getAllFilesIn(workingDir);
        for (int i = 0; i < files.size(); i++) {
            File f = files.get(i);
            try {
                BasicCommand fObj = readCommand(f);
                if (command.equals(fObj)){
                    if (!f.delete()){
                        throw new AppUnCheckException("无法删除文件 " + f.getAbsolutePath());
                    }
                }
            } catch (Exception e) {
                Date now = new Date();
                if (now.getTime() - lastErrorTime.getTime() > 43200000){
                    warn("Cannot delete the failure in {" + f.getAbsolutePath() + "}.");
                    lastErrorTime = now;
                }

            }
        }
    }

    private BasicCommand readCommand(File file) throws Exception{
        FileInputStream fileInputStream = null;
        Object obj = null;
        boolean ex = false;
        try {
            fileInputStream =  new FileInputStream(file);
            obj = new ObjectInputStream(fileInputStream).readObject();
        }catch (Exception e){
            if (fileInputStream != null){
                fileInputStream.close();
            }
            throw new AppUnCheckException(e);
        } finally {
            if (fileInputStream != null){
                fileInputStream.close();
            }
        }

        return  (BasicCommand)(obj);
    }
}

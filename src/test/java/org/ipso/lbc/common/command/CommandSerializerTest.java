/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.command;

import org.ipso.lbc.common.command.BasicCommand;
import org.ipso.lbc.common.command.CommandSerializer;
import org.junit.Test;

import java.util.List;

public class CommandSerializerTest {

    @Test
    public void testGetAllSerializedCommands() throws Exception {
        List<BasicCommand> commands = CommandSerializer.INSTANCE.getAllSerializedCommands();

    }

    @Test
    public void testSerialize() throws Exception {

    }
}
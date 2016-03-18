/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.utils;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class ResourcePathHelperTest {

    @Test
    public void testGetAbsolutePath() throws Exception {


        File file = new File(ResourcePathHelper.getAbsolutePath("dir/does/not/exists/"));
        assertEquals(true, file.exists());

        file = new File(ResourcePathHelper.getAbsolutePath("/file/does/not/exists.tmp"));


        file = new File(ResourcePathHelper.getAbsolutePath("../../dir/does/not/exists/"));
        assertEquals(true, file.exists());

        file = new File(ResourcePathHelper.getAbsolutePath("/../../file/does/not/exists.tmp"));





    }

    @Test
    public void testGetClasspath1() throws Exception {

    }

    @Test
    public void testGetAbsolutePath1() throws Exception {

    }
}
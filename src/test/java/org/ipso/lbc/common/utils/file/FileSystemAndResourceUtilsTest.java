package org.ipso.lbc.common.utils.file;

import org.junit.Test;

import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class FileSystemAndResourceUtilsTest {

    @Test
    public void testGetAllResources() throws Exception {
        List<InputStream> res = FileSystemAndResourceUtils.getAllResources("classpath*:*.properties");
        assertTrue(res.size()>1);
    }
}
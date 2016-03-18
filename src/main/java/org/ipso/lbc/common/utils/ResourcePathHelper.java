

package org.ipso.lbc.common.utils;
import org.ipso.lbc.common.utils.file.FileSystemAndResourceUtils;

/**
 * Created by Beicun Li (John Resse, iPso), 2015-05-26 9:24. Contact mister.resse@outlook.com.<br>
 */
public class ResourcePathHelper {
    public static String getAbsolutePath(String relativePath){
        return FileSystemAndResourceUtils.getAbsolutePath(relativePath);
    }
}



package org.ipso.lbc.common.utils.file;

import org.ipso.lbc.common.exception.AppUnCheckException;
import org.ipso.lbc.common.frameworks.logging.LoggingFacade;
import org.ipso.lbc.common.utils.StringUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Beicun Li (John Resse, iPso), 2016/01/09 21:26. Contact mister.resse@outlook.com.<br>
 */
public class FileSystemAndResourceUtils {
    public static List<File> getAllFilesIn(String dir) {
        File file = new File(dir);
        File[] entities = file.listFiles();
        List<File> files = new LinkedList<File>();
        for (int i = 0; i < entities.length; i++) {
            File f = entities[i];
            if (f.isFile()) {
                files.add(f);
            }
        }
        return files;
    }

    public static void deleteAllFilesIn(String dir) {
        List<File> files = getAllFilesIn(dir);
        for (int i = 0; i < files.size(); i++) {
            files.get(i).delete();
        }
    }

    public static List<InputStream> getAllResources(String urlPattern) {
        Resource[] resources;
        List<InputStream> iss = new LinkedList<InputStream>();
        try {
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            resources = resolver.getResources(urlPattern);
            for (Resource r : resources) {
                iss.add(r.getInputStream());
            }

        } catch (IOException e) {
            throw new AppUnCheckException(e);
        }
        return iss;
    }

    public static void closeAll(List<InputStream> iss) {
        try {
            for (int i = 0; i < iss.size(); i++) {
                iss.get(i).close();
            }
        } catch (IOException e) {
            throw new AppUnCheckException(e);
        }
    }


    private static ClassLoader getClassLoader() {
        return FileSystemAndResourceUtils.class.getClassLoader();
    }

    /**
     * Get the CLASSPATH ending with a '/'.
     *
     * @return the CLASSPATH
     */
    public static String getClasspath() {
        URL url = getClassLoader().getResource("");
        if (url == null) {
            throw new AppUnCheckException("This error should never happens!");
        }
        String urlStr = url.toString();
        String classpath = StringUtils.removeFromHead(urlStr, "/", 1, true);
        return classpath;
    }

    /**
     * Build the absolute path relative to CLASSPATH of a given relative path.
     *
     * @param relativePath the given relative path, if it is a directory it must end with '/', or it would be regarded as a file. If the directory or its parent directories does not exists, it will be created automatically
     * @return the absolute path relative to CLASSPATH.
     */
    public static String getAbsolutePath(String relativePath) {
        String classpath = getClasspath();
        String path;
        String directory;

        // check if it is a directory or a file
        Boolean isDir = relativePath.endsWith("/");

        // remove the unnecessary prefix '/'
        if (relativePath.startsWith("/")) {
            relativePath = StringUtils.removeFromHead(relativePath, "/", 1, true);
        }

        // if the directory is out of insight
        if (relativePath.contains("../")) {
            int count = StringUtils.existCount(relativePath, "../");
            classpath = StringUtils.removeFromTail(classpath, "/", count + 1);
            relativePath = StringUtils.removeFromHead(relativePath, "/", count, true);
        }
        path = classpath + relativePath;
        if (isDir) {
            directory = path;
        } else {
            directory = StringUtils.removeFromTail(path, "/", 1, false);
        }
        createIfDirDoesNotExists(directory);
        return path;

    }

    /**
     * Check if the given directory exists, if not then create it.
     *
     * @param dir the given directory
     */
    private static void createIfDirDoesNotExists(String dir) {
        File file = new File(dir);
        if (!file.exists()) {
            Boolean success = file.mkdirs();
            if (!success) {
                LoggingFacade.warn("Try to create " + dir + ", but failed.");
            } else {
                LoggingFacade.info("The directory " + dir + " does not exists, and we create it automatically.");
            }
        }
    }

}

/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.utils;

import org.ipso.lbc.common.exception.AppUnCheckException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

/**
 * 李倍存 创建于 2015-05-26 9:24。电邮 1174751315@qq.com。
 */
public class ResourcePathHelper {
        /**
         *得到类加载器
         *@return
         */
        private static ClassLoader getClassLoader() {
            ClassLoader classLoader=ResourcePathHelper.class.getClassLoader();
            return classLoader;
        }


        private static InputStream getStream(String relativePath) throws  IOException {
            if(!relativePath.contains("../")){
                return getClassLoader().getResourceAsStream(relativePath);

            }else{
                return ResourcePathHelper.getStreamByExtendResource(relativePath);
            }

        }
        /**
         *
         *@paramurl
         *@return
         *@throwsIOException
         */
        private static InputStream getStream(URL url) throws IOException{
            if(url!=null){

                return url.openStream();


            }else{
                return null;
            }
        }
        /**
         *
         *@paramrelativePath必须传递资源的相对路径。是相对于classpath的路径。如果需要查找classpath外部的资源，需要使用../来查找
         *@return
         *@throwsMalformedURLException
         *@throwsIOException
         */
        private static InputStream getStreamByExtendResource(String relativePath) throws IOException{
            return ResourcePathHelper.getStream(ResourcePathHelper.getExtendResource(relativePath));


        }

        public static Properties getProperties(String relativePath) {
            Properties properties = new Properties();
            try {
                properties.load(getStreamByExtendResource(relativePath));
            } catch (IOException e) {
                throw new RuntimeException("couldn't load properties file '"+relativePath+"'", e);
            }
            return properties;
        }
        /**
         *得到本Class所在的ClassLoader的Classpath的绝对路径。
         *URL形式的
         *@return
         */
        private static String getAbsolutePathOfClassLoaderClassPath(){
            return ResourcePathHelper.getClassLoader().getResource("").toString();

        }
        /**
         * 使用相对路径定位资源。
         *@param relativePath 资源的相对于classpath的路径。若资源位于classpath以外，则使用../。
         *@return 资源的绝对路径URL
         */
        public static URL getExtendResource(String relativePath){
            if(!relativePath.contains("../")){
                return ResourcePathHelper.getResource(relativePath);
            }
            String classPathAbsolutePath=ResourcePathHelper.getAbsolutePathOfClassLoaderClassPath();
            if(relativePath.substring(0, 1).equals("/")){
                relativePath=relativePath.substring(1);
            }
            String wildcardString=relativePath.substring(0,relativePath.lastIndexOf("../")+3);
            relativePath=relativePath.substring(relativePath.lastIndexOf("../")+3);
            int containSum=ResourcePathHelper.containSum(wildcardString, "../");
            classPathAbsolutePath= ResourcePathHelper.cutLastString(classPathAbsolutePath, "/", containSum);
            String resourceAbsolutePath=classPathAbsolutePath+relativePath;
            URL resourceAbsoluteURL= null;
            try {
                resourceAbsoluteURL = new URL(resourceAbsolutePath);
            } catch (MalformedURLException e) {
                throw new AppUnCheckException("URL格式错误。",e);
            }
            return resourceAbsoluteURL;
        }

    /** 从相对路径构造绝对路径。
     * @param relativePath 相对路径，形如'org/ipso/x.x'。
     * @return 绝对路径。
     */
        public static String getAbsolutePath(String relativePath){
            try {
                return getExtendResource(relativePath).toURI().getPath().replaceFirst("/","").replaceAll("/","/");
            } catch (URISyntaxException e) {
                throw new AppUnCheckException("URI格式错误。",e);
            }catch (NullPointerException e){
                throw new AppUnCheckException("似乎找不到目录或文件 "+relativePath+" 。",e);
            }

        }

        private static int containSum(String source,String dest){
            int containSum=0;
            int destLength=dest.length();
            while(source.contains(dest)){
                containSum=containSum+1;
                source=source.substring(destLength);

            }


            return containSum;
        }
        private static String cutLastString(String source,String dest,int num){
            for(int i=0;i<num;i++){
                source=source.substring(0, source.lastIndexOf(dest, source.length()-2)+1);


            }
            return source;
        }

        private static URL getResource(String resource){
            URL url=ResourcePathHelper.getClassLoader().getResource(resource);

            return url;
        }
}

package org.ipso.lbc.common.utils;

import org.ipso.lbc.common.frameworks.logging.LoggingFacade;

/**
 * Created by Beicun Li (John Resse, iPso), 2016/3/17 12:58. Contact mister.resse@outlook.com.<br>
 */
public class StringUtils {
    public static int existCount(String src, String target){
        int r=0;
        int targetLength=target.length();
        while(src.contains(target)){
            r ++;
            src=src.substring(src.indexOf(target) +targetLength);
        }
        return r;
    }
    public static String removeFromTail(String src,String target,int th){
        return removeFromTail(src,target,th,false);
    }
    public static String removeFromTail(String src, String target, int th, Boolean includeTarget){
        th = validate(th, existCount(src,target));
        String r = new String(src);
        for(int i=0;i<th-1;i++){
            r=r.substring(0, r.lastIndexOf(target));
        }
        r = r.substring(0, r.lastIndexOf(target) + (includeTarget?0:1));
        return r;
    }
    public static String removeFromHead(String src, String target, int th){
        return removeFromHead(src, target, th, false);
    }
    public static String removeFromHead(String src, String target, int th, Boolean includeTarget){
        th = validate(th, existCount(src, target));
        String r = new String(src);
        for(int i=0;i<th-1;i++){
            r=r.substring(r.indexOf(target)+1);
        }
        r = r.substring(r.indexOf(target) + (includeTarget?1:0));
        return r;
    }
    private static Integer validate(Integer th, Integer existCount){
        if (th > existCount){
            LoggingFacade.debug("The th is an invalid value because it's greater than c, we make th=c here.(original c = " + existCount +", th = " + th + ")");
            return existCount;
        } else {
            return th;
        }

    }

}

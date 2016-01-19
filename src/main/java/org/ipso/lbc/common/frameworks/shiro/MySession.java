/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.frameworks.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SimpleSession;

import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 李倍存 创建于 2015-05-08 16:28。电邮 1174751315@qq.com。
 */
public class MySession {

    String id;
    String start;
    String lastAccess;
    Map<String,String> attrs;

    public Map<String, String> getAttrs() {
        return attrs;
    }

    public void setAttrs(Map<String, String> attrs) {
        this.attrs = attrs;
    }

    long timeOut;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(String lastAccess) {
        this.lastAccess = lastAccess;
    }

    public long getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(long timeOut) {
        this.timeOut = timeOut;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    String host;
    private static SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Session toSession(){
        SimpleSession session=new SimpleSession();

//        Object[] keys=getAttrs().keySet().toArray();
//        for (int i = 0; i < keys.length; i++) {
//            session.setAttribute(keys[i], (Object)getAttrs().get(keys[i]));
//        }
        session.setTimeout(getTimeOut());
        session.setHost(getHost());
        session.setId(getId());
        try {
            session.setLastAccessTime(format.parse(getLastAccess()));
            session.setStartTimestamp(format.parse(getStart()));
        } catch (ParseException e) {
            ;
        }
        return session;
    }
    public void sessionTo(Session session){
        Map<String,String> attrs=new HashMap<String, String>();
        Object[] keys=session.getAttributeKeys().toArray();
        for (int i = 0; i < keys.length; i++) {
            attrs.put(keys[i].toString(),session.getAttribute(keys[i]).toString());
        }
        setAttrs(attrs);
        setHost(session.getHost());
        setTimeOut(session.getTimeout());
        setId(session.getId().toString());
        setLastAccess(format.format(session.getLastAccessTime()));
        setStart(format.format(session.getStartTimestamp()));
    }

    public void print(PrintStream ps){
        ps.println("会话");
        ps.println("ID  " + id);
        ps.println("--start  "+start);
        ps.println("--lastAccess  "+lastAccess);
        ps.println("--timeOut  "+timeOut);
        ps.println("--host  "+host);
        Object[] keys=attrs.keySet().toArray();
        for (int i = 0; i <keys.length; i++) {
            ps.println("--attr " + keys[i].toString() + "," + attrs.get(keys[i]));
        }
    }
}

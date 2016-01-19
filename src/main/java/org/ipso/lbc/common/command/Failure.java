/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.command;

import org.ipso.lbc.common.utils.DateUtil;

import java.io.Serializable;

/**
 * 信息：李倍存 创建于 2015-10-07 05:34 PM。电邮 1174751315@qq.com。<br>
 * 说明：
 */
public class Failure implements Serializable{
    public Failure() {
    }

    public Failure(String params, String type, String id) {
        this.id = id;
        this.params = params;
        this.type = type;
        this.time = DateUtil.getNow();
        this.date = DateUtil.getToday();
    }
    public Failure(Object[] params, String type, String id){
        this("", type, id);
        setParams(params(params));
    }


    private String type;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String params;
    private String time;
    private String date;

    public String getParams() {
        return params;
    }
    public String getParam(Integer index){
        String[] params = getParams().split(",");
        return params[index];
    }

    public void setParams(String params) {
        this.params = params;
    }
    public void setParams(Object[] params){
        setParams(params(params));
    }

    private String params(Object[] params){
        String s = "";
        for (int i = 0; i < params.length; i++) {
            s += params[i] + ",";
        }
        return s;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void reassignTime(){
        this.time = DateUtil.getNow();
        this.date = DateUtil.getToday();
    }
}

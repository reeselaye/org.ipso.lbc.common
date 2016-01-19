/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */


package org.ipso.lbc.common.utils;

import java.util.ArrayList;

/**
 * Created by LBC on 2015/1/23.
 */
public interface IExcelUtils {
    void write(String file, String table, ArrayList<ArrayList<Object>> datas) throws Exception;

    void write(String file, String table, ArrayList<Object> header, ArrayList<ArrayList<Object>> datas) throws Exception;

    ArrayList<Object> readHeader(String file, String table) throws Exception;

    ArrayList<ArrayList<Object>> readBody(String file, String table) throws Exception;

    ArrayList<ArrayList<Object>> readAll(String file, String table) throws Exception;
}

/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.frameworks.dom4j;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.ipso.lbc.common.exception.ConfigurationException;

import java.io.File;

/**
 * 李倍存 创建于 2015-04-22 21:48。电邮 1174751315@qq.com。
 */
public class Dom4jFacade {
    public Dom4jFacade() {
    }

    public static Document readDocument(String xmlFilePath){

    SAXReader saxReader = new SAXReader();
        Document document;
        try {
            document = saxReader.read(new File(xmlFilePath));
        } catch (DocumentException e) {
            throw new ConfigurationException(e);
        }
        return document;
    }
}

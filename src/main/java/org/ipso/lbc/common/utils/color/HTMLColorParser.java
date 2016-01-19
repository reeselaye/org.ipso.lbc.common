/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.utils.color;


//import com.sun.xml.internal.fastinfoset.util.CharArrayString;

import java.awt.*;

/**
 * 李倍存 创建于 2015-04-14 21:40。电邮 1174751315@qq.com。
 */
public class HTMLColorParser implements IColorParser {
    public Color parse(String s) {
        if (s.indexOf("HTML:") == -1) {
            return null;
        }

        String c = s.substring(5);
        String r = c.substring(0, 2);
        String g = c.substring(2, 4);
        String b = c.substring(4, 6);

        return MyColor.getColor(r, g, b, 16);

    }

    /**
     * 根据HTML格式的颜色码获取对应的Color对象。
     * @param code 代表一种颜色值的形如“HTML:01234”格式的字符串
     * @return 对应于code的Color对象
     */

}

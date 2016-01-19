/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.utils.color;

//import com.sun.xml.internal.fastinfoset.util.CharArrayString;

import java.awt.*;

/**
 * 李倍存 创建于 2015-04-14 21:48。电邮 1174751315@qq.com。
 */
public class RGBColorParser implements IColorParser {
    @Override
    public Color parse(String s) {
        if (s.indexOf("RGB:") == -1) {
            return null;
        }
        String code = s.substring(4);
        String[] rgb = code.split(",");
        String r = rgb[0];
        String g = rgb[1];
        String b = rgb[2];

        return MyColor.getColor(r, g, b, 10);
    }
}

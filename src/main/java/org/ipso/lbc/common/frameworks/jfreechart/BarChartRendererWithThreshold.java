/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.frameworks.jfreechart;

import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;

import java.awt.*;

/**
 * 李倍存 创建于 2015-03-08 15:16。电邮 1174751315@qq.com。
 */
public class BarChartRendererWithThreshold extends BarRenderer {
    private Double threshold;

    public BarChartRendererWithThreshold(Double threshold) {
        this.threshold = threshold;
    }

    public Paint getItemPaint(int i, int j) {
        CategoryDataset categorydataset = this.getPlot().getDataset();
        Double d = categorydataset.getValue(i, j).doubleValue();
        if (d >= threshold)
            return Color.green;
        else
            return Color.red;

    }
}

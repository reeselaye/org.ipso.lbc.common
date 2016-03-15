/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.frameworks.jfreechart;

import org.ipso.lbc.common.ado.MaxAveMinTuple;
import org.ipso.lbc.common.command.AbstractReceiver;
import org.ipso.lbc.common.utils.FileContentUtils;
import org.ipso.lbc.common.utils.color.MyColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.CategoryTableXYDataset;

import javax.print.DocFlavor;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * 李倍存 创建于 2015-03-05 17:59。电邮 1174751315@qq.com。
 * 该类包装了JFreeChart框架的基本使用，约定仅从该类访问JFreeChart而不直接访问。
 */
public class JFreeChartFacade {

    public JFreeChart createLineChart(String title, String categoryLabel, String valueLabel, CategoryDataset ds) {
        return  this.createLineChart(title, categoryLabel, valueLabel, null, ds);
    }

    /** create a general LineChart with a given dataset and given lower and upper bounds.
     * @param title [no details]
     * @param categoryLabel [no details]
     * @param valueLabel [no details]
     * @param bound the given bounds
     * @param ds the given dataset
     * @return the created general LineChart
     */
    public JFreeChart createLineChart(String title, String categoryLabel, String valueLabel, MaxAveMinTuple<Double> bound, CategoryDataset ds){
        JFreeChart chart = ChartFactory.createLineChart(title, categoryLabel, valueLabel, ds, PlotOrientation.VERTICAL, true, true, true);
        return this.attachBound(chart, bound);

    }

    /** create a XYLineChart with a given dataset and lower and upper bounds.
     * @param title [do details]
     * @param categoryLabel [no details]
     * @param valueLabel [no details]
     * @param bound the given bounds, and the bound.max as upper bound, bound.min as lower bound, the bound.ave is not used in this case
     * @param ds the given dataset
     * @return the created chart
     */
    public JFreeChart createXYLineChart(String title, String categoryLabel, String valueLabel, MaxAveMinTuple<Double> bound, CategoryTableXYDataset ds){
        JFreeChart chart = ChartFactory.createXYLineChart(title,categoryLabel,valueLabel, ds, PlotOrientation.VERTICAL, true, true, true);
        return this.attachBound(chart, bound);

    }

    /** set lower and upper bounds for a given chart
     * @param chart the given chart
     * @param bound the lower and upper bounds
     * @return the original chart
     */
    private JFreeChart attachBound(JFreeChart chart, MaxAveMinTuple<Double> bound){
        if (!( bound == null||(bound.max.intValue() ==0 && bound.min.intValue() == 0) )){
            XYPlot xyPlot = chart.getXYPlot();
            ValueAxis valueAxis = xyPlot.getRangeAxis();
            valueAxis.setRange(bound.min, bound.max);
        }
        return chart;
    }

    /** create a DefaultCategoryDataset.
     * @return the created DefaultCategoryDataset
     */
    public DefaultCategoryDataset createDefaultCategoryDataset() {
        return new DefaultCategoryDataset();
    }

    /** create a CategoryTableXYDataset.
     * @return the created CategoryTableXYDataset
     */
    public CategoryTableXYDataset createCategoryTableXYDataset(){
        return new CategoryTableXYDataset();
    }




    /** save the given chart as a jpg.
     * @param chart the given chart
     * @param path the path to save the jpg, it must use a .jpg as extend; if the directory does not exists, it will be created
     * @param width the width(in pixels) of jpg
     * @param height the height(in pixels) of jpg
     * @return the path of the jpg actually saved
     */
    public String saveAs(JFreeChart chart, String path, Integer width, Integer height) {
        FileOutputStream out = null;
        try {

            File outFile = new File(path);
            if (!outFile.getParentFile().exists()) {

                outFile.getParentFile().mkdirs();

            }

            out = new FileOutputStream(path);

            // 保存为PNG

            ChartUtilities.writeChartAsPNG(out, chart, width, height);

            // 保存为JPEG

            // ChartUtilities.writeChartAsJPEG(out, chart, weight, height);

            out.flush();

        } catch (FileNotFoundException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    // do nothing
                }
            }
        }
        return path;
    }

    /** save a given chart as a jpg with default size.
     * @param chart the given chart
     * @param path the path to save the jpg, it must use a .jpg as extend; if the directory does not exists, it will be created
     * @return  the path of the jpg actually saved
     */
    public String saveAs(JFreeChart chart, String path) {
        return this.saveAs(chart, path, 1600, 1200);
    }

    /** save a given chart as a jpg with a auto-generated file name.
     * @param chart the given chart
     * @param dir the directory to save the jpg; if the directory does not exists, it will be created
     * @param filenamePrefix the prefix of the auto-generated file name, if you don't need a prefix, pass a ""
     * @return  the path of the jpg actually saved
     */
    public String saveAsAutoFileName(JFreeChart chart, String dir, String filenamePrefix) {
        String path = dir + FileContentUtils.autoFileName(filenamePrefix, ".jpg");
        return this.saveAs(chart, path);
    }

    /** decorate a given chart with default theme.
     * @param chart the given chart
     * @param seriesNbr how many series of data the given chart has
     */
    public JFreeChart decorate(JFreeChart chart,Integer seriesNbr){
        XYPlot xyPlot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        BasicStroke dotLine = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1.0F, new float[]{3.F, 3.F}, 1.0F);
        BasicStroke line = new BasicStroke(2);


        for (int i = 0; i < seriesNbr; i++) {
            renderer.setSeriesStroke(i, line);
            renderer.setSeriesShapesVisible(0, true);
        }
        renderer.setBasePaint(Color.BLACK);

        xyPlot.setRenderer(renderer);


        /*图形区域背景颜色*/
        xyPlot.setBackgroundPaint(MyColor.COMMON_BACKGROUND);
        chart.setBackgroundPaint(MyColor.COMMON_BACKGROUND);
        chart.setBorderPaint(Color.BLACK);

        xyPlot.setRangeGridlineStroke(new BasicStroke(1));
        xyPlot.setDomainGridlinesVisible(true);
        xyPlot.setRangeGridlinesVisible(true);
        xyPlot.setRangeGridlinePaint(MyColor.COMMON_GRID_LINE);
        xyPlot.setDomainGridlinePaint(MyColor.COMMON_GRID_LINE);


        ValueAxis valueAxis = xyPlot.getRangeAxis();
        valueAxis.setTickLabelPaint(MyColor.COMMON_FOREGROUND);
        valueAxis.setTickLabelFont(new Font("Arial", Font.BOLD, 16));
        valueAxis.setLabelFont(new Font("微软雅黑", Font.BOLD, 20));
        valueAxis.setLabelPaint(MyColor.COMMON_FOREGROUND);
        ValueAxis domainAxis = xyPlot.getDomainAxis();
        domainAxis.setTickLabelFont(new Font("Arial", Font.BOLD, 16));
        domainAxis.setTickLabelPaint(MyColor.COMMON_FOREGROUND);
        domainAxis.setLabelFont(new Font("微软雅黑", Font.BOLD, 20));
        domainAxis.setLabelPaint(MyColor.COMMON_FOREGROUND);

        chart.getTitle().setFont(new Font("Arial", Font.BOLD, 20));
        chart.getTitle().setPaint(MyColor.COMMON_FOREGROUND);

        return chart;
    }
}

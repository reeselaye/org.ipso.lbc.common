/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.math.interp;

/**
 * 信息：李倍存 创建于 2015/7/16 8:33。电邮 1174751315@qq.com。<br>
 * 说明：
 */
public class Lagrange {
    public Lagrange(Integer maxPoints) {
        xs = new Double[maxPoints];
        ys = new Double[maxPoints];
    }

    private Integer xIndex=0,yIndex=0;

    public Double calc(Double x0){
        Double y0 = 0.0;
        int n = xIndex;
        for (int j = 0; j < n; j++)
        {
            Double L = 1.0;
            for (int i = 0; i < n; i++)
            {
                if (i != j)
                {
                    L *= (x0-xs[i])/(xs[j] - xs[i]);
                }
            }
            y0 += L*ys[j];
        }

        return y0;
    }

    public Double[] calc(Double[] X){
        Integer size = X.length;
        Double[] Y = new Double[size];
        for (int i = 0; i < size; i++) {
            Y[i] = this.calc(X[i]);
        }

        return Y;

    }

    public void addPoints(Double[] X,Double[] Y){
        if (X.length!=Y.length){
            throw new IllegalArgumentException("X和Y向量的维数必须相等。");
        }
        Integer size = X.length;
        for (int i = 0; i < size; i++) {
            this.addPoint(X[i],Y[i]);
        }


    }

    public void addPoint(Double x0,Double y0){
        xs[xIndex] = x0;
        ys[yIndex] = y0;
        xIndex ++;
        yIndex ++;
    }
    private Double[] xs,ys;

}

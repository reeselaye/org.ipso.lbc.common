/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.db.dao;

/**
 * 李倍存 创建于 2015/2/19 19:52。电邮 1174751315@qq.com。
 */
public interface IReadOnlyDAO<PK, T> {

    /**
     * @param pk 主键
     * @return 根据给定主键从数据库检索到的对象图。
     * @throws Exception 当执行检索的过程中捕获其它内部异常时抛出。
     */
    T query(PK pk);
}

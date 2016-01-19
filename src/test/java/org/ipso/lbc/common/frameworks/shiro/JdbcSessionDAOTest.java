/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.frameworks.shiro;
import static org.mockito.Mockito.*;
import org.apache.shiro.session.Session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class JdbcSessionDAOTest {

//    @Test
    public void testDoCreate() throws Exception {
        Session mock=mock(Session.class);
        when(mock.getId()).thenReturn("TEST_SESSION_ID");
        when(mock.getHost()).thenReturn("1.185.17.31");
        Collection<Object> keys=new ArrayList<Object>();
        keys.add("USERNAME");
        keys.add("PASSWORD");

        when(mock.getAttributeKeys()).thenReturn(keys);
        when(mock.getAttribute("USERNAME")).thenReturn("LBC");
        when(mock.getAttribute("PASSWORD")).thenReturn("ROOT");
        when(mock.getLastAccessTime()).thenReturn(new Date());
        when(mock.getStartTimestamp()).thenReturn(new Date());
        when(mock.getTimeout()).thenReturn(36000000l);

        JdbcSessionDAO dao=new JdbcSessionDAO();
        dao.doCreate(mock);
    }

//    @Test
    public void testDoRead() throws Exception {
        JdbcSessionDAO dao=new JdbcSessionDAO();
        Session session=dao.doReadSession("TEST_SESSION_ID");
        MySession mySession=new MySession();
        mySession.sessionTo(session);
        mySession.print(System.out);
    }
}
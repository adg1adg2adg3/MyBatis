package tk.mybatis.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import tk.mybatis.domain.sys_privilege.SysPrivilege;
import tk.mybatis.mapper.PrivilegeMapper;

import java.io.IOException;
import java.io.Reader;

public class PrivilegeMapperTest {

    private static SqlSessionFactory sqlSessionFactory;
    private static SqlSession sqlSession;

    @Before
    public void init(){
        try {
            /* 使用reader将MyBatis配置文件传入并获取工厂类 */
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            reader.close();
            sqlSession = sqlSessionFactory.openSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSelectById(){
        PrivilegeMapper mapper = sqlSession.getMapper(PrivilegeMapper.class);
        SysPrivilege sysPrivilege = mapper.selectById(1L);
        System.out.println(sysPrivilege);
        sqlSession.close();
    }
}

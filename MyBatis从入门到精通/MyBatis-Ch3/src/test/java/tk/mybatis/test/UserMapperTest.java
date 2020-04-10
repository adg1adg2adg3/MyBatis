package tk.mybatis.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import tk.mybatis.domain.sys_role.SysRole;
import tk.mybatis.domain.sys_user.SysUser;
import tk.mybatis.mapper.UserMapper;

import java.io.IOException;
import java.io.Reader;
import java.util.Date;
import java.util.List;

public class UserMapperTest {

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
        /* getMapper产生动态代理对象 */
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        /* 使用方法并获取返回值 */
        SysUser user = userMapper.selectById((long) 1);
        System.out.println(user);

        sqlSession.close();
    }

    @Test
    public void testSelectAll(){
        /* getMapper产生动态代理对象 */
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        /* 使用方法并获取返回值 */
        List<SysUser> sysUsers = userMapper.selectAll();

        for (SysUser sysuser : sysUsers){
            System.out.println(sysuser);
        }

        sqlSession.close();
    }

    @Test
    public void testSelectRoleByUserId(){
        /* getMapper产生动态代理对象 */
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        /* 使用方法并获取返回值 */
        List<SysRole> sysRoles = userMapper.selectRoleByUserId((long) 1);

        for (SysRole sysRole : sysRoles) {
            System.out.println(sysRole);
        }

        sqlSession.close();
    }

    @Test
    public void testSelectRoleAndUserByUserId(){
        /* getMapper产生动态代理对象 */
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        /* 使用方法并获取返回值 */
        List<SysRole> sysRoles = userMapper.selectRoleAndUserByUserId((long) 1);

        for (SysRole sysRole : sysRoles) {
            System.out.println(sysRole);
        }

        sqlSession.close();
    }

    @Test
    public void testInsert(){
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        SysUser user = new SysUser();
        user.setUserName("testForInsert");
        user.setUserPassword("123");
        user.setUserEmail("testInsert@mybatis.com");
        user.setUserInfo("test info");
        user.setHeadImg(new byte[] {1,2,3});
        user.setCreateTime(new Date());
        int insert = userMapper.insert(user);
        sqlSession.commit();
    }

    @Test
    public void insertAndGetKey_1(){
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        SysUser user = new SysUser();
        user.setUserName("testForInsertAndGetKey_1");
        user.setUserPassword("123455");
        user.setUserEmail("testInsertAndGetKey_1@mybatis.com");
        user.setUserInfo("test info");
        user.setHeadImg(new byte[] {1,2,3});
        user.setCreateTime(new Date());
        userMapper.insertAndGetKey_1(user);

        //插入到数据库后获得主键
        System.out.println(user.getId());

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testUpdateById(){
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        SysUser user = userMapper.selectById((long) 1004);
        user.setUserName("aadasdsa");
        userMapper.updateById(user);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testDeleteById(){
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.deleteById((long) 1004);
        sqlSession.commit();
    }

    @Test
    public void testSelectRoleByUserIdAndRoleEnabled(){
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<SysRole> sysRoles = userMapper.selectRoleByUserIdAndRoleEnabled(1L, 1);
        for (SysRole sysRole: sysRoles) {
            System.out.println(sysRole);
        }
    }

    @Test
    public void testSelectRoleByUserIdAndRoleEnabled_Bean(){
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        SysUser sysUser = new SysUser();
        sysUser.setId(1L);
        SysRole sysRole = new SysRole();
        sysRole.setEnabled(1);
        List<SysRole> sysRoles = userMapper.selectRoleByUserIdAndRoleEnabled_Bean(sysUser, sysRole);
        for (SysRole result: sysRoles) {
            System.out.println(result);
        }
    }








}

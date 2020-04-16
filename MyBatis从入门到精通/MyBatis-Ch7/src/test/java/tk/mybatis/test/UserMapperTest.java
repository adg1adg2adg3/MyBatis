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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
        SysUser user = userMapper.selectById((long) 1002);
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
            System.out.println(userMapper.selectById(sysuser.getId()));
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

    @Test
    public void testDynamicSelectByUser(){
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        SysUser sysUser = new SysUser();
        //用户名模糊查询
        sysUser.setUserName("e");
        List<SysUser> sysUsers1 = mapper.dynamicSelectByUser(sysUser);
        for (SysUser user : sysUsers1){
            System.out.println("用户名模糊查询: " + user);
        }
        //使用邮箱查询
        sysUser.setUserName(null);
        sysUser.setUserEmail("test@mybatis.tk");
        List<SysUser> sysUsers2 = mapper.dynamicSelectByUser(sysUser);
        for (SysUser user : sysUsers2){
            System.out.println("使用邮箱查询: " + user);
        }
        //同时使用用户名和邮箱查询
        sysUser.setUserName("test");
        List<SysUser> sysUsers3 = mapper.dynamicSelectByUser(sysUser);
        for (SysUser user : sysUsers3){
            System.out.println("同时使用用户名和邮箱查询: " + user);
        }

        sqlSession.close();
    }

    @Test
    public void testDynamicUpdateByIdSelective(){

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        SysUser sysUser = mapper.selectById(1002L);
        System.out.println("修改前" + sysUser);

        sysUser.setUserName("testDynamicUpdateByIdSelective");
        mapper.dynamicUpdateByIdSelective(sysUser);

        SysUser sysUser1 = mapper.selectById(1002L);
        System.out.println("修改后" + sysUser1);
        //没提交回滚了，没有真正更新
        sqlSession.close();

    }

    @Test
    public void testDynamicInsert(){

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        SysUser sysUser = new SysUser();
        sysUser.setUserName("asasas");

        mapper.dynamicInsert(sysUser);
        sqlSession.commit();

        System.out.println(mapper.selectById(sysUser.getId()));

    }

    @Test
    public void testDynamicSelectByIdOrUserName(){

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        //只设置用户名
        SysUser sysUser = new SysUser();
        sysUser.setUserName("admin");
        //只查询用户名
        SysUser result = mapper.dynamicSelectByIdOrUserName(sysUser);
        System.out.println(result);

        //只设置id
        sysUser.setUserName(null);
        sysUser.setId(1L);
        //只查询id
        SysUser sysUser1 = mapper.dynamicSelectByIdOrUserName(sysUser);
        System.out.println(sysUser1);

        //没有用户名与id时
        sysUser.setId(null);
        SysUser sysUser2 = mapper.dynamicSelectByIdOrUserName(sysUser);
        System.out.println(sysUser2);

    }

    @Test
    public void testSelectByIdList(){

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        ArrayList<Long> IdArrayList = new ArrayList<>();

        Long id_1 = 1L;
        Long id_2 = 2L;

        IdArrayList.add(id_1);
        IdArrayList.add(id_2);

        List<SysUser> sysUsers = mapper.selectByIdList(IdArrayList);

        for (SysUser sysUser: sysUsers){
            System.out.println(sysUser);
        }
    }

    @Test
    public void testInsertUserList(){

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        SysUser sysUser1 = new SysUser();
        SysUser sysUser2 = new SysUser();

        sysUser1.setUserName("aaa1");
        sysUser2.setUserName("bbb2");

        ArrayList<SysUser> sysUsers = new ArrayList<>();
        sysUsers.add(sysUser1);
        sysUsers.add(sysUser2);

        mapper.insertUserList(sysUsers);

        sqlSession.commit();

        for (SysUser sysUser: sysUsers){
            System.out.println(mapper.selectById(sysUser.getId()));
        }

        sqlSession.close();

    }

    @Test
    public void testUpdateByMap(){

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        HashMap<String, Object> user = new HashMap<>();

        user.put("user_name", "ccc");
        user.put("id",1005L);

        mapper.updateByMap(user);

        sqlSession.commit();
    }

    @Test
    public void testSelectUserAndRoleById(){

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        SysUser sysUser = mapper.selectUserAndRoleById(1001L);

        System.out.println(sysUser + sysUser.getRole().toString() + "");

        sqlSession.close();
    }

    @Test
    public void testSelectUserAndRoleByIdSelect(){

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        SysUser sysUser = mapper.selectUserAndRoleByIdSelect(1001L);

        System.out.println(sysUser + sysUser.getRole().toString());

    }

    @Test
    public void testSelectAllUserAndRoles(){

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        List<SysUser> sysUsers = mapper.selectAllUserAndRoles();

        for (SysUser sysUser:sysUsers){
            System.out.println(sysUser + sysUser.getRoleList().toString());
        }

    }












}

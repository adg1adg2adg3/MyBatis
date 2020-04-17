package tk.mybatis.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import tk.mybatis.domain.sys_role.SysRole;
import tk.mybatis.mapper.RoleMapper;

import java.io.IOException;
import java.io.Reader;
import java.util.Date;
import java.util.List;

public class RoleMapperTest {

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
        RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
        SysRole sysRole = mapper.selectById((long) 1);
        System.out.println(sysRole);
        sqlSession.close();
    }

    @Test
    public void testSelectAll(){
        RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
        List<SysRole> sysRoles = mapper.selectAll();
        for(SysRole sysRole: sysRoles){
            System.out.println(sysRole);
        }
        sqlSession.close();
    }

    @Test
    public void testInsert(){
        RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
        SysRole sysRole = new SysRole();
        sysRole.setId(3L);
        sysRole.setRoleName("testInsert");
        sysRole.setEnabled(1);
        sysRole.setCreateBy(1L);
        sysRole.setCreateTime(new Date());
        roleMapper.insert(sysRole);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testInsertWithKey_1(){
        RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
        SysRole sysRole = new SysRole();
        sysRole.setRoleName("testInsertWithKey_1");
        sysRole.setEnabled(1);
        sysRole.setCreateBy(2L);
        sysRole.setCreateTime(new Date());
        roleMapper.insertWithKey_1(sysRole);
        sqlSession.commit();
        System.out.println(sysRole.getId());
        sqlSession.close();
    }

    @Test
    public void testInsertWithKey_2(){
        RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
        SysRole sysRole = new SysRole();
        sysRole.setRoleName("testInsertWithKey_2");
        sysRole.setEnabled(1);
        sysRole.setCreateBy(3L);
        sysRole.setCreateTime(new Date());
        roleMapper.insertWhitKey_2(sysRole);
        sqlSession.commit();
        System.out.println(sysRole.getId());
        sqlSession.close();
    }

    @Test
    public void testUpdateById(){
        RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
        SysRole sysRole = new SysRole();
        sysRole.setId(7L);
        sysRole.setRoleName("testUpdateById");
        sysRole.setEnabled(1);
        sysRole.setCreateBy(3L);
        sysRole.setCreateTime(new Date());
        roleMapper.updateById(sysRole);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testDeleteById(){
        RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
        mapper.deleteById(7L);
        sqlSession.commit();
        sqlSession.close();
    }








}

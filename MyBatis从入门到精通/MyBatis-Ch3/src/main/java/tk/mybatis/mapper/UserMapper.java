package tk.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.domain.sys_role.SysRole;
import tk.mybatis.domain.sys_user.SysUser;

import java.util.List;

public interface UserMapper {

    //通过id查找用户
    SysUser selectById(Long id);

    //查询全部用户
    List<SysUser> selectAll();

    //根据用户id获取角色信息
    List<SysRole> selectRoleByUserId(Long userId);

    //根据用户id获取角色信息和用户信息
    List<SysRole> selectRoleAndUserByUserId(Long userId);

    //新增用户，返回值为影响的行数
    int insert(SysUser sysUser);

    /*我们想要插入用户后返回这个用户的主键，那么有两种方式：*/
/*--------------------------------------------------------*/
    //1、JDBC方式得到主键自增的值（只适合支持主键自增的数据库），返回值还是插入的条数
    int insertAndGetKey_1(SysUser sysUser);
    //2、<selectKey>得到主键（都适用），返回值还是插入的条数
    int insertAndGetKey_2(SysUser sysUser);
/*-------------------------------------------------------------------------*/

    //根据主键更新，返回值为影响的行数
    int updateById(SysUser sysUser);

    //通过主键删除，返回值为影响的行数
    int deleteById(Long id);

    /* 参数的类型可以分为两种，一种是基本类型，一种是JavaBean。
        当参数为基本类型时，XML文件中对应的SQL语句只会使用一个参数。
        当参数为JavaBean时，XML文件中对应的SQL语句可以使用JavaBean中的属性。
    且可以看到我们上面接口方法中的参数只有一个。当我们需要使用多个参数时，我们可以将多个参数合并到一个JavaBean中，
     也可以使用Map类型作为参数或使用@Param注解
     Map中的Key来映射XML中SQL使用的参数值名字，Value用来存放参数值。这种方法传递多个参数时比较麻烦。
     以下着重讲解使用@Param注解的使用方式
     */
    /* @Param将Long userId映射到#{userId}，Integer enabled映射到#{enabled}。
    * MyBatis自动封装Map类型，@Param中的值为Key，形参为Value。
    * 当只有一个参数时（除集合与数组）MyBatis会直接使用这个参数，并不关心这个参数叫什么名字。 */
    List<SysRole> selectRoleByUserIdAndRoleEnabled(
            @Param("userId") Long userId,
            @Param("enabled") Integer enabled
    );
    /* 参数为两个Bean类型Map中的Value为两个对象 */
    List<SysRole> selectRoleByUserIdAndRoleEnabled_Bean(
            @Param("user") SysUser userId,
            @Param("role") SysRole enabled
    );

    /* Mapper接口没有实现类为什么却能被正常调用？ */
    /* MyBatis在Mapper接口上使用了动态代理 */
}

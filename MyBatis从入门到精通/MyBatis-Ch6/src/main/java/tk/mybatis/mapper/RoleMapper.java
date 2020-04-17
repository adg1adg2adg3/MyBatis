package tk.mybatis.mapper;

import org.apache.ibatis.annotations.*;
import tk.mybatis.domain.sys_role.SysRole;

import java.util.List;

public interface RoleMapper {


    //使用注解与XML一样需要考虑表字段与Java属性映射，
    //有三种解决方式：1、Sql语句起别名
    //2、在mybatis-config，xml配置自动转换下划线转驼峰命名方式
    //3、使用@Results注解
    /*@Select("select id, role_name roleName, enabled," +
            "created_by createBy," +
            "create_time createTime" +
            "from sys_role" +
            "where id = #{id}")*/

    //@Results包裹@Result集合，@Result对应MapperXML的<result>标签。
    // id=true时，@Result对应MapperXML的<id>标签。
    //从MyBatis 3.3.1版本开始@Results标签增加了一个id属性，可以被其他@Results标签引用。
    //引用方法：@ResultMap("roleResultMap")
    @Results(id = "roleResultMap", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "roleName", column = "role_name"),
            @Result(property = "enabled", column = "enabled"),
            @Result(property = "createBy", column = "create_by"),
            @Result(property = "createTime", column = "create_time")
    })
    @Select("select id, role_name, enabled, create_by, create_time from sys_role where id = #{id}")
    SysRole selectById(Long id);

    @ResultMap("roleResultMap")
    @Select("select * from sys_role")
    List<SysRole> selectAll();

    @Insert({"insert into sys_role(id, role_name, enabled, create_by, create_time)",
    "values(#{id}, #{roleName}, #{enabled}, #{createBy}, #{createTime, jdbcType=TIMESTAMP})"})
    int insert(SysRole sysRole);

    /*<!-- 使用JDBC方式得到插入的主键 -->
    <!-- useGeneratedKeys设置为true后，MyBatis会使用JDBC的useGeneratedKeys方法取出数据库内部生成的主键 -->
    <!-- 获得主键后将其赋值给keyProperty配置的Java对象的属性(多个值用逗号隔开，且还需设置keyColumn属性按顺序指定数据库的列) -->*/
    @Insert({"insert into sys_role(role_name, enabled, create_by, create_time)",
            "values(#{roleName}, #{enabled}, #{createBy}, #{createTime, jdbcType=TIMESTAMP})"})
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertWithKey_1(SysRole sysRole);

/*    <!-- 使用<selectKey>得到主键 -->
    <!-- keyColumn、resultType、keyProperty这些属性的用法含义与上面说的相同 -->
    <!-- 注意的是order属性的值在MySql中要设置为AFTER，因为只有在插入成功后当前需要的值才能获取到 -->
    <!-- 在Oracle中要设置为BEFORE，因为Oracle要先从序列获取值，然后将值作为主键插入到数据库中 -->
    <!-- select last_insert_id()为独立的SQL语句，用于获取数据库中最后插入数据的ID值 -->*/
    @Insert({"insert into sys_role(role_name, enabled, create_by, create_time)",
            "values(#{roleName}, #{enabled}, #{createBy}, #{createTime, jdbcType=TIMESTAMP})"})
    @SelectKey(statement = "select last_insert_id()",
    keyProperty = "id",
    resultType = Long.class,
    before = false)
    int insertWhitKey_2(SysRole sysRole);

    //注意多行要用逗号分隔，且用大括号包裹
    @Update({"update sys_role set role_name = #{roleName}, " ,
            "enabled = #{enabled}," ,
            "create_by = #{createBy}, " ,
            "create_time = #{createTime, jdbcType=TIMESTAMP}" ,
            "where id = #{id}"})
    int updateById(SysRole sysRole);

    @Delete("delete from sys_role where id = #{id}")
    int deleteById(Long id);














}

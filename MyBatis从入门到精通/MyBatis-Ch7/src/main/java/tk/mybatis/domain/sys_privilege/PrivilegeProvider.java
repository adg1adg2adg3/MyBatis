package tk.mybatis.domain.sys_privilege;

import org.apache.ibatis.jdbc.SQL;

public class PrivilegeProvider {

    public String selectById(final Long id){
        return "select id, privilege_name, privilege_url from sys_privilege where id = #{id}";
    }
}

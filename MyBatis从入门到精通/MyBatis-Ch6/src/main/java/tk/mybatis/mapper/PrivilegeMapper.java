package tk.mybatis.mapper;

import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.domain.sys_privilege.PrivilegeProvider;
import tk.mybatis.domain.sys_privilege.SysPrivilege;

public interface PrivilegeMapper {

    @SelectProvider(type = PrivilegeProvider.class, method = "selectById")
    SysPrivilege selectById(Long id);
}

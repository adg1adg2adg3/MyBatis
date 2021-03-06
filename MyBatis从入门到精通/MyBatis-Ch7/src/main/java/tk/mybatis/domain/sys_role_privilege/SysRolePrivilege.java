package tk.mybatis.domain.sys_role_privilege;

public class SysRolePrivilege {

    private Long roleId;
    private Long privilegeId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(Long privilegeId) {
        this.privilegeId = privilegeId;
    }

    @Override
    public String toString() {
        return "SysRolePrivilege{" +
                "roleId=" + roleId +
                ", privilegeId=" + privilegeId +
                '}';
    }
}

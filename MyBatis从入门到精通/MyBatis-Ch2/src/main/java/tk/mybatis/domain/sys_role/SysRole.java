package tk.mybatis.domain.sys_role;

import tk.mybatis.domain.sys_user.SysUser;

import java.util.Date;

public class SysRole {

    private Long id;
    private String roleName;
    private Integer enabled;   //实体类中应该避免使用基本类型（基本类型在实例创建后有默认值导致不能判断为null）
    private Long createBy;
    private Date createTime;
    /*selectRoleAndUserByUserId——UserMapper.Java 需要返回交叉多个类的数据*/
    private SysUser user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SysRole{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", enabled=" + enabled +
                ", createBy=" + createBy +
                ", createTime=" + createTime +
                ", user=" + user +
                '}';
    }
}

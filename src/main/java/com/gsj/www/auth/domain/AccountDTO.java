package com.gsj.www.auth.domain;

import com.gsj.www.common.util.AbstractObject;

import java.util.Date;
import java.util.List;

/**
 * 账号DTO
 */
public class AccountDTO extends AbstractObject {
    /**
     * id
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 员工姓名
     */
    private String name;
    /**
     * 账号备注
     */
    private String remark;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 修改时间
     */
    private Date gmtModified;
    /**
     * 账号角色的关联关系
     */
    private List<AccountRoleRelationshipDTO> roleRelatins;
    /**
     * 账号权限的关联关系
     */
    private List<AccountPriorityRelationshipDTO> priorityRelations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public List<AccountRoleRelationshipDTO> getRoleRelatins() {
        return roleRelatins;
    }

    public void setRoleRelatins(List<AccountRoleRelationshipDTO> roleRelatins) {
        this.roleRelatins = roleRelatins;
    }

    public List<AccountPriorityRelationshipDTO> getPriorityRelations() {
        return priorityRelations;
    }

    public void setPriorityRelations(List<AccountPriorityRelationshipDTO> priorityRelations) {
        this.priorityRelations = priorityRelations;
    }
}

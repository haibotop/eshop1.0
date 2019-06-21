package com.gsj.www.auth.domain;

import java.util.Date;

/**
 * 角色和权限的关联关系DO类
 *
 * @author Holy
 * @create 2019 - 06 - 21 22:22
 */
public class RolePriorityRelationshipDO {
    /**
     * id
     */
    private Long id;
    /**
     * 角色id
     */
    private Long roleId;
    /**
     * 权限id
     */
    private Long priorityId;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 修改时间
     */
    private Date gmtModified;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getRoleId() {
        return roleId;
    }
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
    public Long getPriorityId() {
        return priorityId;
    }
    public void setPriorityId(Long priorityId) {
        this.priorityId = priorityId;
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
}

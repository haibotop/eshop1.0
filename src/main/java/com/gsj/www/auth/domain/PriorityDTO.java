package com.gsj.www.auth.domain;

import com.gsj.www.common.util.BeanCopierUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Objects;

/**
 * 权限DTO类
 *
 * @author Holy
 * @create 2019 - 06 - 20 22:51
 */
public class PriorityDTO {
    private static final Logger logger = LoggerFactory.getLogger(PriorityDTO.class);

    /**
     * id
     */
    private Long id;
    /**
     * 权限编号
     */
    private String code;
    /**
     * 权限URL
     */
    private String url;
    /**
     * 权限备注
     */
    private String priorityComment;
    /**
     * 权限类型
     */
    private Integer priorityType;
    /**
     * 父权限id
     */
    private Long parentId;
    /**
     * 权限的创建时间
     */
    private Date gmtCreate;
    /**
     * 权限的修改时间
     */
    private Date gmtModified;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getPriorityComment() {
        return priorityComment;
    }
    public void setPriorityComment(String priorityComment) {
        this.priorityComment = priorityComment;
    }
    public Integer getPriorityType() {
        return priorityType;
    }
    public void setPriorityType(Integer priorityType) {
        this.priorityType = priorityType;
    }
    public Long getParentId() {
        return parentId;
    }
    public void setParentId(Long parentId) {
        this.parentId = parentId;
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

    /**
     * 克隆方法
     * @param clazz 目标Class对象
     * @return 克隆后的对象
     */
    public <T> T clone(Class<T> clazz) {
        T target = null;

        try {
            target = clazz.newInstance();
        } catch (Exception e) {
            logger.error("error", e);
        }

        BeanCopierUtils.copyProperties(this, target);

        return target;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PriorityDTO)) return false;
        PriorityDTO that = (PriorityDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(code, that.code) &&
                Objects.equals(url, that.url) &&
                Objects.equals(priorityComment, that.priorityComment) &&
                Objects.equals(priorityType, that.priorityType) &&
                Objects.equals(parentId, that.parentId) &&
                Objects.equals(gmtCreate, that.gmtCreate) &&
                Objects.equals(gmtModified, that.gmtModified);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, code, url, priorityComment, priorityType, parentId, gmtCreate, gmtModified);
    }
}

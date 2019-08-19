package com.gsj.www.auth.mapper;

import com.gsj.www.auth.domain.AccountRoleRelationshipDO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 账号角色关系管理模块mapper组件
 */
@Mapper
public interface AccountRoleRelationshipMapper {
    /**
     * 根据角色id来查询记录数
     * @param roleId 角色id
     * @return 记录数
     */
    @Select("select count(*) " +
            "from auth_account_role_relationship " +
            "where role_id=#{roleId}")
    Long countByRoleId(@Param("roleId") Long roleId);

    /**
     * 根据账号id查询账号和角色关联关系
     * @param accountId 账号id
     * @return 账号和角色关联关系
     */
    @Select("SELECT "
            + "id,"
            + "account_id,"
            + "role_id,"
            + "gmt_create,"
            + "gmt_modified "
            + "FROM auth_account_role_relationship "
            + "WHERE account_id=#{accountId}")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "account_id", property = "accountId"),
            @Result(column = "role_id", property = "roleId"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")
    })
    List<AccountRoleRelationshipDO> listByAccountId(
            @Param("accountId") Long accountId);

    /**
     * 新增账号和角色的关联关系
     * @param relation 账号和角色的关联关系
     */
    @Insert("INSERT INTO auth_account_role_relationship("
            + "account_id,"
            + "role_id,"
            + "gmt_create,"
            + "gmt_modified"
            + ") VALUES("
            + "#{accountId},"
            + "#{roleId},"
            + "#{gmtCreate},"
            + "#{gmtModified}"
            + ")")
    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
    void save(AccountRoleRelationshipDO relation);

    /**
     * 根据账号id删除账号和角色的关联关系
     * @param accountId 账号id
     */
    @Delete("DELETE FROM auth_account_role_relationship WHERE account_id=#{accountId}")
    void removeByAccountId(@Param("accountId") Long accountId);
}

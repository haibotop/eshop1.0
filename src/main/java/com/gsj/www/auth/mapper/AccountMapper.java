package com.gsj.www.auth.mapper;

import com.gsj.www.auth.domain.AccountDO;
import com.gsj.www.auth.domain.AccountQuery;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 账号管理mapper组件
 */
public interface AccountMapper {
    /**
     * 分页查询行号
     * @param query 找好查询条件
     * @return 账号
     */
    @Select("<script>" +
                "select " +
                    "a.id," +
                    "a.username," +
                    "a.name," +
                    "a.remark," +
                    "a.gmt_create," +
                    "a.gmt_modified " +
                "from auth_account a," +
                "(" +
                    "select id " +
                    "from auth_account " +
                    "where 1=1 " +
                    "<if test='username != null'>" +
                    "and username like '${username}%' " +
                    "</if>" +
                    "<if test='name != null'>" +
                    "and name like '${name}%' " +
                    "</if>" +
                    "limit #{offset},#{size}" +
                ") b" +
                "where a.id=b.id" +
            "</script>")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "username", property = "username"),
            @Result(column = "name", property = "name"),
            @Result(column = "remark", property = "remark"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")
    })
    List<AccountDO> listByPage(AccountQuery query);

    /**
     * 根据id查询账号
     * @param id 账号id
     * @return 账号
     */
    @Select("SELECT "
            + "id,"
            + "username,"
            + "name,"
            + "remark,"
            + "gmt_create,"
            + "gmt_modified "
            + "FROM auth_account "
            + "WHERE id=#{id}")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "username", property = "username"),
            @Result(column = "name", property = "name"),
            @Result(column = "remark", property = "remark"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")
    })
    AccountDO getById(@Param("id") Long id);

    /**
     * 新增账号
     * @param account 账号
     */
    @Insert("INSERT INTO auth_account("
            + "username,"
            + "password,"
            + "name,"
            + "remark,"
            + "gmt_create,"
            + "gmt_modified"
            + ") VALUES("
            + "#{username},"
            + "#{password},"
            + "#{name},"
            + "#{remark},"
            + "#{gmtCreate},"
            + "#{gmtModified}"
            + ")")
    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
    void save(AccountDO account);

    /**
     * 更新账号
     * @param account 账号
     */
    @Update("UPDATE auth_account SET"
            + "password=#{password},"
            + "remark=#{remark},"
            + "gmt_modified=#{gmtModified} "
            + "WHERE id=#{id}")
    void update(AccountDO account);

    /**
     * 删除账号
     * @param id 账号id
     */
    @Delete("DELETE FROM auth_account WHERE id={id}")
    void remove(@Param("id") Long id);

}

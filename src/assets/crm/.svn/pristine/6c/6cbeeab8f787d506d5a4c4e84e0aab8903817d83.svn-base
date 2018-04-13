package com.hhnz.encrypt.mapper;

import com.hhnz.encrypt.model.TEncryptKey;
import com.hhnz.encrypt.model.TEncryptKeyExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface TEncryptKeyMapper {
    @SelectProvider(type=TEncryptKeySqlProvider.class, method="countByExample")
    int countByExample(TEncryptKeyExample example);

    @DeleteProvider(type=TEncryptKeySqlProvider.class, method="deleteByExample")
    int deleteByExample(TEncryptKeyExample example);

    @Delete({
        "delete from T_ENCRYPT_KEY",
        "where ID = #{id,jdbcType=DECIMAL}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into T_ENCRYPT_KEY (ID, KEY_NAME, ",
        "SYSTEM_NAME, KEY_DESC, ",
        "KEY_VERSION, KEY_STATE, ",
        "KEY_UPDATE_TS, KEY_SENDTYPE, ",
        "KEY_SENDURI, KEY_TYPE, ",
        "KEY_CONTENT)",
        "values (#{id,jdbcType=DECIMAL}, #{keyName,jdbcType=VARCHAR}, ",
        "#{systemName,jdbcType=VARCHAR}, #{keyDesc,jdbcType=VARCHAR}, ",
        "#{keyVersion,jdbcType=DECIMAL}, #{keyState,jdbcType=VARCHAR}, ",
        "#{keyUpdateTs,jdbcType=TIMESTAMP}, #{keySendtype,jdbcType=VARCHAR}, ",
        "#{keySenduri,jdbcType=VARCHAR}, #{keyType,jdbcType=VARCHAR}, ",
        "#{keyContent,jdbcType=BLOB})"
    })
    int insert(TEncryptKey record);

    @InsertProvider(type=TEncryptKeySqlProvider.class, method="insertSelective")
    int insertSelective(TEncryptKey record);

    @SelectProvider(type=TEncryptKeySqlProvider.class, method="selectByExampleWithBLOBs")
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.DECIMAL, id=true),
        @Result(column="KEY_NAME", property="keyName", jdbcType=JdbcType.VARCHAR),
        @Result(column="SYSTEM_NAME", property="systemName", jdbcType=JdbcType.VARCHAR),
        @Result(column="KEY_DESC", property="keyDesc", jdbcType=JdbcType.VARCHAR),
        @Result(column="KEY_VERSION", property="keyVersion", jdbcType=JdbcType.DECIMAL),
        @Result(column="KEY_STATE", property="keyState", jdbcType=JdbcType.VARCHAR),
        @Result(column="KEY_UPDATE_TS", property="keyUpdateTs", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="KEY_SENDTYPE", property="keySendtype", jdbcType=JdbcType.VARCHAR),
        @Result(column="KEY_SENDURI", property="keySenduri", jdbcType=JdbcType.VARCHAR),
        @Result(column="KEY_TYPE", property="keyType", jdbcType=JdbcType.VARCHAR),
        @Result(column="KEY_CONTENT", property="keyContent", jdbcType=JdbcType.BLOB)
    })
    List<TEncryptKey> selectByExampleWithBLOBs(TEncryptKeyExample example);

    @SelectProvider(type=TEncryptKeySqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.DECIMAL, id=true),
        @Result(column="KEY_NAME", property="keyName", jdbcType=JdbcType.VARCHAR),
        @Result(column="SYSTEM_NAME", property="systemName", jdbcType=JdbcType.VARCHAR),
        @Result(column="KEY_DESC", property="keyDesc", jdbcType=JdbcType.VARCHAR),
        @Result(column="KEY_VERSION", property="keyVersion", jdbcType=JdbcType.DECIMAL),
        @Result(column="KEY_STATE", property="keyState", jdbcType=JdbcType.VARCHAR),
        @Result(column="KEY_UPDATE_TS", property="keyUpdateTs", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="KEY_SENDTYPE", property="keySendtype", jdbcType=JdbcType.VARCHAR),
        @Result(column="KEY_SENDURI", property="keySenduri", jdbcType=JdbcType.VARCHAR),
        @Result(column="KEY_TYPE", property="keyType", jdbcType=JdbcType.VARCHAR)
    })
    List<TEncryptKey> selectByExample(TEncryptKeyExample example);

    @Select({
        "select",
        "ID, KEY_NAME, SYSTEM_NAME, KEY_DESC, KEY_VERSION, KEY_STATE, KEY_UPDATE_TS, ",
        "KEY_SENDTYPE, KEY_SENDURI, KEY_TYPE, KEY_CONTENT",
        "from T_ENCRYPT_KEY",
        "where ID = #{id,jdbcType=DECIMAL}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.DECIMAL, id=true),
        @Result(column="KEY_NAME", property="keyName", jdbcType=JdbcType.VARCHAR),
        @Result(column="SYSTEM_NAME", property="systemName", jdbcType=JdbcType.VARCHAR),
        @Result(column="KEY_DESC", property="keyDesc", jdbcType=JdbcType.VARCHAR),
        @Result(column="KEY_VERSION", property="keyVersion", jdbcType=JdbcType.DECIMAL),
        @Result(column="KEY_STATE", property="keyState", jdbcType=JdbcType.VARCHAR),
        @Result(column="KEY_UPDATE_TS", property="keyUpdateTs", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="KEY_SENDTYPE", property="keySendtype", jdbcType=JdbcType.VARCHAR),
        @Result(column="KEY_SENDURI", property="keySenduri", jdbcType=JdbcType.VARCHAR),
        @Result(column="KEY_TYPE", property="keyType", jdbcType=JdbcType.VARCHAR),
        @Result(column="KEY_CONTENT", property="keyContent", jdbcType=JdbcType.BLOB)
    })
    TEncryptKey selectByPrimaryKey(Long id);

    @UpdateProvider(type=TEncryptKeySqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") TEncryptKey record, @Param("example") TEncryptKeyExample example);

    @UpdateProvider(type=TEncryptKeySqlProvider.class, method="updateByExampleWithBLOBs")
    int updateByExampleWithBLOBs(@Param("record") TEncryptKey record, @Param("example") TEncryptKeyExample example);

    @UpdateProvider(type=TEncryptKeySqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") TEncryptKey record, @Param("example") TEncryptKeyExample example);

    @UpdateProvider(type=TEncryptKeySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TEncryptKey record);

    @Update({
        "update T_ENCRYPT_KEY",
        "set KEY_NAME = #{keyName,jdbcType=VARCHAR},",
          "SYSTEM_NAME = #{systemName,jdbcType=VARCHAR},",
          "KEY_DESC = #{keyDesc,jdbcType=VARCHAR},",
          "KEY_VERSION = #{keyVersion,jdbcType=DECIMAL},",
          "KEY_STATE = #{keyState,jdbcType=VARCHAR},",
          "KEY_UPDATE_TS = #{keyUpdateTs,jdbcType=TIMESTAMP},",
          "KEY_SENDTYPE = #{keySendtype,jdbcType=VARCHAR},",
          "KEY_SENDURI = #{keySenduri,jdbcType=VARCHAR},",
          "KEY_TYPE = #{keyType,jdbcType=VARCHAR},",
          "KEY_CONTENT = #{keyContent,jdbcType=BLOB}",
        "where ID = #{id,jdbcType=DECIMAL}"
    })
    int updateByPrimaryKeyWithBLOBs(TEncryptKey record);

    @Update({
        "update T_ENCRYPT_KEY",
        "set KEY_NAME = #{keyName,jdbcType=VARCHAR},",
          "SYSTEM_NAME = #{systemName,jdbcType=VARCHAR},",
          "KEY_DESC = #{keyDesc,jdbcType=VARCHAR},",
          "KEY_VERSION = #{keyVersion,jdbcType=DECIMAL},",
          "KEY_STATE = #{keyState,jdbcType=VARCHAR},",
          "KEY_UPDATE_TS = #{keyUpdateTs,jdbcType=TIMESTAMP},",
          "KEY_SENDTYPE = #{keySendtype,jdbcType=VARCHAR},",
          "KEY_SENDURI = #{keySenduri,jdbcType=VARCHAR},",
          "KEY_TYPE = #{keyType,jdbcType=VARCHAR}",
        "where ID = #{id,jdbcType=DECIMAL}"
    })
    int updateByPrimaryKey(TEncryptKey record);
}
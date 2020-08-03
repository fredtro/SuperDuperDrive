package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialsMapper {

    @Select("SELECT * FROM CREDENTIALS WHERE userId = #{userId}")
    List<Note> getAll(Integer userId);

    @Insert("INSERT INTO CREDENTIALS (url, username, key, password) VALUES(#{url}, #{username}, #{key}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    void insert(Credentials credentials);

    @Update("UPDATE CREDENTIALS SET url=#{url}, username=#{username}, password=#{password} WHERE credentialid=#{credentialId}")
    void update(Credentials credentials);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{id}")
    void delete(Integer id);

}

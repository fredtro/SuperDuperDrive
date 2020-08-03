package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {
    @Select("SELECT * FROM NOTES WHERE userId = #{userId}")
    List<Note> getAll(Integer userId);

    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES(#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    void insert(Note note);

    @Update("UPDATE NOTES SET notetitle=#{noteTitle}, notedescription=#{noteDescription} WHERE noteid=#{noteId}")
    void update(Note note);

    @Delete("DELETE FROM NOTES WHERE noteid = #{id}")
    void delete(Integer id);
}

package javaDB.interfaces;

import javaDB.entities.Task;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TaskMapper  {
    @Insert("INSERT INTO Tasks (id, name, deadline, description, task_type, human) " +
            "VALUES(#{id}, " +
            "#{name}, " +
            "#{deadline}, " +
            "#{description}, " +
            "#{taskType}," +
            "#{human})")
    void save(Task entity);
    @Delete("DELETE FROM Tasks WHERE id = #{id}")
    void deleteById(long id);
    @Delete("DELETE FROM Tasks WHERE id = #{id}")
    void deleteByEntity(Task entity);
    @Delete("DELETE FROM Tasks")
    void deleteAll();
    @Update("UPDATE Tasks " +
            "SET name = #{name}, " +
            "SET deadline = #{deadline}, " +
            "SET description = #{description}, " +
            "SET task_type = ${taskType}, " +
            "SET human = ${human} " +
            "WHERE id = ${id}")
    void update(Task entity);
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "deadline", column = "deadline"),
            @Result(property = "description", column = "description"),
            @Result(property = "taskType", column = "task_type"),
            @Result(property = "human", column = "human")
    })
    @Select("SELECT * FROM Tasks WHERE id = #{id}")
    Task getById(long id);
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "deadline", column = "deadline"),
            @Result(property = "description", column = "description"),
            @Result(property = "taskType", column = "task_type"),
            @Result(property = "human", column = "human")
    })
    @Select("SELECT * FROM Tasks")
    List<Task> getAll();
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "deadline", column = "deadline"),
            @Result(property = "description", column = "description"),
            @Result(property = "taskType", column = "task_type"),
            @Result(property = "human", column = "human")
    })
    @Select("SELECT * FROM Tasks WHERE id = #{id}")
    List<Task> getAllByVId(long id);
}

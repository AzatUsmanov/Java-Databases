package javaDB.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Класс, описывающий объект "Задача".
 */
@Entity
@Table(name="Tasks")
public class Task implements Serializable {
    public final static int MIN_VALUE = 0;

    @Id
    private long id;
    private String name;
    private Date deadline;
    private String description;
    @Column(name = "task_type")
    private String taskType;
    private long human;

    public Task(){}

    public Task(long id, String name, Date deadline, String description, String taskType, long human) {
        if (id < MIN_VALUE){
            throw new IllegalArgumentException("Incorrect id");
        } else if (name.isEmpty()){
            throw new IllegalArgumentException("Incorrect name");
        } else if (description.isEmpty()){
            throw new IllegalArgumentException("Incorrect description");
        } else if (taskType.isEmpty()){
            throw new IllegalArgumentException("Incorrect task type");
        } else if (human < MIN_VALUE){
            throw new IllegalArgumentException("Incorrect human id");
        }
        this.id = id;
        this.name = name;
        this.deadline = deadline;
        this.description = description;
        this.taskType = taskType;
        this.human = human;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public Date getDeadline() {
        return deadline;
    }

    public long getHuman() {
        return human;
    }

    public String getDescription() {
        return description;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setName(String name) throws IllegalArgumentException {
        if (name.isEmpty()){
            throw new IllegalArgumentException("Incorrect name");
        }

        this.name = name;
    }

    public void setId(long id) throws IllegalArgumentException {
        if (id < MIN_VALUE){
            throw new IllegalArgumentException("Incorrect id");
        }

        this.id = id;
    }

    public void setDeadline(Date deadline) throws IllegalArgumentException {
        this.deadline = deadline;
    }

    public void setDescription(String description) throws IllegalArgumentException {
        if (description.isEmpty()){
            throw new IllegalArgumentException("Incorrect description");
        }

        this.description = description;
    }

    public void setHuman(long human) throws IllegalArgumentException {
        if (human < MIN_VALUE){
            throw new IllegalArgumentException("Incorrect human id");
        }

        this.human = human;
    }

    public void setTaskType(String taskType) throws IllegalArgumentException {
        if (taskType.isEmpty()){
            throw new IllegalArgumentException("Incorrect task type");
        }
        this.taskType = taskType;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Task)) {
            return false;
        }

        Task other = (Task) obj;
        return this.id == other.id && this.human == other.human;
    }
}

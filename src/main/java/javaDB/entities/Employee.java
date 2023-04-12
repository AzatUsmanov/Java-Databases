package javaDB.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Класс, описывающий объект "Сотрудник".
 */
@Entity
@Table(name="Employees")
public class Employee implements Serializable {
    public final static int MIN_VALUE = 0;

    @Id
    private long id;
    private String name;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    public Employee() {}

    public Employee(long id, String name, Date dateOfBirth) throws IllegalArgumentException {
        if (id < MIN_VALUE){
            throw new IllegalArgumentException("Incorrect id");
        } else if (name.isEmpty()){
            throw new IllegalArgumentException("Incorrect name");
        }

        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setId(long id) throws  IllegalArgumentException {
        if (id < MIN_VALUE){
            throw new IllegalArgumentException("Incorrect id");
        }

        this.id = id;
    }

    public void setName(String name) throws IllegalArgumentException {
        if (name.isEmpty()){
            throw new IllegalArgumentException("Incorrect name");
        }

        this.name = name;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Employee)) {
            return false;
        }

        Employee other = (Employee) obj;
        return this.id == other.id;
    }
}

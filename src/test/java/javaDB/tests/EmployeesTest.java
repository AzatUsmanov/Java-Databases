package javaDB.tests;

import javaDB.entities.Task;
import javaDB.hibernate.EmployeesHibernate;
import javaDB.hibernate.HibernateUtil;
import javaDB.jdbc.EmployeesJDBC;
import javaDB.mybatis.EmployeesMyBatis;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class EmployeesTest {
    @Test
    public void searchForTasksInTheTableWithJDBC(){
        EmployeesJDBC employees = new EmployeesJDBC();

        List<Task> tasks = employees.getAllByVId(1l);

        assertEquals(3, tasks.size());
    }

    @Test
    public void searchForTasksInTheTableWithHibernate(){

        EmployeesHibernate employees = new EmployeesHibernate();

        List<Task> tasks = employees.getAll();

        assertEquals(3, tasks.size());
        HibernateUtil.getSessionFactory().close();
    }
    @Test
    public void searchForTasksInTheTableWithMyBaits(){
        EmployeesMyBatis employees = new EmployeesMyBatis();

        List<Task> tasks = employees.getAll();

        assertEquals(3, tasks.size());
    }
}

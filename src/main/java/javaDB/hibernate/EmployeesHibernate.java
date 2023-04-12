package javaDB.hibernate;

import javaDB.entities.Task;
import javaDB.interfaces.DataBaseConnector;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Класс, описывающий объект "Коннектор к базе данных "Сотрудники" через Hibernate".
 */
public class EmployeesHibernate implements DataBaseConnector<Task> {
    private final String DELETE_ALL = "DELETE Task";
    private final String GET_ALL = "FROM Task";
    private final String GET_ALL_BY_V_ID  = "FROM Task WHERE human = :idParam";
    private static Logger logger = Logger.getLogger(EmployeesHibernate.class.getName());

    public EmployeesHibernate() {
    }

    /**
     * Метод, сохраняющий задачу в таблице Tasks.
     *
     * @param entity - сущность задачи.
     * @return {@link Task}  - сохраненная задача.
     */
    @Override
    public Task save(Task entity) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
        } catch (Exception exception){
            logger.warning(exception.getMessage());
        }

        return entity;
    }

    /**
     * Метод, удаляющий задачу по id в таблице Tasks.
     *
     * @param id - идентификатор задачи.
     */
    @Override
    public void deleteById(long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            Task task = session.find(Task.class, id);
            session.remove(task);
            session.getTransaction().commit();
        } catch (Exception exception){
            logger.warning(exception.getMessage());
        }
    }

    /**
     * Метод, удаляющий задачу в таблице Tasks.
     *
     * @param entity - сущность удаленной задачи.
     */
    @Override
    public void deleteByEntity(Task entity) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            Task task = session.find(Task.class, entity.getId());
            session.remove(task);
            session.getTransaction().commit();
        } catch (Exception exception){
            logger.warning(exception.getMessage());
        }
    }

    /**
     * Метод, удаляющий все задачи из таблицы Tasks.
     */
    @Override
    public void deleteAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            session.createQuery(DELETE_ALL).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception exception) {
            logger.warning(exception.getMessage());
        }
    }

    /**
     * Метод, обновляющий сущность задачи в таблице Tasks.
     *
     * @param entity - задача с новыми параметрами.
     * @return {@link Task} - сущность обновленной задачи.
     */
    @Override
    public Task update(Task entity) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            session.merge(entity);
            session.getTransaction().commit();
        } catch (Exception exception){
            logger.warning(exception.getMessage());
        }

        return entity;
    }

    /**
     * Метод, возвращающий задачу по id из таблицы Tasks.
     *
     * @param id - идентификатор задачи.
     * @return {@link Task} - найденная задача.
     */
    @Override
    public Task getById(long id) {
        Task task = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            task = session.find(Task.class, id);
            session.getTransaction().commit();
        } catch (Exception exception){
            logger.warning(exception.getMessage());
        }

        return task;
    }

    /**
     * Метод, возвращающий все задачи из таблицы Tasks.
     *
     * @return {@link List<Task>} - список всех задач.
     */
    @Override
    public List<Task> getAll() {
        List<Task> tasks = new ArrayList<Task>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            tasks = session.createQuery(GET_ALL).list();
            session.getTransaction().commit();
        } catch (Exception exception){
            logger.warning(exception.getMessage());
        }

        return tasks;
    }

    /**
     * Метод, находящий список задач, принадлежащих определенному сотруднику из таблицы Tasks.
     *
     * @param id - идентификатор сотрудника.
     * @return {@link List<Task>} - результирующий список задач.
     */
    @Override
    public List<Task> getAllByVId(long id) {
        List<Task> tasks = new ArrayList<Task>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            tasks = session.createQuery(GET_ALL_BY_V_ID).setParameter("idParam", id).list();
            session.getTransaction().commit();
        } catch (Exception exception){
            logger.warning(exception.getMessage());
        }

        return tasks;
    }
}

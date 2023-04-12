package javaDB.mybatis;

import javaDB.entities.Task;
import javaDB.interfaces.DataBaseConnector;
import javaDB.interfaces.TaskMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Класс, описывающий объект "Коннектор к базе данных "Сотрудники" через MyBaits".
 */
public class EmployeesMyBatis implements DataBaseConnector<Task> {

    private static Logger logger = Logger.getLogger(EmployeesMyBatis.class.getName());
    public EmployeesMyBatis() {
    }

    /**
     * Метод, сохраняющий задачу в таблице Tasks.
     *
     * @param entity - сущность задачи.
     * @return {@link Task}  - сохраненная задача.
     */
    @Override
    public Task save(Task entity) {
        try (SqlSession session = MyBaitsUtil.getSqlSessionFactory().openSession()) {
            TaskMapper mapper = session.getMapper(TaskMapper.class);
            mapper.save(entity);
            session.commit();
        } catch (Exception exception) {
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
        try (SqlSession session = MyBaitsUtil.getSqlSessionFactory().openSession()) {
            TaskMapper mapper = session.getMapper(TaskMapper.class);
            mapper.deleteById(id);
            session.commit();
        } catch (Exception exception) {
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
        try (SqlSession session = MyBaitsUtil.getSqlSessionFactory().openSession()) {
            TaskMapper mapper = session.getMapper(TaskMapper.class);
            mapper.deleteByEntity(entity);
            session.commit();
        } catch (Exception exception) {
            logger.warning(exception.getMessage());
        }
    }

    /**
     * Метод, удаляющий все задачи из таблицы Tasks.
     */
    @Override
    public void deleteAll() {
        try (SqlSession session = MyBaitsUtil.getSqlSessionFactory().openSession()) {
            TaskMapper mapper = session.getMapper(TaskMapper.class);
            mapper.deleteAll();
            session.commit();
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
        try (SqlSession session = MyBaitsUtil.getSqlSessionFactory().openSession()) {
            TaskMapper mapper = session.getMapper(TaskMapper.class);
            mapper.update(entity);
            session.commit();
        } catch (Exception exception) {
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
        try (SqlSession session = MyBaitsUtil.getSqlSessionFactory().openSession()) {
            TaskMapper mapper = session.getMapper(TaskMapper.class);
            task = mapper.getById(id);
            session.commit();
        } catch (Exception exception) {
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
        try (SqlSession session = MyBaitsUtil.getSqlSessionFactory().openSession()) {
            TaskMapper mapper = session.getMapper(TaskMapper.class);
            tasks = mapper.getAll();
            session.commit();
        } catch (Exception exception) {
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
        try (SqlSession session = MyBaitsUtil.getSqlSessionFactory().openSession()) {
            TaskMapper mapper = session.getMapper(TaskMapper.class);
            tasks = mapper.getAllByVId(id);
            session.commit();
        } catch (Exception exception) {
            logger.warning(exception.getMessage());
        }

        return tasks;
    }
}

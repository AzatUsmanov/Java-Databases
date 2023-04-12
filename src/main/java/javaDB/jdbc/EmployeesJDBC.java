package javaDB.jdbc;

import javaDB.entities.Task;
import javaDB.interfaces.DataBaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Класс, описывающий объект "Коннектор к базе данных "Сотрудники" через JDBC".
 */
public class EmployeesJDBC implements DataBaseConnector<Task> {
    private static Logger logger = Logger.getLogger(EmployeesJDBC.class.getName());
    private final String URL = "jdbc:postgresql://localhost:5432/db_employees";
    private final String USER = "postgres";
    private final String PASSWORD = "karandashik228";
    private final String INSERT_TASK = "INSERT INTO Tasks (id, name, deadline, description, task_type, human) " +
            "VALUES(?, ?, ?, ?, ?, ?);";
    private final String DELETE_BY_ID = "DELETE FROM Tasks " +
            "WHERE id = ?;";
    private final String DELETE_BY_ENTITY = "DELETE FROM Tasks " +
            "WHERE human = ? AND name = ?;";
    private final String DELETE_ALL = "DELETE FROM Tasks;";
    private final String UPDATE_BY_ENTITY = "UPDATE Tasks" +
            "SET name = ?," +
            "SET deadline = ?," +
            "SET description = ?," +
            "SET task_type = ?," +
            "SET human = ? " +
            "WHERE id = ?;";
    private final String GET_BY_ID = "SELECT * " +
            "FROM Tasks " +
            "WHERE id = ?";
    private final String GET_ALL = "SELECT * " +
            "FROM Tasks";
    private final String GET_ALL_BY_V_ID = "SELECT * " +
            "FROM Tasks " +
            "WHERE human = ?";

    public EmployeesJDBC(){
    }

    /**
     * Метод, выполняющий соединение к базе данных "Сотрудники".
     *
     * @return {@link Connection} - соединение к базе данных.
     * @throws SQLException - отсутствие соединения с базой данных "Cотрудники".
     */
    private Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * Метод, выполняющий сохранение задачи в базу данных "Сотрудники".
     *
     * @param entity - сущность по которой мы совершаем поиск.
     * @return {@link Task} - результат поиска.
     */
    @Override
    public Task save(Task entity) {
        Date sqlDate = new Date(entity.getDeadline().getTime());

        try(Connection connection = getConnection();
            PreparedStatement statement =  connection.prepareStatement(INSERT_TASK)){
            statement.setLong(1, entity.getId());
            statement.setString(2, entity.getName());
            statement.setDate(3,sqlDate);
            statement.setString(4,entity.getDescription());
            statement.setString(5, entity.getTaskType());
            statement.setLong(6, entity.getHuman());
            statement.executeUpdate();
        } catch (SQLException exception){
            logger.warning(exception.getMessage());
        }

        return entity;
    }

    /**
     * Метод, выполняющий удаление задачи из базы данных "Сотрудники" по его идентификатору.
     *
     * @param id - идентификатор задачи.
     */
    @Override
    public void deleteById(long id) {
        try(Connection connection = getConnection();
            PreparedStatement statement =  connection.prepareStatement(DELETE_BY_ID)){
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException exception){
            logger.warning(exception.getMessage());
        }
    }

    /**
     * Метод, выполняющий удаление задачи из базы данных "Сотрудники" по его параметрам.
     *
     * @param entity - пример необходимой задачи.
     */
    @Override
    public void deleteByEntity(Task entity) {
        try(Connection connection = getConnection();
            PreparedStatement statement =  connection.prepareStatement(DELETE_BY_ENTITY)){
            statement.setLong(1, entity.getHuman());
            statement.setString(2, entity.getName());
            statement.executeUpdate();
        } catch (SQLException exception){
            logger.warning(exception.getMessage());
        }
    }

    /**
     * Метод, выполняющий удаление всех задач из базы данных "Сотрудники".
     */
    @Override
    public void deleteAll() {
        try(Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement(DELETE_ALL);
            statement.executeUpdate();
        } catch (SQLException exception){
            logger.warning(exception.getMessage());
        }
    }

    /**
     * Метод, выполняющий изменение данных определенной задачи из базы данных "Сотрудники" по его параметрам.
     *
     * @param entity - пример новой задачи.
     * @return {@link Task} - обновленная задача.
     */
    @Override
    public Task update(Task entity) {
        Date sqlDate = new Date(entity.getDeadline().getTime());

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_BY_ENTITY)) {
            statement.setString(1, entity.getName());
            statement.setDate(2, sqlDate);
            statement.setString(3, entity.getDescription());
            statement.setString(4, entity.getTaskType());
            statement.setLong(5, entity.getHuman());
            statement.setLong(6, entity.getId());
            statement.executeUpdate();
        } catch (SQLException exception) {
            logger.warning(exception.getMessage());
        }

        return entity;
    }

    /**
     * Метод, возвращающий объект задачи из базы данных "Сотрудники" по его идентификатору.
     *
     * @param id - идентификатор необходимой задачи.
     * @return {@link Task} - результат поиска.
     */
    @Override
    public Task getById(long id) {
        Task task = null;

        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_BY_ID)){
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Date date = new Date(resultSet.getDate("deadline").getTime());
                task = new Task(id, resultSet.getString("name"),
                        date,
                        resultSet.getString("description"),
                        resultSet.getString("task_type"),
                        resultSet.getLong("human"));
            }
        } catch (SQLException exception){
            logger.warning(exception.getMessage());
        }

        return task;
    }

    /**
     * Метод, возвращающий все задачи из базы данных "Сотрудники".
     *
     * @return {@link List<Task>} - результат поиска.
     */
    @Override
    public List<Task> getAll() {
        List<Task> tasks = new ArrayList<>();

        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_ALL)){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Date date = new Date(resultSet.getDate("deadline").getTime());

                Task tempTask = new Task(resultSet.getLong("id"),
                        resultSet.getString("name"),
                        date,
                        resultSet.getString("description"),
                        resultSet.getString("task_type"),
                        resultSet.getLong("human"));

                tasks.add(tempTask);
            }
        } catch (SQLException exception){
            logger.warning(exception.getMessage());
        }

        return tasks;
    }

    /**
     * Метод, возвращающий все задачи из базы данных "Сотрудники" принадлежащих определенному сотруднику.
     *
     * @param id - идентификатор сотрудника.
     * @return {@link List<Task>} - результат поиска.
     */
    @Override
    public List<Task> getAllByVId(long id) {
        List<Task> tasks = new ArrayList<>();

        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_ALL_BY_V_ID)){
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Date date = new Date(resultSet.getDate("deadline").getTime());
                Task tempTask = new Task(resultSet.getLong("id"),
                        resultSet.getString("name"),
                        date,
                        resultSet.getString("description"),
                        resultSet.getString("task_type"),
                        resultSet.getLong("human"));

                tasks.add(tempTask);
            }
        } catch (SQLException exception){
            logger.warning(exception.getMessage());
        }

        return tasks;
    }
}

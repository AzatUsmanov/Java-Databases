package javaDB.exceptions;

public class EmployeesException extends Exception {
    public EmployeesException() {
    }

    public EmployeesException(String message) {
        super(message);
    }

    public EmployeesException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeesException(Throwable cause) {
        super(cause);
    }
}

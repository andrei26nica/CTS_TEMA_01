package ro.ase.acs.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

class EmployeeDatabaseManager {


        private Connection connection;

        public void connect(String url) throws SQLException {
                connection = DriverManager.getConnection(url);
                connection.setAutoCommit(false);
        }

        public void disconnect() throws SQLException {
                connection.close();
        }

        public void createTable() throws SQLException {
                String sqlDrop = "DROP TABLE IF EXISTS employees";
                String sqlCreate = "CREATE TABLE employees(id INTEGER PRIMARY KEY,"
                        + "name TEXT, address TEXT, salary REAL)";

                Statement statement = connection.createStatement();
                statement.executeUpdate(sqlDrop);
                statement.executeUpdate(sqlCreate);
                statement.close();
                connection.commit();
        }

        public void insertData(Employee employee) throws SQLException {
                String sqlInsert = "INSERT INTO employees VALUES (?,?,?,?)";
                PreparedStatement preparedStatement =
                        connection.prepareStatement(sqlInsert);
                preparedStatement.setInt(1, employee.getId());
                preparedStatement.setString(2, employee.getName());
                preparedStatement.setString(3, employee.getAddress());
                preparedStatement.setDouble(4, employee.getSalary());
                preparedStatement.executeUpdate();
                preparedStatement.close();

                connection.commit();
        }

        public List<Employee> readData() throws SQLException {
                String sqlSelect = "SELECT * FROM employees";
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(sqlSelect);

                List<Employee> employees = new ArrayList<>();
                while (rs.next()) {
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                }
                return employees;
        }
}

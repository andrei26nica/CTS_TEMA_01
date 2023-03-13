package ro.ase.acs.sql;

import java.util.List;

public class Main {


        public static void main(String[] args) {
                try {
                        EmployeeDatabaseManager employeeManager = new EmployeeDatabaseManager();
                        employeeManager.connect("jdbc:sqlite:database.db");

                        employeeManager.createTable();
                        employeeManager.insertData(new Employee(1, "Popescu Ion", "Bucharest", 4000));
                        employeeManager.insertData(new Employee(2, "Ionescu Vasile", "Brasov", 4500));

                        List<Employee> employees = employeeManager.readData();
                        for (Employee employee : employees) {
                                System.out.println(employee.toString());
                        }

                        employeeManager.disconnect();
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }
}

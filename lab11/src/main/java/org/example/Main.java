package org.example;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import static org.example.CompanyDatabase.*;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        DatabaseConnection conn = new DatabaseConnection();

        createTables(conn);
        insertSampleData(conn);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    Choose an option:
                    1. List all employees
                    2. List all tasks
                    3. List employees by department
                    4. Add task for employee
                    5. List tasks for employee
                    6. Delete employee
                    7. Exit
                    """);
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> listAllEmployees(conn);
                case 2 -> listAllTasks(conn);
                case 3 -> {
                    System.out.print("Enter department ID: ");
                    int departmentId = scanner.nextInt();
                    listEmployeesByDepartment(conn, departmentId);
                }
                case 4 -> {
                    System.out.print("Enter employee ID: ");
                    int employeeId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter task description: ");
                    String taskDescription = scanner.nextLine();
                    addTaskForEmployee(conn, employeeId, taskDescription);
                }
                case 5 -> {
                    System.out.print("Enter employee ID: ");
                    int employeeId = scanner.nextInt();
                    listTasksForEmployee(conn, employeeId);
                }
                case 6 -> {
                    System.out.print("Enter employee ID: ");
                    int employeeId = scanner.nextInt();
                    deleteEmployee(conn, employeeId);
                }
                case 7 -> {
                    conn.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
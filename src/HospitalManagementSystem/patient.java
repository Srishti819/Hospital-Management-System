package HospitalManagementSystem;

import java.sql.*;
import java.util.Scanner;

public class patient {
    private Connection connection;
    private Scanner scanner;

    public patient(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }


    // add patients
    public void addPatient() {
        System.out.println("Enter Patient Name");
        String name = scanner.next();
        System.out.println("enter paitent Age");
        int age = scanner.nextInt();
        System.out.println("Enter paitent Gender");
        String gender = scanner.next();

        try {
            String query = "INSERT INTO patients(name, age , gender) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, gender);
            int affectedrows = preparedStatement.executeUpdate();

            if (affectedrows > 0) {
                System.out.println("patients Added successfully!!");
            } else {
                System.out.println("failed to add patients!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void viewPatients() {

        String query = "SELECT * FROM patients ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println("patients: ");
            System.out.println("________________+___________________________+______________+________________");
            System.out.println("| Patients ID   | Name                      | Age          | Gender         ");
            System.out.println("________________+___________________________+______________+________________");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String gender = rs.getString("gender");
                System.out.println("|%-3s|%-22s|%-10s|%-9s");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean getPatient() {
        String query = " SELECT * FROM patients WHERE id =?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            System.out.println("Enter patients Id:");
            int id = scanner.nextInt();
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}

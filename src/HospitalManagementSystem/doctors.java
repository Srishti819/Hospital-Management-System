package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class doctors {
    Connection connection;
    Scanner scanner;
    public doctors(Connection connection, Scanner scanner){
        this.connection = connection;
        this.scanner=scanner;
    }

    public void viewDoctors(){
        String query = "SELECT * FROM doctors";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs= preparedStatement.executeQuery();
            System.out.println("doctor: ");
            System.out.println("________________+___________________________+______________+________________");
            System.out.println("| Doctors ID   | Name                      | Specialization                 ");
            System.out.println("________________+___________________________+______________+________________");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String specialization = rs.getString("specialization");
                System.out.printf("|%-3s|%-22s|%-10s|%-16s|\n" , id, name, specialization);
                System.out.println("________________+___________________________+______________+________________");
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
    }





}

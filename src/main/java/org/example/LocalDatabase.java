package org.example;

import java.sql.*;

import static java.sql.DriverManager.getConnection;
import static java.sql.DriverManager.println;


public class LocalDatabase {
    private static final String URL = "jdbc:mysql://localhost:3306/localDatabase";
    private static final String USER = "root";
    private static final String PASSWORD = "root";


        public static void main(String[] args) {
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {

                String createQuery = "INSERT INTO ad_soyad1(name, surname, email) VALUES (?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(createQuery)) {
                    preparedStatement.setString(1, "Esma");
                    preparedStatement.setString(2, "Coban");
                    preparedStatement.setString(3, "esmancoban@gmail.com");
                    preparedStatement.executeUpdate();
                }
                String selectQuery = "SELECT * FROM ad_soyad1";
                try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
                     ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        System.out.println("ID: " + resultSet.getString("id"));
                        System.out.println("Name: " + resultSet.getString("name"));
                        System.out.println("Surname: " + resultSet.getString("surname"));
                        System.out.println("Email: " + resultSet.getString("email"));
                    }
                }
                String updateQuery = "UPDATE ad_soyad1 SET email = ? WHERE name = ?";
                try(PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                    preparedStatement.setString(1, "newemail@gmail.com");
                    preparedStatement.setString(2, "Esma");
                    preparedStatement.executeUpdate();
                }

                String deleteQuery = "DELETE FROM ad_soyad1 WHERE name = ?";
                try(PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)){
                    preparedStatement.setString(1,"Esma");
                    preparedStatement.executeUpdate();
                }
        }
        catch (SQLException e){
                e.printStackTrace();
        }
        }
}


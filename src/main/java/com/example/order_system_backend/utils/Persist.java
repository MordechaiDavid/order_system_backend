package com.example.order_system_backend.utils;

import com.example.order_system_backend.objects.Product;
import com.example.order_system_backend.objects.Supplier;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import java.util.Collections;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class Persist {

    private Connection connection;

    @PostConstruct
    public void createConnectionToDatabase() {
        try {
            this.connection = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/order_system", "root", "1234");
            System.out.println("Successfully connected to DB");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Supplier> getAllSuppliers(){
        List<Supplier> allSuppliers = new ArrayList<>();
        try {
            ResultSet resultSet = this.connection.createStatement().
                    executeQuery("select * from supliers");
            while (resultSet.next()){
                String name = resultSet.getString("name");
                String id = resultSet.getString("id");
                String mail = resultSet.getString("mail");
                String phone = resultSet.getString("phone");
                String communicationPreference = resultSet.getString("communication_preference");
                Supplier supplier = new Supplier(name, id, mail, phone, communicationPreference);
                allSuppliers.add(supplier);
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return allSuppliers;
    }

    public void addSupplier(String name, String id, String mail, String phone, String communicationPreferences){
        try {
            PreparedStatement preparedStatement = this.connection.
                    prepareStatement("INSERT INTO supliers(name, id, mail, phone, communication_preference) VALUE (?,?,?,?,?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, id);
            preparedStatement.setString(3, mail);
            preparedStatement.setString(4, phone);
            preparedStatement.setString(5, communicationPreferences);
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException("Error while adding supplier", e);
        }

    }

    public List<Product> getProductByCategory(int categoryID){
        List<Product> products = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = this.connection.
                    prepareStatement("SELECT * FROM products WHERE category_id = ?");
            preparedStatement.setInt(1, categoryID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString("name");
                int id = resultSet.getInt("id");
                String picture = resultSet.getString("picture");
                String supID = resultSet.getString("sup_id");
                int catID = resultSet.getInt("category_id");
                Product product = new Product(name, id, picture, supID, catID);
                products.add(product);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return products;
    }

    public Supplier getSupplierByProductID(int productID){
        Supplier supplier = null;
        try {
            PreparedStatement preparedStatement = this.connection.
                    prepareStatement("select * from supliers where supliers.id = " +
                            "(select sup_id from products where id = ?)");
            preparedStatement.setInt(1, productID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String id = resultSet.getString("id");
                String mail = resultSet.getString("mail");
                String phone = resultSet.getString("phone");
                String communicationPreference = resultSet.getString("communication_preference");
                supplier = new Supplier(name, id, mail, phone, communicationPreference);
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return supplier;
    }



















}
package com.example.order_system_backend.objects;

public class Product {

    private String name;
    private int id;
    private String picture;
    private String supplierID;
    private int categoryID;

    public Product(String name, int id, String picture, String supplierID, int categoryID) {
        this.name = name;
        this.id = id;
        this.picture = picture;
        this.supplierID = supplierID;
        this.categoryID = categoryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }
}

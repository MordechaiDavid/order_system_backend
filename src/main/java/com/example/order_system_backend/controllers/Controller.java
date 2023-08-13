package com.example.order_system_backend.controllers;

import com.example.order_system_backend.objects.Category;
import com.example.order_system_backend.objects.Product;
import com.example.order_system_backend.objects.Supplier;
import com.example.order_system_backend.objects.User;
import com.example.order_system_backend.utils.Persist;
import com.example.order_system_backend.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;



@RestController
public class Controller {

    @Autowired
    private Persist persist;
    @Autowired
    private Utils utils;


    @RequestMapping(value = "/test", method = {RequestMethod.GET, RequestMethod.POST})
    public Object test(){
        return "test";
    }

    @RequestMapping(value = "/get-all-suppliers", method = {RequestMethod.GET, RequestMethod.POST})
    public List<Supplier> getAllSuppliers(){
        List<Supplier> allSuppliers = persist.getAllSuppliers();
        return allSuppliers;
    }

    @RequestMapping(value = "/add-supplier", method = {RequestMethod.GET, RequestMethod.POST})
    public Supplier addSupplier(String name, String id, String mail, String phone, String communicationPreference){
        this.persist.addSupplier(name, id, mail, phone, communicationPreference);
        return new Supplier(name, id, mail, phone, communicationPreference);
    }

    @RequestMapping(value = "/get-all-categories", method = {RequestMethod.GET, RequestMethod.POST})
    public List<Category> getAllCategories(){
        return this.persist.getAllCategories();
    }

    @RequestMapping(value = "/add-category", method = {RequestMethod.GET, RequestMethod.POST})
    public void addCategory(String categoryName){
        this.persist.addCategory(categoryName);
    }

    @RequestMapping(value = "/get-products-by-categoryID", method = {RequestMethod.GET, RequestMethod.POST})
    public List<Product> getProductsByCategoryID(int categoryID){
        List<Product> answer = this.persist.getProductByCategory(categoryID);
        return answer;
    }

    @RequestMapping(value = "/get-supplier-by-productID", method = {RequestMethod.GET, RequestMethod.POST})
    public Supplier getSupplierByProductID(int productID){
        return this.persist.getSupplierByProductID(productID);
    }

    @RequestMapping(value = "/get-all-users", method = {RequestMethod.GET, RequestMethod.POST})
    public List<User> getAllUsers () {
        List<User> allUsers = persist.getAllUsers();
        return allUsers;
    }

    @RequestMapping(value = "/create-account", method = {RequestMethod.GET, RequestMethod.POST})
    public User createAccount (String username, String password) {
        User newAccount = null;
        if (utils.validateUsername(username)) {
            if (utils.validatePassword(password)) {
                if (persist.usernameAvailable(username)) {

                    String token = createHash(username, password);
                    newAccount = new User(username, token);
                    persist.addUser(username, token);
                } else {
                    System.out.println("username already exits");
                }
            } else {
                System.out.println("password is invalid");
            }
        } else {
            System.out.println("username is invalid");
        }
        return newAccount;
    }

    public String createHash (String username, String password) {
        String raw = String.format("%s_%s", username, password);
        String myHash = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(raw.getBytes());
            byte[] digest = md.digest();
            myHash = java.util.Base64.getEncoder().encodeToString(digest).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return myHash;
    }


}

package com.example.order_system_backend.controllers;

import com.example.order_system_backend.objects.Product;
import com.example.order_system_backend.objects.Supplier;
import com.example.order_system_backend.utils.Persist;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private Persist persist;


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

    @RequestMapping(value = "/get-products-by-categoryID", method = {RequestMethod.GET, RequestMethod.POST})
    public List<Product> getProductsByCategoryID(int categoryID){
        List<Product> answer = this.persist.getProductByCategory(categoryID);
        return answer;
    }

    @RequestMapping(value = "/get-supplier-by-productID", method = {RequestMethod.GET, RequestMethod.POST})
    public Supplier getSupplierByProductID(int productID){
        return this.persist.getSupplierByProductID(productID);
    }


}

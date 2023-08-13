package com.example.order_system_backend.objects;

public class Supplier {

    private String name;
    private String id;
    private String mail;
    private String phone;
    private String communicationPreference;


    public Supplier(String name, String id, String mail, String phone, String communicationPreference) {
        this.name = name;
        this.id = id;
        this.mail = mail;
        this.phone = phone;
        this.communicationPreference = communicationPreference;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCommunicationPreference() {
        return communicationPreference;
    }

    public void setCommunicationPreference(String communicationPreference) {
        this.communicationPreference = communicationPreference;
    }
}
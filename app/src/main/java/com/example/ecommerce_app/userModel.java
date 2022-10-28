package com.example.ecommerce_app;

public class userModel {
    private int id;
    private String userName;
    private String userEmail;
    private String password;
    private String mobileNumber;
    private String address;
    private String cart;

    public userModel(int id, String userName, String userEmail, String password, String mobileNumber, String address, String cart) {
        this.id = id;
        this.userName = userName;
        this.userEmail = userEmail;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.address = address;
        this.cart = cart;
    }

    userModel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCart() {
        return cart;
    }

    public void setCart(String cart) {
        this.cart = cart;
    }
}

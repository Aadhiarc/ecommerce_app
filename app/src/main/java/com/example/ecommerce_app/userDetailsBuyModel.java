package com.example.ecommerce_app;

public class userDetailsBuyModel {
    String userName;
    String userEmail;
    String userHouseNumber;
    String userAddress;
    String userPinCode;
    String userState;
    String userPhoneNumber;

    public userDetailsBuyModel(String userName, String userEmail, String userHouseNumber, String userAddress, String userPinCode, String userState, String userPhoneNumber) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userHouseNumber = userHouseNumber;
        this.userAddress = userAddress;
        this.userPinCode = userPinCode;
        this.userState = userState;
        this.userPhoneNumber = userPhoneNumber;
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

    public String getUserHouseNumber() {
        return userHouseNumber;
    }

    public void setUserHouseNumber(String userHouseNumber) {
        this.userHouseNumber = userHouseNumber;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserPinCode() {
        return userPinCode;
    }

    public void setUserPinCode(String userPinCode) {
        this.userPinCode = userPinCode;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }
}

package com.example.web.model;

import java.io.Serializable;

public class ResourceModel implements Serializable {

    // Instance Variables
    private String Phone_Number = "phoneNumber";
    private String Message = "message";

    // Getter and setter for the Instance Variables
    public String getPhone_Number() {
        return Phone_Number;
    }

    public String getMessage() {
        return Message;
    }

    public void setPhone_Number(String ph_Num) {
        this.Phone_Number = ph_Num;
    }

    public void setMessage(String msg) {
        this.Message = msg;
    }

    @Override
    public String toString() {
        return "ResourceModel{" +
                "Phone_Number='" + Phone_Number + '\'' +
                ", Message='" + Message + '\'' +
                '}';
    }
}

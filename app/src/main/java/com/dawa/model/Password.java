package com.dawa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Password {

    @SerializedName("user")
    @Expose
    private users user;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("__v")
    @Expose
    private Integer v;


    public users getUser() {
        return user;
    }

    public void setUser(users user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

}
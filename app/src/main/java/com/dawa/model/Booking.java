package com.dawa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Booking {


    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("user")
    @Expose
    private users user;
    @SerializedName("doctors")
    @Expose
    private doctors doctors;

    @SerializedName("purpose")
    @Expose
    private String purpose;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("__v")
    @Expose



    private Integer v;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public users getUser() {
        return user;
    }

    public void setUser(users user) {
        this.user = user;
    }

    public com.dawa.model.doctors getDoctors() {
        return doctors;
    }

    public void setDoctors(com.dawa.model.doctors doctors) {
        this.doctors = doctors;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }





//        @Override
//    public String toString() {
//        return "Booking{" +
//                "user='" + user + '\'' +
//                ", doctors='" + doctors + '\'' +
//                ", purpose='" + purpose + '\'' +
//                ", date='" + date + '\'' +
//                ", time='" + time + '\'' +
//                '}';
//    }


}
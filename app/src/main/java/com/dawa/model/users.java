package com.dawa.model;

public class users {
    private String _id;


    private String firstname;
    private String lastname;
    private String address;
    private String age;
    private String phone;
    private String email;
    private String gender;
    private String weight;
    private String height;
    private String bloodgroup;
    private String username;
    private String password;
    private String image;
    private String token;

    private boolean expandable;

    public users(String firstname, String lastname, String address, String age, String phone, String email, String gender, String username, String image, int adminUpdate) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.age = age;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.username = username;
        this.image = image;
    }

    public users(String firstname, String lastname, String address, String age, String phone, String email, String gender, String weight, String height, String bloodgroup, String username, String password, String image) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.age = age;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.bloodgroup = bloodgroup;
        this.username = username;
        this.password = password;
        this.image = image;
    }


    public users(String firstname, String lastname, String address, String age, String phone, String email, String gender,  String username) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.age = age;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.username = username;
        this.expandable = false;

    }

    public users(String firstname, String lastname, String age, String address, String phone, String gender, String email, String weight, String height, String bloodgroup) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.address = address;
        this.phone = phone;
        this.gender = gender;
        this.email = email;
        this.weight = weight;
        this.height = height;
        this.bloodgroup = bloodgroup;

    }


    public users(String username, String password){
        this.username = username;
        this.password = password;
    }





    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getWeight(){ return weight;}

    public void setWeight(String weight) {  this.weight = weight; }

    public String getHeight(){ return height;}

    public void setHeight(String height) {  this.height = height; }

    public String getBloodgroup(){ return bloodgroup;}

    public void setBloodgroup(String bloodgroup) {  this.bloodgroup = bloodgroup; }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }
}


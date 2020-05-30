package com.dawa.model;

public class feedbacks {
    private String email;
    private String message;

    public feedbacks(String email, String feedback) {
        this.email = email;
        this.message = feedback;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

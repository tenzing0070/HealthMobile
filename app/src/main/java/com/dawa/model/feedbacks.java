package com.dawa.model;

public class feedbacks {
    private String _id;
    private String email;
    private String message;

    private boolean expandable;

    public feedbacks(String email, String feedback) {
        this.email = email;
        this.message = feedback;
        this.expandable = false;
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

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }


    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }
}

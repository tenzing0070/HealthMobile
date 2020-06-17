package com.dawa.model;

public class Instructions {

    private String _id;
    private String codename;
    private String instruction;
    private String description;
    private String image;

    private boolean expandable;

    public Instructions(String codename, String instruction, String description, String image) {
        this.codename = codename;
        this.instruction = instruction;
        this.description = description;
        this.image = image;
        this.expandable = false;
    }

    public String getCodename() {
        return codename;
    }

    public void setCodename(String codename) {
        this.codename = codename;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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





    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }

//    @Override
//    public String toString() {
//        return "Instructions{" +
//                "codeName='" + codeName + '\'' +
//                ", instruction='" + instruction + '\'' +
//                ", description='" + description + '\'' +
//                ", image='" + image + '\'' +
//                '}';
//    }

}
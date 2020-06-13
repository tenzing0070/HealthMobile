package com.dawa.model;

public class Instructions {

    private String codeName, instruction, description;
    private int image;
    private boolean expandable;

    public Instructions(String codeName, String instruction, String description, int image) {
        this.codeName = codeName;
        this.instruction = instruction;
        this.description = description;
        this.image = image;
        this.expandable = false;
    }


    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
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


    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }

    @Override
    public String toString() {
        return "Instructions{" +
                "codeName='" + codeName + '\'' +
                ", instruction='" + instruction + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

}
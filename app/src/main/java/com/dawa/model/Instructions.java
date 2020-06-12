package com.dawa.model;

public class Instructions {

    private String codeName, instruction, description;
    private boolean expandable;

    public Instructions(String codeName, String instruction, String description) {
        this.codeName = codeName;
        this.instruction = instruction;
        this.description = description;
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
                '}';
    }

}
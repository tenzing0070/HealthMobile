package com.dawa.model;

public class Faqs {

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    private String questionName, answer;

    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }

    private boolean expandable;

    public Faqs(String questionName, String answer) {
        this.questionName = questionName;
        this.answer = answer;
        this.expandable = false;
    }


    @Override
    public String toString() {
        return "Faqs{" +
                "questionName='" + questionName + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}

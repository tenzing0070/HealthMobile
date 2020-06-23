package com.dawa.model;

public class Faqs {

    private String _id;
    private String question;
    private String answer;

    private boolean expandable;

    public Faqs( String question, String answer) {

        this.question = question;
        this.answer = answer;
        this.expandable = false;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }


    //    public String getQuestionName() {
//        return questionName;
//    }
//
//    public void setQuestionName(String questionName) {
//        this.questionName = questionName;
//    }
//
//    public String getAnswer() {
//        return answer;
//    }
//
//    public void setAnswer(String answer) {
//        this.answer = answer;
//    }
//
//    private String questionName, answer;
//
//    public boolean isExpandable() {
//        return expandable;
//    }
//
//    public void setExpandable(boolean expandable) {
//        this.expandable = expandable;
//    }
//
//    private boolean expandable;
//
//    public Faqs(String questionName, String answer) {
//        this.questionName = questionName;
//        this.answer = answer;
//        this.expandable = false;
//    }
//
//    @Override
//    public String toString() {
//        return "Faqs{" +
//                "questionName='" + questionName + '\'' +
//                ", answer='" + answer + '\'' +
//                '}';
//    }
}

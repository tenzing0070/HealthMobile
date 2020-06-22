package com.dawa.mobilehealth.Quiz;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dawa.model.Question;

import java.util.ArrayList;
import java.util.List;


public class QuizDbHelper extends SQLiteOpenHelper {
    private static  final String DATABASE_NAME = "QUiz.db";
    private static  final int DATABASE_VERSION = 1;

    private  SQLiteDatabase db;

    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final  String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuizContract.QuestionsTable.TABLE_NAME + " ( " +
                QuizContract.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_ANSWER_NR + " INTEGER " +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    private void    fillQuestionsTable(){
        Question q1 = new Question("Food nutrients that help your body to grow and to repair itself.  " +
                "These types of foods are needed everyday.", "Carbohydrates", "Fiber",  "Proteins",  3);
        addQuestion(q1);

        Question q2 = new Question("Vitamin D is sometimes called the:", "Sleepy vitamin", "The sunshine vitamin",  "The dorky vitamin",  2);
        addQuestion(q2);

        Question q3 = new Question("What is the most prevalent contagious disease in the world ?", "Common cold", "Heart disease",  "Tooth decay",  1);
        addQuestion(q3);

        Question q4 = new Question("What vitamin is needed by the retina of the eye? ?", "Vitamin A", "Vitamin C",  "Vitamin B6",  1);
        addQuestion(q4);

        Question q5 = new Question("Mild Symptoms of Novel coronavirus are:", "Fever / Cough", "Shortness of breath",  "All the above",  3);
        addQuestion(q5);

        Question q6 = new Question("What is the name of a deadly mosquito-borne disease?", "Mumps", "Malaria",  "Measles",  2);
        addQuestion(q6);

        Question q7 = new Question("Which is the most important hygiene habit to teach young children ?", "Wash hand frequently", "Take a bath daily",  "Don't share a glass or eating utensil",  1);
        addQuestion(q7);

        Question q8 = new Question("Sleep hygiene refers to the promotion of regular sleep. Which of these can help you develop healthy sleep habits ?", "Eat a big meal late in the day", "Cut back on the amount of exercise you get",  "Go to bed early and get up early at the same time every day",  3);
        addQuestion(q8);

        Question q9 = new Question("A normal resting heart rate for adults ranges from ?", "100 - 120 beats per minute", "60 - 100 beats per minute",  "40 - 50 beats per minute",  2);
        addQuestion(q9);

        Question q10 = new Question("Adults needs good sleeping hours between ?", "7 - 9 hours", "4 - 5 hours",  "10 - 12 hours",  1);
        addQuestion(q10);







    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuizContract.QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuizContract.QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        db.insert(QuizContract.QuestionsTable.TABLE_NAME, null, cv);
    }

    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuizContract.QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()){
            do{
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr((c.getInt(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_ANSWER_NR))));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;


    }
}

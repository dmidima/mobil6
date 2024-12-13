package com.example.cursovoi_test;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.List;

public class TestActivity extends AppCompatActivity {
    private List<Question> questions;


    private int[] answers;
    private int currentQuestion = 0;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        dbHelper = new DBHelper(this);
        questions = dbHelper.getAllQuestions();
        answers = new int[questions.size()];
        showQuestion();
    }

    private void showQuestion() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentContainer, QuestionFragment.newInstance(questions.get(currentQuestion), currentQuestion, answers));
        transaction.commit();
    }

    public void showNextQuestion() {
        currentQuestion++;
        if (currentQuestion < questions.size()) {
            showQuestion();
        } else {
            showResult();
        }
    }



    public void showResult() {
        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String userName = sharedPreferences.getString("username", ""); // Получаем имя пользователя

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentContainer, ResultFragment.newInstance(answers, questions, dbHelper.getAllPersonalityTypes(), userName));
        transaction.commit();
    }
}

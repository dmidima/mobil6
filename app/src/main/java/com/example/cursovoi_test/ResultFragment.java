
package com.example.cursovoi_test;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ResultFragment extends Fragment {
    private int[] answers;
    private List<Question> questions;
    private List<PersonalityType> personalityTypes;
    private int[][][] personalityMatrix; // Исправлено: трехмерный массив
    private DBHelper dbHelper;
    private SharedPreferences sharedPreferences;
    private String userName;

    public static ResultFragment newInstance(int[] answers, List<Question> questions, List<PersonalityType> personalityTypes, String userName) {
        ResultFragment fragment = new ResultFragment();
        Bundle args = new Bundle();
        args.putIntArray("answers", answers);
        args.putParcelableArrayList("questions", (ArrayList<Question>) questions);
        args.putParcelableArrayList("personalityTypes", (ArrayList<PersonalityType>) personalityTypes);
        args.putString("userName", userName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            answers = getArguments().getIntArray("answers");
            questions = getArguments().getParcelableArrayList("questions");
            personalityTypes = getArguments().getParcelableArrayList("personalityTypes");
            userName = getArguments().getString("userName");
            personalityMatrix = loadPersonalityMatrix();
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);
        TextView resultTextView = view.findViewById(R.id.resultTextView);
        Button backButton = view.findViewById(R.id.backButton);

        int[] personalityScores = calculatePersonalityScores(answers, questions);
        String personalityType = determinePersonalityType(personalityScores);
        String detailedInfo = getDetailedInfo(personalityType);

        resultTextView.setText("Ваш результат: " + personalityType + "\n\n" + detailedInfo);

        dbHelper = new DBHelper(getContext()); // Инициализируем dbHelper здесь
        String resultDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
        dbHelper.addTestResult(userName, resultDate, personalityType, detailedInfo);

        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), MainActivity.class);
            startActivity(intent);
        });

        return view;
    }

    private int[] calculatePersonalityScores(int[] answers, List<Question> questions) {
        int[] personalityScores = new int[personalityTypes.size()];
        //Исправлено условие цикла
        for (int i = 4; i < answers.length - 5; i++) {
            if (i >= personalityMatrix.length + 4) {
                Log.e("PersonalityTest", "Index out of bounds: i = " + i + ", answers.length = " + answers.length + ", personalityMatrix.length = " + personalityMatrix.length);
                continue;
            }
            int answerIndex = answers[i];
            List<Answer> currentAnswers = questions.get(i).answers;
            if (currentAnswers == null || answerIndex < 0 || answerIndex >= currentAnswers.size()) {
                Log.e("PersonalityTest", "Invalid answerIndex: " + answerIndex + " for question " + i + ", answers size: " + (currentAnswers != null ? currentAnswers.size() : 0));
                continue;
            }
            // Распределение очков изменено
            for (int j = 0; j < personalityTypes.size(); j++) {
                personalityScores[j] += personalityMatrix[i - 4][answerIndex][j];
            }
        }
        return personalityScores;
    }

    private String determinePersonalityType(int[] personalityScores) {
        int maxIndex = 0;
        for (int i = 1; i < personalityScores.length; i++) {
            if (personalityScores[i] > personalityScores[maxIndex]) {
                maxIndex = i;
            }
        }
        return personalityTypes.get(maxIndex).name;
    }

    private int[][][] loadPersonalityMatrix() {
        int numQuestions = 16;
        int numAnswers = 2;
        int numPersonalityTypes = 16;
        int[][][] matrix = new int[numQuestions][numAnswers][numPersonalityTypes];

        //Изменена логика распределения очков. Теперь более равномерно
        for (int i = 0; i < numQuestions; i++) {
            for (int j = 0; j < numPersonalityTypes; j++) {
                //Первый ответ дает немного больше очков близким типам личности
                int scoreAnswer0 = (Math.abs(i - j) <= 3) ? 6 : 3;
                matrix[i][0][j] = scoreAnswer0;
                //Второй ответ дает немного больше очков отдаленным типам личности
                int scoreAnswer1 = (Math.abs(i - j) > 2) ? 2 : 1;
                matrix[i][1][j] = scoreAnswer1;
            }
        }
        return matrix;
    }




    private String getDetailedInfo(String personalityTypeName) {
        for (PersonalityType personalityType : personalityTypes) {
            if (personalityType.name.equals(personalityTypeName)) {
                return personalityType.description;
            }
        }
        return "Информация о типе личности не найдена";
    }
}

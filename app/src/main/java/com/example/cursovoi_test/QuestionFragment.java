package com.example.cursovoi_test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class QuestionFragment extends Fragment {
    private Question question;
    private int questionNumber;
    private int[] answers;

    public static QuestionFragment newInstance(Question question, int questionNumber, int[] answers) {
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putParcelable("question", question);
        args.putInt("questionNumber", questionNumber);
        args.putIntArray("answers", answers);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            question = getArguments().getParcelable("question");
            questionNumber = getArguments().getInt("questionNumber");
            answers = getArguments().getIntArray("answers");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question, container, false);
        TextView questionTextView = view.findViewById(R.id.questionTextView);
        RadioGroup radioGroup = view.findViewById(R.id.radioGroup);
        Button continueButton = view.findViewById(R.id.continueButton);

        questionTextView.setText(question.text);
        radioGroup.removeAllViews(); // Очистка RadioGroup

        // Добавление RadioButton динамически
        for (Answer answer : question.answers) {
            RadioButton radioButton = new RadioButton(getContext());
            radioButton.setText(answer.text);
            radioButton.setId(View.generateViewId());
            radioButton.setPadding(0, 16, 0, 16); // Добавление отступов
            radioGroup.addView(radioButton);
        }

        continueButton.setOnClickListener(v -> {
            int selectedId = radioGroup.getCheckedRadioButtonId();
            int answerIndex = -1;
            for (int i = 0; i < radioGroup.getChildCount(); i++) {
                if (radioGroup.getChildAt(i).getId() == selectedId) {
                    answerIndex = i;
                    break;
                }
            }
            if (answerIndex != -1) {
                answers[questionNumber] = answerIndex;
                ((TestActivity) getActivity()).showNextQuestion();
            }
        });

        return view;
    }
}

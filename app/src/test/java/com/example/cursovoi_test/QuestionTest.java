package com.example.cursovoi_test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class QuestionTest {
    @Test
    public void testQuestionCreation() {
        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer(1, "Ответ 1", true));
        answers.add(new Answer(2, "Ответ 2", false));
        Question question = new Question(1, "Это тестовый вопрос.", answers);
        assertEquals(1, question.id);
        assertEquals("Это тестовый вопрос.", question.text);
        assertEquals(2, question.answers.size());
    }
}

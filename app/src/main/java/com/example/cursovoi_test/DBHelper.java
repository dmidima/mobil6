
package com.example.cursovoi_test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "test_app.db";
    private static final int DATABASE_VERSION = 22;

    // Таблица пользователей
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_LOGIN = "login";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";

    // Таблица вопросов
    private static final String TABLE_QUESTIONS = "questions";
    private static final String COLUMN_QUESTION_ID = "question_id";
    private static final String COLUMN_QUESTION_TEXT = "question_text";

    // Таблица ответов
    private static final String TABLE_ANSWERS = "answers";
    private static final String COLUMN_ANSWER_ID = "answer_id";
    private static final String COLUMN_QUESTION_FK = "question_fk";
    private static final String COLUMN_ANSWER_TEXT = "answer_text";
    private static final String COLUMN_IS_CORRECT = "is_correct";

    // Таблица типов личности
    private static final String TABLE_PERSONALITY_TYPES = "personality_types";
    private static final String COLUMN_TYPE_ID = "type_id";
    private static final String COLUMN_TYPE_NAME = "type_name";
    private static final String COLUMN_TYPE_DESCRIPTION = "type_description";

    private static final String TABLE_TEST_RESULTS = "test_results";
    private static final String COLUMN_RESULT_ID = "result_id";
    private static final String COLUMN_USER_LOGIN = "user_login";
    private static final String COLUMN_RESULT_DATE = "result_date";
    private static final String COLUMN_PERSONALITY_TYPE = "personality_type";
    private static final String COLUMN_PERSONALITY_DESCRIPTION = "personalityDescription";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserTable = "CREATE TABLE " + TABLE_USERS + " (" +
                COLUMN_LOGIN + " TEXT UNIQUE," +
                COLUMN_EMAIL + " TEXT," +
                COLUMN_PASSWORD + " TEXT)";

        String createQuestionsTable = "CREATE TABLE " + TABLE_QUESTIONS + " (" +
                COLUMN_QUESTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_QUESTION_TEXT + " TEXT)";

        String createAnswersTable = "CREATE TABLE " + TABLE_ANSWERS + " (" +
                COLUMN_ANSWER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_QUESTION_FK + " INTEGER," +
                COLUMN_ANSWER_TEXT + " TEXT," +
                COLUMN_IS_CORRECT + " INTEGER," +
                "FOREIGN KEY (" + COLUMN_QUESTION_FK + ") REFERENCES " + TABLE_QUESTIONS + "(" + COLUMN_QUESTION_ID + ")" + ")";

        String createPersonalityTypesTable = "CREATE TABLE " + TABLE_PERSONALITY_TYPES + " (" +
                COLUMN_TYPE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_TYPE_NAME + " TEXT UNIQUE," +
                COLUMN_TYPE_DESCRIPTION + " TEXT)";

        String createTestResultsTable = "CREATE TABLE " + TABLE_TEST_RESULTS + " (" +
                COLUMN_RESULT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_USER_LOGIN + " TEXT," +
                COLUMN_RESULT_DATE + " TEXT," +
                COLUMN_PERSONALITY_TYPE + " TEXT," +
                COLUMN_PERSONALITY_DESCRIPTION + " TEXT," + // Добавлено
                "FOREIGN KEY (" + COLUMN_USER_LOGIN + ") REFERENCES " + TABLE_USERS + "(" + COLUMN_LOGIN + ")" + ")";

        db.execSQL(createTestResultsTable);
        db.execSQL(createUserTable);
        db.execSQL(createQuestionsTable);
        db.execSQL(createAnswersTable);
        db.execSQL(createPersonalityTypesTable);

        db.execSQL("INSERT INTO personality_types (type_name, type_description) VALUES ('ISTJ - Администратор', 'Администраторы – это практичные и опирающиеся на факты личности, в надежности которых сложно усомниться.')");
        db.execSQL("INSERT INTO personality_types (type_name, type_description) VALUES ('ISFJ - Защитник', 'Защитники – это очень преданные и заботливые защитники, всегда готовые оберегать своих близких.')");
        db.execSQL("INSERT INTO personality_types (type_name, type_description) VALUES ('INFJ - Активист', 'Активисты – это тихие мечтатели, часто являющиеся вдохновляющими и неутомимыми идеалистами.')");
        db.execSQL("INSERT INTO personality_types (type_name, type_description) VALUES ('INTJ - Стратег', 'Стратеги – это творческие и стратегические мыслители, у которых на все есть план.')");
        db.execSQL("INSERT INTO personality_types (type_name, type_description) VALUES ('ISTP - Виртуоз', 'Виртуозы – это новаторы и практичные экспериментаторы, мастера на все руки.')");
        db.execSQL("INSERT INTO personality_types (type_name, type_description) VALUES ('ISFP - Артист', 'Артисты – это гибкие и обаятельные люди, всегда готовые исследовать и испытать что-то новое.')");
        db.execSQL("INSERT INTO personality_types (type_name, type_description) VALUES ('INFP - Посредник', 'Посредники – это поэтичные, добрые и альтруистичные люди, всегда готовые помочь в хорошем деле.')");
        db.execSQL("INSERT INTO personality_types (type_name, type_description) VALUES ('INTP - Ученый', 'Ученые – это изобретатели-новаторы с неутолимой жаждой знаний.')");
        db.execSQL("INSERT INTO personality_types (type_name, type_description) VALUES ('ESTP - Делец', 'Дельцы – это сообразительные, энергичные и очень проницательные люди, которым действительно нравится жить на грани.')");
        db.execSQL("INSERT INTO personality_types (type_name, type_description) VALUES ('ESFP - Развлекатель', 'Развлекатели – это спонтанные, энергичные энтузиасты, рядом с которыми жизнь никогда не бывает скучной.')");
        db.execSQL("INSERT INTO personality_types (type_name, type_description) VALUES ('ENFP - Борец', 'Борцы – это энтузиасты, креативные и социальные люди, которые всегда найдут причину, чтобы улыбнуться.')");
        db.execSQL("INSERT INTO personality_types (type_name, type_description) VALUES ('ENTP - Полемист', 'Полемисты – это любопытные и гибкие мыслители, которые не могут противостоять интеллектуальным вызовам.')");
        db.execSQL("INSERT INTO personality_types (type_name, type_description) VALUES ('ESTJ - Менеджер', 'Менеджеры – это отличные организаторы, знающие толк в управлении вещами или людьми.')");
        db.execSQL("INSERT INTO personality_types (type_name, type_description) VALUES ('ESFJ - Консул', 'Консулы – это очень заботливые, социальные, думающие об обществе люди, которые всегда готовы помочь.')");
        db.execSQL("INSERT INTO personality_types (type_name, type_description) VALUES ('ENFJ - Тренер', 'Тренеры – это вдохновляющие оптимисты, готовые сразу действовать, чтобы сделать то, что считают правильным.')");
        db.execSQL("INSERT INTO personality_types (type_name, type_description) VALUES ('ENTJ - Командир', 'Командиры – это смелые люди с хорошим воображением и силой воли, они всегда найдут путь или проложат его сами.')");


        db.execSQL("INSERT INTO questions (question_text) VALUES ('Как вы предпочитаете проводить свободное время?')");
        db.execSQL("INSERT INTO answers (question_fk, answer_text, is_correct) VALUES (1, 'В одиночестве', 0)");
        db.execSQL("INSERT INTO answers (question_fk, answer_text, is_correct) VALUES (1, 'В компании друзей', 1)");

        db.execSQL("INSERT INTO questions (question_text) VALUES ('Как вы принимаете решения?')");
        db.execSQL("INSERT INTO answers (question_fk, answer_text, is_correct) VALUES (2, 'На основе логики и фактов', 0)");
        db.execSQL("INSERT INTO answers (question_fk, answer_text, is_correct) VALUES (2, 'На основе интуиции и чувств', 1)");

        db.execSQL("INSERT INTO questions (question_text) VALUES ('Как вы реагируете на стресс?')");
        db.execSQL("INSERT INTO answers (question_fk, answer_text, is_correct) VALUES (3, 'Анализируете ситуацию', 0)");
        db.execSQL("INSERT INTO answers (question_fk, answer_text, is_correct) VALUES (3, 'Действуете интуитивно', 1)");

        db.execSQL("INSERT INTO questions (question_text) VALUES ('Что для вас важнее?')");
        db.execSQL("INSERT INTO answers (question_fk, answer_text, is_correct) VALUES (4, 'Детали и факты', 0)");
        db.execSQL("INSERT INTO answers (question_fk, answer_text, is_correct) VALUES (4, 'Общая картина и идеи', 1)");

        db.execSQL("INSERT INTO questions (question_text) VALUES ('Как вы относитесь к планированию?')");
        db.execSQL("INSERT INTO answers (question_fk, answer_text, is_correct) VALUES (5, 'Предпочитаю заранее все спланировать', 0)");
        db.execSQL("INSERT INTO answers (question_fk, answer_text, is_correct) VALUES (5, 'Люблю действовать спонтанно', 1)");

        db.execSQL("INSERT INTO questions (question_text) VALUES ('Как вы воспринимаете правила?')");
        db.execSQL("INSERT INTO answers (question_fk, answer_text, is_correct) VALUES (6, 'Следую им строго', 0)");
        db.execSQL("INSERT INTO answers (question_fk, answer_text, is_correct) VALUES (6, 'Предпочитаю гибкость', 1)");

        db.execSQL("INSERT INTO questions (question_text) VALUES ('Вам комфортнее работать:')");
        db.execSQL("INSERT INTO answers (question_fk, answer_text, is_correct) VALUES (7, 'В команде', 0)");
        db.execSQL("INSERT INTO answers (question_fk, answer_text, is_correct) VALUES (7, 'Самостоятельно', 1)");

        db.execSQL("INSERT INTO questions (question_text) VALUES ('Как вы относитесь к новым идеям?')");
        db.execSQL("INSERT INTO answers (question_fk, answer_text, is_correct) VALUES (8, 'Воспринимаю с интересом', 0)");
        db.execSQL("INSERT INTO answers (question_fk, answer_text, is_correct) VALUES (8, 'Предпочитаю проверенные методы', 1)");

        db.execSQL("INSERT INTO questions (question_text) VALUES ('Вы чаще сосредоточены на:')");
        db.execSQL("INSERT INTO answers (question_fk, answer_text, is_correct) VALUES (9, 'Внешнем мире', 0)");
        db.execSQL("INSERT INTO answers (question_fk, answer_text, is_correct) VALUES (9, 'Своих мыслях', 1)");

        db.execSQL("INSERT INTO questions (question_text) VALUES ('Какой стиль общения вам ближе?')");
        db.execSQL("INSERT INTO answers (question_fk, answer_text, is_correct) VALUES (10, 'Прямолинейный и открытый', 0)");
        db.execSQL("INSERT INTO answers (question_fk, answer_text, is_correct) VALUES (10, 'Сдержанный и осторожный', 1)");

        db.execSQL("INSERT INTO questions (question_text) VALUES ('Вам важнее:')");
        db.execSQL("INSERT INTO answers (question_fk, answer_text, is_correct) VALUES (11, 'Принятие обществом', 0)");
        db.execSQL("INSERT INTO answers (question_fk, answer_text, is_correct) VALUES (11, 'Следование своим убеждениям', 1)");

        db.execSQL("INSERT INTO questions (question_text) VALUES ('Как вы относитесь к рутине?')");
        db.execSQL("INSERT INTO answers (question_fk, answer_text, is_correct) VALUES (12, 'Нахожу ее комфортной', 0)");
        db.execSQL("INSERT INTO answers (question_fk, answer_text, is_correct) VALUES (12, 'Скучной и ограничивающей', 1)");

        db.execSQL("INSERT INTO questions (question_text) VALUES ('Вы стремитесь к:')");
        db.execSQL("INSERT INTO answers (question_fk, answer_text, is_correct) VALUES (13, 'Стабильности', 0)");
        db.execSQL("INSERT INTO answers (question_fk, answer_text, is_correct) VALUES (13, 'Изменениям и новшествам', 1)");

        db.execSQL("INSERT INTO questions (question_text) VALUES ('Как вы воспринимаете критику?')");
        db.execSQL("INSERT INTO answers (question_fk, answer_text, is_correct) VALUES (14, 'Принимаю к сведению', 0)");
        db.execSQL("INSERT INTO answers (question_fk, answer_text, is_correct) VALUES (14, 'Обижусь', 1)");

        db.execSQL("INSERT INTO questions (question_text) VALUES ('Вам важнее:')");
        db.execSQL("INSERT INTO answers (question_fk, answer_text, is_correct) VALUES (15, 'Достижения и результаты', 0)");
        db.execSQL("INSERT INTO answers (question_fk, answer_text, is_correct) VALUES (15, 'Отношения с окружающими', 1)");

        db.execSQL("INSERT INTO questions (question_text) VALUES ('Что для вас важнее: процесс или результат?')");
        db.execSQL("INSERT INTO answers (question_fk, answer_text, is_correct) VALUES (16, 'Процесс, ведь он формирует опыт.', 0)");
        db.execSQL("INSERT INTO answers (question_fk, answer_text, is_correct) VALUES (16, 'Результат, так как он показывает успех.', 1)");

        db.execSQL("INSERT INTO questions (question_text) VALUES ('Вам нравится быть в центре внимания?')");
        db.execSQL("INSERT INTO answers (question_fk, answer_text, is_correct) VALUES (17, 'Да, это приятно', 0)");
        db.execSQL("INSERT INTO answers (question_fk, answer_text, is_correct) VALUES (17, 'Нет, предпочитаю оставаться в тени', 1)");

        db.execSQL("INSERT INTO questions (question_text) VALUES ('Как вы воспринимаете новые знакомства?')");
        db.execSQL("INSERT INTO answers (question_fk, answer_text, is_correct) VALUES (18, 'С интересом и любопытством', 0)");
        db.execSQL("INSERT INTO answers (question_fk, answer_text, is_correct) VALUES (18, 'С осторожностью и настороженностью', 1)");

        db.execSQL("INSERT INTO questions (question_text) VALUES ('Вы чаще:')");
        db.execSQL("INSERT INTO answers (question_fk, answer_text, is_correct) VALUES (19, 'Стремитесь к совершенству', 0)");
        db.execSQL("INSERT INTO answers (question_fk, answer_text, is_correct) VALUES (19, 'Принимаете вещи такими, какие они есть', 1)");

        db.execSQL("INSERT INTO questions (question_text) VALUES ('Что для вас важнее в работе?')");
        db.execSQL("INSERT INTO answers (question_fk, answer_text, is_correct) VALUES (20, 'Логика и эффективность', 0)");
        db.execSQL("INSERT INTO answers (question_fk, answer_text, is_correct) VALUES (20, 'Креативность и самовыражение', 1)");


        db.execSQL("INSERT INTO questions (question_text) VALUES ('Какой тип задач вам нравится больше всего?')");
        db.execSQL("INSERT INTO answers (question_fk, answer_text, is_correct) VALUES (21, 'Структурированные задачи с четкими инструкциями.', 0)");
        db.execSQL("INSERT INTO answers (question_fk, answer_text, is_correct) VALUES (21, 'Творческие задачи, требующие нестандартных решений.', 1)");

        db.execSQL("INSERT INTO questions (question_text) VALUES ('Что мотивирует вас в большей степени?')");
        db.execSQL("INSERT INTO answers (question_fk, answer_text, is_correct) VALUES (22, 'Достижение конкретных целей и результатов.', 0)");
        db.execSQL("INSERT INTO answers (question_fk, answer_text, is_correct) VALUES (22, 'Внутренняя удовлетворенность и самореализация.', 1)");


        db.execSQL("INSERT INTO questions (question_text) VALUES ('Как вы предпочитаете получать информацию?')");
        db.execSQL("INSERT INTO answers (question_fk, answer_text, is_correct) VALUES (23, 'Через логические рассуждения и анализ фактов.', 0)");
        db.execSQL("INSERT INTO answers (question_fk, answer_text, is_correct) VALUES (23, 'Через интуицию и эмоциональное восприятие.', 1)");


        db.execSQL("INSERT INTO questions (question_text) VALUES ('Как вы относитесь к риску?')");
        db.execSQL("INSERT INTO answers (question_fk, answer_text, is_correct) VALUES (24, 'Я люблю пробовать новое и рисковать.', 0)");
        db.execSQL("INSERT INTO answers (question_fk, answer_text, is_correct) VALUES (24, 'Я предпочитаю избегать рисков и действовать осторожно.', 1)");

        db.execSQL("INSERT INTO questions (question_text) VALUES ('Как вы реагируете на стрессовые ситуации?')");
        db.execSQL("INSERT INTO answers (question_fk, answer_text, is_correct) VALUES (25, 'Я сохраняю спокойствие и стараюсь найти решение.', 0)");
        db.execSQL("INSERT INTO answers (question_fk, answer_text, is_correct) VALUES (25, 'Я могу паниковать и терять концентрацию.', 1)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion < 2) { // Увеличиваем DATABASE_VERSION в случае изменений!
            db.execSQL("ALTER TABLE " + TABLE_TEST_RESULTS + " ADD COLUMN " + COLUMN_PERSONALITY_DESCRIPTION + " TEXT");
        }
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ANSWERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERSONALITY_TYPES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEST_RESULTS); // и результатов тестов

        onCreate(db);
    }

    public void addTestResult(String userLogin, String resultDate, String personalityType, String personalityDescription) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_LOGIN, userLogin);
        values.put(COLUMN_RESULT_DATE, resultDate);
        values.put(COLUMN_PERSONALITY_TYPE, personalityType);
        values.put("personalityDescription", personalityDescription); // Добавлено сохранение описания
        db.insert(TABLE_TEST_RESULTS, null, values);
        db.close();
    }

    public List<TestResult> getTestResultsForUser(String userLogin) {
        List<TestResult> testResults = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT tr.result_date, tr.personality_type, pt.type_description " +
                "FROM test_results tr " +
                "JOIN personality_types pt ON tr.personality_type = pt.type_name " +
                "WHERE tr.user_login = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{userLogin});

        if (cursor.moveToFirst()) {
            do {
                String resultDate = cursor.getString(cursor.getColumnIndexOrThrow("result_date"));
                String personalityType = cursor.getString(cursor.getColumnIndexOrThrow("personality_type"));
                String personalityDescription = cursor.getString(cursor.getColumnIndexOrThrow("type_description"));
                testResults.add(new TestResult(resultDate, personalityType, personalityDescription));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return testResults;
    }

    public boolean addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_LOGIN, user.login);
        values.put(COLUMN_EMAIL, user.email);
        values.put(COLUMN_PASSWORD, user.password);
        long result = db.insert(TABLE_USERS, null, values);
        db.close();
        return result != -1;
    }

    public User getUser(String login) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_LOGIN, COLUMN_EMAIL, COLUMN_PASSWORD};
        String selection = COLUMN_LOGIN + " = ?";
        String[] selectionArgs = {login};
        Cursor cursor = db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null);

        User user = null;
        if (cursor != null && cursor.moveToFirst()) {
            String userLogin = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LOGIN));
            String userEmail = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL));
            String userPassword = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PASSWORD));
            user = new User(userLogin, userEmail, userPassword);
        }
        cursor.close();
        db.close();
        return user;
    }

    public List<Question> getAllQuestions() {
        List<Question> questions = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_QUESTIONS, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int questionId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_QUESTION_ID));
                String questionText = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_QUESTION_TEXT));
                List<Answer> answers = getAnswersByQuestionId(questionId);
                questions.add(new Question(questionId, questionText, answers));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return questions;
    }

    public List<Answer> getAnswersByQuestionId(int questionId) {
        List<Answer> answers = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String selection = COLUMN_QUESTION_FK + " = ?";
        String[] selectionArgs = {String.valueOf(questionId)};
        Cursor cursor = db.query(TABLE_ANSWERS, null, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int answerId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ANSWER_ID));
                String answerText = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ANSWER_TEXT));
                int isCorrect = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_IS_CORRECT));
                answers.add(new Answer(answerId, answerText, isCorrect == 1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return answers;
    }

    public List<PersonalityType> getAllPersonalityTypes() {
        List<PersonalityType> personalityTypes = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {COLUMN_TYPE_ID, COLUMN_TYPE_NAME, COLUMN_TYPE_DESCRIPTION};
        Cursor cursor = db.query(TABLE_PERSONALITY_TYPES, columns, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int typeId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_TYPE_ID));
                String typeName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TYPE_NAME));
                String typeDescription = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TYPE_DESCRIPTION));
                personalityTypes.add(new PersonalityType(typeId, typeName, typeDescription));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return personalityTypes;
    }


    public Map<String, Integer> getPersonalityTypeStatistics(String userLogin) {
        Map<String, Integer> statistics = new HashMap<>();
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT personality_type, COUNT(*) AS count FROM test_results WHERE user_login = ? GROUP BY personality_type";
        Cursor cursor = db.rawQuery(sql, new String[]{userLogin});

        if (cursor.moveToFirst()) {
            do {
                String personalityType = cursor.getString(0);
                int count = cursor.getInt(1);
                statistics.put(personalityType, count);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return statistics;
    }

}

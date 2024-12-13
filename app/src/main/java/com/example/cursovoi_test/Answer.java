package com.example.cursovoi_test;

import android.os.Parcel;
import android.os.Parcelable;

public class Answer implements Parcelable {
    public int id;
    public String text;
    public boolean isCorrect;

    public Answer(int id, String text, boolean isCorrect) {
        this.id = id;
        this.text = text;
        this.isCorrect = isCorrect;
    }

    protected Answer(Parcel in) {
        id = in.readInt();
        text = in.readString();
        isCorrect = in.readByte() != 0;
    }

    public static final Creator<Answer> CREATOR = new Creator<Answer>() {
        @Override
        public Answer createFromParcel(Parcel in) {
            return new Answer(in);
        }

        @Override
        public Answer[] newArray(int size) {
            return new Answer[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(text);
        dest.writeByte((byte) (isCorrect ? 1 : 0));
    }
}


//public class Answer implements Parcelable {
//    public int id;
//    public String text;
//    public int[] typeScores; // Добавлено поле для баллов типов личности
//
//    public Answer(int id, String text, int[] typeScores) { // Изменен конструктор
//        this.id = id;
//        this.text = text;
//        this.typeScores = typeScores;
//    }
//
//    protected Answer(Parcel in) {
//        id = in.readInt();
//        text = in.readString();
//        typeScores = in.createIntArray(); // Чтение массива int из Parcel
//    }
//
//    public static final Creator<Answer> CREATOR = new Creator<Answer>() {
//        @Override
//        public Answer createFromParcel(Parcel in) {
//            return new Answer(in);
//        }
//
//        @Override
//        public Answer[] newArray(int size) {
//            return new Answer[size];
//        }
//    };
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeInt(id);
//        dest.writeString(text);
//        dest.writeIntArray(typeScores); // Запись массива int в Parcel
//    }
//
//    public String getAnswerText() {
//        return text;
//    }
//
//    public int[] getTypeScores() {
//        return typeScores;
//    }
//}

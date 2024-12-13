package com.example.cursovoi_test;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Question implements Parcelable {
    public int id;
    public String text;
    public List<Answer> answers;

    public Question(int id, String text, List<Answer> answers) {
        this.id = id;
        this.text = text;
        this.answers = answers;
    }

    protected Question(Parcel in) {
        id = in.readInt();
        text = in.readString();
        answers = in.createTypedArrayList(Answer.CREATOR);
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
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
        dest.writeTypedList(answers);
    }
}


//public class Question implements Parcelable {
//    public int id;
//    public String text;
//    public List<Answer> answers;
//
//    public Question(int id, String text, List<Answer> answers) {
//        this.id = id;
//        this.text = text;
//        this.answers = answers;
//    }
//
//    protected Question(Parcel in) {
//        id = in.readInt();
//        text = in.readString();
//        answers = in.createTypedArrayList(Answer.CREATOR);
//    }
//
//    public static final Creator<Question> CREATOR = new Creator<Question>() {
//        @Override
//        public Question createFromParcel(Parcel in) {
//            return new Question(in);
//        }
//
//        @Override
//        public Question[] newArray(int size) {
//            return new Question[size];
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
//        dest.writeTypedList(answers);
//    }
//
//    public List<Answer> getAnswers() {
//        return answers;
//    }
//}

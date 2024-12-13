package com.example.cursovoi_test;

import android.os.Parcel;
import android.os.Parcelable;

public class TestResult implements Parcelable {
    public String resultDate;
    public String personalityType;

    public String personalityDescription; // Добавлено поле для описания


//    public TestResult(String resultDate, String personalityType) {
//        this.resultDate = resultDate;
//        this.personalityType = personalityType;
//    }


    public TestResult(String resultDate, String personalityType, String personalityDescription) {
        this.resultDate = resultDate;
        this.personalityType = personalityType;
        this.personalityDescription = personalityDescription; // Инициализация нового поля
    }



//    protected TestResult(Parcel in) {
//        resultDate = in.readString();
//        personalityType = in.readString();
//    }


    protected TestResult(Parcel in) {
        resultDate = in.readString();
        personalityType = in.readString();
        personalityDescription = in.readString();
    }

//    public static final Creator<TestResult> CREATOR = new Creator<TestResult>() {
//        @Override
//        public TestResult createFromParcel(Parcel in) {
//            return new TestResult(in);
//        }
//
//        @Override
//        public TestResult[] newArray(int size) {
//            return new TestResult[size];
//        }
//    };

    @Override
    public int describeContents() {
        return 0;
    }

//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(resultDate);
//        dest.writeString(personalityType);
//    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(resultDate);
        dest.writeString(personalityType);
        dest.writeString(personalityDescription);
    }


    public static final Creator<TestResult> CREATOR = new Creator<TestResult>() {
        @Override
        public TestResult createFromParcel(Parcel in) {
            return new TestResult(in);
        }

        @Override
        public TestResult[] newArray(int size) {
            return new TestResult[size];
        }
    };



}
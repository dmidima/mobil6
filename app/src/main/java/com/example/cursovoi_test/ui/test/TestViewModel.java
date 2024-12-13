package com.example.cursovoi_test.ui.test;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TestViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public TestViewModel() {
        mText = new MutableLiveData<>();
//        mText.setValue("This is ТЕСТ fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
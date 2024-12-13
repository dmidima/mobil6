package com.example.cursovoi_test.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
//        mText.setValue("This is home fragment - НА НАЧАЛЬНОЙ СТРАНИЦЕ МОЖНО СДЕЛАТЬ ОПИСАНИЕ ТИПОВ ЛИЧНОСТИ , ДОБАВИТЬ ЧТО НЕТ ГАРАНТИЙ РЕЗУЛЬТАТА");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
package com.example.cursovoi_test.ui.profile;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cursovoi_test.QuoteApi;
import com.example.cursovoi_test.ForismaticQuoteResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileViewModel extends ViewModel {

    private MutableLiveData<String> mText = new MutableLiveData<>();
    public LiveData<String> getText() { return mText; }

    private MutableLiveData<String> quoteText = new MutableLiveData<>();
    public LiveData<String> getQuoteText() { return quoteText; }

    public ProfileViewModel() {
//        mText.setValue("This is ПРОФИЛЬ fragment ТУТ БУДЕТ ПРОФИЛЬ");
        fetchQuote();
    }

    private void fetchQuote() {
        Log.d("QuoteAPI", "Fetching quote...");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.forismatic.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        QuoteApi quoteApi = retrofit.create(QuoteApi.class);
        Call<ForismaticQuoteResponse> call = quoteApi.getQuote("getQuote", "json", "API_KEY_HERE");

        call.enqueue(new Callback<ForismaticQuoteResponse>() {
            @Override
            public void onResponse(Call<ForismaticQuoteResponse> call, Response<ForismaticQuoteResponse> response) {
                Log.d("QuoteAPI", "Response code: " + response.code());
                Log.d("QuoteAPI", "Response body: " + response.body());
                if (response.isSuccessful()) {
                    ForismaticQuoteResponse quote = response.body();
                    if (quote != null && quote.quoteText != null && quote.quoteAuthor != null) {
                        quoteText.setValue("\"" + quote.quoteText + "\" - " + quote.quoteAuthor);
                    } else {
                        quoteText.setValue("Ошибка загрузки цитаты");
                        Log.e("QuoteAPI", "Quote or its fields are null");
                    }
                } else {
                    quoteText.setValue("Ошибка загрузки цитаты (" + response.code() + ")");
                    Log.e("QuoteAPI", "Error fetching quote: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ForismaticQuoteResponse> call, Throwable t) {
                Log.e("QuoteAPI", "Network Error: " + t.getMessage(), t);
                quoteText.setValue("Ошибка сети");
            }
        });
    }
}


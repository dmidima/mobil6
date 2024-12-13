package com.example.cursovoi_test;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.cursovoi_test.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private WebView webView;
    private NavController navController;
    private boolean isDocsVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Button startTestButton = findViewById(R.id.startTestButton);
        startTestButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TestActivity.class);
            startActivity(intent);
        });

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_test, R.id.navigation_profile)
                .build();

        webView = findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        // Обработчик нажатия на элементы BottomNavigationView
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (isDocsVisible) {
                    hideDocumentation(); // Скрываем WebView
                }
                return NavigationUI.onNavDestinationSelected(item, navController);
            }
        });

        Button showDocsButton = findViewById(R.id.showDocsButton);
        showDocsButton.setOnClickListener(v -> showDocumentation());


        // Обработчик кнопки "Назад" в WebView
        webView.setWebChromeClient(new WebChromeClient() {
            //  Этот метод может быть нужен для обработки предупреждений JavaScript,
            //  но в данном примере он не обязательно нужен
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }
        });

        webView.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
                webView.goBack();
                return true;
            }
            return false;
        });
    }

    public void showDocumentation() {
        isDocsVisible = true;
        webView.loadUrl("https://dmidima.github.io/flutter_application_1/#/");
        webView.setVisibility(View.VISIBLE);
        findViewById(R.id.nav_host_fragment_activity_main).setVisibility(View.GONE);
        webView.bringToFront();
    }

    private void hideDocumentation() {
        isDocsVisible = false;
        webView.setVisibility(View.GONE);
        findViewById(R.id.nav_host_fragment_activity_main).setVisibility(View.VISIBLE);
    }
}

package com.example.cursovoi_test;

public class User {
    public String login;
    public String email;
    public String password; // В реальном приложении нужно использовать более безопасное хранение паролей!

    public User(String login, String email, String password) {
        this.login = login;
        this.email = email;
        this.password = password;
    }
}

package com.example.loginform;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private final HashMap<String, String> users = new HashMap<>();
    private EditText loginEditText;
    private EditText passEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginEditText = findViewById(R.id.login_input);
        passEditText = findViewById(R.id.pass_input);
        Button registerBtn = findViewById(R.id.register_btn);
        Button loginBtn = findViewById(R.id.login_btn);
        registerBtn.setOnClickListener((View v) -> {
            String login = loginEditText.getText().toString().trim();
            String pass = passEditText.getText().toString().trim();
            if (checkOnEmpty(login, pass)) {
                register(v, login, pass);
            }
        });
        loginBtn.setOnClickListener((View v) -> {
            String login = loginEditText.getText().toString().trim();
            String pass = passEditText.getText().toString().trim();
            if (checkOnEmpty(login, pass)) {
                login(v, login, pass);
            }
        });
        users.put("admin", "12345");
    }

    public boolean checkOnEmpty(String login, String pass) {
        if (login.equals("") || pass.equals("")) {
            if (login.equals("")) {
                loginEditText.setError("Must be not empty!");
            } else {
                passEditText.setError("Must be not empty!");
            }
            return false;
        } else {
            return true;
        }
    }

    private void register(View v, String login, String pass) {
        if (users.containsKey(login)) {
            Snackbar.make(v, login + " is already taken.", Snackbar.LENGTH_SHORT).show();
        } else {
            users.put(login, pass);
            Snackbar.make(v, login + " is registered.", Snackbar.LENGTH_SHORT).show();
        }
    }

    private void login(View v, String login, String pass) {
        if (users.containsKey(login)) {
            if (users.get(login).equals(pass)) {
                Snackbar.make(v, "Hello " + login, Snackbar.LENGTH_SHORT).show();
            } else {
                passEditText.setError("Wrong password!");
            }
        } else {
            loginEditText.setError("Wrong login!");
        }
    }
}
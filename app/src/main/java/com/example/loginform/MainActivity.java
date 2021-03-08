package com.example.loginform;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import java.util.Hashtable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final Hashtable<String, String> users = new Hashtable<>();
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
        registerBtn.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
        users.put("admin", "12345");
    }

    @Override
    public void onClick(View v) {
        String login, pass;
        if (!(login = loginEditText.getText().toString().trim()).equals("") && !(pass = passEditText.getText().toString().trim()).equals("")) {
            if (v.getId() == R.id.register_btn) {
                if (users.containsKey(login)) {
                    Snackbar.make(v, login + " is already taken.", Snackbar.LENGTH_SHORT).show();
                } else {
                    users.put(login, pass);
                    Snackbar.make(v, login + " is registered.", Snackbar.LENGTH_SHORT).show();
                }
            } else {
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

        } else {
            if (login.equals("")) {
                loginEditText.setError("Must be not empty!");
            } else {
                passEditText.setError("Must be not empty!");
            }
        }
    }
}
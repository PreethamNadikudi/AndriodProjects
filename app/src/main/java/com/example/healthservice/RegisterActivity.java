package com.example.healthservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText userName, passWord, confrimpass, mail;
    Button register;
    TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userName = findViewById(R.id.username);
        passWord = findViewById(R.id.password);
        confrimpass = findViewById(R.id.confirmpassword);
        mail = findViewById(R.id.email);

        register = findViewById(R.id.register);
        login = findViewById(R.id.login);
        Database db=new Database(getApplicationContext(),"healthCare",null,1);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = userName.getText().toString();
                String password = passWord.getText().toString();
                String confirmPassword = confrimpass.getText().toString();
                String email = mail.getText().toString();

                if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || email.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Enter Every Detail", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(confirmPassword)) {
                    Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                } else if (!isValidPassword(password)) {
                    Toast.makeText(getApplicationContext(), "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character", Toast.LENGTH_SHORT).show();
                } else {
                    db.register(username,email,password);
                    Toast.makeText(getApplicationContext(), "Registration SucessFul", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                }
            }
        });
    }

    private boolean isValidPassword(String password) {
        // Minimum 8 characters, at least one uppercase letter, one lowercase letter, one number, and one special character
        if (password.length() < 8) {
            return false;
        }
        boolean hasUpperCase = false, hasLowerCase = false, hasDigit = false, hasSpecialChar = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowerCase = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else {
                hasSpecialChar = true;
            }
        }
        return hasUpperCase && hasLowerCase && hasDigit && hasSpecialChar;
    }
}

package com.example.aproject_2101658224_la26.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aproject_2101658224_la26.R;
import com.example.aproject_2101658224_la26.database.UserDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText UsernameETMainActivity, PasswordETMainActivity;
    private Button ButtonLoginMainActivity;
    private TextView RegisterTVMainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UsernameETMainActivity = findViewById(R.id.usernameETMainActivity);
        PasswordETMainActivity = findViewById(R.id.passwordETMainActivity);
        ButtonLoginMainActivity = findViewById(R.id.buttonLoginMainActivity);
        RegisterTVMainActivity = findViewById(R.id.registerTVMainActivity);
        ButtonLoginMainActivity.setOnClickListener(this);
        RegisterTVMainActivity.setOnClickListener(this);
    }

    private void validateInputButtonLogin() {
        String usernamePenampung = UsernameETMainActivity.getText().toString();
        String passwordPenampung = PasswordETMainActivity.getText().toString();
        if (usernamePenampung.equals("")) {
            UsernameETMainActivity.setError("username must be filled");
        } else if (passwordPenampung.equals("")) {
            PasswordETMainActivity.setError("password must be filled");
        } else {
            boolean isUserExists = new UserDatabase(this).isUserAvailable(usernamePenampung, passwordPenampung);
            if (isUserExists) {
                Intent intent = new Intent(this, CharacterActivity.class);
                startActivity(intent);
                finishAffinity();
            } else {
                Toast.makeText(this, "username and password must be registered before", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void validateRegisterTVMainActivity() {
        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View viewMainActivity) {
        if (viewMainActivity.getId() == R.id.buttonLoginMainActivity) {
            validateInputButtonLogin();
        } else if (viewMainActivity.getId() == R.id.registerTVMainActivity) {
            validateRegisterTVMainActivity();
        }
    }
}
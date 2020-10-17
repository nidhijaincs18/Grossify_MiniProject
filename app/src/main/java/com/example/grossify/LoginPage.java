package com.example.grossify;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class LoginPage extends AppCompatActivity {

    TextView doNotHaveAccount, signUpLink, logoText, sloganText;
    Button logIn;
    TextInputLayout phone, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login_page);

        //hooks
        logoText = findViewById(R.id.welcome_log_into);
        sloganText = findViewById(R.id.login_slogan);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        logIn = findViewById(R.id.login_btn);
        doNotHaveAccount = findViewById(R.id.sign_up);
        signUpLink = findViewById(R.id.sign_up_link);


        signUpLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WelcomeSignUpPage.class);
                startActivity(intent);
            }
        });
    }
}

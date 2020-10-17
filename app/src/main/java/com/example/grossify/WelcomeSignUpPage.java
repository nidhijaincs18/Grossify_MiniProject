package com.example.grossify;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WelcomeSignUpPage extends AppCompatActivity {
    //Variables
    TextInputLayout name, email, phone, password;
    Button signUpBtn, loginBtn;

    FirebaseDatabase rootNode;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.welcome_sign_up_page);

        name = findViewById(R.id.reg_name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        signUpBtn = findViewById(R.id.signup_btn);
        loginBtn = findViewById(R.id.login_btn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginPage.class);
                startActivity(intent);
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");
                if (!validateName() | !validateEmail() | !validatePhone() | !validatePassword()) {
                    return;
                }

                //Get the values

                String regName = name.getEditText().getText().toString();
                String regEmail = email.getEditText().getText().toString();
                String regPhone = phone.getEditText().getText().toString();
                String regPassword = password.getEditText().getText().toString();

                UserHelperClass helperClass = new UserHelperClass(regName, regEmail, regPhone, regPassword);


                reference.child(regPhone).setValue(helperClass);
            }
        });
    }

    private boolean validateName() {
        String value = name.getEditText().getText().toString();
        if (value.isEmpty()) {
            name.setError("Field cannot be empty");
            return false;
        } else {
            name.setError(null);
            name.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateEmail() {
        String value = email.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (value.isEmpty()) {
            email.setError("Field cannot be empty");
            return false;
        } else if (!value.matches(emailPattern)) {
            email.setError("Invalid Email Address");
            return false;
        } else {
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePhone() {
        String value = phone.getEditText().getText().toString();

        if (value.isEmpty()) {
            phone.setError("Field cannot be empty");
            return false;
        } else {
            phone.setError(null);
            phone.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePassword() {
        String value = password.getEditText().getText().toString();
        String passwordValidation = "^" +
                "(?=.*[0-9])" +         //at least 1 digit
                "(?=.*[a-z])" +         //at least 1 lower case letter
                "(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                ".{4,}" +               //at least 4 characters
                "$";
        if (value.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        } else if (!value.matches(passwordValidation)) {
            password.setError("Password is too weak");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }
}

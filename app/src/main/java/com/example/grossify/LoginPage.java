package com.example.grossify;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

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

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), VerifyPhoneNumber.class);
                startActivity(intent);
            }
        });


        signUpLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WelcomeSignUpPage.class);
                startActivity(intent);
            }
        });
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

        if (value.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    private void isUser() {
        final String userEnteredPhone = phone.getEditText().getText().toString().trim();
        final String userEnteredPassword = password.getEditText().getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkPassword = reference.orderByChild("phoneNo").equalTo(userEnteredPhone);
        checkPassword.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                phone.setError(null);
                phone.setErrorEnabled(false);

                if (dataSnapshot.exists()) {
                    String passwordFromDB = dataSnapshot.child(userEnteredPhone).child("password").getValue(String.class);
                    if (passwordFromDB.equals(userEnteredPassword)) {
                        phone.setError(null);
                        phone.setErrorEnabled(false);
                        String nameFromDB = dataSnapshot.child(userEnteredPhone).child("name").getValue(String.class);
                        String emailFromDB = dataSnapshot.child(userEnteredPhone).child("email").getValue(String.class);
                        String phoneFromDB = dataSnapshot.child(userEnteredPhone).child("phoneNo").getValue(String.class);

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                        intent.putExtra("name", nameFromDB);
                        intent.putExtra("email", emailFromDB);
                        intent.putExtra("phone", phoneFromDB);
                        intent.putExtra("password", passwordFromDB);

                        startActivity(intent);

                    } else {
                        password.setError("Wrong Password");
                        password.requestFocus();
                    }
                } else {
                    phone.setError("No such User exists");
                    phone.requestFocus();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void loginUser(View view) {
        if (!validatePhone() | !validatePassword()) {
            return;

        } else {
            isUser();

        }
    }


}

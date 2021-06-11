package com.example.animeworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static android.text.TextUtils.isEmpty;

public class Register extends AppCompatActivity {

    private EditText signup_email, signup_pass, signup_confirmpass;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        signup_email = findViewById(R.id.signup_email_id);
        signup_pass = findViewById(R.id.signup_pass_id);
        signup_confirmpass = findViewById(R.id.signup_repass_id);
    }

    public void register(View view) {
        String email = signup_email.getText().toString().trim();
        String pass = signup_pass.getText().toString().trim();
        String confirm_pass = signup_confirmpass.getText().toString().trim();

        if (isEmpty(email)) {
            Toast t = Toast.makeText(this, "Enter your email Address", Toast.LENGTH_SHORT);
            t.show();
        }

        else if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {
            Toast t = Toast.makeText(this, "Invalid Email Address", Toast.LENGTH_SHORT);
            t.show();
        }

        else if (isEmpty(pass)) {
            Toast t = Toast.makeText(this, "You must enter Password", Toast.LENGTH_SHORT);
            t.show();
        }
        else if (pass.length()<6){
            Toast.makeText(this, "Password is too weak", Toast.LENGTH_SHORT).show();
            return;
        }

        else if(!pass.equals(confirm_pass)){
            Toast.makeText(this,"Password Not matching",Toast.LENGTH_SHORT).show();
        }

        else {
            createAccount(email,pass);
        }
    }

    private void createAccount(String email, String pass) {
        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(Register.this,Login.class));
                            Toast.makeText(Register.this, "Successfully registered", Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Register.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void login(View view) {
        startActivity(new Intent(Register.this,Login.class));
    }
}

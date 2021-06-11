package com.example.animeworld;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText login_email, login_pass;
    TextView forgotTextLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        forgotTextLink = findViewById(R.id.forgotPassword);
        login_email = findViewById(R.id.login_email_id);
        login_pass = findViewById(R.id.login_pass_id);
        forgotTextLink = findViewById(R.id.forgotPassword);

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
            forgotTextLink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final EditText resetMail = new EditText(v.getContext());
                    final AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                    passwordResetDialog.setTitle("Reset Password ?");
                    passwordResetDialog.setMessage("Enter Your Email To Received Reset Link.");
                    passwordResetDialog.setView(resetMail);



                    passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // extract the email and send reset link
                            String mail = resetMail.getText().toString();

                            mAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(Login.this, "Reset Link Sent To Your Email.", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Login.this, "Error ! Reset Link is Not Sent" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    });

                    passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // close the dialog
                        }
                    });

                    passwordResetDialog.create().show();

                }
            });

    }

    public void login(View view) {
        String email = login_email.getText().toString().trim();
        String pass = login_pass.getText().toString().trim();

        if (email.isEmpty()){
            Toast.makeText(this, "Enter your email Address", Toast.LENGTH_SHORT).show();
            return;
        }

        else if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {
            Toast t = Toast.makeText(this, "Invalid Email Address", Toast.LENGTH_SHORT);
            t.show();
        }

        else if (pass.isEmpty()){
            Toast.makeText(this, "Enter your Password", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            userLogin(email,pass);
        }
    }

    private void userLogin(String email, String pass) {
        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(Login.this,MainActivity.class));
                            Toast.makeText(Login.this, "Login successfully", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(Login.this, "Login failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    public void register(View view) {
        startActivity(new Intent(Login.this,Register.class));
    }
}


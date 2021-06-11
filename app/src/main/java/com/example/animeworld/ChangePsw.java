package com.example.animeworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePsw extends AppCompatActivity {
    private EditText newPsw;
    FirebaseAuth auth;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepsw);

        dialog = new ProgressDialog(this);
        newPsw = findViewById(R.id.newPsw);
        auth = FirebaseAuth.getInstance();
    }

    public void change(View view) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            dialog.setMessage("Changing password, please wait!!!");
            dialog.show();
            user.updatePassword(newPsw.getText().toString()).
                    addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                dialog.dismiss();
                                Toast.makeText(ChangePsw.this, "Your passord is changed successfully", Toast.LENGTH_SHORT).show();
                                auth.signOut();
                                Intent i = new Intent(ChangePsw.this,Login.class);
                                startActivity(i);
                            }
                            else{
                                dialog.dismiss();
                                Toast.makeText(ChangePsw.this, "Password cannot be changed at the moment", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}

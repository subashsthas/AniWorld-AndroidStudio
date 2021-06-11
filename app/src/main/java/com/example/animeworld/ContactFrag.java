package com.example.animeworld;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ContactFrag extends Fragment {

    private EditText contact_email, contact_subject, contact_message;
    private Button botton;
    private DatabaseReference dbRef;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.frag_contact, container, false);
        contact_email = v.findViewById(R.id.contact_email);
        contact_subject = v.findViewById(R.id.contatc_subject);
        contact_message = v.findViewById(R.id.contact_msg);
        botton = v.findViewById(R.id.contact_btn);

        dbRef = FirebaseDatabase.getInstance().getReference("Contact");


        botton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = contact_email.getText().toString();
                String subject = contact_subject.getText().toString();
                String message = contact_message.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getActivity(), "Email Field is Empty", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(subject)) {
                    Toast.makeText(getActivity(), "Subject Field is Empty", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(message)) {
                    Toast.makeText(getActivity(), "Message Field is Empty", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    String id = dbRef.push().getKey();
                    ContactPerson messageObj = new ContactPerson(id, email, subject, message);
                    dbRef.child(id).setValue(messageObj);
                    startActivity(new Intent(getActivity(), MainActivity.class));
                    Toast.makeText(getActivity(), "Thank you for your message", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return v;
    }

}

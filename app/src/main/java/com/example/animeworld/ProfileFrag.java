package com.example.animeworld;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileFrag extends Fragment {
    private Button changepsw,deactivate,logout,rating;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_profile, container, false);

        changepsw =v.findViewById(R.id.button_changepsw);
        deactivate =v.findViewById(R.id.button_deactivate);
        logout =v.findViewById(R.id.button_logout);
        rating = v.findViewById(R.id.rating_id);


        rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Rating.class);
                startActivity(i);
            }
        });

        changepsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ChangePsw.class));
            }
        });
        deactivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
                if(user != null){
                    user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getActivity(), "Account deactivated", Toast.LENGTH_SHORT).show();
                                startActivity( new Intent(getActivity(),Login.class));

                            }
                            else{
                                Toast.makeText(getActivity(), "Account cannot be deactivated at the time", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }


            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(getActivity(),Login.class);
                startActivity(i);
                Toast.makeText(getActivity(), "Logout Successfully", Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }
}

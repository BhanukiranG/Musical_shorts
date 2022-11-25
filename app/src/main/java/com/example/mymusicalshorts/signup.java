package com.example.mymusicalshorts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class signup extends AppCompatActivity {
    EditText username, email, password;
    Button signup;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    DatabaseReference mdbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        username = findViewById(R.id.usernamesignup);
        email = findViewById(R.id.emailsignup);
        password = findViewById(R.id.passwordsignup);
        signup = findViewById(R.id.submitsignup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performAuth();
            }
        });
    }

    private void performAuth() {
        String user = username.getText().toString();
        String emailid = email.getText().toString();
        String pass = password.getText().toString();
        mAuth.createUserWithEmailAndPassword(emailid, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent i = new Intent(signup.this, MainActivity.class);
                    String userName = username.getText().toString();
                    sample user = new sample(userName, mAuth.getUid().toString(), emailid, pass);
                    addusertoDataBase(user);
                    finish();
                    startActivity(i);
                    Toast.makeText(signup.this, "Registration successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(signup.this, "Registraion failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void addusertoDataBase(sample user) {
        mdbRef = FirebaseDatabase.getInstance().getReference("users");
        mdbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mdbRef.child(user.id).setValue(user);
//                Toast.makeText(signup.this,"uploaded",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
//                Log.v("<<<<.Error>>>>>>>>>","failed to upload");
                Toast.makeText(signup.this, "failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
package com.example.mymusicalshorts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    TextView signup;
    EditText email,password;
    Button signin;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();
        signup=findViewById(R.id.signupmain1);
        email=findViewById(R.id.usernamesignin);
        password=findViewById(R.id.passwordsignin);
        signin=findViewById(R.id.submitsignin);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,signup.class);
                startActivity(i);
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                perform();
            }
        });
    }

    private void perform() {
        String emaill=email.getText().toString();
        String passs=password.getText().toString();
        String adminemail="kiran@gmail.com";
        String adminpassword="123456";
        mAuth.signInWithEmailAndPassword(emaill,passs).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    if(emaill.equals(adminemail) && passs.equals(adminpassword)) {
                        Intent i = new Intent(MainActivity.this, upload.class);
                        startActivity(i);
                        Toast.makeText(MainActivity.this, "Signing bro", Toast.LENGTH_SHORT).show();
                    }else{
                        Intent i=new Intent(MainActivity.this,player.class);
                        startActivity(i);
                        Toast.makeText(MainActivity.this, "Signing", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
package com.project.uwishuttleapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

/*
The Activity_Login class is used to implement the functionality of the student_portal.xml layout.
This class is used to authenticate a user through logging in by using the authentication information stored in the database.
 */
public class Activity_Login extends AppCompatActivity {

    /*
        class variables
    */
    private EditText enterID;//used to capture username information
    private EditText enterPW;//used to capture password information
    private TextView v1;//to display name for login
    private TextView v2;//to display name for username
    private TextView v3;//to display name for password
    private Button logInButton;//used vor the login button
    private Button guestButton;//used for a guest login button
    private Button clear;//used to clear the username and password fields
    FirebaseAuth mFirebaseAuth;//this is the databse
    private FirebaseAuth.AuthStateListener mAuthState;//this variable is used to determine the current authentication state of the user

    /*
    upon creating this page the student portal screen is loaded along with all its functionality
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_portal);

        /*
        finding the appropriate ID's and linking them to the variables created for them.
        begin
       */
        v1 = (TextView) findViewById (R.id.textview2);
        v2 = (TextView) findViewById (R.id.textView4);
        v3 = (TextView) findViewById (R.id.textView5);
        logInButton = (Button) findViewById (R.id.studentLoginButton);
        guestButton = (Button) findViewById (R.id.studentGuestLoginButton);//create a guest button in xml
        EditText user = (EditText) findViewById(R.id.enterID);
        EditText pw = (EditText) findViewById(R.id.enterPW);
        clear = (Button) findViewById (R.id.clear);


        /*
        this is the listener that responds to when the Clear button is clicked.
         */
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Credentials cleared.....", Toast.LENGTH_SHORT).show();
                user.setText("");
                pw.setText("");
            }
        });


        mFirebaseAuth = FirebaseAuth.getInstance();

        /*
        this code is used to authenticate users with the information stored on the databse, upon logging in to the app
         */
        mAuthState = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if(mFirebaseUser != null){
                    Toast.makeText(getApplicationContext(), "Logging In...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Activity_Login.this, Activity_Route_Select.class);
                    String user = mFirebaseUser.toString();
                    intent.putExtra("user", user);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Please LogIn", Toast.LENGTH_SHORT).show();
                }
            }
        };


        /*
        this is the listener that responds to when the Login Button is clicked.
         */
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usr = user.getText().toString();
                user.setVisibility(View.VISIBLE);
                String p = pw.getText().toString();
                pw.setVisibility(View.VISIBLE);
                if(usr.isEmpty()){
                    user.setError("Please Enter id ");
                    user.requestFocus();
                }

                else if (pw.getText().equals("")) {
                        pw.setError("Please Enter id ");
                        pw.requestFocus();
                    }

                else if(!usr.isEmpty() && !pw.getText().equals("")) {
                    if(usr.equals("karsten.lange@my.uwi.edu") && p.equals("321456")){
                        Intent home = new Intent(Activity_Login.this, Activity_driver.class);
                        String user = null;
                        home.putExtra("user", usr);
                        startActivity(home);
                        finish();
                    }
                    else {
                        mFirebaseAuth.signInWithEmailAndPassword(usr, p).addOnCompleteListener(Activity_Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Error Occurred", Toast.LENGTH_SHORT).show();
                                } else {
                                    Intent home = new Intent(Activity_Login.this, Activity_Route_Select.class);
                                    String user = null;
                                    home.putExtra("user", usr);
                                    startActivity(home);
                                    finish();
                                }
                            }
                        });
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Error Occurred", Toast.LENGTH_SHORT).show();
                }



            }
        });

        /*
        this is the listener that responds to when the guest Button is clicked.
         */
        guestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Logging In...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Activity_Login.this,Activity_Route_Select.class);
               // finish();
                String user = null;
                intent.putExtra("user",user);
                startActivity(intent);

            }
        });

    }

    /*
    when the login is sucessful the authentication listener variable is set as the current state of authentication
     */
    @Override
    protected void onStart(){
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthState);
    }

}

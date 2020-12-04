package com.project.uwishuttleapp;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

/*
The MainActivity class is used to implement the functionality of the homepage.xml layout.
This class is used to display the home page for the app where a user can select if they are a student or driver.
this page is mainly  just for style.
 */
public class MainActivity extends AppCompatActivity {

    /*
    upon creating this page the homepage layout is loaded and all of its functionality.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        /*
        creating variables to be used and linking them to the appropriate id's for the textViews and image buttons on this page
         */
        TextView welcome = (TextView) findViewById (R.id.welcomeInfo);
        ImageButton student = (ImageButton) findViewById(R.id.student);
        ImageButton driver = (ImageButton) findViewById(R.id.driver);

        /*
        when this imageButton is selected the user will be redirected to the login screen
         */
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(MainActivity.this, Activity_Login.class);

                    startActivity(intent);

                }
                catch(Exception e)
                {
                    System.out.println(" THIS IS MY ERROR "+ e);
                }
            }
        });

        /*
        when this imageButton is selected the user will be redirected to the login screen
         */
        driver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(MainActivity.this, Activity_Login.class);

                    startActivity(intent);

                }
                catch(Exception e)
                {
                    System.out.println(" THIS IS MY ERROR "+ e);
                }

            }
        });


    }
}

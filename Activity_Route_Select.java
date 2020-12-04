package com.project.uwishuttleapp;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
The Activity_Route_Select class is used to implement the functionality of the routes.xml layout.
This class is used to display the information on all the routes.
 */
public class Activity_Route_Select extends AppCompatActivity {

    /*
       class variables
   */
    private TextView username;//variable  used to store name of user who is logged in
    private Button select1;//button variable used to select route 1
    private Button select2;//button variable used to select route 2
    private Button select3;//button variable used to select route 3
    private Button select4;//button variable used to select route 4
    private Button logout;//button variable used to select logout
    private Button myRes;//button variable used to select the my reservations page
    private Button h1;//button variable used to select return home


    /*
    upon creating this page the routes layout is loaded and all of its functionality.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.routes);

        /*
        finding the appropriate ID's and linking them to the variables created for them.
        begin
       */
        username=(TextView) findViewById(R.id.username);
        select1 = (Button) findViewById(R.id.select_button_1);
        select2 = (Button) findViewById(R.id.select_button_2);
        select3 = (Button) findViewById(R.id.select_button_3);
        select4 = (Button) findViewById(R.id.select_button_4);
        logout = (Button) findViewById(R.id.button);
        myRes = (Button) findViewById(R.id.myres);
        h1 = (Button) findViewById(R.id.home4);
        //end

        /*
        Extras: which are data pulled from previous screens
         */
        String data = getIntent().getExtras().getString("user", "Guest");
        username.setText(data);

        /*
        checks if driver is logged in and will move to driver page if he is
         */
        if(data.toString().equals("karsten.lange@my.uwi.edu")){

            Intent driver= new Intent(Activity_Route_Select.this,Activity_driver.class);
            String user = null;
            driver.putExtra("user", username.toString());
            startActivity(driver);
            finish();
        }else {

            /*
                this is the listener that responds to when the route 1 Button is clicked.
            */
            select1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Activity_Route_Select.this, Activity_Time_Select.class);
                    // finish();
                    String user = null;
                    intent.putExtra("user", data);
                    intent.putExtra("routeSelected", "route1");
                    startActivity(intent);

                }
            });

            /*
                this is the listener that responds to when the route 2 Button is clicked.
            */
            select2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Activity_Route_Select.this, Activity_Time_Select.class);
                    // finish();
                    String user = null;
                    intent.putExtra("user", data);
                    intent.putExtra("routeSelected", "route2");
                    startActivity(intent);

                }
            });

            /*
                this is the listener that responds to when the route 3 Button is clicked.
            */
            select3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Activity_Route_Select.this, Activity_Time_Select.class);
                    //finish();
                    String user = null;
                    intent.putExtra("user", data);
                    intent.putExtra("routeSelected", "route3");
                    startActivity(intent);

                }
            });

            /*
                this is the listener that responds to when the route 4 Button is clicked.
            */
            select4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Activity_Route_Select.this, Activity_Time_Select.class);
                    String user = null;
                    intent.putExtra("user", data);
                    intent.putExtra("routeSelected", "route4");
                    startActivity(intent);

                }
            });

            /*
                this is the listener that responds to when the my Reservations Button is clicked.
            */
            myRes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Activity_Route_Select.this, Activity_Reservations.class);
                    intent.putExtra("user", data);
                    startActivity(intent);
                }
            });

            /*
                this is the listener that responds to when the logout Button is clicked.
            */
            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //finish();
                    FirebaseAuth.getInstance().signOut();
                    Intent restart = new Intent(Activity_Route_Select.this, MainActivity.class);
                    startActivity(restart);

                }
            });

            /*
                this is the listener that responds to when the home Button is clicked.
            */
            h1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Activity_Route_Select.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }


}



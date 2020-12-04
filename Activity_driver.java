package com.project.uwishuttleapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.zxing.WriterException;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.Random;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
/*
The Activity_driver class is used to implement the functionality of the driver.xml and driver_scanner.xml layouts.
 */
public class Activity_driver extends AppCompatActivity {

    /*
        class variables
     */
    private TextView t1;//This text view displays the time information.
    private TextView capacityView;//text view for naming of Total Capacity.
    private TextView remainingView;//text view for naming of naming of Remaining Seats.
    private TextView capacity;// this displays the total capacity of the driver's shuttle.
    private TextView remaining;//this displays the remaining number of seats available in the shuttle.
    private TextView username;//this textview is for displaying the username of the driver currently logged in.
    private Button scanBtn;//this is a button used to access the scanner page which the driver uses to scan tickets
    private TextView type;//this is the type text view that displays the type of user that is logged in at the momment.
    private Button logout;//this is the logout button that users can use to log out of the app.

    /*
    When this class is created it loads all the buttons and text views to the driver interface.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver);//setting the layout that will be loaded for the driver home page screen


        /*
        finding the appropriate ID's and linking them to the variables created for them.
        begin
         */
        username = (TextView) findViewById(R.id.username7);
        logout = (Button) findViewById(R.id.logout3);
        type = (TextView) findViewById(R.id.usertype);
        t1 = (TextView) findViewById (R.id.time);
        capacityView = (TextView) findViewById (R.id.busCap);
        remainingView = (TextView) findViewById (R.id.remainSeats);
        capacity = (TextView) findViewById (R.id.cap);
        remaining = (TextView) findViewById (R.id.rem);
        scanBtn = findViewById(R.id.scanBtn);
        //end

        /*
        this string was created so the information passed from the previous screen could be stored and used on this screen.
         */
        String data = getIntent().getExtras().getString("user","Guest");

        /*
        assigning values to the type, username, and t1 variables.
         */
        //begin
        type.setText("Driver");
        username.setText(data);
        if(username.toString().equals("karsten.lange@my.uwi.edu")){
            type.setText("Driver");
        }
        String currentDateTimeString = java.text.DateFormat.getDateTimeInstance().format(new Date());
        t1.setText(currentDateTimeString);
        //end


        /*
        this is the listener that responds to when the scan Button is clicked.
         */
        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Driver_Scan.class));
            }
        });

        /*
        this is the listener that responds to when the logout Button is clicked.
         */
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),Activity_Login.class));
                finish();
            }
        });

    }
}



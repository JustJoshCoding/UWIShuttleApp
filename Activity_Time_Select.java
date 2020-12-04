package com.project.uwishuttleapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/*
The Activity_Time_Select class is used to implement the functionality of the time_select.xml layout.
This class is used to display the information on all the times available per route.
 */
public class Activity_Time_Select extends AppCompatActivity{

    /*
       class variables
   */
    private TextView username;//textView  used to store name of user who is logged in
    private TextView weekday;//textView used to display the day of the week
    private TextView selected;//textView used to display the time that was selected
    private Button confirm;//the confirm button
    private Button reserves;//the passengers button
    private TextView reserveList;//textView that displays the reserve list
    private TextView routeView;//textView that displays the route that was selected previously
    private Button h1;//the home button

    /*
    upon creating this page the time_select layout is loaded and all of its functionality.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_select);

        /*
        finding the appropriate ID's and linking them to the variables created for them.
        begin
       */
        confirm=(Button) findViewById(R.id.route_confirmation);
        selected =(TextView) findViewById(R.id.selected);
        reserveList=(TextView) findViewById(R.id.list);
        reserveList.setVisibility(View.INVISIBLE);
        username=(TextView) findViewById(R.id.user);
        weekday = (TextView) findViewById(R.id.textView3);
        routeView = (TextView) findViewById(R.id.routeView);
        h1 = (Button) findViewById(R.id.home2);
        reserves = (Button) findViewById(R.id.reservations);
        //end

        /*
        Extras: which are data pulled from previous screens
         */
        String route = getIntent().getExtras().getString("routeSelected");
        String data = getIntent().getExtras().getString("user","Guest");

        /*
        setting values to some of the textViews
         */
        String weekDay;
        SimpleDateFormat dayFormat;
        dayFormat = new SimpleDateFormat("EEEE", Locale.US);
        Calendar calendar = Calendar.getInstance();
        weekDay = dayFormat.format(calendar.getTime());
        weekday.setText(weekDay);
        username.setText(data);

        /*
        setting the display text for the route that was selected
         */
        if(route.equals("route1"))
            routeView.setText("TGR Carpark - JFK Underpass");
        else if(route.equals("route2"))
            routeView.setText("DCFA/Optometry/St. John Rd.");
        else if(route.equals("route3"))
            routeView.setText("TMt. Hope/San Juan Circuit");
        else if(route.equals("route4"))
            routeView.setText("Sir Arthur Lewis Hall of Residence ");
        /*
        this is the drop down list for the times to be selected
         */
        Spinner spinner = findViewById(R.id.spinner);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("8:00 a.m. - 9:00 a.m.");
        arrayList.add("9:00 a.m. - 10:00 a.m.");
        arrayList.add("10:00 a.m. - 11:00 a.m.");
        arrayList.add("11:00 a.m. - 12:00 p.m.");
        arrayList.add("12:00 p.m. - 1:00 p.m.");
        arrayList.add("1:00 p.m. - 2:00 p.m.");
        arrayList.add("2:00 p.m. - 3:00 p.m.");
        arrayList.add("3:00 p.m. - 4:00 p.m.");
        final int[] pos = new int[1];
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        /*
        this is the code to implement functionality for the spinnner
         */
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tutorialsName = parent.getItemAtPosition(position).toString();
               if(reserveList.getText().equals("Apoorv, Jacob, James, John"))
                   reserveList.setText("Joseph");
              else  if(reserveList.getText().equals("Joseph"))
                   reserveList.setText("Gerald, Dwayne, Devindra");
              else if(reserveList.getText().equals("Gerald, Dwayne, Devindra"))
                   reserveList.setText("Harold");
              else  reserveList.setText("No reservations made");
                Toast.makeText(parent.getContext(), "Selected: " + tutorialsName,          Toast.LENGTH_LONG).show();
                selected.setText(tutorialsName);
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
                selected.setText("Nothing Selected");
            }
        });

         /*
            this is the listener that responds to when the cofirm Button is clicked.
         */
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent generate= new Intent(Activity_Time_Select.this, Activity_Generate.class);
                finish();
                String user = null;
                generate.putExtra("user",data);
                generate.putExtra("routeSelected", route);
                generate.putExtra("timeSelected", spinner.getSelectedItem().toString());
                startActivity(generate);

            }
        });

        /*
            this is the listener that responds to when the passengers Button is clicked.
         */
        reserves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          //code here
                reserveList.setVisibility(View.VISIBLE);
                reserveList.setText("Apoorv, Jacob, James, John");
            }
        });

        /*
            this is the listener that responds to when the home Button is clicked.
         */
        h1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Time_Select.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

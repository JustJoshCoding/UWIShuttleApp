package com.project.uwishuttleapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Date;

/*
The Activity_Reservations class is used to implement the functionality of the myreservations.xml layout.
This class is used to pull all the valid tickets belonging to the current user from the online databse and display them on this screen.
 */
public class Activity_Reservations extends AppCompatActivity {

    /*
        class variables
    */
    private Button logout;//this varibale is used the logout button
    private Button h1;//this is used for the home button
    private TextView allTickets;//this is used for the displaying of all the tickets pulled fro mthe database.
    private TextView t1;//this varibale is used for the naming of the My Tickets heading
    private static final String TAG = "TicketGenerate";//this variable is used for error checking information so we know which instance throws the error.
    FirebaseFirestore db = FirebaseFirestore.getInstance();//this is used to get the instance of the database.

    /*
    upon creating this page the myresrvations layout is loaded and all of its functionality.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myreservations);

        /*
        finding the appropriate ID's and linking them to the variables created for them.
        begin
       */
        logout = (Button) findViewById(R.id.logout);
        allTickets = (TextView) findViewById(R.id.tickets);
        t1 = (TextView) findViewById(R.id.resText);
        h1 = (Button) findViewById(R.id.home3);

        /*
        Extras: which are data pulled from previous screens
         */
        String username = getIntent().getExtras().getString("user", "Guest");

        /*
        this code is used to pull the ticeket data stored for the user that is currently logged in and display it to the screen
         */
        db.collection("Tickets")
                .whereEqualTo("User", username)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                allTickets.setText(document.getData().toString());
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

        /*
        this is the listener that responds to when the logout Button is clicked.
         */
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(Activity_Reservations.this, Activity_Login.class);
                startActivity(intent);
                finish();
            }
        });

        /*
        this is the listener that responds to when the home Button is clicked.
         */
        h1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Reservations.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

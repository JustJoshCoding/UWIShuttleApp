package com.project.uwishuttleapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.nfc.Tag;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.quickstart.database.databinding.ActivityNewPostBinding;
//import com.google.firebase.quickstart.database.java.models.Post;
//import com.google.firebase.quickstart.database.java.models.User;
import com.google.firebase.database.Query;
import com.google.firebase.firestore.SetOptions;
import com.google.zxing.WriterException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

/*
The Activity_Generate class is used to implement the functionality of the qr_view.xml layout.
This class is used to generate and store the tickets that are generated by the QR code generator to Google Firebase.
 */
public class Activity_Generate extends AppCompatActivity {
   /*
        class variables
    */
   private EditText qrvalue;//this variable is used to enter the value that needs to be generated for the QR.
   private ImageView qrImage;//this variable is used to display the QR that is generated.
   private Random random;//this is variable is for storing the random variable generated by the rand() function.
   private Button h1;//this variable is used for the home button displayed at the buttom of the qr_view layout.
   private static final String TAG = "TicketGenerate";//this variable is used for error checking information so we know which instance throws the error.
   FirebaseFirestore db = FirebaseFirestore.getInstance();//this is used to get the instance of the database.

   /*
   When this class is created it loads all the buttons and text views to the qr_view interface.
    */
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.qr_view);

      /*
        finding the appropriate ID's and linking them to the variables created for them.
        begin
       */
      qrImage = findViewById(R.id.qrPlaceHolder);
      h1 = (Button) findViewById(R.id.home);
      //end

      /*
      Extras: which are data pulled from previous screens
       */
      String route = getIntent().getExtras().getString("routeSelected");
      String username = getIntent().getExtras().getString("user", "Guest");
      String time = getIntent().getExtras().getString("timeSelected");


      random = new Random();//used to genrerate random variable
      Integer rand = new Integer(random.nextInt());

      /*
      this hashmap is used to save the data for a ticket
       */
      Map<String, Object> ticket = new HashMap<>();
      ticket.put("User", username);
      ticket.put("Route Selected", route);
      ticket.put("Route Selected", time);
      ticket.put("Ticket Number", rand.toString());

      /*
      this secction of the code is used to store the ticket data to the online database.
       */
      db.collection("Tickets")
              .add(ticket)
              .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                 @Override
                 public void onSuccess(DocumentReference documentReference) {
                    Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                 }
              })
              .addOnFailureListener(new OnFailureListener() {
                 @Override
                 public void onFailure(@NonNull Exception e) {
                    Log.w(TAG, "Error adding document", e);
                 }
              });

      String data = String.valueOf(rand).getClass().getName();

      /*
      this section is used to generate the QR image and display it to the user
       */
      if(data.isEmpty()){
         qrvalue.setError("Value Required.");
      }else {
         QRGEncoder qrgEncoder = new QRGEncoder(data,null, QRGContents.Type.TEXT,500);
         try {
            Bitmap qrBits = qrgEncoder.encodeAsBitmap();
            qrImage.setImageBitmap(qrBits);
         } catch (WriterException e) {
            e.printStackTrace();
         }
      }

      /*
      this is the listener that responds to when the home Button is clicked.
       */
      h1.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            Intent intent = new Intent(Activity_Generate.this, MainActivity.class);
            startActivity(intent);
            finish();
         }
      });


   }
}
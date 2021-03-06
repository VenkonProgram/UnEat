package com.example.fernandalopezcardenas.uneatfinal.Restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.fernandalopezcardenas.uneatfinal.Detail.DetailPlate;
import com.example.fernandalopezcardenas.uneatfinal.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;

public class CreatePlateActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    public EditText namePlate, price, ingredients, urlImagePlate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_plate);

        myRef = database.getReference("").child("users");

        namePlate = findViewById(R.id.namePlate);
        price = findViewById(R.id.pricePlate);
        ingredients = findViewById(R.id.ingredients);
        urlImagePlate = findViewById(R.id.imagePlate);

        final String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        final String userre = database.getReference("users").child("restaurant").child(userId).child("plates").push().getKey();
        Button map = findViewById(R.id.buttonCreate);
        map.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                ArrayList<String> ingredientsList = new ArrayList<>(Arrays.asList(ingredients.getText().toString().split(",")));
                writeNewPlate(namePlate.getText().toString(),Integer.parseInt(price.getText().toString()), userre, ingredientsList, urlImagePlate.getText().toString());
                Intent Client= new Intent(CreatePlateActivity.this, MainRestaurantActivity.class);
                startActivity(Client);
                finish();
            }
        });
    }
    private void writeNewPlate(String Name, int Price, String uidrequest, ArrayList<String> Ingredients, String ImagePlate) {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DetailPlate user = new DetailPlate(Name, Price, uidrequest, userId, Ingredients, ImagePlate);
        myRef = database.getReference("");
        myRef.child("users").child("restaurant").child(userId).child("plates").child(uidrequest).setValue(user);
    }
}

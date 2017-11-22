package com.example.fernandalopezcardenas.uneatfinal.Client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.fernandalopezcardenas.uneatfinal.Detail.DetailPlate;
import com.example.fernandalopezcardenas.uneatfinal.Detail.DetailRestaurant;
import com.example.fernandalopezcardenas.uneatfinal.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PlateActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    private ListView listView;
    private DetailRestaurant restaurant;
    private ArrayAdapter<DetailPlate> areasAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plate);
        listView=(ListView) findViewById(R.id.listview);

        restaurant = (DetailRestaurant)getIntent().getSerializableExtra("place");

        areasAdapter = new ArrayAdapter<DetailPlate>(PlateActivity.this,android.R.layout.simple_expandable_list_item_1, restaurant.getPlatesList());
        listView.setAdapter(areasAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long arg) {
                Intent intent = new Intent(PlateActivity.this, CarActivity.class);
                intent.putExtra("place",areasAdapter.getItem(position));
                intent.putExtra("res",restaurant);
                startActivity(intent);
            }
        });
    }
}
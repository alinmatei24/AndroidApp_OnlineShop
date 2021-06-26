package com.example.myonlineshopp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class History extends AppCompatActivity {
    List<String> history=new ArrayList<>();
    ArrayAdapter<String> adapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        String curentUser=getIntent().getStringExtra("CurentUser");

        String[]commands=Client.getCommands(curentUser);
        for(int i=0;i<commands.length;i++){
            Random r = new Random();
            int low = 10;
            int high = 100;
            int result = r.nextInt(high-low) + low;
           // System.out.println(i+"-"+commands[i]);
            history.add("Id Comanda:" + result );
            history.add("Produse: "+commands[i]);
        }

        adapter=new ArrayAdapter<String>(this, R.layout.textcente1r, R.id.textHistory, history);
        listView=(ListView) findViewById(R.id.lisaComenzi);
        listView.setAdapter(adapter);

    }

    public void getBack(View view) {
        finish();
    }
}
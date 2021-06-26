package com.example.myonlineshopp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ProductPage extends AppCompatActivity {
    private Product productChosen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_page);


        setText();
    }

    private void setText(){
        String nameOfChosenProduct=getIntent().getStringExtra("chosenProduct");
        productChosen=Client.getProduct(nameOfChosenProduct);

        TextView name=(TextView) findViewById(R.id.name);
        TextView description=(TextView) findViewById(R.id.description);
        TextView stoc=(TextView) findViewById(R.id.stoc);
        TextView price=(TextView) findViewById(R.id.price);

        name.setText(productChosen.getName());
        description.setText(productChosen.getDescription());
        stoc.setText("Stoc: " + productChosen.getStoc());
        price.setText("$" + productChosen.getPrice());
    }

    public void addToChartButton(View view) {
        app.chart.add(productChosen);
    }

    public void back(View view) {
            this.finish();
    }
}
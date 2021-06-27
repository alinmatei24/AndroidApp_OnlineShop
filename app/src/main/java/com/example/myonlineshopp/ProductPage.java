package com.example.myonlineshopp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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
        ImageView img=(ImageView) findViewById(R.id.productImage);

        String produsName= productChosen.getName();
                switch(produsName) {
                    case "Laptop-Hp" :
                    {
                        img.setImageResource(R.drawable.laptop);
                    }
                    break;
                    case "Smartphone-A32" :
                    {
                        img.setImageResource(R.drawable.smartphone);
                    }
                    break;
                    case "Televizor-Led" :
                    {
                        img.setImageResource(R.drawable.tv);
                    }
                    break;
                    case "Monitor-Led" :
                    {
                        img.setImageResource(R.drawable.monitor);
                    }
                    break;
                    case "Xerox-Hp" :
                    {
                        img.setImageResource(R.drawable.xerox);
                    }
                    break;
                    case "Router-Wireless" :
                    {
                        img.setImageResource(R.drawable.router);
                    }
                    break;
                    case "Casti-Energy" :
                    {
                        img.setImageResource(R.drawable.casti);
                    }
                    break;
                    case "Mouse-Redragon" :
                    {
                        img.setImageResource(R.drawable.mouse);
                    }
                    break;
                    case "Mouse-pad" :
                    {
                        img.setImageResource(R.drawable.mousepad);
                    }
                    break;
                    case "Tastatura-Logitech" :
                    {
                        img.setImageResource(R.drawable.tastatura);
                    }
                    break;
                    default:
                    {
                        img.setImageResource(R.drawable.product);
                    }
                }

        name.setText(productChosen.getName());
        description.setText(productChosen.getDescription());
        stoc.setText("Stoc: " + productChosen.getStoc());
        price.setText("$" + productChosen.getPrice());
    }

    public void addToCartButton(View view) {
        app.chart.add(productChosen);
    }

    public void back(View view) {
            this.finish();
    }
}
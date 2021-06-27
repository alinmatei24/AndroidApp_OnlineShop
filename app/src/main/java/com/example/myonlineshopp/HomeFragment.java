package com.example.myonlineshopp;



import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    ListView myListView;
    ArrayList<RowItem> myRowItems;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container,false);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //display products
        myRowItems = new ArrayList<RowItem>();

        myListView = (ListView) getView().findViewById(R.id.homeList);


        try {
            getProducts();
            for(RowItem item : myRowItems){
                System.out.println(item.getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        CustomAdapter myAdapter = new CustomAdapter(getActivity().getApplicationContext(), myRowItems);
        myListView.setAdapter(myAdapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                RowItem list_row = myRowItems.get(position);

                Intent intent = new Intent(getActivity(), ProductPage.class);
                intent.putExtra("chosenProduct", list_row.getName());
                startActivity(intent);
            }

        });


    }

    private void getProducts() throws InterruptedException {
        String produs="product";
        ArrayList<Product> myList=Client.getProducts();
        for(Product produse : myList){
            RowItem nou=new RowItem();
            nou.setName(produse.getName());
            nou.setPrice(produse.getPrice());
            produs=produse.getName();
            switch(produs) {
                case "Laptop-Hp" :
                    {
                        nou.setImg(R.drawable.laptop);
                }
                break;
                case "Smartphone-A32" :
                {
                    nou.setImg(R.drawable.smartphone);
                }
                break;
                case "Televizor-Led" :
                {
                    nou.setImg(R.drawable.tv);
                }
                break;
                case "Monitor-Led" :
                {
                    nou.setImg(R.drawable.monitor);
                }
                break;
                case "Xerox-Hp" :
                {
                    nou.setImg(R.drawable.xerox);
                }
                break;
                case "Router-Wireless" :
                {
                    nou.setImg(R.drawable.router);
                }
                break;
                case "Casti-Energy" :
                {
                    nou.setImg(R.drawable.casti);
                }
                break;
                case "Mouse-Redragon" :
                {
                    nou.setImg(R.drawable.mouse);
                }
                break;
                case "Mouse-pad" :
                {
                    nou.setImg(R.drawable.mousepad);
                }
                break;
                case "Tastatura-Logitech" :
                {
                    nou.setImg(R.drawable.tastatura);
                }
                break;
                default:
                {
                    nou.setImg(R.drawable.product);
                }
            }
            myRowItems.add(nou);
        }


    }
}

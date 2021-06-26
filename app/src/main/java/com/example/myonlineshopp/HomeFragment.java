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
        String a="product";
        ArrayList<Product> myList=Client.getProducts();
        for(int i=0;i<myList.size();i++){
            RowItem nou=new RowItem();
            nou.setName(myList.get(i).getName());
            nou.setPrice(myList.get(i).getPrice());
            nou.setImg(R.drawable.product);
            myRowItems.add(nou);
        }
    }
}

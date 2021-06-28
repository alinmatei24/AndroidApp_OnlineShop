package com.example.myonlineshopp;



import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartFragment extends Fragment {
    ListView myListView;
    ArrayList<RowItem> myRowItems;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //display products
        myRowItems = new ArrayList<RowItem>();

        myListView = (ListView) getView().findViewById(R.id.chartList);


        try {
            getProducts();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        CustomAdapter myAdapter = new CustomAdapter(getActivity().getApplicationContext(), myRowItems);
        myListView.setAdapter(myAdapter);

        Button golireCos=(Button) getView().findViewById(R.id.golireCos);
        Button veziIstoric=(Button) getView().findViewById(R.id.veziIstoric);
        Button adaugaComando=(Button) getView().findViewById(R.id.adaugaComanda);
        golireCos.setOnClickListener(this::onClickGolire);
        veziIstoric.setOnClickListener(this::onClickVeziIstoric);
        adaugaComando.setOnClickListener(this::onClickAdaugare);

    }
    public void onClickGolire(final View v){

        app.chart.clear();
        System.out.println("Cos golit");

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        if (Build.VERSION.SDK_INT >= 26) {
            ft.setReorderingAllowed(false);
        }
        ft.detach(this).attach(this).commit();
    }
    public void onClickAdaugare(final View v){
        if(app.chart.isEmpty()){
            System.out.println("Cart gol");
            return;
        }
        String curentUser=getActivity().getIntent().getStringExtra("User");
        String products="";
        for(int i=0;i<app.chart.size();i++){
            products+=app.chart.get(i).getName()+",";
        }
        System.out.println("Adaugare comanda");
        Client.addComanda(curentUser,products);
        app.chart.clear();

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        if (Build.VERSION.SDK_INT >= 26) {
            ft.setReorderingAllowed(false);
        }
        ft.detach(this).attach(this).commit();

    }
    public void onClickVeziIstoric(final View v){
        System.out.println("Vezi istoric");
        String curentUser=getActivity().getIntent().getStringExtra("User");
        Intent intent = new Intent(this.getActivity(), History.class);
        intent.putExtra("CurentUser", curentUser);
        startActivity(intent);

    }
    public static String fmt(float d)
    {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String numberAsString = decimalFormat.format(d);
        return  numberAsString;
    }

    private void getProducts() throws InterruptedException {
        ArrayList<Product> myList=app.chart;
        ImageView img=(ImageView) getActivity().findViewById(R.id.emptyCart);
        float pretTotal=0;
        if(myList.size()==0){
            img.refreshDrawableState();
        }else{
            img.setVisibility(View.GONE);
            for(int i=0;i<myList.size();i++){
                RowItem nou=new RowItem();
                nou.setName(myList.get(i).getName());
                nou.setPrice(myList.get(i).getPrice());
                String produs=myList.get(i).getName();
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
                pretTotal+=Float.parseFloat(myList.get(i).getPrice());
            }
        }
        //set price in page
        TextView price=(TextView) getView().findViewById(R.id.totalPrice);

        String finalPrice=fmt(pretTotal);
        price.setText("Pret Total: $" + finalPrice);

    }
}

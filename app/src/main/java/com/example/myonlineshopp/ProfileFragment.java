package com.example.myonlineshopp;



import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container,false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setText();
    }
    public void setText(){

        String user=getActivity().getIntent().getStringExtra("User");
        User currentUser=Client.getUser(user);
        TextView name=(TextView) getView().findViewById(R.id.nameProfile);
        TextView email=(TextView) getView().findViewById(R.id.emailProfile);
        TextView age=(TextView) getView().findViewById(R.id.ageProfile);
        TextView address=(TextView) getView().findViewById(R.id.addressProfile);

        name.setText("Name: "+ currentUser.getName());
        email.setText("Email: "+ currentUser.getEmail());
        age.setText("BirthDate: "+ currentUser.getBirtDate());
        address.setText("Address: " + currentUser.getAddress());

        Button button = (Button) getView().findViewById(R.id.buttonUpdate);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), update.class);
                intent.putExtra("UserObject", currentUser);
                startActivity(intent);
            }
        });

    }
}

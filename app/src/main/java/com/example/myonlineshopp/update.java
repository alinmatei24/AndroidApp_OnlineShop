package com.example.myonlineshopp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class update extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        setText();
    }

    private void setText(){
        User curentUser=(User) getIntent().getSerializableExtra("UserObject");
        EditText name=(EditText) findViewById(R.id.nameText);
        EditText birthDate=(EditText) findViewById(R.id.BirthDate);
        EditText address=(EditText) findViewById(R.id.address);

        name.setText(curentUser.getName());
        birthDate.setText(curentUser.getBirtDate());
        address.setText(curentUser.getAddress());

    }

    public void Update(View view) {
        User currentUser=(User) getIntent().getSerializableExtra("UserObject");

        EditText name=(EditText) findViewById(R.id.nameText);
        EditText currentPassword=(EditText) findViewById(R.id.curentPassword);
        EditText newPassword1=(EditText) findViewById(R.id.newPassword1);
        EditText newPassword2=(EditText) findViewById(R.id.newPassword2);
        EditText birthDate=(EditText) findViewById(R.id.BirthDate);
        EditText address=(EditText) findViewById(R.id.address);

        String namee=name.getText().toString();
        String currentPasswordd=currentPassword.getText().toString();
        String newPassword11=newPassword1.getText().toString();
        String newPassword22=newPassword2.getText().toString();
        String birthDatee=birthDate.getText().toString();
        String addresss=address.getText().toString();

        if(namee.matches("") || currentPasswordd.matches("")|| newPassword11.matches("") || newPassword22.matches("") || birthDatee.matches("") || addresss.matches("")){
            Toast.makeText(this, "Y have empty fields ", Toast.LENGTH_SHORT).show();
        }else if(!currentPasswordd.matches(currentUser.getPassword())){
            System.out.println(currentUser.getPassword());
            Toast.makeText(this, "Password Incorrect", Toast.LENGTH_SHORT).show();
        }else if(!newPassword11.matches(newPassword22)){
            Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show();
        }else{
            Client.updateUsername(namee,currentUser.getEmail());
            Client.updatePassword(newPassword22,currentUser.getEmail());
            Client.updateAge(birthDatee,currentUser.getEmail());
            Client.updateAddress(addresss,currentUser.getEmail());
        }


    }

    public void back(View view) {
        finish();
    }
}
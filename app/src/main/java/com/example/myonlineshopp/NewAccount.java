package com.example.myonlineshopp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class NewAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);
    }

    public void createAccount(View view) throws InterruptedException, IOException {
        //check for fields to be filled up and check email
        EditText name=(EditText) findViewById(R.id.name);
        EditText email=(EditText) findViewById(R.id.email);
        EditText password=(EditText) findViewById(R.id.password);
        EditText dateOfBirth=(EditText) findViewById(R.id.age);
        EditText address=(EditText) findViewById(R.id.adrress);

        String namee=name.getText().toString();
        String emaill=email.getText().toString();
        String passwordd=password.getText().toString();
        String dateOfBirthh=dateOfBirth.getText().toString();
        String addresss=address.getText().toString();


        if(checkFields()){
            if(Client.createAccount(namee,emaill,passwordd,dateOfBirthh,addresss)) {
                Toast.makeText(this, "Account created!!", Toast.LENGTH_LONG).show();
                finish();
            }else{
                Toast.makeText(this, "There is already an account using this email addres", Toast.LENGTH_LONG).show();

            }
        }

    }

    private boolean checkFields(){
        EditText name=(EditText) findViewById(R.id.name);
        EditText email=(EditText) findViewById(R.id.email);
        EditText password=(EditText) findViewById(R.id.password);
        EditText dateOfBirth=(EditText) findViewById(R.id.age);
        EditText address=(EditText) findViewById(R.id.adrress);

        String namee=name.getText().toString();
        String emaill=email.getText().toString();
        String passwordd=password.getText().toString();
        String dateOfBirthh=dateOfBirth.getText().toString();
        String addresss=address.getText().toString();

        if (namee.matches("") || emaill.matches("") || passwordd.matches("") || dateOfBirthh.matches("") || addresss.matches("")) {
            Toast.makeText(this, "You have empty fields", Toast.LENGTH_SHORT).show();
            return false;
        }else if(!emaill.matches("^(.+)@(.+).(.+)$")){ //check email format
            Toast.makeText(this, "You did not enter a valid email address format", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
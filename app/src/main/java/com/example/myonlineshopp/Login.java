package com.example.myonlineshopp;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Intent intent = new Intent(this, app.class);

        //startActivity(intent);

    }

    public void createAccount(View view) {
            Intent intent = new Intent(this, NewAccount.class);
            startActivity(intent);
    }

    public void login(View view) throws IOException, ClassNotFoundException, InterruptedException {
        if(checkFields()) {
            EditText email = (EditText) findViewById(R.id.email);
            EditText password=(EditText) findViewById(R.id.password);
            String emaill = email.getText().toString();
            String passwordd = password.getText().toString();
                if (Client.checkLogIn(emaill,passwordd)) {
                    Intent intent = new Intent(this, app.class);
                    intent.putExtra("User", emaill);
                    startActivity(intent);
                } else {

                    Toast.makeText(this, "Email or password it's incorect ", Toast.LENGTH_SHORT).show();
                }

        }
    }

    private boolean checkFields(){
        //check email and password to be filled
        EditText email = (EditText) findViewById(R.id.email);
        EditText password=(EditText) findViewById(R.id.password);
        String emaill = email.getText().toString();
        String passwordd = password.getText().toString();
        if (emaill.matches("") || passwordd.matches("")) {
            Toast.makeText(this, "You have empty fields", Toast.LENGTH_SHORT).show();
            return false;
        }else if(!emaill.matches("^(.+)@(.+).(.+)$")){ //check email format
            Toast.makeText(this, "You did not enter a valid email address format", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void forgotPassword(View view) {
        Toast.makeText(this,"You are dumb!", Toast.LENGTH_SHORT).show();
    }
}
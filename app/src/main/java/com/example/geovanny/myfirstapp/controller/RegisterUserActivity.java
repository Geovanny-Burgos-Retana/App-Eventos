package com.example.geovanny.myfirstapp.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.geovanny.myfirstapp.LoginActivity;
import com.example.geovanny.myfirstapp.R;
import com.example.geovanny.myfirstapp.database.UserDB;
import com.example.geovanny.myfirstapp.model.User;

public class RegisterUserActivity extends AppCompatActivity {
    private Button registerUser_btnRegister;
    private EditText registerUser_editTextName;
    private EditText registerUser_editTextUsername;
    private EditText registerUser_editTextPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        this.registerUser_editTextName = findViewById(R.id.registerUser_editTextName);
        this.registerUser_editTextUsername = findViewById(R.id.registerUser_editTextUsername);
        this.registerUser_editTextPassword = findViewById(R.id.registerUser_editTextPassword);
        this.registerUser_btnRegister = findViewById(R.id.registerUser_btnRegister);
        this.registerUser_btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Boton");
                UserDB userDB = new UserDB(getBaseContext());
                for (int i=0; i<userDB.findAll().size();i++)
                    System.out.println(userDB.findAll().get(i).toString());
                User user = new User();
                user.setId(1);
                user.setName(registerUser_editTextName.getText().toString());
                user.setUsername(registerUser_editTextUsername.getText().toString());
                user.setPassword(registerUser_editTextPassword.getText().toString());
                if (userDB.create(user)) {
                    System.out.println("Boton");
                    Toast.makeText(getBaseContext(),"Registrando", Toast.LENGTH_LONG);
                    Intent intent1 = new Intent(RegisterUserActivity.this, LoginActivity.class);
                    startActivity(intent1);
                } else {
                    Toast.makeText(getBaseContext(),"Fallo registrando usuario", Toast.LENGTH_LONG);
                }
            }
        });
    }

}

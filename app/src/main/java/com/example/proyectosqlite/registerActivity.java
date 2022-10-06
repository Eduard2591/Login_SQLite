package com.example.proyectosqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class registerActivity extends AppCompatActivity implements View.OnClickListener {

    EditText us, pas, nom, ap;
    Button reg;
    TextView back;
    daUsuario da;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Objects.requireNonNull(getSupportActionBar()).hide();

        us = findViewById(R.id.inputUsername);
        pas = findViewById(R.id.inputPassword);
        nom = findViewById(R.id.inputNombre);
        ap = findViewById(R.id.inputApe);
        reg = findViewById(R.id.btnRegister);
        back = findViewById(R.id.alreadyAccount);

        reg.setOnClickListener(this);
        back.setOnClickListener(this);
        da=new daUsuario(this);

    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegister:
                Usuario u=new Usuario();
                u.setUsuario(us.getText().toString());
                u.setPassword(pas.getText().toString());
                u.setNombre(nom.getText().toString());
                u.setApellidos(ap.getText().toString());
                if (!u.isNull()){
                    Toast.makeText(this,"ERROR: campos vacios",Toast.LENGTH_LONG).show();
                }else if(da.insertUsuario(u)){
                    Toast.makeText(this,"Registro Exitoso",Toast.LENGTH_LONG).show();
                    Intent i2= new Intent( registerActivity.this,loginActivity.class);
                    startActivity(i2);
                    finish();
                }else{
                    Toast.makeText(this,"Usuario ya Registrado",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.alreadyAccount:
                Intent i= new Intent( registerActivity.this,loginActivity.class);
                startActivity(i);
                finish();
                break;
        }

    }
}
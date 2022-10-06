package com.example.proyectosqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class Editar extends AppCompatActivity implements View.OnClickListener{

    EditText ediUser, ediPass, ediNom, ediApell;
    Button btnAct, btnCanc;
    int id=0;
    Usuario u;
    daUsuario da;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);
        Objects.requireNonNull(getSupportActionBar()).hide();
        ediUser=findViewById(R.id.ediUsername);
        ediPass=findViewById(R.id.ediPassword);
        ediNom=findViewById(R.id.ediNombre);
        ediApell=findViewById(R.id.ediApellido);
        btnAct=findViewById(R.id.btnActualizar);
        btnCanc=findViewById(R.id.btnCancel);

        btnAct.setOnClickListener(this);
        btnCanc.setOnClickListener(this);

        Bundle b=getIntent().getExtras();
        id= b.getInt("Id");
        da= new daUsuario(this);
        u=da.getUsuarioById(id);
        ediUser.setText(u.getUsuario());
        ediPass.setText(u.getPassword());
        ediNom.setText(u.getNombre());
        ediApell.setText(u.getApellidos());
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnActualizar:
                u.setUsuario(ediUser.getText().toString());
                u.setPassword(ediPass.getText().toString());
                u.setNombre(ediNom.getText().toString());
                u.setApellidos(ediApell.getText().toString());
                if (!u.isNull()){
                    Toast.makeText(this,"ERROR: campos vacios",Toast.LENGTH_LONG).show();
                }else if(da.updateUsuario(u)){
                    Toast.makeText(this,"Actualizacion Exitosa",Toast.LENGTH_LONG).show();
                    Intent i2= new Intent( Editar.this,Inicio.class);
                    i2.putExtra("Id",u.getId());
                    startActivity(i2);
                    finish();
                }else{
                    Toast.makeText(this,"No se Puede Actualizar",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btnCancel:
                Intent i= new Intent( Editar.this,Inicio.class);
                i.putExtra("Id",u.getId());
                startActivity(i);
                finish();
                break;
        }
    }
}

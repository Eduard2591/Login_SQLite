package com.example.proyectosqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class Inicio extends AppCompatActivity implements View.OnClickListener {
    Button btnEditar, btnBorrar, btnMostrar, btnSalir;
    TextView nombre;
    int id=0;
    Usuario u;
    daUsuario da;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        Objects.requireNonNull(getSupportActionBar()).hide();
        nombre= findViewById(R.id.nombreUsuario);
        btnEditar= findViewById(R.id.btnEditar);
        btnBorrar= findViewById(R.id.btnBorrar);
        btnMostrar= findViewById(R.id.btnMostrar);
        btnSalir= findViewById(R.id.btnSalir);

        btnEditar.setOnClickListener(this);
        btnBorrar.setOnClickListener(this);
        btnMostrar.setOnClickListener(this);
        btnSalir.setOnClickListener(this);

        Bundle b=getIntent().getExtras();
        id= b.getInt("Id");
        da= new daUsuario(this);
        u=da.getUsuarioById(id);
        nombre.setText(u.getNombre()+" "+u.getApellidos());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnEditar:
                Intent k= new Intent( Inicio.this,Editar.class);
                k.putExtra("Id", id);
                startActivity(k);
                break;
            case R.id.btnBorrar:
                AlertDialog.Builder b=new AlertDialog.Builder(this);
                b.setMessage("Estas Seguro De Eliminar tu Cuenta?");
                b.setCancelable(false);
                b.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (da.deleteUsuario(id)){
                            Toast.makeText(Inicio.this,"Se Elimino Coreectamente!!!", Toast.LENGTH_LONG).show();
                            Intent k= new Intent( Inicio.this,loginActivity.class);
                            startActivity(k);
                            finish();
                        }else {
                            Toast.makeText(Inicio.this,"ERROR: no se elimino cuenta", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                b.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                b.show();
                break;
            case R.id.btnMostrar:
                Intent j= new Intent( Inicio.this,Mostrar.class);
                startActivity(j);
                break;
            case R.id.btnSalir:
                Intent i= new Intent( Inicio.this,loginActivity.class);
                startActivity(i);
                finish();
                break;

        }

    }
}
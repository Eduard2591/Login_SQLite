package com.example.proyectosqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Objects;

public class Mostrar extends AppCompatActivity {
    ListView lista;
    daUsuario da;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);
        Objects.requireNonNull(getSupportActionBar()).hide();
        lista=findViewById(R.id.lista);
        da=new daUsuario(this);
        ArrayList<Usuario> l=da.selectUsuario();
        ArrayList<String> list=new ArrayList<String>();
        for (Usuario u:l) {
            list.add(u.getNombre()+" "+u.getApellidos());
        }
        ArrayAdapter<String> a=new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,list);
        lista.setAdapter(a);
    }
}
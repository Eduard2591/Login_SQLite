package com.example.proyectosqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class loginActivity extends AppCompatActivity implements View.OnClickListener{

    EditText user, pass;
    Button btnLogin;
    TextView btnSing;
    daUsuario da;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Objects.requireNonNull(getSupportActionBar()).hide();

        user = findViewById(R.id.inputUser);
        pass = findViewById(R.id.PasswordLogin);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnSing = findViewById(R.id.txtSignUp);

        btnLogin.setOnClickListener(this);
        btnSing.setOnClickListener(this);
        da=new daUsuario(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:
                String u=user.getText().toString();
                String p=pass.getText().toString();
                if(u.equals("")&&p.equals("")){
                    Toast.makeText(this,"ERROR: Campos Vacios",Toast.LENGTH_LONG).show();
                }else if (da.login(u,p)==1){
                    Usuario ux=da.getUsuario(u,p);
                    Toast.makeText(this,"Usuario Correcto",Toast.LENGTH_LONG).show();
                    Intent i2= new Intent( loginActivity.this,Inicio.class);
                    i2.putExtra("Id", ux.getId());
                    startActivity(i2);
                    finish();
                }else{
                    Toast.makeText(this,"Usuario y/o Password Incorrectos",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.txtSignUp:
                Intent i= new Intent( loginActivity.this,registerActivity.class);
                startActivity(i);
                break;

        }

    }
}
package com.trabajonube.trabajoenlanubefirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    Button cerrarSesion, datosUsuario, btnAgregarDB,btn_mostrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cerrarSesion = (Button) findViewById(R.id.btn_cerrarSesion);
        datosUsuario = (Button) findViewById(R.id.btn_datosUsuario);
        btnAgregarDB = (Button) findViewById(R.id.btn_agregarDB);
        btn_mostrar = (Button) findViewById(R.id.btn_mostrar);

        cerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthUI.getInstance().signOut(MainActivity.this).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        MainActivity.this.finish();
                    }
                });
            }
        });

        datosUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzarDatosUsuario();
            }
        });

        btnAgregarDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference myRef = firebaseDatabase.getReference("Articulos");
                myRef.setValue("1:{ costo:500, nombre:'aa', descripcion:'aaaaa' }");

            }
        });

//        leer datos de firebase
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference("mensaje2");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = snapshot.getValue(String.class);
                Log.d("ejemplo firebase", "Value: "+ value);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Error  al leer", "Error : "+ error.toException());

            }
        });


        btn_mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BasedeDatosActivity.class);
                startActivity(intent);
            }
        });
    }


    public void lanzarDatosUsuario(){
        Intent intent = new Intent(this, UsuarioActivity.class);
        startActivity(intent);
    }
}
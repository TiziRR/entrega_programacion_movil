package com.example.entregamovil.actividades;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.entregamovil.R;
import com.example.entregamovil.database.DBHelper;
import com.example.entregamovil.models.User;

public class MainActivity extends AppCompatActivity {

    // BD
    EditText input_usuario, input_contrasena;
    Button btn_ingresar;

    String nombreUsuario, contrasenia;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nombreUsuario = "";
        contrasenia = "";

        input_usuario = findViewById(R.id.main__input_usuario);
        input_contrasena = findViewById(R.id.main__input_contrasena);
        btn_ingresar = findViewById(R.id.main__button_ingresar);

        dbHelper = new DBHelper(MainActivity.this);

        // Crear usuario admin por defecto (solo si no existe)
        User usuarioNuevo = new User(0, "admin", "admin");
        long id = dbHelper.addUser(usuarioNuevo);

        btn_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nombreUsuario = input_usuario.getText().toString();
                contrasenia = input_contrasena.getText().toString();

                User usuarioIngresado = dbHelper.comprobarUsuarioLocal(nombreUsuario, contrasenia);

                if (usuarioIngresado.getId() != -1) {
                    // Login exitoso - ir a la pantalla principal
                    Intent intent = new Intent(MainActivity.this, Principal.class);
                    startActivity(intent);
                    finish(); // Cerrar el login para que no pueda volver con el botón atrás
                } else {
                    Toast.makeText(MainActivity.this, "Usuario no existe", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
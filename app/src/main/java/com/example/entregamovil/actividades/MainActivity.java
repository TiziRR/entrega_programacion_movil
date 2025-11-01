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

        User usuarioNuevo = new User(0, "admin", "admin");
        long id = dbHelper.addUser(usuarioNuevo); // id devuelto por sqlite (o -1 si falla)

        btn_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Leemos los valores ingresados en los EditText
                nombreUsuario = input_usuario.getText().toString();
                contrasenia = input_contrasena.getText().toString();

                // Consultamos localmente si existe un usuario con ese nombre + contraseña
                User usuarioIngresado = dbHelper.comprobarUsuarioLocal(nombreUsuario, contrasenia);

                /*
                 * Lógica de navegación:
                 * - Si usuarioIngresado tiene id != -1 => existe en la DB => abrimos Principal.
                 * - Si no existe mostramos un Toast indicando que no existe.
                 *
                 * Nota de seguridad: comprobarUsuarioLocal compara contraseña en texto plano.
                 * Mejor usar hashes y validar con funciones seguras.
                 */

                if (usuarioIngresado.getId() != -1) {
                    // Usuario válido -> vamos a la pantalla principal
                    Intent intent = new Intent(MainActivity.this, Principal.class);
                    startActivity(intent);
                } else {
                    // Usuario inválido -> feedback al usuario
                    Toast.makeText(MainActivity.this, "Usuario no existe", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
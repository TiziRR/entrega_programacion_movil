package com.example.entregamovil.actividades;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.entregamovil.R;
import com.example.entregamovil.models.Product;

public class DetalleProducto extends AppCompatActivity {

    private ImageView ivProductoDetalle;
    private TextView tvNombreProducto, tvCategoria, tvLocalidad, tvVentas;
    private TextView tvRatingNumero, tvDireccion, tvDescripcion, tvTagCategoria;
    private RatingBar ratingBar;
    private ImageButton btnVolver;
    private Button btnContactar, btnPreguntar;

    private Product producto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detalle_producto);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        getProductoFromIntent();
        setupViews();
        setupListeners();
    }

    private void initViews() {
        ivProductoDetalle = findViewById(R.id.ivProductoDetalle);
        tvNombreProducto = findViewById(R.id.tvNombreProducto);
        tvCategoria = findViewById(R.id.tvCategoria);
        tvLocalidad = findViewById(R.id.tvLocalidad);
        tvVentas = findViewById(R.id.tvVentas);
        tvRatingNumero = findViewById(R.id.tvRatingNumero);
        tvDireccion = findViewById(R.id.tvDireccion);
        tvDescripcion = findViewById(R.id.tvDescripcion);
        tvTagCategoria = findViewById(R.id.tvTagCategoria);
        ratingBar = findViewById(R.id.ratingBar);
        btnVolver = findViewById(R.id.btnVolver);
        btnContactar = findViewById(R.id.btnContactar);
        btnPreguntar = findViewById(R.id.btnPreguntar);
    }

    private void getProductoFromIntent() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            producto = (Product) extras.getSerializable("producto");
        }
    }

    private void setupViews() {
        if (producto != null) {
            ivProductoDetalle.setImageResource(producto.getImageResId());
            tvNombreProducto.setText(producto.getName());
            tvCategoria.setText("Categoría: " + producto.getCategoria());
            tvLocalidad.setText(producto.getLocalidad());
            tvVentas.setText(producto.getVentas());
            tvDireccion.setText(producto.getDireccion());
            tvDescripcion.setText(producto.getDescripcion());
            tvTagCategoria.setText(producto.getTagCategoria());

            ratingBar.setRating(producto.getRating());
            tvRatingNumero.setText(producto.getRating() + " (" + producto.getRatingCount() + ")");
        }
    }

    private void setupListeners() {
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnContactar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetalleProducto.this,
                        "Contactando al vendedor...", Toast.LENGTH_SHORT).show();
            }
        });

        btnPreguntar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetalleProducto.this,
                        "Abriendo chat...", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
package com.example.entregamovil.actividades;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.entregamovil.R;
import com.example.entregamovil.adapters.CarouselAdapter;
import com.example.entregamovil.adapters.ProductAdapter;
import com.example.entregamovil.database.DBHelper;
import com.example.entregamovil.models.Product;

import java.util.ArrayList;
import java.util.List;

public class Principal extends AppCompatActivity {

    private ViewPager2 viewPagerCarousel;
    private LinearLayout layoutIndicators;
    private RecyclerView recyclerFerreteria, recyclerHerreria, recyclerMadera;

    private List<Integer> carouselImages;
    private ImageView[] indicators;

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_principal);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dbHelper = new DBHelper(this);

        initViews();
        setupCarousel();
        setupRecyclerViews();
    }

    private void initViews() {
        viewPagerCarousel = findViewById(R.id.viewPagerCarousel);
        layoutIndicators = findViewById(R.id.layoutIndicators);
        recyclerFerreteria = findViewById(R.id.recyclerFerreteria);
        recyclerHerreria = findViewById(R.id.recyclerHerreria);
        recyclerMadera = findViewById(R.id.recyclerMadera);
    }

    private void setupCarousel() {
        carouselImages = new ArrayList<>();
        carouselImages.add(R.drawable.sponsor_uno);
        carouselImages.add(R.drawable.sponsor_dos);
        carouselImages.add(R.drawable.sponsor_tres);

        CarouselAdapter carouselAdapter = new CarouselAdapter(carouselImages);
        viewPagerCarousel.setAdapter(carouselAdapter);

        setupIndicators(carouselImages.size());
        setCurrentIndicator(0);

        viewPagerCarousel.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentIndicator(position);
            }
        });
    }

    private void setupIndicators(int count) {
        indicators = new ImageView[count];
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(8, 0, 8, 0);

        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(this);
            indicators[i].setImageDrawable(getDrawable(R.drawable.indicator_inactive));
            indicators[i].setLayoutParams(params);
            layoutIndicators.addView(indicators[i]);
        }
    }

    private void setCurrentIndicator(int position) {
        for (int i = 0; i < indicators.length; i++) {
            int drawableId = (i == position) ? R.drawable.indicator_active : R.drawable.indicator_inactive;
            indicators[i].setImageDrawable(getDrawable(drawableId));
        }
    }

    private void setupRecyclerViews() {
        List<Product> ferreteriaList = dbHelper.getProductsByCategory("Ferretería");
        List<Product> herreriaList = dbHelper.getProductsByCategory("Herrería");
        List<Product> maderaList = dbHelper.getProductsByCategory("Madera");

        setImageResources(ferreteriaList);
        setImageResources(herreriaList);
        setImageResources(maderaList);

        ProductAdapter ferreteriaAdapter = new ProductAdapter(ferreteriaList);
        recyclerFerreteria.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        );
        recyclerFerreteria.setAdapter(ferreteriaAdapter);

        ProductAdapter herreriaAdapter = new ProductAdapter(herreriaList);
        recyclerHerreria.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        );
        recyclerHerreria.setAdapter(herreriaAdapter);

        ProductAdapter maderaAdapter = new ProductAdapter(maderaList);
        recyclerMadera.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        );
        recyclerMadera.setAdapter(maderaAdapter);
    }

    private void setImageResources(List<Product> products) {
        for (Product product : products) {
            product.setImageResId(R.drawable.ic_launcher_background);
        }
    }
}
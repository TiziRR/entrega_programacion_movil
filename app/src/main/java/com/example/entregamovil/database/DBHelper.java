package com.example.entregamovil.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.entregamovil.models.Product;
import com.example.entregamovil.models.User;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "usuario"; // Tu nombre actual
    private static final int DATABASE_VERSION = 2; // ¡IMPORTANTE! Incrementar de 1 a 2

    // Tabla Usuarios (YA EXISTENTE)
    private static final String TABLE_USERS = "usuario"; // Tu nombre de tabla actual
    private static final String COLUMN_USER_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";

    // Tabla Productos (NUEVA)
    private static final String TABLE_PRODUCTS = "productos";
    private static final String COLUMN_PRODUCT_ID = "id";
    private static final String COLUMN_PRODUCT_NAME = "name";
    private static final String COLUMN_PRODUCT_IMAGE = "image_url";
    private static final String COLUMN_PRODUCT_RATING = "rating";
    private static final String COLUMN_PRODUCT_RATING_COUNT = "rating_count";
    private static final String COLUMN_PRODUCT_CATEGORIA = "categoria";
    private static final String COLUMN_PRODUCT_LOCALIDAD = "localidad";
    private static final String COLUMN_PRODUCT_VENTAS = "ventas";
    private static final String COLUMN_PRODUCT_DIRECCION = "direccion";
    private static final String COLUMN_PRODUCT_DESCRIPCION = "descripcion";
    private static final String COLUMN_PRODUCT_TAG = "tag_categoria";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear tabla usuarios (por si es primera vez)
        String createUsersTable = "CREATE TABLE IF NOT EXISTS " + TABLE_USERS + " (" +
                COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT NOT NULL, " +
                COLUMN_PASSWORD + " TEXT NOT NULL)";
        db.execSQL(createUsersTable);

        // Crear tabla productos
        crearTablaProductos(db);

        // Insertar datos de ejemplo solo si no existen
        insertarDatosIniciales(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // NO borrar la tabla usuarios existente
        // Solo crear la tabla productos si no existe
        if (oldVersion < 2) {
            crearTablaProductos(db);
            insertarProductosEjemplo(db);
        }
    }

    private void crearTablaProductos(SQLiteDatabase db) {
        String createProductsTable = "CREATE TABLE IF NOT EXISTS " + TABLE_PRODUCTS + " (" +
                COLUMN_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PRODUCT_NAME + " TEXT NOT NULL, " +
                COLUMN_PRODUCT_IMAGE + " TEXT, " +
                COLUMN_PRODUCT_RATING + " REAL, " +
                COLUMN_PRODUCT_RATING_COUNT + " INTEGER, " +
                COLUMN_PRODUCT_CATEGORIA + " TEXT, " +
                COLUMN_PRODUCT_LOCALIDAD + " TEXT, " +
                COLUMN_PRODUCT_VENTAS + " TEXT, " +
                COLUMN_PRODUCT_DIRECCION + " TEXT, " +
                COLUMN_PRODUCT_DESCRIPCION + " TEXT, " +
                COLUMN_PRODUCT_TAG + " TEXT)";
        db.execSQL(createProductsTable);
    }

    private void insertarDatosIniciales(SQLiteDatabase db) {
        // Verificar si ya existe el usuario admin
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " +
                COLUMN_USERNAME + " = 'admin'", null);

        if (cursor.getCount() == 0) {
            // Insertar usuario admin solo si no existe
            ContentValues userValues = new ContentValues();
            userValues.put(COLUMN_USERNAME, "admin");
            userValues.put(COLUMN_PASSWORD, "admin");
            db.insert(TABLE_USERS, null, userValues);
        }
        cursor.close();

        // Insertar productos de ejemplo
        insertarProductosEjemplo(db);
    }

    private void insertarProductosEjemplo(SQLiteDatabase db) {
        // Verificar si ya hay productos
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_PRODUCTS, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();

        // Solo insertar si no hay productos
        if (count == 0) {
            // Productos de Ferretería
            insertProductoEjemplo(db, "Tornillo de Oro 50x50", "tornillo_oro", 5.0f, 34,
                    "Ferretería", "Posadas", "50+", "Av. Uruguay 1234",
                    "El Tornillo de Oro de 50 x 50 es un tornillo de alta calidad, especialmente diseñado para una sujeción firme y duradera en diversas aplicaciones. Fabricado con un material resistente a la corrosión, es ideal para carpintería, construcción y trabajos de reparación en interiores y exteriores.\n\nEste tornillo cuenta con una cabeza plana que permite un acabado estético, ya que queda al ras de la superficie. Su rosca profunda asegura un agarre firme en madera, paneles y otros materiales, facilitando la instalación y proporcionando mayor estabilidad.",
                    "BUILDING MATERIALS");

            insertProductoEjemplo(db, "Martillo Profesional", "martillo", 4.8f, 28,
                    "Ferretería", "Posadas", "40+", "Av. Uruguay 1234",
                    "Martillo profesional de alta resistencia, ideal para trabajos de construcción y reparación. Mango ergonómico antideslizante para mayor comodidad y seguridad. Cabeza de acero forjado para mayor durabilidad.",
                    "BUILDING MATERIALS");

            insertProductoEjemplo(db, "Destornillador Set x12", "destornillador", 4.5f, 45,
                    "Ferretería", "Posadas", "60+", "Av. Uruguay 1234",
                    "Set completo de 12 destornilladores de diferentes tamaños y tipos. Incluye puntas planas y Phillips. Mangos ergonómicos con grip antideslizante. Ideal para profesionales y uso doméstico.",
                    "BUILDING MATERIALS");

            // Productos de Herrería
            insertProductoEjemplo(db, "Perfil de Hierro L 2x2", "perfil_hierro", 4.7f, 20,
                    "Herrería", "Posadas", "30+", "Av. Corrientes 567",
                    "Perfil de hierro en L de 2x2 pulgadas, ideal para estructuras, rejas y construcciones metálicas. Material de primera calidad con tratamiento anticorrosivo. Longitud de 6 metros.",
                    "METAL WORKS");

            insertProductoEjemplo(db, "Soldadura MIG 1kg", "soldadura", 4.9f, 15,
                    "Herrería", "Posadas", "25+", "Av. Corrientes 567",
                    "Alambre de soldadura MIG de 1kg para trabajos de herrería profesional. Excelente calidad de soldadura y rendimiento. Compatible con la mayoría de máquinas de soldar.",
                    "METAL WORKS");

            insertProductoEjemplo(db, "Electrodo 6013 x50", "electrodo", 4.6f, 18,
                    "Herrería", "Posadas", "35+", "Av. Corrientes 567",
                    "Caja de 50 electrodos 6013 para soldadura eléctrica. Ideal para trabajos generales de herrería y construcción. Excelente penetración y acabado.",
                    "METAL WORKS");

            // Productos de Madera
            insertProductoEjemplo(db, "Tabla de Pino 2x4", "tabla_pino", 4.4f, 40,
                    "Madera", "Posadas", "55+", "Av. Mitre 890",
                    "Tabla de pino cepillada de 2x4 pulgadas, ideal para construcción y carpintería. Madera seca y lista para usar. Longitud de 3 metros. Excelente calidad y durabilidad.",
                    "WOOD MATERIALS");

            insertProductoEjemplo(db, "Barniz Marino 1L", "barniz", 4.7f, 32,
                    "Madera", "Posadas", "45+", "Av. Mitre 890",
                    "Barniz marino de 1 litro para protección de madera exterior e interior. Resistente al agua y rayos UV. Acabado brillante y duradero. Fácil aplicación.",
                    "WOOD MATERIALS");

            insertProductoEjemplo(db, "Lija Orbital x10", "lija", 4.3f, 50,
                    "Madera", "Posadas", "70+", "Av. Mitre 890",
                    "Pack de 10 lijas orbitales de diferentes granulometrías. Perfectas para trabajos de carpintería y acabados en madera. Alta durabilidad y rendimiento.",
                    "WOOD MATERIALS");
        }
    }

    private void insertProductoEjemplo(SQLiteDatabase db, String name, String imageUrl,
                                       float rating, int ratingCount, String categoria,
                                       String localidad, String ventas, String direccion,
                                       String descripcion, String tagCategoria) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_NAME, name);
        values.put(COLUMN_PRODUCT_IMAGE, imageUrl);
        values.put(COLUMN_PRODUCT_RATING, rating);
        values.put(COLUMN_PRODUCT_RATING_COUNT, ratingCount);
        values.put(COLUMN_PRODUCT_CATEGORIA, categoria);
        values.put(COLUMN_PRODUCT_LOCALIDAD, localidad);
        values.put(COLUMN_PRODUCT_VENTAS, ventas);
        values.put(COLUMN_PRODUCT_DIRECCION, direccion);
        values.put(COLUMN_PRODUCT_DESCRIPCION, descripcion);
        values.put(COLUMN_PRODUCT_TAG, tagCategoria);
        db.insert(TABLE_PRODUCTS, null, values);
    }

    // ==================== MÉTODOS USUARIOS (MANTENER COMO ESTABAN) ====================

    public long addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, user.getNombreUsuario());
        values.put(COLUMN_PASSWORD, user.getPassword());

        long id = db.insert(TABLE_USERS, null, values);
        db.close();
        return id;
    }

    public User comprobarUsuarioLocal(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        User user = new User();
        user.setId(-1);

        String query = "SELECT * FROM " + TABLE_USERS +
                " WHERE " + COLUMN_USERNAME + " = ? AND " +
                COLUMN_PASSWORD + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{username, password});

        if (cursor.moveToFirst()) {
            user.setId(cursor.getInt(0));
            user.setNombreUsuario(cursor.getString(1));
            user.setPassword(cursor.getString(2));
        }

        cursor.close();
        db.close();
        return user;
    }

    // ==================== MÉTODOS PRODUCTOS (NUEVOS) ====================

    public long addProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_PRODUCT_NAME, product.getName());
        values.put(COLUMN_PRODUCT_IMAGE, product.getImageUrl());
        values.put(COLUMN_PRODUCT_RATING, product.getRating());
        values.put(COLUMN_PRODUCT_RATING_COUNT, product.getRatingCount());
        values.put(COLUMN_PRODUCT_CATEGORIA, product.getCategoria());
        values.put(COLUMN_PRODUCT_LOCALIDAD, product.getLocalidad());
        values.put(COLUMN_PRODUCT_VENTAS, product.getVentas());
        values.put(COLUMN_PRODUCT_DIRECCION, product.getDireccion());
        values.put(COLUMN_PRODUCT_DESCRIPCION, product.getDescripcion());
        values.put(COLUMN_PRODUCT_TAG, product.getTagCategoria());

        long id = db.insert(TABLE_PRODUCTS, null, values);
        db.close();
        return id;
    }

    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_PRODUCTS;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Product product = new Product(
                        cursor.getInt(0),      // id
                        cursor.getString(1),   // name
                        cursor.getString(2),   // image_url
                        cursor.getFloat(3),    // rating
                        cursor.getInt(4),      // rating_count
                        cursor.getString(5),   // categoria
                        cursor.getString(6),   // localidad
                        cursor.getString(7),   // ventas
                        cursor.getString(8),   // direccion
                        cursor.getString(9),   // descripcion
                        cursor.getString(10)   // tag_categoria
                );
                productList.add(product);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return productList;
    }

    public List<Product> getProductsByCategory(String categoria) {
        List<Product> productList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_PRODUCTS +
                " WHERE " + COLUMN_PRODUCT_CATEGORIA + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{categoria});

        if (cursor.moveToFirst()) {
            do {
                Product product = new Product(
                        cursor.getInt(0),      // id
                        cursor.getString(1),   // name
                        cursor.getString(2),   // image_url
                        cursor.getFloat(3),    // rating
                        cursor.getInt(4),      // rating_count
                        cursor.getString(5),   // categoria
                        cursor.getString(6),   // localidad
                        cursor.getString(7),   // ventas
                        cursor.getString(8),   // direccion
                        cursor.getString(9),   // descripcion
                        cursor.getString(10)   // tag_categoria
                );
                productList.add(product);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return productList;
    }

    public Product getProductById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Product product = null;

        String query = "SELECT * FROM " + TABLE_PRODUCTS +
                " WHERE " + COLUMN_PRODUCT_ID + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id)});

        if (cursor.moveToFirst()) {
            product = new Product(
                    cursor.getInt(0),      // id
                    cursor.getString(1),   // name
                    cursor.getString(2),   // image_url
                    cursor.getFloat(3),    // rating
                    cursor.getInt(4),      // rating_count
                    cursor.getString(5),   // categoria
                    cursor.getString(6),   // localidad
                    cursor.getString(7),   // ventas
                    cursor.getString(8),   // direccion
                    cursor.getString(9),   // descripcion
                    cursor.getString(10)   // tag_categoria
            );
        }

        cursor.close();
        db.close();
        return product;
    }
}

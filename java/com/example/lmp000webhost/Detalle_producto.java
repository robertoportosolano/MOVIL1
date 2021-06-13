package com.example.lmp000webhost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Detalle_producto extends AppCompatActivity {
    TextView tvcodigo,tvproducto,tvprecio,tvfabricante;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);


        //Initializando las vistas
        tvcodigo = findViewById(R.id.tv_codigo);
        tvproducto = findViewById(R.id.tv_producto);
        tvprecio = findViewById(R.id.tv_precio);
        tvfabricante = findViewById(R.id.tv_fabricante);

        Intent intent =getIntent();
        position = intent.getExtras().getInt("position");

        tvcodigo.setText("Codigo: "+Enlistar_producto.productoArrayList.get(position).getCodigo());
        tvproducto.setText("Producto: "+Enlistar_producto.productoArrayList.get(position).getProducto());
        tvprecio.setText("Precio: "+Enlistar_producto.productoArrayList.get(position).getPrecio());
        tvfabricante.setText("Fabricante: "+Enlistar_producto.productoArrayList.get(position).getFabricante());
    }
}

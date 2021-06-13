package com.example.lmp000webhost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Editar_producto extends AppCompatActivity {
    EditText edt_codigo,edt_producto,edt_precio,edt_fabricate;
    Button btn_agregar;
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_producto);
        edt_codigo=findViewById(R.id.edt_codigoe);
        edt_producto=findViewById(R.id.edt_productoe);
        edt_precio=findViewById(R.id.edt_precioe);
        edt_fabricate=findViewById(R.id.edt_fabricantee);

        Intent intent= getIntent();
        position= intent.getExtras().getInt("position");

        edt_codigo.setText(Enlistar_producto.productoArrayList.get(position).getCodigo());
        edt_producto.setText(Enlistar_producto.productoArrayList.get(position).getProducto());
        edt_precio.setText(Enlistar_producto.productoArrayList.get(position).getPrecio());
        edt_fabricate.setText(Enlistar_producto.productoArrayList.get(position).getFabricante());




    }

    public void Actualizar_Producto(View view) {

       final String codigo= edt_codigo.getText().toString();
        final String producto= edt_producto.getText().toString();
        final String precio= edt_precio.getText().toString();
        final String fabricante= edt_fabricate.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://pmovil1cua.000webhostapp.com/actualizar_productos_tienda.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                            Toast.makeText(Editar_producto.this,response,
                                     Toast.LENGTH_SHORT).show();
                           startActivity(new Intent(getApplicationContext(),Enlistar_producto.class));
                           finish();

                   }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Editar_producto.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros= new HashMap<String,String>();
                parametros.put("codigo",codigo);
                parametros.put("producto",producto);
                parametros.put("precio",precio);
                parametros.put("fabricante",fabricante);
                return parametros;
            }
        };

        // Add the request to the RequestQueue.
        RequestQueue queue= Volley.newRequestQueue(Editar_producto.this);
        queue.add(stringRequest);

    }


}


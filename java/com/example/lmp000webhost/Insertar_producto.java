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

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class Insertar_producto extends AppCompatActivity {
     EditText  edt_codigo,edt_producto,edt_precio,edt_fabricate;
     Button btn_agregar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_producto);


        edt_codigo=findViewById(R.id.edt_codigoi);
        edt_producto=findViewById(R.id.edt_productoi);
        edt_precio=findViewById(R.id.edt_precioi);
        edt_fabricate=findViewById(R.id.edt_fabricantei);
        btn_agregar=findViewById(R.id.btnAgregar);

        btn_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             insertar_Producto();
            }
        });
    }

    private void insertar_Producto() {
    final String codigo= edt_codigo.getText().toString().trim();
    final String producto= edt_producto.getText().toString().trim();
    final String precio= edt_precio.getText().toString().trim();
    final String fabricante= edt_fabricate.getText().toString().trim();
     if (codigo.isEmpty()){
         Toast.makeText(this, "Ingresa el codigo", Toast.LENGTH_SHORT).show();
          return;
       }else if (producto.isEmpty()){
         Toast.makeText(this, "Ingresa el producto", Toast.LENGTH_SHORT).show();
         return;
       }else if (precio.isEmpty()){
         Toast.makeText(this, "Ingresa el precio", Toast.LENGTH_SHORT).show();
         return;
        }else if (fabricante.isEmpty()){
         Toast.makeText(this, "Ingresa el fabricante", Toast.LENGTH_SHORT).show();
         return;
       }else{

         StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://pmovil1cua.000webhostapp.com/insertar_productos_tienda.php",
                 new Response.Listener<String>() {
                     @Override
                     public void onResponse(String response) {
                          if (response.equalsIgnoreCase("Producto_Insertado")){
                              Toast.makeText(Insertar_producto.this," Producto Insertado", Toast.LENGTH_SHORT).show();
                         }else{

                              Toast.makeText(Insertar_producto.this,response, Toast.LENGTH_SHORT).show();
                         }

                     }
                 }, new Response.ErrorListener() {
             @Override
             public void onErrorResponse(VolleyError error) {
                 Toast.makeText(Insertar_producto.this, error.getMessage(), Toast.LENGTH_SHORT).show();
             }
         }){
             @Override
             protected Map<String, String> getParams() throws AuthFailureError {
                 Map<String,String> parametros= new HashMap<String,String>();
                 parametros.put("codigo",edt_codigo.getText().toString());
                 parametros.put("producto",edt_producto.getText().toString());
                 parametros.put("precio",edt_precio.getText().toString());
                 parametros.put("fabricante",edt_fabricate.getText().toString());
                 return parametros;
             }
         };

         // Add the request to the RequestQueue.
         RequestQueue queue= Volley.newRequestQueue(Insertar_producto.this);
         queue.add(stringRequest);

     }
   }
}

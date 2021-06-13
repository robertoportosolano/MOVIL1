package com.example.lmp000webhost;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Enlistar_producto extends AppCompatActivity  {
    ListView listView;
    MyAdapter adapter;
    public static ArrayList<Producto> productoArrayList = new ArrayList<>();
    String url = "https://pmovil1cua.000webhostapp.com/recuperar_productos_tienda.php";
    Producto Producto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enlistar_producto);



        listView = findViewById(R.id.myListView);
        adapter = new MyAdapter(Enlistar_producto.this, productoArrayList);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                CharSequence[] dialogItem = {"Ver Producto", "Editar Producto", "Eliminar Producto"};
                builder.setTitle(productoArrayList.get(position).getProducto());
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        switch (i) {
                            case 0:
                                startActivity(new Intent(getApplicationContext(), Detalle_producto.class)
                                        .putExtra("position", position));
                                break;
                            case 1:
                                startActivity(new Intent(getApplicationContext(), Editar_producto.class)
                                        .putExtra("position", position));
                                break;
                            case 2:
                                deleteData(productoArrayList.get(position).getCodigo());
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        retrieveData();
    }

    private void deleteData(final String codigo) {

        StringRequest request = new StringRequest(Request.Method.POST, "https://pmovil1cua.000webhostapp.com/eliminar_productos_tienda.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response.equalsIgnoreCase("Producto eliminado")) {
                            Toast.makeText(Enlistar_producto.this, "Se ha eliminado el producto " +
                                    "de forma correcta ", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Enlistar_producto.this, "No se elimino el producto", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Enlistar_producto.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("codigo", codigo);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    public void retrieveData() {
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        productoArrayList.clear();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String sucess = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("producto");
                            if (sucess.equals("1")) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String codigo = object.getString("codigo");
                                    String producto = object.getString("producto");
                                    String precio = object.getString("precio");
                                    String fabricante = object.getString("fabricante");
                                    Producto = new Producto(codigo, producto, precio, fabricante);
                                    productoArrayList.add(Producto);
                                    adapter.notifyDataSetChanged();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Enlistar_producto.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
    public void btn_add_activity(View view) {
        startActivity(new Intent(getApplicationContext(),Insertar_producto.class));
    }
}

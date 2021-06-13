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

public class MainActivity extends AppCompatActivity {
    EditText edtuser,edtpassword;
    Button   btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtuser= findViewById(R.id.edtUsuario);
        edtpassword= findViewById(R.id.edtPassword);
        btnLogin=findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarUsuario("https://pmovil1cua.000webhostapp.com/validar_usuario.php");
            }
        });

   }

   private void validarUsuario(String Url){

       // Request a string response from the provided URL.
       StringRequest stringRequest = new StringRequest(Request.Method.POST, Url,
               new Response.Listener<String>() {
                   @Override
                   public void onResponse(String response) {
                       // Display the first 500 characters of the response string.
                       //textView.setText("Response is: "+ response.substring(0,500));
                   if (!response.isEmpty()){
                       Intent intent= new Intent(getApplicationContext(),Enlistar_producto.class);
                       startActivity(intent);
                   }else{
                       Toast.makeText(MainActivity.this,"usuario o contraseñas o son incorrectas", Toast.LENGTH_SHORT).show();

                   }

                   }
               }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {
                   Toast.makeText(MainActivity.this,error.toString(), Toast.LENGTH_SHORT).show();
           }
       }){
           @Override
           protected Map<String, String> getParams() throws AuthFailureError {
               Map<String,String> parametros= new HashMap   <String,String>();
               parametros.put("usuario",edtuser.getText().toString());
               parametros.put("password",edtpassword.getText().toString());
               return parametros;
           }
       };

       // Add the request to the RequestQueue.
       RequestQueue   queue= Volley.newRequestQueue(this);
       queue.add(stringRequest);



   }

}

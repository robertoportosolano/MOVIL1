package com.example.lmp000webhost;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MyAdapter  extends ArrayAdapter<Producto>
{

    Context context;
    List<Producto> arrayListProducto;

    public MyAdapter(@NonNull Context context, List<Producto> arrayListProducto) {
        super(context,R.layout.personalizar_lista_item,arrayListProducto);
        this.context = context;
        this.arrayListProducto = arrayListProducto;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.personalizar_lista_item,null,true);

        TextView tvCodigo = view.findViewById(R.id.txt_codigo);
        TextView tvProducto = view.findViewById(R.id.producto_nombre);

        tvCodigo.setText(arrayListProducto.get(position).getCodigo());
        tvProducto.setText(arrayListProducto.get(position).getProducto());

        return view;
    }

}
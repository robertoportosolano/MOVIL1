package com.example.lmp000webhost;

public class Producto {
   private String codigo,producto,precio,fabricante;

    public Producto() {
    }

    public Producto(String codigo, String producto, String precio, String fabricante) {
        this.codigo = codigo;
        this.producto = producto;
        this.precio = precio;
        this.fabricante = fabricante;
    }


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }
}

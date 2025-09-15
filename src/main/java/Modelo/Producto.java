/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author estudiante
 */
public class Producto {
    
    private int codigo;
    private String nombre;
    private float precio;
    private boolean importado;
    private Tipo tipo;
    
    public Producto(){
        this.codigo = 0;
        this.nombre = "";
        this.precio = 0.0f;
        this.importado = false;
        this.tipo = new Tipo();
    }
    
    public Producto(int codigo, String nombre, float precio, boolean importado, Tipo tipo){
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.importado = importado;
        this.tipo = tipo;
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the precio
     */
    public float getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(float precio) {
        this.precio = precio;
    }

    /**
     * @return the importado
     */
    public boolean isImportado() {
        return importado;
    }

    /**
     * @param importado the importado to set
     */
    public void setImportado(boolean importado) {
        this.importado = importado;
    }

    /**
     * @return the tipo
     */
    public Tipo getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
    
    public float PrecioFinal(){
        float impuesto = (tipo.getPorcentajeImpuesto()/precio)*100;
        if (importado){
            float recargo = (50/precio)*100;
            return precio + impuesto + recargo;
        }
        return precio + impuesto;
    }
}

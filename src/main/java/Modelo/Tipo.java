/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author estudiante
 */
public class Tipo {
    
    private int idTipo;
    private String descripcion;
    private float porcenImpuesto;
    
    public Tipo(){
        this.idTipo = 0;
        this.descripcion = "";
        this.porcenImpuesto = 0.0f;
    }
    public Tipo(int idTipo, String descripcion, float porcenImpuesto){
        this.idTipo = idTipo;
        this.descripcion = descripcion;
        this.porcenImpuesto = porcenImpuesto;
        
    }

    /**
     * @return the nombre
     */
    public int  getIdTipo() {
        return idTipo;
    }

    /**
     * @param idTipo the nombre to set
     */
    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the porcentajeImpuesto
     */
    public float getPorcentajeImpuesto() {
        return porcenImpuesto;
    }

    /**
     * @param porcentajeImpuesto the porcentajeImpuesto to set
     */
    public void setPorcentajeImpuesto(float porcentajeImpuesto) {
        this.porcenImpuesto = porcentajeImpuesto;
    }

    
}

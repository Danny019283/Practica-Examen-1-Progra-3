/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoADatos;


import java.util.ArrayList;
import Modelo.Producto;
import Datos.AdmiSerializable;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.io.File;
import java.util.List;

/**
 *
 * @author estudiante
 */
@JacksonXmlRootElement(localName = "personas")
public class ColeccionProducto {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "producto")
    private List<Producto> listaProducs = cargar();

    private static final File FILE = new File("productos.xml");

    public boolean insertar(Producto produc){
        if (buscar(produc.getCodigo()) != null){
            return false;
        }
        listaProducs.add(produc);
        guardar();
        return true;
    }
    public boolean modificar(Producto producMod){
        for (int i = 0; i < listaProducs.size(); i++){
            if(listaProducs.get(i).getCodigo() == producMod.getCodigo()){
                listaProducs.set(i, producMod);
                return true;
            }
        }
        return false;
    }
    public boolean eliminar(int id){
        if (listaProducs.isEmpty()){
            return false;
        }
        for (int i = 0; i < listaProducs.size(); i++) {
            if (listaProducs.get(i).getCodigo() == id){
                listaProducs.remove(i);
                return true;
            }
        }
        return false;
        
    }
    public Producto buscar(int id){
        for (Producto produc : listaProducs) {
            if (produc.getCodigo() == id) {
                return produc;
            }
        }
        return null;
    }

    private List<Producto> cargar(){
        try {
            return  AdmiSerializable.loadListFromXml(FILE, Producto.class, new Producto());
        } catch (Exception e) {
            System.out.println("Error al cargar los productos: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    private void guardar(){
        try {
            AdmiSerializable.saveToXml(listaProducs, FILE);
        } catch (Exception e) {
            System.out.println("Error al guardar los productos: " + e.getMessage());
        }
    }



    public ArrayList<Producto> listar(){
        return new ArrayList<>(listaProducs);
    }
}

package Control;
import AccesoADatos.ColeccionProducto;
import AccesoADatos.ColeccionTipo;
import Modelo.Producto;
import Modelo.Tipo;
import Presentacion.tablaProducto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class ControladorProducto {

    private final ColeccionProducto backend = new ColeccionProducto(); //Indica la fuente de datos
    private final ColeccionTipo backendTipo = new ColeccionTipo();

    public ControladorProducto() {
    }

    public void cargarDatos() {
        // Sembrar si está vacío

        if (backend.listar().isEmpty()) {

            Tipo t1 = new Tipo(1, "Canasta basica", 5);
            backendTipo.AgregarTipo(t1);

            Tipo t2 = new Tipo(2, "Electrodomésticos", 15);
            backendTipo.AgregarTipo(t2);

            Tipo t3 = new Tipo(3, "Bebidas", 10);
            backendTipo.AgregarTipo(t3);

            backend.insertar(new Producto(123, "Leche", 1000, false, t1));
            backend.insertar(new Producto(124, "Arroz", 800, false, t1));
            backend.insertar(new Producto(200, "Refrigeradora", 350000, true, t2));
            backend.insertar(new Producto(201, "Coca Cola 2L", 1500, false, t3));

        }

    }

    public void agregarProduc(ActionEvent e, Producto nuevo, tablaProducto tblProducto) {
        if (!backend.insertar(nuevo)) {
            throw new IllegalArgumentException("El codigo ya existe: " + nuevo.getCodigo());
        }
        tblProducto.add(nuevo);

    }

    public ArrayList<String> getDescripciones() {
        ArrayList<String> descripciones = new ArrayList<>();
        for (Tipo tipo : backendTipo.getListaTipos()) {
            descripciones.add(tipo.getDescripcion());
        }
        return descripciones;
    }

    public Producto leerForm(String txtCod, String txtNombre, String txtPrecio, boolean chkImportado, String cmbTipo) throws IllegalArgumentException {
        int cod;
        try {
            cod = Integer.parseInt(txtCod);
        }
        catch (NumberFormatException ex) {
            throw new IllegalArgumentException("El codigo solo puede llevar numeros enteros");
        }
        String nombre = txtNombre.trim();
        String precio = txtPrecio.trim();
        Tipo tipo = backendTipo.buscarTipo((String) cmbTipo);

        if (cod == 0 ||  nombre.isEmpty() || precio.isEmpty()) {
            throw new IllegalArgumentException("Complete todos los campos.");
        }
        float precio1;
        try { precio1 = Float.parseFloat(precio); }
        catch (NumberFormatException ex) { throw new IllegalArgumentException("El año debe ser entero."); }

        if (precio1 < 0) throw new IllegalArgumentException("El año no puede ser negativo.");

        return new Producto(cod, nombre, precio1, chkImportado, tipo);
    }

    public ArrayList<Producto> listar() {
        return backend.listar();
    }



}

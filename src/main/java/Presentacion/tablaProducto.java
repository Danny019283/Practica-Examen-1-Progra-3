/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentacion;


import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import Modelo.Producto;

/**
 *
 * @author Danny
 */
public class tablaProducto extends AbstractTableModel{
    private final String[] cols = {"Codigo", "Nombre", "Importado", "Precio", "Tipo", "Porcentaje", "Precio Final"}; //crea las colummnas
    private final List<Producto> data = new ArrayList<>(); //data es la Lista donde se van almacenar los productos
    
    public void setData(List<Producto> productos) {
        data.clear(); //limpia antes de cargar los datos
        data.addAll(productos); //agrega los productos a la lista interna
        fireTableDataChanged(); //notifica que lo datos han sigo actualizado entonces refresca la lista
    }
    
    public Producto getAt(int row) {
        return data.get(row); //obtiene el producto que se encuentra en una fila especifica
    }
    
    public void add(Producto produc) {
        data.add(produc); //agrega un producto a la tabla
        int fila = data.size() - 1;
        fireTableRowsInserted(fila, fila); //Notifica a todos los oyentes que se han insertado filas en la ultima posicion
    }
    
    public void update(int fila, Producto produc) {
        data.set(fila, produc);
        fireTableRowsUpdated(fila, fila); //Notifica a todos los oyentes que se han actualizado en una posicion especifica
    }
    
    public void remove(int fila) {
        data.remove(fila);
        fireTableRowsDeleted(fila, fila); //Notifica a todos los oyentes que se han eliminado filas en una posicion especifica
    }
    
    //son metodos de un super tipo (propios de Table Model)
    @Override public int getRowCount() { return data.size(); }
    @Override public int getColumnCount() { return cols.length; }
    @Override public String getColumnName(int column) { return cols[column]; }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Producto p = data.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> p.getCodigo();
            case 1 -> p.getNombre();
            case 2 -> {if (p.isImportado()) {yield "si";} else{yield "no";}}
            case 3 -> p.getPrecio();
            case 4 -> p.getTipo().getDescripcion();
            case 5 -> p.getTipo().getPorcentajeImpuesto();
            case 6 -> p.PrecioFinal();
            default -> "";
        };
    }
    //Devuelve la superclase más específica para todos los valores de celda de la columna. 
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnIndex == 3 ? Integer.class : String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false; // editamos desde el formulario
    }

}

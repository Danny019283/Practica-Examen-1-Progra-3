/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentacion;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;   // <-- esto incluye ActionListener, ActionEvent, etc.

import Control.ControladorProducto;

/**
 *
 * @author Danny
 */
public class JFrameProducto extends JFrame{

    private final ControladorProducto controlador = new ControladorProducto();

    private final tablaProducto tblProducto = new tablaProducto(); //Intermediario entre la tabla y los datos
    private JTable tabla = new JTable();
    private TableRowSorter<tablaProducto> sorter;

    //Crea los espacios para escribir
    private final JTextField txtBuscarNombre = new JTextField();
    private final JComboBox<String> cmbBuscarTipo = new JComboBox<String>();
    private final JTextField txtCod = new JTextField();
    private final JTextField txtNombre = new JTextField();
    private final JTextField txtPrecio = new JTextField();
    private final JCheckBox chkImportado = new JCheckBox();
    private final JComboBox<String> cmbTipo = new JComboBox<>();
    
    //Crea los botones
    private final JButton btnBuscarNombre = new JButton("Buscar");
    private final JButton btnBuscarTipo = new JButton("Buscar");
    private final JButton btnAgregar = new JButton("Agregar");

    //Crea el constructor JFrame (configura la ventana)
    JFrameProducto(){
        setTitle("Productos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setLocationRelativeTo(null); //centra la ventana
        buildUI();
        tblProducto.setData(controlador.listar());
        controlador.cargarDatos();
        setCombo();
        setVisible(true);
    }

    public void buildUI() {
        //este es el panel donde va los demas paneles como una hoja
        JPanel pnlContenido = new JPanel(new BorderLayout(12, 12));
        pnlContenido.setBackground(Color.LIGHT_GRAY);
        pnlContenido.setBorder(new EmptyBorder(12, 20, 12, 20)); //crea espacios entre elementos
        setContentPane(pnlContenido);

        //panel superior
        JPanel pnlArriba = new JPanel();
        pnlArriba.setLayout(new BoxLayout(pnlArriba, BoxLayout.X_AXIS)); //Va dividiendo el panel en el x
        pnlArriba.setBorder(new EmptyBorder(12, 12, 12, 12));
        pnlContenido.add(pnlArriba, BorderLayout.NORTH);

        // TopWest: búsqueda por nombre
        JPanel pnlArribaIzq = new JPanel(new BorderLayout(12, 12));
        pnlArribaIzq.add(new JLabel("Nombre:"), BorderLayout.WEST);
        pnlArribaIzq.add(txtBuscarNombre, BorderLayout.CENTER);
        pnlArribaIzq.add(btnBuscarNombre, BorderLayout.EAST);
        pnlArriba.add(pnlArribaIzq);

        pnlArriba.add(Box.createRigidArea(new Dimension(20, 10))); //agrega una separacion entre panel y panel

        //TopEast busqueda por tipo
        JPanel pnlArribaDer = new JPanel(new BorderLayout(12, 12));
        pnlArribaDer.add(new JLabel("Tipo:"), BorderLayout.WEST);
        pnlArribaDer.add(cmbBuscarTipo, BorderLayout.CENTER);
        pnlArribaDer.add(btnBuscarTipo, BorderLayout.EAST);
        pnlArriba.add(pnlArribaDer);

        // Centro: tabla
        tabla.setRowHeight(24);
        tabla = new JTable(tblProducto);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        sorter = new TableRowSorter<>(tblProducto);
        tabla.setRowSorter(sorter);
        pnlContenido.add(new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel pnlAbajo = new JPanel(new GridLayout(0, 2));
        pnlAbajo.setBorder(new EmptyBorder(12, 12, 12, 12));
        pnlContenido.add(pnlAbajo, BorderLayout.SOUTH);

        // Abajp: formulario + boton
        JPanel pnlAbajoIzq = new JPanel();
        pnlAbajoIzq.setBackground(Color.LIGHT_GRAY);
        pnlAbajoIzq.setLayout(new GridLayout(0, 2, 12, 0));
        pnlAbajoIzq.add(field("Codigo", txtCod));
        pnlAbajoIzq.add(Box.createVerticalStrut(6));
        pnlAbajoIzq.add(field("Nombre", txtNombre));
        pnlAbajoIzq.add(Box.createVerticalStrut(6));
        pnlAbajoIzq.add(field("Precio", txtPrecio));
        pnlAbajoIzq.add(Box.createVerticalStrut(6));
        chkImportado.setBackground(Color.LIGHT_GRAY);
        pnlAbajoIzq.add(field("Importado", chkImportado));
        pnlAbajoIzq.add(Box.createVerticalStrut(6));
        pnlAbajoIzq.add(field("Tipo", cmbTipo));

        JPanel pnlBotonA = new JPanel();
        pnlBotonA.setBackground(Color.LIGHT_GRAY);
        pnlBotonA.setBorder(new EmptyBorder(11, 12, 8, 12));
        pnlBotonA.add(btnAgregar);

        pnlAbajoIzq.add(pnlBotonA);
        pnlAbajo.add(pnlAbajoIzq);


        // Listeners
        btnBuscarNombre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = txtBuscarNombre.getText().trim();
                if (nombre.isEmpty()) {
                    sorter.setRowFilter(null); // mostrar todo
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + nombre));
                }
            }
        });

        btnBuscarTipo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipo = (String) cmbBuscarTipo.getSelectedItem();
                if (tipo.isEmpty()) {
                    sorter.setRowFilter(null); // mostrar todo
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + tipo));
                }
            }
        });

        btnAgregar.addActionListener(this::onAgregar);
    }

    //crea filas de JPanel
    private JPanel field(String label, JComponent input) {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(Color.LIGHT_GRAY);
        p.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        JLabel lbl = new JLabel(label);
        lbl.setPreferredSize(new Dimension(50, 25));
        p.add(lbl, BorderLayout.WEST);
        p.add(input, BorderLayout.CENTER);
        return p;
    }

    private void onAgregar(ActionEvent e) {
        try {
            controlador.agregarProduc(e, controlador.leerForm(
                    txtCod.getText(), txtNombre.getText(), txtPrecio.getText(), chkImportado.isSelected(), (String) cmbTipo.getSelectedItem()), tblProducto);
            clearForm();
            JOptionPane.showMessageDialog(this, "Producto agregado.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Validación", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void setCombo(){
        for (String desc : controlador.getDescripciones()) {
            cmbTipo.addItem(desc);
            cmbBuscarTipo.addItem(desc);
        }
    }

    private void clearForm() {
        txtCod.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
        chkImportado.setSelected(false);
        cmbTipo.setSelectedIndex(0);

        tabla.clearSelection();
        txtCod.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception ignored) {}
            new JFrameProducto();
        });
    }

}
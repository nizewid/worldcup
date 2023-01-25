/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forms;

import datos.Datos;
import clases.Equipo;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author jgfs_
 */
public class ListadoForm extends JFrame implements ActionListener, ChangeListener {

    private Datos datos;
    private String accion;
    private JLabel lbl_equipos;
    private JComboBox com_listadoEquipos;
    private JButton btn_seleccion, btn_volver;
    private JPanel pan_etiqueta, pan_botones;

    public ListadoForm(String accion, Datos datos) {
        //Datos
        super(accion + " Equipos");
        this.accion = accion;
        this.datos = datos;

        // propiedades
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setPreferredSize(new Dimension(400, 200));
        setLocationRelativeTo(null); 
        this.setIconImage(LoginForm.icono.getImage());
setResizable(false);
        //elementos
        pan_etiqueta = new JPanel(new FlowLayout());
        pan_botones = new JPanel(new FlowLayout());
        lbl_equipos = new JLabel("Seleccion:");
        com_listadoEquipos = new JComboBox(Datos.listadoEquiposString().toArray());
        btn_volver = new JButton("Volver");
        btn_seleccion = new JButton(accion);
        btn_seleccion.addActionListener(this);
        btn_volver.addActionListener(this);
        
        pan_etiqueta.setBorder(BorderFactory.createTitledBorder("Seleccione un equipo a " + accion));
        btn_seleccion.setEnabled(Datos.hayEquiposRegistrados());
        //Distribucion;
        pan_etiqueta.add(lbl_equipos);
        pan_etiqueta.add(com_listadoEquipos);
        pan_botones.add(btn_seleccion);
        pan_botones.add(btn_volver);

        this.add(pan_etiqueta, BorderLayout.NORTH);
        this.add(pan_botones, BorderLayout.CENTER);

        pack();
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_volver) {
            this.dispose();
        }
        if (e.getSource() == btn_seleccion) {

            if (btn_seleccion.getText().equalsIgnoreCase("editar")) {

                try {
                    System.out.println(com_listadoEquipos.getItemCount());
                    int seleccion = com_listadoEquipos.getSelectedIndex();
                    Equipo equipo = Datos.obtenerE(seleccion);
                    this.dispose();
                    Gestor.cambiarTxa_areaPrincipal(equipo.toString());

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
            if (btn_seleccion.getText().equalsIgnoreCase("eliminar")) {
                int seleccion = com_listadoEquipos.getSelectedIndex();
                Equipo equipo = Datos.obtenerE(seleccion);
                Datos.eliminarE(equipo);
                JOptionPane.showMessageDialog(this, "Eliminado Correctamente", "eliminado " + equipo.getNombreEquipo(), 1);
                Gestor.actualizarValores();
                this.dispose();
            }

        }
    }

    @Override
    public void stateChanged(ChangeEvent che) {
        int cantidad = com_listadoEquipos.getItemCount();
        if (cantidad > 0) {
            btn_seleccion.setEnabled(true);
        }
    }
}

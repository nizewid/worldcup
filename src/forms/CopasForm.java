/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forms;

import datos.Datos;
import clases.Copa;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

import javax.swing.*;

/**
 *
 * @author jgfs_
 */
public class CopasForm extends JFrame implements ActionListener {

    private Datos datos;
    private ImageIcon copaImg;
    private String nombreCopa;
    private JPanel panelUno, panelDos, panelTres;
    private JLabel lbl_nombre, lbl_imagenCopa;
    private JButton btn_agregarLista, btn_quitarLista, btn_agregarCopa, btn_volver;
    private ArrayList<String> equiposArray;
    private ArrayList<String> equipos_seleccionados;
    private JList<String> listaEquipos;
    private JList<String> equiposSeleccionados;
    private JTextField txt_nombreCopa;

    public CopasForm(String nombre, Datos datos) {
        super(nombre);
        this.nombreCopa = nombre;
        this.datos = datos;
        this.equiposArray = Datos.listadoEquiposString();

        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setIconImage(LoginForm.icono.getImage());
        setResizable(false);

        cargarPanelUno();
        cargarPanelDos();
        cargarPanelTres();

        add(panelUno, BorderLayout.NORTH);
        add(panelDos, BorderLayout.CENTER);
        add(panelTres, BorderLayout.SOUTH);

        setVisible(true);

    }

    private void cargarPanelUno() {

        panelUno = new JPanel();
        copaImg = new ImageIcon("src/img/copa-mundial.png");
        lbl_nombre = new JLabel("Nombre Copa:");
        lbl_imagenCopa = new JLabel();
        txt_nombreCopa = new JTextField(this.nombreCopa, 20);

        panelUno.setBorder(BorderFactory.createTitledBorder("Gestion de Copas"));

        txt_nombreCopa.setEditable(false);
        lbl_imagenCopa.setIcon(copaImg);

        panelUno.add(lbl_imagenCopa);
        panelUno.add(lbl_nombre);
        panelUno.add(txt_nombreCopa);
    }

    private void cargarPanelDos() {
        panelDos = new JPanel();
        GridLayout grid = new GridLayout(1, 3, 2, 2);
        listaEquipos = new JList<>();
        equiposSeleccionados = new JList();
        JPanel botones = new JPanel();
        BoxLayout boxVertical = new BoxLayout(botones, BoxLayout.PAGE_AXIS);
        equipos_seleccionados = new ArrayList<>();

        JScrollPane scrollSeleccionados = new JScrollPane(equiposSeleccionados);
        JScrollPane scrollListado = new JScrollPane(listaEquipos);

        btn_quitarLista = new JButton("<");
        btn_agregarLista = new JButton(">");

        btn_agregarLista.setAlignmentX(0.5f);
        btn_quitarLista.setAlignmentX(0.5f);

        panelDos.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        panelDos.setLayout(grid);

        listaEquipos.setListData(equiposArray.toArray(String[]::new));

        botones.setLayout(boxVertical);

        btn_agregarLista.addActionListener(this);
        btn_quitarLista.addActionListener(this);

        botones.add(btn_agregarLista);
        botones.add(btn_quitarLista);

        panelDos.add(scrollListado);
        panelDos.add(botones);
        panelDos.add(scrollSeleccionados);

    }

    private void cargarPanelTres() {

        panelTres = new JPanel();
        btn_agregarCopa = new JButton("Agregar copa");
        btn_agregarCopa.addActionListener(this);
        btn_volver = new JButton("Volver");
        btn_volver.addActionListener(this);

        panelTres.add(btn_agregarCopa);
        panelTres.add(btn_volver);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_volver) {
            this.dispose();
        }
        if (e.getSource() == btn_agregarLista) {
            try {
                String seleccionado = listaEquipos.getSelectedValue();
                removerDelArray(seleccionado);
                //System.out.println("El tamaño de equipos seleccionado es " + equipos_seleccionados.size() + "\n" + " El tamaño de listado equipos es de " + equiposArray.size());
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        if (e.getSource() == btn_quitarLista) {
            try {
                String seleccionado = equiposSeleccionados.getSelectedValue();
                removerDelArraySeleccionado(seleccionado);
                // System.out.println("El tamaño de equipos seleccionado es " + equipos_seleccionados.size() + "\n" + " El tamaño de listado equipos es de " + equiposArray.size());
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            //   equiposSeleccionados.setListData(equipos_seleccionados);
        }
        if (e.getSource() == btn_agregarCopa) {
            JFileChooser jfc = new JFileChooser();
            jfc.setCurrentDirectory(new File("."));
            jfc.showSaveDialog(this);
            try {
                //lo agrego al programa
                Copa c = new Copa(getNombreCopa(), equipos_seleccionados.size());
                datos.agregarCopa(c);
                //lo escribo en un fichero
                File archivo = jfc.getSelectedFile();
                try ( FileWriter escribir = new FileWriter(archivo)) {
                    escribir.write(c.getNombreCopa());
                    escribir.write(leerEquiposSeleccionados(equipos_seleccionados));
                    escribir.write("Total Equipos: " + String.valueOf(c.getNumeroEquipos()));
                }

                this.dispose();
            } catch (IOException exeption) {
                System.out.println(exeption.getMessage());
            }
            Gestor.actualizarValores();

        }
    }

    private void removerDelArray(String seleccionado) {
        equiposArray.remove(seleccionado);
        listaEquipos.setListData(equiposArray.toArray(String[]::new));
        equipos_seleccionados.add(seleccionado);
        equiposSeleccionados.setListData(equipos_seleccionados.toArray(String[]::new));
    }

    private void removerDelArraySeleccionado(String seleccionado) {
        equipos_seleccionados.remove(seleccionado);
        equiposArray.add(seleccionado);
        equiposSeleccionados.setListData(equipos_seleccionados.toArray(String[]::new));
        listaEquipos.setListData(equiposArray.toArray(String[]::new));
        // listaEquipos.setListData(equiposArray.toArray(String[]::new));
    }

    public String getNombreCopa() {
        return nombreCopa;
    }

    public String leerEquiposSeleccionados(ArrayList<String> equiposSel) {
        String cadena = "Equipos: \n";
        for (int i = 0; i < equiposSel.size(); i++) {
            cadena += (i + 1) + equiposSel.get(i) + "\n";
        }
        return cadena;
    }

}

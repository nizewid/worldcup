/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forms;

import datos.Datos;
import clases.Equipo;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author jgfs_
 */
public class EquiposForm extends JFrame implements ChangeListener, ActionListener {

    JPanel panelSuperior, panelMedio, panelInferior, panel_logo;
    JLabel lbl_equipo, lbl_localidad, lbl_patrocinador, lbl_plantilla, lbl_colorUniformeCasa, lbl_colorUniformeVisitante, lbl_valorSlider, lbl_cargar;
    Datos datos;
    JTextField txt_nombre, txt_subirLogo;
    JCheckBox chk_logo;
    Equipo nuevoEquipo;
    JComboBox com_localidad, com_uniformeCasa, com_uniformeVisit;
    JButton btn_agregar, btn_borrar, btn_volver, btn_subirLogo;
    ButtonGroup grupo_patrocinador;
    JRadioButton rab_si, rab_no;
    JSlider sli_plantilla;

    public EquiposForm(String nombre, Datos datos) {
        //Propiedades
        super("Gestion de Equipos");
        this.datos = datos;
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(450, 520);
        setResizable(false);

        //Creando los paneles dentro del form
        crearPanelSuperior();
        crearPanelMedio();
        crearPanelInferior();

        this.setIconImage(LoginForm.icono.getImage());
        //Asignacion 
        this.add(panelSuperior, BorderLayout.NORTH);
        this.add(panelMedio, BorderLayout.CENTER);
        this.add(panelInferior, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void crearPanelSuperior() {
        GridLayout grid = new GridLayout(0, 2, 15, 15);
        panel_logo = new JPanel();
        panelSuperior = new JPanel();
        lbl_equipo = new JLabel("Nombre del equipo:");
        lbl_colorUniformeCasa = new JLabel("Uniforme Casa:");
        lbl_colorUniformeVisitante = new JLabel("Uniforme Visitante: ");
        lbl_localidad = new JLabel("Comunidad Autónoma:");
        lbl_cargar = new JLabel("Subir logo");
        chk_logo = new JCheckBox();
        txt_subirLogo = new JTextField("Logo.jpg");
        txt_nombre = new JTextField(20);
        com_localidad = new JComboBox(Datos.ARR_LOCALIDADES);
        com_uniformeCasa = new JComboBox(Datos.ARR_COLORES);
        com_uniformeVisit = new JComboBox(Datos.ARR_COLORES);
        btn_subirLogo = new JButton("Subir logo");

        panelSuperior.setLayout(grid);
        panelSuperior.setBorder(BorderFactory.createTitledBorder("Datos Principales:"));

        txt_subirLogo.setEditable(false);
        chk_logo.addChangeListener(this);
        //chk_logo.addActionListener(this);

        btn_subirLogo.addActionListener(this);
        btn_subirLogo.setEnabled(false);

        //Distribucion
        panelSuperior.add(lbl_equipo);
        panelSuperior.add(txt_nombre);
        panelSuperior.add(lbl_localidad);
        panelSuperior.add(com_localidad);
        panelSuperior.add(lbl_colorUniformeCasa);
        panelSuperior.add(com_uniformeCasa);
        panelSuperior.add(lbl_colorUniformeVisitante);
        panelSuperior.add(com_uniformeVisit);
        panelSuperior.add(panel_logo);
        panelSuperior.add(txt_subirLogo); // 

        panel_logo.add(lbl_cargar);
        panel_logo.add(chk_logo);
        panel_logo.add(btn_subirLogo);

    }

    private void crearPanelInferior() {
        panelInferior = new JPanel();
        btn_agregar = new JButton("Agregar");
        btn_borrar = new JButton("Borrar");
        btn_volver = new JButton("Volver");

        btn_agregar.addActionListener(this);
        btn_borrar.addActionListener(this);
        btn_volver.addActionListener(this);

        panelInferior.add(btn_agregar);
        panelInferior.add(btn_borrar);
        panelInferior.add(btn_volver);
    }

    private void crearPanelMedio() {
        //Instancias a usar
        panelMedio = new JPanel();
        BoxLayout panelMedioLayout = new BoxLayout(panelMedio, BoxLayout.PAGE_AXIS);

        lbl_plantilla = new JLabel("Jugadores en Plantilla: ");
        lbl_patrocinador = new JLabel("Patrocinador");
        lbl_valorSlider = new JLabel("15"); // es el valor por defecto del slider
        rab_si = new JRadioButton();
        rab_no = new JRadioButton();
        grupo_patrocinador = new ButtonGroup();
        JPanel panel_patrocinador = new JPanel();
        BoxLayout box_patrocinador = new BoxLayout(panel_patrocinador, BoxLayout.LINE_AXIS);
        JPanel panel_slider = new JPanel();
        sli_plantilla = new JSlider(SwingConstants.HORIZONTAL, 0, 30, 15);

        panelMedio.setLayout(panelMedioLayout);
        panelMedio.setBorder(BorderFactory.createTitledBorder("Detalles"));

        rab_si.setText("Si");
        rab_no.setText("No");

        Font font = lbl_valorSlider.getFont();
        lbl_valorSlider.setFont(font.deriveFont(20.0f));

        grupo_patrocinador.add(rab_si);
        grupo_patrocinador.add(rab_no);
        panel_patrocinador.setLayout(box_patrocinador);
        panel_patrocinador.add(lbl_patrocinador);
        panel_patrocinador.add(rab_si);
        panel_patrocinador.add(rab_no);
        panelMedio.add(panel_patrocinador);

        //slider 
        sli_plantilla.setMinorTickSpacing(10);
        sli_plantilla.setMajorTickSpacing(30);
        sli_plantilla.setPaintTicks(true);
        sli_plantilla.setPaintLabels(true);
        sli_plantilla.addChangeListener(this);

        panelMedio.add(sli_plantilla);
        panelMedio.add(panel_slider);
        panel_slider.add(lbl_plantilla);
        panel_slider.add(lbl_valorSlider);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_subirLogo) {
            dialogoAbrir();
        }
        if (e.getSource() == btn_agregar) {
            if (validar() == true) {
                dialogoGuardar();
                this.dispose();
            }
        }
        if (e.getSource() == btn_volver) {
            this.dispose();
        }
        if (e.getSource() == btn_borrar) {
            txt_nombre.setText("");
            com_localidad.setSelectedIndex(0);
            com_uniformeCasa.setSelectedIndex(0);
            com_uniformeVisit.setSelectedIndex(0);
            txt_subirLogo.setText("Logo.jpg");
            sli_plantilla.setValue(15);
        }

    }

    @Override
    public void stateChanged(ChangeEvent ce) {
        int valor = sli_plantilla.getValue();
        lbl_valorSlider.setText(String.valueOf(valor));

        if (chk_logo.isSelected()) {
            btn_subirLogo.setEnabled(true);
            txt_subirLogo.setEditable(true);
        } else {
            btn_subirLogo.setEnabled(false);
            txt_subirLogo.setEditable(false);
        }

    }

    private void dialogoAbrir() {
        JFileChooser dlgAbrir = new JFileChooser();
        int valor = dlgAbrir.showOpenDialog(null);
        if (valor == JFileChooser.APPROVE_OPTION) {
            File archivoSel = dlgAbrir.getSelectedFile();
            txt_subirLogo.setText(archivoSel.getName());
        }

    }

    private void dialogoGuardar() {
        JFileChooser fc = new JFileChooser();

        fc.setCurrentDirectory(new File("."));//Establece el directorio predetermnado en el directorio del proyecto

        FileNameExtensionFilter filtro = new FileNameExtensionFilter("archivos de texto .txt", "txt", "TXT");
        fc.setFileFilter(filtro);
        int valor = fc.showSaveDialog(this);
        if (valor == JFileChooser.APPROVE_OPTION) {

            File f = fc.getSelectedFile();
            if (!f.getName().endsWith(".txt")) {
                f = new File(f.getAbsolutePath() + ".txt");
            }
            try {
                nuevoEquipo = new Equipo(
                        txt_nombre.getText(),
                        com_localidad.getSelectedItem().toString(),
                        com_uniformeCasa.getSelectedItem().toString(),
                        com_uniformeVisit.getSelectedItem().toString(),
                        txt_subirLogo.getText(),
                        false,
                        Integer.parseInt(lbl_valorSlider.getText()));
                datos.agregarEquipo(nuevoEquipo);
                FileWriter fw = new FileWriter(f);
                fw.write(nuevoEquipo.getNombreEquipo() + "\n");
                fw.write(nuevoEquipo.getCa() + "\n");
                fw.write(nuevoEquipo.getUniformeCasa() + "\n");
                fw.write(nuevoEquipo.getUniformeVisitante() + "\n");
                fw.write(nuevoEquipo.getLogo() + "\n");
                fw.write(nuevoEquipo.isPatrocinador() + "\n");
                fw.write(String.valueOf(nuevoEquipo.getJugadores()));
                fw.close();
            } catch (IOException | NumberFormatException e) {
                System.out.println(e.getMessage());
            }
            Gestor.actualizarValores();

        }
    }

    private boolean validar() {
        if (txt_nombre.getText().length() < 2 || txt_nombre.getText() == null) {
            JOptionPane.showMessageDialog(this, "Revisar el campo Nombre");
            return false;
        }
        if (grupo_patrocinador.getSelection() == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar si tiene o no patrocinador");
            return false;
        }
        return true;
    }

}

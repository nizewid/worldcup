/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forms;

import componentes.JmenuItemPersonalizado;
import datos.Datos;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.awt.event.InputEvent.ALT_DOWN_MASK;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.help.HelpSetException;
import javax.swing.*;

/**
 *
 * @author jgfs_
 */
public class Gestor extends JFrame implements ActionListener {
    
    public ImageIcon icono;
    private final Datos datos;
    private ImageIcon copa, historial, edit, imgalta, imgbaja, imgedit;
    private ImageIcon img_nueva, img_copa, img_equipo, img_salir, img_verCopas, img_editarEquipos, img_programador, img_edicion;
    private JMenuBar barra;
    private JMenu menuInicio, menuVer, menu_nueva, menuAyuda;
    private JMenuItem jmi_autor, jmi_nuevaCopa, jmi_nuevoEquipo, jmi_salir, jmi_verCopas, jmi_verEquipos, jmi_activarEdicion;
    private JToolBar barSuperior, barIzquierda;
    private JButton btnNuevaCopa, btnVerCopas, btnEditar, btnNuevoEquipo, btnEditarEqupo, btnEliminarEquipo;
    private JPanel panelInformacion, panelPrincipal;
    private static JTextArea txa_areaPrincipal;
    private JLabel lblCopasRegistradas, lblEquipos;
    private JScrollPane scroll;
    private static JLabel lbl_valorCopasRegistradas, lbl_valorEquiposRegistrados;
    private JmenuItemPersonalizado jmi_personalizado;
    
    public Gestor(String usuario) {
        //Propiedades del Formulario
        super("Gestor de copas de " + usuario);
        this.datos = new Datos();
        setSize(800, 600);
        setLocationRelativeTo(null);
        BorderLayout admin = new BorderLayout();
        setLayout(admin);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLookAndFeel();
        setResizable(false);
        //Metodos Para cargar Elementos
        crearPanelPrincipal();
        cargarImagenes();
        crearMenu();
        crearJTool();
        crearPanelInformacion();

        //Distribuirlos en el JFrame
        add(barSuperior, BorderLayout.NORTH);
        add(barIzquierda, BorderLayout.WEST);
        add(panelPrincipal, BorderLayout.CENTER);
        add(panelInformacion, BorderLayout.SOUTH);
        setVisible(true);
    }
    
    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void crearMenu() {
        //Crear la barra 
        barra = new JMenuBar();

        /**
         * --- INICIO ---
         */
        menuInicio = new JMenu("Inicio");
        menu_nueva = new JMenu("Nueva");
        
        jmi_nuevaCopa = new JMenuItem("Copa", img_copa);
        jmi_nuevoEquipo = new JMenuItem("Equipo", img_equipo);
        jmi_salir = new JMenuItem("Salir", img_salir);
        
        menuInicio.setMnemonic(KeyEvent.VK_I);
        menu_nueva.setMnemonic(KeyEvent.VK_N);
        jmi_salir.setMnemonic(KeyEvent.VK_S);
        menu_nueva.setIcon(img_nueva);
        
        KeyStroke ks_salir = KeyStroke.getKeyStroke(KeyEvent.VK_S, ALT_DOWN_MASK);
        jmi_salir.setAccelerator(ks_salir);
        
        jmi_salir.addActionListener(this);
        // *************** Agregar los AccionListener de NuevaCopa y NuevoEquipo;

        menu_nueva.add(jmi_nuevaCopa);
        menu_nueva.add(jmi_nuevoEquipo);
        menuInicio.add(menu_nueva);
        menuInicio.addSeparator();
        menuInicio.add(jmi_salir);

        /**
         * Ver---------
         */
        menuVer = new JMenu("Ver");
        jmi_verCopas = new JMenuItem("Ver copas", img_verCopas);
        jmi_verEquipos = new JMenuItem("Edicion de equipos", img_editarEquipos);
        jmi_activarEdicion = new JMenuItem("Edicion", img_edicion);
        
        menuVer.setMnemonic(KeyEvent.VK_V);
        jmi_verCopas.setMnemonic(KeyEvent.VK_C);
        jmi_verEquipos.setMnemonic(KeyEvent.VK_E);
        
        KeyStroke ks_verCopas = KeyStroke.getKeyStroke(KeyEvent.VK_C, ALT_DOWN_MASK);
        KeyStroke ks_verEquipos = KeyStroke.getKeyStroke(KeyEvent.VK_E, ALT_DOWN_MASK);
        
        jmi_verEquipos.setAccelerator(ks_verEquipos);
        jmi_verCopas.setAccelerator(ks_verCopas);
        
        jmi_verEquipos.addActionListener(this);
        jmi_verCopas.addActionListener(this);
        
        menuVer.add(jmi_verCopas);
        menuVer.add(jmi_verEquipos);
        menuVer.addSeparator();
        menuVer.add(jmi_activarEdicion);

        /**
         * Ayuda------
         */
        menuAyuda = new JMenu("Acerca de");
        jmi_autor = new JMenuItem("Autor", img_programador);
        jmi_personalizado = new JmenuItemPersonalizado("JavaHelp");
        jmi_personalizado.setMnemonic(KeyEvent.VK_F1);
        
        menuAyuda.setMnemonic(KeyEvent.VK_A);
        jmi_autor.setMnemonic(KeyEvent.VK_U);
        
        KeyStroke ks_ayuda = KeyStroke.getKeyStroke(KeyEvent.VK_Y, ALT_DOWN_MASK);
        
        jmi_autor.setAccelerator(ks_ayuda);
        
        jmi_autor.addActionListener(this);
        jmi_personalizado.addActionListener(this);
        
        verLaAyuda();
        
        menuAyuda.add(jmi_autor);
        menuAyuda.addSeparator();
        menuAyuda.add(jmi_personalizado);
        /**
         * ------ Distribución en la Barra
         */
        barra.add(menuInicio);
        
        barra.add(menuVer);
        barra.add(Box.createHorizontalGlue()); //Box Para separarlo y ponerlo al final
        barra.add(menuAyuda);
        
        this.setJMenuBar(barra);
    }
    
    private void crearJTool() {
        barSuperior = new JToolBar();

        /**
         * ***** BARRA Superior ----------
         */
        btnNuevaCopa = new JButton("Nueva Copa", copa);
        btnVerCopas = new JButton("Ver copas", historial);
        btnEditar = new JButton("Editar", edit);
        
        btnNuevaCopa.addActionListener(this);
        btnVerCopas.addActionListener(this);
        btnEditar.addActionListener(this);
        
        barSuperior.setFloatable(false);
        btnNuevaCopa.setActionCommand("nuevacopa");
        btnVerCopas.setActionCommand("vercopas");
        btnEditar.setActionCommand("editar");

        //Distribucion
        barSuperior.add(btnNuevaCopa);
        barSuperior.addSeparator();
        barSuperior.add(btnVerCopas);
        barSuperior.addSeparator();
        barSuperior.add(btnEditar);
        barSuperior.addSeparator();

        //Barra Izquierda
        barIzquierda = new JToolBar(1);
        btnNuevoEquipo = new JButton("Nuevo Equipo", imgalta);
        btnEditarEqupo = new JButton("Editar equipo", imgedit);
        btnEliminarEquipo = new JButton("Eliminar Equipo", imgbaja);
        
        barIzquierda.setFloatable(false);
        btnNuevoEquipo.setHorizontalTextPosition(SwingConstants.CENTER);
        btnNuevoEquipo.setVerticalTextPosition(SwingConstants.TOP);
        btnEditarEqupo.setHorizontalTextPosition(SwingConstants.CENTER);
        btnEditarEqupo.setVerticalTextPosition(SwingConstants.TOP);
        btnEliminarEquipo.setHorizontalTextPosition(SwingConstants.CENTER);
        btnEliminarEquipo.setVerticalTextPosition(SwingConstants.TOP);
        
        btnNuevoEquipo.addActionListener(this);
        btnEliminarEquipo.addActionListener(this);
        btnEditarEqupo.addActionListener(this);
        
        btnNuevoEquipo.setActionCommand("agregarequipo");
        btnEliminarEquipo.setActionCommand("eliminarequipo");
        btnEditarEqupo.setActionCommand("editarequipo");

        //Distribucion
        barIzquierda.add(btnNuevoEquipo);
        barIzquierda.addSeparator();
        barIzquierda.add(btnEditarEqupo);
        barIzquierda.addSeparator();
        barIzquierda.add(btnEliminarEquipo);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String valor;
        if (e.getActionCommand().equalsIgnoreCase("agregarequipo") || e.getSource() == jmi_nuevoEquipo) {
            new EquiposForm("Agregar Equipo", datos);
            
        }
        if (e.getActionCommand().equalsIgnoreCase("nuevacopa") || e.getSource() == jmi_nuevaCopa) {
            valor = JOptionPane.showInputDialog(this, "Indique el nombre de la copa:", "Registro de Copa", 3);
            if (valor != null) {
                //Filtro para validar el nombre de la copa // se le puede agregar un regex
                if (valor.isEmpty() || valor.isBlank()) {
                    JOptionPane.showMessageDialog(this, "Debe indicar el nombre de la copa", "Error", 2);
                } else {
                    new CopasForm(valor, datos);
                }
            }
            
        }
        if (e.getActionCommand().equalsIgnoreCase("editarequipo") || e.getSource() == jmi_verEquipos) {
            String accion = "editar";
            new ListadoForm(accion, datos);
        }
        if (e.getActionCommand().equalsIgnoreCase("eliminarequipo")) {
            String accion = "eliminar";
            new ListadoForm(accion, datos);
            
        }
        if (e.getActionCommand().equalsIgnoreCase("vercopas") || e.getSource() == jmi_verCopas) {
            Gestor.cambiarTxa_areaPrincipal(datos.listadoCopas());
        }
        if (e.getActionCommand().equalsIgnoreCase("editar")) {
            txa_areaPrincipal.setEditable(true);
            txa_areaPrincipal.setBackground(Color.WHITE);
        }
        if (e.getSource() == jmi_salir) {
            System.exit(0);
        }
        if (e.getSource() == jmi_autor) {
            JOptionPane.showMessageDialog(this, "Este programa ha sido hecho por: \n José Flores \n Alumno de IES N1 2022 \n Asignatura: Desarrollo de interfaces ", "Autor: Jose Flores", 1);
        }
        
    }
    
    private void crearPanelInformacion() {
        panelInformacion = new JPanel();
        BoxLayout box = new BoxLayout(panelInformacion, BoxLayout.LINE_AXIS);
        lblCopasRegistradas = new JLabel("Copas registradas: ");
        lblEquipos = new JLabel(" Cantidad de equipos registrados: ");
        lbl_valorCopasRegistradas = new JLabel(String.valueOf(datos.getCopasRegistradas()));
        lbl_valorEquiposRegistrados = new JLabel(String.valueOf(datos.getEquiposRegistrados()));
        
        lbl_valorCopasRegistradas.setForeground(Color.RED);
        lbl_valorEquiposRegistrados.setForeground(Color.RED);
        
        panelInformacion.setLayout(box);

        //Distribucion
        panelInformacion.add(lblCopasRegistradas);
        panelInformacion.add(lbl_valorCopasRegistradas);
        panelInformacion.add(lblEquipos);
        panelInformacion.add(lbl_valorEquiposRegistrados);
        
    }
    
    public static void cambiarTxa_areaPrincipal(String t) {
        txa_areaPrincipal.setText(t);
        txa_areaPrincipal.setEditable(false);
        txa_areaPrincipal.setBackground(Color.LIGHT_GRAY);
        
    }
    
    public static void actualizarValores() {
        //Metodo para actualizar el valor de los JLabelInferiores del panel principal

        //System.out.println(Datos.listadoEquiposString().size());
        //System.out.println(Datos.listadoCopasString().size());
        lbl_valorCopasRegistradas.setText(String.valueOf(Datos.listadoCopasString().size()));
        lbl_valorEquiposRegistrados.setText(String.valueOf(Datos.listadoEquiposString().size()));
    }
    
    private void cargarImagenes() {
        
        copa = new ImageIcon("src/img/copa-mundial.png");
        historial = new ImageIcon("src/img/hourglass.png");
        edit = new ImageIcon("src/img/editar.png");
        imgalta = new ImageIcon("src/img/alta.png");
        imgbaja = new ImageIcon("src/img/baja.png");
        imgedit = new ImageIcon("src/img/worldwide.png");
        img_nueva = new ImageIcon("src/img/nueva.png");
        img_copa = new ImageIcon("src/img/premio-de-futbol.png");
        img_equipo = new ImageIcon("src/img/equipo.png");
        img_salir = new ImageIcon("src/img/tarjeta-roja.png");
        img_verCopas = new ImageIcon("src/img/copaMundial.png");
        img_editarEquipos = new ImageIcon("src/img/camisa.png");
        img_programador = new ImageIcon("src/img/programador.png");
        img_edicion = new ImageIcon("src/img/lapiz.png");
        
        this.setIconImage(LoginForm.icono.getImage());
    }
    
    private void crearPanelPrincipal() {
        
        panelPrincipal = new JPanel(new GridLayout(1, 0));
        txa_areaPrincipal = new JTextArea("\n******Bienvenido al gestor de copas********");
        txa_areaPrincipal.setFont(new Font("Arial", Font.BOLD, 20));
        scroll = new JScrollPane(txa_areaPrincipal);
        
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        txa_areaPrincipal.setLineWrap(rootPaneCheckingEnabled);
        txa_areaPrincipal.setEditable(false);
        txa_areaPrincipal.setBackground(Color.LIGHT_GRAY);
        
        panelPrincipal.add(scroll);
        
    }
    
    private void verLaAyuda() {
        HelpSet helpset = null;
        
        try {
            File fichero = new File("./help/help_set.hs");
            URL hsUrl = fichero.toURI().toURL();
            try {
                helpset = new HelpSet(getClass().getClassLoader(), hsUrl);
            } catch (HelpSetException ex) {
                Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
            }
            HelpBroker hb = helpset.createHelpBroker();
            hb.enableHelpKey(getRootPane(), "gestorcopas", helpset);
            hb.enableHelpOnButton(jmi_personalizado, "gestorcopas", helpset);
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}

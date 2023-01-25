/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forms;

import clases.Usuario;
import java.awt.BorderLayout;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;


/**
 *
 * @author jgfs_
 */
public class LoginForm extends JFrame implements ActionListener {

//Datos para acceder
    Usuario admin = new Usuario("admin", "admin");
//Variables de la clase
    public static ImageIcon icono;
    private boolean acceso;
    private JLabel lblBienvenida, lblUsuario, lblPassword;
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JPanel panelUno, panelAcceso, panelBotones;
    private JButton btnContinuar, btnSalir;

    public LoginForm() {
        //Propiedades del formulario
        super("Menu Principal");
        acceso = false;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(300, 300);
        setLookAndfeel();
        setResizable(false);

        //elementos
        icono = new ImageIcon("src/img/furniture.png");      
        panelUno = new JPanel();
        panelAcceso = new JPanel();
        lblBienvenida = new JLabel("Gestor de copas");
        lblUsuario = new JLabel("Usuario:");
        lblPassword = new JLabel("Password:");
        txtUsuario = new JTextField(20);
        txtPassword = new JPasswordField(20);
        btnContinuar = new JButton("Continuar");
        btnSalir = new JButton("Salir");
        panelBotones = new JPanel();
        lblBienvenida.setFont(new Font("serif", Font.BOLD, 15));

        panelAcceso.setBorder(BorderFactory.createTitledBorder("Datos de Acceso:"));
        lblBienvenida.setIcon(icono);
        this.setIconImage(icono.getImage());
        //Listeners
        btnContinuar.addActionListener(this);
        btnContinuar.setActionCommand("continuar");
        btnSalir.setActionCommand("salir");
        btnSalir.addActionListener(this);

        //Distribucion 
        panelUno.add(lblBienvenida);
        panelAcceso.add(lblUsuario);
        panelAcceso.add(txtUsuario);
        panelAcceso.add(lblPassword);
        panelAcceso.add(txtPassword);
        panelBotones.add(btnContinuar);
        panelBotones.add(btnSalir);

        add(panelUno, BorderLayout.NORTH);
        add(panelAcceso, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void setLookAndfeel() {

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() != null) {
            if (e.getActionCommand().equals("salir")) {
                System.exit(0);
            }
            if (e.getActionCommand().equals("continuar")) {
                String nombre = txtUsuario.getText();
                String contra = txtPassword.getText();
                if ((txtUsuario.getText().length() == 0) || (txtPassword.getText().length() == 0)) {
                    JOptionPane.showMessageDialog(null, "el campo usuario o contraseña esta vacio");
                } else {
                    verificarUsuario(nombre, contra);
                    if (acceso == true) {
                        this.setVisible(false);
                        new Gestor(nombre);
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Credenciales Incorrectas");
                        System.out.println("ERROR");
                    }
                }

            }
        }
    }

    private boolean verificarUsuario(String usuario, String contrasena) {

//        Usuario u1 = new Usuario(usuario, contrasena);
        if ((usuario.equals(admin.getNombre())) && (contrasena.equals(admin.getContrasena()))) {
            acceso = true;
        } else {

            acceso = false;
        }
        return acceso;
    }



}

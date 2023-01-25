/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

/**
 *
 * @author jgfs_
 */
public class Usuario {
    private String nombre;
    private String contrasena;
    
    public Usuario(String nombre, String contrasena){
        this.nombre=nombre;
        this.contrasena=contrasena;
    }
    public String getNombre(){
        return this.nombre;
    }
    public String getContrasena(){
        return this.contrasena;
    }
}

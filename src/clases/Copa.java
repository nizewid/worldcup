/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

/**
 *
 * @author jgfs_
 */
public class Copa {

    private String nombre;
    private int numeroEquipos;

    public Copa(String nombre, int equipos) {
        this.nombre = nombre;
        this.numeroEquipos = equipos;
    }

    public int getNumeroEquipos() {
        return this.numeroEquipos;
    }

    public String getNombreCopa() {
        return this.nombre;
    }

    public void setNumeroEquipos(int num) {
        this.numeroEquipos = num;
    }

    public void setNombreCopa(String nom) {
        this.nombre = nom;
    }

}

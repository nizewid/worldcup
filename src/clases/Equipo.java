/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

/**
 *
 * @author jgfs_
 */
public class Equipo {

    private String nombreEquipo, ca, uniformeCasa, uniformeVisitante, logo;
    int jugadores;
    private boolean patrocinador;

    public Equipo(String nombre, String ca, String uniformeCasa, String uniformeVisitante, String logo, boolean patrocinador, int jugadores) {
        this.nombreEquipo = nombre;
        this.ca = ca;
        this.uniformeCasa = uniformeCasa;
        this.uniformeVisitante = uniformeVisitante;
        this.logo = logo;
        this.patrocinador = patrocinador;
        this.jugadores = jugadores;

    }

    public Equipo() {
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public String getCa() {
        return ca;
    }

    public void setCa(String ca) {
        this.ca = ca;
    }

    public String getUniformeCasa() {
        return uniformeCasa;
    }

    public void setUniformeCasa(String uniformeCasa) {
        this.uniformeCasa = uniformeCasa;
    }

    public String getUniformeVisitante() {
        return uniformeVisitante;
    }

    public void setUniformeVisitante(String uniformeVisitante) {
        this.uniformeVisitante = uniformeVisitante;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getJugadores() {
        return jugadores;
    }

    public void setJugadores(int jugadores) {
        this.jugadores = jugadores;
    }

    public boolean isPatrocinador() {
        return patrocinador;
    }

    public void setPatrocinador(boolean patrocinador) {
        this.patrocinador = patrocinador;
    }

    public String tienePatrocinador(boolean f) {
        String valor;
        if (f == true) {
            valor = "si";
        } else {
            valor = "no";
        }
        return valor;

    }

    @Override
    public String toString() {
        String a = "\n";
        a += "Nombre del Equipo: ";
        a += nombreEquipo + " \n";
        a += "Comunidad Autonoma: ";
        a += ca + " \n";
        a += "Color uniforme casa: ";
        a += uniformeCasa + " \n";
        a += "Color uniforme Visitante: ";
        a += uniformeVisitante + " \n";
        a += "URL del logo: ";
        a += logo + " \n";
        a += "Cantidad de Jugadores: ";
        a += jugadores + " \n";
        a += "Tiene Patrocinador: ";
        a += patrocinador + " \n";

        return a;
    }

}

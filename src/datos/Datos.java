/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import clases.Copa;
import clases.Equipo;
import java.util.ArrayList;

/**
 *
 * @author jgfs_
 */
public class Datos {

    protected static ArrayList<Copa> copasArray;
    protected static ArrayList<Equipo> equiposArray;
    public final static String[] ARR_LOCALIDADES = {"Andalucía", "Aragón", "Canarias", "Cantabria", "Castilla y León", "Castilla-La Mancha", "Cataluña", "Ceuta", "Comunidad Valenciana", "Comunidad de Madrid", "Extremadura", "Galicia", "Islas Baleares", "La Rioja", "Melilla", "Navarra", "País Vasco", "Principado de Asturias", "Región de Murcia"};
    public final static String[] ARR_COLORES = {"ROJO", "AZUL", "VERDE", "NEGRO", "BLANCO", "AMARILLO", "VERDE", "ROJO/AZUL", "VERDE/AZUL"};

    public Datos() {
        this.equiposArray = new ArrayList<>();
        this.copasArray = new ArrayList<>();
    }

    public void agregarEquipo(Equipo equipo) {
        equiposArray.add(equipo);
    }

    public void agregarCopa(Copa copa) {
        copasArray.add(copa);
    }

    public static ArrayList<String> listadoEquiposString() {
        ArrayList<String> EquipoNombresArray = new ArrayList<>();
        for (int i = 0; i < equiposArray.size(); i++) {
            EquipoNombresArray.add(equiposArray.get(i).getNombreEquipo());
        }
        return EquipoNombresArray;

    }

    public int getEquiposRegistrados() {
        return equiposArray.size();
    }

    public static ArrayList<String> listadoCopasString() {
        ArrayList<String> CopasNombreArray = new ArrayList<>();
        for (int i = 0; i < copasArray.size(); i++) {
            CopasNombreArray.add(copasArray.get(i).getNombreCopa());
        }
        return CopasNombreArray;
    }

    public int getCopasRegistradas() {
        return equiposArray.size();
    }

    public static Equipo obtenerE(int pos) {
        Equipo nro = equiposArray.get(pos);
        return nro;
    }

    public static boolean eliminarE(Equipo equipo) {
        boolean accion;
        if (equiposArray.remove(equipo)) {
            accion = true;
        } else {
            accion = false;
        }
        return accion;
    }

    public static boolean hayEquiposRegistrados() {
        if (equiposArray.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public String listadoCopas() {
        String a = "Copas Registradas:";
        for (int i = 0; i < copasArray.size(); i++) {

            a += "Nombre de copa: " + copasArray.get(i).getNombreCopa() + " \n";
            a += "Cantidad de equipos en la copa : " + copasArray.get(i).getNumeroEquipos() + "\n";
            a += "\n";
        }
        return a;
    }

}

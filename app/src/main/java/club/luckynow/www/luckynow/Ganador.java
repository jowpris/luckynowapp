package club.luckynow.www.luckynow;

import java.net.URI;

/**
 * Created by master on 06/09/17.
 */

public class Ganador {


    private String nombre;
    private String foto;
    private int puntos;


    public Ganador(String nombre, String foto, int puntos){
        this.nombre = nombre;
        this.foto = foto;
        this.puntos = puntos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
}



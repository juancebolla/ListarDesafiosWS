package cl.ares.bice.seguridad.sfa.modelo;

import cl.ares.bice.ws.servicio.CoordenadaType;
import cl.ares.bice.ws.servicio.CoordenadasType;
import cl.ares.bice.ws.servicio.DesafioType;

import java.util.List;
import java.util.ArrayList;

public class DesafioGrilla extends DesafioType {


    public DesafioGrilla() {
        super("GRILLA");
        CoordenadasType coord= new CoordenadasType();
        coord.setCoordenada(new ArrayList<CoordenadaType>());
        this.setCoordenadas(coord);
    }

    public class Coordenada {
        char columna;
        int fila;


        public Coordenada(int fila, char columna) {
            this.columna = columna;
            this.fila = fila;
        }

        public char getColumna() {
            return columna;
        }

        public int getFila() {
            return fila;
        }


        public String toString() {
            return "[fila:" + fila + "|columna:" + columna + "]";
        }
    }

    String serieTarjeta;

    public String getIdentificador() {
        return serieTarjeta;
    }

    public void setNumeroSerieTarjeta(String serie) {
        serieTarjeta = serie;
    }


    public void agregarCoordenada(int fila, String columna) {
        agregarCoordenada(new CoordenadaType(fila, columna));
    }

    public void agregarCoordenada(CoordenadaType coord) {
        coordenadas.getCoordenada().add(coord);
    }


    public String toString() {
        StringBuffer buff = new StringBuffer();

        buff.append("[").
                append("serieTarjeta:").
                append(serieTarjeta).
                append("|").
                append("coordenadas:").
                append(coordenadas).
                append("]");

        return buff.toString();
    }
}

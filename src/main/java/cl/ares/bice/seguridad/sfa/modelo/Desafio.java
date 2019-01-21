package cl.ares.bice.seguridad.sfa.modelo;

import cl.ares.bice.ws.servicio.CoordenadasType;

public class Desafio {

    String tipo;
    String identificador;
    protected String grupo;
    protected CoordenadasType coordenadas;


    public Desafio(String tipo) {
        this.tipo = tipo;
    }

    public Desafio(String tipo, String identificador) {
        this.tipo = tipo;
        this.identificador = identificador;
    }

    public String getTipo() {
        return tipo;
    }


    public String getIdentificador() {
        return identificador;
    }


    public String toString() {
        StringBuffer msg = new StringBuffer();
        msg.append("[tipo:").append(tipo).append("|identificador:").append(identificador).append("]");
        return msg.toString();
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setCoordenadas(CoordenadasType coordenadas) {
        this.coordenadas = coordenadas;
    }

    public CoordenadasType getCoordenadas() {
        return coordenadas;
    }
}

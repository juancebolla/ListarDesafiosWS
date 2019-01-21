package cl.ares.bice.seguridad.sfa.ws;

import cl.ares.bice.seguridad.sfa.schema.TipoDesafioType;
import cl.ares.bice.seguridad.sfa.modelo.TipoDispositivo;

public class DesafioBuilder {

    public static String getFromTipoDesafio(String tipoDispositivo) {
        if(tipoDispositivo.equals("GRILLA"))
            return "GRID";
        if(tipoDispositivo.equals("SMS"))
            return "SMS";
        if(tipoDispositivo.equals("SFTKN"))
            return "SFTKN";
        if(tipoDispositivo.equals("TOKEN"))
            return "TOKEN";
        if(tipoDispositivo.equals("PIN_BICE"))
            return "PIN_BICE";
        if(tipoDispositivo.equals("TODOS"))
            return "TODOS";
        else
            return tipoDispositivo;
            //throw new RuntimeException("Tipo de desafio no reconocido. Valor entregado: " + tipoDispositivo);
    }

    public static String getTipoDesafio(String tipoDesafio) {
        if(tipoDesafio.equals("GRID"))
            return "GRILLA";
        if(tipoDesafio.equals("SMS"))
            return "SMS";
        if(tipoDesafio.equals("SFTKN"))
            return "SFTKN";
        if(tipoDesafio.equals("TOKEN"))
            return "TOKEN";
        if(tipoDesafio.equals("TODOS"))
            return "TODOS";
        else 
            throw new RuntimeException("Tipo de desafio no reconozido. Valor entregado: " + tipoDesafio);
    }
}

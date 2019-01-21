package cl.ares.bice.seguridad.sfa.modelo;

import java.util.List;

public class Dispositivos {

    public static Dispositivo buscar(List<Dispositivo> dispositivos, String tipoDispositivo) {
        for(Dispositivo d : dispositivos)
            if(d.getTipo() == tipoDispositivo) return d;
        return null;
    }

}

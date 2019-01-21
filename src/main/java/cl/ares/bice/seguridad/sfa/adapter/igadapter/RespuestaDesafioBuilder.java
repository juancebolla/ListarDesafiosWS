package cl.ares.bice.seguridad.sfa.adapter.igadapter;

import cl.ares.bice.seguridad.sfa.modelo.TipoDispositivo;
import cl.ares.bice.seguridad.sfa.adapter.SfaAdapterException;

public class RespuestaDesafioBuilder {

    private static int CANTIDAD_COORD = 3;

    public static String[] descomponerDesafio(String respuestaDesafio, String t){
        //TipoDispositivo tipoDispositivo = TipoDispositivo.valueOf(t);
        if(t.equals("GRILLA")||t.equals("GRID")){
            return descomponerGrilla(respuestaDesafio);
        }else{//if(t.equals("SFTKN")||t.equals("TOKEN")||t.equals("SMS"))
            return new String[] { respuestaDesafio };
            //default:
                //throw new SfaAdapterException("El tipo de desafio '" + tipoDispositivo.name() +"' no es reconocido");
        }
    }

    private static String[] descomponerGrilla(String respuestaDesafio) {
        if(respuestaDesafio == null || respuestaDesafio.length() == 0)
            throw new SfaAdapterException("El desafio vino vacio");
        if( (respuestaDesafio.length() % CANTIDAD_COORD) != 0)
            throw new SfaAdapterException("La longitud de la respuesta del desafio no es correcto. [Debe ser mod3]");

        String[] resps = new String[CANTIDAD_COORD];
        int numCharXCoord = respuestaDesafio.length() / CANTIDAD_COORD;
        for(int i=0; i < CANTIDAD_COORD; i++){
            int startIdx = i * numCharXCoord;
            int endIdx = (i * numCharXCoord + numCharXCoord);
            resps[i] = respuestaDesafio.substring(startIdx, endIdx );
        }
        return resps;
    }

}

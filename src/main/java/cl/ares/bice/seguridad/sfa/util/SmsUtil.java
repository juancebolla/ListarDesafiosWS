package cl.ares.bice.seguridad.sfa.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Clase Utilitaria para el envio de mensajeria SMS.
 * 
 * Registro de Versiones:
 * <ul>
 * <li> 1.0 17/04/2014 Miguel Garcia H. (TInet): Version inicial.
 * </ul>
 * @author Miguel Garcia H. (TInet)
 * @version 1.0
 */
public class SmsUtil {

    /**
     * Largo de numero celular con formato nacional.
     */
    private static final int LARGO_NRO_NACIONAL = 8;

    /**
     * Largo de numero celular con formato internacional.
     */
    private static final int LARGO_NRO_INTERNACIONAL = 11;

    /**
     * Prefijo de pais (Chile) para formato internacional.
     */
    private static final String PREFIJO_PAIS = "56";

    /**
     * Prefijo de numero de celular.
     */
    private static final String PREFIJO_CEL = "9";

    /**
     * Obtiene el numero telefono con foramto Internacional 569XXXXXXXX.
     * 
     * @param numero numero telefonico.
     * @return numero telefonico con formato internacional.
     */
    public static String formatoNumeroInternacional(long numero) {
        return formatoNumeroInternacional(String.valueOf(numero));
    }

    /**
     * Obtiene el numero telefono con foramto Internacional 569XXXXXXXX.
     * 
     * @param numero numero telefonico.
     * @return numero telefonico con formato internacional.
     */
    public static String formatoNumeroInternacional(String numero) {
        String numeroFromatoInter = "";
        if (numero.length() == LARGO_NRO_NACIONAL) {
            numeroFromatoInter = PREFIJO_PAIS + PREFIJO_CEL + numero;
        }
        return numeroFromatoInter;
    }

    /**
     * Obtiene el numero telefono con foramto Nacional (sin 56+9) XXXXXXXX.
     * 
     * @param numero numero telefonico.
     * @return numero telefonico con formato nacional.
     */
    public static String formatoNumeroNacional(long numero) {
        return formatoNumeroNacional(String.valueOf(numero));
    }

    /**
     * Obtiene el numero telefono con foramto Nacional (sin 56+9) XXXXXXXX.
     * 
     * @param numero numero telefonico.
     * @return numero telefonico con formato nacional.
     */
    public static String formatoNumeroNacional(String numero) {
        String numeroFormatoNacional = "";
        if (numero.length() == LARGO_NRO_INTERNACIONAL) {
            numeroFormatoNacional =
                numero.substring(PREFIJO_CEL.length() + PREFIJO_PAIS.length());
        }
        return numeroFormatoNacional;
    }

    /**
     * Valida un numero telefonico.
     * 
     * @param numero telefono
     * @return indicador si es valido.
     */
    public static boolean esNumeroValido(String numero) {
        boolean esValido = true;
        try {
            Long.valueOf(numero);
            if (numero.length() != LARGO_NRO_NACIONAL
                && numero.length() != LARGO_NRO_INTERNACIONAL) {
                esValido = false;
            }
        } catch (NumberFormatException nfe) {
            esValido = false;
        }
        return esValido;
    }

    /**
     * Genera el mensaje de Excepcion que se debe enviar en caso de error
     * en el uso del servicio SMS.
     * 
     * @param exception Exception generada.
     * @param idServicio Identificador de servicio.
     * @return Mensaje completo de exception.
     */
    public static String generarMensajeException(Exception exception,
        String idServicio) {
        StringBuffer errorBuffer = new StringBuffer();

        if (idServicio != null && idServicio.length() > 0) {
            errorBuffer.append("[ID Servicio SMS: " + idServicio + "]\n");
        }
        errorBuffer.append("[Error: " + exception.getMessage() + "]\n");
        StringWriter errors = new StringWriter();
        exception.printStackTrace(new PrintWriter(errors));
        errorBuffer.append("[StackTrace: " + errors.toString() + "]");
        return errorBuffer.toString();
    }

}

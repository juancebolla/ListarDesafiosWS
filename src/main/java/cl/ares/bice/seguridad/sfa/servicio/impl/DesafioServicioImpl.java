package cl.ares.bice.seguridad.sfa.servicio.impl;

import cl.ares.bice.seguridad.sfa.adapter.igadapter.IdGuardSfaAdapter;
//import cl.ares.bice.seguridad.sfa.adapter.iscadapter.IscSmsAdapter;
import cl.ares.bice.seguridad.sfa.dao.IDesafioServicioDao;
import cl.ares.bice.seguridad.sfa.factory.IDesafioStrategy;
//import cl.ares.bice.seguridad.sfa.factory.SmsDesafioStrategy;
import cl.ares.bice.seguridad.sfa.modelo.Dispositivo;
import cl.ares.bice.seguridad.sfa.modelo.Dispositivos;
import cl.ares.bice.seguridad.sfa.modelo.Enrolamiento;
import cl.ares.bice.seguridad.sfa.servicio.RespuestaDesafioInvalido;
import cl.ares.bice.seguridad.sfa.servicio.SfaException;
import cl.ares.bice.seguridad.sfa.servicio.UsuarioBloqueadoException;
import cl.ares.bice.seguridad.sfa.servicio.UsuarioNoExisteException;
import cl.ares.bice.seguridad.sfa.servicio.UsuarioNoSolicitoDesafioException;
import cl.ares.bice.seguridad.sfa.servicio.UsuarioSinDesafioException;
import cl.ares.bice.seguridad.sfa.servicio.UsuarioSuspendidoException;
import cl.ares.bice.ws.servicio.DesafioType;
import cl.ares.bice.ws.servicio.DesafiosType;
import cl.ares.bice.ws.servicio.MachineSecret;
import cl.ares.bice.ws.servicio.MensajeDesafioType;
import cl.ares.bice.ws.utiles.LoggerUtil;

import com.entrust.identityGuard.userManagement.wsv9.AdminServiceFault;
import com.entrust.identityGuard.userManagement.wsv9.UserInfo;
import com.entrust.identityGuard.userManagement.wsv9.ErrorCode;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;


public class DesafioServicioImpl{// implements IDesafioServicio {

    private static final Logger log = LoggerUtil.getLoggerInput(DesafioServicioImpl.class);

    IdGuardSfaAdapter sfaAdapter = new IdGuardSfaAdapter();
    //IscSmsAdapter smsSrv = new IscSmsAdapter();
    //IDesafioStrategy desafioFactory = new SmsDesafioStrategy();
    IDesafioServicioDao dao = null;

    public void setDao(IDesafioServicioDao dao) {
        this.dao = dao;
    }

    public void setSfaAdapter(IdGuardSfaAdapter sfaAdapter) {
        this.sfaAdapter = sfaAdapter;
    }
    
    public IdGuardSfaAdapter getSfaAdapter(){
        return sfaAdapter;
    }

    /*public void setSmsSrv(IscSmsAdapter smsSrv) {
        this.smsSrv = smsSrv;
    }*/

    /*public void setDesafioFactory(IDesafioStrategy desafioFactory) {
        this.desafioFactory = desafioFactory;
    }*/

    public void autentificar(String usuarioId, String grupo, String respuestaDesafio, String ip, String tipoDispositivo)
            throws UsuarioNoSolicitoDesafioException, RespuestaDesafioInvalido,
            UsuarioBloqueadoException, UsuarioNoExisteException, UsuarioSuspendidoException {

        log.debug("autentificar(usuarioId:'" + usuarioId + "', grupo:'" + grupo + "', ip:'" + ip + "', tipoDispositivo:'" + tipoDispositivo + "' )");

        // Para realizar pruebas
        if ("19".equals(usuarioId) || "35".equals(usuarioId))
            return;

        if (respuestaDesafio == null || respuestaDesafio.length() == 0)
            throw new RuntimeException("La respuesta al desafio vino vacio");
        sfaAdapter.autentificar(usuarioId, grupo, respuestaDesafio, ip, tipoDispositivo);
    }

    public void autentificar(String usuarioId, String grupo, String respuestaDesafio, String ip, String tipoDispositivo, String canal, String mensajeUsuario, MensajeDesafioType mensaje, MachineSecret machineSecret)
            throws UsuarioNoSolicitoDesafioException, RespuestaDesafioInvalido,
            UsuarioBloqueadoException, UsuarioNoExisteException, UsuarioSuspendidoException {

        log.debug("autentificar(usuarioId:'" + usuarioId + "', grupo:'" + grupo + "', ip:'" + ip + "', tipoDispositivo:'" + tipoDispositivo + "' )");

        // Para realizar pruebas
        if ("19".equals(usuarioId) || "35".equals(usuarioId))
            return;

        if (respuestaDesafio == null || respuestaDesafio.length() == 0)
            throw new RuntimeException("La respuesta al desafio vino vacio");
        sfaAdapter.autentificar(usuarioId, grupo, respuestaDesafio, ip, tipoDispositivo, canal, mensajeUsuario, mensaje, machineSecret);
    }

    public void desbloquearUsuario(String usuarioId, String grupo) {
        sfaAdapter.desbloquearUsuario(usuarioId, grupo);
    }

    public DesafioType crearDesafio(String usuarioId, String grupo, String tipoDispositivo) throws SfaException {
        return sfaAdapter.crearDesafio(usuarioId, grupo, tipoDispositivo);
    }

    public DesafiosType crearDesafio(String usuarioId, String grupo, String ip, String tipoDispositivo, String Canal, String mensajeUsuario, MensajeDesafioType mensaje, MachineSecret machineSecret) throws SfaException {
        if(tipoDispositivo.equals("SMS")||tipoDispositivo.equals("OTP"))
            return null;//desafioFactory.crear(usuarioId, grupo, tipoDispositivo);
        else
            return sfaAdapter.crearDesafio(usuarioId, grupo, ip, tipoDispositivo, Canal, mensajeUsuario, mensaje, machineSecret);
    }

    public List<Dispositivo> listarDispositvos(String usuarioId, String grupo)
            throws UsuarioNoExisteException, UsuarioBloqueadoException, UsuarioSinDesafioException, UsuarioSuspendidoException {
        //log.debug("ListarDispositivos");
        System.out.println("[DesafioServicioImpl][listarDispositvos][INICIO] usuario,grupo:"+ usuarioId+","+grupo);
        List<Dispositivo> dispositivos = new ArrayList<Dispositivo>();
        try{
            //log.debug("usuario: "+ usuarioId + " grupo: "+grupo);
            dispositivos = sfaAdapter.dipositivosDelUsuario(usuarioId, grupo);
            System.out.println(dispositivos.size());
        } catch (Exception e) {
                //log.error("Problemas de Conexion:" + e.getMessage());
                //e.printStackTrace();
        	    System.out.println("[DesafioServicioImpl]Exception:"+ e.getMessage());
                throw new RuntimeException("Problemas de Conexion:" + e.getMessage());
        }
        Dispositivo disp = Dispositivos.buscar(dispositivos, "OTP");
        //if(grupo.equals( BANCA_PERSONAS )){
            try {
                long celular = obtenerCelular(usuarioId);
                if (disp !=  null && celular > 0) {
                    //log.debug("El ususario " + usuarioId + " tiene configurado envio a sms");
                    dispositivos.add( new Dispositivo("SMS", String.valueOf(celular),String.valueOf("")));
                }
            } catch (Exception e) {
                //log.debug("ERROR CARGANDO SMS!!!!: " + e.getMessage());
        //                e.printStackTrace();
            }
        //}

        if(disp != null)
            dispositivos.remove(disp);

        if(dispositivos == null || dispositivos.size() == 0){
            String msg = "El usuario '" + usuarioId + "' del grupo '" + grupo + "' no tiene algun sfa activo";
            throw new UsuarioSinDesafioException(msg);
        }

        return dispositivos;
    }

    public long obtenerCelular(String rut){
    	
        //log.debug("smsSrv.obtenerCelulares");
        long[] cels = null;
        try{
            cels = null;//smsSrv.obtenerCelulares(rut);
        } catch (Exception e){
            e.printStackTrace();
        }
        //log.debug(cels.length);
        if(cels == null || cels.length ==0)
            return 0;

        return cels[0];
    }

    public void ingresoEnrolamientoSMS(Enrolamiento enrolamiento) {
        log.debug("ingresoEnrolamientoSMS: [" + enrolamiento + "]");
        dao.ingresoEnrolamientoSMS(enrolamiento);
    }

    public void actualizarEnrolamientoSMS(String idMensaje, String estadoOperacion) {
        log.debug("actualizarEnrolamientoSMS: [ IDMensaje:" + idMensaje + "Estado Operacion: " + estadoOperacion +  "]");
        dao.actualizarEnrolamientoSMS(idMensaje, estadoOperacion);
    }

    private DesafioType procesarDesafio(String t, String usuarioId, String grupo)
            throws SfaException {
        DesafioType desafio = null;
        if (t == "GRILLA" || t == "TOKEN" || t == "SFTKN")
            desafio = crearDesafio(usuarioId, grupo, t);
        else if (t != "OTP")
            desafio = new DesafioType(t);
        return desafio;
    }

}

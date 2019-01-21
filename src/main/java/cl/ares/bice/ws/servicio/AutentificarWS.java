package cl.ares.bice.ws.servicio;


import cl.ares.bice.seguridad.sfa.servicio.impl.DesafioServicioImpl;
import cl.ares.bice.seguridad.sfa.ws.DesafioBuilder;
import cl.ares.bice.seguridad.sfa.ws.DesafioEstados;
//import cl.ares.bice.ws.smsUtils.SmsUtilsWS;
import cl.ares.bice.ws.utiles.LoggerUtil;
import cl.ares.bice.ws.utiles.Tools;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.rmi.RemoteException;

import java.util.Properties;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.log4j.Logger;


@WebService (targetNamespace = "http://servicio.ws.bice.cl/")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public class AutentificarWS {
    static final Logger log = LoggerUtil.getLoggerInput(ListarDesafiosWS.class);
    private DesafioServicioImpl desafioServicio = new DesafioServicioImpl();
    public AutentificarWS() {
        super();
    }

    @WebMethod
    @SOAPBinding(parameterStyle=ParameterStyle.BARE)
    public AutentificarResponse Autentificar(@WebParam(partName="parameters", name="Autentificar") Autentificar parameters){
        Tools t = new Tools();
        t.carga();
        AutentificarResponse response = new AutentificarResponse();
        String respDesafio = "";
        String usuarioId = "";
        String grupo = "";
        String ip = "";
        String tip = "";
        String tipo = "";
        String canal = "";
        String mensajeUsuario = "";
        MensajeDesafioType mensaje = new MensajeDesafioType();
        MachineSecret machineSecret;


        Context env;
        String file = "";
        InputStream input = null;
        Properties prop = new Properties();
        String file2 = "";
        InputStream input2 = null;
        Properties prop2 = new Properties();
        try {
            env = (Context)new InitialContext().lookup("java:comp/env");
            file = (String)env.lookup("TipoCliente.props");
            input = new FileInputStream(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // load a properties file
        try {
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            env = (Context)new InitialContext().lookup("java:comp/env");
            file2 = (String)env.lookup("desafios.props");
            input2 = new FileInputStream(file2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // load a properties file
        try {
            prop2.load(input2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String smsUtilsActivo=prop2.getProperty("smsUtils.activo");


        try {
            usuarioId = parameters.getUsuarioId();
            //log.debug(usuarioId);
            log.debug(prop.getProperty(parameters.getTipoCliente()));
            grupo = prop.getProperty(parameters.getTipoCliente());
            if(grupo == null)
                grupo = parameters.getTipoCliente();
            //log.debug(grupo);
            ip = parameters.getIPCliente();
            respDesafio = parameters.getRespuestaDesafio();
            tip = DesafioBuilder.getFromTipoDesafio(parameters.getTipoDesafio());
            if(tip.equals("SMS")||tip.equals("OTP")){
                log.debug("otp");
                tipo = "OTP";
            }else if(tip.equals("GRILLA")){
                log.debug("grid");
                tipo = "GRILLA";
            }else{
                log.debug(tip);
                tipo=tip;
            }
            mensajeUsuario = parameters.getMensajeUsuario();
            mensaje = parameters.getMensajeDesafio();
            machineSecret = parameters.getMachineSecret();

            desafioServicio.autentificar(usuarioId, grupo, respDesafio, ip, tipo, canal, mensajeUsuario, mensaje, machineSecret);
            response.setEstado(DesafioEstados.ok());
            MachineSecret mSecret = desafioServicio.getSfaAdapter().getSecretMachine();
            if(machineSecret!=null && mSecret!=null){
                log.debug("MachineSecretResponse:");
                log.debug(String.valueOf(mSecret.getMachineLabel()));
                log.debug(String.valueOf(mSecret.getMachineNonce()));
                log.debug(String.valueOf(mSecret.getSequenceNonce()));
                response.setMachineSecret(mSecret);
                desafioServicio.getSfaAdapter().setSecretMachine(null);
            }
            if(smsUtilsActivo.equalsIgnoreCase("SI")){
                /*try {
                    //log.debug(SmsUtilsWS.SmsUtils(usuarioId,DesafioEstados.ok().getCodigo()));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }*/
            }
        } catch (Throwable e) {
            e.printStackTrace();
            response.setEstado(DesafioEstados.createEstadoError(e));
            if(smsUtilsActivo.equalsIgnoreCase("SI")){
                /*try {
                    //log.debug(SmsUtilsWS.SmsUtils(usuarioId,DesafioEstados.createEstadoError(e).getCodigo()));
                } catch (RemoteException e1) { 
                    e1.printStackTrace();
                }*/
            }
        }
        return response;
    }


}

package cl.ares.bice.ws.servicio;


import cl.ares.bice.seguridad.sfa.modelo.DesafioGrilla;
import cl.ares.bice.seguridad.sfa.servicio.RespuestaDesafioAutenticado;
import cl.ares.bice.seguridad.sfa.servicio.impl.DesafioServicioImpl;
import cl.ares.bice.seguridad.sfa.ws.DesafioBuilder;
import cl.ares.bice.seguridad.sfa.ws.DesafioEstados;
import cl.ares.bice.ws.utiles.LoggerUtil;
import cl.ares.bice.ws.utiles.Tools;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

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
public class CrearDesafioWS {
    static final Logger log = LoggerUtil.getLoggerInput(CrearDesafioWS.class);
    private DesafioServicioImpl desafioServicio = new DesafioServicioImpl();
    //private ObjectFactory objectFactory = new ObjectFactory();

    public CrearDesafioWS() {
        super();
    }

    @WebMethod
    @SOAPBinding(parameterStyle=ParameterStyle.BARE)
    public CrearDesafioResponse CrearDesafio(@WebParam(partName="parameters", name="CrearDesafio") CrearDesafio parameters){
        Tools t = new Tools();
        t.carga();
        CrearDesafioResponse response = new CrearDesafioResponse();
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
            usuarioId = parameters.getUsuarioId();
            log.debug(prop.getProperty(parameters.getTipoCliente()));
            grupo = prop.getProperty(parameters.getTipoCliente());
            if(grupo == null)
                grupo = parameters.getTipoCliente();
            ip = parameters.getIPCliente();
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
            canal = parameters.canalOrigen;
            mensajeUsuario = parameters.getMensajeUsuario(); 
            mensaje = parameters.getMensajeDesafio();
            machineSecret = parameters.getMachineSecret();
            log.debug(String.valueOf("MachineSecret: "+machineSecret));

            DesafiosType d = desafioServicio.crearDesafio(usuarioId, grupo, ip, tipo, canal, mensajeUsuario, mensaje, machineSecret);

            response.setDesafios(d);//new DesafiosType());
            MachineSecret mSecret = desafioServicio.getSfaAdapter().getSecretMachine();
            if(machineSecret!=null && mSecret!=null){
                log.debug("MachineSecretResponse:");
                log.debug(String.valueOf(mSecret.getMachineLabel()));
                log.debug(String.valueOf(mSecret.getMachineNonce()));
                log.debug(String.valueOf(mSecret.getSequenceNonce()));
                response.setMachineSecret(mSecret);
                desafioServicio.getSfaAdapter().setSecretMachine(null);
            }

            response.setEstado(DesafioEstados.ok());
        } catch (Throwable e) {
            response.setEstado(DesafioEstados.createEstadoError(e));
            e.printStackTrace();
            if(e instanceof RespuestaDesafioAutenticado){
                MachineSecret mSecret = desafioServicio.getSfaAdapter().getSecretMachine();
                if(mSecret!=null){
                    log.debug("MachineSecretResponse:");
                    log.debug(String.valueOf(mSecret.getMachineLabel()));
                    log.debug(String.valueOf(mSecret.getMachineNonce()));
                    log.debug(String.valueOf(mSecret.getSequenceNonce()));
                    response.setMachineSecret(mSecret);
                    desafioServicio.getSfaAdapter().setSecretMachine(null);
                }
            }
        }
        return response;
    }

    private DesafioType getDesafioCoord(DesafioGrilla d) {
        DesafioType desafio = getDesafio(d);

        CoordenadasType coords = new CoordenadasType();
        for (CoordenadaType c : d.getCoordenadas().getCoordenada()) {
            CoordenadaType coord = new CoordenadaType();
            coord.setFila(c.getFila());
            coord.setColumna(c.getColumna() + "");

            coords.getCoordenada().add(coord);
        }

        desafio.setCoordenadas(coords);

        return desafio;
    }

    private DesafioType getDesafio(DesafioType d) {        
        DesafioType desafio = new DesafioType();
        desafio.setIdentificador(d.getIdentificador());
        desafio.setTipoDesafio(DesafioBuilder.getFromTipoDesafio(d.getTipoDesafio()));
        return desafio;
    }

}

package cl.ares.bice.ws.servicio;


import cl.ares.bice.seguridad.sfa.modelo.Dispositivo;
import cl.ares.bice.seguridad.sfa.servicio.impl.DesafioServicioImpl;
import cl.ares.bice.seguridad.sfa.ws.DesafioEstados;
import cl.ares.bice.seguridad.sfa.ws.ListarDesafiosRequestBuilder;
import cl.ares.bice.ws.utiles.LoggerUtil;
import cl.ares.bice.ws.utiles.Tools;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.List;
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
public class ListarDesafiosWS {
    static final Logger log = LoggerUtil.getLoggerInput(ListarDesafiosWS.class);
    public ListarDesafiosWS() {
        super();
    }

    @WebMethod
    @SOAPBinding(parameterStyle=ParameterStyle.BARE)
    public ListarDesafiosResponse ListarDesafios(@WebParam(partName="parameters", name="ListarDesafios") ListarDesafios parameters){
        Tools t = new Tools();
        t.carga();
        ListarDesafiosResponse response = new ListarDesafiosResponse();
        String usuarioId = "";
        String grupo = "";

        Context env;
        String file = "";
        InputStream input = null;
        Properties prop = new Properties();
        //Carga SMS PROPERTIES
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
            //System.out.println(usuarioId);
            log.debug(prop.getProperty(parameters.getTipoCliente()));
            grupo = prop.getProperty(parameters.getTipoCliente());
            if(grupo == null)
                grupo = parameters.getTipoCliente();
            //System.out.println(grupo);
            List<Dispositivo> dispositivos = new DesafioServicioImpl().listarDispositvos(usuarioId, grupo);
            response = ListarDesafiosRequestBuilder.crearRespuesta(dispositivos);

        } catch (Throwable e) {
            log.error("Error al tratar de listar desafios para usuario '" + usuarioId + "'  del grupo '" + grupo + "'. Error: " + e.getMessage());
            e.printStackTrace();
            response.setEstado(DesafioEstados.createEstadoError(e));
        }
        return response;
    }

}

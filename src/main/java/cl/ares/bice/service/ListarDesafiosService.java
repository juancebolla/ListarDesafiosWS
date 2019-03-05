package cl.ares.bice.service;

import org.springframework.stereotype.Service;



import cl.ares.bice.seguridad.sfa.modelo.Dispositivo;
import cl.ares.bice.seguridad.sfa.servicio.impl.DesafioServicioImpl;
import cl.ares.bice.seguridad.sfa.ws.DesafioEstados;
import cl.ares.bice.seguridad.sfa.ws.ListarDesafiosRequestBuilder;
import cl.ares.bice.ws.servicio.ListarDesafios;
import cl.ares.bice.ws.servicio.ListarDesafiosResponse;
import cl.ares.bice.ws.utiles.Tools;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

@Service
public class ListarDesafiosService {
	
	public ListarDesafiosResponse listarDesafios(ListarDesafios parameters){
		System.out.println("LISTAR DESAFIOS POST PRUEBA 05032019b");
		//String fileName = System.getProperty("jboss.server.config.dir");
		//System.out.println("fileName:"+fileName);
		System.out.println("entrada.usuario:"+parameters.getUsuarioId());
		System.out.println("entrada.tipo:"+parameters.getTipoCliente());
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
            //e.printStackTrace();
        	System.out.println(e.getMessage());
        }
        // load a properties file
        try {
            prop.load(input);
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
        	//e.printStackTrace();
        	System.out.println(e.getMessage());
        }
        
        try {
            usuarioId = parameters.getUsuarioId();
            //System.out.println(usuarioId);
            //log.debug(prop.getProperty(parameters.getTipoCliente()));
            grupo = prop.getProperty(parameters.getTipoCliente());
            System.out.println("GRUPO PROPERTIES: " + grupo);
            if(grupo == null)
                grupo = parameters.getTipoCliente();
            System.out.println("grupo:" + grupo);
            List<Dispositivo> dispositivos = new DesafioServicioImpl().listarDispositvos(usuarioId, grupo);
            response = ListarDesafiosRequestBuilder.crearRespuesta(dispositivos);

        } catch (Throwable e) {
            //log.error("Error al tratar de listar desafios para usuario '" + usuarioId + "'  del grupo '" + grupo + "'. Error: " + e.getMessage());
            //e.printStackTrace();
            System.out.println(e.getMessage());
            response.setEstado(DesafioEstados.createEstadoError(e));
        }
        return response;
	}
	
}

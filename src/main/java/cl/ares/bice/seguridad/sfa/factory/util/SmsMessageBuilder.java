package cl.ares.bice.seguridad.sfa.factory.util;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;


public class SmsMessageBuilder {

    private static final String FECHA_TOKEN = "###FECHA###";
    private static final String HORA_TOKEN = "###HORA###";
    private static final String CLAVE_TOKEN = "###CLAVE###";
    String plantilla;


    public void setPlantilla(String plantilla) {
        this.plantilla = plantilla;
    }

    public String crearMensaje(String clave){
        Context env;
        String file = "";
        InputStream input = null;
        Properties prop = new Properties();
        //Carga SMS PROPERTIES
        try {
            env = (Context)new InitialContext().lookup("java:comp/env");
            file = (String)env.lookup("desafios.props");
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
        plantilla = prop.getProperty("sms.messageTmpl");
        SimpleDateFormat fmtDate = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat fmtHora = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        return plantilla.replaceAll(FECHA_TOKEN, fmtDate.format(date)).replaceAll(HORA_TOKEN, fmtHora.format(date)).replaceAll(CLAVE_TOKEN, clave);
    }


}

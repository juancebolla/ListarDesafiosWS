package cl.ares.bice.ws.enviosms;

//import cl.ares.bice.ws.sms.SmsBalanceadoService;


import cl.ares.bice.ws.enviosms.ws.proxy.SmsBalanceado;
import cl.ares.bice.ws.enviosms.ws.proxy.SmsBalanceadoService;
import cl.ares.bice.ws.enviosms.ws.types.EnviaSmsRespTO;
import cl.ares.bice.ws.enviosms.ws.types.EnviaSmsTO;
import cl.ares.bice.ws.utiles.LoggerUtil;
import cl.ares.bice.ws.utiles.Tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import java.net.MalformedURLException;
import java.net.URL;

import java.rmi.RemoteException;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.xml.namespace.QName;

import org.apache.log4j.Logger;


public class EnvioSMS implements Serializable {
    @SuppressWarnings("compatibility:7084625842619174291")
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerUtil.getLoggerInput(EnvioSMS.class);
    public EnvioSMS() {
        super();
    }
    
    public static String envioSMS(long numTelefono, String mensaje, int idServicio, int idMsgOd) throws RemoteException {

        Tools t = new Tools();
        t.carga();
        log.debug("enviarSms(tel:'" + numTelefono + "', msg:'" + mensaje + "' )");
        SmsBalanceadoService smsBalanceadoService;
        SmsBalanceado smsBalanceado;
        EnviaSmsRespTO resp = null;

        Context env;
        String file = "";
        InputStream input = null;
        Properties prop = new Properties();
        //Carga SMS PROPERTIES
        try {
            env = (Context)new InitialContext().lookup("java:comp/env");
            file = (String)env.lookup("sms.props");
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

        try{
            URL baseUrl=null;
            try {
                baseUrl = new File(".").toURL();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            URL wsdlLocation=null;
            try {
                wsdlLocation = new URL(baseUrl, (String)prop.getProperty("URL_SMS_BALANCEADO"));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            QName qname =new QName("http://ws.sms.bice.cl/", "SmsBalanceadoService");
            smsBalanceadoService = new SmsBalanceadoService(wsdlLocation,qname);
            smsBalanceado = smsBalanceadoService.getSmsBalanceadoPort();
            
            EnviaSmsTO request = new EnviaSmsTO();
            request.setMensaje(mensaje);
            request.setNumeroCelularDestino(numTelefono);
            resp = smsBalanceado.enviarSMS(request);

        } catch (Exception e){
            e.printStackTrace();
        }

        return resp.getCodOperAsignado();
    }
}

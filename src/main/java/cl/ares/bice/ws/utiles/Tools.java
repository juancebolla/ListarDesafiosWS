package cl.ares.bice.ws.utiles;


import cl.ares.bice.ws.utiles.dao.LimpiarCache;
import cl.ares.bice.ws.utiles.dao.LimpiarCacheResponse;

import com.sun.org.apache.xml.internal.security.utils.Base64;

//import com.tangosol.net.CacheFactory;
//import com.tangosol.net.NamedCache;
//import com.tangosol.net.cache.CacheMap;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.Enumeration;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.log4j.Logger;


public class Tools {
    static final Logger oLogger = LoggerUtil.getLoggerInput(Tools.class);
    public Tools() {
        super();
    }

    public LimpiarCacheResponse limpiarCache(LimpiarCache parameters){
        LimpiarCacheResponse resp = new LimpiarCacheResponse();
        //CacheFactory.ensureCluster();
        //NamedCache cacheAPP = CacheFactory.getCache("cacheDesafiosAPP");
        //NamedCache cacheSMS = CacheFactory.getCache("cacheDesafiosSMS");
        //NamedCache cacheDVM = CacheFactory.getCache("cacheDesafiosDVM");
        //cacheAPP.clear();
        oLogger.debug("Clear cache cacheDesafiosAPP");
        //cacheSMS.clear();
        oLogger.debug("Clear cache cacheDesafiosSMS");
        //cacheDVM.clear();
        oLogger.debug("Clear cache cacheDesafiosDVM");
        resp.setReturn("Borrado de Cache Exitoso");
        return resp;
    }


    public void carga(){
        //if(!validarCache().equals("OK"))
            try {
                processPropertiesFile();
            } catch (Exception e) {
                //e.printStackTrace();
            	System.out.println("[Tools][carga] Exception:"+e.getMessage());
                oLogger.debug("Lee properties y carga cache");
            }
    }

    //private String agregarRegistroCache(String Cache, String variable, String valor){
    //    CacheFactory.ensureCluster();
    //    NamedCache cache = CacheFactory.getCache(Cache);
    //    cache.put(variable, valor, CacheMap.EXPIRY_NEVER);
    //    return "Exito";
    //}

    //public String validarCache() {
        //CacheFactory.ensureCluster();
        //NamedCache cacheAPP;
        //NamedCache cacheSMS;
        //NamedCache cacheDVM;
        //cacheAPP = CacheFactory.getCache("cacheDesafiosAPP");
        //oLogger.debug("cacheAPP: "+cacheAPP.size());
        //if (cacheAPP.size() > 1) {
            //cacheSMS = CacheFactory.getCache("cacheDesafiosSMS");
            //oLogger.debug("cacheSMS: "+cacheSMS.size());
            //if (cacheAPP.size() > 1) {
                //cacheDVM = CacheFactory.getCache("cacheDesafiosDVM");
                //oLogger.debug("cacheDVM: "+cacheDVM.size());
                //if (cacheDVM.size() > 1) {
                //    return "OK";
                //} else {
                //    LimpiarCacheResponse a;
                //    a = limpiarCache(new LimpiarCache());
                //    try {
                //        processPropertiesFile();
                //    } catch (ConfigurationException e) {
                //        e.printStackTrace();
                //        return "No OK";
                //    }
                //}
            //} else {
                //LimpiarCacheResponse a;
                //a = limpiarCache(new LimpiarCache());
                //try {
                //    processPropertiesFile();
                //} catch (ConfigurationException e) {
                //    e.printStackTrace();
                //    return "No OK";
                //}
            //}
        //} else {
            //LimpiarCacheResponse a;
            //a = limpiarCache(new LimpiarCache());
            //try {
            //    processPropertiesFile();
            //} catch (ConfigurationException e) {
            //    e.printStackTrace();
            //    return "No OK";
            //}
        //}
        //return "OK";
    //}

    private void processPropertiesFile()  {
        Properties prop = new Properties();
        InputStream input = null;

        Context env;
        String file = "";
        Boolean config_error = false;
        Enumeration<?> e1;
        Enumeration<?> e2;
        Enumeration<?> e3;
        try {
            //Carga DESAFIOS PROPERTIES
            env = (Context)new InitialContext().lookup("java:comp/env");
            file = (String)env.lookup("desafios.props");
            input = new FileInputStream(file);

            // load a properties file
            prop.load(input);
            e1 = prop.propertyNames();
            while (e1.hasMoreElements()) {
                String key = (String) e1.nextElement();
                String value;
                if(key.contains("password")){
                    try{
                        value = new String(Base64.decode(prop.getProperty(key)));
                    }catch(Exception e){
                        value = prop.getProperty(key);
                        //e.printStackTrace();
                        System.out.println("Exception:"+e.getMessage());
                    }
                }else{
                    value = prop.getProperty(key);
                }
                //agregarRegistroCache("cacheDesafiosAPP", key, value);
                //log.debug("Key : " + key + ", Value : " + value);
            }

            //Carga SMS PROPERTIES
            file = (String)env.lookup("sms.props");
            input = new FileInputStream(file);

            // load a properties file
            prop.load(input);
            e2 = prop.propertyNames();
            while (e2.hasMoreElements()) {
                String key = (String) e2.nextElement();
                String value;
                //log.debug("sms.props:" + key +"|"+ prop.getProperty(key));
                if(key.contains("password")){
                    try{
                        value = new String(Base64.decode(prop.getProperty(key)));
                    }catch(Exception e){
                        value = prop.getProperty(key);
                        //e.printStackTrace();
                        System.out.println("Exception:"+e.getMessage());
                    }
                }else{
                    value = prop.getProperty(key);
                }
                //agregarRegistroCache("cacheDesafiosSMS", key, value);
                //log.debug("Key : " + key + ", Value : " + value);
            }

            //Carga DVM PROPERTIES
            file = (String)env.lookup("TipoCliente.props");
            input = new FileInputStream(file);

            // load a properties file
            prop.load(input);
            e3 = prop.propertyNames();
            while (e3.hasMoreElements()) {
                String key = (String) e3.nextElement();
                String value;
                if(key.contains("password")){
                    try{
                        value = new String(Base64.decode(prop.getProperty(key)));
                    }catch(Exception e){
                        value = prop.getProperty(key);
                        //e.printStackTrace();
                        System.out.println("Exception:"+e.getMessage());
                    }
                }else{
                    value = prop.getProperty(key);
                }
                //agregarRegistroCache("cacheDesafiosDVM", key, value);
                //log.debug("Key : " + key + ", Value : " + value);
            }

        } catch (IOException ioe) {
            //ioe.printStackTrace(System.err);
        	System.out.println("Exception:"+ioe.getMessage());
        } catch (Exception ex) {
                //ex.printStackTrace();
        	System.out.println("Exception:"+ex.getMessage());
        } catch (Throwable f) {
            //f.printStackTrace();
        	System.out.println("Exception:"+f.getMessage());
        } finally {
                if (input != null) {
                        try {
                                input.close();
                        } catch (IOException ie) {
                                //ie.printStackTrace();
                        	System.out.println("Exception:"+ie.getMessage());
                        }
                }
        }

        if (config_error) {
            System.exit(-1);
        }
    }

}

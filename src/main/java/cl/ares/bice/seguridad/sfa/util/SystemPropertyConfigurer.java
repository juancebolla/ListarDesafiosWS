package cl.ares.bice.seguridad.sfa.util;


import cl.ares.bice.ws.utiles.LoggerUtil;

import java.util.Map;

import org.apache.log4j.Logger;


public class SystemPropertyConfigurer {

    private static final Logger log = LoggerUtil.getLoggerInput(SystemPropertyConfigurer.class);

    Map<String, String> properties;


    public void setProperties(Map<String, String> properties) {
        this.properties = properties;

        if(log.isDebugEnabled())
            dumpCurrentSystemProps();

        for(String key : properties.keySet()){
            String value = properties.get(key);
            if( value == null || value.equals("") )
                System.clearProperty(key);
            else{
                log.debug("****Previous value of " + key + ": " + System.getProperty(key));
                System.clearProperty(key);
                log.debug("Setting propvalue:" + key + " to :" +  properties.get(key) );
                System.setProperty(key, properties.get(key));
            }
        }
        
    }

    private void dumpCurrentSystemProps() {
//        Set keys =  System.getProperties().keySet();
//        for(Object okey : keys){
//            log.debug("Property: " + okey + " Value: " + System.getProperty((String)okey) );
//        }
    }
}

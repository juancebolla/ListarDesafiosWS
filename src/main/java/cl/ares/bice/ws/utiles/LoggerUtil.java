package cl.ares.bice.ws.utiles;

import java.io.File;
import java.io.FileInputStream;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggerUtil {
    public LoggerUtil() {
        super();
    }

    public static Logger getLoggerInput(Class clase) {
        Logger logger = null;
        Properties rutaFile = new Properties();
        // Get a handle to the JNDI environment naming context
        Context env;
        String file;

        try {
            env = (Context)new InitialContext().lookup("java:comp/env");
            file = (String)env.lookup("log4j.props");
            java.io.InputStream strm = new FileInputStream(new File(file));
            rutaFile.load(strm);
            PropertyConfigurator.configure(rutaFile);
            logger = Logger.getLogger(clase);
        } catch (NamingException e) {
            //e.printStackTrace();
        	System.out.println("[LoggerUtil]NamingException:"+e.getMessage());
        } catch (Exception ie) {
            //ie.printStackTrace(System.err);
        	System.out.println("[LoggerUtil]Exception:"+ie.getMessage());
        }
        return logger;
    }
}
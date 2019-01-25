package cl.ares.bice.seguridad.sfa.ws;


import cl.ares.bice.ws.utiles.LoggerUtil;

import javax.servlet.ServletContextEvent;

import org.apache.log4j.Logger;


public class InitParamsFrameworkServlet implements javax.servlet.ServletContextListener{

    private static final Logger logger = LoggerUtil.getLoggerInput(InitParamsFrameworkServlet.class);

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        logger.debug("cert**************" + System.getProperty("javax.net.ssl.trustStore"));
//        logger.debug("Clearing property javax.xml.parsers.DocumentBuilderFactory");
//        logger.debug("Clearing property javax.wsdl.factory.WSDLFactory");

  //      System.clearProperty("javax.xml.parsers.DocumentBuilderFactory");
    //    System.clearProperty("javax.wsdl.factory.WSDLFactory");
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}

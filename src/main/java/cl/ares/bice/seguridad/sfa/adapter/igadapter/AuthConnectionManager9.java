package cl.ares.bice.seguridad.sfa.adapter.igadapter;


import cl.ares.bice.seguridad.sfa.adapter.SfaAdapterException;
import cl.ares.bice.seguridad.sfa.util.SSLUtilities;
import cl.ares.bice.ws.utiles.LoggerUtil;

import com.entrust.identityGuard.authenticationManagement.wsv9.AuthenticationServiceBindingStub;
import com.entrust.identityGuard.authenticationManagement.wsv9.AuthenticationService_ServiceLocator;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;


public class AuthConnectionManager9 {

    private static final Logger log = LoggerUtil.getLoggerInput(AuthConnectionManager9.class);

    String url;
    //AuthenticationService_PortType ms_serviceBinding;


    public void setUrl(String url) {
        this.url = url;
    }

    public AuthenticationServiceBindingStub getBinding() {//AuthenticationService_PortType
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
        url=prop.getProperty("igauth.url");
        log.debug(url);
        SSLUtilities.trustAllHttpsCertificates();

        AuthenticationServiceBindingStub authenticationBinding = createBinding();
        return authenticationBinding;
/*        try {
            if (ms_serviceBinding == null) {
                synchronized (this) {
                    if (ms_serviceBinding == null) {
                        log.debug("Binding to authentication service. Url: " + url);
                        URL igURL = new URL(url);
                        AuthenticationService_ServiceLocator locator = new AuthenticationService_ServiceLocator();
                        ms_serviceBinding = locator.getAuthenticationService(igURL);
                    }
                }
            }
            return ms_serviceBinding;
        } catch (MalformedURLException e) {
            log.error(e.getStackTrace());
            throw new SfaAdapterException(e);
        } catch (ServiceException e) {
            log.error(e.getStackTrace());
            throw new SfaAdapterException(e);
        }*/
    }

    private AuthenticationServiceBindingStub createBinding() {
       // System.out.println("*********************** CREATE_BINDING ADMIN ***************************");
        try {
            AuthenticationServiceBindingStub adminBinding = (AuthenticationServiceBindingStub) new AuthenticationService_ServiceLocator().getAuthenticationService(new URL(url));
            //System.out.println("paso1");
            adminBinding.setMaintainSession(true);
            //System.out.println("paso2");
            return adminBinding;

    //            if (adminBinding == null) {
    //                synchronized (this) {
    //                    if (adminBinding == null) {
    //                        log.debug("Binding to IdGuard Admin Service. Url: " + url);
    //                        adminBinding = (AdminServiceBindingStub)
    //                                new AdminService_ServiceLocator().getAdminService(new URL(url));
    ////                        adminBinding.setMaintainSession(true);
    //                        adminBinding.
    //                    }
    //                }
    //            }
        } catch (ServiceException e) {
            log.error(e.getStackTrace());
            throw new SfaAdapterException(e);
        } catch (MalformedURLException e) {
            log.error(e.getStackTrace());
            throw new SfaAdapterException(e);
        }
    }
}

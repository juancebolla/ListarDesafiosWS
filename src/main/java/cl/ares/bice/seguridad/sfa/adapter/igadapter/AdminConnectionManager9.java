package cl.ares.bice.seguridad.sfa.adapter.igadapter;


import cl.ares.bice.seguridad.sfa.adapter.SfaAdapterException;
import cl.ares.bice.seguridad.sfa.util.SSLUtilities;
import cl.ares.bice.ws.utiles.LoggerUtil;

import com.entrust.identityGuard.userManagement.wsv9.AdminServiceBindingStub;
import com.entrust.identityGuard.userManagement.wsv9.AdminService_ServiceLocator;
import com.entrust.identityGuard.userManagement.wsv9.LoginCallParms;
import com.entrust.identityGuard.userManagement.wsv9.LoginParms;
import com.entrust.identityGuard.userManagement.wsv9.LoginResult;
import com.entrust.identityGuard.userManagement.wsv9.LoginState;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.net.MalformedURLException;
import java.net.URL;

import java.rmi.RemoteException;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;


public class AdminConnectionManager9 {

    private static final Logger log = LoggerUtil.getLoggerInput(AdminConnectionManager9.class);

    String url;
    String user;
    String password;
//    AdminServiceBindingStub adminBinding;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AdminServiceBindingStub getBinding() {
    	System.out.println("[AdminConnectionManager9][getBinding]INICIO");
    	AdminServiceBindingStub adminBinding = null;
    	try {
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
        url=(prop.getProperty("igadmin.url"));
        //log.debug(url);
        System.out.println("[AdminConnectionManager9][getBinding]url:"+url);
        user=(prop.getProperty("igadmin.username"));
        //System.out.println(user);
        //System.out.println(password);
        try {
            password=new String(Base64.decode(prop.getProperty("igadmin.password")));
        } catch (Base64DecodingException e) {
            e.printStackTrace();
        }
        SSLUtilities.trustAllHttpsCertificates();
        //AdminServiceBindingStub adminBinding = createBinding();
        adminBinding = createBinding();
        login(adminBinding);
        System.out.println("[AdminConnectionManager9][getBinding]adminBinding:"+adminBinding);
    	}catch (Exception e) {
    		System.out.println("[AdminConnectionManager9][getBinding]Exception e:"+e.getMessage());
		}
        return adminBinding;
    }

    private void login(AdminServiceBindingStub adminBinding) throws Exception{
        try {
        	System.out.println("[AdminConnectionManager9][login]Inicio");
        	System.out.println("[AdminConnectionManager9][login]adminBinding" + adminBinding);
            LoginParms parms = new LoginParms();
            parms.setAdminId(user);
            parms.setPassword(password);
            System.out.println("[AdminConnectionManager9][login]user: "+user);
            System.out.println("[AdminConnectionManager9][login]password: "+password);
            //System.out.println(user + " - " + password);
            //log.debug("Trying to log in to IdGuard Admin service");
            //log.debug("User:" + user);
            LoginResult result = adminBinding.login(new LoginCallParms(parms));
            System.out.println("[AdminConnectionManager9][login]result:"+((result != null && result.getState() != null) ? result.getState().getValue():"null"));
            if (!result.getState().equals(LoginState.COMPLETE)) {
            	System.out.println("[AdminConnectionManager9][login]Login to IdGuard not accepted");
                throw new SfaAdapterException("Login to IdGuard not accepted");
            }
            System.out.println("[AdminConnectionManager9][login]Fin OK");
//            log.debug("Login successful");
        } catch (RemoteException e) {
        	System.out.println("[AdminConnectionManager9][login]RemoteException e: "+ e.getMessage() );
            //log.error("Error while trying to login to IdGuard admin service: " + e.getMessage());
            //log.error(e.toString());
            throw new SfaAdapterException(e);
        }
         catch(Exception e) {
        	 System.out.println("[AdminConnectionManager9][login]Exception e: "+ getStackTrace(e) );
        	 throw new Exception(e);
         }
    }

    private AdminServiceBindingStub createBinding() throws Exception{
        //System.out.println("*********************** CREATE_BINDING ADMIN ***************************");
    	System.out.println("[AdminConnectionManager9][createBinding]INICIO");
        try {
            AdminServiceBindingStub adminBinding = (AdminServiceBindingStub) new AdminService_ServiceLocator().getAdminService(new URL(url));
           // System.out.println("paso1");
            adminBinding.setMaintainSession(true);
           // System.out.println("paso2");
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
            //log.error(e.getStackTrace());
        	System.out.println("[AdminConnectionManager9][createBinding]ServiceException e:"+e.getMessage());
            throw new SfaAdapterException(e);
        } catch (MalformedURLException e) {
            //log.error(e.getStackTrace());
        	System.out.println("[AdminConnectionManager9][createBinding]MalformedURLException e:"+e.getMessage());
            throw new SfaAdapterException(e);
        }
        catch (Exception e) {
        	System.out.println("[AdminConnectionManager9][createBinding]Exception e:"+ getStackTrace(e));
        	throw new Exception(e);
		}
    }
    
    public static String getStackTrace(final Throwable throwable) {
   	 final java.io.StringWriter sw = new java.io.StringWriter();
   	 final java.io.PrintWriter pw = new java.io.PrintWriter(sw, true);
   	 throwable.printStackTrace(pw);
   	 return sw.getBuffer().toString();
   }

}

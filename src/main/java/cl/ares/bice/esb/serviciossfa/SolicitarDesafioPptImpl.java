package cl.ares.bice.esb.serviciossfa;


import cl.ares.bice.seguridad.esb.desafio.AutentificarRequestType;
import cl.ares.bice.seguridad.esb.desafio.CrearDesafioRequestType;
import cl.ares.bice.seguridad.esb.desafio.ListarDesafiosRequestType;
import cl.ares.bice.seguridad.esb.desafio.SolicitudDesafioType;
import cl.ares.bice.seguridad.sfa.adapter.igadapter.AdminConnectionManager9;
import cl.ares.bice.seguridad.sfa.adapter.igadapter.IdGuardSfaAdapter;
import cl.ares.bice.seguridad.sfa.factory.DesafioFactory;
import cl.ares.bice.seguridad.sfa.modelo.DesafioGrilla;
import cl.ares.bice.seguridad.sfa.modelo.Dispositivo;
import cl.ares.bice.seguridad.sfa.modelo.Dispositivos;
import cl.ares.bice.seguridad.sfa.servicio.ISmsServicio;
import cl.ares.bice.seguridad.sfa.servicio.SfaException;
import cl.ares.bice.seguridad.sfa.servicio.UsuarioNoExisteException;
import cl.ares.bice.seguridad.sfa.servicio.UsuarioSinDesafioException;
import cl.ares.bice.seguridad.sfa.servicio.impl.DesafioServicioImpl;
import cl.ares.bice.seguridad.sfa.ws.DesafioEstados;
import cl.ares.bice.seguridad.sfa.ws.ListarDesafiosRequestBuilder;
import cl.ares.bice.ws.servicio.AutentificarResponse;
import cl.ares.bice.ws.servicio.CoordenadaType;
import cl.ares.bice.ws.servicio.CoordenadasType;
import cl.ares.bice.ws.servicio.CrearDesafioResponse;
import cl.ares.bice.ws.servicio.DesafioType;
import cl.ares.bice.ws.servicio.DesafiosType;
import cl.ares.bice.ws.servicio.ListarDesafiosResponse;
import cl.ares.bice.ws.utiles.LoggerUtil;
import cl.ares.bice.ws.utiles.Tools;

import com.entrust.identityGuard.userManagement.wsv9.AdminServiceFault;
import com.entrust.identityGuard.userManagement.wsv9.ErrorCode;
import com.entrust.identityGuard.userManagement.wsv9.State;
import com.entrust.identityGuard.userManagement.wsv9.UserCardInfo;
import com.entrust.identityGuard.userManagement.wsv9.UserFilter;
import com.entrust.identityGuard.userManagement.wsv9.UserGetParms;
import com.entrust.identityGuard.userManagement.wsv9.UserInfo;
import com.entrust.identityGuard.userManagement.wsv9.UserListCallParms;
import com.entrust.identityGuard.userManagement.wsv9.UserListResult;
import com.entrust.identityGuard.userManagement.wsv9.UserTokenInfo;
import com.entrust.identityGuard.userManagement.wsv9.UserTokenSetParser;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.log4j.Logger;

import org.springframework.util.Assert;


public class SolicitarDesafioPptImpl {
    static final Logger oLogger = LoggerUtil.getLoggerInput(SolicitarDesafioPptImpl.class);
    private DesafioServicioImpl desafioServicio;
    DesafioFactory desafioFactory;
    IdGuardSfaAdapter sfaAdapter;
    ISmsServicio smsSrv = null;

    public SolicitarDesafioPptImpl(DesafioServicioImpl airlineService) {
        Assert.notNull(airlineService, "airlineService must not be null");
        this.desafioServicio = airlineService;
    }

    public SolicitarDesafioPptImpl() {
    }

    @WebResult(name = "CrearDesafioResponse",
               partName = "CrearDesafioResponse",
               targetNamespace = "http://www.bice.cl/seguridad/esb/desafio")
    @WebMethod
    public CrearDesafioResponse crearDesafio(@WebParam(name = "CrearDesafioRequest",
                                                       partName = "CrearDesafioRequest",
                                                       targetNamespace = "http://www.bice.cl/seguridad/esb/desafio")
        CrearDesafioRequestType crearDesafioRequest) {
        Tools t = new Tools();
        //oLogger.debug("Cache es " + t.validarCache());
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
        String grupo = null;

        CrearDesafioResponse response = new CrearDesafioResponse();
        CrearDesafioRequestType request = crearDesafioRequest;

        String usuarioId = request.getUsuarioId();
        if (prop.getProperty(request.getTipoCliente()) != null)
            grupo = prop.getProperty(request.getTipoCliente());
        String tipo = request.getTipoDesafio();

        try {
            DesafioType d;
            d = desafioServicio.crearDesafio(usuarioId, grupo, tipo);

            response.setDesafios(new DesafiosType());
            if (d.getTipoDesafio() == "GRILLA")
                response.getDesafios().getDesafio().add(getDesafioCoord((DesafioGrilla)d));
            else
                response.getDesafios().getDesafio().add(getDesafio(d));

            response.setEstado(DesafioEstados.ok());
        } catch (Throwable e) {
            response.setEstado(DesafioEstados.createEstadoError(e));
        }
        return response;
    }

    @WebResult(name = "AutentificarResponse",
               partName = "AutentificarResponse",
               targetNamespace = "http://www.bice.cl/seguridad/esb/desafio")
    @WebMethod
    public AutentificarResponse autentificar(@WebParam(name =
                                                           "AutentificarRequest",
                                                           partName =
                                                           "AutentificarRequest",
                                                           targetNamespace =
                                                           "http://www.bice.cl/seguridad/esb/desafio")
        AutentificarRequestType autentificarRequest) {
        Tools t = new Tools();
        //oLogger.debug("Cache es " + t.validarCache());
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
        String grupo = null;

        AutentificarResponse response = new AutentificarResponse();
        AutentificarRequestType request = autentificarRequest;

        String usuarioId = request.getUsuarioId();
        if (prop.getProperty(request.getTipoCliente()) != null)
            grupo = prop.getProperty(request.getTipoCliente());
        String ip = request.getIPCliente();
        String respDesafio = request.getRespuestaDesafio();
        String tipo = request.getTipoDesafio();

        try {
            desafioServicio.autentificar(usuarioId, grupo, respDesafio, ip,
                                         tipo);
            response.setEstado(DesafioEstados.ok());
        } catch (Throwable e) {
            response.setEstado(DesafioEstados.createEstadoError(e));
        }

        return response;
    }

    @WebResult(name = "ListarDesafiosResponse",
               partName = "ListarDesafiosResponse",
               targetNamespace = "http://www.bice.cl/seguridad/esb/desafio")
    @WebMethod
    public ListarDesafiosResponse listarDesafios(@WebParam(name =
                                                               "ListarDesafiosRequest",
                                                               partName =
                                                               "ListarDesafiosRequest",
                                                               targetNamespace =
                                                               "http://www.bice.cl/seguridad/esb/desafio")
        ListarDesafiosRequestType listarDesafiosRequest) {
        Tools t = new Tools();
        //oLogger.debug("Cache es " + t.validarCache());
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
        ListarDesafiosRequestType request = listarDesafiosRequest;
        ListarDesafiosResponse response = new ListarDesafiosResponse();
        String usuarioId = "";
        String grupo = "";
        List<Dispositivo> dispositivos = new ArrayList<Dispositivo>();
        //ISfaAdapter sfaAdapter = null;
        ISmsServicio smsSrv = null;
        AdminConnectionManager9 adminConnManager = new AdminConnectionManager9();

        try {
            usuarioId = request.getUsuarioId();
            //System.out.println(usuarioId);
            if (prop.getProperty(request.getTipoCliente()) != null)
                grupo = (String)prop.getProperty(request.getTipoCliente()); //getGrupo();
            oLogger.debug("usuario: " + usuarioId + " grupo: " + grupo);
            /**************1111111111111111******************/
            //dispositivos = desafioServicio.listarDispositvos(usuarioId, grupo);
            oLogger.debug("ListarDispositivos");
            /*******************2222222222222******************/
            //dispositivos = sfaAdapter.dipositivosDelUsuario(usuarioId, grupo);
            //List<Dispositivo> listaDispositivos = new ArrayList<Dispositivo>();
            try {
                oLogger.debug("usuario: " + usuarioId + " grupo: " + grupo);
                //System.out.println("usuario: " + usuarioId + " grupo: " + grupo);

                /**************33333333**********/
                //UserInfo[] userInfo = getUserInfo(usuarioId, grupo);
                //UserInfo resp = null;
                UserFilter filter = new UserFilter();
                filter.setUserid(usuarioId);

                if (grupo.length() > 0) {
                    ArrayList<String> groups = new ArrayList<String>();
                    groups.add(grupo);
                    filter.setGroups(groups.toArray(new String[groups.size()]));
                }

                UserGetParms getParms = new UserGetParms();

                getParms.setGetOTP(Boolean.TRUE);
                getParms.setGetTokens(Boolean.TRUE);
                getParms.setGetGrid(Boolean.TRUE);
                getParms.setGetQASecrets(Boolean.TRUE);
                getParms.setGetAuthenticationSecrets(Boolean.TRUE);
                getParms.setGetMachineSecrets(Boolean.TRUE);
                getParms.setGetPVN(Boolean.TRUE);
                getParms.setGetPIN(Boolean.TRUE);
                getParms.setGetCards(Boolean.TRUE);
                getParms.setGetCertificates(Boolean.TRUE);
                getParms.setGetFederations(Boolean.TRUE);
                getParms.setGetLocationHistory(Boolean.TRUE);
                getParms.setGetExpectedLocations(Boolean.TRUE);
                getParms.setGetActivationExpiryInfo(Boolean.TRUE);
                getParms.setGetPassword(Boolean.TRUE);
                getParms.setGetUserContactInfo(Boolean.TRUE);
                UserListResult result = adminConnManager.getBinding().userList(new UserListCallParms(filter, getParms));
                UserInfo[] userInfo = result.getUsers();
                if(userInfo.length==0){
                    throw new UsuarioNoExisteException("Usuario: "+ usuarioId + " grupo: "+grupo+" No existe");
                }
                /***********333333333333**********/

                /***************44444444444***********/
                //return getTipoAutUsuario(userInfo);
                //List<Dispositivo> tiposAuth = new ArrayList<Dispositivo>();
                List<Dispositivo> tokens = new ArrayList<Dispositivo>();
                List<Dispositivo> cards = new ArrayList<Dispositivo>();
                //List<Dispositivo> otps = new ArrayList<Dispositivo>();

                /********555555555******/
                //cards = getActiveCard(userInfo);
                for (int i = 0; i < userInfo.length; i++) {
                    if (userInfo[i].getCards() != null &&
                        userInfo[i].getCards().length > 0) {
                        for (UserCardInfo c : userInfo[i].getCards()) {
                            if (c.getState() == State.CURRENT)
                                cards.add(new Dispositivo("GRILLA",
                                                          c.getSerialNumber(),
                                                          c.getGroup()));
                        }
                    }
                }
                /******5555555*******/
                if (cards != null)
                    dispositivos.addAll(cards);

                /********666666******/
                //tokens = getActiveToken(userInfo);
                for (int i = 0; i < userInfo.length; i++) {

                    if (userInfo[i].getTokens() != null &&
                        userInfo[i].getTokens().length > 0) {
                        UserTokenInfo[] token = userInfo[i].getTokens();
                        if (tokens != null && token.length > 0) {
                            UserTokenSetParser setParser = new UserTokenSetParser(token);
                            Iterator sets = setParser.getTokenSets().iterator();
                            while (sets.hasNext()) {
                                String set = (String)sets.next();
                                if (set.equals("")) {
                                    //System.out.println("Default Token Set");
                                } else {
                                    //System.out.println("Token Set: " + set);
                                }
                                Iterator toks = setParser.getTokensForSet(set).iterator();
                                while (toks.hasNext()) {
                                    Dispositivo tokend = new Dispositivo();
                                    UserTokenInfo disp = (UserTokenInfo)toks.next();
                                    if (disp.getState() == State.CURRENT) {
                                        tokend.setUsuarioId(disp.getUserid());
                                        //System.out.println(disp.getUserid());
                                        tokend.setGrupo(disp.getGroup());
                                        //System.out.println(disp.getGroup());
                                        tokend.setIdentificador(disp.getSerialNumber());
                                        //System.out.println(disp.getSerialNumber());
                                        if (disp.getTokenSet().toUpperCase().indexOf("SFTKN") > 0) {
                                            tokend.setTipo("SFTKN");
                                        } else if (disp.getTokenSet() == null||disp.getTokenSet().isEmpty() ||disp.getTokenSet().equals("")) {
                                            tokend.setTipo("TOKEN");
                                        } else
                                            tokend.setTipo("TOKEN");
                                        tokens.add(tokend);
                                    }
                                }
                            }
                        }
                    }
                }

                /*******66666666********/
                if (tokens != null)
                    dispositivos.addAll(tokens);
                //if (userinfo[0].isOTPAllowed())
                dispositivos.add(new Dispositivo("OTP", ""));
                /************444444444444**************/
            } catch (AdminServiceFault ase) {
                oLogger.error("Error de sistema " + ase.getMessage());
                throw new RuntimeException("Error de sistema" +
                                           ase.getErrorMessage());
            } catch (RemoteException e) {
                oLogger.error("Problemas de Conexion:" + e.getMessage());
                throw new RuntimeException("Problemas de Conexion:" +
                                           e.getMessage());
            }

            /*******************2222222222222******************/
            Dispositivo disp = Dispositivos.buscar(dispositivos, "OTP");
            //if(grupo.equals( BANCA_PERSONAS )){
            try {
                long celular = smsSrv.obtenerCelular(usuarioId);
                if (disp != null && celular > 0) {
                    oLogger.debug("El ususario " + usuarioId +
                                  " tiene configurado envio a sms");
                    dispositivos.add(new Dispositivo("SMS",
                                                     String.valueOf(celular),
                                                     String.valueOf("")));
                }
            } catch (Exception e) {
                oLogger.debug("ERROR CARGANDO SMS!!!!: " + e.getMessage());
                //                e.printStackTrace();
            }
            //}

            if (disp != null)
                dispositivos.remove(disp);

            if (dispositivos == null || dispositivos.size() == 0) {
                String msg = "El usuario '" + usuarioId + "' del grupo '" + grupo + "' no tiene algun sfa activo";
                throw new UsuarioSinDesafioException(msg);
            }
            /**************1111111111111111******************/

            response = ListarDesafiosRequestBuilder.crearRespuesta(dispositivos);

        } catch (Throwable e) {
            System.out.println("Error al tratar de listar desafios para usuario '" + usuarioId + "'  del grupo '" + grupo + "'. Error: " + e.getMessage());
            oLogger.error("Error al tratar de listar desafios para usuario '" + usuarioId + "'  del grupo '" + grupo + "'. Error: " + e.getMessage());
            e.printStackTrace();
            response.setEstado(DesafioEstados.createEstadoError(e));
        }

        return response;
    }

    private void dumpRequest(SolicitudDesafioType request) {
        oLogger.debug("UsuarioId: " + request.getUsuarioId());
        oLogger.debug("Grupo: " + request.getTipoCliente());
        oLogger.debug("IPCliente: " + request.getIPCliente());
        oLogger.debug("TipoDispositivo: " + request.getTipoDesafio());
    }

    private DesafioType getDesafioCoord(DesafioGrilla d) {
        DesafioType desafio = getDesafio(d);

        CoordenadasType coords = new CoordenadasType();
        for (CoordenadaType c : d.getCoordenadas().getCoordenada()) {
            CoordenadaType coord = new CoordenadaType();
            coord.setFila(c.getFila());
            coord.setColumna(c.getColumna() + "");

            coords.getCoordenada().add(coord);
        }

        desafio.setCoordenadas(coords);

        return desafio;
    }

    private DesafiosType procesarDesafio(String t, String usuarioId, String grupo)
            throws SfaException {
        DesafiosType resp = new DesafiosType();
        DesafioType desafio = null;
        if (t == "GRILLA" || t == "TOKEN" || t == "SFTKN")
            resp = crearDesafio(usuarioId, grupo, t);
        else if (t != "OTP"){
            desafio = new DesafioType(t);
            resp.getDesafio().add(desafio);
        }
        return resp;
    }

    private DesafiosType crearDesafio(String usuarioId, String grupo, String tipoDispositivo) throws SfaException {
        return desafioFactory.crearDesafio(usuarioId, grupo, tipoDispositivo);
    }

    private DesafioType getDesafio(DesafioType d) {
        DesafioType desafio = new DesafioType();
        desafio.setIdentificador(d.getIdentificador());
        desafio.setTipoDesafio(d.getTipoDesafio());
        return desafio;
    }
}

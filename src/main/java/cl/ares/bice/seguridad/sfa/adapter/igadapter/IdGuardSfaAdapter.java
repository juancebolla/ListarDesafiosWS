package cl.ares.bice.seguridad.sfa.adapter.igadapter;


import cl.ares.bice.seguridad.sfa.adapter.ISfaAdapter;
import cl.ares.bice.seguridad.sfa.adapter.SfaAdapterException;
import cl.ares.bice.seguridad.sfa.factory.util.SmsMessageBuilder;
import cl.ares.bice.seguridad.sfa.modelo.Dispositivo;
import cl.ares.bice.seguridad.sfa.modelo.Dispositivos;
import cl.ares.bice.seguridad.sfa.modelo.Otp;
import cl.ares.bice.seguridad.sfa.servicio.RespuestaDesafioAutenticado;
import cl.ares.bice.seguridad.sfa.servicio.RespuestaDesafioInvalido;
import cl.ares.bice.seguridad.sfa.servicio.SfaException;
import cl.ares.bice.seguridad.sfa.servicio.UsuarioBloqueadoException;
import cl.ares.bice.seguridad.sfa.servicio.UsuarioNoExisteException;
import cl.ares.bice.seguridad.sfa.servicio.UsuarioNoSolicitoDesafioException;
import cl.ares.bice.seguridad.sfa.servicio.UsuarioSinDesafioException;
import cl.ares.bice.seguridad.sfa.servicio.UsuarioSuspendidoException;
//import cl.ares.bice.seguridad.sfa.servicio.impl.SmsSerivicioImpl;
import cl.ares.bice.ws.servicio.AtributoMensajeType;
import cl.ares.bice.ws.servicio.CoordenadaType;
import cl.ares.bice.ws.servicio.CoordenadasType;
import cl.ares.bice.ws.servicio.DesafioType;
import cl.ares.bice.ws.servicio.DesafiosType;
import cl.ares.bice.ws.servicio.ListOf_NameValue;
import cl.ares.bice.ws.servicio.MensajeDesafioType;
import cl.ares.bice.ws.utiles.LoggerUtil;

import com.entrust.identityGuard.authenticationManagement.wsv9.AuthenticateGenericChallengeCallParms;
import com.entrust.identityGuard.authenticationManagement.wsv9.AuthenticateGenericChallengeExCallParms;
import com.entrust.identityGuard.authenticationManagement.wsv9.AuthenticationFault;
import com.entrust.identityGuard.authenticationManagement.wsv9.AuthenticationServiceFault;
import com.entrust.identityGuard.authenticationManagement.wsv9.AuthenticationType;
import com.entrust.identityGuard.authenticationManagement.wsv9.AuthenticationTypeEx;
import com.entrust.identityGuard.authenticationManagement.wsv9.Challenge;
import com.entrust.identityGuard.authenticationManagement.wsv9.ChallengeSet;
import com.entrust.identityGuard.authenticationManagement.wsv9.GenericAuthenticateParms;
import com.entrust.identityGuard.authenticationManagement.wsv9.GenericAuthenticateParmsEx;
import com.entrust.identityGuard.authenticationManagement.wsv9.GenericAuthenticateResponse;
import com.entrust.identityGuard.authenticationManagement.wsv9.GenericAuthenticateResponseEx;
import com.entrust.identityGuard.authenticationManagement.wsv9.GenericChallenge;
import com.entrust.identityGuard.authenticationManagement.wsv9.GenericChallengeEx2;
import com.entrust.identityGuard.authenticationManagement.wsv9.GenericChallengeParms;
import com.entrust.identityGuard.authenticationManagement.wsv9.GenericChallengeParmsEx2;
import com.entrust.identityGuard.authenticationManagement.wsv9.GetGenericChallengeCallParms;
import com.entrust.identityGuard.authenticationManagement.wsv9.GetGenericChallengeEx2CallParms;
import com.entrust.identityGuard.authenticationManagement.wsv9.MachineSecret;
import com.entrust.identityGuard.authenticationManagement.wsv9.NameValue;
import com.entrust.identityGuard.authenticationManagement.wsv9.Response;
import com.entrust.identityGuard.authenticationManagement.wsv9.TokenTransactionMode;
import com.entrust.identityGuard.userManagement.wsv9.AdminServiceFault;
import com.entrust.identityGuard.userManagement.wsv9.ErrorCode;
import com.entrust.identityGuard.userManagement.wsv9.State;
import com.entrust.identityGuard.userManagement.wsv9.UserCardInfo;
import com.entrust.identityGuard.userManagement.wsv9.UserFilter;
import com.entrust.identityGuard.userManagement.wsv9.UserGetParms;
import com.entrust.identityGuard.userManagement.wsv9.UserInfo;
import com.entrust.identityGuard.userManagement.wsv9.UserListCallParms;
import com.entrust.identityGuard.userManagement.wsv9.UserListResult;
import com.entrust.identityGuard.userManagement.wsv9.UserLockoutParms;
import com.entrust.identityGuard.userManagement.wsv9.UserOTPFilter;
import com.entrust.identityGuard.userManagement.wsv9.UserOTPGetCallParms;
import com.entrust.identityGuard.userManagement.wsv9.UserOTPInfo;
import com.entrust.identityGuard.userManagement.wsv9.UserParms;
import com.entrust.identityGuard.userManagement.wsv9.UserSetCallParms;
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

import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.log4j.Logger;


public class IdGuardSfaAdapter {//implements ISfaAdapter {

    private static final Logger log = LoggerUtil.getLoggerInput(IdGuardSfaAdapter.class);
    
    cl.ares.bice.ws.servicio.MachineSecret secretMachine = null;
    AdminConnectionManager9 adminConnManager = new AdminConnectionManager9();
    AuthConnectionManager9 authConnManager = new AuthConnectionManager9();
    DesafioFactory desafioFactory = new DesafioFactory();
    DesafioTokenBuilder desafioToken = new DesafioTokenBuilder();
    DesafioSftknBuilder desafioSftkn = new DesafioSftknBuilder();
    DesafioGrillaBuilder desafioGrilla = new DesafioGrillaBuilder();

    public void setAdminConnManager(AdminConnectionManager9 adminConnManager){
        this.adminConnManager = adminConnManager;
    }

    public void setAuthConnManager(AuthConnectionManager9 authConnManager){
        this.authConnManager = authConnManager;
    }

    public void setDesafioFactory(DesafioFactory desafioFactory) {
        this.desafioFactory = desafioFactory;
    }
    
    public void setSecretMachine(cl.ares.bice.ws.servicio.MachineSecret secretMachine) {
        this.secretMachine = secretMachine;
    }

    public cl.ares.bice.ws.servicio.MachineSecret getSecretMachine() {
        return secretMachine;
    }

    public Otp obtenerOtp(String usuarioId, String grupo){
        try {
            UserOTPInfo otpInfo = adminConnManager.getBinding().userOTPGet(new UserOTPGetCallParms(getIgUserId(usuarioId, grupo), new UserOTPFilter()))[0];

            Otp otp = new Otp(usuarioId, grupo);
            if (otpInfo.getOTP() != null)
                otp.setOtp(otpInfo.getOTP());


            return otp;
        } catch (RemoteException e) {
            throw new SfaAdapterException(e);
        }
    }

    public void desbloquearUsuario(String usuarioId, String grupo) {
        try {
            UserLockoutParms a = new UserLockoutParms();
            UserParms userParms = new UserParms();
            a.setClearlockout(Boolean.TRUE);
            a.setIncreaseLockout(Boolean.TRUE);
            a.setAuthenticatorLockoutIds(null);
            userParms.setLockoutParms(a);
            UserSetCallParms userSetCallParms = new UserSetCallParms();
            userSetCallParms.setUserid( getIgUserId(usuarioId, grupo));
            userSetCallParms.setParms(userParms);
            adminConnManager.getBinding().userSet(userSetCallParms);
        } catch (RemoteException e) {
            throw new SfaAdapterException(e);
        }
    }

    public void autentificar(String usuarioId, String grupo, String respuestaDesafio, String ip, String tipoDispositivo)
            throws UsuarioNoSolicitoDesafioException, RespuestaDesafioInvalido, UsuarioBloqueadoException, UsuarioNoExisteException, UsuarioSuspendidoException
    {
        //System.out.println("AQUI1");
        try {
            //System.out.println("AQUI1");
            GenericAuthenticateParms parms = new GenericAuthenticateParms();
            //System.out.println("AQUI1");
            parms.setAuthenticationType(AuthenticationTypeBuilder.getAuthenticationType(tipoDispositivo));
            //System.out.println("AQUI1");
          //  parms.setIPAddress(ip);
            String[] respDesafioArr = RespuestaDesafioBuilder.descomponerDesafio(respuestaDesafio, tipoDispositivo);
            //System.out.println("AQUI1");
            //LogHelper.dumpRespuestaDesafio(log, respDesafioArr);
            GenericAuthenticateResponse resp = authConnManager.getBinding().authenticateGenericChallenge(new AuthenticateGenericChallengeCallParms(getIgUserId(usuarioId, grupo),new Response(null, respDesafioArr, null),parms));
           // CardData ci = resp.getCardInfo();
        } catch(AuthenticationFault e){
            LogHelper.logFault(log, e);
            if( "USER_NO_CHALLENGE".equals(e.getErrorCode().getValue()))
                throw new UsuarioNoSolicitoDesafioException(e.getErrorMessage());
            if("INVALID_RESPONSE".equals(e.getErrorCode().getValue()))
                throw new RespuestaDesafioInvalido(e.getErrorMessage());
            if("AUTH_FAILED_USER_LOCKED".equals(e.getErrorCode().getValue()) ||
                    "USER_LOCKED".equals(e.getErrorCode().getValue()))
                throw new UsuarioBloqueadoException(e.getErrorMessage());
            if("USER_DOES_NOT_EXIST".equals(e.getErrorCode().getValue()))
                throw new UsuarioNoExisteException(e.getErrorMessage());
            if("USER_SUSPENDED".equals(e.getErrorCode().getValue()))
                throw new UsuarioSuspendidoException(e.getErrorMessage());
            throw new SfaAdapterException(e);
        }catch (RemoteException e) {
             throw new SfaAdapterException(e);
        }


    }

    private NameValue[]     nameValueIDG(MensajeDesafioType mensaje){
        List<NameValue> mensajeIDG;
        mensajeIDG = new ArrayList<NameValue>();
        //List<AtributoMensajeType> atributoMensaje = mensaje.getAtributoMensaje();
        NameValue paso = null;
        log.debug(mensaje.getAtributoMensaje().isEmpty());
        for (AtributoMensajeType temp : mensaje.getAtributoMensaje()) {
            paso = new NameValue();
            paso.setName(temp.getNombreCampo());
            paso.setValue(temp.getValorCampo());
            mensajeIDG.add(paso);
        }
        return mensajeIDG.toArray(new NameValue[mensaje.getAtributoMensaje().size()]);
    }
    
    //FIXME Metodo nuevo para convertir Machine secret de entrada
    private MachineSecret convertMachineSecret(cl.ares.bice.ws.servicio.MachineSecret machine){
        try{
            log.debug("inicio convertMachineSecret");
            MachineSecret mSecret = new MachineSecret();
            List<NameValue> values = new ArrayList<NameValue>();
            ListOf_NameValue listNameValues = machine.getApplicationData();
            NameValue paso = null;
            int listSize = 0;
            log.debug("inicio nameValues");
            if(listNameValues!=null && listNameValues.getItem()!=null && listNameValues.getItem().size()>0)
                listSize = listNameValues.getItem().size();
                for (cl.ares.bice.ws.servicio.NameValue temp : listNameValues.getItem()) {
                    paso = new NameValue();
                    paso.setName(temp.getName());
                    paso.setValue(temp.getValue());
                    values.add(paso);
                    log.debug("Name: "+temp.getName()+", Value: "+temp.getValue());
                }
            log.debug("fin nameValues");
            mSecret.setMachineLabel(machine.getMachineLabel());
            mSecret.setMachineNonce(machine.getMachineNonce());
            mSecret.setSequenceNonce(machine.getSequenceNonce());
            mSecret.setApplicationData(values.toArray(new NameValue[listSize]));
            log.debug("fin convertMachineSecret");
            return mSecret;
        }catch (NullPointerException npe){
            npe.printStackTrace();
            return null;
        }
    }
    
    private cl.ares.bice.ws.servicio.MachineSecret convertToResponseMachineSecret(MachineSecret machine){
        log.debug("Inicio convertToResponseMachineSecret");
        cl.ares.bice.ws.servicio.MachineSecret mSecret = new cl.ares.bice.ws.servicio.MachineSecret();
        List<cl.ares.bice.ws.servicio.NameValue> values = new ArrayList<cl.ares.bice.ws.servicio.NameValue>();
        ListOf_NameValue listNameValues = new ListOf_NameValue();
        cl.ares.bice.ws.servicio.NameValue paso = null;
        log.debug("Inicio nameValues");
        if(machine.getApplicationData()!=null && machine.getApplicationData().length>0)
            for (NameValue temp : machine.getApplicationData()) {
                paso = new cl.ares.bice.ws.servicio.NameValue();
                paso.setName(temp.getName());
                paso.setValue(temp.getValue());
                values.add(paso);
                log.debug("Name: "+temp.getName()+", Value: "+temp.getValue());
            }
        else
            log.debug("nameValues nulo o vacio");
        log.debug("Fin nameValues");
        listNameValues.setItem(values);
        mSecret.setMachineLabel(String.valueOf(machine.getMachineLabel()));
        mSecret.setMachineNonce(String.valueOf(machine.getMachineNonce()));
        mSecret.setSequenceNonce(String.valueOf(machine.getSequenceNonce()));
        mSecret.setApplicationData(listNameValues); 
        log.debug("Fin convertToResponseMachineSecret");
        return mSecret;
    }
    //FIN METODOS NUEVOS

    public void autentificar(String usuarioId, String grupo, String respuestaDesafio, String ip, String tipoDispositivo, String Canal, String mensajeUsuario, MensajeDesafioType mensaje, cl.ares.bice.ws.servicio.MachineSecret machineSecret)
            throws UsuarioNoSolicitoDesafioException, RespuestaDesafioInvalido, UsuarioBloqueadoException, UsuarioNoExisteException, UsuarioSuspendidoException
    {
        if(tipoDispositivo.equals("TOKEN_SIGN_ONLINE")||tipoDispositivo.equals("TOKEN_SIGN_OFFLINE")){
            try {
                AuthenticationTypeEx authType = AuthenticationTypeBuilder.getAuthenticationType2(tipoDispositivo);
            
                GenericAuthenticateParmsEx parms = new GenericAuthenticateParmsEx(); 
                Response resp = new Response();
                parms.setAuthenticationType(authType);
                log.debug("authType: " + authType.getValue());
                parms.setApplicationName(Canal);
                log.debug("Canal: "+ Canal);
                if(mensaje != null){
                    if(mensaje.getAtributoMensaje() != null){
                        log.debug(mensaje.getAtributoMensaje().size());
                        parms.setTransactionDetails(nameValueIDG(mensaje));
                    }
                }
                if(tipoDispositivo.equals("TOKEN_SIGN_ONLINE")){
                    parms.setTransactionId(respuestaDesafio);
                    log.debug("respuestaDesafio: "+respuestaDesafio);
                }else{
                    resp.setResponse(new String[] {respuestaDesafio});
                    log.debug("respuestaDesafio: "+respuestaDesafio);
                }
                //Cambios, agrega IP y MachineSecret a los parametros
                if(machineSecret != null){
                    MachineSecret macSecret = convertMachineSecret(machineSecret);
                    log.debug("MachineSecret data length: "+macSecret.getApplicationData().length);
                    if(macSecret!=null){
                        parms.setMachineSecret(macSecret);
                        parms.setIPAddress(ip);
                    }
                }
                AuthenticateGenericChallengeExCallParms callPrms = new AuthenticateGenericChallengeExCallParms();
                callPrms.setParms(parms);
                callPrms.setUserId(getIgUserId(usuarioId, grupo));
                if(tipoDispositivo.equals("TOKEN_SIGN_OFFLINE")){
                    callPrms.setResponse(resp);
                }
                log.debug("Parametros seteados!");
                GenericAuthenticateResponseEx challengeSet = authConnManager.getBinding().authenticateGenericChallengeEx(callPrms);
                //FIXME Cambios agrega MachineSecret a la salida                
                MachineSecret machine = challengeSet.getMachineSecret();
                if(machine!=null){
                    cl.ares.bice.ws.servicio.MachineSecret mSecret = new cl.ares.bice.ws.servicio.MachineSecret();
                    mSecret = convertToResponseMachineSecret(machine);
                    setSecretMachine(mSecret);
                }
            } catch (AuthenticationServiceFault e) {
                e.printStackTrace();
                if("USER_LOCKED".equals(e.getErrorCode().getValue()))
                    throw new UsuarioBloqueadoException(e.getErrorMessage());
                if("USER_SUSPENDED".equals(e.getErrorCode().getValue()))
                    throw new UsuarioSuspendidoException(e.getErrorMessage());
                String msg = "Error al crear desafio:" + e.getErrorCode() + " - " + e.getErrorMessage();
                log.error(msg);
                throw new SfaAdapterException(msg);
            } catch (RemoteException e){
                throw new SfaAdapterException(e);
            }
        }else{
            try {
                GenericAuthenticateParms parms = new GenericAuthenticateParms();
                parms.setAuthenticationType(AuthenticationTypeBuilder.getAuthenticationType(tipoDispositivo));
                if(machineSecret != null){
                    MachineSecret macSecret = convertMachineSecret(machineSecret);
                    if(macSecret!=null){
                        parms.setMachineSecret(macSecret);
                        parms.setIPAddress(ip);
                    }
                }
                //NameValue[] mensajeIDG = nameValueIDG(mensaje);
                //parms.setTransactionDetails(mensajeIDG);
                //parms.setIPAddress(ip);
                String[] respDesafioArr = RespuestaDesafioBuilder.descomponerDesafio(respuestaDesafio, tipoDispositivo);
                //LogHelper.dumpRespuestaDesafio(log, respDesafioArr);
                GenericAuthenticateResponse resp = authConnManager.getBinding().authenticateGenericChallenge(new AuthenticateGenericChallengeCallParms(getIgUserId(usuarioId, grupo),new Response(null, respDesafioArr, null),parms));
                MachineSecret machine = resp.getMachineSecret();
                if(machine!=null){
                    cl.ares.bice.ws.servicio.MachineSecret mSecret = new cl.ares.bice.ws.servicio.MachineSecret();
                    mSecret = convertToResponseMachineSecret(machine);
                    setSecretMachine(mSecret);
                }
               // CardData ci = resp.getCardInfo();
            } catch(AuthenticationFault e){
                LogHelper.logFault(log, e);
                if( "USER_NO_CHALLENGE".equals(e.getErrorCode().getValue()))
                    throw new UsuarioNoSolicitoDesafioException(e.getErrorMessage());
                if("INVALID_RESPONSE".equals(e.getErrorCode().getValue()))
                    throw new RespuestaDesafioInvalido(e.getErrorMessage());
                if("AUTH_FAILED_USER_LOCKED".equals(e.getErrorCode().getValue()) || "USER_LOCKED".equals(e.getErrorCode().getValue()))
                    throw new UsuarioBloqueadoException(e.getErrorMessage());
                if("USER_DOES_NOT_EXIST".equals(e.getErrorCode().getValue()))
                    throw new UsuarioNoExisteException(e.getErrorMessage());
                if("USER_SUSPENDED".equals(e.getErrorCode().getValue()))
                    throw new UsuarioSuspendidoException(e.getErrorMessage());
                throw new SfaAdapterException(e);
            }catch (RemoteException e) {
                 throw new SfaAdapterException(e);
            }

        }


    }

    public DesafioType crearDesafio(Dispositivo dispositvo) throws UsuarioBloqueadoException, UsuarioSuspendidoException {
        return crearDesafio(dispositvo.getUsuarioId(), dispositvo.getGrupo(), dispositvo.getTipo());
    }

    public List<Dispositivo> dipositivosDelUsuario(String usuarioId, String grupo)
            throws UsuarioNoExisteException, UsuarioBloqueadoException, UsuarioSuspendidoException, UsuarioSinDesafioException {
        try {
            //log.debug("usuario: "+ usuarioId + " grupo: "+grupo);
            System.out.println("[IdGuardSfaAdapter][dipositivosDelUsuario]usuario: "+ usuarioId + " grupo: "+grupo);

            UserInfo[] userInfo = getUserInfo(usuarioId, grupo);
            return getTipoAutUsuario(userInfo);
        } catch (RemoteException e) {
            //log.error("Problemas de Conexion:" + e.getMessage());
            throw new RuntimeException("Problemas de Conexion:" + e.getMessage());
        }
    }

    public DesafioType crearDesafio(String usuarioId, String grupo, String tipoDispositivo)
            throws UsuarioBloqueadoException, UsuarioSuspendidoException
    {
        try {
            AuthenticationType authType = AuthenticationTypeBuilder.getAuthenticationType(tipoDispositivo);

            GenericChallengeParms parms = new GenericChallengeParms();
            parms.setAuthenticationType(authType);
            if(tipoDispositivo.equals("SFTKN")){
                String[] TokenSets = {"SFTKN"};
                parms.setTokenSets(TokenSets);
            }
            GetGenericChallengeCallParms callPrms = new GetGenericChallengeCallParms(getIgUserId(usuarioId, grupo), parms);
            GenericChallenge challengeSet = authConnManager.getBinding().getGenericChallenge(callPrms);

            return desafioFactory.crearDesafio(challengeSet, tipoDispositivo);
        } catch (AuthenticationServiceFault e) {
            if("USER_LOCKED".equals(e.getErrorCode().getValue()))
                throw new UsuarioBloqueadoException(e.getErrorMessage());
            if("USER_SUSPENDED".equals(e.getErrorCode().getValue()))
                throw new UsuarioSuspendidoException(e.getErrorMessage());
            String msg = "Error al crear desafio:" + e.getErrorCode() + " - " + e.getErrorMessage();
            log.error(msg);
            throw new SfaAdapterException(msg);
        } catch (RemoteException e){
            throw new SfaAdapterException(e);
        }
    }
    
    public DesafioType crearDesafioSftkn(String usuarioId, String grupo, String tipoDispositivo, String ip, cl.ares.bice.ws.servicio.MachineSecret machineSecret)
            throws UsuarioBloqueadoException, UsuarioSuspendidoException, RespuestaDesafioAutenticado
    {
        try {
            AuthenticationType authType = AuthenticationTypeBuilder.getAuthenticationType(tipoDispositivo);

            GenericChallengeParms parms = new GenericChallengeParms();
            parms.setAuthenticationType(authType);
            if(tipoDispositivo.equals("SFTKN")){
                String[] TokenSets = {"SFTKN"};
                parms.setTokenSets(TokenSets);
            }
            if(machineSecret!=null){
                MachineSecret macSecret = new MachineSecret();
                macSecret = convertMachineSecret(machineSecret);
                if(macSecret!=null){
                    parms.setMachineSecret(macSecret);
                    parms.setIPAddress(ip);
                }
            }

            GetGenericChallengeCallParms callPrms = new GetGenericChallengeCallParms(getIgUserId(usuarioId, grupo), parms);
            GenericChallenge challengeSet = authConnManager.getBinding().getGenericChallenge(callPrms);

            log.debug(challengeSet.getChallengeRequestResult().getValue());
            if(challengeSet.getChallengeRequestResult().getValue().equalsIgnoreCase("AUTHENTICATED")) {
                MachineSecret machine = challengeSet.getMachineSecret();
                if(machine!=null){
                    cl.ares.bice.ws.servicio.MachineSecret mSecret = new cl.ares.bice.ws.servicio.MachineSecret();
                    mSecret = convertToResponseMachineSecret(machine);
                    setSecretMachine(mSecret);
                }
                else{
                    log.debug("challengeSet.getMachineSecret nulo o vacio");
                }
                throw new RespuestaDesafioAutenticado("");
            }

            return desafioFactory.crearDesafio(challengeSet, tipoDispositivo);
        } catch (AuthenticationServiceFault e) {
            if("USER_LOCKED".equals(e.getErrorCode().getValue()))
                throw new UsuarioBloqueadoException(e.getErrorMessage());
            if("USER_SUSPENDED".equals(e.getErrorCode().getValue()))
                throw new UsuarioSuspendidoException(e.getErrorMessage());
            String msg = "Error al crear desafio:" + e.getErrorCode() + " - " + e.getErrorMessage();
            log.error(msg);
            throw new SfaAdapterException(msg);
        } catch (RemoteException e){
            throw new SfaAdapterException(e);
        }
    }

    public List<String> getTokenSetsSFTRX(UserInfo[] userinfo){
        List<String> tokenSetsTRX = new ArrayList<String>();
        for (int i=0; i<userinfo.length; i++){
            if (userinfo[i].getTokens() != null && userinfo[i].getTokens().length > 0){
                UserTokenInfo[] tokens = userinfo[i].getTokens();
                if (tokens != null && tokens.length > 0){
                    UserTokenSetParser setParser = new UserTokenSetParser(tokens);
                    Iterator sets = setParser.getTokenSets().iterator();
                    while (sets.hasNext()){
                        String set = (String) sets.next();
                        Iterator toks = setParser.getTokensForSet(set).iterator();
                        while (toks.hasNext()){
                            UserTokenInfo disp = (UserTokenInfo) toks.next();
                            if(disp.getState()==State.CURRENT){
                                if(disp.getTokenSet().toUpperCase().contains("SFTRX".subSequence(1,5))){
                                    tokenSetsTRX.add(disp.getTokenSet());
                                }
                            }
                        }
                    }
                }
            }
        }
        return tokenSetsTRX;
    }

    public DesafiosType crearDesafio(String usuarioId, String grupo, String ip, String tipoDispositivo, String Canal, String mensajeUsuario, MensajeDesafioType mensaje, cl.ares.bice.ws.servicio.MachineSecret machineSecret)
            throws UsuarioBloqueadoException, UsuarioSuspendidoException, UsuarioSinDesafioException, RespuestaDesafioAutenticado
    {
        DesafiosType resp = new DesafiosType();
        List<String> tokenSetsSFTRX=null;
            if(tipoDispositivo.equals("TOKEN_SIGN_ONLINE")||tipoDispositivo.equals("TOKEN_SIGN_OFFLINE")){
                IdGuardSfaAdapter sfaAdapter = new IdGuardSfaAdapter();
                log.debug("ListarDispositivos");
                //List<Dispositivo> dispositivos = new ArrayList<Dispositivo>();
                try{
                    UserInfo[] userInfo = sfaAdapter.getUserInfo(usuarioId, grupo);
                    tokenSetsSFTRX = getTokenSetsSFTRX(userInfo);
                }catch(Exception e){
                    e.printStackTrace();
                }
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
                    String url=prop.getProperty("url.pooling");

                    AuthenticationTypeEx authType = AuthenticationTypeBuilder.getAuthenticationType2(tipoDispositivo);

                    GenericChallengeParmsEx2 parms = new GenericChallengeParmsEx2();
                    parms.setTokenTransactionQRCodeSize(220);
                    parms.setAuthenticationType(authType);
                    parms.setApplicationName(Canal);
                    parms.setTransactionDetails(nameValueIDG(mensaje));
                    parms.setTokenChallengeSummary(mensajeUsuario);
                    
                    //FIXME NUEVO SETMACHINESECRET CrearDesafio
                    if(machineSecret!=null){
                        MachineSecret macSecret = new MachineSecret();
                        macSecret = convertMachineSecret(machineSecret);
                        if(macSecret!=null){
                            parms.setMachineSecret(macSecret);
                            parms.setIPAddress(ip);
                        }
                    }
                    
                    String[] t = tokenSetsSFTRX.toArray(new String[0]);
                    parms.setTokenSets(t);
                    if(tipoDispositivo.equals("TOKEN_SIGN_ONLINE")){
                        parms.setTokenTransactionMode(TokenTransactionMode.ONLINE);
                        parms.setTokenDeliveryCallback(url);
                        log.debug("online");
                    }else{
                        parms.setTokenTransactionMode(TokenTransactionMode.OFFLINE);
                        parms.setTokenTransactionReturnQRCode(Boolean.TRUE);
                        log.debug("offline");
                    }
                    log.debug("Parametros seteados!");
                    GetGenericChallengeEx2CallParms callPrms = new GetGenericChallengeEx2CallParms(getIgUserId(usuarioId, grupo), parms);
                    GenericChallengeEx2 challengeSet = authConnManager.getBinding().getGenericChallengeEx2(callPrms);
                    
                    log.debug(challengeSet.getChallengeRequestResult().getValue());
                    if(challengeSet.getChallengeRequestResult().getValue().equalsIgnoreCase("AUTHENTICATED")) {
                        MachineSecret machine = challengeSet.getMachineSecret();
                        if(machine!=null){
                            cl.ares.bice.ws.servicio.MachineSecret mSecret = new cl.ares.bice.ws.servicio.MachineSecret();
                            mSecret = convertToResponseMachineSecret(machine);
                            setSecretMachine(mSecret);
                        }
                        else{
                            log.debug("challengeSet.getMachineSecret nulo o vacio");
                        }
                        throw new RespuestaDesafioAutenticado("");
                    }

                    resp = desafioSftkn.crear(challengeSet, tipoDispositivo);//, tipoDispositivo);
                    return resp;
                } catch (AuthenticationServiceFault e) {
                    if("USER_LOCKED".equals(e.getErrorCode().getValue()))
                        throw new UsuarioBloqueadoException(e.getErrorMessage());
                    if("USER_SUSPENDED".equals(e.getErrorCode().getValue()))
                        throw new UsuarioSuspendidoException(e.getErrorMessage());
                    String msg = "Error al crear desafio:" + e.getErrorCode() + " - " + e.getErrorMessage();
                    log.error(msg);
                    throw new SfaAdapterException(msg);
                } catch (RemoteException e){
                    throw new SfaAdapterException(e);
                }
            }else if(tipoDispositivo.equals("OTP2")){
                IdGuardSfaAdapter sfaAdapter = new IdGuardSfaAdapter();
                try{
                    UserInfo[] userInfo = sfaAdapter.getUserInfo(usuarioId, grupo);
                    tokenSetsSFTRX = getTokenSetsSFTRX(userInfo);
                }catch(Exception e){
                    e.printStackTrace();
                }
                try {    
                    AuthenticationTypeEx authType = AuthenticationTypeBuilder.getAuthenticationType2("OTP");

                    GenericChallengeParmsEx2 parms = new GenericChallengeParmsEx2();
                    parms.setAuthenticationType(authType);
                    
                    //FIXME NUEVO SETMACHINESECRET CrearDesafio
                    if(machineSecret!=null){
                        MachineSecret macSecret = new MachineSecret();
                        macSecret = convertMachineSecret(machineSecret);
                        if(macSecret!=null){
                            parms.setMachineSecret(macSecret);
                            parms.setIPAddress(ip);
                        }
                    }
                                        log.debug("Parametros seteados!");
                    GetGenericChallengeEx2CallParms callPrms = new GetGenericChallengeEx2CallParms(getIgUserId(usuarioId, grupo), parms);
                    GenericChallengeEx2 challengeSet = authConnManager.getBinding().getGenericChallengeEx2(callPrms);

                    log.debug(challengeSet.getChallengeRequestResult().getValue());
                    if(challengeSet.getChallengeRequestResult().getValue().equalsIgnoreCase("AUTHENTICATED")) {
                        MachineSecret machine = challengeSet.getMachineSecret();
                        if(machine!=null){
                            cl.ares.bice.ws.servicio.MachineSecret mSecret = new cl.ares.bice.ws.servicio.MachineSecret();
                            mSecret = convertToResponseMachineSecret(machine);
                            setSecretMachine(mSecret);
                        }
                        else{
                            log.debug("challengeSet.getMachineSecret nulo o vacio");
                        }
                        throw new RespuestaDesafioAutenticado("");
                    }
                    DesafioType desafio = new DesafioType(tipoDispositivo);
                    desafio.setIdentificador("0");
                    resp.getDesafio().add(desafio);
                    return resp;
                } catch (AuthenticationServiceFault e) {
                    if("USER_LOCKED".equals(e.getErrorCode().getValue()))
                        throw new UsuarioBloqueadoException(e.getErrorMessage());
                    if("USER_SUSPENDED".equals(e.getErrorCode().getValue()))
                        throw new UsuarioSuspendidoException(e.getErrorMessage());
                    String msg = "Error al crear desafio:" + e.getErrorCode() + " - " + e.getErrorMessage();
                    log.error(msg);
                    throw new SfaAdapterException(msg);
                } catch (RemoteException e){
                    throw new SfaAdapterException(e);
                }
            }else if(tipoDispositivo.equals("SFTKN")||tipoDispositivo.equals("TOKEN")){
            DesafioType desafio = crearDesafioSftkn(usuarioId, grupo, tipoDispositivo, ip, machineSecret);
            resp.getDesafio().add(desafio);
            return resp;
            /*try {
                //CacheFactory.ensureCluster();
                //NamedCache cacheAPP = CacheFactory.getCache("cacheDesafiosAPP");
                //String url=((String)cacheAPP.get("url.pooling"));

                AuthenticationTypeEx authType = AuthenticationTypeBuilder.getAuthenticationType2(tipoDispositivo);

                GenericChallengeParmsEx2 parms = new GenericChallengeParmsEx2();
                parms.setTokenTransactionQRCodeSize(220);
                parms.setAuthenticationType(authType);
                parms.setApplicationName(Canal);
                parms.setTransactionDetails(nameValueIDG(mensaje));
                parms.setTokenChallengeSummary(mensajeUsuario);
                String[] t = {"SFTKN"};
                parms.setTokenSets(t);
                GetGenericChallengeEx2CallParms callPrms = new GetGenericChallengeEx2CallParms(getIgUserId(usuarioId, grupo), parms);
                GenericChallengeEx2 challengeSet = authConnManager.getBinding().getGenericChallengeEx2(callPrms);

                resp = desafioSftkn.crear(challengeSet, tipoDispositivo);//, tipoDispositivo);
                return resp;
            } catch (AuthenticationServiceFault e) {
                if("USER_LOCKED".equals(e.getErrorCode().getValue()))
                    throw new UsuarioBloqueadoException(e.getErrorMessage());
                if("USER_SUSPENDED".equals(e.getErrorCode().getValue()))
                    throw new UsuarioSuspendidoException(e.getErrorMessage());
                String msg = "Error al crear desafio:" + e.getErrorCode() + " - " + e.getErrorMessage();
                log.error(msg);
                throw new SfaAdapterException(msg);
            } catch (RemoteException e){
                throw new SfaAdapterException(e);
            }*/
        }else{
            try {
                AuthenticationType authType = AuthenticationTypeBuilder.getAuthenticationType(tipoDispositivo);
                GenericChallengeParms parms = new GenericChallengeParms();
                parms.setAuthenticationType(authType);
                if(machineSecret!=null){
                    MachineSecret macSecret = new MachineSecret();
                    macSecret = convertMachineSecret(machineSecret);
                    if(macSecret!=null){
                        parms.setMachineSecret(macSecret);
                        parms.setIPAddress(ip);
                    }
                }
                GetGenericChallengeCallParms callPrms = new GetGenericChallengeCallParms(getIgUserId(usuarioId, grupo), parms);
                GenericChallenge challengeSet = authConnManager.getBinding().getGenericChallenge(callPrms);
                log.debug(challengeSet.getChallengeRequestResult().getValue());
                if(challengeSet.getChallengeRequestResult().getValue().equalsIgnoreCase("AUTHENTICATED")) {
                    MachineSecret machine = challengeSet.getMachineSecret();
                    if(machine!=null){
                        cl.ares.bice.ws.servicio.MachineSecret mSecret = new cl.ares.bice.ws.servicio.MachineSecret();
                        mSecret = convertToResponseMachineSecret(machine);
                        setSecretMachine(mSecret);
                    }
                    else{
                        log.debug("challengeSet.getMachineSecret nulo o vacio");
                    }
                    throw new RespuestaDesafioAutenticado("");
                }

                if(tipoDispositivo.equals("GRILLA")||tipoDispositivo.equals("GRID")){
                    DesafioType desafio = new DesafioType();
                    ChallengeSet gridChallenge = challengeSet.getGridChallenge();
                    List<CoordenadaType> coords = new ArrayList<CoordenadaType>();
                    CoordenadaType coord = new CoordenadaType();

                    Challenge[] challArr = gridChallenge.getChallenge();
                    for (int i = 0; i < challArr.length; i++) {
                        Challenge chall = challArr[i];
                        String col;
                        int fila;
                        col = (char)(chall.getColumn() + (int) 'A') + "";
                        log.debug(col);
                        fila = chall.getRow() + 1;
                        coord = new CoordenadaType(fila, col);
                        coords.add(coord);
                        log.debug(coords.size());
                    }
                    CoordenadasType coordenada = new CoordenadasType();
                    coordenada.setCoordenada(coords);

                    desafio.setTipoDesafio("GRILLA");
                    desafio.setIdentificador(gridChallenge.getCardSerialNumbers()[0]);
                    desafio.setCoordenadas(coordenada);
                    //desafioGrilla.crear(challengeSet);//, tipoDispositivo);
                    resp.getDesafio().add(desafio);//, tipoDispositivo);
                    return resp;
                }else{
                    //, tipoDispositivo);
                    resp.getDesafio().add(desafioToken.crear(challengeSet));//, tipoDispositivo);
                    return resp;
                }
            } catch (AuthenticationServiceFault e) {
                if("USER_LOCKED".equals(e.getErrorCode().getValue()))
                    throw new UsuarioBloqueadoException(e.getErrorMessage());
                if("USER_SUSPENDED".equals(e.getErrorCode().getValue()))
                    throw new UsuarioSuspendidoException(e.getErrorMessage());
                String msg = "Error al crear desafio:" + e.getErrorCode() + " - " + e.getErrorMessage();
                log.error(msg);
                throw new SfaAdapterException(msg);
            } catch (RemoteException e){
                throw new SfaAdapterException(e);
            }
            
        }
    }

    //private
    public UserInfo[] getUserInfo(String usuarioId, String grupo) throws RemoteException {
    	UserInfo[] users = null;
    	try {
    	System.out.println("[IdGuardSfaAdapter][getUserInfo][INICIO2]usuario,grupo:"+usuarioId+","+grupo);
        UserFilter filter = new UserFilter();
        filter.setUserid(usuarioId);
        if(grupo.length()>0){
            ArrayList<String> groups = new ArrayList<String>();
            groups.add(grupo);
            filter.setGroups(groups.toArray(new String[groups.size()]));
        }
        UserGetParms getParms = new UserGetParms();

        getParms.setGetActivationExpiryInfo(Boolean.TRUE);
        getParms.setGetAuthenticationSecrets(Boolean.TRUE);
        getParms.setGetCards(Boolean.TRUE);
        getParms.setGetCertificates(Boolean.TRUE);
        getParms.setGetDigitalIds(Boolean.TRUE);
        getParms.setGetExpectedLocations(Boolean.TRUE);
        getParms.setGetFederations(Boolean.TRUE);
        getParms.setGetGrid(Boolean.TRUE);
        getParms.setGetLocationHistory(Boolean.TRUE);
        getParms.setGetMachineSecrets(Boolean.TRUE);
        getParms.setGetOTP(Boolean.TRUE);
        getParms.setGetPassword(Boolean.TRUE);
        getParms.setGetPIN(Boolean.TRUE);
        getParms.setGetPVN(Boolean.TRUE);
        getParms.setGetQASecrets(Boolean.TRUE);
        getParms.setGetSharedSecrets(Boolean.TRUE);
        getParms.setGetSmartCredentials(Boolean.TRUE);
        getParms.setGetTokens(Boolean.TRUE);
        getParms.setGetUserContactInfo(Boolean.TRUE);

    	System.out.println("[IdGuardSfaAdapter][getUserInfo]llamado a userList");
    	UserListResult result = adminConnManager.getBinding().userList(new UserListCallParms(filter, getParms));
    	users = result.getUsers();
    	
		} catch (Exception e) {
			System.out.println("[IdGuardSfaAdapter][getUserInfo]Exception:"+e.getMessage());
		}
        System.out.println("[IdGuardSfaAdapter][getUserInfo][FIN]users.size:"+( users != null ? users.length:"null"));
        return users;
    }

    //private
    public List<Dispositivo> getTipoAutUsuario(UserInfo[] userinfo) {
    	System.out.println("[IdGuardSfaAdapter][getTipoAutUsuario] Inicio");
        List<Dispositivo> tiposAuth = new ArrayList<Dispositivo>();
        List<Dispositivo> tokens = new ArrayList<Dispositivo>();
        List<Dispositivo> cards = new ArrayList<Dispositivo>();
        //List<Dispositivo> otps = new ArrayList<Dispositivo>();

        if(userinfo.length>0){
            cards = getActiveCard(userinfo);
            if(cards != null)
                tiposAuth.addAll(cards);

            tokens = getActiveToken(userinfo);
            if(tokens != null)
                    tiposAuth.addAll(tokens);
            //if (userinfo[0].isOTPAllowed())
            tiposAuth.add( new Dispositivo("OTP", "") );
        }

        System.out.println("[IdGuardSfaAdapter][getTipoAutUsuario] FIN, tiposAutu.size:" + (tiposAuth != null ? tiposAuth.size():"null"));
        return tiposAuth;
    }

    //private
    public List<Dispositivo> getActiveToken(UserInfo[] userinfo){
    	System.out.println("[IdGuardSfaAdapter][getActiveToken] INICIO");
    	List<Dispositivo> listaTokens = new ArrayList<Dispositivo>();
    	for (int i=0; i<userinfo.length; i++){
            if (userinfo[i].getTokens() != null && userinfo[i].getTokens().length > 0){
    	    	UserTokenInfo[] tokens = userinfo[i].getTokens();
    	        if (tokens != null && tokens.length > 0){
    	            UserTokenSetParser setParser = new UserTokenSetParser(tokens);
    	            Iterator sets = setParser.getTokenSets().iterator();
    	            while (sets.hasNext()){
    	               String set = (String) sets.next();
    	               Iterator toks = setParser.getTokensForSet(set).iterator();
    	               while (toks.hasNext()){
    	            	   Dispositivo token = new Dispositivo();
    	            	   UserTokenInfo disp = (UserTokenInfo) toks.next();
    	            	   if(disp.getState()==State.CURRENT){
                               token.setUsuarioId(disp.getUserid());
                               //System.out.println(disp.getUserid());
                               token.setGrupo(disp.getGroup());
                               //System.out.println(disp.getGroup());
                               token.setIdentificador(disp.getSerialNumber());
                               //System.out.println(disp.getSerialNumber());
                               token.setOnline(disp.isDeliveryAndSignatureSupported());
                               token.setAliasUsuario(disp.getTokenSet());
                               if(disp.getTokenSet().toUpperCase().contains("SFTRX".subSequence(1,5))){
                                   //log.debug(disp.getTokenSet());
                                   //log.debug(disp.getTokenType()+" - SFTRX");
                                   if(disp.isDeliveryAndSignatureSupported()){
                                       Dispositivo token2 = new Dispositivo();
                                       token2.setUsuarioId(disp.getUserid());
                                       token2.setGrupo(disp.getGroup());
                                       token2.setIdentificador(disp.getSerialNumber());
                                       token2.setOnline(disp.isDeliveryAndSignatureSupported());
                                       token2.setAliasUsuario(disp.getTokenSet());
                                       token2.setTipo("TOKEN_SIGN_ONLINE");
                                       listaTokens.add(token2);
                                   }
                                   token.setTipo("TOKEN_SIGN_OFFLINE");
                               }else if(disp.getTokenSet().toUpperCase().trim().equals("SFTKN")){
                                   //log.debug(disp.getTokenType()+" - SFTKN");
                                   token.setTipo("SFTKN");
                               }else{
                                   //log.debug(disp.getTokenSet());
                                   //log.debug(disp.getTokenType()+" - TOKEN");
                                   token.setTipo("TOKEN");
                               }
                               listaTokens.add(token);
                            }
                        }
                    }
                }
            }
        }
    	System.out.println("[IdGuardSfaAdapter][getActiveToken] FIN, listaTokens.size:" + (listaTokens != null ? listaTokens.size():"null"));
        return listaTokens;
    }

    //private
    public List<Dispositivo> getActiveCard(UserInfo[] userinfo){
    	List<Dispositivo> listaCards = new ArrayList<Dispositivo>();
        for (int i=0; i<userinfo.length; i++){
	        if (userinfo[i].getCards() != null && userinfo[i].getCards().length > 0){
	            for(UserCardInfo c : userinfo[i].getCards()){
	                if(c.getState() == State.CURRENT)
	                	listaCards.add( new Dispositivo("GRILLA", c.getSerialNumber(), c.getGroup()));
	            }
	        }
        }
        return listaCards;
    }

    //private
    public String getIgUserId(String userId, String group){
        return group + "/" + userId; 
    }

    public DesafiosType crear(String usuarioId, String grupo, String tipoDispositivo) throws SfaException {
        //SmsSerivicioImpl sms = new SmsSerivicioImpl();
        SmsMessageBuilder messageBuilder = new SmsMessageBuilder();
        log.debug(usuarioId + "||" + grupo);
        List<Dispositivo> dispositivos = dipositivosDelUsuario(usuarioId, grupo);
        Dispositivo disp = Dispositivos.buscar(dispositivos, "OTP");
        /*if(disp == null)
            throw new UsuarioSinDesafioException("Se requiere que el usuario tenga configurado OTP para envio SMS");
    */

        long telefono = 0;//sms.obtenerCelular(usuarioId);
        if(telefono == 0)
            throw new UsuarioSinDesafioException("El usuario no tiene configurado un numero en el sistema SMS");

        crearDesafio(usuarioId, grupo, "OTP");
        Otp otp = obtenerOtp(usuarioId, grupo);
        String clave = otp.getOtp();
        
        //sms.enviarSms(telefono, messageBuilder.crearMensaje(clave));
        
        DesafiosType resp = new DesafiosType();
        resp.getDesafio().add(new DesafioType("SMS", String.valueOf( telefono )));

        return resp;
    }
}

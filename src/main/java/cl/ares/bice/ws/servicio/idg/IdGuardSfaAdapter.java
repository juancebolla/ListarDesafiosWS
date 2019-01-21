package cl.ares.bice.ws.servicio.idg;

import cl.ares.bice.seguridad.sfa.adapter.SfaAdapterException;
import cl.ares.bice.seguridad.sfa.adapter.igadapter.AdminConnectionManager9;
import cl.ares.bice.seguridad.sfa.adapter.igadapter.AuthConnectionManager9;
import cl.ares.bice.seguridad.sfa.adapter.igadapter.AuthenticationTypeBuilder;
import cl.ares.bice.seguridad.sfa.adapter.igadapter.DesafioFactory;
import cl.ares.bice.seguridad.sfa.adapter.igadapter.LogHelper;
import cl.ares.bice.seguridad.sfa.adapter.igadapter.RespuestaDesafioBuilder;
import cl.ares.bice.seguridad.sfa.modelo.Desafio;
import cl.ares.bice.seguridad.sfa.modelo.Dispositivo;
import cl.ares.bice.seguridad.sfa.modelo.Otp;
import cl.ares.bice.seguridad.sfa.modelo.TipoDispositivo;
import cl.ares.bice.seguridad.sfa.servicio.RespuestaDesafioInvalido;
import cl.ares.bice.seguridad.sfa.servicio.UsuarioBloqueadoException;
import cl.ares.bice.seguridad.sfa.servicio.UsuarioNoExisteException;
import cl.ares.bice.seguridad.sfa.servicio.UsuarioNoSolicitoDesafioException;
import cl.ares.bice.seguridad.sfa.servicio.UsuarioSuspendidoException;
import cl.ares.bice.ws.servicio.DesafioType;
import cl.ares.bice.ws.utiles.LoggerUtil;

import com.entrust.identityGuard.authenticationManagement.wsv9.AuthenticateGenericChallengeCallParms;
import com.entrust.identityGuard.authenticationManagement.wsv9.AuthenticationFault;
import com.entrust.identityGuard.authenticationManagement.wsv9.AuthenticationServiceFault;
import com.entrust.identityGuard.authenticationManagement.wsv9.AuthenticationType;
import com.entrust.identityGuard.authenticationManagement.wsv9.ErrorCode;
import com.entrust.identityGuard.authenticationManagement.wsv9.GenericAuthenticateParms;
import com.entrust.identityGuard.authenticationManagement.wsv9.GenericAuthenticateResponse;
import com.entrust.identityGuard.authenticationManagement.wsv9.GenericChallenge;
import com.entrust.identityGuard.authenticationManagement.wsv9.GenericChallengeParms;
import com.entrust.identityGuard.authenticationManagement.wsv9.GetGenericChallengeCallParms;
import com.entrust.identityGuard.authenticationManagement.wsv9.Response;
import com.entrust.identityGuard.userManagement.wsv9.AdminServiceFault;
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

import java.rmi.RemoteException;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

public class IdGuardSfaAdapter {

    static final Logger oLogger = LoggerUtil.getLoggerInput(IdGuardSfaAdapter.class);
    AdminConnectionManager9 adminConnManager;
    AuthConnectionManager9 authConnManager;
    DesafioFactory desafioFactory;

    public void setAdminConnManager(AdminConnectionManager9 adminConnManager){
        this.adminConnManager = adminConnManager;
    }

    public void setAuthConnManager(AuthConnectionManager9 authConnManager){
        this.authConnManager = authConnManager;
    }

    public void setDesafioFactory(DesafioFactory desafioFactory) {
        this.desafioFactory = desafioFactory;
    }

    public Otp obtenerOtp(String usuarioId, String grupo){
        try {
            UserOTPInfo otpInfo =adminConnManager.getBinding().userOTPGet(new UserOTPGetCallParms(getIgUserId(usuarioId, grupo), new UserOTPFilter()))[0];

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
        try {
            GenericAuthenticateParms parms = new GenericAuthenticateParms();
            parms.setAuthenticationType(AuthenticationTypeBuilder.getAuthenticationType(tipoDispositivo));
          //  parms.setIPAddress(ip);
            String[] respDesafioArr = RespuestaDesafioBuilder.descomponerDesafio(respuestaDesafio, tipoDispositivo);
            //LogHelper.dumpRespuestaDesafio(log, respDesafioArr);
            GenericAuthenticateResponse resp =
               authConnManager.getBinding().authenticateGenericChallenge(
                  new AuthenticateGenericChallengeCallParms(
                         getIgUserId(usuarioId, grupo),
                         new Response(null, respDesafioArr, null),
                         parms));
           // CardData ci = resp.getCardInfo();
        } catch(AuthenticationFault e){
            LogHelper.logFault(oLogger, e);
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

    public DesafioType crearDesafio(Dispositivo dispositvo) throws UsuarioBloqueadoException, UsuarioSuspendidoException {
        return crearDesafio(dispositvo.getUsuarioId(), dispositvo.getGrupo(), dispositvo.getTipo());
    }

    public List<Dispositivo> dipositivosDelUsuario(String usuarioId, String grupo)
            throws UsuarioNoExisteException, UsuarioBloqueadoException, UsuarioSuspendidoException {
        List<Dispositivo> listaDispositivos = new ArrayList<Dispositivo>();
        try {

            UserInfo[] userInfo = getUserInfo(usuarioId, grupo);
    //            LogHelper.dumpUser(userInfo);

    /*            for (int i=0; i<userInfo.length; i++)
            {
                if(userInfo[i].getUserState() == UserState.ACTIVE){
                        listaDispositivos.addAll()
                    //throw new UsuarioSuspendidoException(userInfo[i].getSuspendReason());
                }
                if(userInfo[i].getUserid()==usuarioId){
                        resp = (UserInfo)userInfo[i];
                }
            }
            */
            return getTipoAutUsuario(userInfo);
        } catch (AdminServiceFault ase) {
            if (ase.getErrorCode().equals(ErrorCode.USER_DOES_NOT_EXIST))
                throw new UsuarioNoExisteException(ase.getErrorMessage());
            oLogger.error("Error de sistema " + ase.getMessage());
            throw new RuntimeException("Error de sistema" + ase.getErrorMessage());
        } catch (RemoteException e) {
            oLogger.error("Problemas de Conexion:" + e.getMessage());
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
            
            
            GetGenericChallengeCallParms callPrms =
                    new GetGenericChallengeCallParms(getIgUserId(usuarioId, grupo), parms);
            GenericChallenge challengeSet = authConnManager.getBinding().getGenericChallenge(callPrms);

            return desafioFactory.crearDesafio(challengeSet, tipoDispositivo);
        } catch (AuthenticationServiceFault e) {
            if("USER_LOCKED".equals(e.getErrorCode().getValue()))
                throw new UsuarioBloqueadoException(e.getErrorMessage());
            if("USER_SUSPENDED".equals(e.getErrorCode().getValue()))
                throw new UsuarioSuspendidoException(e.getErrorMessage());
            String msg = "Error al crear desafio:" + e.getErrorCode() + " - " + e.getErrorMessage();
            oLogger.error(msg);
            throw new SfaAdapterException(msg);
        } catch (RemoteException e){
            throw new SfaAdapterException(e);
        }
    }

    private UserInfo[] getUserInfo(String usuarioId, String grupo) throws RemoteException {

        UserInfo resp=null;
        UserFilter filter = new UserFilter();
        filter.setUserid(usuarioId);
        
        if(grupo.length()>0){
            ArrayList<String> groups = new ArrayList<String>();
            groups.add(grupo);
            filter.setGroups((String[]) groups.toArray(new String[groups.size()]));
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
        UserInfo[] users = result.getUsers();
        return users;
    }

    private List<Dispositivo> getTipoAutUsuario(UserInfo[] userinfo) {
        List<Dispositivo> tiposAuth = new ArrayList<Dispositivo>();
        List<Dispositivo> tokens = new ArrayList<Dispositivo>();
        List<Dispositivo> cards = new ArrayList<Dispositivo>();
        List<Dispositivo> otps = new ArrayList<Dispositivo>();

        cards = getActiveCard(userinfo);
        if(cards != null)
            tiposAuth.addAll(cards);

        tokens = getActiveToken(userinfo);
        if(tokens != null)
                tiposAuth.addAll(tokens);
        //if (userinfo[0].isOTPAllowed())
            tiposAuth.add( new Dispositivo("OTP", "") );


        return tiposAuth;
    }

    private List<Dispositivo> getActiveToken(UserInfo[] userinfo){
        List<Dispositivo> listaTokens = new ArrayList<Dispositivo>();
        for (int i=0; i<userinfo.length; i++){

                if (userinfo[i].getTokens() != null && userinfo[i].getTokens().length > 0){
                UserTokenInfo[] tokens = userinfo[i].getTokens();
                if (tokens != null && tokens.length > 0){
                    UserTokenSetParser setParser = new UserTokenSetParser(tokens);
                    Iterator sets = setParser.getTokenSets().iterator();
                    while (sets.hasNext()){
                       String set = (String) sets.next();
                       if (set.equals("")){
                          oLogger.debug("Default Token Set");
                       }
                       else{
                          oLogger.debug("Token Set: " + set);
                       }
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
                                   if(disp.getTokenSet().toUpperCase().isEmpty() == false){
                                           token.setTipo("SFTKN");
                                   }
                                   else
                                           token.setTipo("TOKEN");
                                   listaTokens.add(token);
                           }
                       }
                    }
                }
                }
        }
        return listaTokens;
    }

    private List<Dispositivo> getActiveCard(UserInfo[] userinfo){
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

    private String getIgUserId(String userId, String group){
        return group + "/" + userId; 
    }

}

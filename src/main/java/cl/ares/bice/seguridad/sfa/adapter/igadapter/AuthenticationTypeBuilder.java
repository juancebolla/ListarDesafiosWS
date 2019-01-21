package cl.ares.bice.seguridad.sfa.adapter.igadapter;

import cl.ares.bice.seguridad.sfa.modelo.Otp;

import com.entrust.identityGuard.authenticationManagement.wsv9.AuthenticationType;
import cl.ares.bice.seguridad.sfa.modelo.TipoDispositivo;

import com.entrust.identityGuard.authenticationManagement.wsv9.AuthenticationTypeEx;

public class AuthenticationTypeBuilder {

    public static  AuthenticationType getAuthenticationType(String t) {
        if(t.toUpperCase().equals("GRILLA")||t.toUpperCase().equals("GRID"))
            return AuthenticationType.GRID;
        if(t.toUpperCase().equals("SMS")||t.toUpperCase().equals("OTP"))
            return AuthenticationType.OTP;
        if(t.toUpperCase().equals("SFTKN"))
                return AuthenticationType.TOKENRO;
        if(t.toUpperCase().equals("TOKEN"))
                return AuthenticationType.TOKENRO;
        else{
            //String msg = "El tipo de desafio " + t + " no tiene una correspondecia en IGuard.";
            //throw new RuntimeException(msg);
            return AuthenticationType.TOKENRO;
        }
    }

    public static AuthenticationTypeEx getAuthenticationType2(String t) {
        if(t.equals("GRILLA"))
            return AuthenticationTypeEx.GRID;
        if(t.equals("SMS")||t.equals("OTP"))
            return AuthenticationTypeEx.OTP;
        if(t.equals("SFTKN"))
                return AuthenticationTypeEx.TOKENRO;
        if(t.equals("TOKEN"))
                return AuthenticationTypeEx.TOKENRO;
        else{
            //String msg = "El tipo de desafio " + t + " no tiene una correspondecia en IGuard.";
            //throw new RuntimeException(msg);
            return AuthenticationTypeEx.TOKENRO;
        }
    }

}

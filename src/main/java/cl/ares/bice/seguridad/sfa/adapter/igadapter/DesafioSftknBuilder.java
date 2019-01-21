package cl.ares.bice.seguridad.sfa.adapter.igadapter;

import cl.ares.bice.seguridad.sfa.modelo.Desafio;
import cl.ares.bice.seguridad.sfa.adapter.SfaAdapterException;
import cl.ares.bice.ws.servicio.DesafioType;

import cl.ares.bice.ws.servicio.DesafiosType;

import cl.ares.bice.ws.utiles.LoggerUtil;

import com.entrust.identityGuard.authenticationManagement.wsv9.GenericChallenge;
import com.entrust.identityGuard.authenticationManagement.wsv9.GenericChallengeEx2;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

public class DesafioSftknBuilder{// implements IDesafioBuilder {

    private static final Logger log = LoggerUtil.getLoggerInput(DesafioGrillaBuilder.class);

    public DesafioType crear(GenericChallenge challenge) {
        String identificador = null;
        if(challenge.getTokenChallenge().getTokens().length == 0)
            throw new SfaAdapterException("El desafio no retorno un SFTKN");
        for(int i=0;i < challenge.getTokenChallenge().getTokens().length;i++){
                if(challenge.getTokenChallenge().getTokens()[i].getTokenSet().toUpperCase().equals("SFTKN")){
                        identificador = challenge.getTokenChallenge().getTokens()[i].getSerialNumber();                 
                }
        }
        return new DesafioType("SFTKN", identificador);
    }

    public DesafiosType crear(GenericChallengeEx2 challenge, String tipo) {
        DesafiosType desafios = new DesafiosType();
        String identificador = null;
        String codigoQA = null;
        String alias = null;
        if(challenge.getTokenChallenge().getTokens().length == 0)
            throw new SfaAdapterException("El desafio no retorno un SFTRX");
        for(int i=0;i < challenge.getTokenChallenge().getTokens().length;i++){
            if(challenge.getTokenChallenge().getTokens()[i].getTokenSet().startsWith("SFTRX")){
                DesafioType desafio = new DesafioType();
                identificador = challenge.getTokenChallenge().getTokens()[i].getSerialNumber();
                alias = challenge.getTokenChallenge().getTokens()[i].getTokenSet();
                if(tipo.equals("TOKEN_SIGN_ONLINE")){
                    codigoQA = challenge.getTokenChallenge().getTransactionId();
                    log.debug(codigoQA);
                }else{
                    try{
                        codigoQA = Base64.encodeBase64String(challenge.getTokenChallenge().getTokens()[i].getOfflineChallengeQRCode());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                desafio.setTipoDesafio(tipo);
                desafio.setIdentificador(identificador);
                desafio.setCodigoQA(codigoQA);
                desafio.setAliasUsuario(alias);
                if(tipo.equals("TOKEN_SIGN_ONLINE")){
                    desafio.setOnline("true");
                }else{
                    desafio.setOnline("false");
                }
                desafios.getDesafio().add(desafio);
            }
        }
        return desafios;
    }
}

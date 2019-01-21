package cl.ares.bice.seguridad.sfa.adapter.igadapter;

import cl.ares.bice.seguridad.sfa.modelo.Desafio;
import cl.ares.bice.seguridad.sfa.modelo.TipoDispositivo;
import cl.ares.bice.ws.servicio.DesafioType;

import com.entrust.identityGuard.authenticationManagement.wsv9.GenericChallenge;

import com.entrust.identityGuard.authenticationManagement.wsv9.GenericChallengeEx2;

import java.util.Map;

public class DesafioFactory {

    Map<String, IDesafioBuilder> builders;


    public void setBuilders(Map<String, IDesafioBuilder> builders) {
        this.builders = builders;
    }

    public DesafioType crearDesafio(GenericChallenge challenge, String tipo) {
        //IDesafioBuilder builder = builders.get(tipo);
        //if(builder != null)
        //    return builders.get(tipo).crear(challenge);
        DesafioType desafio = new DesafioType(tipo);
       // System.out.println(tipo);
        if(tipo.equals("OTP")){
            //System.out.println(challenge.getOTPChallenge().getDeliveryMechanism()[0].getContactInfoLabel());
        }else{
            for(int i=0;i < challenge.getTokenChallenge().getTokens().length;i++){
                if(tipo.equals("TOKEN")&&!challenge.getTokenChallenge().getTokens()[i].getTokenSet().contains("SFTRX".subSequence(1,5))&&!challenge.getTokenChallenge().getTokens()[i].getTokenSet().contains("SFTKN".subSequence(1,5))){
                    desafio.setIdentificador(challenge.getTokenChallenge().getTokens()[i].getSerialNumber());
                    desafio.setAliasUsuario(challenge.getTokenChallenge().getTokens()[i].getTokenSet());                
                }else if(challenge.getTokenChallenge().getTokens()[i].getTokenSet().equals(tipo)){
                    desafio.setIdentificador(challenge.getTokenChallenge().getTokens()[i].getSerialNumber());
                    desafio.setAliasUsuario(challenge.getTokenChallenge().getTokens()[i].getTokenSet());
                }
            }
        }
        return desafio;
    }

    public DesafioType crearDesafio(GenericChallengeEx2 challenge, String tipo) {
        IDesafioBuilder builder = builders.get(tipo);
        if(builder != null)
            return builders.get(tipo).crear(challenge, tipo);
        return new DesafioType(tipo);        
    }
}

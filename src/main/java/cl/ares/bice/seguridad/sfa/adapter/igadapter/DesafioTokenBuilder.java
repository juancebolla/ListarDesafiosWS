package cl.ares.bice.seguridad.sfa.adapter.igadapter;

import cl.ares.bice.seguridad.sfa.modelo.Desafio;
import cl.ares.bice.seguridad.sfa.modelo.TipoDispositivo;
import cl.ares.bice.seguridad.sfa.adapter.SfaAdapterException;
import cl.ares.bice.ws.servicio.DesafioType;

import com.entrust.identityGuard.authenticationManagement.wsv9.GenericChallenge;
import com.entrust.identityGuard.authenticationManagement.wsv9.GenericChallengeEx2;

public class DesafioTokenBuilder{// implements IDesafioBuilder {

    public DesafioType crear(GenericChallenge challenge) {
        if(challenge.getTokenChallenge().getTokens().length == 0)
            throw new SfaAdapterException("El desafio no retorno un TOKEN");

        String identificador = challenge.getTokenChallenge().getTokens()[0].getSerialNumber();
        return new DesafioType("TOKEN", identificador);

    }

    public DesafioType crear(GenericChallengeEx2 challenge, String tipo) {
        if(challenge.getTokenChallenge().getTokens().length == 0)
            throw new SfaAdapterException("El desafio no retorno un TOKEN");

        String identificador = challenge.getTokenChallenge().getTokens()[0].getSerialNumber();
        return new DesafioType("TOKEN", identificador);

    }
}

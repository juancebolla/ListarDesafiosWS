package cl.ares.bice.seguridad.sfa.adapter.igadapter;

import cl.ares.bice.seguridad.sfa.modelo.Desafio;
import cl.ares.bice.seguridad.sfa.adapter.SfaAdapterException;
import cl.ares.bice.ws.servicio.DesafioType;

import cl.ares.bice.ws.servicio.DesafiosType;

import com.entrust.identityGuard.authenticationManagement.wsv9.GenericChallenge;
import com.entrust.identityGuard.authenticationManagement.wsv9.GenericChallengeEx2;

import org.apache.commons.codec.binary.Base64;

public interface IDesafioBuilder {
    public DesafioType crear(GenericChallenge challenge);

    public DesafioType crear(GenericChallengeEx2 challenge, String tipo);
}

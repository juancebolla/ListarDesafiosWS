package cl.ares.bice.seguridad.sfa.adapter.igadapter;

import cl.ares.bice.seguridad.sfa.modelo.DesafioGrilla;
import cl.ares.bice.ws.servicio.CoordenadaType;
import cl.ares.bice.ws.servicio.CoordenadasType;
import cl.ares.bice.ws.servicio.DesafioType;
import cl.ares.bice.ws.utiles.LoggerUtil;

import com.entrust.identityGuard.authenticationManagement.wsv9.AuthenticationType;
import com.entrust.identityGuard.authenticationManagement.wsv9.Challenge;
import com.entrust.identityGuard.authenticationManagement.wsv9.ChallengeRequestResult;
import com.entrust.identityGuard.authenticationManagement.wsv9.ChallengeSet;
import com.entrust.identityGuard.authenticationManagement.wsv9.GenericChallenge;

import com.entrust.identityGuard.authenticationManagement.wsv9.GenericChallengeEx2;

import java.util.ArrayList;

import java.util.List;

import org.apache.log4j.Logger;

public class DesafioGrillaBuilder{// implements IDesafioBuilder {

    private static final Logger log = LoggerUtil.getLoggerInput(DesafioGrillaBuilder.class);

    public DesafioType crear(GenericChallenge challenge) {
        try {
            if (!challenge.getChallengeRequestResult().equals(ChallengeRequestResult.CHALLENGE)) {
                String msg = "Challenge result of type " + challenge.getChallengeRequestResult().getValue() + " is not valid";
                throw new RuntimeException(msg);
            }
            if (!challenge.getType().equals(AuthenticationType.GRID)) {
                String msg = "Exepcting a grid challenge but received " + challenge.getTokenChallenge().getChallenge();
                throw new RuntimeException(msg);
            }
            ChallengeSet gridChallenge = challenge.getGridChallenge();
            if (gridChallenge == null)
                throw new RuntimeException("IdGuard returned a null grid challenge");

            DesafioGrilla desafioGrilla = new DesafioGrilla();
            if (gridChallenge.getCardSerialNumbers() != null && gridChallenge.getCardSerialNumbers().length > 0)
                desafioGrilla.setNumeroSerieTarjeta(gridChallenge.getCardSerialNumbers()[0]);

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
            desafioGrilla.setCoordenadas(coordenada);
            log.debug(desafioGrilla.getCoordenadas().getCoordenada().size());
            return desafioGrilla;
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    public DesafioType crear(GenericChallengeEx2 challenge, String tipo) {

        try {

            if (!challenge.getChallengeRequestResult().equals(ChallengeRequestResult.CHALLENGE)) {
                String msg = "Challenge result of type " +
                        challenge.getChallengeRequestResult().getValue() + " is not valid";
                throw new RuntimeException(msg);
            }

            if (!challenge.getType().equals(AuthenticationType.GRID)) {
                String msg = "Exepcting a grid challenge but received " + challenge.getTokenChallenge().getChallenge();
                throw new RuntimeException(msg);
            }

            ChallengeSet gridChallenge = challenge.getGridChallenge();

            if (gridChallenge == null)
                throw new RuntimeException("IdGuard returned a null grid challenge");


            DesafioGrilla desafioGrilla = new DesafioGrilla();
            if (gridChallenge.getCardSerialNumbers() != null && gridChallenge.getCardSerialNumbers().length > 0)
                desafioGrilla.setNumeroSerieTarjeta(gridChallenge.getCardSerialNumbers()[0]);


            Challenge[] challArr = gridChallenge.getChallenge();
            for (int i = 0; i < challArr.length; i++) {
                Challenge chall = challArr[i];
                String col = (char) (chall.getColumn() + (int) 'A') + "";
                int fila = chall.getRow() + 1;
                desafioGrilla.agregarCoordenada(fila, col);
            }


            return desafioGrilla;
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}

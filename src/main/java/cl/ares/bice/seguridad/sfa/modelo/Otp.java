package cl.ares.bice.seguridad.sfa.modelo;

public class Otp {
    String usuario;
    String grupo;
    String otp;

    public Otp(String usuarioId, String grupo) {
        this.usuario = usuarioId;
        this.grupo = grupo;
    }


    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}

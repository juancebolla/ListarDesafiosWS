package cl.ares.bice.seguridad.sfa.modelo;

public class Dispositivo {
    String tipo;
    String identificador;
    String usuarioId;
    String grupo;
    String aliasUsuario;
    Boolean online;


    public Dispositivo() {
    }

    public Dispositivo(String tipo, String identificador,String grupo, String aliasUsuario, Boolean online) {
        this.tipo = tipo;
        this.identificador = identificador;
        this.grupo = grupo;
        this.aliasUsuario = aliasUsuario;
        this.online = online;
    }

    public Dispositivo(String tipo, String identificador,String grupo) {
        this.tipo = tipo;
        this.identificador = identificador;
        this.grupo = grupo;
        this.aliasUsuario = "";
        this.online = Boolean.FALSE;
    }
    
    public Dispositivo(String tipo, String identificador) {
        this.tipo = tipo;
        this.identificador = identificador;
        this.grupo = "";
        this.aliasUsuario = "";
        this.online = Boolean.FALSE;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public void setAliasUsuario(String aliasUsuario) {
        this.aliasUsuario = aliasUsuario;
    }

    public String getAliasUsuario() {
        return aliasUsuario;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public Boolean getOnline() {
        return online;
    }

    public String toString(){
        StringBuffer buff = new StringBuffer();
        return buff
            .append("[")
            .append("tipo:").append(tipo).append("|")
            .append("identificador:").append(identificador).append("|")
            .append("usuarioId:").append(usuarioId).append("|")
            .append("grupo:").append(grupo).append("|")
            .append("aliasUsuario:").append(aliasUsuario).append("|")
            .append("online:").append(online).append("|")
            .append("]").toString();
    }
}

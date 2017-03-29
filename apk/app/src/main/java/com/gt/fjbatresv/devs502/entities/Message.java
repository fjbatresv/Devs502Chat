package com.gt.fjbatresv.devs502.entities;

import java.io.Serializable;

/**
 * Created by javie on 28/03/2017.
 */

public class Message implements Serializable{
    private String cui;
    private String mensaje;
    private String remitente;
    private String remitenteUid;
    private String remitentePhoto;

    public Message() {
    }

    public Message(String cui, String mensaje, String remitente, String remitenteUid, String remitentePhoto) {
        this.cui = cui;
        this.mensaje = mensaje;
        this.remitente = remitente;
        this.remitenteUid = remitenteUid;
        this.remitentePhoto = remitentePhoto;
    }

    public String getCui() {
        return cui;
    }

    public void setCui(String cui) {
        this.cui = cui;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public String getRemitenteUid() {
        return remitenteUid;
    }

    public void setRemitenteUid(String remitenteUid) {
        this.remitenteUid = remitenteUid;
    }

    public String getRemitentePhoto() {
        return remitentePhoto;
    }

    public void setRemitentePhoto(String remitentePhoto) {
        this.remitentePhoto = remitentePhoto;
    }
}

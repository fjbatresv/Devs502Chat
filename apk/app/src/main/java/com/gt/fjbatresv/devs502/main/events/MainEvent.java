package com.gt.fjbatresv.devs502.main.events;

import java.io.Serializable;

/**
 * Created by javie on 28/03/2017.
 */

public class MainEvent implements Serializable{
    private int tipo;
    private Object object;
    private String mensaje;
    private String error;

    public final static int loadMessage = 0;
    public final static int logOut = 1;
    public final static int loadUser = 2;
    public final static int sendMessage = 3;

    public MainEvent() {
    }

    public MainEvent(int tipo) {
        this.tipo = tipo;
    }

    public MainEvent(int tipo, Object object, String mensaje, String error) {
        this.tipo = tipo;
        this.object = object;
        this.mensaje = mensaje;
        this.error = error;
    }

    public MainEvent(int tipo, Object object, String mensaje) {
        this.tipo = tipo;
        this.object = object;
        this.mensaje = mensaje;
    }

    public MainEvent(int tipo, Object object) {
        this.tipo = tipo;
        this.object = object;
    }

    public MainEvent(int tipo, String error) {
        this.tipo = tipo;
        this.error = error;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}

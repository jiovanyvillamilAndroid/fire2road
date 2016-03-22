package com.kingmonkey.fire2road;

/**
 * Created by Jiovany on 20/03/2016.
 */
public class MecanicoObject {
    String id;
    String nombre;
    String latitud;
    String longitud;
    String urlFoto;

    public MecanicoObject(String id, String latitud, String longitud, String nombre, String urlFoto) {
        this.id = id;
        this.latitud = latitud;
        this.longitud = longitud;
        this.nombre = nombre;
        this.urlFoto = urlFoto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    @Override
    public String toString() {
        return "id: "+id+" nombre: "+nombre+" latitud: "+latitud+" longitud: "+longitud+" url: "+urlFoto;
    }
}

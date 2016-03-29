package com.kingmonkey.fire2road.Domain;

/**
 * Created by Jiovany on 28/03/2016.
 */
public class PerfilObject {
    String nombre;
    String username;
    String marcaMoto;//Yamaha, Honda, Kawasaki
    String refMoto;
    String profileUrl;
    String marcaUrl;

    public PerfilObject(String marcaMoto, String marcaUrl, String nombre, String profileUrl, String refMoto, String username) {
        this.marcaMoto = marcaMoto;
        this.marcaUrl = marcaUrl;
        this.nombre = nombre;
        this.profileUrl = profileUrl;
        this.refMoto = refMoto;
        this.username = username;
    }

    @Override
    public String toString() {
        return "PerfilObject{" +
                "marcaMoto='" + marcaMoto + '\'' +
                ", nombre='" + nombre + '\'' +
                ", username='" + username + '\'' +
                ", refMoto='" + refMoto + '\'' +
                ", profileUrl='" + profileUrl + '\'' +
                ", marcaUrl='" + marcaUrl + '\'' +
                '}';
    }

    public String getMarcaMoto() {
        return marcaMoto;
    }

    public void setMarcaMoto(String marcaMoto) {
        this.marcaMoto = marcaMoto;
    }

    public String getMarcaUrl() {
        return marcaUrl;
    }

    public void setMarcaUrl(String marcaUrl) {
        this.marcaUrl = marcaUrl;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getRefMoto() {
        return refMoto;
    }

    public void setRefMoto(String refMoto) {
        this.refMoto = refMoto;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

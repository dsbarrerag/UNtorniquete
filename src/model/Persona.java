package model;

import java.awt.*;

public class Persona {

    private boolean entra;
    private String nombre;
    private int retraso;
    private Color color;

    public Persona(int d, boolean e, String nm, Color clr) {
        this.retraso = d;
        this.entra = e;
        this.nombre = nm;
        this.color = clr;
    }

    public String getNombre() {
        return this.nombre;
    }

    public double getRetraso() {
        return (double)this.retraso;
    }

    public boolean isEntrada() {
        return this.entra;
    }

    public void addRretraso(int i) {
        this.retraso += i;
    }

    public String toString() {
        String p = this.entra ? " - entrada" : " - salida";
        return this.nombre + p;
    }

    public Color getColor() {
        return this.color;
    }
}

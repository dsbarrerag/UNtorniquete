package controller;

import model.Generador;
import model.Persona;
import model.Tiempos;
import view.Vista;

public class App {
    private Tiempos t;
    private Runnable generador;
    private Vista vista;
    private static App _instance = new App();

    public static void main(String[] args) {
        getInstance().start();
    }

    private App() {
    }

    public static App getInstance() {
        return _instance;
    }

    public void crearTiempo(double tEnt, double tSal, int c) {
        this.t = new Tiempos(tEnt * 1000.0D, tSal * 1000.0D, c * 1000);
    }

    public void start() {
        this.vista = new Vista();
        this.vista.setVisible(true);
    }

    public void crear(int num, double tEnt, double tSal, int i) {
        getInstance().crearTiempo(tEnt, tSal, 1);
        this.vista.crearTorniquetes(num);
        this.generador = new Generador(num, i);
        (new Thread(this.generador)).start();
    }

    public void crear(int num, double tEnt, double tSal, int personas, double porcentaje) {
        getInstance().crearTiempo(tEnt, tSal, 1);
        this.vista.crearTorniquetes(num);
        this.generador = new Generador(personas, porcentaje, num);
        (new Thread(this.generador)).start();
    }

    public void llegaPersona(Persona p) {
        this.vista.crearPersona(p);
    }

    public void pasarPersona(int i) {
        this.vista.eliminarPersona(i);
    }

    public void personaPaso(int i) {
        this.vista.paso(i);
    }

    public int getTiempo() {
        return this.t.getT_entrada();
    }

    public int getSalida() {
        return this.t.getT_salida();
    }

    public int getDemora() {
        return this.t.getT_demora();
    }
}

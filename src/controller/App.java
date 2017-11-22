package controller;

import generator.PoissonGenerator;
import model.Entrance;
import model.User;
import view.Vista;

public class App {
    private Entrance generador;
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
        //this.t = new Tiempos(tEnt * 1000.0D, tSal * 1000.0D, c * 1000);
    }

    public void start() {
        Entrance e = new Entrance(5, 10);
        //e.setEntryGenerator(new PoissonGenerator(10));
        //e.setExitGenerator(new PoissonGenerator(0.2));
        e.setTurnstilesState(3,1);
        //this.vista = new Vista();
        //this.vista.setVisible(true);
    }

    public void crear(int num, double tEnt, double tSal, int i) {
        getInstance().crearTiempo(tEnt, tSal, 1);
        this.vista.crearTorniquetes(num);
        this.generador = new Entrance(num, 1);
        //generador.setGenerator(new PoissonGenerator(10.0));
    }

    public void crear(int num, double tEnt, double tSal, int personas, double porcentaje) {
        getInstance().crearTiempo(tEnt, tSal, 1);
        this.vista.crearTorniquetes(num);
    }

    public void llegaPersona(User p) {
        this.vista.crearPersona(p);
    }

    public void pasarPersona(int i) {
        this.vista.eliminarPersona(i);
    }

    public void personaPaso(int i) {
        this.vista.paso(i);
    }

    public int getTiempo() {
        return 1;
    }

    public int getSalida() {
        return 1;
    }

    public int getDemora() {
        return 2;
    }
}

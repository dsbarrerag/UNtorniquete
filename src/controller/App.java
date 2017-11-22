package controller;

import generator.PoissonGenerator;
import model.Entrance;
import model.User;
import view.Vista;

public class App {
    private Entrance entrance;
    private Vista vista;
    private static App _instance = new App();

    public static void main(String[] args) {
        getInstance().initialize();
    }

    private App() { }

    public static App getInstance() {
        return _instance;
    }


    public void initialize() {
        entrance = new Entrance();
    }

    public void start(){

    }

    public void setTurnstiles(int num, double mean){
        entrance.setTurnstiles(num, mean);
    }

    public void setEntryGenerator(double mean){
        entrance.setEntryGenerator(new PoissonGenerator(mean));
    }

    public void setExitGenerator(double mean){
        entrance.setExitGenerator(new PoissonGenerator(mean));
    }








    // TODO Borrar esta basofia

    public void crear(int num, double tEnt, double tSal, int i) {

    }

    public void crear(int num, double tEnt, double tSal, int personas, double porcentaje) {

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
        return 0;
    }

    public int getSalida() {
        return 0;
    }

    public int getDemora() {
        return 0;
    }
}

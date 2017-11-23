package controller;

import analysis.Calculator.MMCCalculator;
import analysis.Calculator.QueueCalculator;
import analysis.QueueAnalyzer;
import generator.PoissonGenerator;
import model.Entrance;
import model.Queue;
import model.User;
import view.Vista;

public class App {

    private static int serviceMean = 10;

    private Entrance entrance;
    private QueueAnalyzer analyzer;

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
        analyzer = new QueueAnalyzer();
        QueueAnalyzer analyzer = new QueueAnalyzer();
    }

    public void start(){
        analyzer.calculateBest();
    }

    public void setTurnstilesState(int entry, int exit){
        entrance.setTurnstilesState(entry, exit);
    }

    public void setTurnstiles(int num, double mean){
        entrance.setTurnstiles(num, mean);
        analyzer.setTurnstiles(num);
    }

    public void setEntryGenerator(double mean){
        entrance.setEntryGenerator(new PoissonGenerator(mean));
        analyzer.setEntryQueue(new Queue(mean, serviceMean, 1));
    }

    public void setExitGenerator(double mean){
        entrance.setExitGenerator(new PoissonGenerator(mean));
        analyzer.setExitQueue(new Queue(mean, serviceMean, 1));
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

package controller;

import analysis.QueueAnalyzer;
import generator.PoissonGenerator;
import model.Entrance;
import model.Queue;
import sample.Main;

public class App {

    private final int SERVICE_MEAN = 10;

    private Entrance entrance;
    private QueueAnalyzer analyzer;

    //private Vista vista;
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

        setTurnstiles(13, SERVICE_MEAN);

        setEntryGenerator(1);
        setExitGenerator(12);

        //Main.initialize();
    }

    public void inizializeWoutWindow(){
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
        analyzer.setEntryQueue(new Queue(getLambda(mean), getLambda(SERVICE_MEAN)));
        entrance.setEntryGenerator(new PoissonGenerator(mean));
    }

    public void setExitGenerator(double mean){
        analyzer.setExitQueue(new Queue(getLambda(mean), getLambda(SERVICE_MEAN)));
        entrance.setExitGenerator(new PoissonGenerator(mean));
    }

    public void badQueue() {
        System.out.println("Con esos valores no se cumple la condicion de estabilidad");
    }

    private double getLambda(double mean){
        int timeValue = 60;
        return timeValue/mean;
    }
}

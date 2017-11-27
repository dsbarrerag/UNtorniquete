package controller;

import analysis.QueueAnalyzer;
import generator.PoissonGenerator;
import model.Entrance;
import model.Queue;
import sample.Main;

public class App {

    private static int serviceMean = 10;

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
        QueueAnalyzer analyzer = new QueueAnalyzer();
        Main.initialize();
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
        entrance.setEntryGenerator(new PoissonGenerator(mean));
        analyzer.setEntryQueue(new Queue(mean, serviceMean, 1));
    }

    public void setExitGenerator(double mean){
        entrance.setExitGenerator(new PoissonGenerator(mean));
        analyzer.setExitQueue(new Queue(mean, serviceMean, 1));
    }
}

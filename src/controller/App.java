package controller;

import analysis.QueueAnalyzer;
import generator.PoissonGenerator;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Entrance;
import model.Queue;
import model.Turnstile;
import model.User;
import sample.Controller;
import sample.Main;

import javax.swing.text.html.ImageView;
import java.io.IOException;

public class App {

    private final int SERVICE_MEAN = 10;

    private Entrance entrance;
    private QueueAnalyzer analyzer;
    private Controller controller;

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
        Main.initialize();
    }


    //TODO: LLamar con esos 3 valores
    public void start(int turnstiles, double entryMean, double exitMean, Controller controller){
        this.controller = controller;
        setTurnstiles(turnstiles, SERVICE_MEAN);
        setEntryGenerator(entryMean);
        setExitGenerator(exitMean);
        analyzer.calculateBest();
    }

    //TODO: llamar siempre que se cambie alguno de los valores
    public void update(double entryMean, double exitMean) {
        setEntryGenerator(entryMean);
        setExitGenerator(exitMean);
        analyzer.calculateBest();
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
        //TODO mostrar ese mensaje en un popup
        System.out.println("Con esos valores no se cumple la condicion de estabilidad");
    }

    private double getLambda(double mean){
        int timeValue = 60;
        return timeValue/mean;
    }

    //TODO LLamar metodos de la interfaz

    public void setTurnstilesState(int entry, int exit){
        entrance.setTurnstilesState(entry, exit);
        //Dice cuantos torniquetes son de entrada y cuantos de salida

    }

    public void createEntryUser(User user) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // Update UI here.
                controller.systemUsersRight(user);
            }
        });

        // Agregar usuario a la cola de entrada
    }

    public void createExitUser(User user) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // Update UI here.
                controller.systemUsersLeft(user);
            }
        });
    }

    public void freeTurnstile(Turnstile t) {
        // Desocupar el torniquete t
    }

    public void userIn(Turnstile t) {
        //Ocupar el torniquete t y remover usuario de la cola de entrada
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // Update UI here.
                controller.pullUserIn();
            }
        });
    }

    public void userOut(Turnstile t) {
        //Ocupar el torniquete t y remover usuario de la cola de salida
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // Update UI here.
                controller.pullUserOut();
            }
        });
    }
}

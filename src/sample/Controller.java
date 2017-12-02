package sample;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import controller.App;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.Turnstile;
import model.User;

import java.util.ArrayList;
import java.util.Random;

public class Controller{

    @FXML
    private ImageView tor1, tor2, tor3, tor4, tor5, tor6;

    @FXML
    private TextField inMeasure, outMeasure, numberTor;

    @FXML
    private Pane circleContainerRight, circleContainerLeft, circleContainerCenter;

    private ArrayList<Circle> usersIn, usersOut;

    public void setup(){
        usersIn = new ArrayList<>();
        usersOut = new ArrayList<>();
        Integer in, out, numTor;
        in = Integer.parseInt(inMeasure.getText());
        out = Integer.parseInt(outMeasure.getText());
        numTor = Integer.parseInt(numberTor.getText());
        App.getInstance().start(numTor, in, out, this);
        if(in > 0 && out >0 && numTor > 0 && numTor < 7 ){
            switch (numTor){
                case 1:
                    tor1.setVisible(true);
                    break;
                case 2:
                    tor1.setVisible(true);
                    tor2.setVisible(true);
                    break;
                case 3:
                    tor1.setVisible(true);
                    tor2.setVisible(true);
                    tor3.setVisible(true);
                    break;
                case 4:
                    tor1.setVisible(true);
                    tor2.setVisible(true);
                    tor3.setVisible(true);
                    tor4.setVisible(true);
                    break;
                case 5:
                    tor1.setVisible(true);
                    tor2.setVisible(true);
                    tor3.setVisible(true);
                    tor4.setVisible(true);
                    tor5.setVisible(true);
                    break;
                case 6:
                    tor1.setVisible(true);
                    tor2.setVisible(true);
                    tor3.setVisible(true);
                    tor4.setVisible(true);
                    tor5.setVisible(true);
                    tor6.setVisible(true);
                    break;
            }
        }
    }

    public void start(){
        Integer in, out, numTor;
        in = Integer.parseInt(inMeasure.getText());
        out = Integer.parseInt(outMeasure.getText());
        numTor = Integer.parseInt(numberTor.getText());

        App.getInstance().setExitGenerator(out);
        App.getInstance().setEntryGenerator(in);
        App.getInstance().setTurnstiles(numTor,10);
        App.getInstance().setTurnstilesState(in, out);
    }

    public void update(){
        Integer in, out, numTor;
        in = Integer.parseInt(inMeasure.getText());
        out = Integer.parseInt(outMeasure.getText());
        numTor = Integer.parseInt(numberTor.getText());
        if(in > 0 && out >0 && numTor > 0 && numTor < 7 ){
            switch (numTor){
                case 1:
                    tor1.setVisible(true);
                    tor2.setVisible(false);
                    tor3.setVisible(false);
                    tor4.setVisible(false);
                    tor5.setVisible(false);
                    tor6.setVisible(false);
                    break;
                case 2:
                    tor1.setVisible(true);
                    tor2.setVisible(true);
                    tor3.setVisible(false);
                    tor4.setVisible(false);
                    tor5.setVisible(false);
                    tor6.setVisible(false);
                    break;
                case 3:
                    tor1.setVisible(true);
                    tor2.setVisible(true);
                    tor3.setVisible(true);
                    tor4.setVisible(false);
                    tor5.setVisible(false);
                    tor6.setVisible(false);
                    break;
                case 4:
                    tor1.setVisible(true);
                    tor2.setVisible(true);
                    tor3.setVisible(true);
                    tor4.setVisible(true);
                    tor5.setVisible(false);
                    tor6.setVisible(false);
                    break;
                case 5:
                    tor1.setVisible(true);
                    tor2.setVisible(true);
                    tor3.setVisible(true);
                    tor4.setVisible(true);
                    tor5.setVisible(true);
                    tor6.setVisible(false);
                    break;
                case 6:
                    tor1.setVisible(true);
                    tor2.setVisible(true);
                    tor3.setVisible(true);
                    tor4.setVisible(true);
                    tor5.setVisible(true);
                    tor6.setVisible(true);
                    break;
            }
        }

    }

    public void repaintTurnstile(Turnstile turnstile){
    }

    public synchronized void systemUsersLeft(User user){

        Random r = new Random();
        float randomX = r.nextFloat() * (164);
        float randomY = r.nextFloat() * (344);

        usersOut.add(new Circle(randomX, randomY, 5.0f, Color.rgb(user.getColor().getRed(), user.getColor().getGreen(), user.getColor().getBlue())));

        circleContainerLeft.getChildren().add(usersOut.get(usersOut.size()-1));

    }

    public synchronized void systemUsersRight(User user){

        Random r = new Random();
        float randomX = r.nextFloat() * (164);
        float randomY = r.nextFloat() * (344);

        usersIn.add(new Circle(randomX, randomY, 5.0f, Color.rgb(user.getColor().getRed(), user.getColor().getGreen(), user.getColor().getBlue())));

        circleContainerRight.getChildren().add(usersIn.get(usersIn.size()-1));
    }

    public synchronized void pullUserIn(){
        try{
            Circle user = usersIn.remove(usersIn.size()-1);
            circleContainerRight.getChildren().removeAll(user);
        }catch (Exception e){

        }
    }

    public synchronized void pullUserOut(){
        try{
            Circle user = usersOut.remove(usersOut.size()-1);
            circleContainerLeft.getChildren().removeAll(user);
        }catch (Exception e){

        }
    }

}

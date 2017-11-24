package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class Controller {

    @FXML
    private ImageView tor1, tor2, tor3, tor4, tor5, tor6;

    @FXML
    private TextField inMeasure, outMeasure, numberTor;

    public void start(){
        Integer in, out, numTor;
        in = Integer.parseInt(inMeasure.getText());
        out = Integer.parseInt(outMeasure.getText());
        numTor = Integer.parseInt(numberTor.getText());
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

}

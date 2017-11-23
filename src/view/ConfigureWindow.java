package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfigureWindow {

    public static void display(){

        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Configurar");
        window.setMinWidth(400);
        window.setMinHeight(175);

        Button accept = new Button("Aceptar");
        accept.setOnAction(e -> window.close()  );

        GridPane form = new GridPane();
        form.setPadding(new Insets(20,20,20,20));
        form.setVgap(10);
        form.setHgap(10);

        Label inMeasure = new Label("Tasa de entrada: ");
        Label outMeasure = new Label("Tasa de Salida: ");
        TextField inInput = new TextField();
        TextField outInput = new TextField();

        GridPane.setConstraints(inMeasure,0,0);
        GridPane.setConstraints(inInput,1,0);
        GridPane.setConstraints(outMeasure,0,1);
        GridPane.setConstraints(outInput,1,1);
        GridPane.setConstraints(accept, 3,2);

        form.getChildren().addAll(inMeasure, inInput, outInput, outMeasure, accept);

        Scene scene = new Scene(form);
        window.setScene(scene);
        window.showAndWait();

    }


}

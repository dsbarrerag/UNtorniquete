package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;


public class MainWindow extends Application{

    Stage window;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Image imageTurnstile = new Image("https://d30y9cdsu7xlg0.cloudfront.net/png/80101-200.png");
        Image imageInArrow = new Image("http://www.clker.com/cliparts/7/8/3/2/11949855931744447303arrow-left-green_benji_p_01.svg.hi.png");
        Image imageOutArrow = new Image("http://www.clker.com/cliparts/O/l/h/r/H/R/red-arrow-right-hi.png");
        Image imageBuho = new Image("file:images/buho.png");

        window = primaryStage;
        window.setTitle("UNTorniquete");

        HBox topMenu = new HBox();
        Button configure = new Button("Configurar");
        configure.setOnAction(e -> ConfigureWindow.display());
        topMenu.getChildren().add(configure);

        GridPane entrance = new GridPane();
        entrance.setPadding(new Insets(20, 20, 20, 20));
        entrance.setVgap(10);
        entrance.setHgap(10);

        ImageView turnstile1 = setTurnstile(imageTurnstile);
        ImageView turnstile2 = setTurnstile(imageTurnstile);
        ImageView turnstile3 = setTurnstile(imageTurnstile);
        ImageView turnstile4 = setTurnstile(imageTurnstile);
        ImageView turnstile5 = setTurnstile(imageTurnstile);
        ImageView turnstile6 = setTurnstile(imageTurnstile);

        ImageView inArrow1 = setTurnstile(imageInArrow);
        ImageView inArrow2 = setTurnstile(imageInArrow);
        ImageView inArrow3 = setTurnstile(imageInArrow);
        ImageView inArrow4 = setTurnstile(imageInArrow);
        ImageView inArrow5 = setTurnstile(imageInArrow);
        ImageView inArrow6 = setTurnstile(imageInArrow);

        ImageView outArrow1 = setTurnstile(imageOutArrow);
        ImageView outArrow2 = setTurnstile(imageOutArrow);
        ImageView outArrow3 = setTurnstile(imageOutArrow);
        ImageView outArrow4 = setTurnstile(imageOutArrow);
        ImageView outArrow5 = setTurnstile(imageOutArrow);
        ImageView outArrow6 = setTurnstile(imageOutArrow);

        GridPane.setConstraints(turnstile1,10,0);
        GridPane.setConstraints(turnstile2,10,1);
        GridPane.setConstraints(turnstile3,10,2);
        GridPane.setConstraints(turnstile4,10,3);
        GridPane.setConstraints(turnstile5, 10,4);
        GridPane.setConstraints(turnstile6, 10,5);

        GridPane.setConstraints(inArrow1,11,0);
        GridPane.setConstraints(inArrow2,11,1);
        GridPane.setConstraints(inArrow3,11,2);
        GridPane.setConstraints(inArrow4,11,3);
        GridPane.setConstraints(inArrow5, 11,4);
        GridPane.setConstraints(inArrow6, 11,5);

        GridPane.setConstraints(outArrow1,9,0);
        GridPane.setConstraints(outArrow2,9,1);
        GridPane.setConstraints(outArrow3,9,2);
        GridPane.setConstraints(outArrow4,9,3);
        GridPane.setConstraints(outArrow5, 9,4);
        GridPane.setConstraints(outArrow6, 9,5);

        entrance.getChildren().addAll(turnstile1,
                turnstile2,
                turnstile3,
                turnstile4,
                turnstile5,
                turnstile6,
                inArrow1,
                inArrow2,
                inArrow3,
                inArrow4,
                inArrow5,
                inArrow6,
                outArrow1,
                outArrow2,
                outArrow3,
                outArrow4,
                outArrow5,
                outArrow6
        );

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(topMenu);
        borderPane.setCenter(entrance);

        Scene scene = new Scene(borderPane,600,400);
        window.setScene(scene);
        window.show();
    }

    private ImageView setTurnstile(Image sampleImage) {

        ImageView turnstile = new ImageView();

        turnstile.setImage(sampleImage);
        turnstile.setFitWidth(50);
        turnstile.setPreserveRatio(true);
        turnstile.setSmooth(true);
        turnstile.setCache(true);

        return turnstile;
    }
}

/**
 * Created by Andrew Schwartz on 10/26/17.
 */
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.animation.Animation;

import java.util.Date;
import java.util.stream.Stream;

import static javafx.stage.StageStyle.UNDECORATED;


public class Window extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(final Stage primaryStage) {
        primaryStage.setTitle("Smart Mirror");
//        primaryStage.setAlwaysOnTop(true);
        Group root = new Group();
        Scene scene = new Scene(root, Color.BLACK); // dimensions of window


        // build window
        primaryStage.initStyle(StageStyle.UNDECORATED); // perfect for mirror
        primaryStage.setResizable(false); // rm size changer
        primaryStage.setMaximized(true);
        primaryStage.setY(0); // top of screen
        primaryStage.setX(0); // left corner
        primaryStage.setScene(scene);
        DateManager dm = new DateManager();
        dm.addTime(scene);
        GreetingManager gm = new GreetingManager();
        gm.addGreeting(scene);

        primaryStage.show();
    }
}

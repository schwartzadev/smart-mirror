/**
 * Created by Andrew Schwartz on 10/26/17.
 */
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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

        WeatherManager wm = new WeatherManager("Denver");
        wm.addWeather(scene);

        primaryStage.show();
    }
}

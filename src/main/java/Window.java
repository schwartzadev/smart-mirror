/**
 * Created by Andrew Schwartz on 10/26/17.
 */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
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
        VBox root = new VBox();
        root.setFillWidth(true);
        Scene scene = new Scene(root, Color.BLACK);


        // build window
        primaryStage.initStyle(StageStyle.UNDECORATED); // perfect for mirror
        primaryStage.setResizable(false); // rm size changer
        primaryStage.setMaximized(true);
        primaryStage.setY(0); // top of screen
        primaryStage.setX(0); // left corner
        primaryStage.setScene(scene);

        DateManager dm = new DateManager();
        dm.addTime(scene);

        WeatherManager wm = new WeatherManager("Denver");
        wm.addWeather(scene);

        GreetingManager gm = new GreetingManager();
        gm.addGreeting(scene);

        primaryStage.show();
    }
}

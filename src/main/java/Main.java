/**
 * Created by Andrew Schwartz on 10/26/17.
 */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

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

        new DateManager().addTime(scene);
        new WeatherManager("Denver").addWeather(scene);
        new GreetingManager().addGreeting(scene);
        new NewsManager("reuters", "top").add(scene);

        scene.getStylesheets().add("styles.css");

        primaryStage.show();
    }
}

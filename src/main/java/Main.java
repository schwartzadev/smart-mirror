/**
 * Created by Andrew Schwartz on 10/26/17.
 */
import com.google.gson.Gson;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(final Stage primaryStage) {
        Config cfg = loadConfig();

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
        new WeatherManager(cfg.getWeather()).addWeather(scene); // TODO put date and weather into one HBox
        new GreetingManager(cfg.getName()).addGreeting(scene);
        new NewsManager(cfg.getNews()).add(scene);

        scene.getStylesheets().add("styles.css"); // TODO make news time font size actually work..?

        primaryStage.show();
    }

    private Config loadConfig() {
        Config c = new Config();
        try {
            Gson gson = new Gson();
            c = gson.fromJson(new String(Files.readAllBytes(Paths.get(".\\config.json"))), Config.class);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return c;
    }
}

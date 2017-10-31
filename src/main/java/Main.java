/**
 * Created by Andrew Schwartz on 10/26/17.
 */
import com.google.gson.Gson;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
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


        // ADD MODULES
        VBox news = new NewsManager(cfg.getNews()).make(scene);
        Region region1 = new Region();
        HBox.setHgrow(region1, Priority.ALWAYS);

        Region region2 = new Region();
        HBox.setHgrow(region2, Priority.ALWAYS);

        HBox newsHolder = new HBox(region1, news, region2);

        VBox date = new DateManager().make();
        date.setAlignment(Pos.TOP_LEFT);

        VBox weather =  new WeatherManager(cfg.getWeather()).make();
        weather.setAlignment(Pos.TOP_RIGHT);
        HBox.setHgrow(weather, Priority.ALWAYS);

        HBox greeting = new GreetingManager(cfg.getName()).make();

        HBox topBar = new HBox(date, newsHolder, weather);

        root.getChildren().addAll(topBar, greeting);

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

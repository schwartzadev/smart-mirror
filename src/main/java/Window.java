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

        addTime(scene);
        addGreeting(scene);

        primaryStage.show();
    }

    private void addGreeting(final Scene scene) {
        GreetingManager gm = new GreetingManager();
        Timeline tl = new Timeline();

        final Text greeting = new Text(200, 600, gm.getGreeting());

        greeting.setFont(Font.font("Verdana", FontWeight.BOLD, 70));
        greeting.setFill(Color.WHITE);

        final Group root = (Group)scene.getRoot();
        root.getChildren().addAll(greeting);

        tl.setCycleCount(Animation.INDEFINITE);
        KeyFrame updateTime = new KeyFrame(Duration.seconds(60 * 60), event -> { // 1 hr refresh rate
            greeting.setText(gm.getGreeting());
        });
        tl.getKeyFrames().add(updateTime);
        tl.play();
    }

    private void addTime(final Scene scene) {
        DateManager dm = new DateManager();
        Timeline tl = new Timeline();

        final Text time = new Text(10, 110, dm.getTime());
        final Text day = new Text(10, 210, dm.getDay());
        final Text fullDate = new Text(10, 310, dm.getFullDate());

        Stream.of(time, day, fullDate).forEach(text -> {
                text.setFill(Color.WHITE);
                text.setFont(Font.font("Verdana", FontWeight.BOLD, 70));
        });
        final Group root = (Group)scene.getRoot();
        root.getChildren().addAll(time, day, fullDate);

        tl.setCycleCount(Animation.INDEFINITE);
        KeyFrame updateTime = new KeyFrame(Duration.seconds(2), event -> { // 2 seconds is the max margin of error for the time
//            (the value 2 for the keyframe duration is the refresh rate, i.e. the time will be off at max. 2 seconds from the clock)
            dm.updateDate();
            time.setText(dm.getTime());
            day.setText(dm.getDay());
            fullDate.setText(dm.getFullDate());
//            System.out.println("updated " + dm.getTime());
        });
        tl.getKeyFrames().add(updateTime);
        tl.play();
    }
}
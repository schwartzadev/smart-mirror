import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Andrew Schwartz on 10/26/17.
 */
public class GreetingManager {
    String name;

    public GreetingManager(String name) {
        this.name = name;
    }

    public String getGreeting() {
        return "Good " + getTimeOfDay() + " " + this.name;
    }

    private String getTimeOfDay() {
        int hour =  Integer.parseInt(new SimpleDateFormat("HH").format(new java.util.Date())); // 24 hr format
        if (hour > 12 && hour <= 17) {
            return "afternoon";
        } else if (hour > 17 && hour <= 19) {
            return "evening";
        } else if (hour > 19 && hour <= 23) {
            return "night";
        } else {
            return "morning";
        }
    }

    HBox make() {
        Timeline tl = new Timeline();

        final Text greeting = new Text(this.getGreeting());

        HBox greetingContainer = new HBox(greeting);
        greetingContainer.setAlignment(Pos.CENTER);
        greetingContainer.setPadding(new Insets(100, 20, 20, 20));

        greeting.setTextAlignment(TextAlignment.CENTER);

        tl.setCycleCount(Animation.INDEFINITE);

        KeyFrame updateTime = new KeyFrame(Duration.seconds(60 * 60), event -> { // 1 hr refresh rate
            greeting.setText(this.getGreeting());
        });

        tl.getKeyFrames().add(updateTime);
        tl.play();
        return greetingContainer;
    }
}

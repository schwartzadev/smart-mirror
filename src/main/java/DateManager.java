import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Andrew Schwartz on 10/26/17.
 */
public class DateManager {
    private java.util.Date date;
    private DateFormat time = new SimpleDateFormat("HH:mm");
    private DateFormat day = new SimpleDateFormat("EEEEE");
    private DateFormat fullDate = new SimpleDateFormat("MMM. dd, yyyy");

    DateManager() {
        this.date = new java.util.Date();
    }

    public void updateDate() {
        setDate(new java.util.Date());
    }

    void printInfo() {
        System.out.println("---------------");
        System.out.println(time.format(date));
        System.out.println(day.format(date));
        System.out.println("---------------");
    }

    public VBox make() {
        Timeline tl = new Timeline();

        final Text time = new Text(this.getTime());
        time.setId("clock");

        final Text day = new Text(this.getDay() + " • " + this.getFullDate());

        VBox timeContainer = new VBox(time, day);
        timeContainer.setPadding(new Insets(20));

        tl.setCycleCount(Animation.INDEFINITE);
        KeyFrame updateTime = new KeyFrame(Duration.seconds(2), event -> { // 2 seconds is the max margin of error for the time
//            (the value 2 for the keyframe duration is the refresh rate, i.e. the time will be off at max. 2 seconds from the clock)
            this.updateDate();
            time.setText(this.getTime());
            day.setText(this.getDay() + " • " + this.getFullDate());// TODO consider moving the day and full date updates to a longer keyframe...
//            System.out.println("updated " + dm.getTime());
        });
        tl.getKeyFrames().add(updateTime);
        tl.play();
        return timeContainer;
    }

    public String getDay() {
        return day.format(date);
    }

    public String getTime() {
        return time.format(date);
    }

    public String getFullDate() {
        return fullDate.format(date);
    }

    public java.util.Date getDate() {
        return date;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }

    public void setTime(DateFormat time) {
        this.time = time;
    }

    public void setDay(DateFormat day) {
        this.day = day;
    }
}

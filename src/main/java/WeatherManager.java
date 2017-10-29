import com.google.gson.Gson;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Stream;

/**
 * Created by Andrew Schwartz on 10/26/17.
 */
public class WeatherManager {
    private String location;
    private Weather weather;

    public Weather getWeather() {
        return weather;
    }

    void printInfo() {
        System.out.println("---------------");
        System.out.println("Clouds at " + this.getWeather().current.cloud + "%");
        System.out.println(this.getWeather().current.condition.text);
        System.out.println("Feels like " + this.getWeather().current.tempF);
        System.out.println("Location: " + this.getWeather().location.region);
        System.out.println("---------------");
    }

    public WeatherManager(String location) {
        this.location = location;
    }

    public Weather updateWeather() {
        String raw = null;
        try {
            raw = getJsonFromURL("https://api.apixu.com/v1/current.json?key=" + Config.key + "&q=" + location);
            Gson gson = new Gson();
            this.weather = gson.fromJson(raw, Weather.class);
        } catch (IOException e) {
            e.printStackTrace();
            this.weather = null;
        }
        return getWeather();
    }

    void addWeather(final Scene scene) {
        Timeline tl = new Timeline();

        final Text temp = new Text(this.updateWeather().current.tempF.toString());
        final Text conditions = new Text(this.updateWeather().current.condition.text);

        VBox weatherContainer = new VBox(temp, conditions);
        weatherContainer.setPadding(new Insets(20)); // TODO make 'n' pixels away from right edge

        Stream.of(temp, conditions).forEach(text -> {
            text.setFill(Color.WHITE);
            text.setFont(Font.font("Verdana", FontWeight.BOLD, 70));
        });

        weatherContainer.setAlignment(Pos.CENTER_RIGHT);

        final VBox root = (VBox)scene.getRoot();
        root.getChildren().add(weatherContainer);

        tl.setCycleCount(Animation.INDEFINITE);
        KeyFrame updateTime = new KeyFrame(Duration.seconds(10 * 60), event -> { // 10 min refresh
            temp.setText(this.updateWeather().current.tempF.toString());
        });
        tl.getKeyFrames().add(updateTime);
        tl.play();
    }

    private String getJsonFromURL(String urlLink) throws IOException{
        BufferedReader reader = null;
        StringBuffer buffer = new StringBuffer();
        try {
            URL url = new URL(urlLink);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1) {
                buffer.append(chars, 0, read);
            }
        }  catch (MalformedURLException mue) {
            System.out.println("location invalid");
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return buffer.toString();
    }
}


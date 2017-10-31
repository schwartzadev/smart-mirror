import com.google.gson.Gson;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;

/**
 * Created by Andrew Schwartz on 10/26/17.
 */
public class WeatherManager {
    private String location;
    private String apiKey;
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

    public WeatherManager(ConfigWeather cfw) {
        this.location = cfw.getLocation();
        this.apiKey = cfw.getWeatherApiKey();
    }

    public Weather update() {
        String raw;
        try {
            raw = JsonUtils.getJsonFromURL("https://api.apixu.com/v1/current.json?key=" + this.apiKey + "&q=" + location);
            Gson gson = new Gson();
            this.weather = gson.fromJson(raw, Weather.class);
        } catch (IOException e) {
            e.printStackTrace();
            this.weather = null;
        }
        return getWeather();
    }

    public VBox make() {
        Timeline tl = new Timeline();

        final Text temp = new Text(this.update().current.tempF.toString()); // TODO: use getters
        final Text conditions = new Text(this.update().current.condition.text); // TODO: use getters

        VBox weatherContainer = new VBox(temp, conditions);
        weatherContainer.setPadding(new Insets(10, 20, 0, 0)); // TODO make 'n' pixels away from right edge

        weatherContainer.setAlignment(Pos.CENTER_RIGHT);

        tl.setCycleCount(Animation.INDEFINITE);
        KeyFrame updateTime = new KeyFrame(Duration.seconds(10 * 60), event -> { // 10 min refresh
            temp.setText(this.update().current.tempF.toString());
        });
        tl.getKeyFrames().add(updateTime);
        tl.play();

        return weatherContainer;
    }
}


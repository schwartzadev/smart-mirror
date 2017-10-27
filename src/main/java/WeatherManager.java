import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

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

    public void updateWeather() throws IOException {
        String raw = getJsonFromURL("https://api.apixu.com/v1/current.json?key=" + Config.key + "&q=" + location);
        Gson gson = new Gson();
        this.weather = gson.fromJson(raw, Weather.class);
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


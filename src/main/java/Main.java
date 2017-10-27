import java.io.IOException;

/**
 * Created by Andrew Schwartz on 10/26/17.
 */
public class Main {
    public static void main(String[] args) {
        DateManager dm = new DateManager();
        WeatherManager wm = new WeatherManager("Denver");
        GreetingManager gm = new GreetingManager();
        try {
            wm.updateWeather();
        } catch (IOException e) {
            e.printStackTrace();
        }
        wm.printInfo();
        dm.printInfo();
        System.out.println(gm.getGreeting());
    }
}

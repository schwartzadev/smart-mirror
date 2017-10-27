import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Andrew Schwartz on 10/26/17.
 */
public class GreetingManager {
    String name;
    String timeOfDay;

    public GreetingManager() {
        this.name = Config.name;
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
}

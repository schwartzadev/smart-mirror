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
        System.out.println(fullDate.format(date));
        System.out.println("---------------");
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

    public void setFullDate(DateFormat fullDate) {
        this.fullDate = fullDate;
    }
}

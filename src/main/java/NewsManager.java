import com.google.gson.Gson;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * Created by Andrew Schwartz on 10/26/17.
 */
public class NewsManager {
    private News news;
    private final String source;
    private final String sortBy; // either 'top' or 'latest'
    private List<Text> headlines = new ArrayList<Text>();

    public NewsManager(String source, String sortBy) {
        this.source = source;
        this.sortBy = sortBy;
    }

    void add(final Scene scene) {
        Timeline tl = new Timeline();

        VBox container = new VBox();

        this.news = this.update();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
//        System.out.println(sdf.format(new Date()));

        for (int i = 0; i < 5; i++) {
            Text time = new Text();

            Date result = parseDate(sdf, this.news.articles.get(i).publishedAt);

            long diff = result.getTime() - new Date().getTime();
            long minutes = TimeUnit.MILLISECONDS.toMinutes(diff);
            long hours = TimeUnit.MILLISECONDS.toHours(diff);

            if (Math.abs(minutes) >= 60) {
//                time.setText(minutes + "m  " + hours + "h");
                time.setText(Math.abs(hours) + "h");
            } else {
                time.setText(Math.abs(minutes) + "m");
            }


            time.getStyleClass().add("time");

            HBox hBox = new HBox(new Text(this.news.articles.get(i).title), time); // TODO shorten article name to n characters
            hBox.setSpacing(20);
            container.getChildren().add(hBox);
//            headlines.add(new Text(this.news.articles.get(i).title));
        }
        container.setSpacing(10);
//        container.getChildren().addAll(headlines);

        container.setPadding(new Insets(20, 20, 20, 80));
        container.setId("news-container");

        final VBox root = (VBox)scene.getRoot();
        root.getChildren().add(container);

        tl.setCycleCount(Animation.INDEFINITE);
        KeyFrame updateTime = new KeyFrame(Duration.seconds(2), event -> {
            // TODO: add update cycle
        });

        tl.getKeyFrames().add(updateTime);
        tl.play();
    }

    private Date parseDate(SimpleDateFormat sdf, String parseMe) {
        Date published = null;
        try {
            published = sdf.parse(parseMe);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return published;
    }

    private News update() {
        String raw = null;
        try {
            raw = JsonUtils.getJsonFromURL("https://newsapi.org/v1/articles?source=" + this.getSource() + "&sortBy=" + this.getSortBy() + "&apiKey=" + Config.newsKey);
            Gson gson = new Gson();
            this.news = gson.fromJson(raw, News.class);
        } catch (IOException e) {
            e.printStackTrace();
            this.news = null;
        }
        return getNews();
    }

    public String getSource() {
        return source;
    }

    public String getSortBy() {
        return sortBy;
    }

    public News getNews() {
        return news;
    }
}

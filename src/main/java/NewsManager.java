import com.google.gson.Gson;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private final String apiKey;
    private final String source;
    private final String sortBy; // either 'top' or 'latest'
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");

    public NewsManager(ConfigNews cfn) {
        this.source = cfn.getSource();
        this.sortBy = cfn.getSortBy();
        this.apiKey = cfn.getNewsApiKey();
    }

    void add(final Scene scene) {
        Timeline tl = new Timeline();

        VBox container = cycle();
        final VBox root = (VBox)scene.getRoot();
        root.getChildren().add(container);


        this.sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

        tl.setCycleCount(Animation.INDEFINITE);
        KeyFrame updateTime = new KeyFrame(Duration.seconds(60 * 2), event -> {
            VBox v = cycle();
            root.getChildren().remove(root.lookup("#news-container"));
            root.getChildren().add(v);
            System.out.println("updated news");
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

    private VBox cycle() {
        VBox container = new VBox();
        this.news = this.update();
        for (int i = 0; i < 5; i++) {
            Text time = new Text();

            Date result = parseDate(this.sdf, this.news.articles.get(i).publishedAt);

            long diff = result.getTime() - new Date().getTime();
            long minutes = TimeUnit.MILLISECONDS.toMinutes(diff);
            long hours = TimeUnit.MILLISECONDS.toHours(diff);
            long days = TimeUnit.MILLISECONDS.toDays(diff);

            if (Math.abs(days) >= 1) {
                time.setText(Math.abs(days) + "d");
            } else if (Math.abs(minutes) >= 60) {
                time.setText(Math.abs(hours) + "h");
            } else {
                time.setText(Math.abs(minutes) + "m");
            }

            time.getStyleClass().add("time");

            Image newsIcon = new Image("news.png");

            HBox hBox = new HBox(new ImageView(newsIcon), new Text(this.news.articles.get(i).title), time); // TODO shorten article name to n characters
            hBox.setAlignment(Pos.BASELINE_LEFT);

            hBox.setSpacing(20);
            container.getChildren().add(hBox);
        }
        container.setSpacing(10);
        container.setPadding(new Insets(20, 20, 20, 80));
        container.setId("news-container");
        return container;
    }

    private News update() {
        String raw = null;
        try {
            raw = JsonUtils.getJsonFromURL("https://newsapi.org/v1/articles?source=" + this.getSource() + "&sortBy=" + this.getSortBy() + "&apiKey=" + this.apiKey);
            Gson gson = new Gson();
            this.news = gson.fromJson(raw, News.class);
        } catch (IOException e) {
            e.printStackTrace();
            this.news = null;
        }
        System.out.println(raw);
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

import com.google.gson.Gson;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

        for (int i = 0; i < 5; i++) {
            headlines.add(new Text(this.news.articles.get(i).title));
        }

        container.getChildren().addAll(headlines);

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

    public News update() {
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

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Andrew Schwartz on 10/29/17.
 */
public class News {
    @SerializedName("status")
    public String status;
    @SerializedName("source")
    public String source;
    @SerializedName("sortBy")
    public String sortBy;
    @SerializedName("articles")
    public List<Article> articles = null;
}

class Article {
    @SerializedName("author")
    public String author;
    @SerializedName("title")
    public String title;
    @SerializedName("description")
    public String description;
    @SerializedName("url")
    public String url;
    @SerializedName("urlToImage")
    public String urlToImage;
    @SerializedName("publishedAt")
    public String publishedAt;
}

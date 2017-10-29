import com.google.gson.annotations.SerializedName;

/**
 * Created by Andrew Schwartz on 10/26/17.
 */
public class Config {
    @SerializedName("name")
    private String name;
    @SerializedName("weather")
    private ConfigWeather weather;
    @SerializedName("news")
    private ConfigNews news;

    public String getName() {
        return name;
    }

    public ConfigWeather getWeather() {
        return weather;
    }

    public ConfigNews getNews() {
        return news;
    }
}
class ConfigNews {
    @SerializedName("source")
    private String source;
    @SerializedName("sort_by")
    private String sortBy;
    @SerializedName("news_api_key")
    private String newsApiKey;

    public String getSource() {
        return source;
    }

    public String getSortBy() {
        return sortBy;
    }

    public String getNewsApiKey() {
        return newsApiKey;
    }
}
class ConfigWeather {
    @SerializedName("location")
    private String location;
    @SerializedName("weather_api_key")
    private String weatherApiKey;

    public String getLocation() {
        return location;
    }

    public String getWeatherApiKey() {
        return weatherApiKey;
    }
}

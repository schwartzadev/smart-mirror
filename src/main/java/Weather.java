import com.google.gson.annotations.SerializedName;

/**
 * Created by Andrew Schwartz on 10/26/17.
 */
public class Weather {
    public Location location;
    public Current current;
}

    class Condition {
        public String text;
        public String icon;
        public Integer code;
    }

    class Current {
        @SerializedName("last_updated_epoch")
        public Integer lastUpdatedEpoch;
        @SerializedName("last_updated")
        public String lastUpdated;
        @SerializedName("temp_c")
        public Double tempC;
        @SerializedName("temp_f")
        public Double tempF;
        @SerializedName("is_day")
        public Integer isDay;
        public Condition condition;
        @SerializedName("wind_mph")
        public Double windMph;
        @SerializedName("wind_kph")
        public Double windKph;
        @SerializedName("wind_degree")
        public Integer windDegree;
        @SerializedName("wind_dir")
        public String windDir;
        @SerializedName("pressure_mb")
        public Double pressureMb;
        @SerializedName("pressure_in")
        public Double pressureIn;
        @SerializedName("precip_mm")
        public Double precipMm;
        @SerializedName("precip_in")
        public Double precipIn;
        public Integer humidity;
        public Integer cloud;
        @SerializedName("feelslike_c")
        public Double feelslikeC;
        @SerializedName("feelslike_f")
        public Double feelslikeF;
        @SerializedName("vis_km")
        public Double visKm;
        @SerializedName("vis_miles")
        public Double visMiles;
    }

    class Location {
        public String name;
        public String region;
        public String country;
        public Double lat;
        public Double lon;
        @SerializedName("tz_id")
        public String tzId;
        @SerializedName("localtime_epoch")
        public Integer localtimeEpoch;
        public String localtime;
    }

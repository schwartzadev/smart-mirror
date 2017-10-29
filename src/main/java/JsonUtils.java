import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Andrew Schwartz on 10/29/17.
 */
public class JsonUtils {
    public static String getJsonFromURL(String urlLink) throws IOException {
        BufferedReader reader = null;
        StringBuilder buffer = new StringBuilder();
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

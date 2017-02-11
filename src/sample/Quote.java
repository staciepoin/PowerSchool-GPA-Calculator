package sample;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by 17rsaiyid on 2/6/2017.
 */
public class Quote {
    public static String q = "";

    public Quote() throws IOException{
        getQuote();
    }

    public static String getQuote() throws IOException {
        try {
            Document doc = Jsoup.connect("https://www.adafruit.com/quotes").get();
            Element table = doc.select("table").get(0);
            Elements rows = table.select("tr");
            int rand = (int) (Math.random() * (rows.size() + 1));
            q = rows.get(rand).text();
            int endI = 0;
            for (int x = 0; x < q.length(); x++) {
                if (q.substring(x, x + 5).equals(" (Add")) {
                    endI = x;
                    break;
                }
            }
            q = q.substring(0, endI);
        } catch (IOException e) {}
        return q;
    }
}

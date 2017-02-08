import com.netty.client.HttpClients;

/**
 * Created by yh on 17-2-7.
 */
public class Test1 {
    public static void main(String[] args) {

        String url = "http://127.0.0.1:30000";
        String jsonString = "{\"name\":\"aa\",\"flag\":\"login\",\"method\":\"login\"}";
        String encoding ="utf-8";
        try {
            String text = HttpClients.send(url,jsonString,encoding);
            System.out.println(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

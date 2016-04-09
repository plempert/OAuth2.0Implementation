import java.util.UUID;

/**
 * Created by Patrick on 2/20/16.
 */
public class Model {


    public boolean verifyClientID(String client_id) {
        return true;
    }

    public String addAndReturnCode(String client_id) {

        return UUID.randomUUID().toString();
    }

    public String addAndReturnAccessToken(String client_id, String client_secret, String redirect_uri, String code) {
        return UUID.randomUUID().toString();
    }
}

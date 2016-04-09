/**
 * Created by Patrick on 2/20/16.
 */
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;


public class Controller {

    public static void main(String [] args){
        Model model = new Model();

        staticFileLocation("public");

        get("/oauth/authorize", (request, response) -> {
            if(IDandURIareValid(request)) {
                response.redirect("/login.html?client_id="+request.queryParams("client_id"));
            } else{
                return "error";
            }
            return null;
        });

        /*{

            Map map = new HashMap();
            map.put("client_id", request.queryParams("client_id"));
            map.put("redirect_uri", request.queryParams("redirect_uri"));
            new ModelAndView(map, "login.hbs"), new HandlebarsTemplateEngine()
        });

        get("/stuff", (request1, response1) -> "hello");
*/
        /*{

            String client_id = request.queryParams("client_id");
            String redirect_uri = request.queryParams("redirect_uri");
            if("code".compareTo(request.queryParams("response_type"))==0) {
                return new ModelAndView(map, "hello.hbs"), new HandlebarsTemplateEngine();
//                if (model.verifyClientID(client_id)) {
//                    String code = model.addAndReturnCode(client_id);
//                    response.redirect(redirect_uri + "?code=" + code);
//                } else {
//                    response.redirect(redirect_uri + "?error=access_denied&error_reason=user_denied&error_description=The+user+denied+your+request");
//                }
            }

        });*/

        post("/oauth/access_token", (request, response) -> {
            String client_id = request.queryParams("client_id");
            String client_secret = request.queryParams("client_secret");
            String redirect_uri = request.queryParams("redirect_uri");
            String code = request.queryParams("code");

            if("authorization_code".compareTo(request.queryParams("grant_type"))==0){
                String access_token = model.addAndReturnAccessToken(client_id, client_secret, redirect_uri, code);
            }

            return null;
        });
/* curl -F 'client_id=CLIENT_ID' \
    -F 'client_secret=CLIENT_SECRET' \
    -F 'grant_type=authorization_code' \
    -F 'redirect_uri=AUTHORIZATION_REDIRECT_URI' \
    -F 'code=CODE' \
    https://api.instagram.com/oauth/access_token*/


    }

    private static boolean IDandURIareValid(Request request) {
        return true;
    }
}

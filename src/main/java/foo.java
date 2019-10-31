import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.remote.http.W3CHttpCommandCodec;
import org.openqa.selenium.remote.http.W3CHttpResponseCodec;

import java.io.*;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;


public class foo {
    public static RemoteWebDriver createDriverFromSession(final SessionId sessionId, URL command_executor){
        CommandExecutor executor = new HttpCommandExecutor(command_executor) {

            @Override
            public Response execute(Command command) throws IOException {
                Response response = null;
                if (command.getName() == "newSession") {
                    response = new Response();
                    response.setSessionId(sessionId.toString());
                    response.setStatus(0);
                    response.setValue(Collections.<String, String>emptyMap());

                    try {
                        Field commandCodec = null;
                        commandCodec = this.getClass().getSuperclass().getDeclaredField("commandCodec");
                        commandCodec.setAccessible(true);
                        commandCodec.set(this, new W3CHttpCommandCodec());

                        Field responseCodec = null;
                        responseCodec = this.getClass().getSuperclass().getDeclaredField("responseCodec");
                        responseCodec.setAccessible(true);
                        responseCodec.set(this, new W3CHttpResponseCodec());
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                } else {
                    response = super.execute(command);
                }
                return response;
            }
        };

        return new RemoteWebDriver(executor, new DesiredCapabilities());
    }


    private static final boolean new_session = true;

    public static void main(String[] args){
        String[] session_url = readSessionIDandUrl();
        System.out.println(session_url[0] + ":" + session_url[1]);
        System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
        if(new_session){
            WebDriver driver = new FirefoxDriver();
            HttpCommandExecutor executor = (HttpCommandExecutor) ((FirefoxDriver) driver).getCommandExecutor();
            driver.get("http://www.google.com");


            //save
            URL url = executor.getAddressOfRemoteServer();
            SessionId session_id = ((FirefoxDriver) driver).getSessionId();
            saveSessionIDAndUrl(session_id.toString(), url.toString());
        }else {
            try {
                RemoteWebDriver driver2 = createDriverFromSession(new SessionId(session_url[0]), new URL(session_url[1]));
                driver2.get("http://www.microsoft.com");
            }catch (MalformedURLException e){
                e.printStackTrace();
            }
        }
    }

    private static void saveSessionIDAndUrl(String session_id, String url){
        try {
            PrintWriter writer = new PrintWriter("browser_session.txt", "UTF-8");
            writer.println(session_id);
            writer.println(url);
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static String[] readSessionIDandUrl() {
        String [] ret = new String[]{"", ""};
        try {
            File file = new File("browser_session.txt");

            BufferedReader br = new BufferedReader(new FileReader(file));

            String st;
            int count = 0;
            while ((st = br.readLine()) != null){
                ret[count] = st;
                count ++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ret;
        }
        return ret;
    }

}

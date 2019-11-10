import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;


public class bar {
    private static void testByLocal() {
        WebDriver driver = new InternetExplorerDriver();
        driver.get("https://www.google.com");
        System.out.println("Begin");
        WebElement it = driver.findElement(By.id("hptl"));

        try {
            if (it != null) {
                System.out.println(it.getText());
            } else {
                System.out.println("Not find element");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testByRemote() {
        try {
            //172.19.249.68
            URL url = new URL("http://localhost:4444/wd/hub");
            // Query the driver to find out more information
            WebDriver driver = new RemoteWebDriver(url, DesiredCapabilities.internetExplorer());
            // And now use it
            driver.get("https://www.google.com");
            System.out.println("Begin");
            WebElement it = driver.findElement(By.id("hptl"));

            //IE screenshot is black...
//            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//            String file_name = "c:\\Docker_Share\\screenshot_1.jpg";
            try {
//                FileUtils.copyFile(scrFile, new File(file_name));
                if (it != null) {
                    System.out.println(it.getText());
                } else {
                    System.out.println("Not find element");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            //very important to quit session, otherwise will reach max session 5
            System.out.println("Driver is quiting...");
            driver.quit();
        }catch (MalformedURLException e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
//        testByLocal();
        testByRemote();
    }
}

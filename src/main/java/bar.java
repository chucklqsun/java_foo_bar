import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;


public class bar {
    private static void testByLocal() {
        WebDriver driver = new EdgeDriver();
        driver.get("https://www.google.com");
        System.out.println("Begin");
        WebElement it = driver.findElement(By.id("hptl"));

        try {
            if (it != null) {
                System.out.println(it.getText());
            } else {
                System.out.println("Not find element");
            }
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testByRemote() {
        String browser = "chrome";
        try {
            //172.19.249.68
            URL url = new URL("http://localhost:4444/wd/hub");
            // Query the driver to find out more information
            WebDriver driver = null;
            if(browser.equalsIgnoreCase("chrome")){
                ChromeOptions cap = new ChromeOptions();
                cap.addArguments("--no-first-run", "--no-sandbox");
                cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
                        UnexpectedAlertBehaviour.IGNORE);
                driver = new RemoteWebDriver(url, cap);
            }
            if(browser.equalsIgnoreCase("ie")){
                driver = new RemoteWebDriver(url, DesiredCapabilities.internetExplorer());
            }
            if(browser.equalsIgnoreCase("firefox")){
                FirefoxOptions cap = new FirefoxOptions();
                cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
                        UnexpectedAlertBehaviour.IGNORE);
                driver = new RemoteWebDriver(url, cap);
            }
            if(browser.equalsIgnoreCase("edge")){
                EdgeOptions cap = new EdgeOptions();
                cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
                        UnexpectedAlertBehaviour.IGNORE);
                driver = new RemoteWebDriver(url, cap);
            }
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

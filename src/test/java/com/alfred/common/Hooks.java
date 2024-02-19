package com.alfred.common;

import com.alfred.utils.ConfigFileReader;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Hooks {

    private static ConfigFileReader configFileReader;
    @Setter
    private static WebDriver driver;
    @Getter
    private static String baseUrlWeb;
    @Getter
    private static String baseUrlApi;

    @Before //Cucumber Before Hook
    public static void setUp(){
        configFileReader = new ConfigFileReader("src/test/resources/application.properties");
        String env = configFileReader.getEnv();
        if (System.getProperty("env") != null){
            env = System.getProperty("env");
        }
        System.out.println("env is: " + env);

        configFileReader = new ConfigFileReader("src/test/resources/environments/web/" + env + ".properties");
        baseUrlWeb = configFileReader.getBaseUrl();
        System.out.println("baseUrlWeb is: " + baseUrlWeb);

        configFileReader = new ConfigFileReader("src/test/resources/environments/api/" + env + ".properties");
        baseUrlApi = configFileReader.getBaseUrl();
        System.out.println("baseUrlApi is: " + baseUrlApi);
    }

    @After // Cucumber After hook
    public static void tearDown(){
        try {
            Thread.sleep(3000);
        } catch(InterruptedException e) {
            e.printStackTrace(System.out);
        }
        if (driver != null) {
            driver.quit();
        }
    }

    //@AfterStep //Add screenshot for all steps
    public static void addScreenshot(Scenario scenario){
        final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", "image");
    }

    @AfterStep //Add screenshot for failed steps
    public static void addScreenshotForFailed(Scenario scenario){
        //validate if scenario has failed
        if(scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "image");
        }
    }

}

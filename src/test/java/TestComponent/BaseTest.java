package TestComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import AbstractComponent.TelegramNotifier;
import PageObject.MainPageRZD;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver driver;

	public MainPageRZD mainPageRZD;
	public TelegramNotifier telegram;
	
	public WebDriver initializeDriver() throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//test//java//resources//GlobalData.properties");
		prop.load(fis);
		String browserName=prop.getProperty("browser");
		
		if (browserName.contains("chrome")) {
			
			ChromeOptions options = new ChromeOptions();
			
			//WebDriverManager.chromedriver().setup();
			System.setProperty("webdriver.chrome.driver", "/home/ec2-user/chromedriver-linux64/chromedriver");
			
			if (browserName.contains("headless")) {
			options.addArguments("headless");
			 System.out.println("Headless mode!");
			}
			 driver = new ChromeDriver(options);
			 driver.manage().window().setSize(new Dimension(1440, 900));
			 System.out.println("ChromeDriver was initialized ");
			 }
		
		
		else if (browserName.equalsIgnoreCase("firefox")) {
			
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		
        else if (browserName.equalsIgnoreCase("edge")) {
			
        	WebDriverManager.edgedriver().setup();
        	driver = new EdgeDriver();
		}
		
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		return driver;
	}
	
	@BeforeMethod(alwaysRun=true)
	public MainPageRZD launchApplication() throws IOException {
		
		driver = initializeDriver();

		mainPageRZD = new MainPageRZD(driver);
		mainPageRZD.goToRZD();
	    telegram = new TelegramNotifier();
		//loginPage.goTo();
		
		return mainPageRZD;
	}
	
	//@AfterMethod(alwaysRun=true)
	public void closeBrowser() throws InterruptedException {
		
		Thread.sleep(2000);
		driver.close();
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException{
		
		String jsonContent  = FileUtils.readFileToString(new File(filePath), "UTF-8");
		ObjectMapper mapper = new ObjectMapper();
		
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
	
		return data;
	}
	
	
	//screenshot maker method
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		FileUtils.copyFile(source, file);
		
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
		
		
	}
	
	

}

package AbstractComponent;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class AbstractComponent {
	
	WebDriver driver;
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(4));
	
	@FindBy (xpath = "//button[contains(text(), 'Cart')]")
	WebElement cartIcon;
	
	public AbstractComponent(WebDriver driver) {
		
		this.driver=driver;
	}
	

	

	public void waitOfVisibilityWebElement(WebElement element) {
		
		
		wait.until(ExpectedConditions.visibilityOf(element));
		
		
	}
	
	
	

	public void waitOfWebElementClickable(WebElement element) {
		
		
		wait.until(ExpectedConditions.elementToBeClickable(element));
		
		
	}
	public void waitOfElementPresence(By locator) {
		
		
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		
		
	}
	
	public void waitOfVisibilityWebElements(List<WebElement> elements) {
		
		wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}
	
	

	

}

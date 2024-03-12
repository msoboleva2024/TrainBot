package PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponent.AbstractComponent;


public class SearchResultsRZD extends AbstractComponent {

	WebDriver driver;
	
	public SearchResultsRZD(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	


@FindBy (xpath ="//div[@class='ant-modal-content']")
WebElement modal_wnd;

@FindBy (xpath = "//div[@role='alert']")
WebElement error;

//error.findElement(By.cssSelector(".ant-alert-message")).getText();

public String checkAlerts() {
	
	try {
		
		waitOfVisibilityWebElement(error);
		System.out.println(error.findElement(By.cssSelector(".ant-alert-message")).getText());
	    
		return error.findElement(By.cssSelector(".ant-alert-message")).getText();
		
		
	}catch(Exception e) {
	
	try {
		
		waitOfVisibilityWebElement(modal_wnd);
		System.out.println(modal_wnd.findElement(By.cssSelector(".ant-modal-body")).getText());
		modal_wnd.findElement(By.cssSelector("button .ant-btn ant-btn-primary")).click();
		return modal_wnd.findElement(By.cssSelector(".ant-modal-body")).getText();
	}
	catch(Exception e1) {
		return "no errors";
	}}
}


@FindBy (xpath = "//div[@class='l-filter-dropdown__found']")
WebElement foundTrainQuantity;

public String returnTrains() {
	
	String finalOutput = " ";
	
	
	if (checkAlerts().contains("no errors")){
	
	waitOfVisibilityWebElement(foundTrainQuantity);
	finalOutput = foundTrainQuantity.getText()+": ";
    System.out.println(foundTrainQuantity.getText());
	
	List<WebElement> listOfTrains = driver.findElements(By.cssSelector(".l-train-card"));
	
	for (int i=0;i<listOfTrains.size();i++) {
		finalOutput = finalOutput+listOfTrains.get(i).findElement(By.xpath("//div[@class='l-train-desc__num']/a")).getText()+";";		
	}
	
	System.out.println(finalOutput);
	finalOutput = finalOutput+"CКОРЕЕ БЕГИ БРОНИРОВАТЬ =)";
	
	
} else
		
		finalOutput = "К сожалению, все еще выдает вот такое сообщение при поиске: " + checkAlerts();
	
	return finalOutput;
	
}


	

}

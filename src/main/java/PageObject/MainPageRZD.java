package PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponent.AbstractComponent;



public class MainPageRZD extends AbstractComponent {
	

	WebDriver driver;
	
	public MainPageRZD(WebDriver driver) {
		super(driver);
        this.driver=driver;
		
		PageFactory.initElements(driver,this);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy (xpath = "//div[contains(text(),'Откуда')]/parent::div//input[@type='search']")
	WebElement from_field;
	
	@FindBy (xpath = "//div[contains(text(),'Куда')]/parent::div//input[@type='search']")
	WebElement to_field;
	
	@FindBy (css ="div[class*='ddeparture']")
	WebElement departureDate;
	
	@FindBy (css = ".l-datepicker__menu .l-datepicker__list")
	WebElement menuMonths;
	
	@FindBy (css = ".ant-picker-panel")
	WebElement calender;
	
	@FindBy (id = "returnDatePiker")
	WebElement returnDate;
	
	@FindBy (css =".ant-picker-month-btn")
	WebElement currentMonth;
	
	@FindBy (css =".ant-picker-year-btn")
	WebElement currentYear;
	
	@FindBy (css=".ant-picker-header-next-btn")
	WebElement arrow_btn_calender;
	
	@FindBy (css=".ant-picker-cell-in-view .ant-picker-cell-inner")
	List<WebElement> dates;
	
	@FindBy (xpath="//button[@class='ant-btn ant-btn-primary']")
	WebElement suchen_btn;
	
	public void goToRZD() {
		
		driver.get("https://xn--d1ad.xn--90aiim0b4c.xn--80aswg/");
	
	}
	
	public boolean checkTitle() {
		
		System.out.println(driver.getTitle());
		
		if(driver.getTitle().equals("Сайт ЖД билеты - ЖД билеты на поезда онлайн"))
			
			return true;
			
		
		else return false;
		
	}
	
	public void setCityFrom(String city) throws InterruptedException {
		
		
		Actions action = new Actions(driver);
		action.click(from_field).sendKeys(from_field,city).build().perform();
		Thread.sleep(2000);
		//WebElement cityListBorder = driver.findElement(By.cssSelector(".rc-virtual-list-holder-inner"));
		List<WebElement> cityList = driver.findElements(By.cssSelector(".rc-virtual-list-holder-inner"));
		
		for (int i=0;i<cityList.size();i++) {
			
			if (cityList.get(i).findElement(By.cssSelector(".ant-select-item-option-content")).getText().equals(city))
			{	
				cityList.get(i).findElement(By.cssSelector(".ant-select-item-option-content")).click();
			    break;
			}
		}
		
		
	}
	
public void setCityTo(String city) throws InterruptedException {
		
	Actions action = new Actions(driver);
	action.click(to_field).sendKeys(to_field,city).build().perform();
	Thread.sleep(2000);
	//WebElement cityListBorder = driver.findElement(By.cssSelector(".rc-virtual-list-holder-inner"));
	List<WebElement> cityList = driver.findElements(By.cssSelector(".rc-virtual-list-holder-inner"));
	
	for (int i=0;i<cityList.size();i++) {
		
		if (cityList.get(i).findElement(By.cssSelector(".ant-select-item-option-content")).getText().equals(city))
		{	
			cityList.get(i).findElement(By.cssSelector(".ant-select-item-option-content")).click();
		    break;
		}
	}
	
	
}




public void setFromDateUpdated( String date, String month, String year) throws InterruptedException {
	

	int k=0;
	departureDate.click();
	
	waitOfVisibilityWebElement(menuMonths);
	List<WebElement> months  = menuMonths.findElements(By.cssSelector(".l-datepicker__list-item"));
	
	for (int i=0;i<months.size();i++) {
		
		if (months.get(i).getText().contains(month)) {
			
			months.get(i).click();
			WebElement actualMonth = driver.findElement(By.xpath("//div[@class='l-datepicker__name']/div[contains(text(),'"+month+"')]"));
			waitOfVisibilityWebElement(actualMonth);
			WebElement indexMonth = driver.findElement(By.xpath("//div[@class='l-datepicker__name']/div[contains(text(),'"+month+"')]/parent::div/parent::div/parent::div"));
			List<WebElement> dates = indexMonth.findElements(By.cssSelector(".l-datepicker__day"));
			
			for (int j=1;j<dates.size();j++) {
				
				if (dates.get(j).findElement(By.cssSelector("span")).getText().equals(date))
				{
					
					dates.get(j).click();
					k=1;
					
				//	Actions action = new Actions(driver);
				//	action.moveToElement(dates.get(j).findElement(By.xpath("/parent::div")).click().build().perform();
					break;
					
				}
				
			
			}
			
			if (k==1)
				break;
		
		}
		
		
		
	}
	
	
	
}

public void setFromDate( String date, String month, String year) throws InterruptedException {
	

		
		departureDate.click();
	
	
	String monthSubstring = month.substring(0, 3).toLowerCase();
	waitOfVisibilityWebElement(calender.findElement(By.cssSelector(".ant-picker-month-btn")));
	
	while (!calender.findElement(By.cssSelector(".ant-picker-month-btn")).getText().toLowerCase().contains(monthSubstring))
	{
		calender.findElement(By.cssSelector(".ant-picker-header-next-btn")).click();
	}
	
	if (calender.findElement(By.cssSelector(".ant-picker-month-btn")).getText().toLowerCase().contains(monthSubstring)) {
		
		if (calender.findElement(By.cssSelector(".ant-picker-year-btn")).getText().equals(year))
		{
			for (int i=0;i<calender.findElements(By.cssSelector(".ant-picker-cell-in-view .ant-picker-cell-inner")).size();i++) {
				
				if(calender.findElements(By.cssSelector(".ant-picker-cell-in-view .ant-picker-cell-inner")).get(i).getText().equals(date))
				{
					//calender.findElements(By.cssSelector(".ant-picker-cell-in-view .ant-picker-cell-inner")).get(i).click();
					Actions action = new Actions(driver);
					action.moveToElement(calender.findElements(By.cssSelector(".ant-picker-cell-in-view .ant-picker-cell-inner")).get(i)).build().perform();
				    
					action.click(calender.findElements(By.cssSelector(".ant-picker-cell-in-view .ant-picker-cell-inner")).get(i)).build().perform();

					break;
				}
			}
			
		}
	}
	
	
}


public void setToDate( String date, String month, String year) throws InterruptedException {
	

	returnDate.click();


String monthSubstring = month.substring(0, 3).toLowerCase();
//waitOfElementPresence(By.cssSelector(".ant-picker-panel"));

WebElement calender = driver.findElements(By.cssSelector(".ant-picker-panel")).get(1);

while (!calender.findElement(By.cssSelector(".ant-picker-month-btn")).getText().toLowerCase().contains(monthSubstring))
{
	calender.findElement(By.cssSelector(".ant-picker-header-next-btn")).click();
}

if (calender.findElement(By.cssSelector(".ant-picker-month-btn")).getText().toLowerCase().contains(monthSubstring)) {
	
	if (calender.findElement(By.cssSelector(".ant-picker-year-btn")).getText().equals(year))
	{
		for (int i=0;i<calender.findElements(By.cssSelector(".ant-picker-cell-in-view .ant-picker-cell-inner")).size();i++) {
			
			if(calender.findElements(By.cssSelector(".ant-picker-cell-in-view .ant-picker-cell-inner")).get(i).getText().equals(date))
			{
				calender.findElements(By.cssSelector(".ant-picker-cell-in-view .ant-picker-cell-inner")).get(i).click();
			
			    break;
			}
		}
		
	}
}


}


public SearchResultsRZD clickSearchTickets() {
	
	waitOfWebElementClickable(suchen_btn);
	suchen_btn.click();
	return new SearchResultsRZD(driver);
	
}

}

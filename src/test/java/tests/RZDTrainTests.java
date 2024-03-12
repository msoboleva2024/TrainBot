package tests;

import org.testng.annotations.Test;

import PageObject.SearchResultsRZD;
import TestComponent.BaseTest;

import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Test;



public class RZDTrainTests extends BaseTest {

	

	@Test
	public void searchTicketsActual() throws InterruptedException, IOException {
		
		
		long currentTimeInMillis = System.currentTimeMillis();
        Date currentDate = new Date(currentTimeInMillis);
		String currentDateTime = currentDate.toString(); 
		telegram.sendMsgToTelegram(currentDateTime. toString()+": Запуск бота со следующими критериями поиска: \n" + "Откуда: Санкт-Петербург, Куда: Анапа, Туда: 01.05.2024, Обратно: 10.05.2024");
		
		AssertJUnit.assertTrue(mainPageRZD.checkTitle());
		mainPageRZD.setCityFrom("Санкт-Петербург");
		mainPageRZD.setCityTo("Анапа");
		mainPageRZD.setFromDate("1","Май","2024");
		mainPageRZD.setToDate("10","Май","2024");
		
		SearchResultsRZD searchResultsRZD = mainPageRZD.clickSearchTickets();
		
		telegram.sendMsgToTelegram(searchResultsRZD.returnTrains());
		
		
	}
	
	@Test
	public void searchTicketsJune() throws InterruptedException, IOException {
		
		
		long currentTimeInMillis = System.currentTimeMillis();
        Date currentDate = new Date(currentTimeInMillis);
		String currentDateTime = currentDate.toString(); 
		telegram.sendMsgToTelegram(currentDateTime. toString()+": Запуск бота со следующими критериями поиска: \n" + "Откуда: Санкт-Петербург, Куда: Анапа, Туда: 11.06.2024, Обратно: 25.06.2024");
		
		AssertJUnit.assertTrue(mainPageRZD.checkTitle());
		mainPageRZD.setCityFrom("Санкт-Петербург");
		mainPageRZD.setCityTo("Анапа");
		mainPageRZD.setFromDate("11","Июнь","2024");
		mainPageRZD.setToDate("25","Июнь","2024");
		
		SearchResultsRZD searchResultsRZD = mainPageRZD.clickSearchTickets();

		telegram.sendMsgToTelegram(searchResultsRZD.returnTrains());
		
		
	}
}

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
	
	
	

	public void searchTicketsJune() throws InterruptedException, IOException {
		
		telegram.sendMsgToTelegram("Тест1: поиск билетов туда/обратно.");
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
		telegram.sendMsgToTelegram("Тест1: закончен.");
		
		
	}
	
	
	

	public void searchTicketsJuneOneDirectionA () throws InterruptedException, IOException {
		
		
		telegram.sendMsgToTelegram("Тест2: поиск билетов ТОЛЬКО ТУДА.");
		long currentTimeInMillis = System.currentTimeMillis();
        Date currentDate = new Date(currentTimeInMillis);
		String currentDateTime = currentDate.toString(); 
		telegram.sendMsgToTelegram(currentDateTime. toString()+": Запуск бота со следующими критериями поиска: \n" + "Откуда: Санкт-Петербург, Куда: Анапа, Туда: 11.06.2024. Без билетов обратно");
		
		AssertJUnit.assertTrue(mainPageRZD.checkTitle());
		mainPageRZD.setCityFrom("Санкт-Петербург");
		mainPageRZD.setCityTo("Анапа");
		mainPageRZD.setFromDate("11","Июнь","2024");
		
		
		SearchResultsRZD searchResultsRZD = mainPageRZD.clickSearchTickets();

		telegram.sendMsgToTelegram(searchResultsRZD.returnTrains());
		
		telegram.sendMsgToTelegram("Тест2: закончен.");
		
		
		
	}


	@Test
	public void searchTicketsJuneDirectionBack() throws InterruptedException, IOException {
		
		
		telegram.sendMsgToTelegram("Тест3: поиск билетов ТОЛЬКО ОБРАТНО.");
		long currentTimeInMillis = System.currentTimeMillis();
        Date currentDate = new Date(currentTimeInMillis);
		String currentDateTime = currentDate.toString(); 
		telegram.sendMsgToTelegram(currentDateTime. toString()+": Запуск бота со следующими критериями поиска: \n" + "Откуда: Анапа, Куда: Санкт-Петербург, Обратно: 25.06.2024. Без билетов туда");
		
		AssertJUnit.assertTrue(mainPageRZD.checkTitle());
		mainPageRZD.setCityFrom("Анапа");
		mainPageRZD.setCityTo("Санкт-Петербург");
		mainPageRZD.setFromDateUpdated("25","Июнь","2024");
		
		SearchResultsRZD searchResultsRZD = mainPageRZD.clickSearchTickets();
        System.out.println(searchResultsRZD.returnTrains());
		telegram.sendMsgToTelegram(searchResultsRZD.returnTrains());
		
	    telegram.sendMsgToTelegram("Тест3: закончен.");
		
		
		
	}
}

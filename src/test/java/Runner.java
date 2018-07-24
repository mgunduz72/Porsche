import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Runner {
	
	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.porsche.com/usa/modelstart/");
		driver.findElement(By.xpath("//a[@href='/usa/modelstart/all/?modelrange=718']")).click();
		
		String strCarPrice = driver.findElement(By.xpath("//*[@id=\"m982120\"]/div[1]/div[2]/div[2]")).getText();
//		System.out.println(strCarPrice);	//Print the price to verify
		
		
		strCarPrice = strCarPrice.replace("From $ ", "").replace(",", "").replace("*", "");	//strips off excessive characters
		
		double dblCarPrice = Double.parseDouble(strCarPrice); 	//converts string price to double price
		//System.out.println(dblCarPrice); //Print the price to verify
		
		// Store the current window handle
		String winHandleBefore = driver.getWindowHandle();
		System.out.println(driver.getTitle());
		
		// Clicks Build & Price, and opens a new window
		driver.findElement(By.xpath("//a[@href=\"javascript:POPUP.CC('https://cc.porsche.com/icc_pcna/ccCall.do?userID=USM&lang=us&PARAM=parameter_internet_us&ORDERTYPE=982120&CNR=C02&MODELYEAR=2019&hookURL=https%253a%252f%252fwww.porsche.com%252fusa%252fmodelstart%252fall%252f');\"]")).click();
		

//		driver.switchTo().window(driver.getWindowHandle());
		
		
		
		// Switch to new window opened
		for(String winHandle : driver.getWindowHandles()){
		   driver.switchTo().window(winHandle);
		   System.out.println(driver.getWindowHandle());
		   //System.out.println(driver.getTitle());
		}
		
		// wait to let the driver load all the code on the newly switched window
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		
		String strCarPrice2 = driver.findElement(By.xpath("//*[@id='s_price']/div[1]/div[1]/div[2]")).getText();
		System.out.println(strCarPrice2);
		strCarPrice2 = strCarPrice2.replace("From $ ", "").replace(",", "").replace("*", "");
		
	
	}

}

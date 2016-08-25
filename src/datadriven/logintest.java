package datadriven;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.base.Verify;

	
	
public class logintest {
	Object Data[][];
	WebDriver drv;
	
	
	@BeforeTest 
	public void setup()
	{
		
	drv=new FirefoxDriver();
	drv.get("http://192.168.1.241/#/login");
	}
	
	@AfterTest
	public void teardown()
	{
		drv.quit();
		
	}
	
	@DataProvider(name="inputdata")
	public Object[][] inputdata() throws IOException{
		DataDrivenExcel excel=new DataDrivenExcel();
		Object [][] x= excel.ReadData("input","D:\\login.xlsx");		
		return x;
	}
	
	@Test(priority=0,dataProvider="inputdata")
	public void Testlogin(String x,String y) throws IOException, InterruptedException
	{
	
	
	WebElement username=drv.findElement(By.xpath(".//*[@id='username']"));
	WebElement password=drv.findElement(By.xpath(".//*[@id='Password']"));
	WebElement btn=drv.findElement(By.xpath(".//*[@id='login']"));
	
	username.clear();
	
	username.sendKeys(x.replaceAll(".0", ""));
	
	password.sendKeys(y);
	
	Thread.sleep(2000);
	btn.click();
	Thread.sleep(3000);
	
	
	String	msg=drv.getCurrentUrl();
		Assert.assertEquals(msg,"http://192.168.1.241/#/listemployee");

	
	}

	}

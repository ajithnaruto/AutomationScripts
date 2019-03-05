package Automation;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class CerillionAutomation {
	String Iccid;
	
	public WebDriver driver;
	String driverpath = "D:\\\\IEDriver_32\\\\IEDriverServer.exe";
	String BaseUrl = "http://10.255.215.105/BTC/Login.aspx";
	static String accno,accessno,iccid;
	//Generating random 8digits number in-order to feed NIB field
	static int Nib = 10000000 + new Random().nextInt(90000000);
	//Generating random 3digits number to concatenate with the name
	static int namenum = 100 + new Random().nextInt(999);
	
  @Test(priority = 1)
  public void VerifyLogin() {
	  System.setProperty("webdriver.ie.driver",driverpath);
		driver = new InternetExplorerDriver();
		driver.get(BaseUrl);
		driver.manage().window().maximize();
		driver.findElement(By.id("txtUserName")).sendKeys("d2902");
		driver.findElement(By.id("txtPassword")).sendKeys("Narumugai02");
		driver.findElement(By.xpath("//span[@id='loginButton']/span")).click();  
  }
  
  @Test(priority = 2)
  public void GetSpareSim()
  {
	  driver.findElement(By.xpath("//div[@title='Inventory']")).click();
	  driver.findElement(By.xpath("//div[@title='Spare SIM']")).click();
	  driver.switchTo().frame("Content");
	  driver.findElement(By.xpath("//input[@id='btnGetData']")).click();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='grvResults']/tbody/tr[2]/td")));
	  Iccid  = driver.findElement(By.xpath("//table[@id='grvResults']/tbody/tr[2]/td")).getAttribute("innerHTML");
	  
  }
  
  
  @Test(priority = 3)
  public void CreateResidentialMobileAccount() {
	  	driver.findElement(By.xpath("//div[@title='Customer']")).click();
		driver.findElement(By.xpath("//div[@title='With Residential Account']")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.switchTo().frame("Content");
		driver.findElement(By.xpath("//table/tbody/tr[1]/td[1]/table/tbody/tr[1]/td[2]/input[@id='renderer$ctl01']")).sendKeys("BTC Test "+namenum);
		driver.findElement(By.id("renderer$NIB")).sendKeys(""+Nib);
		Select island = new Select(driver.findElement(By.id("renderer$ctl13$ctl14$ctl04")));
		island.selectByVisibleText("New Providence");
		driver.findElement(By.id("wizardControls_btn_Next")).click();
		Select offisland = new Select(driver.findElement(By.id("renderer$ctl00")));
		offisland.selectByVisibleText("New Providence");
		try {
			Thread.sleep(6000);
		} 
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("renderer$ddlGlCustomerSegment")));
		Select GLCust = new Select(driver.findElement(By.id("renderer$ddlGlCustomerSegment")));
		GLCust.selectByVisibleText("BTC Customers");
		WebDriverWait waitforct138 = new WebDriverWait(driver, 10);
		waitforct138.until(ExpectedConditions.visibilityOfElementLocated(By.id("renderer_ctl38")));
		driver.findElement(By.id("renderer_ctl38")).click();
		WebDriverWait waitforct131 = new WebDriverWait(driver, 10);
		waitforct131.until(ExpectedConditions.visibilityOfElementLocated(By.id("renderer_ctl31")));
		driver.findElement(By.id("renderer_ctl31")).click();
		driver.findElement(By.id("wizardControls_btn_Next")).click();
		WebDriverWait waitforct105 = new WebDriverWait(driver, 10);
		waitforct105.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table/tbody/tr[3]/td/table/tbody/tr[1]/td[2]/input[@id='editContact$ctl05']")));
		driver.findElement(By.xpath("//table/tbody/tr[3]/td/table/tbody/tr[1]/td[2]/input[@id='editContact$ctl05']")).sendKeys("Test");
		driver.findElement(By.xpath("//table/tbody/tr[9]/td/table/tbody/tr[1]/td[2]/input[@id='editContact$ctl21']")).sendKeys("84565987754");
		driver.findElement(By.id("wizardControls_btn_Next")).click();
		driver.findElement(By.name("wizardControls$ctl03")).click();
		driver.findElement(By.name("wizardControls$ctl01")).click(); 
  }
  
  @Test(priority = 4)
  public void ProvidePostpaidplan()
	{
		driver.findElement(By.xpath("//div/table/tbody/tr[3]/td/div/div/div/ul[20]/li[2]/ul/li[2]/a/div[@title='Sales and Post Sales']")).click();
		driver.findElement(By.xpath("//div/table/tbody/tr[3]/td/div/div/div/ul[20]/li[2]/ul/li[2]/ul/li/a/div[@title='Browse Catalogue']")).click();
		driver.switchTo().frame("Content");
		driver.findElement(By.xpath("//table/tbody/tr[3]/td/input[@id='addressDetails_btnNext']")).click();
		WebDriverWait waitforcatalogue = new WebDriverWait(driver, 10);
		waitforcatalogue.until(ExpectedConditions.visibilityOfElementLocated(By.id("ddlCatalogue")));
		Select catalogue = new Select(driver.findElement(By.id("ddlCatalogue")));
		catalogue.selectByVisibleText("GSM - Cyberworld");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/div/div/span/table/tbody/tr/td/a[contains(.,'CW - GSM Packages')]")));
		driver.findElement(By.xpath("//div/div/div/span/table/tbody/tr/td/a[contains(.,'CW - GSM Packages')]")).click();
		WebDriverWait waitforflam = new WebDriverWait(driver, 10);
		waitforflam.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/div/div/div/div/div/table/tbody/tr[2]/td[4]/a[contains(.,'Flamingo')]")));
		driver.findElement(By.xpath("//div/div/div/div/div/div/table/tbody/tr[2]/td[4]/a[contains(.,'Flamingo')]")).click();
		driver.findElement(By.xpath("//div/div/table/tbody/tr/td/input[@id='btnSaveAndGotoBasket']")).click();
		WebDriverWait waitforcheckout = new WebDriverWait(driver, 10);
		waitforcheckout.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table/tbody/tr/td/input[@id='btnGotoCheckout']")));
		driver.findElement(By.xpath("//table/tbody/tr/td/input[@id='btnGotoCheckout']")).click();
		driver.findElement(By.xpath("//div/div/table/tbody/tr/td/div/input[@id='Repeater1_ctl00_ctl00_rdoManSel']")).click();
		WebDriverWait waitforsearch = new WebDriverWait(driver, 10);
		waitforsearch.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/div/div/input[@id='Repeater1_ctl00_ctl00_btnAddManualSelection']")));
		driver.findElement(By.xpath("//div/div/div/input[@id='Repeater1_ctl00_ctl00_btnAddManualSelection']")).click();
		WebDriverWait waitforselect = new WebDriverWait(driver, 10);
		waitforselect.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/div/div/table/tbody/tr[2]/td/input[@id='manSelTable_tmscb5']")));
		driver.findElement(By.xpath("//div/div/div/table/tbody/tr[2]/td/input[@id='manSelTable_tmscb5']")).click();
		driver.findElement(By.xpath("//div/div/img[@src='http://10.255.215.105/BTC/Images/GuidedDialog/guided-dialog-next-ball.png']")).click();
		WebDriverWait waitforiccid = new WebDriverWait(driver, 10);
		waitforiccid.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/div/div/table/tbody/tr[2]/td/table/tbody/tr/td[2]/input[@id='Repeater1$ctl01$ctl00$renderer$ctl03']")));
		driver.findElement(By.xpath("//div/div/div/table/tbody/tr[2]/td/table/tbody/tr/td[2]/input[@id='Repeater1$ctl01$ctl00$renderer$ctl03']")).sendKeys(""+iccid);		
		driver.findElement(By.xpath("//div/div/div/table/tbody/tr[2]/td[2]/table/tbody/tr/td[2]/input[@id='Repeater1$ctl01$ctl00$renderer$ctl05']")).sendKeys(""+iccid);		
		driver.findElement(By.xpath("//div/div/div/table/tbody/tr[2]/td[3]/input[@src='/BTC/images/search.png']")).click();		
		Select offisland = new Select(driver.findElement(By.xpath("//div/div/div/table/tbody/tr[10]/td/table/tbody/tr/td[2]/select[@id='Repeater1$ctl01$ctl00$renderer$ctl24']")));
		offisland.selectByVisibleText("$250 Limit");
		driver.findElement(By.xpath("//div/div/img[@src='http://10.255.215.105/BTC/Images/GuidedDialog/guided-dialog-next-ball.png']")).click();		
		
	}
  
  	
  
  
  
}

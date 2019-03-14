package Automation;
import java.io.File;
import org.apache.commons.io.FileUtils;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class CerillionAutomation {
	String Iccid;
	String workorder;
	public WebDriver driver;
	String driverpath = "D:\\IEDriver_32\\IEDriverServer.exe";
	String BaseUrl = "http://10.255.215.105/BTC/Login.aspx";
	
	//Generating random 8digits number in-order to feed NIB field
	static int Nib = 10000000 + new Random().nextInt(90000000);
	//Generating random 3digits number to concatenate with the name
	static int namenum = 100 + new Random().nextInt(999);
	
  @Test(priority=1)
  public void VerifyLogin() throws Exception {
	  System.setProperty("webdriver.ie.driver",driverpath);
		driver = new InternetExplorerDriver();
		driver.get(BaseUrl);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.findElement(By.id("txtUserName")).sendKeys("d2904");
		driver.findElement(By.id("txtPassword")).sendKeys("Cerillion123");;	
		driver.findElement(By.xpath("//span[@id='loginButton']/span")).click();  
		takeScreenshot("loginsuccess");
		driver.switchTo().parentFrame();
  }
  
  	@Test(priority=2,dependsOnMethods = { "VerifyLogin" })
  	public void GetSpareSim() throws Exception
  	{
	  WebDriverWait waitforInventory = new WebDriverWait(driver, 10);
	  waitforInventory.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@title='Inventory']")));
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@title='Inventory']")).click();
	  WebDriverWait waitforspare = new WebDriverWait(driver, 10);
	  waitforspare.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@title='Spare SIM']")));
	  driver.findElement(By.xpath("//div[@title='Spare SIM']")).click();
	  driver.switchTo().frame("Content");
	  driver.findElement(By.xpath("//input[@id='btnGetData']")).click();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='grvResults']/tbody/tr[2]/td")));
	  Iccid  = driver.findElement(By.xpath("//table[@id='grvResults']/tbody/tr[2]/td")).getAttribute("innerHTML");
	  takeScreenshot("GetSparesimsuccess");
	}
	  

 
  @Test(priority=3,dependsOnMethods = {"GetSpareSim"})
  public  void SearchByAccount() throws InterruptedException
	{
		Thread.sleep(1000);
		//accno = textField_1.getText();
	  	driver.switchTo().defaultContent();
		driver.findElement(By.id("txtAccountNo")).sendKeys("91000298");
		driver.findElement(By.xpath("//img[@src ='/BTC/images/toolbar-button-off.png']")).click();
	}
  //
//  
//  @Test(priority = 3,dependsOnMethods = { "GetSpareSim" })
//  public void CreateResidentialMobileAccount() throws Exception {
//		    driver.switchTo().defaultContent();
//		    Thread.sleep(6000);
//		    driver.findElement(By.xpath("//div[@title='Customer']")).click();
//			driver.findElement(By.xpath("//div[@title='With Residential Account']")).click();
//			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//			driver.switchTo().frame("Content");
//			driver.findElement(By.xpath("//table/tbody/tr[1]/td[1]/table/tbody/tr[1]/td[2]/input[@id='renderer$ctl01']")).sendKeys("BTC Test "+namenum);
//			driver.findElement(By.id("renderer$NIB")).sendKeys(""+Nib);
//			Select island = new Select(driver.findElement(By.id("renderer$ctl13$ctl14$ctl04")));
//			island.selectByVisibleText("New Providence");
//			driver.findElement(By.id("wizardControls_btn_Next")).click();
//			Select offisland = new Select(driver.findElement(By.id("renderer$ctl00")));
//			offisland.selectByVisibleText("New Providence");
//			Thread.sleep(6000);
//			WebDriverWait waitforgl = new WebDriverWait(driver, 10);
//			waitforgl.until(ExpectedConditions.visibilityOfElementLocated(By.id("renderer$ddlGlCustomerSegment")));
//			Select GLCust = new Select(driver.findElement(By.id("renderer$ddlGlCustomerSegment")));
//			GLCust.selectByVisibleText("BTC Customers");
//			driver.findElement(By.id("renderer_ctl31")).click();
//			driver.findElement(By.id("renderer_ctl38")).click();
//			WebDriverWait waitforbtnext = new WebDriverWait(driver, 10);
//			waitforbtnext.until(ExpectedConditions.visibilityOfElementLocated(By.id("wizardControls_btn_Next")));
//			driver.findElement(By.id("wizardControls_btn_Next")).click();
//			driver.findElement(By.xpath("//table/tbody/tr[3]/td/table/tbody/tr[1]/td[2]/input[@id='editContact$ctl05']")).sendKeys("Test");
//			driver.findElement(By.xpath("//table/tbody/tr[9]/td/table/tbody/tr[1]/td[2]/input[@id='editContact$ctl21']")).sendKeys("84565987754");
//			driver.findElement(By.id("wizardControls_btn_Next")).click();
//			driver.findElement(By.name("wizardControls$ctl03")).click();
//			driver.findElement(By.name("wizardControls$ctl01")).click();
//		    takeScreenshot("Createcustsuccess");
//
//  }

  
  
  
  @Test(priority=4,dependsOnMethods = { "SearchByAccount" })
  public void ProvidePostpaidplan() throws Exception
	{
	  	Thread.sleep(2000);
	  	WebDriverWait waitforsales = new WebDriverWait(driver, 10);
		waitforsales.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/table/tbody/tr[3]/td/div/div/div/ul[20]/li[2]/ul/li[2]/a/div[@title='Sales and Post Sales']")));
		driver.findElement(By.xpath("//div/table/tbody/tr[3]/td/div/div/div/ul[20]/li[2]/ul/li[2]/a/div[@title='Sales and Post Sales']")).click();
		driver.findElement(By.xpath("//div/table/tbody/tr[3]/td/div/div/div/ul[20]/li[2]/ul/li[2]/ul/li/a/div[@title='Browse Catalogue']")).click();
		driver.switchTo().frame("Content");
	  	WebDriverWait waitforbtnnext = new WebDriverWait(driver, 10);
		waitforbtnnext.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table/tbody/tr[3]/td/input[@id='addressDetails_btnNext']")));
		driver.findElement(By.xpath("//table/tbody/tr[3]/td/input[@id='addressDetails_btnNext']")).click();
		WebDriverWait waitforcatalogue = new WebDriverWait(driver, 10);
		waitforcatalogue.until(ExpectedConditions.visibilityOfElementLocated(By.id("ddlCatalogue")));
		Select catalogue = new Select(driver.findElement(By.id("ddlCatalogue")));
		catalogue.selectByVisibleText("GSM - Cyberworld");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		Thread.sleep(1000);
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
		driver.findElement(By.xpath("//div/div[@id='Repeater1_ctl00_GuidedDialogBottomBar']/img[@src='http://10.255.215.105/BTC/Images/GuidedDialog/guided-dialog-next-ball.png']")).click();
		WebDriverWait waitforiccid = new WebDriverWait(driver, 10);
		waitforiccid.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/div/div/table/tbody/tr[2]/td/table/tbody/tr/td[2]/input[@id='Repeater1$ctl01$ctl00$renderer$ctl03']")));
		driver.findElement(By.xpath("//div/div/div/table/tbody/tr[2]/td/table/tbody/tr/td[2]/input[@id='Repeater1$ctl01$ctl00$renderer$ctl03']")).sendKeys(""+Iccid);		
		driver.findElement(By.xpath("//div/div/div/table/tbody/tr[2]/td[2]/table/tbody/tr/td[2]/input[@id='Repeater1$ctl01$ctl00$renderer$ctl05']")).sendKeys(""+Iccid);		
		driver.findElement(By.xpath("//div/div/div/table/tbody/tr[2]/td[3]/input[@src='/BTC/images/search.png']")).click();	
		Thread.sleep(3000);
		Select offisland = new Select(driver.findElement(By.xpath("//div/div/div/table/tbody/tr[10]/td/table/tbody/tr/td[2]/select[@id='Repeater1$ctl01$ctl00$renderer$ctl24']")));
		offisland.selectByVisibleText("$250 Limit");
		Thread.sleep(2500);
		driver.findElement(By.xpath("//div/div[@id='Repeater1_ctl01_GuidedDialogBottomBar']/img[@src='http://10.255.215.105/BTC/Images/GuidedDialog/guided-dialog-next-ball.png']")).click();
		
		Thread.sleep(4000);
		
		driver.findElement(By.xpath("//div/div/div/table/tbody/tr/td/table/tbody/tr/td[2]/input[@name='Repeater1$ctl02$ctl00$editContact$ctl05']")).sendKeys("Test");
		driver.findElement(By.xpath("//div/div/div/table/tbody/tr[2]/td/table/tbody/tr/td[2]/input[@name='Repeater1$ctl02$ctl00$editContact$ctl07']")).sendKeys("Test");
		driver.findElement(By.xpath("//div/div/div/table/tbody/tr[3]/td/table/tbody/tr/td[2]/input[@name='Repeater1$ctl02$ctl00$editContact$ctl08']")).sendKeys("Test");
		driver.findElement(By.xpath("//div/div/div/table/tbody/tr[4]/td/table/tbody/tr/td[2]/input[@name='Repeater1$ctl02$ctl00$editContact$ctl17']")).sendKeys("8954785465");
		driver.findElement(By.xpath("//div/div[@id='Repeater1_ctl02_GuidedDialogBottomBar']/img[@src='http://10.255.215.105/BTC/Images/GuidedDialog/guided-dialog-next-ball.png']")).click();
		
		Thread.sleep(4000);
		driver.findElement(By.xpath("//div/div/div/table/tbody/tr/td/table/tbody/tr/td/textarea[@name='Repeater1$ctl03$ctl00$txtOrderNotes']")).sendKeys("Provisioning");
		driver.findElement(By.xpath("//div/div[@id='Repeater1_ctl03_GuidedDialogBottomBar']/img[@src='http://10.255.215.105/BTC/Images/GuidedDialog/guided-dialog-next-ball.png']")).click();

		Thread.sleep(4000);
		driver.findElement(By.xpath("//div/div[@id='Repeater1_ctl04_GuidedDialogBottomBar']/img[@src='http://10.255.215.105/BTC/Images/GuidedDialog/guided-dialog-next-ball.png']")).click();

		
	
		WebDriverWait waitfornex = new WebDriverWait(driver, 10);
		waitfornex.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/div[@id='Repeater1_ctl05_GuidedDialogBottomBar']/img[@src='http://10.255.215.105/BTC/Images/GuidedDialog/guided-dialog-next-ball.png']")));
		driver.findElement(By.xpath("//div/div[@id='Repeater1_ctl05_GuidedDialogBottomBar']/img[@src='http://10.255.215.105/BTC/Images/GuidedDialog/guided-dialog-next-ball.png']")).click();
		Thread.sleep(4000);
		WebDriverWait waitforfinal = new WebDriverWait(driver, 10);
		waitforfinal.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/div[@id='Repeater1_ctl00_GuidedDialogBottomBar']/span[contains(text(),'Final Step')]")));
		driver.findElement(By.xpath("//div/div[@id='Repeater1_ctl00_GuidedDialogBottomBar']/span[contains(text(),'Final Step')]")).click();
		driver.switchTo().defaultContent();
		WebDriverWait waitforwork = new WebDriverWait(driver, 10);
		waitforwork.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("*//td[@class='infoMsg']/a")));
		workorder = driver.findElement(By.xpath("*//td[@class='infoMsg']/a")).getText();
		System.out.println(""+workorder);
		driver.findElement(By.xpath("*//td[@class='infoMsg']/a")).click();
		driver.switchTo().frame("Content");
		driver.findElement(By.xpath("//table/tbody/tr/td/div/table/tbody/tr/td/table/tbody/tr/td[7]/a[contains(.,'Job Steps')]")).click();
		Thread.sleep(3000);
		takeScreenshot("workorders");
		WebDriverWait waitforjob1 = new WebDriverWait(driver, 10);
		waitforjob1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='tableIcon']/img")));
		driver.findElement(By.xpath("//a[@class='tableIcon']/img[@title='Complete Step']")).click();
		Thread.sleep(9000);
		WebDriverWait waitforjob2= new WebDriverWait(driver, 10);
		waitforjob2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='tableIcon']/img")));
		driver.findElement(By.xpath("//a[@class='tableIcon']/img[@title='Complete Step']")).click();
		Thread.sleep(9000);
		WebDriverWait waitforjob3= new WebDriverWait(driver, 10);
		waitforjob3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='tableIcon']/img")));
		driver.findElement(By.xpath("//a[@class='tableIcon']/img[@title='Complete Step']")).click();
		Thread.sleep(3000);
		//Check Prov History

		driver.findElement(By.xpath("//div/table/tbody/tr[3]/td/div/div/div/ul[20]/li[2]/a/div[@title='Account']")).click();
		driver.findElement(By.xpath("//div/table/tbody/tr[3]/td/div/div/div/ul[20]/li[2]/ul/li[10]/a/div[@title[@='View Service Tree']")).click();
		driver.findElement(By.xpath("//a[@href='SummaryForService.aspx?Equipment=5089939&VisibleTab=ServiceSummary&pageID=SUMMARYFORSERVICE']"));	
		
	}
  
  
  	//function to take screenshot
  	public void takeScreenshot(String ssname) throws Exception {
      File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
      FileUtils.copyFile(scrFile, new File("C:\\Users\\ajithkumar.p\\Desktop\\Screenshots",String.format("%s.png",""+ssname)));
  }
  
}

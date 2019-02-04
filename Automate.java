import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;


public class Automate {
	
	//declaring globally to access anywhere within the class
    static WebDriver driver;
    
    
    //List of Actions Automated
    public static void Actions()
    {
	   	  Login();
	      //SearchByAccount();
	      //SearchByAccess();
	      //CreateResAccount();
	   	  CreateBusAccount();
    }
    
    
   //Cerillion Login
   public static void Login()
    {
	   	  driver.findElement(By.id("txtUserName")).sendKeys("d2902");
	      driver.findElement(By.id("txtPassword")).sendKeys("Ajithkumar1107");
	      driver.findElement(By.xpath("//span[@id='loginButton']/span")).click();
    }
   
   //Search with account number
   public static void SearchByAccount()
    {
	   driver.findElement(By.id("txtAccountNo")).sendKeys("91000100");
	   driver.findElement(By.xpath("//img[@src ='/BTC/images/toolbar-button-off.png']")).click();
    }
   
   //Search with access number
   public static void SearchByAccess()
    { 
	   driver.findElement(By.id("txtAccessNo")).sendKeys("");
	   driver.findElement(By.xpath("//img[@src ='/BTC/images/toolbar-button-on.png']")).click();
    }
   
   //Create Residential account
   public static void CreateResAccount()
   {
	      driver.findElement(By.xpath("//div[@title='Customer']")).click();
	      driver.findElement(By.xpath("//div[@title='With Residential Account']")).click();
	      driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	      driver.switchTo().frame("Content");
	      driver.findElement(By.xpath("//table/tbody/tr[1]/td[1]/table/tbody/tr[1]/td[2]/input[@id='renderer$ctl01']")).sendKeys("BTC Test");
	      driver.findElement(By.id("renderer$NIB")).sendKeys("84578965");
	      Select island = new Select(driver.findElement(By.id("renderer$ctl13$ctl14$ctl04")));
	      island.selectByVisibleText("New Providence");
	      driver.findElement(By.id("wizardControls_btn_Next")).click();
	      Select offisland = new Select(driver.findElement(By.id("renderer$ctl00")));
	      offisland.selectByVisibleText("New Providence");
	      
	      //sleep time issued since the page reloads after selecting office island
	      try {
			Thread.sleep(5000);
	      	} 
	      catch (InterruptedException e)
	      {
			e.printStackTrace();
	      }
	      
	      Select GLCust = new Select(driver.findElement(By.id("renderer$ddlGlCustomerSegment")));
	      GLCust.selectByVisibleText("BTC Customers");
	      driver.findElement(By.id("renderer_ctl31")).click();
	      driver.findElement(By.id("renderer_ctl38")).click();
	      driver.findElement(By.id("wizardControls_btn_Next")).click();
	      driver.findElement(By.xpath("//table/tbody/tr[3]/td/table/tbody/tr[1]/td[2]/input[@id='editContact$ctl05']")).sendKeys("Test");
	      driver.findElement(By.xpath("//table/tbody/tr[9]/td/table/tbody/tr[1]/td[2]/input[@id='editContact$ctl21']")).sendKeys("84565987754");
	      driver.findElement(By.id("wizardControls_btn_Next")).click();
	      driver.findElement(By.name("wizardControls$ctl03")).click();
	    //driver.findElement(By.name("wizardControls$ctl01")).click();
   }
   
   //Create Business account
   public static void CreateBusAccount()
   {
	      driver.findElement(By.xpath("//div[@title='Customer']")).click();
	      driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	      driver.findElement(By.xpath("//div[@title='With Business Account']")).click();
	      driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	      driver.switchTo().frame("Content");
	      driver.findElement(By.xpath("//table/tbody/tr[1]/td[1]/table/tbody/tr[1]/td[2]/input[@id='renderer$ctl01']")).sendKeys("BTC Test");
	      Select CusCategory = new Select(driver.findElement(By.id("renderer$ctl08")));
	      CusCategory.selectByVisibleText("Businesses");
	      driver.findElement(By.id("renderer$NIB")).sendKeys("84578965");
	      Select island = new Select(driver.findElement(By.id("renderer$ctl13$ctl14$ctl04")));
	      island.selectByVisibleText("New Providence");
	      driver.findElement(By.id("wizardControls_btn_Next")).click();
	      Select offisland = new Select(driver.findElement(By.id("renderer$ctl00")));
	      offisland.selectByVisibleText("New Providence");
		      
	      //sleep time issued since the page reloads after selecting office island
	      try {
			Thread.sleep(5000);
	      	} 
	      catch (InterruptedException e)
	      {
			e.printStackTrace();
	      }
	      driver.findElement(By.xpath("//table/tbody/tr[1]/td[1]/table/tbody/tr[1]/td[2]/input[@id='renderer$ctl02']")).sendKeys("Test Company");
	      
	      Select AccCategory = new Select(driver.findElement(By.id("renderer$ctl04")));
	      AccCategory.selectByVisibleText("Business");
	      Select GLCust = new Select(driver.findElement(By.id("renderer$ddlGlCustomerSegment")));
	      GLCust.selectByVisibleText("BTC Customers");
	      driver.findElement(By.id("renderer_ctl31")).click();
	      driver.findElement(By.id("renderer_ctl38")).click();
	      driver.findElement(By.id("wizardControls_btn_Next")).click();
	      driver.findElement(By.xpath("//table/tbody/tr[1]/td/table/tbody/tr/td[2]/input[@id='editContact$ctl08']")).sendKeys("BTC");
	      driver.findElement(By.xpath("//table/tbody/tr[3]/td/table/tbody/tr/td[2]/input[@id='editContact$ctl05']")).sendKeys("Test");
	      driver.findElement(By.xpath("//table/tbody/tr[7]/td/table/tbody/tr/td[2]/input[@id='editContact$ctl17']")).sendKeys("84565987754");
	      driver.findElement(By.id("wizardControls_btn_Next")).click();
	      driver.findElement(By.name("wizardControls$ctl03")).click();
//	      driver.findElement(By.name("wizardControls$ctl01")).click();
   }
   
   public static void main(String[] args)
   {
     System.setProperty("webdriver.ie.driver","D:\\IEDriver_32\\IEDriverServer.exe");
     driver = new InternetExplorerDriver();
     String BaseUrl = "http://10.255.215.105/BTC/Login.aspx";
     driver.get(BaseUrl);
     driver.manage().window().maximize();
     Actions();
   }
}

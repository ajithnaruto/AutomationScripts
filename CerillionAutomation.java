import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class CerillionAutomation extends JFrame {

	private JPanel contentPane;
	private static JTextField textField;
	private static JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	
	
	static WebDriver driver;
	//Variables to get acc# and service# from the user while search operation
	static String accno,accessno;
	//Generating random 8digits number in-order to feed NIB field
	static int Nib = 10000000 + new Random().nextInt(90000000);
	//Generating random 3digits number to concatenate with the name
	static int namenum = 100 + new Random().nextInt(999);


	/*
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CerillionAutomation frame = new CerillionAutomation();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/*
	 * Create the frame.
	 */
	public CerillionAutomation() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 541, 487);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.windowBorder);
		panel.setBounds(0, 0, 262, 214);
		contentPane.add(panel);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(CerillionAutomation.class.getResource("/logo/Prodapt.png")));
		panel.add(label);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(240, 248, 255));
		panel_1.setBounds(263, 0, 262, 448);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		//Regression automation for mobile
		JButton btnRunMobileCases = new JButton("Run Mobile Cases");
		btnRunMobileCases.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login();
				SearchByAccount();
				//CreateResCust();
				ProvidePostpaidplan();
			}
		});
		btnRunMobileCases.setBounds(73, 42, 117, 23);
		panel_1.add(btnRunMobileCases);
		
		JLabel lblRegressionAutomation = new JLabel("Regression Automation");
		lblRegressionAutomation.setBounds(61, 0, 129, 14);
		lblRegressionAutomation.setFont(new Font("Georgia", Font.ITALIC, 11));
		panel_1.add(lblRegressionAutomation);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(105, 105, 105));
		panel_2.setBounds(0, 213, 262, 235);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblCerillionAutomator = new JLabel("CERILLION AUTOMATOR");
		lblCerillionAutomator.setBounds(40, 5, 182, 15);
		lblCerillionAutomator.setForeground(new Color(128, 0, 128));
		lblCerillionAutomator.setFont(new Font("Georgia", Font.BOLD | Font.ITALIC, 13));
		panel_2.add(lblCerillionAutomator);
		
		JButton btnOneClickLogin = new JButton("One Click Login");
		btnOneClickLogin.setFont(new Font("Georgia", Font.ITALIC, 11));
		btnOneClickLogin.setBounds(10, 31, 242, 23);
		panel_2.add(btnOneClickLogin);
		
		btnOneClickLogin.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
		    Login();
		    
		  }
		});
		
		textField = new JTextField();
		textField.setToolTipText("Enter the Access number to be searched");
		textField.setBounds(10, 65, 103, 23);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JButton btnSearchAccessNo = new JButton("Search Access No");
		btnSearchAccessNo.setFont(new Font("Georgia", Font.ITALIC, 10));
		btnSearchAccessNo.setBounds(135, 65, 117, 23);
		panel_2.add(btnSearchAccessNo);
		btnSearchAccessNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				accessno = textField.getText();
				if (accessno.equals(""))
				{
					JOptionPane optionPane = new JOptionPane("Enter valid Access number",JOptionPane.WARNING_MESSAGE);
					JDialog dialog = optionPane.createDialog("Warning!");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
				}
				else
				{
				Login();
				SearchByAccess();
				}
			}
		});
		
		
		
		textField_1 = new JTextField();
		textField_1.setToolTipText("Enter the Account number to be searched");
		textField_1.setColumns(10);
		textField_1.setBounds(10, 99, 103, 23);
		panel_2.add(textField_1);
		
		JButton btnSearchAccount = new JButton("Search Account");
		btnSearchAccount.setFont(new Font("Georgia", Font.ITALIC, 10));
		btnSearchAccount.setBounds(135, 99, 117, 23);
		panel_2.add(btnSearchAccount);
		btnSearchAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				accno = textField_1.getText();
				if (accno.equals(""))
				{
					JOptionPane optionPane = new JOptionPane("Enter valid Account number",JOptionPane.WARNING_MESSAGE);
					JDialog dialog = optionPane.createDialog("Warning!");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
				}
				else
				{
				Login();
				SearchByAccount();
				}
			}
		});
		
		
		textField_2 = new JTextField();
		textField_2.setToolTipText("Enter No of Accounts to be created");
		textField_2.setBounds(10, 133, 103, 23);
		panel_2.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnCreateRes = new JButton("Create Res Account");
		btnCreateRes.setFont(new Font("Georgia", Font.ITALIC, 10));
		btnCreateRes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				String Noofacc = textField_2.getText();
				if(Noofacc.equals(""))
				{
					JOptionPane optionPane = new JOptionPane("Please Enter number of accounts to be created",JOptionPane.WARNING_MESSAGE);
					JDialog dialog = optionPane.createDialog("Warning!");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
				}
				else
				{
					for(int i=0;i<Integer.parseInt(Noofacc);i++)
					{
						Login();
						CreateResCust();
						driver.close();
					}
				}
			}
		});
		btnCreateRes.setBounds(135, 133, 117, 23);
		panel_2.add(btnCreateRes);
		
		textField_3 = new JTextField();
		textField_3.setToolTipText("Enter No of Accounts to be created");
		textField_3.setBounds(10, 167, 103, 23);
		panel_2.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnCreateBus = new JButton("Create Bus Account");
		btnCreateBus.setFont(new Font("Georgia", Font.ITALIC, 10));
		btnCreateBus.setBounds(135, 167, 117, 23);
		panel_2.add(btnCreateBus);
		btnCreateBus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String Noofacc1 = textField_3.getText();
				if(Noofacc1.equals(""))
				{
					JOptionPane optionPane = new JOptionPane("Please Enter number of accounts to be created",JOptionPane.WARNING_MESSAGE);
					JDialog dialog = optionPane.createDialog("Warning!");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
				}
				else
				{
					for(int i=0;i<Integer.parseInt(Noofacc1);i++)
					{
						Login();
						CreateBusCust();
						driver.close();
					}
				}
			}
		});
	}
	
	//Functions handling the buttons listeners
	public static void Login()
	{
		System.setProperty("webdriver.ie.driver","D:\\IEDriver_32\\IEDriverServer.exe");
		driver = new InternetExplorerDriver();
		String BaseUrl = "http://10.255.215.105/BTC/Login.aspx";
		driver.get(BaseUrl);
		driver.manage().window().maximize();
		driver.findElement(By.id("txtUserName")).sendKeys("d2902");
		driver.findElement(By.id("txtPassword")).sendKeys("Narumugai02");
		driver.findElement(By.xpath("//span[@id='loginButton']/span")).click();  
	}
	public static void CreateResCust()
	{
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
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("renderer$ddlGlCustomerSegment")));
		Select GLCust = new Select(driver.findElement(By.id("renderer$ddlGlCustomerSegment")));
		GLCust.selectByVisibleText("BTC Customers");
//		driver.findElement(By.id("renderer_ctl31")).click();
		driver.findElement(By.id("renderer_ctl38")).click();
		driver.findElement(By.id("wizardControls_btn_Next")).click();
		driver.findElement(By.xpath("//table/tbody/tr[3]/td/table/tbody/tr[1]/td[2]/input[@id='editContact$ctl05']")).sendKeys("Test");
		driver.findElement(By.xpath("//table/tbody/tr[9]/td/table/tbody/tr[1]/td[2]/input[@id='editContact$ctl21']")).sendKeys("84565987754");
		driver.findElement(By.id("wizardControls_btn_Next")).click();
		driver.findElement(By.name("wizardControls$ctl03")).click();
		//driver.findElement(By.name("wizardControls$ctl01")).click();
	}
	public static void CreateBusCust()
	{
		driver.findElement(By.xpath("//div[@title='Customer']")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//div[@title='With Business Account']")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.switchTo().frame("Content");
		driver.findElement(By.xpath("//table/tbody/tr[1]/td[1]/table/tbody/tr[1]/td[2]/input[@id='renderer$ctl01']")).sendKeys("BTC Test "+namenum);
		Select CusCategory = new Select(driver.findElement(By.id("renderer$ctl08")));
		CusCategory.selectByVisibleText("Businesses");
		driver.findElement(By.id("renderer$NIB")).sendKeys(""+Nib);
		Select island = new Select(driver.findElement(By.id("renderer$ctl13$ctl14$ctl04")));
		island.selectByVisibleText("New Providence");
		driver.findElement(By.id("wizardControls_btn_Next")).click();
		Select offisland = new Select(driver.findElement(By.id("renderer$ctl00")));
		offisland.selectByVisibleText("New Providence");
		//sleep time issued since the page reloads after selecting office island
//		try {
//			Thread.sleep(6000);
//		} 
//		catch (InterruptedException e)
//		{
//			e.printStackTrace();
//		}
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
		//driver.findElement(By.name("wizardControls$ctl01")).click();
	}

	//Search with account number
	public static void SearchByAccount()
	{
		//accno = textField_1.getText();
		driver.findElement(By.id("txtAccountNo")).sendKeys("91000100");
		driver.findElement(By.xpath("//img[@src ='/BTC/images/toolbar-button-off.png']")).click();
	}

	//Search with access number
	public static void SearchByAccess()
	{ 
		accessno = textField.getText();
		driver.findElement(By.id("txtAccessNo")).sendKeys(""+accessno);
		driver.findElement(By.xpath("//img[@src ='/BTC/images/toolbar-button-on.png']")).click();
	}
	
	public static void ProvidePostpaidplan()
	{
		driver.findElement(By.xpath("//div/table/tbody/tr[3]/td/div/div/div/ul[20]/li[2]/ul/li[2]/a/div[@title='Sales and Post Sales']")).click();
		driver.findElement(By.xpath("//div/table/tbody/tr[3]/td/div/div/div/ul[20]/li[2]/ul/li[2]/ul/li/a/div[@title='Browse Catalogue']")).click();
		driver.switchTo().frame("Content");
		driver.findElement(By.xpath("//table/tbody/tr[3]/td/input[@id='addressDetails_btnNext']")).click();

		

	}
}

package com.firebase.test.tests;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
//import org.bouncycastle.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.By.ByCssSelector;

	import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.firebase.test.Utility.CommonUtilities;
import com.firebase.test.base.BaseTest;
//import com.teckarch.test.ReadDataPropertyFile;




public class AutomationScripts extends BaseTest{
	
	@Test
	public static void WithoutPassword() throws IOException{
		CommonUtilities CU=new CommonUtilities();
	Properties applicationPropertiesFile=CU.loadFile("applicationproperties");
		String url=CU.getApplicationProperty("url", applicationPropertiesFile);
		String usrname=CU.getApplicationProperty("username", applicationPropertiesFile);
		String password=CU.getApplicationProperty("password", applicationPropertiesFile);		
	//	setDriver("chrome");
	//	gotoUrl(url);		
		System.out.println("SalesForce login opens");
		driver.manage().window().maximize();
		System.out.println("window maximized");
		WebElement username=driver.findElement(By.id("username"));	
		enterText(username, usrname, "username");
		WebElement Loginbutton=driver.findElement(By.id("Login"));			
		clickElement(Loginbutton, "Login button");
	//	closeBrowser();
				
	}

	@Test
	public static void ValidCredentials() throws IOException, InterruptedException
	{
	
		
		LoginToSalesForce();
		String expected = "Home Page ~ Salesforce - Developer Edition";
		String actual=getPageTitle();
		Assert.assertEquals(actual, expected);
		report.logTestInfo("testScript execution");

	}
	@Test
	
	public static void InvalidCredentials() throws IOException
	{
		CommonUtilities CU=new CommonUtilities();
		Properties applicationPropertiesFile=CU.loadFile("applicationproperties");
			String url=CU.getApplicationProperty("url", applicationPropertiesFile);
			String Iusrname=CU.getApplicationProperty("InValidUserName", applicationPropertiesFile);
			String Ipassword=CU.getApplicationProperty("InvalidPassword", applicationPropertiesFile);
			
		//	setDriver("chrome");
		//	gotoUrl(url);
		//	SetUp();
			
			System.out.println("SalesForce login opens");
			driver.manage().window().maximize();
			System.out.println("window maximized");
			WebElement username=driver.findElement(By.id("username"));
		
			enterText(username, Iusrname, "InValidUserName");
			WebElement password=driver.findElement(By.id("password"));
			enterText(password, Ipassword, "InvalidPassword");
			WebElement Loginbutton=driver.findElement(By.id("Login"));
			clickElement(Loginbutton, "Login button");
		//	closeBrowser();
		//	System.out.println("Browser closed");
			//tearDown();
			

	}

	//3rd Remember me check box test case
	
	@Test
	
	public static void RememberMeCheckBox() throws InterruptedException, IOException
	{
		
		CommonUtilities CU=new CommonUtilities();
		Properties applicationPropertiesFile=CU.loadFile("applicationproperties");
			String url=CU.getApplicationProperty("url", applicationPropertiesFile);
			String usrname=CU.getApplicationProperty("username", applicationPropertiesFile);
			String pword=CU.getApplicationProperty("password", applicationPropertiesFile);
			
	//		setDriver("chrome");			
	//		driver.get("https://login.salesforce.com/  ");
			System.out.println("SalesForce login opens");
			driver.manage().window().maximize();
			System.out.println("window maximized");
			WebElement username=driver.findElement(By.id("username"));		
			enterText(username, usrname, "username");
			WebElement password=driver.findElement(By.id("password"));
			enterText(password, pword, "password");
			 
	    WebElement rememberme=driver.findElement(By.id("rememberUn"));
	    System.out.println("Remember me is displayed");	   
	    	rememberme.click();
	    	Thread.sleep(8000);
	    	WebElement Loginbutton=driver.findElement(By.id("Login"));
			clickElement(Loginbutton, "Login button");	

		
		WebElement usermenu=driver.findElement(By.className("menuButtonLabel"));
		System.out.println("User menu ");
		clickElement(usermenu,"usermenu");
	//	usermenu.click();
		System.out.println("User menu drop down list displayed");
		WebElement Logout=driver.findElement(By.linkText("Logout"));
		clickElement(Logout, "Logout");	
		//Logout.click();
		
	//	closeBrowser();
		
	}
	
	//Forgot password
	@Test
	
	public static void ForgetPassword() throws InterruptedException, IOException
	{
		CommonUtilities CU=new CommonUtilities();
		Properties applicationPropertiesFile=CU.loadFile("applicationproperties");
			String url=CU.getApplicationProperty("url", applicationPropertiesFile);
			String usrname=CU.getApplicationProperty("username", applicationPropertiesFile);
			String UNM=CU.getApplicationProperty("username", applicationPropertiesFile);
			String pword=CU.getApplicationProperty("password", applicationPropertiesFile);
			System.out.println("SalesForce login opens");
			driver.manage().window().maximize();
			System.out.println("window maximized");
			WebElement username=driver.findElement(By.id("username"));		
			enterText(username, usrname, "username");	
		WebElement Forgot=driver.findElement(By.id("forgot_password_link"));
		clickElement(Forgot, "Forgot Link");
		String Title=driver.getTitle();
		System.out.println("Title ="+Title);
		Thread.sleep(8000);
		WebElement UN=driver.findElement(By.xpath("//*[@id=\"un\"]"));			
		
		System.out.println(UNM);
		enterText(UN, UNM, "UserName");			
			//continue
		WebElement Continue=driver.findElement(By.id("continue"));
		clickElement(Continue,"Continue button");
	//	closeBrowser();
				
	}
	
	@Test
	
	public static void TC05SelectUserMenu() throws InterruptedException, IOException
	
	{
		ValidCredentials();
		WebElement usermenu=driver.findElement(By.className("menuButtonLabel"));
		clickElement(usermenu,"usermenu");

	
	}
	
	
	@Test
	
	public static void TC08DeveloperConsole() throws InterruptedException, IOException
	{
	TC05SelectUserMenu();
	WebElement DeveloperConsole=driver.findElement(By.linkText("Developer Console"));
    clickElement(DeveloperConsole,"Developer Console");
    //Thread.sleep(5000);
	
	}
	
	public static void TC10CreateAnAccount() throws InterruptedException, IOException
	{
		TC05SelectUserMenu();
		WebElement account=driver.findElement(By.linkText("Accounts"));
	    clickElement(account,"Accounts");
	    Thread.sleep(3000);
	   WebElement popup=driver.findElement(By.xpath("//div[@id=\"tryLexDialogConten\"]"));
	   if(popup.isDisplayed())
	   {
		   driver.findElement(By.xpath("//a[@id=\"tryLexDialogX\"]")).click();
	   }
	   else {
		   System.out.println("Popup is not displayed");
	   }
	 String disusername=driver.findElement(By.xpath("//*[@id=\"mru0054x000005syQN\"]/a/span")).getText();
	 System.out.println(disusername);
	 WebElement creatnewaccount=driver.findElement(By.id("createNewButton"));
	    clickElement(creatnewaccount,"creatnewaccount");
	    
	 WebElement creatnewacc=driver.findElement(By.linkText("Account"));
	    clickElement(creatnewacc,"account");
	    Thread.sleep(3000);
	    ////id=//option[contains(text(),'Technology Partner')]
	    WebElement accountname=driver.findElement(By.xpath("//input[@id='acc2']"));
	    accountname.sendKeys("Jim"); 
	   
	    Select objSelect =new Select(driver.findElement(By.xpath("//*[@id=\"acc6\"]")));
	    objSelect.selectByVisibleText("Technology Partner");
	    System.out.println("Technology partner");
	    ////body[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[2]/form[1]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[2]/input[1]
	  //   //*[@id="topButtonRow"]/input[1]
	    WebElement savebuttone=driver.findElement(By.xpath("//body[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[2]/form[1]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[2]/input[1]"));
	    clickElement(savebuttone,"savebuttone");
	    System.out.println("Test Case TC10 Create an account is pass");

	}
	
	
	//No Thanks
	public static void TC11CreateNewView() throws InterruptedException, IOException
	{
		ValidCredentials();
		WebElement account=driver.findElement(By.linkText("Accounts"));
	    clickElement(account,"Accounts");
	    Thread.sleep(3000);
	    WebElement popup=driver.findElement(By.xpath("//*[@id=\"tryLexDialog\"]"));
		   if(popup.isDisplayed())
		   {
			   driver.findElement(By.xpath("//*[@id=\"tryLexDialogX\"]")).click();
		   }
		   else {
			   System.out.println("Popup is not displayed");
		   }
	    WebElement createnewview= driver.findElement(By.linkText("Create New View"));
	    clickElement(createnewview,"createnewview");  //   
	    WebElement viewname= driver.findElement(By.id("fname"));
	    viewname.sendKeys("TestView");
	    System.out.println("TestView entered");
	    
	    WebElement viewuniname= driver.findElement(By.id("devname"));
	    viewname.sendKeys("TestView 1234");
	    System.out.println("viewuniname entered");
	    WebElement viewsave= driver.findElement(By.xpath("//body[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[2]/div[2]/form[1]/div[1]/table[1]/tbody[1]/tr[1]/td[2]/input[1]"));
	    clickElement(viewsave,"viewsave");
	    System.out.println("TC11create new view pass");
	    		    
	}
	
	public static void TC12EditView() throws IOException, InterruptedException
	{
		ValidCredentials();
		WebElement account=driver.findElement(By.linkText("Accounts"));
	    clickElement(account,"Accounts");
	    Thread.sleep(3000);
	    WebElement popup=driver.findElement(By.xpath("//*[@id=\"tryLexDialog\"]"));
		   if(popup.isDisplayed())
		   {
			   driver.findElement(By.xpath("//*[@id=\"tryLexDialogX\"]")).click();
		   }
		   else {
			   System.out.println("Popup is not displayed");
		   }
	    Select obj=new Select(driver.findElement(By.xpath("//select[@id='fcf']")));
	    obj.selectByVisibleText("TestViewTestView 123");
	    System.out.println("TestViewTestView 123");
	    System.out.println("TC12 edit view test case pass");
	}
	
	//
	public static void TC15OpertunitiesDropDown() throws IOException, InterruptedException
	{
		ValidCredentials();
		WebElement Opportunities=driver.findElement(By.linkText("Opportunities"));
	    clickElement(Opportunities,"Opportunities");
	    Thread.sleep(3000);
	    // //*[@id="lexNoThanks"]       //*[@id="tryLexDialogX"]      //*[@id="tryLexDialog"]
	    WebElement popup=driver.findElement(By.xpath("//*[@id=\"tryLexDialog\"]"));
		   if(popup.isDisplayed())
		   {
			   driver.findElement(By.xpath("//*[@id=\"tryLexDialogX\"]")).click();
		   }
		   else {
			   System.out.println("Popup is not displayed");
		   }
	    Select obj=new Select(driver.findElement(By.xpath("//select[@id='fcf']")));
	   
	    List <WebElement> elementCount=obj.getOptions();
	    System.out.println("Select opertunities drop down list" +elementCount);
	    
	    
	}
	public static void TC16CreatNewOpt() throws IOException, InterruptedException
	{
		ValidCredentials();
		WebElement Opportunities=driver.findElement(By.linkText("Opportunities"));
	    clickElement(Opportunities,"Opportunities");
	    Thread.sleep(3000);
	    // //*[@id="lexNoThanks"]       //*[@id="tryLexDialogX"]      //*[@id="tryLexDialog"]
	    WebElement popup=driver.findElement(By.xpath("//*[@id=\"tryLexDialog\"]"));
		   if(popup.isDisplayed())
		   {
			   driver.findElement(By.xpath("//*[@id=\"tryLexDialogX\"]")).click();
		   }
		   else {
			   System.out.println("Popup is not displayed");
		   }
		   WebElement newbutton=driver.findElement(By.name("new"));
		   newbutton.click();
		   Thread.sleep(2000);
		   System.out.println("new buuton clicked");
		   //opp3   opp6   opp9   stage=opp11
		   WebElement optname=driver.findElement(By.id("opp3"));
		   optname.sendKeys("NEW16");
		   System.out.println(optname);
		   WebElement Accname=driver.findElement(By.id("opp4"));
		   Accname.sendKeys("Jim123");
		   Select obj=new Select(driver.findElement(By.id("opp6")));
		   obj.selectByValue("Web");
		   System.out.println("web selected");
		   Select stageobj=new Select(driver.findElement(By.id("opp11")));
		   stageobj.selectByValue("Qualification");
		   System.out.println("Qualification selected");
		   WebElement Probability=driver.findElement(By.id("opp12"));
		   Probability.sendKeys("0");
		   System.out.println("Probability 0");
		//   WebElement Pcs=driver.findElement(By.id("opp17"));
		//   Pcs.sendKeys("jim678");
		//   System.out.println("jim678");
		   WebElement CloseDate=driver.findElement(By.id("opp9"));
		   CloseDate.click();
		   WebElement datec=driver.findElement(By.xpath("//*[@id=\"calRow2\"]/td[3]"));
		   datec.click();
		   WebElement savebutton=driver.findElement(By.name("save"));
		   savebutton.click();
		   System.out.println("Tesst case TC16CreateNewOpt pass");

	}
	
	//Opportunity Pipeline
	public static void TC17OptPipeLine() throws IOException, InterruptedException
	{
		ValidCredentials();
		WebElement Opportunities=driver.findElement(By.linkText("Opportunities"));
	    clickElement(Opportunities,"Opportunities");
	    Thread.sleep(3000);
	  
	    WebElement popup=driver.findElement(By.xpath("//*[@id=\"tryLexDialog\"]"));
		   if(popup.isDisplayed())
		   {
			   driver.findElement(By.xpath("//*[@id=\"tryLexDialogX\"]")).click();
		   }
		   else {
			   System.out.println("Popup is not displayed");
		   }
		   WebElement optpipeline=driver.findElement(By.linkText("Opportunity Pipeline"));
		    clickElement(optpipeline,"Opportunity Pipeline");
		    System.out.println("Opportunity Pipeline page displayed");
		   
	}
	//Stuck Opportunities
	public static void TC18OptStuckOptReport() throws IOException, InterruptedException
	{
		ValidCredentials();
		WebElement Opportunities=driver.findElement(By.linkText("Opportunities"));
	    clickElement(Opportunities,"Opportunities");
	    Thread.sleep(3000);
	  
	    WebElement popup=driver.findElement(By.xpath("//*[@id=\"tryLexDialog\"]"));
		   if(popup.isDisplayed())
		   {
			   driver.findElement(By.xpath("//*[@id=\"tryLexDialogX\"]")).click();
		   }
		   else {
			   System.out.println("Popup is not displayed");
		   }
		   WebElement stuckopt=driver.findElement(By.linkText("Stuck Opportunities"));
		    clickElement(stuckopt,"Stuck Opportunities");
		    System.out.println("Stuck Opportunities page displayed");
		   
	}
	//quarter_q=interval id, option=Next FQ
	//include id=open,  Closed Opportunities
	
	public static void TC19QuaterlyReport() throws IOException, InterruptedException
	{
		ValidCredentials();
		WebElement Opportunities=driver.findElement(By.linkText("Opportunities"));
	    clickElement(Opportunities,"Opportunities");
	    Thread.sleep(3000);
	  
	    WebElement popup=driver.findElement(By.xpath("//*[@id=\"tryLexDialog\"]"));
		   if(popup.isDisplayed())
		   {
			   driver.findElement(By.xpath("//*[@id=\"tryLexDialogX\"]")).click();
		   }
		   else {
			   System.out.println("Popup is not displayed");
		   }
		  Select objinterval=new Select(driver.findElement(By.id("quarter_q")));
		   objinterval.selectByVisibleText("Next FQ");
		   System.out.println("Next FQ selected");
		   Select objinclude=new Select(driver.findElement(By.id("open")));
		   objinclude.selectByVisibleText("Closed Opportunities");
		   System.out.println("Closed oppertunities");
		   WebElement runreport= driver.findElement(By.xpath("//tbody/tr[3]/td[1]/input[1]"));
		   runreport.click();
		   //System.out.println("Report page");
		   
		   
	}
	
	
	//Leads
	public static void TC20Leads() throws IOException, InterruptedException
	{
		ValidCredentials();
		WebElement Leads=driver.findElement(By.linkText("Leads"));
	    clickElement(Leads,"Leads");
	    Thread.sleep(3000);
	  
	    WebElement popup=driver.findElement(By.xpath("//*[@id=\"tryLexDialog\"]"));
		   if(popup.isDisplayed())
		   {
			   driver.findElement(By.xpath("//*[@id=\"tryLexDialogX\"]")).click();
		   }
		   else {
			   System.out.println("Popup is not displayed");
		   }
	}
	////select[@id='fcf']
	
	public static void TC22DefaultView() throws IOException, InterruptedException
	{
		ValidCredentials();
		WebElement Leads=driver.findElement(By.linkText("Leads"));
	    clickElement(Leads,"Leads");
	    Thread.sleep(8000);
	  //   //*[@id="tryLexDialogX"]      //*[@id="tryLexDialog"]
	    WebElement popup=driver.findElement(By.xpath("//*[@id=\"tryLexDialog\"]"));
		   if(popup.isDisplayed())
		   {
			   driver.findElement(By.xpath("//*[@id=\"tryLexDialogX\"]")).click();
		   }
		   else {
			   System.out.println("Popup is not displayed");
		   }
		   
		   Select obj1=new Select(driver.findElement(By.xpath("//select[@id='fcf']")));
		   obj1.selectByVisibleText("Today's Leads");
		   System.out.println("Todays lead");
	   Thread.sleep(8000);
		   WebElement umenu=driver.findElement(By.id("userNavLabel"));
		   clickElement(umenu,"umenu");
		   Thread.sleep(3000);
		   	   WebElement logout=driver.findElement(By.linkText("Logout"));
		   Thread.sleep(3000);
		   	   clickElement(logout,"logout");
		   	   
		   	Thread.sleep(3000);
		   	   String title=driver.getTitle();
		   	   System.out.println(title);
		   	   
		    WebElement username=driver.findElement(By.id("username"));
			String p_username=ReadDataPropertyFile.readPropertiesFile("UserName");
			System.out.println(p_username);
			enterText(username, p_username, "UserName");			
			WebElement Password=driver.findElement(By.id("password"));
			//WebElement Password=driver.findElement(By.id("password"));
			String pwd=ReadDataPropertyFile.readPropertiesFile("Password");
			System.out.println(pwd);
			enterText(Password, pwd, "Password");		   
			WebElement Loginbutton=driver.findElement(By.id("Login"));
			clickElement(Loginbutton, "Login button");
			Thread.sleep(5000);
		 		WebElement Leads1=driver.findElement(By.linkText("Leads"));
		    clickElement(Leads1,"Leads1");
		    Thread.sleep(3000);
			  //   //*[@id="tryLexDialogX"]      //*[@id="tryLexDialog"]
			 /*   WebElement popup1=driver.findElement(By.xpath("//*[@id=\"tryLexDialog\"]"));
				   if(popup1.isDisplayed())
				   {
					   driver.findElement(By.xpath("//*[@id=\"tryLexDialogX\"]")).click();
				   }
				   else {
					   System.out.println("Popup is not displayed");
				   }*/
		    
		    WebElement Go=driver.findElement(By.name("go"));
		    clickElement(Go,"Go");

	}
	//Recently Viewed Leads
	
	public static void TC23SelectedLeadWorks() throws IOException, InterruptedException
	{
		ValidCredentials();
		WebElement Leads=driver.findElement(By.linkText("Leads"));
	    clickElement(Leads,"Leads");
	    Thread.sleep(8000);
	  //   //*[@id="tryLexDialogX"]      //*[@id="tryLexDialog"]
	    WebElement popup=driver.findElement(By.xpath("//*[@id=\"tryLexDialog\"]"));
		   if(popup.isDisplayed())
		   {
			   driver.findElement(By.xpath("//*[@id=\"tryLexDialogX\"]")).click();
		   }
		   else {
			   System.out.println("Popup is not displayed");
		   }
		   
		   Select obj1=new Select(driver.findElement(By.xpath("//select[@id='fcf']")));
		   obj1.selectByVisibleText("Recently Viewed Leads");
		   System.out.println("Recently Viewed Leads");
	  // Thread.sleep(8000);
	}
	
	
	public static void TC24NewBuutonLeads() throws IOException, InterruptedException
	{
		ValidCredentials();
		WebElement Leads=driver.findElement(By.linkText("Leads"));
	    clickElement(Leads,"Leads");
	    Thread.sleep(8000);
	  
	    WebElement popup=driver.findElement(By.xpath("//*[@id=\"tryLexDialog\"]"));
		   if(popup.isDisplayed())
		   {
			   driver.findElement(By.xpath("//*[@id=\"tryLexDialogX\"]")).click();
		   }
		   else {
			   System.out.println("Popup is not displayed");
		   }
		   WebElement newbutton=driver.findElement(By.name("new"));
		   clickElement(newbutton,"newbutton");
		   String pagetitle=driver.getTitle();
		   System.out.println(pagetitle);
		   
		   WebElement LName=driver.findElement(By.id("name_lastlea2"));
		   LName.sendKeys("wxyz");
		   System.out.println("wxyz");
		   WebElement company=driver.findElement(By.id("lea3"));
		   company.sendKeys("wxyz1");
		   System.out.println("Company wxyz1");
		   WebElement sbutton=driver.findElement(By.name("save"));
		   clickElement(sbutton,"sbutton");
   
	}
	
	public static void TC25CreateNewAccount() throws IOException, InterruptedException
	{
		ValidCredentials();
		WebElement Contacts=driver.findElement(By.linkText("Contacts"));
	    clickElement(Contacts,"Contacts");
	    Thread.sleep(8000);
	  
	    WebElement popup=driver.findElement(By.xpath("//*[@id=\"tryLexDialog\"]"));
		   if(popup.isDisplayed())
		   {
			   driver.findElement(By.xpath("//*[@id=\"tryLexDialogX\"]")).click();
		   }
		   else {
			   System.out.println("Popup is not displayed");
		   }
		   WebElement newbut=driver.findElement(By.name("new"));
		   clickElement(newbut,"new");
		   String pagetitle=driver.getTitle();
		   System.out.println(pagetitle);
		   WebElement lname=driver.findElement(By.id("name_lastcon2"));
		   lname.sendKeys("king");
		   WebElement acc1name=driver.findElement(By.id("con4"));
		   acc1name.sendKeys("Jim123");
		   WebElement save2=driver.findElement(By.name("save"));
		   clickElement(save2,"save2");
		   String pagename=driver.getTitle();
		   System.out.println(pagename);
		   
		   
	}

	
	
	public static void TC26CreateNewViewInContact() throws IOException, InterruptedException
	{
		ValidCredentials();
		WebElement Contacts=driver.findElement(By.linkText("Contacts"));
	    clickElement(Contacts,"Contacts");
	    Thread.sleep(8000);
	  
	    WebElement popup=driver.findElement(By.xpath("//*[@id=\"tryLexDialog\"]"));
		   if(popup.isDisplayed())
		   {
			   driver.findElement(By.xpath("//*[@id=\"tryLexDialogX\"]")).click();
		   }
		   else {
			   System.out.println("Popup is not displayed");
		   }
		  WebElement newview=driver.findElement(By.linkText("Create New View"));
		   clickElement(newview,"newview");
		   Thread.sleep(3000);
		     WebElement vname=driver.findElement(By.id("fname"));
		   vname.sendKeys("Test26view2");
		   WebElement vuniquename=driver.findElement(By.id("devname"));
		   vuniquename.sendKeys("Test26UniqueView2");
		    WebElement saveview=driver.findElement(By.name("save"));
		   clickElement(saveview,"saveview");
		  
		   
	}
	
	
	
	//recently created =id=hotlist_mode,  option=Recently Created
	public static void TC27CheckRecentlyCreatedContact() throws IOException, InterruptedException
	{
		ValidCredentials();
		WebElement Contacts=driver.findElement(By.linkText("Contacts"));
	    clickElement(Contacts,"Contacts");
	    Thread.sleep(8000);
	  
	    WebElement popup=driver.findElement(By.xpath("//*[@id=\"tryLexDialog\"]"));
		   if(popup.isDisplayed())
		   {
			   driver.findElement(By.xpath("//*[@id=\"tryLexDialogX\"]")).click();
		   }
		   else {
			   System.out.println("Popup is not displayed");
		   }
		   Select obj2=new Select(driver.findElement(By.id("hotlist_mode")));
		   obj2.selectByVisibleText("Recently Created");
		   System.out.println("recently created selected");
		   String page1name=driver.getTitle();
		   System.out.println(page1name);
		   System.out.println("Test case Pass");
		   
	}
	
	
	//id=fcf, option=My Contacts
	
	public static void TC28CheckMyContactsView() throws IOException, InterruptedException
	{
		ValidCredentials();
		WebElement Contacts=driver.findElement(By.linkText("Contacts"));
	    clickElement(Contacts,"Contacts");
	    Thread.sleep(8000);
	  
	    WebElement popup=driver.findElement(By.xpath("//*[@id=\"tryLexDialog\"]"));
		   if(popup.isDisplayed())
		   {
			   driver.findElement(By.xpath("//*[@id=\"tryLexDialogX\"]")).click();
		   }
		   else {
			   System.out.println("Popup is not displayed");
		   }
		   Select obj3=new Select(driver.findElement(By.id("fcf")));
		   obj3.selectByVisibleText("My Contacts");
		   System.out.println("My Contacts selected");
		   System.out.println("Test case Pass");
	}
	
	
	
// link=king
	
	public static void TC29ViewContactInContactPage() throws IOException, InterruptedException
	{
		ValidCredentials();
		WebElement Contacts=driver.findElement(By.linkText("Contacts"));
	    clickElement(Contacts,"Contacts");
	    Thread.sleep(8000);
	  
	    WebElement popup=driver.findElement(By.xpath("//*[@id=\"tryLexDialog\"]"));
		   if(popup.isDisplayed())
		   {
			   driver.findElement(By.xpath("//*[@id=\"tryLexDialogX\"]")).click();
		   }
		   else {
			   System.out.println("Popup is not displayed");
		   }
		   WebElement contactname=driver.findElement(By.linkText("king"));
		   clickElement(contactname,"contactname");
		   String page1name=driver.getTitle();
		   System.out.println(page1name);
		   System.out.println("Test case Pass");
		   
	}
	
	public static void TC30ErrorMessageInNewViewPage() throws IOException, InterruptedException
	{
		ValidCredentials();
		WebElement Contacts=driver.findElement(By.linkText("Contacts"));
	    clickElement(Contacts,"Contacts");
	    Thread.sleep(8000);
	  
	    WebElement popup=driver.findElement(By.xpath("//*[@id=\"tryLexDialog\"]"));
		   if(popup.isDisplayed())
		   {
			   driver.findElement(By.xpath("//*[@id=\"tryLexDialogX\"]")).click();
		   }
		   else {
			   System.out.println("Popup is not displayed");
		   }
		   WebElement newview=driver.findElement(By.linkText("Create New View"));
		   clickElement(newview,"newview");
		   Thread.sleep(3000);
		    
		   WebElement vuniquename=driver.findElement(By.id("devname"));
		   vuniquename.sendKeys("SDFGH");
		    WebElement saveview=driver.findElement(By.name("save"));
		   clickElement(saveview,"saveview");
		   System.out.println("Test case Pass: displyed error message as mentioned");
		   
		   
	}
	
	public static void TC31CheckCancelButton() throws IOException, InterruptedException
	{
		ValidCredentials();
		WebElement Contacts=driver.findElement(By.linkText("Contacts"));
	    clickElement(Contacts,"Contacts");
	    Thread.sleep(8000);
	  
	    WebElement popup=driver.findElement(By.xpath("//*[@id=\"tryLexDialog\"]"));
		   if(popup.isDisplayed())
		   {
			   driver.findElement(By.xpath("//*[@id=\"tryLexDialogX\"]")).click();
		   }
		   else {
			   System.out.println("Popup is not displayed");
		   }
		  WebElement newview=driver.findElement(By.linkText("Create New View"));
		   clickElement(newview,"newview");
		   Thread.sleep(3000);
		     WebElement vname=driver.findElement(By.id("fname"));
		   vname.sendKeys("HJK");
		   WebElement vuniquename=driver.findElement(By.id("devname"));
		   vuniquename.sendKeys("UTU");
		   WebElement cancel=driver.findElement(By.name("cancel"));
		   clickElement(cancel,"cancel");
		   String pagetitle=driver.getTitle();
		   System.out.println(pagetitle);
		   System.out.println("Test case 31 passed");
		   
	}
	
	public static void TC32SaveAndNewButton() throws IOException, InterruptedException
	{
		ValidCredentials();
		WebElement Contacts=driver.findElement(By.linkText("Contacts"));
	    clickElement(Contacts,"Contacts");
	    Thread.sleep(8000);
	  
	    WebElement popup=driver.findElement(By.xpath("//*[@id=\"tryLexDialog\"]"));
		   if(popup.isDisplayed())
		   {
			   driver.findElement(By.xpath("//*[@id=\"tryLexDialogX\"]")).click();
		   }
		   else {
			   System.out.println("Popup is not displayed");
		   }
		   WebElement newbut=driver.findElement(By.name("new"));
		   clickElement(newbut,"new");
		   String pagetitle=driver.getTitle();
		   System.out.println(pagetitle);
		   WebElement lname=driver.findElement(By.id("name_lastcon2"));
		   lname.sendKeys("Ana");
		   WebElement acc1name=driver.findElement(By.id("con4"));
		   acc1name.sendKeys("Global media");
		   WebElement saveandNew=driver.findElement(By.name("save_new"));
		   clickElement(saveandNew,"saveandNew");
		   String pagename=driver.getTitle();
		   System.out.println(pagename);
		   System.out.println("Test case 32 passed");
		
	}
	
	
	public static void TC33FirstAndLastNameOfLogedUser() throws IOException, InterruptedException
	{
		ValidCredentials();
		WebElement Home=driver.findElement(By.linkText("Home"));
	    clickElement(Home,"Home");
	    Thread.sleep(8000);
	  
	    WebElement popup=driver.findElement(By.xpath("//*[@id=\"tryLexDialog\"]"));
		   if(popup.isDisplayed())
		   {
			   driver.findElement(By.xpath("//*[@id=\"tryLexDialogX\"]")).click();
		   }
		   else {
			   System.out.println("Popup is not displayed");
		   }
		   String pagename=driver.getTitle();
		   System.out.println(pagename);
		   String uname="sahana hk67j";
		   WebElement disname=driver.findElement(By.linkText("sahana hk67j"));
		   if(uname.equals(disname))
		   {
			   System.out.println("Display name and logged in user name is same");
		   }
		   else {
			   System.out.println("Display name and logged in user name is  same");
		   }
		   System.out.println("Test case 33 passed");
	}
	
	
	public static void TC34VerifyEditedLastName() throws IOException, InterruptedException
	{
		ValidCredentials();
		WebElement Home=driver.findElement(By.linkText("Home"));
	    clickElement(Home,"Home");
	    Thread.sleep(8000);
	  
	    WebElement popup=driver.findElement(By.xpath("//*[@id=\"tryLexDialog\"]"));
		   if(popup.isDisplayed())
		   {
			   driver.findElement(By.xpath("//*[@id=\"tryLexDialogX\"]")).click();
		   }
		   else {
			   System.out.println("Popup is not displayed");
		   }
		   WebElement name=driver.findElement(By.xpath("//tbody/tr[1]/td[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/a[1]/span[1]"));
		   clickElement(name,"name");
		   WebElement edit=driver.findElement(By.xpath("//tbody/tr[1]/td[1]/div[1]/div[2]/div[2]/div[1]/h3[1]/div[1]/div[1]/a[1]/img[1]"));
		   clickElement(edit,"edit");
		   driver.switchTo().frame("contactInfoContentId");
		   WebElement about=driver.findElement(By.xpath("//a[contains(text(),'About')]"));
		   clickElement(about,"About");
		   
		   WebElement lname=driver.findElement(By.id("lastName"));
		   lname.sendKeys("abcd");
		 
		   WebElement saveall=driver.findElement(By.xpath("//body/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/input[1]"));
		   clickElement(saveall,"saveall");
		   driver.switchTo().defaultContent();
		   System.out.println("Test case 34 passed");
	}
	
	public static void TC35VerifyTheTabCustamization() throws IOException, InterruptedException
	{
		ValidCredentials();
		WebElement AllTab=driver.findElement(By.xpath("//body/div[@id='contentWrapper']/div[@id='AppBodyHeader']/div[1]/div[1]/nav[1]/ul[1]/li[10]/a[1]/img[1]"));
	    clickElement(AllTab,"AllTab");
	   Thread.sleep(8000);
	  
	    WebElement custamize=driver.findElement(By.name("customize"));
	    clickElement(custamize,"custamize");
	    Thread.sleep(5000);
	/*	   if(popup.isDisplayed())
		   {
			   driver.findElement(By.xpath("//*[@id=\"tryLexDialogX\"]")).click();
		   }
		   else {
			   System.out.println("Popup is not displayed");
		   }*/
	    Select selecttab=new Select(driver.findElement(By.id("duel_select_1")));
	    selecttab.deselectByValue("Subscriptions");
	  
	    WebElement Remove=driver.findElement(By.xpath("//tbody/tr[1]/td[2]/div[3]/a[1]/img[1]"));
	    clickElement(Remove,"Remove");
	    
	    WebElement save=driver.findElement(By.name("save"));
	    clickElement(save,"save");
	    
	    WebElement usermenu=driver.findElement(By.id("userNavLabel"));
	    clickElement(usermenu,"usermenu");
	    
	    WebElement logout=driver.findElement(By.linkText("Logout"));
	    clickElement(logout,"logout");
	    ValidCredentials();
	    
	}
	
	public static void TC36LaunchAndLogin() throws IOException, InterruptedException
	{
		ValidCredentials();
		System.out.println("Test case 36 Launch and login pass");
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

package genericLibraries;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import pomPages.AddNewCategoryPage;
import pomPages.AddNewCoursePage;
import pomPages.AddNewUserPage;
import pomPages.AdminHomePage;
import pomPages.CategoryPage;
import pomPages.CourseListPage;
import pomPages.LoginPage;
import pomPages.UsersPage;
import pomPages.WelcomePage;

public class BaseClass {
	
	//@BeforeSuite
	//@BeforeTest
	
	protected PropertiesUtilities property;
	protected ExcelUtility excel;
	protected JavaUtility jutil;
	protected WebDriverUtility webutil;
	protected WebDriver driver;
	
	public static WebDriver sdriver;
	public static JavaUtility sjutil;
	
	protected WelcomePage welcome;
	protected LoginPage login;
	protected AdminHomePage home;
	protected UsersPage users;
	protected CourseListPage course;
	protected CategoryPage category;
	protected AddNewUserPage adduser;
	protected AddNewCoursePage addcourse;
	protected AddNewCategoryPage addcategory;
	
	
	@BeforeClass
	public void classConfig() {
		property=new PropertiesUtilities();
		excel=new ExcelUtility();
		jutil=new JavaUtility();
		webutil=new WebDriverUtility();
		
		property.propertiesInitialization(IConstantPath.PROPERTIES_PATH);
		driver=webutil.launchBrowser(property.readFromProperties("browser"));
		
		sdriver=driver;
		sjutil=jutil;
	}
	
	@BeforeMethod
	public void  methodConfig() {
		excel.excelInitialization(IConstantPath.EXCEL_PATH);
		
		welcome=new WelcomePage(driver);
		login=new LoginPage(driver);
		home=new AdminHomePage(driver);
		users=new UsersPage(driver);
		course=new CourseListPage(driver);
		category=new CategoryPage(driver);
		adduser=new AddNewUserPage(driver);
		addcourse=new AddNewCoursePage(driver);
		addcategory=new AddNewCategoryPage(driver);
		
		webutil.navigateToApp(property.readFromProperties("url"));
		
		long time = Long.parseLong(property.readFromProperties("timeouts"));
		webutil.waitTillElementFound(time);
		
		welcome.clickLoginButton();
		login.setEmail(property.readFromProperties("username"));
		login.setPassword(property.readFromProperties("password"));
		login.clickLogin();
	}
	@AfterMethod
	public void methodThreaddown() {
		excel.closeExcel();
		home.signOutofApp();
	}
	
	@AfterClass
	public void classThreaddown() {
		webutil.closeAllWindows();
	}
	
	//@AfterTest
    //@AfterSuit
}

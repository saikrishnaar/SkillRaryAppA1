package testScripts;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibraries.BaseClass;

public class CreateCategoryTest extends BaseClass {  
	@Test
	
	public void createCategoryTest() throws InterruptedException
	{
		SoftAssert soft=new SoftAssert();
		home.clickCoursesTab();
		home.clickCategoryLink();
		soft.assertTrue(category.getPageHeader().contains("Category"));
		
		category.clickNewButton();
		Thread.sleep(3000);
		soft.assertEquals(addcategory.getPageHeader(), "Add New category");
		Map<String,String> map=excel.readFromExcel("sheet1", "Add category");
		String categoryName= map.get("Name")+jutil.generateRandomNum(100);
		addcategory.setName(categoryName);
		addcategory.clickSave();
		
		//This is modified in github and verifying pull or not
		
		soft.assertTrue(category.getSuccessMessage().contains("Success"));
		boolean isPresent = false;
		List<WebElement> categoryList=category.getCategoryList();
		for(WebElement e: categoryList)
		{
			if(e.getText().equals(categoryName))
			{
				isPresent=true;
				break;
			}
		}
		soft.assertTrue(isPresent);
		
		category.cliclDeleteButton(categoryName, driver);
		category.clickDelete();
		soft.assertTrue(category.getSuccessMessage().contains("success"));
		soft.assertAll();
		
	}

}

package testScripts;

import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibraries.BaseClass;
import genericLibraries.IConstantPath;

public class CreateUserTest extends BaseClass{
	
	@Test
	public void createUserTest() {
		SoftAssert soft=new SoftAssert();
		home.clickUsersTab();
		soft.assertTrue(users.getPageHeader().contains("Users"));
		
		users.clickNewButton();
		soft.assertEquals(adduser.getPageHeader(), "Add New User");
		Map<String , String>map=excel.readFromExcel("Sheet1", "Add User");
		adduser.createNewUser(map.get("Email"), map.get("Password"),
				map.get("FirstName"), map.get("LastName"), map.get("Address"),
				map.get("Contactinfo"), map.get("Photo"));
		
		soft.assertTrue(users.getSuccessMessage().contains("Success"));
		
		if(course.getSuccessMessage().contains("Succes")) {
			excel.writeToExcel("Sheet1", "Add User", "Pass", IConstantPath.EXCEL_PATH);
		}
		else {
			excel.writeToExcel("Sheet1", "Add User", "Fail", IConstantPath.EXCEL_PATH);
		}
		soft.assertAll();
	}
}

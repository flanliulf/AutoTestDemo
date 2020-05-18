package testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.qameta.allure.Issue;
import java.io.IOException;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import entity.BaseTest;
import pages.LoginPageElements;
import util.ToolUt;

public class Test��½_������ȷ�Ͳ���ȷ extends BaseTest {
	private StringBuffer verificationErrors = new StringBuffer();
	String folder = "test/testLogin/";
	org.openqa.selenium.chrome.ChromeDriver driver = null;

    @BeforeClass(description = "���Գ�ʼ��")
	@Feature("��½�˳�")
	@Description("���Գ�ʼ��")
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver",
				"D:/autotest/tool/chromedriver.exe");
		driver = new org.openqa.selenium.chrome.ChromeDriver();

		this.setDrivername(driver);
		this.setDriver(driver);
		goToUrl("https://www.zentao.net/user-login.html");

		ToolUt.takeScreenShot("�����ɹ�", driver);

	}

	/*
	 * ������ȷ��¼
	 */
	@Test(priority = 1, description = "��������_�˺�������ȷ��¼")
	@Parameters({ "username", "password" })
	@Issue("AG-2759823")
	@Feature("��½�˳�")
	@Story("��½�˳�")
	@Description("���Բ���:                                                                                                                "
			+ "1.����վ                                                                                                           "                                                                                                                                                                                                                                                                                                                                                                                            
			+ "2.�����û���                                                                                                                                                                                                                                                                                                                                                                                                       "
			+ "3.������ȷ������                                                                                                                                                                                                                                                                                                                                                                                                                                                                         "
			+ "4.�����¼��ť����֤�Ƿ��¼�ɹ�                                                                                                                                                                                                                                                                                                                                                  "
			+ "5.����˳���¼��ť����֤�Ƿ��˳��ɹ�        ")
	@Severity(SeverityLevel.TRIVIAL)
	public void test_AccountLoginAndLogout(String username, String password) throws IOException  {
		try {
			enterKey(By.name(LoginPageElements.username), username);
			enterKey(By.id(LoginPageElements.pwd), password);
			click(By.id(LoginPageElements.loginBtn));
			verifyElement(By.xpath("//a[.='�˳�']"));
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUt.takeScreenShot("ϵͳ����:" + e.toString(), this.getDrivername());
			AssertJUnit.fail("���Բ�ͨ��");
		}
	}

	/*
	 * ������������¼
	 */
	@Test(priority = 2, description = "��������_�˺���������¼")
	@Parameters({ "username", "Wpassword" })
	@Issue("AG-2759823")
	@Feature("��½�˳�")
	@Story("��½�˳�")
	@Description("���Բ���:                                                                                                                "
			+ "1.�����û���                                                                                                                                                                                                                                                                                                                                                                                                     "
			+ "2.������������                                                                                                                                                                                                                                                                                                                                                                                                                                                                         "
			+ "3.�����¼��ť����֤�Ƿ��¼�ɹ�                                                                                                                                                                                                                                                                                                                                                  ")
	@Severity(SeverityLevel.TRIVIAL)
	public void test_AccountLoginWithWrongPassword(String username,
			String Wpassword) throws IOException {
		try {

			enterKey(By.name(LoginPageElements.username), username);
			enterKey(By.id(LoginPageElements.pwd), Wpassword);
			click(By.id(LoginPageElements.loginBtn));
			verifyElement(By.xpath(LoginPageElements.username));
			Thread.sleep(1000);

		} catch (Exception e) {
			e.printStackTrace();
			ToolUt.takeScreenShot("ϵͳ����:" + e.toString(), driver);
			AssertJUnit.fail("���Բ�ͨ��");
		}
	}

	@AfterClass(description = "tearDown")
	@Feature("tearDown")
	@Description("tearDown")
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			AssertJUnit.fail(verificationErrorString);
		}
	}
	

}

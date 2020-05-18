package entity;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;
import org.testng.internal.ConstructorOrMethod;

import util.ToolUt;



/**
 * ���Ի���
 */
public class BaseTest implements IHookable {
	TakesScreenshot drivername;
	ChromeDriver driver = null;

 
 
	public ChromeDriver getDriver() {
		return driver;
	}
	public void setDriver(ChromeDriver driver) {
		this.driver = driver;
	}
	public void run(IHookCallBack iHookCallBack, ITestResult iTestResult) {
        //��iTestResult�л�ȡmethod    
        ConstructorOrMethod method = iTestResult.getMethod().getConstructorOrMethod();
    
        String name = method.getName();
        
        System.out.println("����method�� "+name);
        System.out.println("��ʼִ��~");
        iHookCallBack.runTestMethod(iTestResult); 
        System.out.println("����~");

        
    }
    @Attachment(value = "Failure in method {0}", type = "image/png")
    private byte[] takeScreenShot(String methodName,TakesScreenshot drivername) throws IOException {

    	 return drivername.getScreenshotAs(OutputType.BYTES);    }
    public TakesScreenshot getDrivername() {
 		return drivername;
 	}
 	public void setDrivername(TakesScreenshot drivername) {
 		this.drivername = drivername;
 	}
 	
 	@Step("����վ:{0}��")
	public void goToUrl(String url) throws IOException {
		try {
			driver.navigate().to(url);
			ToolUt.takeScreenShot("����վ:" + url + "�ɹ�", driver);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUt.takeScreenShot("����վ:" + url + "ʧ��", driver);
			AssertJUnit.fail("����վ:" + url + "�ɹ�");
		}

	}	
 	
 	
 	
 	/*
 	 * ������steps
 	 */
 	@Step("��������:{0},Ȼ������ {1} ��")
	public void enterKey(By by,String key) throws IOException{
		
		try {
			
			driver.findElement(by).click();
			driver.findElement(by).sendKeys(key);

			Thread.sleep(2000);
			ToolUt.takeScreenShot("����:"+key, driver);

		} catch (Exception e) {
			e.printStackTrace();
			ToolUt.takeScreenShot(e.toString(), driver);
			Assert.fail("����ʧ�ܣ��Ҳ���Ԫ��"+by);


		}
		
	}
 	
 	

	@Step("����ؼ�:{0} ��")
	public void click(By by) throws IOException{
		
		try {
			driver.findElement(by).click();
			Thread.sleep(2000);
			ToolUt.takeScreenShot("����ؼ�::"+by, driver);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUt.takeScreenShot(e.toString(), driver);
			Assert.fail("���ʧ�ܣ��Ҳ���Ԫ��"+by);

		}
		
	}
	


	@Step("��֤Ԫ��:{0} �Ƿ���� ��")
	public void verifyElement(By by) throws IOException {
		try {
			AssertJUnit.assertEquals(true, ToolUt.isElementExsit(driver, by));
			ToolUt.takeScreenShot("��֤�ɹ�:Ԫ��" + by + "���ҵ�", driver);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUt.takeScreenShot("��֤ʧ��:Ԫ��" + by + "�Ҳ���", driver);
			AssertJUnit.fail("��֤ʧ��:Ԫ��" + by + "�Ҳ���");
		}
	}	
	
	
	
 	
}

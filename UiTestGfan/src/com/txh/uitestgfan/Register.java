package com.txh.uitestgfan;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class Register extends UiAutomatorTestCase{
	public static void main(String[] args) {
		String jarName = "Register";
		String testClass = "com.txh.uitestgfan.Register";
		String testName = "testRegister";
		String androidId = "1";
		new UiAutomatorHelper(jarName, testClass, testName, androidId);
	}
	public void testRegister()throws UiObjectNotFoundException{
		clickMy();
		clickHeadrImage();
		registerScuss();
	}
	
	/**
	 * 点击“我的”进入个人中心
	 * @throws UiObjectNotFoundException
	 */
	public void clickMy()throws UiObjectNotFoundException{
		UiObject my = new UiObject(new UiSelector().text("我的"));
		my.click();
	}
	/**
	 * 点击用户登录头像
	 * @throws UiObjectNotFoundException
	 */
	public void clickHeadrImage() throws UiObjectNotFoundException {
		UiObject userLogin = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/iv_user_head"));
		userLogin.click();
		//收起键盘
		UiDevice device = getUiDevice();
		device.pressBack();
	}
	/**
	 * 使用未注册过的手机号注册,如何获取手机短信，自动填写验证码 密码大于6位小于15位
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void registerScuss() throws UiObjectNotFoundException {
		// 点击注册机锋账号
		UiObject tvReg = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_register"));
		tvReg.click();
		UiObject userN = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/et_username"));
		userN.setText("18701559910");
		UiObject identifyCode = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/rl_get_verify_code"));
		identifyCode.clickAndWaitForNewWindow(5000);
		UiObject idenCode = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/et_email"));
		idenCode.setText("845692");
		UiObject Pw = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/et_password"));
		Pw.setText("1234567");
		UiObject conPw = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/et_confirm_password"));
		conPw.setText("1234567");
		// 获取键盘并收起键盘
		UiDevice keybox = getUiDevice();
		keybox.pressBack();
		UiObject regeAndLog = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/rl_register"));
		regeAndLog.click();
	}

}

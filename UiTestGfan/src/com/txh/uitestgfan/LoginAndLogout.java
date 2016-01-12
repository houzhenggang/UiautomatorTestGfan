package com.txh.uitestgfan;

import junit.framework.Assert;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class LoginAndLogout extends UiAutomatorTestCase{
	public static void main(String[] args) {
		String jarName = "LoginAndLogout";
		String testClass = "com.txh.uitestgfan.LoginAndLogout";
		String testName = "testLoginAndLogout";
		String androidId = "1";
		new UiAutomatorHelper(jarName, testClass, testName, androidId);
	}
	/**
	 * 测试执行注册，登录的方法
	 * @throws UiObjectNotFoundException
	 */
	public void testLoginAndLogout() throws UiObjectNotFoundException{
		clickHeadrImage();
		//judgeDefaultTex();
		//succLogin();	
		blankUserNPw();
	}
	/**
	 * 点击用户登录头像
	 * 
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
	 * 判断账号、密码输入框提示默认提示字符串是否正确
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void judgeDefaultTex() throws UiObjectNotFoundException {
		UiObject userName = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/et_gfan"));
		String edUser = userName.getText();
		Assert.assertEquals("请输入登录账号", edUser);
		UiObject password = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/et_password"));
		String edPass = password.getText();
		Assert.assertEquals("", edPass);
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
	/**
	 * 输入正确的用户名、密码点击登录
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void succLogin() throws UiObjectNotFoundException {
		UiObject userName = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/et_gfan"));
		userName.setText("imopan88");
		UiObject password = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/et_password"));
		password.setText("654321");
		UiObject login = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/rl_login"));
		login.clickAndWaitForNewWindow(5000);
		UiObject afterLogin = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_user_name"));
		afterLogin.waitForExists(1000);// 等待登录
		Assert.assertEquals(true, afterLogin.exists()); // 如果成功登录，判断是否进入登录后的页面
		
		//判断登录后用户名是否正确
		String username = afterLogin.getText();
		Assert.assertEquals("imopan88", username);
		
		exitGfan();
	}
	/**
	 * 输入用户名&密码输入空格，点击登录
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void blankUserNPw() throws UiObjectNotFoundException {
		UiObject blankUser = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/et_gfan"));
		blankUser.setText(" ");
		UiObject blankPw = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/et_password"));
		blankPw.setText(" ");
		UiObject clicklogin = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/rl_login"));
		clicklogin.clickAndWaitForNewWindow(2000);
		// 判断是否提示手机号码不能为空
		UiObject waitMesg = new UiObject(
				new UiSelector().resourceId("android:id/message"));
		Assert.assertEquals(true, waitMesg.exists());

	}

	/**
	 * 直接点击登录按钮
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void clickLoginImedetely() throws UiObjectNotFoundException {
		UiObject clicklogin = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/rl_login"));
		clicklogin.click();
		// 判断是否提示手机号（用户名）不能为空
		UiObject waitMesg = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/et_gfan"));
		String text = waitMesg.getText();
		Assert.assertEquals("手机号码不能为空", text);

	}

	/**
	 * 用户名为空，输入密码点击登录
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void blankUser() throws UiObjectNotFoundException {
		UiObject userName = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/et_gfan"));
		userName.setText("imopan88");
		UiObject clicklogin1 = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/rl_login"));
		clicklogin1.click();
		// 判断是否提示手机号（用户名）不能为空
		UiObject waitMesg = new UiObject(
				new UiSelector().resourceId("android:id/message"));
		Assert.assertEquals(true, waitMesg.exists());

	}

	/**
	 * 输入用户名，密码为空点击登录
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void blankPass() throws UiObjectNotFoundException {
		UiObject userName = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/et_gfan"));
		userName.setText("imopan88");
		UiObject clicklogin2 = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/rl_login"));
		clicklogin2.click();
		// 判断是否提示密码不能为空
		UiObject waitMesg = new UiObject(
				new UiSelector().resourceId("android:id/message"));
		Assert.assertEquals(true, waitMesg.exists());
	}

	
	/**
	 * 注销-取消-确定,判断注销提示框信息是否正确
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void userLogout() throws UiObjectNotFoundException {
		UiObject userHead = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/iv_user_head"));
		userHead.click();
		UiObject confirm = new UiObject(new UiSelector().className(
				"android.widget.TextView").text("注销"));
		confirm.click();
		// 判断注销提示框信息是否正确
		UiObject tvTitle = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/textview_title"));// 提示框标题
		String titleMeg = tvTitle.getText();
		Assert.assertEquals("温馨提示", titleMeg);
		UiObject tvMesg = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/textview_middle"));// 提示框内容
		String tvMess = tvMesg.getText();
		Assert.assertEquals("你要退出当前账号吗？", tvMess);
		// 点击取消-确定，判断按钮名称是否正确
		UiObject cancle = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/button_left"));
		String cancTex = cancle.getText();
		Assert.assertEquals("取消", cancTex);
		cancle.click();
		confirm.click();
		UiObject comfirm = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/button_right"));
		String confTex = cancle.getText();
		Assert.assertEquals("确定", confTex);
		comfirm.click();

	}
	/**
	 * pressback方法退出机锋市场
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void exitGfan() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		device.pressBack();
		device.pressBack();
		device.pressBack();
	}




}

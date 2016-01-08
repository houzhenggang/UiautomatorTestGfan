package com.txh.uitestgfan;

import java.io.File;

import junit.framework.Assert;

import android.os.RemoteException;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.core.UiWatcher;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class UiTestGfan extends UiAutomatorTestCase {

	/**
	 * 执行方法
	 * 
	 * @param 机锋应用商店uitest
	 * @throws RemoteException
	 */
	public void testAppGfan() throws UiObjectNotFoundException, RemoteException {
		// openGfan();
		// jumpSplash();
		// excuteTestSearchDefaultTex();
		// clickSearchTextV();
		// inPutWord();
		// openMyPage();
		// clickUserImage();
		// userLogin();
		// userLogout();
		// leftPage();
		// exit();
		// pull();
		// screenOnOrOFF();
		// wakeUp();

	}

	/**
	 * 打开机锋应用商店
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void openGfan() throws UiObjectNotFoundException {
		getUiDevice().pressHome();
		/*
		 * UiScrollable uiScrollable = new UiScrollable(new
		 * UiSelector().scrollable(true)); uiScrollable.setAsHorizontalList();
		 * uiScrollable.scrollForward();
		 */
		// 点击机锋应用商店
		UiObject logo = new UiObject(new UiSelector().index(7));
		logo.clickAndWaitForNewWindow();
	}

	/**
	 * 点击跳过闪屏,点击跳过闪屏进入机锋市场首页
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void jumpSplash() throws UiObjectNotFoundException {
		UiObject splash = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/ib_jump_advert"));
		if (splash.exists()) {
			splash.clickAndWaitForNewWindow();
		} else {
			return;
		}
	}

	/**
	 * 获取默认搜索关键字并判断与预期结果是否一致
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void excuteTestSearchDefaultTex() throws UiObjectNotFoundException {
		UiSelector textView = new UiSelector();
		textView.index(3);
		UiObject search = new UiObject(textView);
		String text = search.getText();
		Assert.assertEquals("", text);
	}

	/**
	 * 点击搜索框
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void clickSearchTextV() throws UiObjectNotFoundException {
		UiObject textV = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_search"));
		textV.click();

	}

	/**
	 * 点击搜索框后，输入搜索关键词进行搜索
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void inPutWord() throws UiObjectNotFoundException {
		UiObject textVChild = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/top_main_rl"));
		textVChild.setText("QQ");
		sleep(1000);
		UiObject listViewOb = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/baselistview"));
		// Assert.assertEquals(0, listViewOb.getChildCount());
		UiObject childOb = listViewOb.getChild(new UiSelector().index(6));
		// childOb.click();
		Assert.assertEquals(true, childOb.exists());
		if (childOb.exists()) {
			childOb.clickAndWaitForNewWindow();
		} else {
			UiObject selectButton = new UiObject(
					new UiSelector()
							.resourceId("com.mappn.gfan:id/top_search_container"));
			selectButton.clickAndWaitForNewWindow();
		}
	}

	/**
	 * 注册
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void register() throws UiObjectNotFoundException {
		UiObject tvReg = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_register"));
		tvReg.click();
		UiObject user = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/et_username"));
		user.setText("18701559910");
		UiObject verify = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/rl_get_verify_code"));
		verify.clickAndWaitForNewWindow(5000);
		UiObject email = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/et_email"));
		email.setText("");
		UiObject pass = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/et_password"));
		pass.setText("");
		UiObject conPass = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/et_confirm_password"));
		conPass.setText("");
		UiDevice keybox = getUiDevice();
		keybox.pressBack();
		UiObject reLogin = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/rl_register"));
		reLogin.click();
	}

	/**
	 * 打开个人中心
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void openMyPage() throws UiObjectNotFoundException {
		UiObject my = new UiObject(new UiSelector().text("我的"));
		my.click();

	}

	public void clickUserImage() throws UiObjectNotFoundException {
		UiObject userLogin = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/iv_user_head"));
		userLogin.click();

	}

	/**
	 * 输入用户名、密码点击登录
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void userLogin() throws UiObjectNotFoundException {
		UiDevice uide = getUiDevice();
		uide.registerWatcher("installApp", new UiWatcher() {
			UiObject install = new UiObject(
					new UiSelector()
							.resourceId("com.android.packageinstaller:id/cancel_button"));

			public boolean checkForCondition() {
				if (install.exists()) {
					// TODO Auto-generated method stub
					try {
						install.click();
					} catch (UiObjectNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return true;
				}
				return true;
			}
		});

		UiObject userName = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/et_gfan"));
		// userName.click();
		userName.setText("imopan88");
		// Assert.assertEquals(true, userName.setText("imopan88"));
		UiObject password = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/et_password"));
		// password.click();
		password.setText("654321");
		UiObject login = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/rl_login"));
		login.clickAndWaitForNewWindow(5000);

	}

	public void userLogout() throws UiObjectNotFoundException {
		UiObject userHead = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/iv_user_head"));
		userHead.click();
		UiObject logout = new UiObject(new UiSelector().className(
				"android.widget.TextView").text("注销"));
		logout.click();
		UiObject cancle = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/button_left"));
		cancle.click();
		logout.click();
		UiObject comfirm = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/button_right"));
		comfirm.click();

	}

	/**
	 * swipe打开收起侧拉菜单
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void leftPage() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		device.swipe(0, 650, 650, 650, 4);
		sleep(2000);
		device.click(1000, 1800);

	}

	/**
	 * pressback方法退出机锋市场
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void exit() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		device.pressBack();
		device.pressBack();
	}

	/**
	 * 打开关闭通知栏
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void pull() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		device.openNotification();
		sleep(1000);
		device.swipe(520, 1900, 520, 1600, 4);

	}

	/**
	 * 判断屏幕是否打开
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void screenOnOrOFF() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		try {
			Assert.assertEquals(true, device.isScreenOn());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * wakeup()\sleep()方法，通过device打开或者关闭屏幕
	 * 
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public void wakeUp() throws UiObjectNotFoundException, RemoteException {
		UiDevice uidev = getUiDevice();
		// 判断屏幕是否为关闭，如果关闭则使用电源键打开
		Assert.assertEquals(false, uidev.isScreenOn());
		uidev.wakeUp();
		// sleep(4000);
		// uidev.swipe(520, 1900, 520, 1600, 4);
		uidev.drag(520, 1900, 520, 1600, 4);
		// 判断屏幕是否为打开，若打开则使用电源键关闭
		// Assert.assertEquals(true, uidev.isScreenOn());
		// uidev.sleep();
		// 截取当前屏幕并保存在指定位置
		File path = new File("sdcard/filename.png");
		uidev.takeScreenshot(path);
		Assert.assertEquals(true, uidev.takeScreenshot(path));// 判断截屏是否成功，成功返回ok

	}
}

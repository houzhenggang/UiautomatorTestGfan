package com.txh.uitestgfan;

import java.io.File;

import junit.framework.Assert;

import android.os.RemoteException;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.core.UiWatcher;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class OpenExitApp extends UiAutomatorTestCase {
	public static void main(String[] args) {
		String jarName = "OpenExitApp";
		String testClass = "com.txh.uitestgfan.OpenExitApp";
		String testName = "testOpenExitApp";
		String androidId = "1";
		new UiAutomatorHelper(jarName, testClass, testName, androidId);
	}

	/**
	 * 执行方法
	 * 
	 * @param 机锋应用商店uitest
	 * @throws RemoteException
	 */
	public void testOpenExitApp() throws UiObjectNotFoundException {
		openGfan();
		jumpSplash();
		exitGfan();
		// pullNOtyfied();
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
		UiScrollable uiScrollable = new UiScrollable(
				new UiSelector().scrollable(true));
		uiScrollable.setAsHorizontalList();
		uiScrollable.scrollForward();
		uiScrollable.scrollForward();
		uiScrollable.scrollForward();

		// 点击机锋应用商店
		UiObject logo = new UiObject(new UiSelector().index(2).text("机锋应用商店"));
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
	 * 打开关闭通知栏
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void pullNOtyfied() throws UiObjectNotFoundException {
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
	 * pressback方法退出机锋市场
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void exitGfan() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		device.pressBack();
		device.pressBack();
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

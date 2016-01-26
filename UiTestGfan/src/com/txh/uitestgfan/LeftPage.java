package com.txh.uitestgfan;

import com.android.uiautomator.core.UiCollection;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class LeftPage extends UiAutomatorTestCase {
	public static void main(String[] args) {
		String jarName = "LeftPage";
		String testClass = "com.txh.uitestgfan.LeftPage";
		String testName = "testHomeMenu";
		String androidId = "1";
		new UiAutomatorHelper(jarName, testClass, testName, androidId);
	}

	/**
	 * 测试执行入口
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testHomeMenu() throws UiObjectNotFoundException {
		// swipeLeftPage();
		homeMenu();
	}

	/**
	 * swipe打开收起侧拉菜单
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void swipeLeftPage() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		device.swipe(0, 650, 650, 650, 4);
		sleep(2000);
		device.click(1000, 1800);

	}

	/**
	 * 点击menu打开侧拉菜单
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void homeMenu() throws UiObjectNotFoundException {
		UiObject clickMenu = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/home_menu"));
		clickMenu.click();

	}

	/**
	 * 点击加速
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void goSpeed() throws UiObjectNotFoundException {
		UiObject clickSpeed = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/sv_go_speed"));
		clickSpeed.click();
		UiObject speedTex = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_go_speed"));
		String tex = speedTex.getText();
		System.out.println("*****执行清理中  =  " + tex + " *****");
		sleep(10000);
		UiObject afterSpeed = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_go_speed"));
		String text = afterSpeed.getText();
		System.out.println("*****您的手机已经达到最佳状态  =  " + text + " *****");

	}

	/**
	 * 打开设置
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void clickSetup() throws UiObjectNotFoundException {
		UiObject clickSetup = new UiObject(new UiSelector().text("设置"));
		clickSetup.click();
	}

	/**
	 * 设置页面
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public String[] setupPage() throws UiObjectNotFoundException {
		clickSetup();
		UiCollection listview = new UiCollection(
				new UiSelector().resourceId("android:id/list"));
		int count = 0;
		String[] lists = { "a", "a", "a", "a", "a" };
		if (listview.exists()) {
			/*
			 * int framecount = listview.getChildCount(new UiSelector()
			 * .className("android.widget.FrameLayout")); int linearcount =
			 * listview.getChildCount(new UiSelector()
			 * .className("android.widget.LinearLayout")); int relativecount =
			 * listview.getChildCount(new UiSelector()
			 * .className("android.widget.RelativeLayout"));
			 */
			int tvnamecount = listview.getChildCount(new UiSelector()
					.resourceId("com.mappn.gfan:id/tv_name"));
			int summarycount = listview.getChildCount(new UiSelector()
					.resourceId("android:id/summary"));
			int boxcount = listview.getChildCount(new UiSelector()
					.resourceId("android:id/checkbox"));
			count = tvnamecount;
		}
		for (int j = 0; j < 10; j++) {
			UiObject tvname = new UiObject(new UiSelector().resourceId(
					"com.mappn.gfan:id/tv_name").instance(j));
			if (tvname.exists()) {
				lists[j] = tvname.getText();
			} else {
				break;
			}

		}
		String[] change = { String.valueOf(count), lists[0], lists[1],
				lists[2], lists[3], lists[4] };
		return change;

	}

}

package com.txh.uitestgfan;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class LeftPage extends UiAutomatorTestCase{
	public static void main(String[] args) {
		String jarName = "LeftPage";
		String testClass = "com.txh.uitestgfan.LeftPage";
		String testName = "testLeftPage";
		String androidId = "1";
		new UiAutomatorHelper(jarName, testClass, testName, androidId);
	}
	public void testLeftPage() throws UiObjectNotFoundException{
		
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

}

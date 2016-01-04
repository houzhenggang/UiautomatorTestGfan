package com.txh.uitestgfan;

import com.android.uiautomator.core.UiObjectNotFoundException;

public class TestUiautomator extends UiTestGfan{
	public static void mai(String[] args) throws UiObjectNotFoundException{
		UiTestGfan uitest = new UiTestGfan();
		uitest.testOpenGfan();
		uitest.testSetUp();
	}
	

}

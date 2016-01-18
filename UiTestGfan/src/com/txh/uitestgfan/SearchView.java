package com.txh.uitestgfan;

import junit.framework.Assert;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class SearchView  extends UiAutomatorTestCase{
	public static void main(String[] args) {
		String jarName = "SearchView";
		String testClass = "com.txh.uitestgfan.SearchView";
		String testName = "testSearchView";
		String androidId = "1";
		new UiAutomatorHelper(jarName, testClass, testName, androidId);
	}
	public void testSearchView() throws UiObjectNotFoundException{
		//excuteSearchDefaultTex();
		setSearchWord();
		
	}
	/**
	 * 获取默认搜索关键字并判断与预期结果是否一致
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void excuteSearchDefaultTex() throws UiObjectNotFoundException {
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
	public void setSearchWord() throws UiObjectNotFoundException {
		//点击搜索框
		UiObject textV = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_search"));
		textV.click();
		//判断搜索框是否存在-输入QQ搜索
		UiObject textVChild = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/top_search_container"));
		Assert.assertEquals(true, textVChild.exists());
		textVChild.setText("QQ");
		sleep(1000);
		// 获取搜索关键字列表
		UiObject listViewOb = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/baselistview"));
		// Assert.assertEquals(0, listViewOb.getChildCount());
		//点击列表第一个搜索关键字
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


}

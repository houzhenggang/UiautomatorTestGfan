package com.txh.uitestgfan;

import java.util.ArrayList;

import com.android.uiautomator.core.UiCollection;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class ApplicationPage extends UiAutomatorTestCase {
	public static void main(String[] args) {
		String jarName = "ApplicationPage";
		String testClass = "com.txh.uitestgfan.ApplicationPage";
		String testName = "testApplication";
		String androidId = "1";
		new UiAutomatorHelper(jarName, testClass, testName, androidId);
	}

	public void testApplication() throws UiObjectNotFoundException {
		clickApplication();
	}

	/**
	 * 点击应用管理-进入应用管理页面
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void clickApplication() throws UiObjectNotFoundException {
		UiObject application = new UiObject(new UiSelector().text("应用"));
		application.click();
	}

	/**
	 * 通过arraylist数组取出【应用】中的二级标签 并依次点击
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void getTags() throws UiObjectNotFoundException {
		UiCollection coll = new UiCollection(
				new UiSelector().resourceId("com.mappn.gfan:id/page_indicator"));
		ArrayList<String> array = new ArrayList<String>();
		if (coll.exists()) {
			int textviewCount = coll.getChildCount(new UiSelector()
					.className("android.widget.TextView"));
			System.out.println("共有 " + textviewCount + "个二级标签");
			for (int i = 0; i < textviewCount; i++) {
				UiObject textview = new UiObject(new UiSelector().className(
						"android.widget.TextView").instance(i));
				if (textview.exists()) {
					array.add(textview.getText());
					textview.click();
					System.out.println("二级标签为 ： " + textview);
				}
			}
		}
	}

	/**
	 * 通过arraylist获取列表页面的app
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void listPage() throws UiObjectNotFoundException {
		UiScrollable scrollable = new UiScrollable(
				new UiSelector().scrollable(true));
		scrollable.setAsVerticalList();
		UiCollection collection = new UiCollection(
				new UiSelector().resourceId("com.mappn.gfan:id/baselistview"));
		ArrayList<String> arraylist = new ArrayList<String>();
		if (collection.exists()) {
			int framCount = collection
					.getChildCount(new UiSelector()
							.className("android.widget.FrameLayout")
							.childSelector(
									new UiSelector()
											.className(
													"android.widget.RelativeLayout")
											.childSelector(
													new UiSelector()
															.resourceId("com.mappn.gfan:id/common_list_item_name"))));
			for (int i = 0; i < framCount; i++) {
				UiObject textview = new UiObject(
						new UiSelector()
								.className("android.widget.FrameLayout")
								.childSelector(
										new UiSelector()
												.className(
														"android.widget.RelativeLayout")
												.childSelector(
														new UiSelector()
																.resourceId(
																		"com.mappn.gfan:id/common_list_item_name")
																.instance(i))));
				if (textview.exists()) {
					arraylist.add(textview.getText());
				}
			}
		}
		System.out.println("列表中的app为 ： " + arraylist);
	}

	/**
	 * 获取app名称 点击app进入应用详情 返回，点击更新/下载按钮
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void getItemName() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		UiObject appname = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/common_list_item_name"));
		System.out.println("app名称为 ： " + appname.getText());
		appname.click();
		UiObject name = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/app_name"));
		if (appname.getText().equals(name.getText())) {
			System.out.println("appname与详情页面app名称一样： " + appname.getText()
					+ " = " + name.getText());
		}
		device.pressBack();
		UiObject updateBtn = new UiObject(new UiSelector().text("更新"));
		UiObject downloadBtn = new UiObject(new UiSelector().text("下载"));
		if (updateBtn.exists()) {
			updateBtn.click();
		} else {
			downloadBtn.click();
		}
	}

	/**
	 * 排行页面 通过listPage()方法获取列表页面的app名称 通过getItemName()方法点击进入应用详情并返回
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void ranking() throws UiObjectNotFoundException {
		UiObject ranking = new UiObject(new UiSelector().text("排行"));
		ranking.click();
		listPage();
		getItemName();
	}

	/**
	 * 推荐页面 通过listPage()方法获取列表页面的app名称 通过getItemName()方法点击进入应用详情并返回
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void recommendPage() throws UiObjectNotFoundException {
		UiObject recom = new UiObject(new UiSelector().text("推荐"));
		recom.click();
		listPage();
		getItemName();
	}

	/**
	 * 新品页面 通过listPage()方法获取列表页面的app名称 通过getItemName()方法点击进入应用详情并返回
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void newApp() throws UiObjectNotFoundException {
		UiObject newApp = new UiObject(new UiSelector().text("新品"));
		newApp.click();
		listPage();
		getItemName();
	}

	/**
	 * 锋神榜 通过device点击进入应用详情
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void fengShenBang() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		UiObject fsb = new UiObject(new UiSelector().text("锋神榜"));
		fsb.click();
		UiScrollable scrollable = new UiScrollable(
				new UiSelector().scrollable(true));
		scrollable.setAsVerticalList();
		device.click(300, 500);
		device.pressBack();
	}

	/**
	 * 分类 获取分类列表并且挨个点击
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void classify() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		UiObject classify = new UiObject(new UiSelector().text("分类"));
		classify.click();
		UiScrollable scrollable = new UiScrollable(
				new UiSelector().scrollable(true));
		scrollable.setAsVerticalList();
		ArrayList<String> lists = new ArrayList<String>();
		UiCollection coll = new UiCollection(
				new UiSelector().resourceId("com.mappn.gfan:id/baselistview"));
		if (coll.exists()) {
			int linearCount = coll
					.getChildCount(new UiSelector()
							.className("android.widget.LinearLayout")
							.childSelector(
									new UiSelector()
											.className(
													"android.widget.LinearLayout")
											.childSelector(
													new UiSelector()
															.resourceId("com.mappn.gfan:id/tv_category_1"))));
			for (int i = 0; i < linearCount; i++) {
				UiObject textview = new UiObject(
						new UiSelector()
								.resourceId("com.mappn.gfan:id/tv_category_1").instance(i));
				if (textview.exists()) {
					lists.add(textview.getText());
					textview.click();
					getclassifyTag();
					device.pressBack();
				}
			}
		}
		System.out.println("分类列表 ：" + lists);
	}
	/**
	 * 获取分类页面子类页面的二级标签
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void getclassifyTag() throws UiObjectNotFoundException {
		UiObject textview = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_category_1"));
		textview.click();
		getTags();
	}
	/**
	 * 分类页面子类页面-上升最快页面，通过listPage()方法获取列表页面的app名称 通过getItemName()方法点击进入应用详情并返回
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void riseQuick() throws UiObjectNotFoundException {
		UiObject riseQuick = new UiObject(new UiSelector().text("上升最快"));
		riseQuick.click();
		listPage();
		getItemName();
	}
}

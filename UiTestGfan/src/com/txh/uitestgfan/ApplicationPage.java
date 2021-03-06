package com.txh.uitestgfan;

import java.io.File;
import java.text.SimpleDateFormat;
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
		getItemName();
		ranking();
		recommendPage();
		newApp();
		fsBang();
		classify();
		getclassifyTag();
		riseQuick();
	}

	/**
	 * 点击应用管理-进入应用管理页面 ①getTags()获取二级标签
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void clickApplication() throws UiObjectNotFoundException {
		UiObject application = new UiObject(new UiSelector().text("应用"));
		application.click();
		testGetTags();
	}

	/**
	 * 通过arraylist数组取出[应用]中的二级标签 并依次点击 输出到控制台
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testGetTags() throws UiObjectNotFoundException {
		System.out.println("======获取[应用]列表二级标签-并依次点击========");
		UiCollection coll = new UiCollection(
				new UiSelector().resourceId("com.mappn.gfan:id/page_indicator"));
		ArrayList<String> array = new ArrayList<String>();
		if (coll.exists()) {
			int textviewCount = coll.getChildCount(new UiSelector()
					.className("android.widget.TextView"));
			System.out.println("共有 " + textviewCount + "个二级标签");
			for (int i = 0; i < textviewCount; i++) {
				UiObject textview = coll.getChild(new UiSelector().className(
						"android.widget.TextView").instance(i));
				if (textview.exists()) {
					String textName = textview.getText();
					array.add(textName);
					textview.click();
					System.out.println("二级标签为 ： " + textName);
				}
			}
		}
	}

	/**
	 * 通过arraylist获取列表页面的app 输出到控制台
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testPageLists() throws UiObjectNotFoundException {
		System.out.println("======获取列表app========");
		UiScrollable scrollable = new UiScrollable(
				new UiSelector().scrollable(true));
		scrollable.setAsVerticalList();
		UiCollection collection = new UiCollection(
				new UiSelector().resourceId("com.mappn.gfan:id/baselistview"));
		ArrayList<String> arraylist = new ArrayList<String>();
		if (collection.exists()) {
			int framCount = collection.getChildCount(new UiSelector()
					.resourceId("com.mappn.gfan:id/common_list_item_name"));
			for (int i = 0; i < framCount; i++) {
				UiObject textview = new UiObject(new UiSelector().resourceId(
						"com.mappn.gfan:id/common_list_item_name").instance(i));
				if (textview.exists()) {
					arraylist.add(textview.getText());
				}
			}
		}
		System.out.println("列表中的app为 ： " + arraylist);
	}

	/**
	 * getItemName()获取app名称 点击app进入应用详情--返回 如果更新/安装/暂停/继续打开存在==点击
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void getItemName() throws UiObjectNotFoundException {
		System.out.println("======获取appname名称-点击某个app进入详情========");
		UiDevice device = getUiDevice();
		UiObject appname = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/common_list_item_name"));
		String appName = appname.getText();
		sleep(5000);
		System.out.println("app名称为 ： " + appName);
		appname.click();
		sleep(2000);
		UiObject name = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/app_name"));
		try {
			if (appName.equals(name.getText())) {
				System.out.println("appname是否一致： "
						+ appName.equals(name.getText()));
			}
		} catch (Exception e) {
			File path = new File("sdcard/Download/filename.png");
			device.takeScreenshot(path);
			e.printStackTrace();
		}
		device.pressBack();
		UiObject updateBtn = new UiObject(new UiSelector().text("更新"));
		// UiObject downloadBtn = new UiObject(new UiSelector().text("下载"));
		UiObject install = new UiObject(new UiSelector().text("安装"));
		UiObject open = new UiObject(new UiSelector().text("打开"));
		UiObject contin = new UiObject(new UiSelector().text("暂停"));
		UiObject stop = new UiObject(new UiSelector().text("继续"));
		if (updateBtn.exists()) {
			updateBtn.click();
		} else if (install.exists()) {
			install.click();
			UiObject cancleinstall = new UiObject(
					new UiSelector()
							.resourceId("com.android.packageinstaller:id/cancel_button"));
			cancleinstall.click();
		} else if (contin.exists()) {
			contin.click();
		} /*
		 * else if (downloadBtn.exists()) { downloadBtn.click(); }
		 */else if (stop.exists()) {
			stop.click();
		} else if (open.exists()) {
			open.click();
			sleep(1000);
			device.pressBack();
		}
	}

	/**
	 * 排行页面 通过listPage()方法获取列表页面的app名称 通过getItemName()方法点击进入应用详情并返回
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void ranking() throws UiObjectNotFoundException {
		System.out.println("======[排行]========");
		UiObject ranking = new UiObject(new UiSelector().text("排行"));
		ranking.click();
		testPageLists();
		getItemName();
	}

	/**
	 * 推荐页面 通过listPage()方法获取列表页面的app名称 通过getItemName()方法点击进入应用详情并返回
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void recommendPage() throws UiObjectNotFoundException {
		System.out.println("======[推荐]========");
		UiObject recom = new UiObject(new UiSelector().text("推荐"));
		recom.click();
		testPageLists();
		getItemName();
	}

	/**
	 * 新品页面 通过listPage()方法获取列表页面的app名称 通过getItemName()方法点击进入应用详情并返回
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void newApp() throws UiObjectNotFoundException {
		System.out.println("======[新品]========");
		UiObject newApp = new UiObject(new UiSelector().text("新品"));
		newApp.click();
		testPageLists();
		getItemName();
	}

	/**
	 * 锋神榜 通过device点击进入应用详情 获取appname
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void fsBang() throws UiObjectNotFoundException {
		System.out.println("======[锋神榜]========");
		UiDevice device = getUiDevice();
		UiObject fsb = new UiObject(new UiSelector().text("锋神榜"));
		fsb.click();
		UiObject fs = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/common_list_item_name"));
		System.out.println("锋神榜appname：" + fs.getText());
		UiScrollable scrollable = new UiScrollable(
				new UiSelector().scrollable(true));
		scrollable.setAsVerticalList();
		sleep(1000);
		device.click(300, 500);
		sleep(5000);
		device.pressBack();
	}

	/**
	 * 分类 获取分类列表并且挨个点击
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void classify() throws UiObjectNotFoundException {
		System.out.println("======获取[分类]并且挨个点击进入分类页面========");
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
				UiObject textview = new UiObject(new UiSelector().resourceId(
						"com.mappn.gfan:id/tv_category_1").instance(i));
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
		System.out.println("======分类子类-二级标签========");
		testGetTags();
		getItemName();
		riseQuick();
		newApp();
		ranking();
		recommendPage();
	}

	/**
	 * 分类页面子类页面-上升最快页面，通过listPage()方法获取列表页面的app名称 通过getItemName()方法点击进入应用详情并返回
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void riseQuick() throws UiObjectNotFoundException {
		System.out.println("======分类子类-[上升最快]========");
		UiObject riseQuick = new UiObject(new UiSelector().text("上升最快"));
		riseQuick.click();
		testPageLists();
		getItemName();
	}
}

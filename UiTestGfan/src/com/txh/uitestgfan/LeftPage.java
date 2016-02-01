package com.txh.uitestgfan;

import java.util.ArrayList;
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
		String testName = "testLeftPage";
		String androidId = "1";
		new UiAutomatorHelper(jarName, testClass, testName, androidId);
	}

	/**
	 * 测试执行入口
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testLeftPage() throws UiObjectNotFoundException {
		swipeLeftPage();
		homeMenu();
		goSpeed();
		clickSetup();
		setupPage();
		feedBack();
		evaluate();
		checkUpdate();
		community();
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
		device.click(710, 1080);
		sleep(2000);

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
	 * 设置页面 使用ArrayList取出listviews的子控件，挨个点击子控件
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void setupPage() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		UiObject cancleRoot = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/btn_negative"));
		UiCollection listview = new UiCollection(
				new UiSelector().resourceId("android:id/list"));
		ArrayList<String> lists = new ArrayList<String>();
		if (listview.exists()) {
			int tvnamecount = listview.getChildCount(new UiSelector()
					.resourceId("com.mappn.gfan:id/tv_name"));
			System.out.println("设置页面子控件个数 = " + tvnamecount);
			for (int j = 0; j < tvnamecount; j++) {
				UiObject tvname = new UiObject(new UiSelector().resourceId(
						"com.mappn.gfan:id/tv_name").instance(j));
				if (tvname.exists()) {
					lists.add(tvname.getText());// 取出子控件放入集合中
					tvname.click();// 挨个点击子控件
					if (cancleRoot.exists()) {
						cancleRoot.click();
					}
				} else {
					break;
				}
			}
		}
		System.out.println("list页面子控件名称为 ：" + lists);
		device.pressBack();
	}

	/**
	 * 反馈
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void feedBack() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		homeMenu();
		UiObject feddback = new UiObject(new UiSelector().text("反馈"));
		feddback.click();
		UiObject submit = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/bt_submit"));
		submit.click();
		System.out.println("直接点击提交按钮，提示：内容不能为空");
		UiObject contentTex = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/et_content"));
		System.out.println("编辑栏默认文本为： " + contentTex.getText());
		UiObject contactTex = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/et_contact"));
		System.out.println("联系方式栏默认文本为： " + contactTex.getText());
		contentTex.click();
		contentTex.setText("123456789");
		contactTex.click();
		contactTex.setText("1234567@163.com");
		sleep(1000);
		if (submit.exists()) {
			submit.click();
		} else {
			device.pressBack();
			submit.click();
		}

		device.pressBack();
	}

	/**
	 * 评价
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void evaluate() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		homeMenu();
		UiObject evaluate = new UiObject(new UiSelector().text("评价"));
		evaluate.click();
		UiCollection collection = new UiCollection(
				new UiSelector().resourceId("android:id/resolver_grid"));
		ArrayList<String> lists = new ArrayList<String>();
		if (collection.exists()) {
			int textviewCount = collection.getChildCount(new UiSelector()
					.className("android.widget.LinearLayout").childSelector(
							new UiSelector().resourceId("android:id/text1")));
			System.out.println("应用商店个数 = " + textviewCount);
			for (int i = 0; i < textviewCount; i++) {
				UiObject textview = new UiObject(new UiSelector().resourceId(
						"android:id/text1").instance(i));
				if (textview.exists()) {
					lists.add(textview.getText());
					textview.click();
				}
				device.pressBack();
			}
			System.out.println("应用商店名称为 ： " + lists);
		}
		sleep(1000);
		device.pressBack();
	}

	/**
	 * 检查更新
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void checkUpdate() throws UiObjectNotFoundException {
		homeMenu();
		UiObject update = new UiObject(new UiSelector().text("检查更新"));
		update.click();
		sleep(2000);
		System.out.println("检查更新：正在更新，请稍等片刻....已经是最新版本了");
	}

	/**
	 * 论坛
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void community() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		homeMenu();
		UiObject community = new UiObject(new UiSelector().text("论坛"));
		community.click();
		sleep(2000);
		System.out.println("进入论坛啦");
		device.pressBack();
	}

}

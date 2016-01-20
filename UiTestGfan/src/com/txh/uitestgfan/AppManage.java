package com.txh.uitestgfan;

import junit.framework.Assert;

import android.view.animation.DecelerateInterpolator;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class AppManage extends UiAutomatorTestCase {
	UiDevice device = getUiDevice();
	// 应用管理左上角更新数值
	UiObject updateOutNum = new UiObject(
			new UiSelector().resourceId("com.mappn.gfan:id/num"));
	UiObject appManage = new UiObject(
			new UiSelector().resourceId("com.mappn.gfan:id/app_update"));
	UiObject checkIgnore = new UiObject(
			new UiSelector()
					.resourceId("com.mappn.gfan:id/manager_check_ignore_button"));
	String updateOutnumber = null;

	public static void main(String[] args) {
		String jarName = "AppManage";
		String testClass = "com.txh.uitestgfan.AppManage";
		String testName = "testAppManage";
		String androidId = "1";
		new UiAutomatorHelper(jarName, testClass, testName, androidId);
	}

	/**
	 * 测试执行入口
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testAppManage() throws UiObjectNotFoundException {
		appMangement();
		// ignorePage();

	}

	// 设备是否有应用更新：有-升级页面；没有-任务页面
	public void appMangement() throws UiObjectNotFoundException {
		if (updateOutNum.exists()) {
			// 点击应用管理-进入升级页面
			updateOutnumber = updateOutNum.getText();
			updatePage();
		} else {
			// 更新页面没有软件更新-进入任务页面
			appManage.click();
			UiObject taskPage = new UiObject(new UiSelector().text("任务"));
			Assert.assertEquals(true, taskPage.exists());
		}

	}
	//任务页面
	public void taskPage()throws UiObjectNotFoundException{
		
	}

	/**
	 * 一键更新页面
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void updatePage() throws UiObjectNotFoundException {
		// 点击应用管理-进入升级页面
		appManage.click();
		UiObject updatePage = new UiObject(new UiSelector().index(2));
		Assert.assertEquals(true, updatePage.exists());
		System.out.println("******点击应用管理应该进入的是升级页面******");

		UiScrollable scrollable = new UiScrollable(
				new UiSelector().scrollable(true));
		scrollable.setAsVerticalList();

		// 获取升级页面更新数值,如果有升级，则获取升级数量--判断两个更新数量是否一致，并点击更新
		UiObject updateInnerNum = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_upgrade_num"));
		String updateInnernumber = null;
		updateInnernumber = updateInnerNum.getText();
		// 判断两个更新数值是否一致
		if (updateOutnumber == updateInnernumber) {
			System.out.println("******里面的更新数量与外面的一致******");
		}
		// 升级页面按钮的状态
		UiObject buttonStatus = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_operation"));
		String btnupdatePage = buttonStatus.getText();// 升级页面按钮状态：更新
		// 三角形按钮-详情
		UiObject itemDetail = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/iv_item_detail"));
		itemDetail.click();
		// 新版本更新的内容
		UiObject contentDetail = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/tv_option_content"));
		Assert.assertEquals(true, contentDetail.exists());

		// 详情
		// 点击详情-详情页面-点击按钮：更新/下载中/暂停下载/安装
		UiObject detailBtn = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/tv_option_deatil"));
		detailBtn.click();
		// 等待加载出详情页
		UiObject detailPage = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/product_detail_layout"));
		detailPage.waitForExists(100000);
		// 详情页按钮状态:如果为更新/安装，则对比升级页面的状态
		UiObject detailPageUpdateBtn = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/info_action_1_tv"));
		UiObject differentAuthod = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/button_right"));
		if (detailPageUpdateBtn.exists()) {
			String btnStatus = detailPageUpdateBtn.getText();// 详情页面按钮状态
			// 判断升级页面与详情页面按钮状态是否一致
			Assert.assertEquals(btnupdatePage, btnStatus);
			if (btnStatus == "安装") {
				detailPageUpdateBtn.click();
				sleep(10000);
				device.click(270, 1800);
				detailPageUpdateBtn.click();
				device.click(850, 1800);
				sleep(5000);
			}
			detailPageUpdateBtn.click();
			if (differentAuthod.exists()) {// 如果软件作者不是同一个
				differentAuthod.click();
			}
		}
		// 按钮为下载/暂停下载/下载中/打开的状态时
		UiObject progressBar = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/progress"));
		progressBar.click();
		// 返回升级页面
		UiObject backBtn = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/iv_left"));
		backBtn.click();

		// 忽略
		// 查看忽略页面-返回升级页面
		UiObject ignoreBtn = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/tv_option_ignore"));
		ignoreBtn.click();
		checkIgnore.click();
		// 点击取消忽略
		UiObject singleIgnore = new UiObject(new UiSelector().text("取消忽略"));
		singleIgnore.click();

		// 点击全部取消忽略
		UiObject ignoreAll = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/manager_cancel_ignore_all_button"));
		ignoreAll.click();
		// 返回升级页面
		UiObject backBttn = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/iv_left"));
		backBttn.click();

		// 升级页面：点击左侧按钮：更新/安装/继续/等待中
		buttonStatus.click();
		// 点击更新后-获取更新数量
		updateInnernumber = updateInnerNum.getText();
		// 一键更新
		UiObject updateAll = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/manager_down_all_button"));
		updateAll.click();
		// 再次点击右侧button
		buttonStatus.click();

		// 打开
		// 三角形按钮-详情
		// 如果还有软件更新-点击右侧三角形
		if (itemDetail.exists()) {
			itemDetail.click();
			UiObject open = new UiObject(
					new UiSelector()
							.resourceId("com.mappn.gfan:id/tv_option_open"));
			UiObject viewpage = new UiObject(
					new UiSelector().resourceId("com.mappn.gfan:id/viewpage"));
			open.click();
			if (viewpage.exists()) {
				System.out.println("******找不到对应的app，无法打开应用******");
				return;
			}
			sleep(100000);
			device.pressBack();
			device.pressBack();
		}
		// 卸载
		if (itemDetail.exists()) {
			itemDetail.click();
			UiObject uninstall = new UiObject(
					new UiSelector()
							.resourceId("com.mappn.gfan:id/tv_option_uninstall"));
			uninstall.click();
			UiObject cancleuninstall = new UiObject(
					new UiSelector()
							.resourceId("com.android.packageinstaller:id/cancel_button"));
			cancleuninstall.click();
			UiObject confirmuninstall = new UiObject(
					new UiSelector()
							.resourceId("com.android.packageinstaller:id/ok_button"));
			confirmuninstall.clickAndWaitForNewWindow();
			sleep(10000);
			UiObject uninstallMessage = new UiObject(
					new UiSelector()
							.resourceId("com.android.packageinstaller:id/center_text"));
			String mess = uninstallMessage.getText();
			if (mess == "卸载失败") {
				System.out.println("******系统自带应用，不能卸载******");
			} else {
				System.out.println("******卸载成功******");
			}
			confirmuninstall.click();
		}
		// 忽略页面
		// 查看忽略
		ignorePage();
	}

	/**
	 * 忽略页面测试用例
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void ignorePage() throws UiObjectNotFoundException {
		// 查看忽略-获取忽略数量
		UiObject ignoreOutNum = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_ignore_num"));
		String ignoreOutnumber = null;
		if (ignoreOutNum.exists()) {
			ignoreOutnumber = ignoreOutNum.getText();
		}
		// 点击查看忽略-进入查看忽略页面
		UiObject checkIgnore = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/manager_check_ignore_button"));
		checkIgnore.click();
		// 获取忽略的数量
		UiObject ignoreInnerNum = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_upgrade_num"));
		String ignoreInnernumber = "0";
		if (ignoreInnerNum.exists()) {
			ignoreInnernumber = ignoreInnerNum.getText();
		}
		// 判断两个忽略数值是否一致
		if (ignoreOutnumber == ignoreInnernumber) {
			System.out.println("里面的忽略数量与外面的一致");
		}
		UiScrollable scrollable = new UiScrollable(
				new UiSelector().scrollable(true));
		scrollable.setAsVerticalList();
		// 点击取消忽略
		UiObject clickIgnore = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_operation"));
		if (clickIgnore.exists()) {
			clickIgnore.click();
			// 取消忽略后与之前获取的忽略数应该不一致
			String ignorenumber1 = ignoreInnerNum.getText();
			Assert.assertNotSame(ignoreInnernumber, ignorenumber1);
		}
		// 忽略数不存在，获取提示-点击全部取消忽略按钮
		UiObject nodateMessage = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/no_data"));
		String mess = nodateMessage.getText();
		Assert.assertEquals("你很勤快嘛，继续保持哦~", mess);
		UiObject clickIgnoreAll = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/manager_cancel_ignore_all_button"));
		clickIgnoreAll.click();
		// 返回升级页面
		UiObject backBtn = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/iv_left"));
		backBtn.click();
	}

}

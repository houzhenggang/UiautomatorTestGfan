package com.txh.uitestgfan;

import junit.framework.Assert;


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
		// appMangement();
		// installedPage();
		nodataUpdate();
		installPackage();

	}

	/**
	 * 升级-没有应该用可以更新
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void nodataUpdate() throws UiObjectNotFoundException {
		appManage.click();
		UiObject nodataUpdate = new UiObject(new UiSelector().text("升级"));
		nodataUpdate.click();
		UiObject updatePageNoData = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/no_data"));
		if (updatePageNoData.exists()) {
			String nodataTex = updatePageNoData.getText();
			System.out.println("***没有更新数据 ： " + nodataTex + "***");
		}
		UiObject updateSize = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/manager_tv_total_update_size"));
		String updatesize = updateSize.getText();
		System.out.println("***没有更新数据 ： " + updatesize + "***");

	}

	/**
	 * 安装包管理
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void installPackage() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		UiObject instaPackage = new UiObject(new UiSelector().text("安装包管理"));
		instaPackage.click();
		UiObject allSelector = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/all_delete_cb"));
		allSelector.click();
		allSelector.click();
		UiObject oneDeleteCheckBox = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/checkbox_package"));
		oneDeleteCheckBox.click();
		UiObject allDelete = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/all_delete_button"));
		allDelete.click();
		oneDeleteCheckBox.click();
		UiObject oneDelete = new UiObject(new UiSelector().text("删除"));
		oneDelete.click();
		UiObject confirmDelete = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/button_right"));
		confirmDelete.click();

	}
	
	/**
	 * 已安装页面
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void installedPage() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		appManage.click();
		UiObject installed = new UiObject(new UiSelector().text("已安装"));
		installed.click();
		UiObject open = new UiObject(new UiSelector().text("打开"));
		open.click();
		if (open.exists()) {
			System.out.println("***对不起，无法打开此应用***");
		}
		device.pressBack();
		device.pressBack();
		UiObject item = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/iv_item_detail"));
		item.click();
		UiObject itemOpen = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_option_open"));
		itemOpen.click();
		if (itemOpen.exists()) {
			System.out.println("***对不起，无法打开此应用***");
		}
		device.pressBack();
		device.pressBack();
		UiObject details = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/tv_option_deatil"));
		details.clickAndWaitForNewWindow();
		sleep(5000);
		if (details.exists()) {
			System.out.println("***没有找到对应的app***");
		}
		UiObject appName = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/app_name"));
		String name = appName.getText();
		System.out.println("**** app名称为 ：" + name + "****");
		UiObject detailBtn = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/progress"));
		detailBtn.click();
		device.pressBack();
		device.pressBack();
		device.pressBack();
		UiObject uninstall = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/tv_option_uninstall"));
		uninstall.click();
		UiObject cancle = new UiObject(
				new UiSelector()
						.resourceId("com.android.packageinstaller:id/cancel_button"));
		cancle.click();
		uninstall.click();
		UiObject confirm = new UiObject(
				new UiSelector()
						.resourceId("com.android.packageinstaller:id/ok_button"));
		confirm.click();
		UiObject uninstallMessage = new UiObject(
				new UiSelector()
						.resourceId("com.android.packageinstaller:id/center_text"));
		String mess = uninstallMessage.getText();
		if (mess == "卸载失败") {
			System.out.println("******系统自带应用，不能卸载******");
		} else {
			System.out.println("******卸载成功******");
		}
		confirm.click();

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
			taskPage();
		}

	}

	/** 任务列表不为空 */
	public void taskPage() throws UiObjectNotFoundException {
		UiDevice dev = getUiDevice();
		/** 获取任务页面appname，获取详情页面appname，并比较这两个名称是否相同 */
		UiObject appName = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_name"));
		String getName = appName.getText();
		UiObject operation = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/iv_operation"));
		operation.click();
		UiObject details = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/tv_option_detail"));
		details.clickAndWaitForNewWindow();
		UiObject detailsAppName = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/app_name"));
		if (getName.equals(detailsAppName)) {
			System.out.println("任务页面与详情页面appname相同");
		} else {
			System.out.println("任务页面与详情页面appname不相同");
		}
		dev.pressBack();
		/** 如果“开始”按钮存在，判断详情页面按钮状态 */
		UiObject startBtn = new UiObject(new UiSelector().text("开始"));
		if (startBtn.exists()) {
			details.click();
			UiObject operationBtn = new UiObject(
					new UiSelector()
							.resourceId("com.mappn.gfan:id/status_info"));
			String statusInfo = operationBtn.getText();
			Assert.assertEquals("下载暂停，点击恢复", statusInfo);
			System.out.println("***任务列表与详情页的状态对应正确***" + statusInfo);
			dev.pressBack();
		}
		/** 如果“暂停”按钮存在，判断详情页面按钮状态 */
		UiObject continueBtn = new UiObject(new UiSelector().text("暂停"));
		if (continueBtn.exists()) {
			details.click();
			UiObject operationBtn = new UiObject(
					new UiSelector()
							.resourceId("com.mappn.gfan:id/status_info"));
			String statusInfo = operationBtn.getText();
			System.out.println("下载中....: " + statusInfo);
			dev.pressBack();
		}

		/** 如果“安装”按钮 */
		UiObject installBtn = new UiObject(new UiSelector().text("安装"));
		installBtn.clickAndWaitForNewWindow();
		UiObject cancle = new UiObject(
				new UiSelector().resourceId("android:id/button2"));
		if (cancle.exists()) {
			System.out.println("***如果系統已經安裝過此應用-替换应用程序***");
			cancle.click();
			installBtn.click();
			UiObject confirm = new UiObject(
					new UiSelector().resourceId("android:id/button1"));
			Assert.assertEquals(true, confirm.exists());
			confirm.click();
		}
		System.out.println("***安装中***");
		UiObject installBttn = new UiObject(
				new UiSelector()
						.resourceId("com.android.packageinstaller:id/ok_button"));
		Assert.assertEquals(true, installBttn.exists());
		installBttn.click();
		UiObject installing = new UiObject(
				new UiSelector()
						.resourceId("com.android.packageinstaller:id/center_text"));
		String text = installing.getClassName();
		System.out.println("***正在安装....: " + text + "***");
		sleep(5000);
		UiObject finish = new UiObject(
				new UiSelector()
						.resourceId("com.android.packageinstaller:id/finish"));
		finish.click();
		/** 如果“删除”按钮 */
		if (operation.exists()) {
			operation.click();
			UiObject deleteBtn = new UiObject(
					new UiSelector()
							.resourceId("com.mappn.gfan:id/tv_option_delete"));
			deleteBtn.click();
			UiObject checkbox = new UiObject(
					new UiSelector().resourceId("android:id/checkbox"));
			checkbox.click();
			checkbox.click();
			UiObject cancleBtn = new UiObject(
					new UiSelector()
							.resourceId("com.mappn.gfan:id/button_left"));
			cancleBtn.click();
			deleteBtn.click();
			UiObject confirmBtn = new UiObject(
					new UiSelector()
							.resourceId("com.mappn.gfan:id/button_right"));
			confirmBtn.click();

		}
		UiObject nodata = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/no_data"));
		if (nodata.exists()) {
			System.out.println("你怎么忍心让我空着呢，快去精品推荐看看吧~");
		}

	}

	/**
	 * 一键更新页面 详情页按钮-忽略-取消忽略
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void updatePage() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
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
		// 三角形按钮-详情
		UiObject itemDetail = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/iv_item_detail"));
		itemDetail.click();
		// 新版本更新的内容
		UiObject contentDetail = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/tv_option_content"));
		Assert.assertEquals(true, contentDetail.exists());
		/** 点击详情-详情页面-点击按钮：更新/下载中/暂停下载/安装 */
		UiObject detailBtn = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/tv_option_deatil"));
		detailBtn.click();
		/** 等待加载出详情页 */
		UiObject detailPage = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/product_detail_layout"));
		detailPage.waitForExists(100000);
		/** 点击详情页按钮 */
		UiObject progressBar = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/progress"));
		UiObject differentAuthod = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/button_right"));
		progressBar.click();
		if (differentAuthod.exists()) {// 如果软件作者不是同一个
			differentAuthod.click();
		}
		progressBar.click();
		// 返回升级页面
		UiObject backBtn = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/iv_left"));
		backBtn.click();

		/** 查看忽略页面-返回升级页面 */
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
		/** 打开- 三角形按钮-详情 -如果还有软件更新-点击右侧三角形 */
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
			sleep(5000);
			device.pressBack();
			device.pressBack();
		}
		/** 卸载 */
		if (itemDetail.exists()) {
			// itemDetail.click();
			UiObject uninstall = new UiObject(
					new UiSelector()
							.resourceId("com.mappn.gfan:id/tv_option_uninstall"));
			uninstall.click();
			UiObject cancleuninstall = new UiObject(
					new UiSelector()
							.resourceId("com.android.packageinstaller:id/cancel_button"));
			cancleuninstall.click();
			uninstall.click();
			UiObject confirmuninstall = new UiObject(
					new UiSelector()
							.resourceId("com.android.packageinstaller:id/ok_button"));
			confirmuninstall.clickAndWaitForNewWindow();
			sleep(5000);
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
		/** 没有更新数据 */
		UiObject updatePageNoData = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/no_data"));
		if (updatePageNoData.exists()) {
			String nodataTex = updatePageNoData.getText();
			System.out.println("***没有更新数据 ： " + nodataTex + "***");
		}
		/** 忽略页面-查看忽略 */
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
		String ignoreInnernumber = null;
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
		System.out.println("忽略页面没有数据 ： " + mess);
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

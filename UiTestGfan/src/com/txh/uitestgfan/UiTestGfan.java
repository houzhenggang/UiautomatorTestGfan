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

public class UiTestGfan extends UiAutomatorTestCase {
	public static void main(String[] args){
		String jarName = "UiTestGfan";
		String testClass = "com.txh.uitestgfan.UiTestGfan";
		String testName = "testAppGfan";
		String androidId = "1";
		new UiAutomatorHelper(jarName, testClass, testName, androidId);
	}

	/**
	 * 执行方法
	 * 
	 * @param 机锋应用商店uitest
	 * @throws RemoteException
	 */
	public void testAppGfan() throws UiObjectNotFoundException, RemoteException {
		// openGfan();
		// jumpSplash();
		// excuteSearchDefaultTex();
		//clickSearchTextV();
		//setSearchWord();
		// openMyPage();
		// clickHeadImage();
		// succLogin();
		// blankUserNPw();
		 //clickLoginImedetely();
		// blankUser();
		// blankPass();
		// userLogout();
		// leftPage();
		// exitGfan();
		// pullNOtyfied();
		// screenOnOrOFF();
		// wakeUp();
		// getScore();
		// getTicket();
		// bookIn();
		//bookInNotLogin();
		share();
	}

	/**
	 * 打开机锋应用商店
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void openGfan() throws UiObjectNotFoundException {
		getUiDevice().pressHome();
		/*
		 * UiScrollable uiScrollable = new UiScrollable(new
		 * UiSelector().scrollable(true)); 
		 * uiScrollable.setAsHorizontalList();
		 * uiScrollable.scrollForward();
		 */
		// 点击机锋应用商店
		UiObject logo = new UiObject(new UiSelector().index(7));
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
		UiObject textVChild = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/et_search"));
		Assert.assertEquals(true, textVChild.exists());
		textVChild.setText("QQ");
		sleep(1000);
		// 获取搜索关键字列表
		UiObject listViewOb = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/baselistview"));
		// Assert.assertEquals(0, listViewOb.getChildCount());
		UiObject childOb = listViewOb.getChild(new UiSelector().index(1));
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

	/**
	 * 使用未注册过的手机号注册,如何获取手机短信，自动填写验证码 密码大于6位小于15位
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void registerScuss() throws UiObjectNotFoundException {
		// 点击注册机锋账号
		UiObject tvReg = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_register"));
		tvReg.click();
		UiObject userN = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/et_username"));
		userN.setText("18701559910");
		UiObject identifyCode = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/rl_get_verify_code"));
		identifyCode.clickAndWaitForNewWindow(5000);
		UiObject idenCode = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/et_email"));
		idenCode.setText("845692");
		UiObject Pw = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/et_password"));
		Pw.setText("1234567");
		UiObject conPw = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/et_confirm_password"));
		conPw.setText("1234567");
		// 获取键盘并收起键盘
		UiDevice keybox = getUiDevice();
		keybox.pressBack();
		UiObject regeAndLog = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/rl_register"));
		regeAndLog.click();
	}

	/**
	 * 打开个人中心
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void openMyPage() throws UiObjectNotFoundException {
		UiObject my = new UiObject(new UiSelector().text("我的"));
		my.click();

	}

	/**
	 * 点击用户登录头像
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void clickHeadrImage() throws UiObjectNotFoundException {
		UiObject userLogin = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/iv_user_head"));
		userLogin.click();

	}

	/**
	 * 判断账号、密码输入框提示默认提示字符串是否正确
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void getStriOfUserPass() throws UiObjectNotFoundException {
		UiObject userName = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/et_gfan"));
		String edUser = userName.getText();
		Assert.assertEquals("请输入登录账号", edUser);
		UiObject password = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/et_password"));
		String edPass = password.getText();
		Assert.assertEquals("请输入登录密码", edPass);
	}

	/**
	 * 输入正确的用户名、密码点击登录
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void succLogin() throws UiObjectNotFoundException {
		UiObject userName = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/et_gfan"));
		userName.setText("imopan88");
		UiObject password = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/et_password"));
		password.setText("654321");
		UiObject login = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/rl_login"));
		login.clickAndWaitForNewWindow(5000);
		UiObject afterLogin = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_user_name"));
		afterLogin.waitForExists(1000);// 等待登录
		Assert.assertEquals(false, afterLogin.exists()); // 如果成功登录，判断是否进入登录后的页面

	}
	/**
	 * 个人中心是否能滚动
	 * @throws UiObjectNotFoundException
	 */
	public void personCenterScroll() throws UiObjectNotFoundException{
		UiScrollable scro = new UiScrollable(new UiSelector().scrollable(true));
		scro.setAsVerticalList();
	}

	/**
	 * 输入用户名&密码输入空格，点击登录
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void blankUserNPw() throws UiObjectNotFoundException {
		UiObject blankUser = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/et_gfan"));
		blankUser.setText(" ");
		UiObject blankPw = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/et_password"));
		blankPw.setText(" ");
		UiObject clicklogin = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/rl_login"));
		clicklogin.clickAndWaitForNewWindow(2000);
		// 判断是否提示手机号码不能为空
		UiObject waitMesg = new UiObject(
				new UiSelector().resourceId("android:id/message"));
		Assert.assertEquals(true, waitMesg.exists());

	}

	/**
	 * 直接点击登录按钮
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void clickLoginImedetely() throws UiObjectNotFoundException {
		UiObject clicklogin = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/rl_login"));
		clicklogin.click();
		// 判断是否提示手机号（用户名）不能为空
		UiObject waitMesg = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/et_gfan"));
		String text = waitMesg.getText();
		Assert.assertEquals("手机号码不能为空", text);

	}

	/**
	 * 用户名为空，输入密码点击登录
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void blankUser() throws UiObjectNotFoundException {
		UiObject userName = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/et_gfan"));
		userName.setText("imopan88");
		UiObject clicklogin1 = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/rl_login"));
		clicklogin1.click();
		// 判断是否提示手机号（用户名）不能为空
		UiObject waitMesg = new UiObject(
				new UiSelector().resourceId("android:id/message"));
		Assert.assertEquals(true, waitMesg.exists());

	}

	/**
	 * 输入用户名，密码为空点击登录
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void blankPass() throws UiObjectNotFoundException {
		UiObject userName = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/et_gfan"));
		userName.setText("imopan88");
		UiObject clicklogin2 = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/rl_login"));
		clicklogin2.click();
		// 判断是否提示密码不能为空
		UiObject waitMesg = new UiObject(
				new UiSelector().resourceId("android:id/message"));
		Assert.assertEquals(true, waitMesg.exists());
	}

	/**
	 * 获取积分并检查是否正确
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void getScore() throws UiObjectNotFoundException {
		// 判断积分image控件是否存在
		UiObject ima = new UiObject(
				new UiSelector().className("android.widget.ImageView"));
		Assert.assertEquals(true, ima.exists());
		// 判断文字“积分”是否正确
		/*
		 * UiObject scoTex = new UiObject(new
		 * UiSelector().className("android.widget.TextView")); String scoreText
		 * = scoTex.getText(); Assert.assertEquals("积分：", scoreText);
		 */
		// 判断文字积分数值是否正确
		UiObject scoNum = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_credit"));
		String scoNumber = scoNum.getText();
		Assert.assertEquals("1125", scoNumber);

	}
	/**
	 * 用户未登录-跳转到登录页面-登录成功后-签到
	 * @throws UiObjectNotFoundException
	 */
	public void bookInNotLogin() throws UiObjectNotFoundException {
		UiObject clickBookIn = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/title"));
		clickBookIn.click();
		// 判断用户是否已经登录
		UiSelector uisLogin = new UiSelector();
		UiSelector s1 = uisLogin
				.resourceId("com.mappn.gfan:id/ll_input_container");
		if (s1 != null) {
			// 跳转到登录界面，调用succLogin()方法登录
			succLogin();
		} else {
			return;
		}
		// 登录成功后点击检查签到状态
		String bookText = clickBookIn.getText();
		// 如果getText()获取到的不是已签到，则点击签到，签到成功后判断状态是否变化
		if (!bookText.equals("已签到")) {
			clickBookIn.click();
			// 获取点击后的签到状态
			UiObject bookedTe = new UiObject(
					new UiSelector().resourceId("com.mappn.gfan:id/title"));
			String bookedTex = bookedTe.getText();
			Assert.assertEquals("签到", bookedTex);
		} else {
			Assert.assertEquals("签到", bookText);
		}

	}

	/**
	 * 用户已登录-签到
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void bookIn() throws UiObjectNotFoundException {
		// 判断签到签的image是否存在
		UiObject bookIma = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/iv_icon"));
		Assert.assertEquals(true, bookIma.exists());
		// 获取签到状态
		UiObject bookTex = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/title"));
		String bookText = bookTex.getText();
		// 如果getText()获取到的不是已签到，则点击签到，签到成功后判断状态是否变化
		if (!bookText.equals("已签到")) {
			bookTex.click();
			// 获取点击后的签到状态
			UiObject bookedTe = new UiObject(
					new UiSelector().resourceId("com.mappn.gfan:id/title"));
			String bookedTex = bookedTe.getText();
			Assert.assertEquals("签到", bookedTex);
		} else {
			Assert.assertEquals("签到", bookText);
		}

	}

	/**
	 * 获取机锋券并检查是否正确
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void getTicket() throws UiObjectNotFoundException {
		// 判断机锋券image控件是否存在
		UiObject ima = new UiObject(
				new UiSelector().className("android.widget.ImageView"));
		Assert.assertEquals(true, ima.exists());
		// 判断文字“机锋券”是否正确
		/*
		 * UiObject scoTex = new UiObject(new
		 * UiSelector().className("android.widget.TextView")); String scoreText
		 * = scoTex.getText(); Assert.assertEquals("积分：", scoreText);
		 */
		// 判断文字机锋券数值是否正确
		UiObject scoNum = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_quan"));
		String scoNumber = scoNum.getText();
		Assert.assertEquals("4", scoNumber);

	}
	/**
	 * 微信登录
	 * @throws UiObjectNotFoundException
	 */
	public void weixinLogin() throws UiObjectNotFoundException{
		UiObject weixinU = new UiObject(new UiSelector().resourceId("com.tencent.mm:id/axb"));
		weixinU.setText("18519055006");
		
		UiObject weixinP = new UiObject(new UiSelector().resourceId("com.tencent.mm:id/axc"));
		weixinP.setText("imopan2016");
		
		UiObject loginButton = new UiObject(new UiSelector().resourceId("com.tencent.mm:id/axd"));
		loginButton.clickAndWaitForNewWindow();
	}
	/**
	 * 分享
	 * @throws UiObjectNotFoundException
	 */
	public void share() throws UiObjectNotFoundException{
		//判断分享前面的五角星是否存在
		UiObject sobj = new UiObject(new UiSelector().resourceId("com.mappn.gfan:id/iv_icon"));
		Assert.assertEquals(true, sobj.exists());
		//判断分享是否为“分享”
		UiObject sText = new UiObject(new UiSelector().resourceId("com.mappn.gfan:id/title"));
		String sTex = sText.getText();
		Assert.assertEquals("分享", sTex);
		sText.click();

		UiObject layout = new UiObject(new UiSelector().className("android.widget.RelativeLayout"));
		Assert.assertEquals(true,layout.exists());
		
		UiObject shareTex = new UiObject(new UiSelector().className("android.widget.TextView"));
		String shareText = shareTex.getText();
		Assert.assertEquals("分享至", shareText);
		//判断分享弹框是否有关闭按钮
		UiObject shareBack = new UiObject(new UiSelector().resourceId("com.mappn.gfan:id/dialog_back"));
		if(shareBack.exists()){
			return;
		}
		//分享到微信朋友圈
		UiObject weixinFriend = new UiObject(new UiSelector().index(0));
		weixinFriend.clickAndWaitForNewWindow();
		//判断微信是否登录
		UiObject loginPage = new UiObject(new UiSelector().resourceId("android:id/text1"));
		if(loginPage.exists()){
			weixinLogin();
		}else{
			return;
		}
		//检查分享页面是否带有机锋应用商店的分享链接
		UiObject sharePage = new UiObject(new UiSelector().resourceId("com.tencent.mm:id/c25"));
		Assert.assertEquals(true, sharePage.exists());
		//输入分享内容
		UiObject shareEdit = new UiObject(new UiSelector().resourceId("com.tencent.mm:id/c24"));
		shareEdit.setText("share gfanApp");
		//点击发送按钮
		UiObject sendButton = new UiObject(new UiSelector().resourceId("com.tencent.mm:id/es"));
		sendButton.click();
		//分享后返回首页
		UiObject personPage = new UiObject(new UiSelector().resourceId("android:id/content"));
		Assert.assertEquals(true, personPage.exists());
		
		
	}

	/**
	 * 注销-取消-确定,判断注销提示框信息是否正确
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void userLogout() throws UiObjectNotFoundException {
		UiObject userHead = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/iv_user_head"));
		userHead.click();
		UiObject confirm = new UiObject(new UiSelector().className(
				"android.widget.TextView").text("注销"));
		confirm.click();
		// 判断注销提示框信息是否正确
		UiObject tvTitle = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/textview_title"));// 提示框标题
		String titleMeg = tvTitle.getText();
		Assert.assertEquals("温馨提示", titleMeg);
		UiObject tvMesg = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/textview_middle"));// 提示框内容
		String tvMess = tvMesg.getText();
		Assert.assertEquals("你要退出当前账号吗？", tvMess);
		// 点击取消-确定，判断按钮名称是否正确
		UiObject cancle = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/button_left"));
		String cancTex = cancle.getText();
		Assert.assertEquals("取消", cancTex);
		cancle.click();
		confirm.click();
		UiObject comfirm = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/button_right"));
		String confTex = cancle.getText();
		Assert.assertEquals("确定", confTex);
		comfirm.click();

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

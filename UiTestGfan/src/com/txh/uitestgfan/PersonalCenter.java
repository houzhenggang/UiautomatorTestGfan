package com.txh.uitestgfan;

import junit.framework.Assert;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class PersonalCenter extends UiAutomatorTestCase {
	public static void main(String[] args) {
		String jarName = "PersonalCenter";
		String testClass = "com.txh.uitestgfan.PersonalCenter";
		String testName = "testPensonalCenter";
		String androidId = "1";
		new UiAutomatorHelper(jarName, testClass, testName, androidId);
	}

	/**
	 * 测试个人中心执行方法
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testPensonalCenter() throws UiObjectNotFoundException {
		// openMyPage();
		// personCenterScroll();
		// getScore();
		// getTicket();
		// bookIn();
		// bookInNotLogin();
		// succLogin();
		// weixinLogin();
		// share();
		//depositPage();
		weixinPay();
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
	 * 个人中心是否能滚动
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void personCenterScroll() throws UiObjectNotFoundException {
		UiScrollable scro = new UiScrollable(new UiSelector().scrollable(true));
		scro.setAsVerticalList();// 垂直滚动
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
		/*
		 * // 判断文字“积分”是否正确 UiObject scoTex = new UiObject( new
		 * UiSelector().className("android.widget.TextView").childSelector(new
		 * UiSelector().index(1)).text("积分：")); Assert.assertEquals(true,
		 * scoTex.exists());
		 */
		// 判断文字积分数值是否正确
		UiObject scoNum = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_credit"));
		String scoNumber = scoNum.getText();
		Assert.assertEquals("31", scoNumber);

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
		/*
		 * // 判断文字“机锋券”是否正确 UiObject scoTex = new UiObject( new
		 * UiSelector().className("android.widget.TextView")); String scoreText
		 * = scoTex.getText(); Assert.assertEquals("机锋券：", scoreText);
		 */

		// 判断文字机锋券数值是否正确
		UiObject scoNum = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_quan"));
		String scoNumber = scoNum.getText();
		Assert.assertEquals("0", scoNumber);

	}

	/**
	 * 输入正确的用户名、密码点击登录
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void succLogin() throws UiObjectNotFoundException {
		/*
		 * //点击用户登录头像 UiObject userLogin = new UiObject( new
		 * UiSelector().resourceId("com.mappn.gfan:id/iv_user_head"));
		 * userLogin.click();
		 */
		// 输入用户名密码登录
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
		Assert.assertEquals(true, afterLogin.exists()); // 如果成功登录，判断是否进入登录后的页面

	}

	/**
	 * 用户未登录-跳转到登录页面-登录成功后-签到
	 * 
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
	 * 微信登录
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void weixinLogin() throws UiObjectNotFoundException {
		UiObject weixinU = new UiObject(
				new UiSelector().text("QQ号/微信号/Email"));
		weixinU.setText("18519055006");

		UiObject weixinP = new UiObject(
				new UiSelector().resourceId("com.tencent.mm:id/ayd"));
		weixinP.setText("imopan2016");

		UiObject loginButton = new UiObject(
				new UiSelector().resourceId("com.tencent.mm:id/aye"));
		loginButton.clickAndWaitForNewWindow();
	}

	/**
	 * 分享
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void share() throws UiObjectNotFoundException {
		// 判断分享前面的五角星是否存在
		UiObject sobj = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/iv_icon"));
		Assert.assertEquals(true, sobj.exists());
		// 判断分享是否为“分享”
		UiObject sText = new UiObject(new UiSelector().resourceId(
				"com.mappn.gfan:id/title").text("分享"));
		String sTex = sText.getText();
		Assert.assertEquals("分享", sTex);
		sText.click();

		UiObject layout = new UiObject(
				new UiSelector().className("android.widget.RelativeLayout"));
		Assert.assertEquals(true, layout.exists());

		UiObject shareTex = new UiObject(new UiSelector().className(
				"android.widget.TextView").text("分享至"));
		String shareText = shareTex.getText();
		Assert.assertEquals("分享至", shareText);
		// 判断分享弹框是否有关闭按钮，并点击关闭按钮
		UiObject shareBack = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/dialog_back"));
		if (shareBack.exists()) {
			shareBack.click();

		}
		// 再次点击分享
		sText.click();
		// 点击分享到微信朋友圈
		UiObject weixinFriend = new UiObject(new UiSelector().text("微信朋友圈"));
		Assert.assertEquals(true, weixinFriend.exists());
		weixinFriend.clickAndWaitForNewWindow();

		// 检查分享页面是否存在
		UiObject sharePage = new UiObject(
				new UiSelector().resourceId("com.tencent.mm:id/c3g"));
		if (!sharePage.exists()) {// 微信登录
			UiObject loginPage = new UiObject(
					new UiSelector().resourceId("com.tencent.mm:id/dm"));
			Assert.assertEquals(true, loginPage.exists());// 判断微信登录页面是否存在
			weixinLogin();
			return;
		}
		// 输入分享内容
		UiObject shareEdit = new UiObject(
				new UiSelector().resourceId("com.tencent.mm:id/c24"));
		shareEdit.setText("share gfanApp");
		// 点击发送按钮
		UiObject sendButton = new UiObject(
				new UiSelector().resourceId("com.tencent.mm:id/es"));
		sendButton.click();
		// 分享后返回首页
		UiObject personPage = new UiObject(
				new UiSelector().resourceId("android:id/content"));
		Assert.assertEquals(true, personPage.exists());
	}

	/**
	 * 点击充值，未登录则先登录
	 * @throws UiObjectNotFoundException
	 */
	public void depositPage() throws UiObjectNotFoundException {
		UiObject deposit = new UiObject(new UiSelector().text("充值"));
		deposit.click();
		//判断登录界面是否存在
		UiObject depsPage = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_title").text("登录"));				
		if (depsPage.exists()) {// 如果登录页面存在，则登录
			succLogin();
			return;
		}	
	}
	public void aliPay()throws UiObjectNotFoundException{
		//点击“充值”
		UiObject deposit = new UiObject(new UiSelector().text("充值"));
		deposit.click();
		
		UiObject alipay = new UiObject(new UiSelector().text("支付宝充值"));
		alipay.click();
		UiObject alipayPage = new UiObject(new UiSelector().resourceId("com.mappn.gfan:id/tvContent"));
		String alipayTex = alipayPage.getText();
		//Assert.assertEquals("您好，哈喽，您有0机锋券。
		//请您输入充值金额。", alipayTex);
		UiObject edText = new UiObject( new UiSelector().resourceId("com.mappn.gfan:id/et_input"));
		edText.clearTextField();
		edText.setText("1");
		//判断输入1元的时候，右侧显示的机锋券数量是否正确
		//UiObject jifengquan = new UiObject(new UiSelector().resourceId("com.mappn.gfan:id/tv_info"));
		//String quan = jifengquan.getText();
		//Assert.assertEquals("元  价值10机锋券", quan);
		
		UiObject confirmButn = new UiObject(new UiSelector().resourceId("com.mappn.gfan:id/btn_charge_alipay"));
		confirmButn.clickAndWaitForNewWindow(10000);
	}
	public void weixinPay()throws UiObjectNotFoundException{
		//点击“微信支付充值”
		UiObject weixinPay = new UiObject(new UiSelector().text("微信支付充值"));
		weixinPay.click();
		//输入充值金额
		UiObject edText = new UiObject( new UiSelector().resourceId("com.mappn.gfan:id/et_input"));
		edText.clearTextField();
		edText.setText("1");
		
		UiObject confirmButn = new UiObject(new UiSelector().resourceId("com.mappn.gfan:id/btn_charge_alipay"));
		confirmButn.clickAndWaitForNewWindow(10000);
		
		UiObject weixinPage = new UiObject(new UiSelector().text("登录微信"));
		if(weixinPage.exists()){
			weixinLogin();
			return;
		}
		
		
	}

}

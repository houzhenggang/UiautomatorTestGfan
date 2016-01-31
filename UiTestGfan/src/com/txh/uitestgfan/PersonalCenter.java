package com.txh.uitestgfan;

import junit.framework.Assert;

import android.app.backup.BackupManager;
import android.bluetooth.BluetoothClass.Device;
import android.text.style.ClickableSpan;
import android.util.Log;

import com.android.uiautomator.core.UiDevice;
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
		// popShareBox();
		// shareFriendCircle();
		// shareWeiXinFriend();
		// shareWeiBo();
		// depositPage();
		// aliPay();
		// weixinPay();
		// unionPay();
		// tenPay();
		// phonecardPay();
		// exchangeMall();
		// storeBox();
		// fav();
		// modifyUserImage();
		// modifyNickName();
		modifyPassWord();

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
		// 判断文字积分数值是否正确
		UiObject scoNum = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_credit"));
		String scoNumber = scoNum.getText();
		System.out.println("积分为： "+ scoNumber);
		

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

		// 判断文字机锋券数值是否正确
		UiObject scoNum = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_quan"));
		String ticketNum = scoNum.getText();
		System.out.println("机锋券为： "+ ticketNum);

	}

	/**
	 * 输入正确的用户名、密码点击登录
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void succLogin() throws UiObjectNotFoundException {
		// 点击用户登录头像
		UiObject userLogin = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/iv_user_head"));
		userLogin.click();

		// 输入用户名密码登录
		UiObject userName = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/et_gfan"));
		userName.setText("imopan506");
		UiObject password = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/et_password"));
		password.setText("123456");
		UiObject login = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/rl_login"));
		login.clickAndWaitForNewWindow(5000);
		UiObject afterLogin = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_user_name"));
		afterLogin.waitForExists(1000);// 等待登录
		Assert.assertEquals(true, afterLogin.exists()); // 如果成功登录，判断是否进入登录后的页面

	}

	/**
	 * 注销-取消-确定,判断注销提示框信息是否正确
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void Logout() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		UiObject userHead = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/iv_user_head"));
		userHead.click();
		UiObject confirm = new UiObject(new UiSelector().className(
				"android.widget.TextView").text("注销"));
		confirm.click();
		UiObject comfirm = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/button_right"));
		String confTex = comfirm.getText();
		Assert.assertEquals("确定", confTex);
		comfirm.click();
		// device.pressBack();

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
			System.out.println("签到成功");
		}

	}

	/**
	 * 微信登录
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void weixinLogin() throws UiObjectNotFoundException {
		UiObject weixinU = new UiObject(
				new UiSelector().resourceId("com.tencent.mm:id/ayc"));
		weixinU.setText("imopantest@163.com");

		UiObject weixinP = new UiObject(new UiSelector().resourceId(
				"com.tencent.mm:id/ayd").instance(1));
		weixinP.setText("imopan88");

		UiObject loginButton = new UiObject(
				new UiSelector().resourceId("com.tencent.mm:id/aye"));
		loginButton.clickAndWaitForNewWindow();
	}

	/**
	 * 点击“分享”，检查是否弹出分享渠道框
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void popShareBox() throws UiObjectNotFoundException {
		// 判断分享前面的五角星是否存在
		UiObject sobj = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/iv_icon"));
		Assert.assertEquals(true, sobj.exists());
		// 判断分享是否为“分享”
		UiObject shareBtn = new UiObject(new UiSelector().resourceId(
				"com.mappn.gfan:id/title").text("分享"));
		String sTex = shareBtn.getText();
		Assert.assertEquals("分享", sTex);
		shareBtn.click();

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
	}

	/**
	 * 分享到微信朋友圈 如果未登录微信，则先进行登录 输入分享内容并分享到朋友圈
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void shareFriendCircle() throws UiObjectNotFoundException {
		// 点击分享
		UiObject shareBtn = new UiObject(new UiSelector().resourceId(
				"com.mappn.gfan:id/title").text("分享"));
		shareBtn.click();
		// 点击分享到微信朋友圈
		UiObject friendCircle = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/weixin_image"));
		// Assert.assertEquals(true, friendCircle.exists());
		friendCircle.clickAndWaitForNewWindow();

		// 检查分享页面是否存在
		UiObject sharePage = new UiObject(
				new UiSelector().resourceId("com.tencent.mm:id/c2q"));
		if (!sharePage.exists()) {// 微信登录
			UiObject loginPage = new UiObject(
					new UiSelector().resourceId("com.tencent.mm:id/dm"));
			Assert.assertEquals(true, loginPage.exists());// 判断微信登录页面是否存在
			weixinLogin();
		}
		// 输入分享内容
		UiObject shareEdit = new UiObject(
				new UiSelector().resourceId("com.tencent.mm:id/c2q"));
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

	public void shareWeiXinFriend() throws UiObjectNotFoundException {
		// 点击分享
		UiObject shareBtn = new UiObject(new UiSelector().resourceId(
				"com.mappn.gfan:id/title").text("分享"));
		shareBtn.click();
		UiObject weixinFriend = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/weixin_friend_image"));
		weixinFriend.click();
		// 检查选择分享好友页面是否存在
		UiObject shareFriend = new UiObject(
				new UiSelector().resourceId("com.tencent.mm:id/b94"));
		Assert.assertEquals(true, shareFriend.exists());// 判断选择好友页面是否存在
		if (!shareFriend.exists()) {
			UiObject loginPage = new UiObject(
					new UiSelector().resourceId("com.tencent.mm:id/dm"));
			Assert.assertEquals(true, loginPage.exists());// 判断微信登录页面是否存在
			weixinLogin();
		}
		// 选择需要分享到的好友
		UiObject selectorFriend = new UiObject(
				new UiSelector().resourceId("com.tencent.mm:id/bcb"));
		selectorFriend.click();
		// 判断分享自带的信息是否正确
		UiObject shareMessage = new UiObject(
				new UiSelector().resourceId("com.tencent.mm:id/b9b"));
		String shareMess = shareMessage.getText();
		Assert.assertEquals("来用机锋应用商店吧！", shareMess);

		// 判断分享来源
		UiObject fromMessage = new UiObject(
				new UiSelector().resourceId("com.tencent.mm:id/a4m"));
		String fromMess = fromMessage.getText();
		Assert.assertEquals("来自：机锋市场", fromMess);

		// 输入分享信息
		UiObject setShareTex = new UiObject(
				new UiSelector().resourceId("com.tencent.mm:id/a4j"));
		setShareTex.setText("share to weixin friend");

		// 点击分享按钮
		UiObject shareBttn = new UiObject(
				new UiSelector().resourceId("com.tencent.mm:id/b9g"));
		shareBttn.clickAndWaitForNewWindow();

		// 判断分享后的状态
		UiObject sendStatus = new UiObject(
				new UiSelector().resourceId("com.tencent.mm:id/a4l"));
		String statusMess = sendStatus.getText();
		Assert.assertEquals("已发送", statusMess);

		// 检查“返回机锋市场”是否为“返回机锋市场”
		UiObject btnTex = new UiObject(
				new UiSelector().resourceId("com.tencent.mm:id/a4g"));
		String btnText = btnTex.getText();
		Assert.assertEquals("返回机锋市场", btnText);

		// 点击返回机锋市场
		btnTex.click();

		// 判断是否成功返回个人中心页面
		UiObject personCenterPage = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/fragment_container"));
		Assert.assertEquals(true, personCenterPage.exists());
	}

	/**
	 * 分享到新浪微博 无法获取微博账号、密码输入框，只能测试到是否跳转到微博分享页面
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void shareWeiBo() throws UiObjectNotFoundException {
		// 点击分享
		UiObject shareBtn = new UiObject(new UiSelector().resourceId(
				"com.mappn.gfan:id/title").text("分享"));
		shareBtn.click();
		// 点击微博
		UiObject weibo = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/weibo_image"));
		weibo.click();

		// 判断微博页面是否存在
		UiObject weiboView = new UiObject(
				new UiSelector().className("android.webkit.WebView"));
		Assert.assertEquals(true, weiboView.exists());
		// 返回个人中心
		UiDevice device = getUiDevice();
		device.pressBack();
		device.pressBack();

	}

	/**
	 * 点击充值，未登录机锋账号则先登录 获取充值方式并一一点击最后返回个人中心页面
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void depositPage() throws UiObjectNotFoundException {
		UiObject deposit = new UiObject(new UiSelector().text("充值"));
		deposit.click();
		// 判断登录界面是否存在
		UiObject depsPage = new UiObject(new UiSelector().resourceId(
				"com.mappn.gfan:id/tv_title").text("登录"));
		if (depsPage.exists()) {// 如果登录页面存在，则登录
			succLogin();
		}
		// 点击充值
		deposit.click();
		// 检查title是否为“充值”
		UiObject title = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_title"));
		String titleTex = title.getText();
		Assert.assertEquals("充值", titleTex);
		// 获取有多少种充值方式
		UiObject listview = new UiObject(
				new UiSelector().resourceId("android:id/list"));
		int count = listview.getChildCount();
		for (int i = 0; i < count; i++) {
			UiObject childObj = listview.getChild(new UiSelector().clickable(
					true).instance(i));
			/*
			 * //获取支付方式并在控制台打印出来 String strObj = childObj.getText();
			 * System.out.println("payMethod: " +strObj.toString());
			 * System.out.println("============");
			 */
			childObj.click();
			/*
			 * // 点击左上角返回按钮 UiObject backBttn = new UiObject( new
			 * UiSelector().resourceId("com.mappn.gfan:id/iv_left"));
			 * backBttn.click();
			 */

			// 点击手机返回按钮
			UiDevice device = getUiDevice();
			device.pressBack();// 收起输入法键盘
			device.pressBack();// 返回到充值页面
		}

	}

	/**
	 * 支付宝支付-取消支付 手机已安装支付宝app
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void aliPay() throws UiObjectNotFoundException {
		// 点击“充值”
		UiObject deposit = new UiObject(new UiSelector().text("充值"));
		deposit.click();
		// 判断机锋市场登录界面是否存在
		UiObject depsPage = new UiObject(new UiSelector().resourceId(
				"com.mappn.gfan:id/tv_title").text("登录"));
		if (depsPage.exists()) {// 如果登录页面存在，则登录
			succLogin();
		}
		UiObject alipay = new UiObject(new UiSelector().text("支付宝充值"));
		alipay.click();
		// 检查title是否为“支付宝充值”
		UiObject alipTitle = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_title"));
		String alipTex = alipTitle.getText();
		Assert.assertEquals("支付宝充值", alipTex);

		// 检查页面用户名‘机锋券数量是否正确
		/*
		 * UiObject alipayPage = new UiObject( new
		 * UiSelector().resourceId("com.mappn.gfan:id/tvContent")); String
		 * alipayTex = alipayPage.getText();
		 * Assert.assertEquals("您好，哈喽，您有0机锋券。请您输入充值金额。", alipayTex);
		 */
		UiObject edText = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/et_input"));
		edText.clearTextField();
		edText.setText("1");
		// 判断输入1元的时候，右侧显示的机锋券数量是否正确
		// UiObject jifengquan = new UiObject(new
		// UiSelector().resourceId("com.mappn.gfan:id/tv_info"));
		// String quan = jifengquan.getText();
		// Assert.assertEquals("元  价值10机锋券", quan);

		// 点击确认按钮，跳转到支付宝登录页面
		UiObject confirmButn = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/btn_charge_alipay"));
		confirmButn.clickAndWaitForNewWindow(10000);

		// 点击手机返回按钮
		UiDevice devi = getUiDevice();
		devi.pressBack();

		// 取消支付弹出提示信息
		UiObject canclePayMess = new UiObject(
				new UiSelector().resourceId("android:id/message"));
		String mess = canclePayMess.getText();
		Assert.assertEquals("确定要退出吗?", mess);

		// 点击确定
		UiObject confirmBtn = new UiObject(
				new UiSelector().resourceId("android:id/button1"));
		confirmBtn.click();

		// 取消支付后返回支付页面提示信息
		UiObject cancleMessBtn = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/button_right"));
		cancleMessBtn.click();
		// 取消后返回支付宝支付页面
		UiObject payPage = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_title"));
		String payTitle = payPage.getText();
		Assert.assertEquals("支付宝充值", payTitle);

		// 点击手机返回按钮，返回个人中心
		devi.pressBack();
		devi.pressBack();
	}

	/**
	 * 微信支付-取消支付
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void weixinPay() throws UiObjectNotFoundException {
		// 点击“充值”
		UiObject deposit = new UiObject(new UiSelector().text("充值"));
		deposit.click();

		// 判断机锋市场登录界面是否存在
		UiObject depsPage = new UiObject(new UiSelector().resourceId(
				"com.mappn.gfan:id/tv_title").text("登录"));
		if (depsPage.exists()) {// 如果登录页面存在，则登录
			succLogin();
		}
		// 点击“微信支付充值”
		UiObject weixinPay = new UiObject(new UiSelector().text("微信支付充值"));
		weixinPay.click();
		// 检查title是否为“微信支付充值”
		UiObject weixinTitle = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_title"));
		String weixinTex = weixinTitle.getText();
		Assert.assertEquals("微信支付充值", weixinTex);

		// 输入充值金额
		UiObject edText = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/et_input"));
		edText.clearTextField();
		edText.setText("1");
		// 点击确定按钮
		UiObject confirmButn = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/btn_charge_alipay"));
		confirmButn.clickAndWaitForNewWindow(10000);
		// 判断微信登录页面是否存在，存在则登录微信账号
		UiObject weixinPage = new UiObject(new UiSelector().text("登录微信"));
		if (weixinPage.exists()) {
			weixinLogin();
		}
		// 点击手机返回按钮
		UiDevice devi = getUiDevice();
		devi.pressBack();

		// 判断是否弹出取消支付提示
		UiObject canclePayMess = new UiObject(
				new UiSelector().resourceId("android:id/message"));
		String mess = canclePayMess.getText();
		Assert.assertEquals("是否继续完成微信支付?", mess);

		// 取消支付
		UiObject cancleBtn = new UiObject(
				new UiSelector().resourceId("android:id/button2"));
		cancleBtn.click();

		// 取消后返回微信支付页面
		UiObject payPage = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_title"));
		String payTitle = payPage.getText();
		Assert.assertEquals("微信支付充值", payTitle);

		// 点击手机返回按钮，返回个人中心
		devi.pressBack();
		devi.pressBack();
	}

	/**
	 * 银联充值-取消支付
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void unionPay() throws UiObjectNotFoundException {
		// 点击“充值”
		UiObject deposit = new UiObject(new UiSelector().text("充值"));
		deposit.click();
		// 判断机锋市场登录界面是否存在
		UiObject depsPage = new UiObject(new UiSelector().resourceId(
				"com.mappn.gfan:id/tv_title").text("登录"));
		if (depsPage.exists()) {// 如果登录页面存在，则登录
			succLogin();
		}
		// 点击银联充值
		UiObject unionPay = new UiObject(new UiSelector().text("银联充值"));
		unionPay.click();
		// 检查title是否为“银联充值”
		UiObject unionTitle = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_title"));
		String unionTex = unionTitle.getText();
		Assert.assertEquals("银联充值", unionTex);

		// 输入充值金额
		UiObject edText = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/et_input"));
		edText.clearTextField();
		edText.setText("1");
		// 点击确定按钮
		UiObject confirmButn = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/btn_charge_alipay"));
		confirmButn.clickAndWaitForNewWindow(10000);
		// 检查银联支付页面是否存在
		UiObject unionPayPage = new UiObject(
				new UiSelector().resourceId("android:id/content"));
		unionPayPage.waitForExists(100000);// 等待跳转到银联支付页面
		Assert.assertEquals(true, unionPayPage.exists());
		sleep(1000);
		// 点击手机返回按钮
		UiDevice device = getUiDevice();
		// device.pressBack();
		// 左上角×按钮
		UiObject cancle = new UiObject(
				new UiSelector().className("android.widget.ImageView"));
		Assert.assertEquals(true, cancle.exists());
		cancle.click();
		// 弹出是否取消支付提示框
		UiObject canclePayMess = new UiObject(new UiSelector().resourceId(
				"android:id/content").childSelector(new UiSelector().index(1)));
		String mess = canclePayMess.getText();
		Assert.assertEquals("交易尚未完成，确定放弃？", mess);
		// 点击确定
		UiObject confirmBtn = new UiObject(new UiSelector().text("确定"));
		confirmBtn.click();
		// 检查是否返回银联充值页面
		UiObject payPage = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_title"));
		String payTitle = payPage.getText();
		Assert.assertEquals("银联充值", payTitle);
		// 点击手机返回按钮，返回个人中心
		device.pressBack();
		device.pressBack();

	}

	/**
	 * 财付通充值
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void tenPay() throws UiObjectNotFoundException {
		// 点击“充值”
		UiObject deposit = new UiObject(new UiSelector().text("充值"));
		deposit.click();
		// 判断机锋市场登录界面是否存在
		UiObject depsPage = new UiObject(new UiSelector().resourceId(
				"com.mappn.gfan:id/tv_title").text("登录"));
		if (depsPage.exists()) {// 如果登录页面存在，则登录
			succLogin();
		}
		// 点击“财付通充值”
		UiObject tenPay = new UiObject(new UiSelector().text("财付通充值"));
		tenPay.click();
		// 检查title是否为“财付通充值”
		UiObject tenTitle = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_title"));
		String tenTex = tenTitle.getText();
		Assert.assertEquals("财付通充值", tenTex);
		// 输入充值金额
		UiObject edText = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/et_input"));
		edText.clearTextField();
		edText.setText("1");
		// 点击确定按钮
		UiObject confirmButn = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/btn_charge_alipay"));
		confirmButn.clickAndWaitForNewWindow(10000);
		// 判断财付通登录页面是否存在
		UiObject tenPayPage = new UiObject(
				new UiSelector().className("android.widget.FrameLayout"));
		Assert.assertEquals(true, tenPayPage.exists());
		// 点击手机返回按钮
		UiDevice device = getUiDevice();
		device.pressBack();
		// 判断提示信息是否正确
		UiObject popMessage = new UiObject(new UiSelector().resourceId(
				"android:id/parentPanel").childSelector(
				new UiSelector().resourceId("android:id/message")));
		String mess = popMessage.getText();
		Assert.assertEquals("本次支付尚未完成，是否退出财付通手机安全支付服务？", mess);
		// 点击提示确认按钮
		UiObject confirmBtn = new UiObject(
				new UiSelector().resourceId("android:id/button1"));
		confirmBtn.click();
		// 点击左上角返回按钮
		UiObject backBtn = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/iv_left"));
		backBtn.click();
		// 点击左上角返回按钮
		UiObject backBttn = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/iv_left"));
		backBttn.click();
	}

	/**
	 * 电话卡充值
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void phonecardPay() throws UiObjectNotFoundException {
		// 点击“充值”
		UiObject deposit = new UiObject(new UiSelector().text("充值"));
		deposit.click();
		// 判断机锋市场登录界面是否存在
		UiObject depsPage = new UiObject(new UiSelector().resourceId(
				"com.mappn.gfan:id/tv_title").text("登录"));
		if (depsPage.exists()) {// 如果登录页面存在，则登录
			succLogin();
		}
		// 点击电话卡充值
		UiObject phonecardPay = new UiObject(new UiSelector().text("电话卡充值"));
		phonecardPay.click();
		// 跳转到电话卡充值页面，判断title是否正确
		UiObject phonecardTitle = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_title"));
		String phonecardTex = phonecardTitle.getText();
		Assert.assertEquals("电话卡充值", phonecardTex);
		// 点击左上角返回按钮
		UiObject backBtn = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/iv_left"));
		backBtn.click();
		// 点击左上角返回按钮
		UiObject backBttn = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/iv_left"));
		backBttn.click();

	}

	/**
	 * 兑换商城 网页视图，获取不到相关控件
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void exchangeMall() throws UiObjectNotFoundException {
		// 点击兑换商城
		UiObject exMall = new UiObject(new UiSelector().text("兑换商城"));
		exMall.click();
		sleep(1000);
		// 检查积分是否存在
		UiObject scoreMall = new UiObject(new UiSelector().description("1203")
				.clickable(false));
		Assert.assertEquals(true, scoreMall.exists());
		// 点击“兑换记录”
		UiObject exchange = new UiObject(new UiSelector().description(
				"兑换记录 Link").clickable(true));
		exchange.click();
		// 跳转到兑换记录页面
		UiObject exRecord = new UiObject(new UiSelector().text("兑换记录"));
		Assert.assertEquals(true, exRecord.exists());
		exchange.click();
		// 返回个人中心
		UiObject back = new UiObject(
				new UiSelector().className("android.widget.ImageView"));
		back.click();
	}

	/**
	 * 存卡箱
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void storeBox() throws UiObjectNotFoundException {
		// 点击存卡箱
		UiObject storeBox = new UiObject(new UiSelector().text("存卡箱"));
		storeBox.click();
		// 检查title是否为：存卡箱
		UiObject title = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_title"));
		String titleName = title.getText();
		Assert.assertEquals("存卡箱", titleName);
		// 左上角返回按钮
		UiObject backBtn = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/iv_left"));

		// 点击“已领”-点击左上角返回按钮-再次点击“存卡箱”进入存卡箱页面
		UiObject yiling = new UiObject(new UiSelector().resourceId(
				"com.mappn.gfan:id/page_indicator").childSelector(
				new UiSelector().text("已领")));
		yiling.click();
		backBtn.click();
		storeBox.click();
		// 点击“已淘”-点击左上角返回按钮-再次点击“存卡箱”进入存卡箱页面
		UiObject yitao = new UiObject(new UiSelector().resourceId(
				"com.mappn.gfan:id/page_indicator").childSelector(
				new UiSelector().text("已淘")));
		yitao.click();
		backBtn.click();
		storeBox.click();
		// 测试“已领”页面
		// 垂直滚动
		UiScrollable scrollable = new UiScrollable(
				new UiSelector().scrollable(true));
		scrollable.setAsVerticalList();
		// 点击礼包名称，进入礼包详情页
		UiObject presentName = new UiObject(
				new UiSelector().description("《自由之战》 强化石礼包 Link"));
		presentName.click();
		// 获取title的名称是否与presentName的名称一致
		UiObject presentTitle = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_title"));
		String name = presentTitle.getText();
		Assert.assertEquals("《自由之战》 强化石礼包", name);

		// 返回存卡箱页面
		UiObject backBttn = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/iv_left"));
		backBttn.click();

		// 点击礼包图片进入礼包详情页-返回存卡箱
		UiObject imageLink = new UiObject(new UiSelector().description("Link"));
		imageLink.click();
		// 点击“领号”
		UiObject linghao = new UiObject(new UiSelector().description("领号 Link"));
		linghao.click();
		// 判断任务是否在下载状态，如果不在下载中则点击下载按钮
		UiObject downloadProcess = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/progress"));
		if (!downloadProcess.exists()) {
			// 点击下载
			UiObject download = new UiObject(
					new UiSelector()
							.resourceId("com.mappn.gfan:id/info_action_1"));
			download.click();
		}

		// 点击右上角存卡箱image-返回存卡箱页面
		UiObject boxBtn = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/iv_right1"));
		boxBtn.click();

		// 点击“游戏详情”
		UiObject detailsLink = new UiObject(
				new UiSelector().description("游戏详情 Link"));
		detailsLink.clickAndWaitForNewWindow();
		// 获取app详情页app的名称
		UiObject detailsPage = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/app_name"));
		detailsPage.waitForExists(5000);
		String appName = detailsPage.getText();
		Assert.assertEquals("自由之战", appName);
		// 返回存卡箱
		UiDevice device = getUiDevice();
		device.pressBack();
		// 点击复制按钮
		UiObject copyBtn = new UiObject(
				new UiSelector().className("android.widget.Button"));
		copyBtn.click();
		/*
		 * // 点击“全选”-点击“全选” UiObject seleAllBtn = new UiObject(new
		 * UiSelector().description("全选")); seleAllBtn.click();// 选中 UiObject
		 * cancleeAllBtn = new UiObject(new UiSelector().description("全选"));
		 * cancleeAllBtn.click();// 取消选中 // 点击删除按钮 UiObject deleBtn = new
		 * UiObject(new UiSelector().description("删除 Link")); deleBtn.click();
		 */
		// device.click(265, 1270);

		// 点击“已淘”页面-测试已淘页面
		yitao.click();
		sleep(5000);
		// 垂直滚动
		// scrollable.setAsVerticalList();
		// 点击礼包名称，进入礼包详情页
		UiObject presentName1 = new UiObject(
				new UiSelector().description("《酋长萨尔》公测钻石礼包 Link"));
		String name1 = presentName1.getText();
		Assert.assertEquals("", name1);
		presentName1.click();
		// 获取title的名称是否与presentName的名称一致
		UiObject presentTitle1 = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_title"));
		String name2 = presentTitle1.getText();
		Assert.assertEquals("《酋长萨尔》公测钻石礼包 Link", name2);

		// 返回存卡箱页面
		backBttn.click();
		// 点击礼包图片进入礼包详情页-返回存卡箱
		UiObject imageLink1 = new UiObject(new UiSelector().description("Link"));
		imageLink1.click();
		// 点击“淘号 ”
		UiObject taohao = new UiObject(new UiSelector().description("淘号 Link"));
		taohao.click();
		// 判断任务是否在下载状态，如果不在下载中则点击下载按钮
		if (!downloadProcess.exists()) {
			// 点击下载
			UiObject download = new UiObject(
					new UiSelector()
							.resourceId("com.mappn.gfan:id/info_action_1"));
			download.click();
		}
		// 点击右上角存卡箱image-返回存卡箱页面
		boxBtn.click();
		// 点击“游戏详情”
		detailsLink.click();
		// 获取app详情页app的名称
		String appName1 = detailsPage.getText();
		Assert.assertEquals("全民魔兽", appName1);
		// 返回存卡箱
		device.pressBack();
		// 点击复制按钮
		copyBtn.click();
		backBtn.click();
		storeBox.click();
	}

	/**
	 * 收藏夹
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void fav() throws UiObjectNotFoundException {
		// 点击“收藏夹”
		UiObject favBox = new UiObject(new UiSelector().text("收藏夹"));
		favBox.click();
		// 获取收藏app的名称
		UiObject itemName = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_item_name"));
		String name = itemName.getText();
		// 点击右侧下拉三角形
		UiObject itemDetails = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/iv_item_detail"));
		itemDetails.click();
		// 取消收藏
		UiObject cancleFav = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/tv_option_cancel_favorite"));
		cancleFav.click();
		// 判断已经取消的app是否还存在-取消收藏第一个app
		Assert.assertNotSame("烈火遮天", name);
		// 再次点击右侧下拉三角形
		itemDetails.click();
		UiObject details = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/tv_option_deatil"));
		details.click();
		// 点击详情页的取消收藏
		UiObject detailsCancle = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/iv_right2"));
		detailsCancle.click();
		// 返回收藏夹
		UiObject back = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/iv_left"));
		back.click();
		// 判断在详情页中取消的app是否还在收藏夹-取消收藏第二个app
		Assert.assertNotSame("去哪儿旅行-机票酒店.火车票.旅游团购.出租叫车.景点门票", name);

	}

	/**
	 * 修改个人信息-修改头像 拍照 从相册中选择修改
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void modifyUserImage() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		// 点击用户头像
		UiObject headImage = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/iv_user_head"));
		headImage.click();
		// 个人中心-头像
		UiObject userHead = new UiObject(new UiSelector().text("头像"));
		userHead.click();
		// 选择拍照
		UiObject takePhoto = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/btn_take_photo"));
		takePhoto.click();
		// 拍照
		UiObject photoBtn = new UiObject(
				new UiSelector()
						.resourceId("com.lenovo.scgmtk:id/shutter_button_photo"));
		photoBtn.click();
		// 拍照保存
		UiObject btnDone = new UiObject(
				new UiSelector().resourceId("com.lenovo.scgmtk:id/btn_done"));
		btnDone.click();
		// 再次点击头像
		userHead.click();
		// 从相册中选择
		UiObject pickPhoto = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/btn_pick_photo"));
		pickPhoto.click();
		// 点击手机某个坐标选择图片
		device.click(300, 500);
		UiObject image = new UiObject(
				new UiSelector()
						.resourceId("com.lenovo.scgmtk:id/gl_root_view"));
		image.click();
		// 保存图片
		UiObject photoSave = new UiObject(
				new UiSelector().resourceId("com.lenovo.scgmtk:id/save"));
		photoSave.click();

	}

	/**
	 * 修改昵称
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void modifyNickName() throws UiObjectNotFoundException {
		// 点击用户头像
		UiObject headImage = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/iv_user_head"));
		headImage.click();
		// 获取昵称
		UiObject NickName = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_username"));
		String name = NickName.getText();
		// 点击昵称
		UiObject clickNickName = new UiObject(new UiSelector().text("昵称"));
		clickNickName.click();
		// 输入新的昵称
		UiObject inputNickName = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/et_user_name"));
		inputNickName.setText("imopan2016");
		// 点击提交
		UiObject submit = new UiObject(new UiSelector().text("提交"));
		submit.click();
		// 检查修改昵称是否成功：与之前的昵称对比
		Assert.assertNotSame("修改昵称成功", "imopan88", name);

	}

	/**
	 * 修改密码
	 * 注销登录-重新登录，验证修改的密码是否正确
	 * @throws UiObjectNotFoundException
	 */
	public void modifyPassWord() throws UiObjectNotFoundException {
		// 点击用户头像
		UiObject headImage = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/iv_user_head"));
		headImage.click();
		//点击修改密码
		UiObject clickPass = new UiObject(new UiSelector().text("修改密码"));
		clickPass.click();
		//输入旧密码
		UiObject inputOldPass = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/et_old_password"));
		inputOldPass.setText("654321");
		//输入新密码
		UiObject inputNesPass1 = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/et_new_password1"));
		inputNesPass1.setText("987654");
		//确认密码
		UiObject inputNesPass2 = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/et_new_password2"));
		inputNesPass2.setText("987654");
		//点击确认修改
		UiObject confirmModify = new UiObject(new UiSelector().text("确认修改"));
		confirmModify.click();

		//返回个人中心page
		UiObject backBtn = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/iv_left"));
		backBtn.click();
		//注销账户
		Logout();
		//登录，验证修改的密码是否正确
		succLogin();
	}

}

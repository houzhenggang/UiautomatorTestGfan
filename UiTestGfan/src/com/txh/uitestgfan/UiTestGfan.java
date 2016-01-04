package com.txh.uitestgfan;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class UiTestGfan extends UiAutomatorTestCase{

	/**
	 * @param 机锋应用商店uitest
	 */
	private UiObject uiObject;
	private UiSelector uiSelector = new UiSelector();
	//打开机锋应用商店
	public void testOpenGfan() throws UiObjectNotFoundException{
		getUiDevice().pressHome();
		UiScrollable uiScrollable = new UiScrollable(new UiSelector().scrollable(true));
		uiScrollable.setAsHorizontalList();
		uiScrollable.scrollForward();
		uiScrollable.scrollForward();
		
		//点击机锋应用商店
		uiObject = new UiObject(uiSelector.index(13));
		uiObject.clickAndWaitForNewWindow();
		
		//点击跳过闪屏,点击跳过闪屏进入机锋市场首页
		uiObject = new UiObject(uiSelector.resourceId("com.mappn.gfan:id/ib_jump_advert"));
		uiObject.clickAndWaitForNewWindow();
	}
	//侧拉菜单
	public void testSetUp()throws UiObjectNotFoundException{
		//侧拉菜单，点击加速
		uiObject = new UiObject(uiSelector.resourceId("com.mappn.gfan:id/home_menu"));
		uiObject.click();
		uiObject = new UiObject(uiSelector.resourceId("com.mappn.gfan:id/sv_go_speed"));
		uiObject.clickAndWaitForNewWindow();
		//进入设置页面
		uiObject = new UiObject(uiSelector.resourceId("com.mappn.gfan:id/text1"));
		uiObject.clickAndWaitForNewWindow();
				//设置页面点击安装后删除安装包复选框
		uiObject = new UiObject(uiSelector
					.className("android.widget.RelativeLayout").instance(5)
					.childSelector(new UiSelector().resourceId("android:id/checkbox")));
		uiObject.clickBottomRight();
				
				//检查更新
		uiObject = new UiObject(uiSelector.className("android.widget.RelativeLayout")
					.childSelector(uiSelector.resourceId("com.mappn.gfan:id/tv_name")));
		uiObject.clickAndWaitForNewWindow(21000);
				
				
		//检查版本号
	/**	uiSelector = uiSelector.text("??2.1.1");
		uiObject = new UiObject(uiSelector);
				if(uiSelector.equals("??2.1.1")){
					System.out.println("机锋应用商店：版本号为：" + uiSelector);
					
				}*/
		//返回首页
		uiObject = new UiObject(uiSelector.resourceId("com.mappn.gfan:id/iv_left"));
		uiObject.click();
				
	}
	//判断搜索框是否为空，不为空的时候默认搜索关键字是否正确
	public void testSearch() throws UiObjectNotFoundException{
		uiSelector.text(null);
		uiObject = new UiObject(uiSelector);
		if(uiObject != null){
			UiSelector u = uiSelector.text(getName());
		    uiObject = new UiObject(u);
		    System.out.println(uiObject);
		}
		uiObject.clearTextField();//点击搜索框
		uiObject = new UiObject(uiSelector.resourceId("com.mappn.gfan:id/home_search_back"));
		uiObject.click();//点击搜索框左侧返回箭头
		uiObject.clearTextField();//再次点击搜索框
		uiObject.setText("机锋应用商店");//搜索框输入“机锋应用商店”
		uiObject = new UiObject(uiSelector.resourceId("com.mappn.gfan:id/home_search_button"));
		uiObject.click();//点击搜索		
		
	}
	//我的-注册
	public void signUp() throws UiObjectNotFoundException{
		//进入个人中心
		uiObject = new UiObject(uiSelector.className("android.widget.TextView"));
		uiObject.clickAndWaitForNewWindow();
		//进入登录页面
		uiObject = new UiObject(uiSelector.resourceId("com.mappn.gfan:id/iv_user_head"));
		uiObject.click();
		//点击返回键收起键盘
		UiScrollable s = new UiScrollable(uiSelector.scrollable(true));
		s.scrollBackward();
		//注册机锋账号
		uiObject = new UiObject(uiSelector.resourceId("com.mappn.gfan:id/tv_register"));
		uiObject.clickAndWaitForNewWindow();
		uiObject = new UiObject(uiSelector.resourceId("com.mappn.gfan:id/et_username"));
		uiObject.setText("18010015719");
		uiObject = new UiObject(uiSelector.className("android.widget.TextView").enabled(true));
		uiObject.click();
		
		
		
	}
	//登录
	public void signIn() throws UiObjectNotFoundException{
		
	}
	//退出账号
	public void signOut() throws UiObjectNotFoundException{
		
	}
	//退出机锋应用商店
	public void exitGfan() throws UiObjectNotFoundException{
		UiScrollable uiScrollable = new UiScrollable(uiSelector.scrollable(true));
		uiScrollable.scrollBackward();
		uiScrollable.scrollBackward();
	}
	

}

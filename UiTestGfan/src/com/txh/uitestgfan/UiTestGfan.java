package com.txh.uitestgfan;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class UiTestGfan extends UiAutomatorTestCase{

	/**
	 * @param ����Ӧ���̵�uitest
	 */
	private UiObject uiObject;
	private UiSelector uiSelector = new UiSelector();
	//�򿪻���Ӧ���̵�
	public void testOpenGfan() throws UiObjectNotFoundException{
		getUiDevice().pressHome();
		UiScrollable uiScrollable = new UiScrollable(new UiSelector().scrollable(true));
		uiScrollable.setAsHorizontalList();
		uiScrollable.scrollForward();
		uiScrollable.scrollForward();
		
		//�������Ӧ���̵�
		uiObject = new UiObject(uiSelector.index(13));
		uiObject.clickAndWaitForNewWindow();
		
		//�����������,�������������������г���ҳ
		uiObject = new UiObject(uiSelector.resourceId("com.mappn.gfan:id/ib_jump_advert"));
		uiObject.clickAndWaitForNewWindow();
	}
	//�����˵�
	public void testSetUp()throws UiObjectNotFoundException{
		//�����˵����������
		uiObject = new UiObject(uiSelector.resourceId("com.mappn.gfan:id/home_menu"));
		uiObject.click();
		uiObject = new UiObject(uiSelector.resourceId("com.mappn.gfan:id/sv_go_speed"));
		uiObject.clickAndWaitForNewWindow();
		//��������ҳ��
		uiObject = new UiObject(uiSelector.resourceId("com.mappn.gfan:id/text1"));
		uiObject.clickAndWaitForNewWindow();
				//����ҳ������װ��ɾ����װ����ѡ��
		uiObject = new UiObject(uiSelector
					.className("android.widget.RelativeLayout").instance(5)
					.childSelector(new UiSelector().resourceId("android:id/checkbox")));
		uiObject.clickBottomRight();
				
				//������
		uiObject = new UiObject(uiSelector.className("android.widget.RelativeLayout")
					.childSelector(uiSelector.resourceId("com.mappn.gfan:id/tv_name")));
		uiObject.clickAndWaitForNewWindow(21000);
				
				
		//���汾��
	/**	uiSelector = uiSelector.text("??2.1.1");
		uiObject = new UiObject(uiSelector);
				if(uiSelector.equals("??2.1.1")){
					System.out.println("����Ӧ���̵꣺�汾��Ϊ��" + uiSelector);
					
				}*/
		//������ҳ
		uiObject = new UiObject(uiSelector.resourceId("com.mappn.gfan:id/iv_left"));
		uiObject.click();
				
	}
	//�ж��������Ƿ�Ϊ�գ���Ϊ�յ�ʱ��Ĭ�������ؼ����Ƿ���ȷ
	public void testSearch() throws UiObjectNotFoundException{
		uiSelector.text(null);
		uiObject = new UiObject(uiSelector);
		if(uiObject != null){
			UiSelector u = uiSelector.text(getName());
		    uiObject = new UiObject(u);
		    System.out.println(uiObject);
		}
		uiObject.clearTextField();//���������
		uiObject = new UiObject(uiSelector.resourceId("com.mappn.gfan:id/home_search_back"));
		uiObject.click();//�����������෵�ؼ�ͷ
		uiObject.clearTextField();//�ٴε��������
		uiObject.setText("����Ӧ���̵�");//���������롰����Ӧ���̵ꡱ
		uiObject = new UiObject(uiSelector.resourceId("com.mappn.gfan:id/home_search_button"));
		uiObject.click();//�������		
		
	}
	//�ҵ�-ע��
	public void signUp() throws UiObjectNotFoundException{
		//�����������
		uiObject = new UiObject(uiSelector.className("android.widget.TextView"));
		uiObject.clickAndWaitForNewWindow();
		//�����¼ҳ��
		uiObject = new UiObject(uiSelector.resourceId("com.mappn.gfan:id/iv_user_head"));
		uiObject.click();
		//������ؼ��������
		UiScrollable s = new UiScrollable(uiSelector.scrollable(true));
		s.scrollBackward();
		//ע������˺�
		uiObject = new UiObject(uiSelector.resourceId("com.mappn.gfan:id/tv_register"));
		uiObject.clickAndWaitForNewWindow();
		uiObject = new UiObject(uiSelector.resourceId("com.mappn.gfan:id/et_username"));
		uiObject.setText("18010015719");
		uiObject = new UiObject(uiSelector.className("android.widget.TextView").enabled(true));
		uiObject.click();
		
		
		
	}
	//��¼
	public void signIn() throws UiObjectNotFoundException{
		
	}
	//�˳��˺�
	public void signOut() throws UiObjectNotFoundException{
		
	}
	//�˳�����Ӧ���̵�
	public void exitGfan() throws UiObjectNotFoundException{
		UiScrollable uiScrollable = new UiScrollable(uiSelector.scrollable(true));
		uiScrollable.scrollBackward();
		uiScrollable.scrollBackward();
	}
	

}

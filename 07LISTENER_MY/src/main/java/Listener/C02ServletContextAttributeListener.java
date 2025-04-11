package Listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class C02ServletContextAttributeListener implements ServletContextAttributeListener{

	//	컨텍스트의 속성 추가 감지
	@Override
	public void attributeAdded(ServletContextAttributeEvent event) {
		System.out.println("[LISTENER] C02ServletContextAttributeListener add()..");
	}

	//	컨텍스트의 속성 수정 감지
	@Override
	public void attributeRemoved(ServletContextAttributeEvent event) {
		System.out.println("[LISTENER] C02ServletContextAttributeListener remove()..");

	}

	//	컨텍스트의 속성 삭제 감지
	@Override
	public void attributeReplaced(ServletContextAttributeEvent event) {
		System.out.println("[LISTENER] C02ServletContextAttributeListener replace()..");

	}	



}

package Listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

//@WebListener
public class C01ServletContextListener implements ServletContextListener{	

	//웹 애플리케이션 시작 감지
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("[LISTENER] C01ServletContextListener..start...");
	}
	
	//웹 애플리케이션 종료 감지
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("[LISTENER] C01ServletContextListener..end...");
	}

}

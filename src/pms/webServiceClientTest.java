package pms;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pms.inface.WebServiceInterface;

public class webServiceClientTest {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		WebServiceInterface webServiceInterface = context.getBean(WebServiceInterface.class);
		String result = webServiceInterface.sayBye("honey,");
		System.out.println(result);
	}

}

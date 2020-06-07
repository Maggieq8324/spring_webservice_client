package pms;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import pms.inface.WebServiceInterface;
import pms.support.AddHeaderInterceptor;

public class webServiceClientMain {
	public static void main(String[] args) {
		JaxWsProxyFactoryBean svr = new JaxWsProxyFactoryBean();
		svr.setServiceClass(WebServiceInterface.class);
		svr.setAddress("http://localhost:8081/spring_webservice_server/webservice/webServiceInterface?wsdl");
		WebServiceInterface webServiceInterface = (WebServiceInterface) svr.create();
		
		// jaxws API 转到 cxf API 添加日志拦截器
		org.apache.cxf.endpoint.Client client = org.apache.cxf.frontend.ClientProxy
				.getClient(webServiceInterface);
		org.apache.cxf.endpoint.Endpoint cxfEndpoint = client.getEndpoint();
		//添加自定义的拦截器
		cxfEndpoint.getOutInterceptors().add(new AddHeaderInterceptor("dc1fd00e3eeeb940ff46f457bf97d66ba7fcc36e0b20802383de142860e76ae6", "c2de40449a2019db9936381fa9810c22c8548a8635ed2b7fb3c7ec362e37429d"));
		
		System.out.println(webServiceInterface.sayBye("honey,"));
	}
}

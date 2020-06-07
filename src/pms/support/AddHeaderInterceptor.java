package pms.support;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.DOMUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class AddHeaderInterceptor extends AbstractPhaseInterceptor<SoapMessage>{ 
    
    private String userName; 
    private String password; 
       
    public AddHeaderInterceptor(String userName, String password) { 
        super(Phase.PREPARE_SEND); 
        this.userName = userName; 
        this.password = password;  
    } 
   
    @Override 
    public void handleMessage(SoapMessage msg) throws Fault { 
           
//        List<Header> headers = msg.getHeaders(); 
//           
//        //创建Document对象 
//        Document document = DOMUtils.createDocument(); 
//        Element element = document.createElement("security");  
//           
//        //配置服务器端Head信息的用户密码 
//        Element userNameElement= document.createElement("username");  
//        userNameElement.setTextContent(userName); 
//        Element passwordElement = document.createElement("password");  
//        passwordElement.setTextContent(password); 
//           
//        element.appendChild(userNameElement); 
//        element.appendChild(passwordElement); 
//        headers.add(new Header(new QName("security"), element)); 
        
    	   System.out.println("拦截...");
        
           /**
            * 生成的XML文档
            * <authHeader>
            *      <userName>admin</userName>
            *      <password>P@ssw0rd</password>
            * </authHeader>
            */ 
        
        	// SoapHeader部分待添加的节点
     		QName qName = new QName("security");
     		Document doc = DOMUtils.createDocument();

     		Element pwdEl = doc.createElement("password");
     		pwdEl.setTextContent(password);
     		Element userEl = doc.createElement("username");
     		userEl.setTextContent(userName);
     		Element root = doc.createElement("security");
     		root.appendChild(userEl);
     		root.appendChild(pwdEl);
     		// 创建SoapHeader内容
     		SoapHeader header = new SoapHeader(qName, root);
     		// 添加SoapHeader内容
     		List<Header> headers = msg.getHeaders();
     		headers.add(header); 
       
    } 
}

package cn.cd.caoyeung.webapp.ws.server.interceptor;


import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

public class HelloInterceptor extends AbstractPhaseInterceptor<Message> {  
	  
    public HelloInterceptor(String phase) {  
        super(phase);  
    }  
      
    public HelloInterceptor() {  
        super(Phase.PROTOCOL);  
    }  
  
    public void handleMessage(Message message) throws Fault {  
        System.out.println("*********HelloInterceptor******");  
    }  
  
}  
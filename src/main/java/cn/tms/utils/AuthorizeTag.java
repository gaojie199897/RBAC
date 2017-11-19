package cn.tms.utils;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.tagext.BodyTagSupport;
import cn.tms.dao.IPrivilegeDao;
import cn.tms.entity.Privilege;
import cn.tms.entity.UserInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


public class AuthorizeTag  extends BodyTagSupport {


	   private IPrivilegeDao iPrivilegeDao;

	    private String URL;  
	  
	    public String getURL() {  
	        return URL;  
	    }  
	      
	    public void setURL(String uRL) {  
	        URL = uRL;  
	    }  
	    @Override  
	    public int doStartTag() { 
	    	  
	        // 如果URL不空就显示URL，否则就不显  
	        if (null != URL) {
				getUserDao();
	        	HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
	        	UserInfo info=(UserInfo)request.getSession().getAttribute("userinfo");
				List<Privilege> list = iPrivilegeDao.getAllPrivilegesByEmpId(info.getUserid()) ;
	        	for (Privilege item : list) {
					if(item.getUrl().equals(URL)){
						  return EVAL_BODY_INCLUDE;  
					}
				}
	        }  
	        return this.SKIP_BODY;  
	    }
		public void getUserDao() {
			  WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext());
			  iPrivilegeDao = (IPrivilegeDao)applicationContext.getBean("iPrivilegeDao");
		}



}

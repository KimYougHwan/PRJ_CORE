/**
 * 
 */
package co.kr.kyhgh.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *	
 * @Author : yhkim
 * @Date   : 2017. 5. 22.
 */
public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		HttpSession session = request.getSession(false);
		
		if(session == null){
			RequestDispatcher dispatcher =request.getRequestDispatcher("/");
			dispatcher.forward(request, response);
			return false;
		}
		
		String userId = (String)session.getAttribute("userId");
		if(StringUtils.isEmpty(userId)){
			RequestDispatcher dispatcher =request.getRequestDispatcher("/");
			dispatcher.forward(request, response);
			return false;
		}
		
		return super.preHandle(request, response, handler);
	}

}

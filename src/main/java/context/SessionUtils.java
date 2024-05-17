package context;

import javax.servlet.http.HttpServletRequest;

public class SessionUtils {
	// example:
//                SessionUtil.getInstance().putValue(request,"USERMODEL",userModel);//save session user login
	private static SessionUtils sessionUtil = null;

	public static SessionUtils getInstance() {
		if (sessionUtil == null) {
			sessionUtil = new SessionUtils();
		}
		return sessionUtil;
	}

	public void putValue(HttpServletRequest request, String key, Object value) {
		request.getSession().setAttribute(key, value);
		// add session with key is user and value is user name
	}

	public Object getValue(HttpServletRequest request, String key) {
		// get value of key
		return request.getSession().getAttribute(key);
	}

	public void removeValue(HttpServletRequest request, String key) {
		// remove session after logout
		request.getSession().removeAttribute(key);
	}

}

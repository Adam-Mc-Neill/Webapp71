package views;

import java.util.HashMap;

import org.h2.mvstore.MVMap;

import model.Account;
import storage.DatabaseInterface;

public class Navbar {
	
	public static String getNav(HashMap<String, String> cookies, DatabaseInterface db) {
		String username = cookies.get("username"); 
		String password = cookies.get("password");
		
		if(username!=null)
		{
			MVMap<String, Account> u1 = db.s.openMap("Account");
			Account u = u1.get(username);
			if((u==null)||(!u.password.contentEquals(password)))
			{
				String clearCookies = ""
						+ "<div onload=\""
						+ "document.cookie='username=;expires=' + new Date(0).toGMTString();\n"
						+ "document.cookie='password=;expires=' + new Date(0).toGMTString();\n"
						+ "location.reload();"
						+ " \" ></div>";
				
				return clearCookies;
			}
			else {
				return getLoggedInNav(cookies.get("username"));
			}
		}
		
		return getNormalNav();
		
	}
	
	public static String getNormalNav() {
		return "<nav class=\"navbar navbar-expand-md navbar-dark bg-blue fixed-top text-upper\">\r\n" + 
				"      <a class=\"navbar-brand nav-brand\" href=\"/browseview\">A.71</a>\r\n" + 
				"      <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarsExampleDefault\" aria-controls=\"navbarsExampleDefault\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\r\n" + 
				"      <span class=\"navbar-toggler-icon\"></span>\r\n" + 
				"      </button>\r\n" + 
				"\r\n" + 
				"      <div class=\"collapse navbar-collapse\" id=\"navbarsExampleDefault\">\r\n" + 
				"        <ul class=\"navbar-nav \" style=\"width: 100%; \">\r\n" + 
				"          <li class=\"nav-item\">\r\n" + 
				"            <a class=\"nav-link\" href=\"/browseview\">Games</a>\r\n" + 
				"          </li>\r\n" + 
				"          <li class=\"nav-item\">\r\n" + 
				"            <a class=\"nav-link\" href=\"/publish\">Publish</a>\r\n" + 
				"          </li>\r\n" + 
				"          <li class=\"nav-item dropdown\">\r\n" + 
				"            <a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"navbarDropdown\" role=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\r\n" + 
				"              Help\r\n" + 
				"            </a>\r\n" + 
				"            <div class=\"dropdown-menu\" aria-labelledby=\"navbarDropdown\">\r\n" + 
				"              <a class=\"dropdown-item\" href=\"/help\">How-To</a>\r\n" + 
				"              <a class=\"dropdown-item\" href=\"/reportissue\">Report Issue</a>\r\n" + 
				"            </div>\r\n" + 
				"          </li>\r\n" + 
				
				"          <li class=\"nav-item dropdown\">\r\n" + 
				"            <a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"navbarDropdown\" role=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\r\n" + 
				"              Forum\r\n" + 
				"            </a>\r\n" + 
				"            <div class=\"dropdown-menu\" aria-labelledby=\"navbarDropdown\">\r\n" + 
				"              <a class=\"dropdown-item\" href=\"/submitQuestion\">Make a Post</a>\r\n" + 
				"              <a class=\"dropdown-item\" href=\"/faqPage\">Browse Forum</a>\r\n" + 
				"            </div>\r\n" + 
				"          </li>\r\n" + 
				
				"          <li class=\"nav-item\" style=\"margin-left: auto; padding-right: 15px; \" >\r\n" + 
				"            <a class=\"nav-link\" href=\"/login.html\">Login / Signup</a>\r\n" + 
				"          </li>\r\n" + 
				"        </ul>\r\n" + 
				"      </div>\r\n" + 
				"    </nav>";
	}
	
	public static String getLoggedInNav(String username) {
		return "<nav class=\"navbar navbar-expand-md navbar-dark bg-blue fixed-top text-upper\">\r\n" + 
				"      <a class=\"navbar-brand nav-brand\" href=\"/browseview\">A.71</a>\r\n" + 
				"      <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarsExampleDefault\" aria-controls=\"navbarsExampleDefault\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\r\n" + 
				"      <span class=\"navbar-toggler-icon\"></span>\r\n" + 
				"      </button>\r\n" + 
				"\r\n" + 
				"      <div class=\"collapse navbar-collapse\" id=\"navbarsExampleDefault\">\r\n" + 
				"        <ul class=\"navbar-nav \" style=\"width: 100%; \">\r\n" + 
				"          <li class=\"nav-item\">\r\n" + 
				"            <a class=\"nav-link\" href=\"/browseview\">Games</a>\r\n" + 
				"          </li>\r\n" + 
				"          <li class=\"nav-item\">\r\n" + 
				"            <a class=\"nav-link\" href=\"/publish\">Publish</a>\r\n" + 
				"          </li>\r\n" + 
				"          <li class=\"nav-item dropdown\">\r\n" + 
				"            <a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"navbarDropdown\" role=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\r\n" + 
				"              Help\r\n" + 
				"            </a>\r\n" + 
				"            <div class=\"dropdown-menu\" aria-labelledby=\"navbarDropdown\">\r\n" + 
				"              <a class=\"dropdown-item\" href=\"/help\">How-To</a>\r\n" + 
				"              <a class=\"dropdown-item\" href=\"/reportissue\">Report Issue</a>\r\n" + 
				"            </div>\r\n" + 
				"          </li>\r\n" + 
				
				"          <li class=\"nav-item dropdown\">\r\n" + 
				"            <a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"navbarDropdown\" role=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\r\n" + 
				"              Forum\r\n" + 
				"            </a>\r\n" + 
				"            <div class=\"dropdown-menu\" aria-labelledby=\"navbarDropdown\">\r\n" + 
				"              <a class=\"dropdown-item\" href=\"/submitQuestion\">Make a Post</a>\r\n" + 
				"              <a class=\"dropdown-item\" href=\"/forum\">Browse Forum</a>\r\n" + 
				"            </div>\r\n" + 
				"          </li>\r\n" + 
				"          <li class=\"nav-item\" style=\"margin-left: auto; padding-right: 15px; \" >\r\n" + 
				"			 <a class=\"nav-link\" style=\"display:inline-block;\" >"+username+"</a>\r\n" +	
				"            <a class=\"nav-link\" style=\"display:inline-block; cursor:pointer;\" onclick=\" "
				+ " document.cookie='username=;expires=' + new Date(0).toGMTString() + ';path=/';\n"
				+ "document.cookie='password=;expires=' + new Date(0).toGMTString() + ';path=/';\n"
				+ "location.reload();   "
				+ "\">| Logout</a>\r\n" + 
				"          </li>\r\n" + 
				"        </ul>\r\n" + 
				"      </div>\r\n" + 
				"    </nav>";
	}
	
}

package views;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.h2.mvstore.MVMap;

import model.Account;
import model.User;
import storage.DatabaseInterface;
import storage.FileStoreInterface;
import web.WebRequest;
import web.WebResponse;

public class LoginPageView extends DynamicWebPage {

	public LoginPageView(DatabaseInterface db, FileStoreInterface fs) {
		super(db, fs);
	}
	
	private String loadPage(String filePath) {
		String everything = "";
		
		try {
			File fileDir = new File(filePath);
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF8"));
			String str;

			while ((str = in.readLine()) != null) {
			    everything += str + "\n";
			}

	        in.close();

		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (Exception e){
			System.out.println(e.getMessage());
	    }

		return everything;
	}
	
	public boolean process(WebRequest toProcess)
	{
		if(toProcess.path.equalsIgnoreCase("login.html"))
		{
			String code = loadPage("./httpdocs/login.html");
			
			code = code.replace("{{nav}}", Navbar.getNav(toProcess.cookies, db));
				
			toProcess.r = new WebResponse(WebResponse.HTTP_OK, WebResponse.MIME_HTML, code);
			return true;			
		}
		
		
		
		
		if(toProcess.path.equalsIgnoreCase("loginsubmit.html"))
		{	
			String username = toProcess.params.get("username");
			String password = toProcess.params.get("password");
			
			MVMap<String, Account> users2 = db.s.openMap("Account");
			
			if(!users2.containsKey(username) || username.equals(null) || username.equals(""))
			{	
				
				String code = "alertbox.show('Invalid Login Details.'); \n"
						+ "document.getElementById(\"createUsername2\").value = ''; \n"
						+ "document.getElementById(\"createPassword2\").value = ''; \n";
				toProcess.r = new WebResponse( WebResponse.HTTP_OK, WebResponse.MIME_HTML, code );
				return true;	
			}
			
			Account getaccount = users2.get(username);
			
			if(!getaccount.password.contentEquals(password))
			{ 				
				String code = "alertbox.show('Invalid Login Details.'); \n"
						+ "document.getElementById(\"createPassword2\").value = ''; \n";
				toProcess.r = new WebResponse( WebResponse.HTTP_OK, WebResponse.MIME_HTML, code );
				return true;
			}
			
	
			String code = "setCookie('username',document.getElementById(\"createUsername2\").value,365); \n "
					+ " setCookie('password',document.getElementById(\"createPassword2\").value,365);   \n"
					+ " window.location = '/browseview';";
			
			
			toProcess.r = new WebResponse( WebResponse.HTTP_OK, WebResponse.MIME_HTML, code);
			return true;
			
		}	
			
		else if(toProcess.path.equalsIgnoreCase("createaccount.html"))
		{	
			Account acc1 = new Account();
			acc1.username = toProcess.params.get("username");
			acc1.password = toProcess.params.get("password");
			
			MVMap<String, Account> users1 = db.s.openMap("Account");
			
			if(users1.containsKey(acc1.username))
			{
				String code = "alertbox.show('This username is already in use.'); \n"
						+ "document.getElementById(\"createUsername\").value = ''; \n"
						+ "document.getElementById(\"createPassword\").value = ''; \n";
				toProcess.r = new WebResponse( WebResponse.HTTP_OK, WebResponse.MIME_HTML, code );
				return true;							
			}
			
			users1.put(acc1.username, acc1);
			
			db.commit();
			
			String stringToSendToWebBrowser = "switchToLogin();"
					+ " alertbox.show('Account created. Proceed to login.'); \n"
					+ "document.getElementById(\"createUsername2\").value = '"+acc1.username+"';";
			toProcess.r = new WebResponse( WebResponse.HTTP_OK, WebResponse.MIME_HTML, stringToSendToWebBrowser );
			return true;
		}							
			
		return false;
	}
}




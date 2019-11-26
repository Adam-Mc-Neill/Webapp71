package views;

import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;

import parser.LanguageParser;
import parser.ParserException;

import java.io.IOException;

import storage.DatabaseInterface;
import storage.FileStoreInterface;
import web.WebRequest;
import web.WebResponse;


public class HelpPageView extends DynamicWebPage {

	public HelpPageView(DatabaseInterface db, FileStoreInterface fs) {
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
	
	public boolean process(WebRequest toProcess) {
		if (toProcess.path.equals("check_code")) {
			try {
				LanguageParser.parse(toProcess.params.get("code"));
				toProcess.r = new WebResponse(WebResponse.HTTP_OK, WebResponse.MIME_PLAINTEXT, "ok");
			} catch (ParserException e) {
				toProcess.r = new WebResponse(WebResponse.HTTP_OK, WebResponse.MIME_PLAINTEXT, e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				toProcess.r = new WebResponse(WebResponse.HTTP_OK, WebResponse.MIME_PLAINTEXT, "An unkown error has occured!");
			}
			
			return true;
		}
		
		if (!toProcess.path.startsWith("help")) {
			return false;
		}

		String template = loadPage("./httpdocs/HelpPage.html");
		template = template.replace("{{nav}}", Navbar.getNav(toProcess.cookies, db));
		toProcess.r = new WebResponse(WebResponse.HTTP_OK, WebResponse.MIME_HTML, template);
		return true;
	}
}

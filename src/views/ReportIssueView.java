package views;

import org.h2.mvstore.MVMap;

import model.ReportIssue;
import storage.DatabaseInterface;
import storage.FileStoreInterface;
import web.WebRequest;
import web.WebResponse;

public class ReportIssueView extends DynamicWebPage{

	public ReportIssueView(DatabaseInterface db, FileStoreInterface fs) {
		super(db, fs);
	}
	
	public boolean process(WebRequest toProcess)
	{
        if(toProcess.path.equalsIgnoreCase("reportissue"))
        {
        	
        	String stringToSendToWebBrowser = "<!DOCTYPE html>\r\n" + 
        			"<html>\r\n" + 
        			"\r\n" + 
        			"<head>\r\n" + 
        			"  <meta charset=\"utf-8\">\r\n" + 
        			"  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" + 
        			"  <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\" type=\"text/css\">\r\n" + 
        			"  <link rel=\"stylesheet\" href=\"https://static.pingendo.com/bootstrap/bootstrap-4.3.1.css\">\r\n" + 
        			"<style>\r\n" + 
        			".navbar {\r\n" + 
        			"  margin-bottom: 0; \r\n" + 
        			"}\r\n" + 
        			"\r\n" + 
        			".bg-blue {\r\n" + 
        			"  background-color: #2f3542;\r\n" + 
        			"}\r\n" + 
        			"body  {\r\n" + 
        			"  margin-top: 50px;\r\n" + 
        			"  color: #fff; \r\n" +
        			"  " +
        			"}\r\n" +
        			"\r\n" + 
        			".text-upper {\r\n" + 
        			"  text-transform: uppercase;\r\n" + 
        			"}\r\n" + 
        			"\r\n" + 
        			".nav-brand {\r\n" + 
        			"  color: #ced6e0;\r\n" + 
        			"  font-weight: 700;\r\n" + 
        			"  margin-right: 80px;\r\n" + 
        			"  margin-left: 20px;\r\n" + 
        			"}\r\n" + 
        			".report {\r\n" +
        			" width: 60%; \r\n" +
        			"}\r\n" +
        			".headingtop {\r\n" +
        			"  border-bottom: 2px solid #2EAAA8;\r\n" +
        			"  text-align: center;\r\n" +
        			"  padding-top: 20px; \r\n" +
        			"  padding-bottom: 10px; \r\n" +
        			"}\r\n" +
        			".problem-type {\r\n" +
        			"  padding-top: 10px; \r\n" +
        			"  padding-bottom: 10px; \r\n" +
        			"}\r\n" +
        			".error-detail {\r\n" +
        			"  padding-top: 10px; \r\n" +
        			"  padding-bottom: 10px; \r\n" +
        			"}\r\n" +
        			".errorlabel {\r\n" +
        			"  vertical-align: top;\r\n" +
        			"}\r\n" +
        			".paragraph {\r\n" +
        			"  border-bottom: 2px solid #2EAAA8;\r\n" +
        			"  padding-bottom: 10px;\r\n" +
        			"}\r\n" +
        			".buttonsub {\r\n" +
        			"  border: none;\r\n" +
        			"  border-radius: 12px;\r\n" + 
        			"  color: white;\r\n" + 
        			"  padding-top: 10px; \r\n" +
        			"  padding: 15px 32px;\r\n" + 
        			"  text-align: center;\r\n" + 
        			"  text-decoration: none;\r\n" + 
        			"  display: inline-block;\r\n" + 
        			"  font-size: 16px; \r\n" +
        			"  background-color: #2EAAA8;\r\n" +
        			"}\r\n" +
        			"</style>"+
        			"</head>\r\n" + 
        			"\r\n" + 
        			"<body style=\"background-color: #575761\">\r\n" + 
        			Navbar.getNav(toProcess.cookies, db) +
        			"<div class = \"container-fluid report\">\r\n" +
        			"  <h1 class = \"headingtop\"> Report A Bug </h1>\r\n" + 
        			"  <h3 class = \"paragraph\"> Fill in the form below and let us know about any issues you have with the site or any bugs you might have found and we'll fix them as soon as we can </h3>\r\n" + 
        			"  <form action=\"/reported.html\" method=\"GET\">\r\n" + 
        			"    <section class=\"problem-type\">\r\n" + 
        			"      <label for=\"problem\">What kind of issue are you having?</label>\r\n" + 
        			"      <select id=\"problem\" name=\"problem\">\r\n" + 
        			"        <option value=\"blank\"></option>\r\n" + 
        			"        <option value=\"publish\">Issue with publishing a a game</option>\r\n" + 
        			"        <option value=\"creating\">Issue with creating a game</option>\r\n" + 
        			"        <option value=\"browsing\">Issue with finding a game</option>\r\n" + 
        			"        <option value=\"bug\">General site bugs</option>\r\n" + 
        			"        <option value=\"login\">Issue logging in or signing up</option>\r\n" + 
        			"		 <option value=\"game\">Inappropriate game content</option>\r\n" +
        			"        <option value=\"other\">Other issue</option>\r\n" + 
        			"      </select>\r\n" + 
        			"    </section>\r\n" + 
        			"    <section class=\"error-detail\">\r\n" + 
        			"      <label class = \"errorlabel\" for=\"details\">Enter details of your issues here</label>\r\n" + 
        			"      <textarea id=\"details\" name=\"extra\" rows=\"3\" cols=\"50\"></textarea>\r\n" + 
        			"    </section>\r\n" +
        			"<div style = \"text-align:center;\">\r\n" +
        			"    <section class=\"submission\">\r\n" + 
        			"      <input class = \"buttonsub\" align = \"center\" type=\"submit\" value=\"Submit\">\r\n" + 
        			"    </section>\r\n" + 
        			"</div>\r\n" +
        			"  </form>\r\n" + 
        			"</div>\r\n" +
        			"  <script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\" integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\" crossorigin=\"anonymous\"></script>\r\n" + 
        			"  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js\" integrity=\"sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut\" crossorigin=\"anonymous\"></script>\r\n" + 
        			"  <script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\" integrity=\"sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM\" crossorigin=\"anonymous\"></script>\r\n" + 
        			"</body>\r\n" + 
        			"\r\n" + 
        			"</html>";
        	
        	toProcess.r = new WebResponse( WebResponse.HTTP_OK, WebResponse.MIME_HTML, stringToSendToWebBrowser );

        	return true;
        } else if(toProcess.path.equalsIgnoreCase("reported.html")) {
        	ReportIssue issue = new ReportIssue();
        	issue.issueSelect = toProcess.params.get("problem");
        	issue.detail = toProcess.params.get("extra");
        	
        	MVMap<String, ReportIssue> reportedIssues = db.s.openMap("ReportIssue");
        	reportedIssues.put(issue.issueSelect, issue);
        	reportedIssues.put(issue.detail, issue);
        	this.db.commit();
        	
        	String stringToSendToWebBrowser = "<html>\r\n" +
        	"<head>\r\n" +
        	"<style>\r\n" +
        	".p1 {\r\n" +
        	"  padding-top: 50px;\r\n" +
        	"}\r\n" +
        	".p1, p2 {\r\n" +
        	"  color: #fff;\r\n" +
        	"  text-align: center;\r\n" +
        	"}\r\n" +
        	".fix {\r\n" +
        	"  color: #fff; \r\n" +
        	"}\r\n" +
        	".return {\r\n" +
        	"  color: #2EAAA8;\r\n" + 
    		"}\r\n" +
        	"</style>\r\n" +
        	"<body style=\"background-color: #575761\">\r\n" +
        	"  <p class = \"p1\"><b>Issue Reported Successfully<b></p>\r\n" +
        	"  <p class = \"p2\" style = \"text-align:center;\"><a class = \"return\" href = \"/browseview\"><b>Click here to return to the home page<b></a> <b class = \"fix\">if you are not automatically redirected after 5 seconds</b></p>\r\n" +
        	"<script type=\"text/javascript\">   \r\n" + 
        	"    function Redirect() \r\n" + 
        	"    {  \r\n" + 
        	"        window.location=\"/browseview\"; \r\n" + 
        	"    } \r\n" + 
        	"    setTimeout('Redirect()', 5000);   \r\n" + 
        	"</script>" +
        	"</body>\r\n" +
        	"</html>";
        	
        	toProcess.r = new WebResponse(WebResponse.HTTP_OK, WebResponse.MIME_HTML, stringToSendToWebBrowser);
        	return true;
        }
        return false;
	}
	
}

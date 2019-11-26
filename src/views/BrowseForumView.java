package views;

import storage.DatabaseInterface;
import storage.FileStoreInterface;
import web.WebRequest;
import web.WebResponse;
import views.Navbar;

import java.util.List;

import org.h2.mvstore.MVMap;

import model.Question;

public class BrowseForumView extends DynamicWebPage
{
	public BrowseForumView(DatabaseInterface db,FileStoreInterface fs)
	{
		super(db,fs);
	}

	public boolean process(WebRequest toProcess)
	{
        if(toProcess.path.equalsIgnoreCase("forum"))
        {
        	//Lab 1 Task 4
        	//Change this string so that it contains HTML from a page you created in Pingendo 
        	String stringToSendToWebBrowser = "<!DOCTYPE html>\r\n" + 
        			"<html lang=\"en\">\r\n" + 
        			"  <head>\r\n" + 
        			"    <meta charset=\"utf-8\">\r\n" + 
        			"    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n" + 
        			"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" + 
        			"\r\n" + 
        			"    <title>Bootstrap 4, from LayoutIt!</title>\r\n" + 
        			"\r\n" + 
        			"    <meta name=\"description\" content=\"Source code generated using layoutit.com\">\r\n" + 
        			"    <meta name=\"author\" content=\"LayoutIt!\">\r\n" + 
        			"\r\n" + 
        			"    <link href=\"css/bootstrap.min.css\" rel=\"stylesheet\">\r\n" + 
        			"    <link href=\"css/style.css\" rel=\"stylesheet\">\r\n" + 
        			"    <link href=\"css/PlayGame.css\" rel=\"stylesheet\">\r\n" +
        			"\r\n" + 
        			"  </head>\r\n" + 
        			"  <body>\r\n" + 
        			"\r\n" + 
        			Navbar.getNav(toProcess.cookies, db) +
        			"<br><br><br>" +
        			"    <div class=\"container-fluid\">\r\n" + 
        			"	<div class=\"row\">\r\n" + 
        			"		<div class=\"col-md-12\">\r\n" + 
        			"			<div class=\"page-header\">\r\n" + 
        			"				<h1>\r\n" + 
        			"					Community Forum\r\n" +
        			"				</h1>\r\n" + 
        			"			</div>\r\n" 
        			+ "			</div>\r\n" 
        			+ "			</div>\r\n"  +
        			"			</div>" ;
        	
        	MVMap<Long, Question> Questions = db.s.openMap("questions");
        	List<Long> QuestionKeys = Questions.keyList();
        	
        	for (int i = 0; i < QuestionKeys.size(); i++) {
        		
        		Long questionKeyId = QuestionKeys.get(i);
        		Question loadQuestion = Questions.get(questionKeyId);
        		
        		
        		stringToSendToWebBrowser +=
            		
            			"			<div class=\"card bg-default\">\r\n" + 
            			"				<h5 class=\"card-header\">\r\n" + 
            			"					" + loadQuestion.title + "\r\n" + 
            			"				</h5>\r\n" + 
            			"				</div>\r\n" +
            			"				<div class=\"card-body\">\r\n" + 
            			"					<p class=\"card-text\">\r\n" + 
            			"						" + loadQuestion.question + "\r\n" + 
            			"					</p>\r\n" + 
            			"				</div>\r\n" + 
            			"				<div class=\"card-footer\">\r\n" + 
            			"					" + loadQuestion.username +"\r\n" + 
            			"				</div>";
            		 
            		
				
			}
        	
        	
        	stringToSendToWebBrowser +=
        	
					"   <script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\" integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\" crossorigin=\"anonymous\"></script>\r\n" + 
					"    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js\" integrity=\"sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut\" crossorigin=\"anonymous\"></script>\r\n" + 
					"    <script src=\"./js/bootstrap.min.js\"></script>" + 
        			"  </body>\r\n" + 
        			"</html> ";
        	
        	toProcess.r = new WebResponse( WebResponse.HTTP_OK, WebResponse.MIME_HTML, stringToSendToWebBrowser );

        	return true;
        }
        return false;
	}

}

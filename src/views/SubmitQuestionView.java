package views;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.h2.mvstore.MVMap;

import model.Question;
import storage.DatabaseInterface;
import storage.FileStoreInterface;
import web.WebRequest;
import web.WebResponse;


public class SubmitQuestionView extends DynamicWebPage
{
	public SubmitQuestionView(DatabaseInterface db,FileStoreInterface fs)
	{
		super(db,fs);
	}

	public boolean process(WebRequest toProcess)
	{
		if(toProcess.path.equalsIgnoreCase("SubmitQuestion"))
		{
			//Lab 1 Task 4
			//Change this string so that it contains HTML from a page you created in Pingendo 
			String stringToSendToWebBrowser = "";
			if (toProcess.cookies.get("username") != null) {
			stringToSendToWebBrowser = ""
					+ "<!DOCTYPE html>\r\n" + 
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
					"	<link href=\"css/PlayGame.css\" rel=\"stylesheet\">\r\n" +
					"\r\n" +
					"</head>"
					+ "<body>";
				stringToSendToWebBrowser += Navbar.getNav(toProcess.cookies, db);
			
				stringToSendToWebBrowser += ""+
						"\r\n<br><br><br><br>" + 
					
				
					"    <div class=\"container\">\r\n" + 
					"	<div class=\"row\">\r\n" + 
					"		<div class=\"col-md-12\">\r\n" + 
					"			<h3 class=\"text-center\">\r\n" + 
					"				Submit Question\r\n" + 
					"			</h3>\r\n" + 
					"			<form method=\"GET\" role=\"form\" action=\"/submit\">\r\n" + 
					"				<div class=\"form-group\">\r\n" + 
					"					 \r\n" + 
					"					<label for=\"title\">\r\n" + 
					"						Question Title:\r\n" + 
					"					</label>\r\n" + 
					"					<input type=\"text\" class=\"form-control\" id=\"title\" name=\"title\">\r\n" + 
					"				</div>\r\n" + 
					"				<div class=\"form-group\">\r\n" + 
					"					 \r\n" + 
					"					<label for=\"question\">\r\n" + 
					"						Question body:\r\n" + 
					"					</label>\r\n" + 
					"					<input type=\"text\" class=\"form-control\" id=\"question\" name=\"question\">\r\n" + 
					"				</div>\r\n" + 
					"				<button type=\"submit\" class=\"btn btn-primary\">\r\n" + 
					"					Submit\r\n" + 
					"				</button>\r\n" + 
					"			</form>\r\n" + 
					"		</div>\r\n" + 
					"	</div>\r\n" + 
					"</div>\r\n" + 
					"\r\n" + 
					"   <script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\" integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\" crossorigin=\"anonymous\"></script>\r\n" + 
					"    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js\" integrity=\"sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut\" crossorigin=\"anonymous\"></script>\r\n" + 
					"    <script src=\"./js/bootstrap.min.js\"></script>" +
					"  </body>\r\n" + 
					"</html>";
			}
			else {
				stringToSendToWebBrowser = "<p>You need to be logged in to submit a question</p> <a href=\"browseview\">Back to home</a>";
			
			}
			toProcess.r = new WebResponse( WebResponse.HTTP_OK, WebResponse.MIME_HTML, stringToSendToWebBrowser );

			return true;
		} else if(toProcess.path.equals("submit")) {
			Question newQuestion = new Question();
			newQuestion.ID = System.currentTimeMillis();
			newQuestion.title = toProcess.params.get("title");
			newQuestion.question = toProcess.params.get("question");
			newQuestion.username = toProcess.cookies.get("username");
			Date date = new Date();
			LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			int year = localDate.getYear();
			int month = localDate.getMonthValue();
			int day = localDate.getDayOfMonth();
			newQuestion.date = day + "/" + month + "/" + year;
			
			MVMap<Long, Question> database = db.s.openMap("questions");
			
			database.put(System.currentTimeMillis(), newQuestion);
			
			db.s.commit();
			
			for(Question eachQuestion : database.values()) {
				System.out.println(eachQuestion.title);
			}
		}
		return false;
	}

}


package views;

import org.h2.mvstore.MVMap;

import model.Question;
import storage.DatabaseInterface;
import storage.FileStoreInterface;
import web.WebRequest;
import web.WebResponse;

public class FAQPageView extends DynamicWebPage
{
	public FAQPageView(DatabaseInterface db,FileStoreInterface fs)
	{
		super(db,fs);
	}

	public boolean process(WebRequest toProcess)
	{
        if(toProcess.path.equalsIgnoreCase("faq"))
        {
        	
        	for(String cookie : toProcess.cookies.values()) {
        		System.out.println(cookie);
        	}
        	for(String param : toProcess.params.values()) {
        		System.out.println(param);
        	}
        	
        	
        	String stringToSendToWebBrowser = "Hello";
        	
        	MVMap<Long, Question> questions = db.s.openMap("questions");
        	
        	Question ourNewQuestion = new Question();
        	ourNewQuestion.title = "the question title";
        	ourNewQuestion.question = "content here blah blah";
        	
        	questions.put(System.currentTimeMillis(), ourNewQuestion);
        	
        	db.s.commit();
        	
        	toProcess.r = new WebResponse( WebResponse.HTTP_OK, WebResponse.MIME_HTML, stringToSendToWebBrowser );

        	return true;
        }
        return false;
	}

}

/**package views;

import java.util.List;

import org.h2.mvstore.MVMap;

import model.Game;
import storage.DatabaseInterface;
import storage.FileStoreInterface;
import web.WebRequest;
import web.WebResponse;

public class SearchResultsPage extends DynamicWebPage
{
	public SearchResultsPage(DatabaseInterface db,FileStoreInterface fs)
	{
		super(db,fs);
	}

	public static String embedSearchBox()
	{
		String stringToSendToWebBrowser = "";
		stringToSendToWebBrowser += "          <form class=\"form-inline\" role=\"form\" method=\"GET\" action=\"/searchresults.html\">\r\n" + 
		"            <div class=\"input-group\">\r\n" + 
		"              <input type=\"text\" class=\"form-control\" id=\"searchbox\" onkeyup=\"myFunction()\" name=\"searchstring\" placeholder=\"Search\">\r\n" + 
		"              <div class=\"input-group-append\"><button class=\"btn btn-primary\" type=\"submit\"><i class=\"fa fa-search\"></i></button></div>\r\n" + 
		"            </div>\r\n" + 
		"          </form>\r\n";

		return stringToSendToWebBrowser;
	}
	
	

}*/
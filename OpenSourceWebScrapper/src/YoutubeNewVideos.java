import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;

import org.jsoup.nodes.Element;

import org.jsoup.select.Elements;


public class YoutubeNewVideos {
  
    public final static String YOUTUBE_New_Video_Start_URL = "\"/watch";
    public final static String YOUTUBE_New_Video_End_URL = "\"";
 
    public static ArrayList getYoutubeNewVideos(String channelUrl) throws IOException
    {
        Document document = Jsoup.connect(channelUrl).get();
    
        String html = document.toString();
        
        Pattern pattern = Pattern.compile(YOUTUBE_New_Video_Start_URL + "(.*?)" + YOUTUBE_New_Video_End_URL);
        Matcher matcher = pattern.matcher(html); 
        
        ArrayList newVideoUrlArray = new ArrayList(); 
        
        while (matcher.find()) { 
        	newVideoUrlArray.add(matcher.group());
//        	System.out.println("https://" + YOUTUBE_PROFILE_IMAGE_START_URL+imgUrl.get(imgUrl.size()-1)+YOUTUBE_PROFILE_IMAGE_END_URL); 
        } ;
        
        ArrayList returnArray = new ArrayList(); 
        for(int i=0; i<3; i++) {
        	String newVideoUrl = "youtube.com"+newVideoUrlArray.get(i).toString().replace("\"", "");
        	returnArray.add(newVideoUrl);
        }
        
        
        
        return returnArray;
       
    }
 
}
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;

import org.jsoup.nodes.Element;

import org.jsoup.select.Elements;


public class YoutubeChannelProfileImage {
  
    public final static String YOUTUBE_PROFILE_IMAGE_START_URL = "yt3.ggpht.com/ytc/";
    public final static String YOUTUBE_PROFILE_IMAGE_END_URL = "-c-k-c0x00ffffff-no-rj";
 
    public static String getYoutubeChannelProfileImage(String channelUrl) throws IOException
    {
        Document document = Jsoup.connect(channelUrl).get();
    
        String html = document.toString();
        Pattern pattern = Pattern.compile(YOUTUBE_PROFILE_IMAGE_START_URL + "(.*?)" + YOUTUBE_PROFILE_IMAGE_END_URL);
        Matcher matcher = pattern.matcher(html); 
        ArrayList imgUrl = new ArrayList(); 
        
        while (matcher.find()) { 
             imgUrl.add(matcher.group());
//           System.out.println("https://" + YOUTUBE_PROFILE_IMAGE_START_URL+imgUrl.get(imgUrl.size()-1)+YOUTUBE_PROFILE_IMAGE_END_URL); 
        }  
        return imgUrl.get(imgUrl.size()-1).toString();
       
    }
 
}
import java.awt.FileDialog;

import java.io.IOException;

import java.util.ArrayList;

import java.util.Iterator;

 

import javax.swing.JFrame;

 
import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;

import org.jsoup.nodes.Element;

import org.jsoup.select.Elements;

import org.json.simple.JSONObject; //JSON객체를 만드는데 사용
import org.json.simple.parser.JSONParser; //JSON객체를 파싱해오는데 사용
import org.json.simple.parser.ParseException; //예외처리

public class Main {

 

	public static void main(String[] args) {

		
		// Jsoup를 이용해서 http://www.cgv.co.kr/movies/ 크롤링

		String url = "https://www.youtube.com/channel/UCmN88HSz-EQdwrkGXkc1ElA"; //크롤링할 url지정

		Document doc = null;        //Document에는 페이지의 전체 소스가 저장
		try {

			doc = Jsoup.connect(url).get();

		} catch (IOException e) {

			e.printStackTrace();
		}

		//이름 추출
		String siteName = doc.select("meta[itemprop=name]").attr("content");    
		//이미지 추출
		String profileImageUrl = null;
		YoutubeChannelProfileImage youtube = new YoutubeChannelProfileImage();
		try {
			profileImageUrl = youtube.getYoutubeChannelProfileImage(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//새로운 비디오 페이지 문서
		url = url+"/videos";
		
		try {

			doc = Jsoup.connect(url).get();

		} catch (IOException e) {

			e.printStackTrace();
		}
		
		ArrayList newVideoUrl = null;
		YoutubeNewVideos youtube2 = new YoutubeNewVideos();
		try {
			newVideoUrl = youtube2.getYoutubeNewVideos(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("============================================================");
		System.out.println(siteName);
		System.out.println(profileImageUrl);
		System.out.println(newVideoUrl);
		System.out.println("============================================================");

	}
	static void jsonadd(String parseString){
		String json;
		
		String str = "{\"name\" : \"apple\", \"id\" : 1, \"price\" : 1000}";
		
		System.out.println(str);
		System.out.println(parseString);
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;
		try {
			jsonObject = (JSONObject) parser.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(jsonObject.get("name")); // apple
		System.out.println(jsonObject.get("id")); // 1
		System.out.println(jsonObject.get("price")); // 1000

		
		
    }
}
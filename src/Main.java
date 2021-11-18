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

import org.json.simple.JSONObject; //JSON��ü�� ����µ� ���
import org.json.simple.parser.JSONParser; //JSON��ü�� �Ľ��ؿ��µ� ���
import org.json.simple.parser.ParseException; //����ó��

public class Main {

 

	public static void main(String[] args) {

		
		// Jsoup�� �̿��ؼ� http://www.cgv.co.kr/movies/ ũ�Ѹ�

		String url = "https://www.youtube.com/channel/UCmN88HSz-EQdwrkGXkc1ElA"; //ũ�Ѹ��� url����

		Document doc = null;        //Document���� �������� ��ü �ҽ��� ����
		try {

			doc = Jsoup.connect(url).get();

		} catch (IOException e) {

			e.printStackTrace();
		}

		//�̸� ����
		String siteName = doc.select("meta[itemprop=name]").attr("content");    
		//�̹��� ����
		String profileImageUrl = null;
		YoutubeChannelProfileImage youtube = new YoutubeChannelProfileImage();
		try {
			profileImageUrl = youtube.getYoutubeChannelProfileImage(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//���ο� ���� ������ ����
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
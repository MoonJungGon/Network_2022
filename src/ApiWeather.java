import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import org.json.simple.parser.ParseException;


public class ApiWeather {
	static String[][] data = new String[6][2];

    public static String[][] getWeather() throws IOException, ParseException{
        String apiUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst";
        // 홈페이지에서 받은 키 
        String serviceKey = "z1yF8I0dKCXiMtqLxrOB50flBkAdB1HRrNdmRSJxZY8dQbsZQG5pFrABuegUpQYFod5EBzSbhMzkubgPDwNxqg%3D%3D";
        String nx = "37.4556163";// 위도
        String ny = "127.133144";// 경도
        TransLocalPoint.LatXLngY data1;
        data1 = TransLocalPoint.convertGRID_GPS(0, Double.parseDouble(nx), Double.parseDouble(ny));
        System.out.println(data1.lat);
        System.out.println(data1.lng);
        System.out.println(data1.x);
        System.out.println(data1.y);
        nx = String.valueOf((int)data1.x);
        ny = String.valueOf((int)data1.y);
        System.out.println("nx : " + nx + "ny : " + ny);
        String baseDate = "20221213";// 조회하고싶은 날짜
        String baseTime = "1800";// 조회하고싶은 시간
        String type = "JSON";// 타입 xml, json 등등 ..
        StringBuilder urlBuilder1 = new StringBuilder(apiUrl);
        urlBuilder1.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + serviceKey);
        urlBuilder1.append("&" + URLEncoder.encode("nx", "UTF-8") + "=" + URLEncoder.encode(nx, "UTF-8")); // 경도 
        urlBuilder1.append("&" + URLEncoder.encode("ny", "UTF-8") + "=" + URLEncoder.encode(ny, "UTF-8")); // 위도
        urlBuilder1.append("&" + URLEncoder.encode("base_date", "UTF-8") + "=" + URLEncoder.encode(baseDate, "UTF-8")); /* 조회하고싶은 날짜 */
        urlBuilder1.append("&" + URLEncoder.encode("base_time", "UTF-8") + "=" + URLEncoder.encode(baseTime, "UTF-8")); /* 조회하고싶은 시간 AM 02시부터 3시간 단위 */
        urlBuilder1.append("&" + URLEncoder.encode("dataType", "UTF-8") + "=" + URLEncoder.encode(type, "UTF-8"));/* 타입 */
        /* GET방식으로 전송해서 파라미터 받아오기 */
        URL url = new URL(urlBuilder1.toString());
        // 어떻게 넘어가는지 확인하고 싶으면 아래 출력분 주석 해제
        // System.out.println(url);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        // System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        String result = sb.toString();
        // System.out.println(result);
        
        // Json parser를 만들어 만들어진 문자열 데이터를 객체화
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject) parser.parse(result); 
		// response 키를 가지고 데이터를 파싱
		JSONObject parse_response = (JSONObject) obj.get("response"); 
		// response 로 부터 body 찾기
		JSONObject parse_body = (JSONObject) parse_response.get("body"); 
		// body 로 부터 items 찾기 
		JSONObject parse_items = (JSONObject) parse_body.get("items");

		// items로 부터 itemlist 를 받기 
		JSONArray parse_item = (JSONArray) parse_items.get("item");
        String category;
		JSONObject weather; // parse_item은 배열형태이기 때문에 하나씩 데이터를 하나씩 가져올때 사용
		// 카테고리와 값만 받아오기
		String day="";
		String time="";
        // data [기온, 1시간 강수량, 습도, 강수형태, 풍향, 풍속]
        // data [T1H, RN1, REH, PTY, VEC, WSD]
        // String[] cate = {"T1H(기온, ℃)", "RN1(1시간 강수량, mm)", "REH(습도, %)", "PTY(코드값)", "VEC(풍향, deg)", "WSD(풍속, m/s)"};
		String[] cate = {"기온", "1시간 강수량", "습도", "강수형태", "풍향", "풍속"};
		for(int i=0; i<6; i++) {
        	data[i][0] = cate[i];
        }
		for(int i = 0 ; i < parse_item.size(); i++) {
			weather = (JSONObject) parse_item.get(i);
            System.out.println(weather);
			Object fcstValue = weather.get("obsrValue");
			Object fcstDate = weather.get("baseDate");
			Object fcstTime = weather.get("baseTime");
			//double형으로 받고싶으면 아래내용 주석 해제
			//double fcstValue = Double.parseDouble(weather.get("fcstValue").toString());
			category = (String)weather.get("category"); 
            // System.out.println(category);
			// 출력
			if(!day.equals(fcstDate.toString())) {
				day=fcstDate.toString();
			}
			if(!time.equals(fcstTime.toString())) {
				time=fcstTime.toString();
				System.out.println(day+"  "+time);
			}

            if(category.equals("T1H")){
                data[0][1] = (String)fcstValue;
            }
            if(category.equals("RN1")){
                data[1][1] = (String)fcstValue;
            }
            if(category.equals("REH")){
                data[2][1] = (String)fcstValue;
            }
            if(category.equals("PTY")){
                int temp = Integer.parseInt((String) fcstValue);
                String temps = "";
                if(temp==1){
                    temps = "비";
                } else if(temp==2){
                    temps = "비/눈";
                } else if(temp==3){
                    temps = "눈";
                } else if(temp==5){
                    temps = "빗방울";
                } else if(temp==6){
                    temps = "빗방울눈날림";
                } else if(temp==7){
                    temps = "눈날림";
                } else{
                    temps = "없음";
                }
                data[3][1] = temps;
            }
            if(category.equals("VEC")){
                int temp = Integer.parseInt((String) fcstValue);
                String temps = "";
                if(temp > 0 && temp < 45){
                    temps = "북-북동(N-NE)";
                } else if(temp == 45){
                    temps = "북동(NE)";
                } else if(temp > 45 && temp < 90){
                    temps = "북동-동(NE-E)";
                } else if(temp == 90){
                    temps = "동(E)";
                } else if(temp > 90 && temp < 135){
                    temps = "동-동남(E-SE)";
                } else if(temp == 135){
                    temps = "동남(SE)";
                } else if(temp > 135 && temp < 180){
                    temps = "동남-남(SE-S)";
                } else if(temp == 180){
                    temps = "남(S)";
                } else if(temp > 180 && temp < 225){
                    temps = "남-남서(S-SW)";
                } else if(temp == 225){
                    temps = "남서(SW)";
                } else if(temp > 225 && temp < 270){
                    temps = "남서-서(SW-W)";
                } else if(temp == 270){
                    temps = "서(W)";
                } else if(temp > 270 && temp < 315){
                    temps = "서-북서(W-NW)";
                } else if(temp == 315){
                    temps = "북서(NW)";
                } else if(temp > 315 && temp < 360){
                    temps = "북서-북(NW-N)";
                } else {
                    temps = "N";
                }
                data[4][1] = temps;
            }
            if(category.equals("WSD")){
                data[5][1] = (String)fcstValue;
            }
			// System.out.print("\tcategory : "+ category);
			// System.out.print(", fcst_Value : "+ fcstValue);
			// System.out.print(", fcstDate : "+ fcstDate);
			// System.out.println(", fcstTime : "+ fcstTime);
		}
        // for(int i=0; i<6; i++){
        //     System.out.println(data[i][0] + " : " + data[i][1]);
        // }
        return data;
    }
}
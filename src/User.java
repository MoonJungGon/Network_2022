
import java.net.InetAddress;

public class User {
    public  String id; // 아이디
    public  String name; // 이름
    public  String nickName; // 별명
    public  String email; // 이메일
    public  String phone; // 휴대폰 번호
    public  String birthdate; // 생년월일
    public  String connect; // 접속유무, 'online'이면 접속X, 'offline'이면 접속O
    public  String clientIp;// 클라이언트 IP
    public  int clientPort; // 클라이언트 Port 번호

    public  InetAddress serverIp;// 접속 서버 IP
    public  int serverPort; // 서버 Port 번호

    public  String lastLoginTime;// 마지막 로그인 시간
    public  String logOutTime;// 로그인 아웃한 시간
    public  String todayWords;// 오늘의 한마디
    public  int numOfLogins;// 로그인 횟수



}
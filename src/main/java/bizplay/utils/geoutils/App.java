package bizplay.utils.geoutils;

import java.util.TimeZone;

import org.joda.time.LocalDateTime;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        System.out.println( "Hello World!" );
        
        LocalDateTime now = LocalDateTime.now();
        String format = "yyyyMMddHHmmss";
        String clientIpAddress = "168.126.63.1";
        
        //1. 날짜 객체 생성 
        LocalDateTime clientDt = GeoUtils.toLocalDateTime(
        	clientIpAddress,		// ip 또는 request 객체 
        	now.toString(format),	// 날짜 문자열
        	format					// 날짜 포맷
        	);
        
        //2. 출력
        System.out.println("# 내가 있는 곳 timezone");
        System.out.println(TimeZone.getDefault());
        System.out.println("# 내가 있는 곳 시간");
        System.out.println(now);
        System.out.println("\n");
        System.out.println("# 접속 지역 timezone");
        System.out.println(GeoUtils.getTimeZoneByIpAddress(clientIpAddress));
        System.out.println("# 접속 지역 시간");
        System.out.println(clientDt);
    }
}

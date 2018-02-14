# geoutils

* 예제 (App.java)

<pre>
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
</pre>

* 실행 결과

<pre>
# 내가 있는 곳 timezone
sun.util.calendar.ZoneInfo[id="Asia/Bangkok",offset=25200000,dstSavings=0,useDaylight=false,transitions=3,lastRule=null]
# 내가 있는 곳 시간
2018-02-14T13:48:08.580


# 접속 지역 timezone
sun.util.calendar.ZoneInfo[id="Asia/Seoul",offset=32400000,dstSavings=0,useDaylight=false,transitions=22,lastRule=null]
# 접속 지역 시간
2018-02-14T15:48:08.000
</pre>

* 참조 사이트

<pre>
https://www.mkyong.com/java/how-to-get-client-ip-address-in-java/

https://en.wikipedia.org/wiki/List_of_time_zone_abbreviations

https://github.com/agap/llttz

https://github.com/maxmind/geoip-api-java

https://dev.maxmind.com/geoip/legacy/geolite/
</pre>

* was(tomcat, jboss)에 적용 시 참고 사항

<pre>
파일을 해당 위치에 업로드하세요. (was 재시작 )

- resources/GeoLiteCity.dat -> WEB-INF/classes/GeoLiteCity.dat
- resources/timezones.csv -> WEB-INF/classes/timezones.csv
</pre>

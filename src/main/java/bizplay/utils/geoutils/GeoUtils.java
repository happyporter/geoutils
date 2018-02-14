package bizplay.utils.geoutils;

import java.io.File;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;

import gapchenko.llttz.Converter;
import gapchenko.llttz.IConverter;
import gapchenko.llttz.stores.TimeZoneListStore;

public class GeoUtils {
	
	public static File datFile;
	final public static TimeZone DEFAULT_TIMEZONE = TimeZone.getDefault();	//getTimeZone("KST");
	final private static String PATH_TO_DAT = "/GeoLiteCity.dat";
	
	static {
		datFile = new File(GeoUtils.class.getResource(PATH_TO_DAT).getFile());
	}
	
	public static TimeZone getTimeZoneByIpAddress(String ipAddress) {
		TimeZone tz = DEFAULT_TIMEZONE;
		
		try {
			LookupService lookup;
			lookup = new LookupService(datFile, LookupService.GEOIP_MEMORY_CACHE);

			Location locationServices = lookup.getLocation(ipAddress);
			
			if(locationServices != null) {
				IConverter iconv = Converter.getInstance(TimeZoneListStore.class);
				tz = iconv.getTimeZone(locationServices.latitude, locationServices.longitude);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return tz;
	} 
	
	public static LocalDateTime toLocalDateTime(HttpServletRequest request, String str, String format) {
		return toLocalDateTime(getClientIp(request), str, format);
	}
	
	public static LocalDateTime toLocalDateTime(String ipAddress, String str, String format) {
		DateTimeFormatter formatter = DateTimeFormat.forPattern(format);
		
		try {
			DateTime dt = formatter.withZone(DateTimeZone.forTimeZone(GeoUtils.DEFAULT_TIMEZONE)).parseDateTime(str);
			return GeoUtils.toLocalDateTime(ipAddress, dt);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static LocalDateTime toLocalDateTime(HttpServletRequest request, DateTime dt) {
		return toLocalDateTime(getClientIp(request), dt);
	}
	
	public static LocalDateTime toLocalDateTime(String ipAddress, DateTime dt) {
		TimeZone tz = getTimeZoneByIpAddress(ipAddress);
		return dt.withZone(DateTimeZone.forTimeZone(tz)).toLocalDateTime();
	}
	
	private static String getClientIp(HttpServletRequest request) {

        String remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }

        return remoteAddr;
    }
}



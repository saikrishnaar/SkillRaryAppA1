package genericLibraries;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * This method contains reusable methods of java
 * @author SaiArpi
 */
public class JavaUtility {
	
	public int generateRandomNum(int limit) {
		Random random=new Random();
		return random.nextInt(limit);
	}
	
	/**
	 * This method generates current time
	 * @return
	 */
	public String getCurrentTime() {
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("dd_MM_hh_mm_ss");
		return sdf.format(date);
	}
}

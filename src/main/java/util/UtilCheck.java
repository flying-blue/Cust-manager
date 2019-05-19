package util;
/**
 * ¹²Í¨Check
 * @author Xu_fei
 *
 */
public class UtilCheck {
	
	public static int isEmptyStr(String str) {
		
		if(str==null || "".equals(str)) {
			return 1;
		}else {
			return 0;
		}
		
	}
	public static boolean isEmpty(String str) {
		
		if(str==null || "".equals(str)) {
			return true;
		}else {
			return false;
		}
		
	}

}

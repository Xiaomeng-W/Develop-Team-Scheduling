package wxm.view;

import java.util.*;

/**
 * 
 * @author xmwang 
 * @Description 项目中提供了TSUtility.java类，可用来方便地实现键盘访问
 * @date 2020年12月29日
 */
public class TSUtility {
	private static Scanner scanner = new Scanner(System.in);
	/**
	 * 
	 * @author xmwang
	 * @Description 读取键盘，如果用户输入1-4中的任意字符，则方法返回，返回值为用户键入字符，否则重新输入
	 * @date 2020年12月29日
	 * @return
	 */
	public static char readMenuSelection() {
		char c;
		while (true) {
			String str = readKeyBoard(1, false);
			c = str.charAt(0);
			if (c!='1' && c!='2' && c!='3' && c!='4') {
				System.out.println("选择错误，请重新输入：");
			}else break;
		}
		return c;
	}
	/**
	 * 
	 * @author xmwang
	 * @Description 该方法提示并等待，直到用户按回车后返回
	 * @date 2020年12月29日
	 */
	public static void readReturn() {
		System.out.println("按回车继续...");
		readKeyBoard(100, true);
	}
	/**
	 * 
	 * @author xmwang
	 * @Description 从键盘读取一个长度不超过2位的整数，并将其作为方法的返回值
	 * @date 2020年12月29日
	 * @return
	 */
	public static int readInt() {
		int n;
		while (true){
			String str = readKeyBoard(2, false);
			try {
				n = Integer.parseInt(str);
				break;
			}catch(NumberFormatException e) {
				System.out.println("数字输入错误，请重新输入：");
			}
		}
		return n;
	}
	/**
	 * 
	 * @author xmwang
	 * @Description 从键盘读取'Y'或'N'，并将其作为方法的返回值
	 * @date 2020年12月29日
	 * @return
	 */
	public static char readConfirmSelection() {
		char c;
		while (true) {
			String str = readKeyBoard(1, false).toUpperCase();
			c = str.charAt(0);
			if (c=='Y' || c=='N')
				break;
			else
				System.out.println("选择错误，请重新输入：");
		}
		return c;
	}
	
	private static String readKeyBoard(int limit, boolean blankReturn) {
		String line = "";
		while (scanner.hasNextLine()) {
			line = scanner.nextLine();
			if (line.length()==0) {
				if (blankReturn) return line;
				else continue;
			}
			
			if (line.length()<1 || line.length()>limit) {
				System.out.println("输入长度（不大于" + limit + "），请重新输入：");
				continue;
			}
			break;
		}
		return line;
	}
}

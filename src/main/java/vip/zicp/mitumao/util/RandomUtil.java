package vip.zicp.mitumao.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
/**
 * 
 * @author iven
 * 
 */
public class RandomUtil {

	private static int[] primes = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37,
			41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107,
			109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179,
			181, 191, 193, 197, 199 };

	/**
	 * 获取步长
	 * 
	 * @return
	 */
	public static int getPrimes() {
		return primes[(int) (Math.random() * (primes.length))];
	}
	
	private static final char[] codeSequences = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9' };
	
	/**
	 * 
	 * 随机生成6位数新密码
	 */
	public static String randomInt(int length) {
		StringBuffer randomCode = new StringBuffer(length);
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			String strRand = String.valueOf(codeSequences[random.nextInt(10)]);
			randomCode.append(strRand);
		}
		return randomCode.toString();
	}
	
	/**
	 * 生产流水号
	 * @param session
	 * @return
	 */
	public static String createTransSeq(){
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		String date = format.format(new Date());
		String seq = System.currentTimeMillis()+"";
		String random = RandomUtil.randomInt(6);
		return seq.substring(6, seq.length()) + random + date.substring(2);
	}

	/**
	 * 获取基准数
	 * 
	 * @return
	 */
	public static int getBaseNum() {
		return (int) Math.round(Math.random() * 8999 + 1000);
	}
}

package repast.yiyou.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class EncryptUtil {

	final static String ENCRYPT_KEY = "418d8681b964b0a772325e03a076bb26";

	static Key key;

	/**
	 * 根据参数生成KEY
	 * 
	 */
	static {
		try {
			KeyGenerator _generator = KeyGenerator.getInstance("DES");
			_generator.init(new SecureRandom(ENCRYPT_KEY.getBytes()));
			key = _generator.generateKey();
			_generator = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 加密String明文输入,String密文输出
	 * 
	 * @param strMing
	 * @return
	 */
	public static String getEncString(String strMing) {
		byte[] byteMi = null;
		byte[] byteMing = null;
		String strMi = "";
		BASE64Encoder base64en = new BASE64Encoder();
		try {
			byteMing = strMing.getBytes("UTF8");
			byteMi = getEncCode(byteMing);
			strMi = base64en.encode(byteMi);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			base64en = null;
			byteMing = null;
			byteMi = null;
		}
		return strMi;
	}

	/**
	 * 解密 以String密文输入,String明文输出
	 * 
	 * @param strMi
	 * @return
	 */
	public static String getDesString(String strMi) {
		BASE64Decoder base64De = new BASE64Decoder();
		byte[] byteMing = null;
		byte[] byteMi = null;
		String strMing = "";
		try {
			byteMi = base64De.decodeBuffer(strMi);
			byteMing = getDesCode(byteMi);
			strMing = new String(byteMing, "UTF8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			base64De = null;
			byteMing = null;
			byteMi = null;
		}
		return strMing;
	}

	/**
	 * 加密以byte[]明文输入,byte[]密文输出
	 * 
	 * @param byteS
	 * @return
	 */
	private static byte[] getEncCode(byte[] byteS) {
		byte[] byteFina = null;
		Cipher cipher;
		try {
			cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byteFina = cipher.doFinal(byteS);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cipher = null;
		}
		return byteFina;
	}

	/**
	 * 解密以byte[]密文输入,以byte[]明文输出
	 * 
	 * @param byteD
	 * @return
	 */
	private static byte[] getDesCode(byte[] byteD) {
		Cipher cipher;
		byte[] byteFina = null;
		try {
			cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			byteFina = cipher.doFinal(byteD);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cipher = null;
		}
		return byteFina;
	}

	public static String encryptUrl(String s) {
		try {
			return URLEncoder.encode(s, "gbk");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}
	}

	public static String decryptUrl(String s) {
		try {
			return URLDecoder.decode(s, "gbk");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 
	 * function: MD5加密
	 * 
	 * @param
	 * @param source
	 * @return
	 */
	public static String getMD5(String encryString) {
		if(encryString==null)return null;
		String s = null;
		try {
			byte[] source = encryString.getBytes("UTF-8");
			char hexDigits[] = { // 用来将字节转换成 16 进制表示的字�??
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			md.update(source);
			byte tmp[] = md.digest(); // MD5 的计算结果是�??�� 128 位的长整数，
			// 用字节表示就�??16 个字�??
			char str[] = new char[16 * 2]; // 每个字节�??16 进制表示的话，使用两个字符，
			// �??��表示�??16 进制�??�� 32 个字�??
			int k = 0; // 表示转换结果中对应的字符位置
			for (int i = 0; i < 16; i++) { // 从第�??��字节�??��，对 MD5 的每�??��字节
				// 转换�??16 进制字符的转�??
				byte byte0 = tmp[i]; // 取第 i 个字�??
				str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中�??4 位的数字转换,
				// >>> 为�?辑右移，将符号位�??��右移
				str[k++] = hexDigits[byte0 & 0xf]; // 取字节中�??4 位的数字转换
			}
			s = new String(str); // 换后的结果转换为字符�??
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return s;
	}

	/**
	 * 对字符串进行GB2312编码后再加密
	 * 
	 * @param encryString
	 * @return
	 */
	public static String getMD5WithGBK(String encryString) {
		String s = null;
		try {
			byte[] source = encryString.getBytes("GB2312");
			char hexDigits[] = { // 用来将字节转换成 16 进制表示的字�??
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			md.update(source);
			byte tmp[] = md.digest(); // MD5 的计算结果是�??�� 128 位的长整数，
			// 用字节表示就�??16 个字�??
			char str[] = new char[16 * 2]; // 每个字节�??16 进制表示的话，使用两个字符，
			// �??��表示�??16 进制�??�� 32 个字�??
			int k = 0; // 表示转换结果中对应的字符位置
			for (int i = 0; i < 16; i++) { // 从第�??��字节�??��，对 MD5 的每�??��字节
				// 转换�??16 进制字符的转�??
				byte byte0 = tmp[i]; // 取第 i 个字�??
				str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中�??4 位的数字转换,
				// >>> 为�?辑右移，将符号位�??��右移
				str[k++] = hexDigits[byte0 & 0xf]; // 取字节中�??4 位的数字转换
			}
			s = new String(str); // 换后的结果转换为字符�??
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return s;
	}

	// 转化字符串为十六进制编码
	public static String toHexString(String s) {
		String str = "";
		for (int i = 0; i < s.length(); i++) {
			int ch = (int) s.charAt(i);
			String s4 = Integer.toHexString(ch);
			str = str + s4;
		}
		return str;
	}

	// 转化十六进制编码为字符串
	public static String hextoString(String s) {
		byte[] baKeyword = new byte[s.length() / 2];
		for (int i = 0; i < baKeyword.length; i++) {
			try {
				baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			s = new String(baKeyword, "GBK");// UTF-16le:Not
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return s;
	}
	
	public static void main(String[] args) {
		System.out.println(getMD5("admin"));
	}

}

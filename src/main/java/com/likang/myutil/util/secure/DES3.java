package com.likang.myutil.util.secure;

import java.security.MessageDigest;
import java.util.Base64;//JDK1.8

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


/**
 * 3DES加密
 */
public class DES3 {

	private static final String Algorithm = "DESede"; //定义加密算法,可用DES,DESede,Blowfish

	private static final String CODE = "UTF-8";

	/**
	 * Decrypt3DES 3des解码
	 * 
	 * @param value
	 * @param key
	 * @return
	 * @throws Exception
	 * @exception
	 * @since 1.0.0
	 */
	public static String Decrypt3DES(String value, String key) throws Exception {

		byte[] b = decryptMode(GetKeyBytes(key), Base64.getDecoder().decode(value.getBytes(CODE)));
		return new String(b, CODE);

	}

	/**
	 * Encrypt3DES 3DES加密
	 * 
	 * @param value
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String Encrypt3DES(String value, String key) throws Exception {

		String str = byte2Base64(encryptMode(GetKeyBytes(key),	value.getBytes(CODE)));

		return str;

	}

	// 计算24位长的密码byte值,首先对原始密钥做MD5算hash值，再用前8位数据对应补全后8位
	private static byte[] GetKeyBytes(String strKey) throws Exception {

		if (null == strKey || strKey.length() < 1)
			throw new Exception("key is null or empty!");

		MessageDigest alg = MessageDigest.getInstance("MD5");

		alg.update(strKey.getBytes());

		byte[] bkey = alg.digest();

		int start = bkey.length;

		byte[] bkey24 = new byte[24];

		for (int i = 0; i < start; i++) {
			bkey24[i] = bkey[i];
		}

		for (int i = start; i < 24; i++) {// 为了与.net16位key兼容
			bkey24[i] = bkey[i - start];
		}

		return bkey24;

	}


	/**
	 * 
	 * @param keybyte加密密钥，长度为24字节
	 * @param src被加密的数据缓冲区（源）
	 * @return
	 */
	public static byte[] encryptMode(byte[] keybyte, byte[] src) {

		try {

			// 生成密钥
			SecretKey deskey = new SecretKeySpec(keybyte, Algorithm); // 加密

			Cipher c1 = Cipher.getInstance(Algorithm);

			c1.init(Cipher.ENCRYPT_MODE, deskey);

			return c1.doFinal(src);

		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}

		return null;
	}

	/**
	 * 
	 * @param keybyte加密密钥，长度为24字节
	 * @param src加密后的缓冲区
	 * @return
	 */
	public static byte[] decryptMode(byte[] keybyte, byte[] src) {

		try { // 生成密钥

			SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);

			// 解密

			Cipher c1 = Cipher.getInstance(Algorithm);

			c1.init(Cipher.DECRYPT_MODE, deskey);

			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}

		return null;
	}

	/**
	 * 转换成base64编码
	 * @param b
	 * @return
	 */
	public static String byte2Base64(byte[] b) {
		return new String(Base64.getEncoder().encode(b));
	}

	/**
	 * 转换成十六进制字符串 
	 * @param b
	 * @return
	 */
	public static String byte2hex(byte[] b) {

		String hs = "";

		String stmp = "";

		for (int n = 0; n < b.length; n++) {

			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));

			if (stmp.length() == 1){
				hs = hs + "0" + stmp;
			}else{
				hs = hs + stmp;
			}
			
			if (n < b.length - 1){
				hs = hs + ":";
			}
		}

		return hs.toUpperCase();
	}

	public static void main(String[] args) throws Exception{
		String str = "12345678";
		String key = "123456";
		String encrypt = Encrypt3DES(str, key);
		System.err.println(encrypt);
		System.err.println(Decrypt3DES(encrypt, key));
	}
}

package user;

import java.io.UnsupportedEncodingException;
import java.security.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public abstract class Login {
	private String password;
	private byte[] hashPassword;
/*
	public String getHashedPassword(String enteredPassword) {
		byte[] bytesOfMessage;
		try {
			bytesOfMessage = enteredPassword.getBytes("UTF-8");

			MessageDigest md = MessageDigest.getInstance("MD5");
			hashPassword = md.digest(bytesOfMessage);

			// password = DigestUtils.md5Hex(hashPassword);

		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return password;
	}

	public static String bcryptHash(String enteredPW) {
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String password = passwordEncoder.encode(enteredPW);

		return password;
	}
*/
	public void setPassword(String newPassword) {
		this.password = newPassword;
	}

	public boolean setPassword(String newPassword, String password) {
		// TODO Auto-generated method stub
		return false;
	}
}
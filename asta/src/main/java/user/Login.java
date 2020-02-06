
package user;

public abstract class Login extends LoginData {
  private String passwordExtension;
  private byte[] passwordHash;
  private String hashMethode;
  private String password;
/*
  public Login(String password) {
    StringBuilder buildAddToPassword = new StringBuilder(LoginData.NORMALLENGTH);
    for (int i = 0; i < LoginData.NORMALLENGTH; ++i) {
      buildAddToPassword.append((char) (Math.random() * Short.MAX_VALUE));
    }
    passwordExtension = buildAddToPassword.toString();
    hashMethode = LoginData.NORMALLENGTH;
    if (password != null) {
      setPassword(password);
    }
  }

  public boolean testPassword(String input) {
    byte[] hashPassword;
    if (hashPassword == null || !match(hashPassword, hashPassword(input))) {
      return false;
    }
    if (!hashMethode.equals(LoginData.STANDARD_HASH)) {
      hashMethode = LoginData.STANDARD_HASH;
      hashPassword = hashPassword(input);
    }
    return true;
  }
*/
  private boolean match(byte[] match, byte[] with) {
    for (int i = 0; i < match.length; ++i) {
      if (match[i] != with[i]) {
        return false;
      }
    }
    return true;
  }
  
  public void setPassword(String newPassword) {
    this.password = newPassword;
  }

  public boolean setPassword(String newPassword, String password) {
    // TODO Auto-generated method stub
    return false;
  }
}
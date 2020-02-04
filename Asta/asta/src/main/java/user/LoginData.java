
package user;

public abstract class LoginData {
  public static final String HASHING = "MD5";
  public static final String CODING = "UTF-8";
  public static final int NORMALLENGTH = 255;
  private Integer id;

  public abstract boolean testPassword(String input);

  public abstract void setPassword(String newPassword);

  public abstract boolean setPassword(String newPassword, String password);

  public final Integer getId() {
    return id;
  }

  public final void setId(Integer id) {
    this.id = id;
  }
}

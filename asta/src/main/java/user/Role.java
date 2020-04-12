
package user;

public enum Role {
  ADMIN("Administrator"), USER("Nutzer");

  /* Die Rolle des Users. Bisher gibt es einen Admin. */

  private final String role;

  private Role(String role) {
    this.role = role;
  }

  public String getRole() {
    return this.role;
  }
}

package user;

public enum Status {
  STUDENT("Student"), EXTERN("Externer");

  private final String status;

  private Status(String status) {
    this.status = status;
  }

  public String getStatus() {
    return this.status;
  }
}
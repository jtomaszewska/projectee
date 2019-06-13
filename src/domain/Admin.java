package domain;

public class Admin extends User{
    public Admin(String firstName, String lastName, String email) {
        super(firstName, lastName, email);
    }

    public void givePermission(String email) {
    }
}

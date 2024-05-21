package pk.wieik.it.model;

import java.util.ArrayList;
import java.util.List;

public class FCuser {

    private String login;
    private String password;
    private int privileges = -1;
    private ArrayList<Integer> favourites = new ArrayList<>();

    public FCuser() {
        this.login = "";
        this.password = "";
    }

    public FCuser(String login, String password, int privileges) {
        this.login = login;
        this.password = password;
        this.privileges = privileges;
    }

    // -1 user not logged in
    // 1 user logged in
    // 2 administrator

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() { return this.password; }

    public int getPrivileges() {
        return privileges;
    }

    public void setPrivileges(int privileges) {
        this.privileges = privileges;
    }

    public ArrayList<Integer> getFavourites() {
        return this.favourites;
    }

    public void toggleFavourite(int id) {
        System.out.println("Toggle favourite " + id);
        if (this.favourites.contains(id)) {
            this.favourites.remove(Integer.valueOf(id));
        } else {
            this.favourites.add(id);
        }
    }

    @Override
    public String toString() {
        return "FCuser{" +
                "login='" + login + "'" +
                ", privileges=" + privileges +
                "}";
    }

    public static List<FCuser> initializeDb() {
        List<FCuser> users = new ArrayList<>();
        users.add(new FCuser("admin", "admin", 2));
        users.add(new FCuser("user", "user", 1));

        return users;
    }
}
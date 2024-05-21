package pk.wieik.it.model;

import java.util.ArrayList;

public class FCuser {

    private String login = "";
    private int privileges = -1;
    private ArrayList<Integer> favourites = new ArrayList<>();

    // -1 user not logged in
    // 1 user logged in
    // 2 administrator

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

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
}

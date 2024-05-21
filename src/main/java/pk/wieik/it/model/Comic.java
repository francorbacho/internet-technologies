package pk.wieik.it.model;

import java.util.ArrayList;
import java.util.List;

public class Comic {
    public String title;
    public String author;
    public int year;
    public int dateAdded;

    public Comic(String title, String author, int year, int dateAdded) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.dateAdded = dateAdded;
    }

    public static List<Comic> initializeDb() {
        List<Comic> comics = new ArrayList<Comic>();
        comics.add(new Comic("A", "B", 1999, 2024));
        comics.add(new Comic("A", "B", 2000, 2024));
        comics.add(new Comic("A", "B", 2001, 2024));
        comics.add(new Comic("A", "B", 2002, 2024));
        comics.add(new Comic("A", "B", 2003, 2024));
        comics.add(new Comic("A", "B", 2004, 2024));
        comics.add(new Comic("A", "B", 2002, 2024));

        return comics;
    }
}

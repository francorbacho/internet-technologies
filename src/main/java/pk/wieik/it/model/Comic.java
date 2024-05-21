package pk.wieik.it.model;

import java.util.ArrayList;
import java.util.List;

public class Comic {
    public int id;
    public String title;
    public String author;
    public int year;
    public int dateAdded;

    public Comic(int id, String title, String author, int year, int dateAdded) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.dateAdded = dateAdded;
    }

    public static List<Comic> initializeDb() {
        List<Comic> comics = new ArrayList<Comic>();
        comics.add(new Comic(0, "A", "B", 1999, 2024));
        comics.add(new Comic(1, "A", "B", 2000, 2024));
        comics.add(new Comic(2, "A", "B", 2001, 2024));
        comics.add(new Comic(3, "A", "B", 2002, 2024));
        comics.add(new Comic(4, "A", "B", 2003, 2024));
        comics.add(new Comic(5, "A", "B", 2004, 2024));
        comics.add(new Comic(6, "A", "B", 2002, 2024));

        return comics;
    }
}

import java.io.Serializable;
import java.util.Objects;

/**
 * Challenge Game Library
 * Stores one game's details.
 *
 * @author Anathi Mudunuri, CS 18000 
 *
 * @version October 23, 2026
 *
 */


public class Game implements Serializable {

    private String name;
    private int releaseYear;
    private String genre;
    private int hoursPlayed;

    public Game(String name, int releaseYear, String genre, int hoursPlayed) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.hoursPlayed = hoursPlayed;
    }

    public Game(String data) throws ReadGameException {
        if (data == null) {
            throw new ReadGameException("Data is null.");
        }

        String[] parts = data.split("\\|", -1);

        if (parts.length != 4) {
            throw new ReadGameException("Expected 4 fields: " + data);
        }

        if (parts[0].isEmpty() || parts[2].isEmpty()) {
            throw new ReadGameException("Missing value: " + data);
        }

        try {
            this.name = parts[0];
            this.releaseYear = Integer.parseInt(parts[1]);
            this.genre = parts[2];
            this.hoursPlayed = Integer.parseInt(parts[3]);
        } catch (NumberFormatException e) {
            throw new ReadGameException("Invalid number: " + data);
        }

    }

    public String getName() {
        return name;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getGenre() {
        return genre;
    }

    public int getHoursPlayed() {
        return hoursPlayed;
    }

    public void setHoursPlayed(int hoursPlayed) {
        this.hoursPlayed = hoursPlayed;
    }

    @Override
    public String toString() {
        return name + "|" + releaseYear + "|" + genre + "|" + hoursPlayed;
    }

    @Override 
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Game)) {
            return false;
        }
        Game other = (Game) o;

        return releaseYear == other.releaseYear
                && Objects.equals(name, other.name)
                && Objects.equals(genre, other.genre);
    }
}

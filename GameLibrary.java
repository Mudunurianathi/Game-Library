import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Challenge Game Library
 * Manages a list of games and saves them from files.
 *
 * @author Anathi Mudunuri, CS 18000 
 *
 * @version October 23, 2026
 *
 */


public class GameLibrary {

    private ArrayList<Game> games;

    public GameLibrary() {
        games = new ArrayList<>();
    }

    public Game[] getGames() {
        return games.toArray(new Game[0]);
    }

    public void addGame(Game game) {
        games.add(game);
    }

    public void playGame(String name, int hours) {
        for (Game game : games) {
            if (game.getName().equals(name)) {
                game.setHoursPlayed(game.getHoursPlayed() + hours);
                return;
            }
        }
    }
    public void playGame(Game game, int hours) {
        for (Game current : games) {
            if (current.equals(game)) {
                current.setHoursPlayed(current.getHoursPlayed() + hours);
                return;
            }
        }
    }

    public int readLibraryText(String filename) throws ReadGameException {
        games.clear();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = br.readLine()) != null) {
                games.add(new Game(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return games.size();
    }

    public int writeLibraryText(String filename) {
        int count = 0;
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Game game : games) {
                bw.write(game.toString()); 
                bw.newLine();
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    public int readLibraryBin(String filename) throws ReadGameException {
        games.clear();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            while (true) {
                Object obj = ois.readObject();

                try {
                    games.add((Game) obj);
                } catch (ClassCastException e) {
                    throw new ReadGameException("Object is not a Game.");
                }
            }
        } catch (EOFException e) {
            return games.size();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return games.size();
    }

    public int writeLibraryBin(String filename) {
        int count = 0;
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            for (Game game : games) {
                oos.writeObject(game);
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }
}

import java.util.Scanner;

public class MusicCollectionDriver {

    private static Scanner prompt = new Scanner(System.in);

    public static void main(String[]args) {
        Song oneMoreNight = new Song("One More Night", "Maroon 5", "Pop");
        Song loveAway = new Song("Love Away", "Capital Cities", "Alternative");
        Song odeToSleep = new Song("Ode to Sleep", "Twenty One Pilots");
        Album overexposed = new Album("Overexposed", oneMoreNight);
        Album tidalWave = new Album("In a Tidal Wave of Mystery", loveAway);
        Album vessel = new Album("Vessel", odeToSleep);

        int input = 0;

        while (input != -1) {
            System.out.println("Music Collection:");
            System.out.println("[0]: " + overexposed.getTitle());
            System.out.println("[1]: " + tidalWave.getTitle());
            System.out.println("[2]: " + vessel.getTitle());
            System.out.print("Your choice (or -1 to quit): ");

            input = Integer.parseInt(prompt.nextLine());

            System.out.print("\n");

            if (input == 0) {
                albumOptions(overexposed);
            }
            if (input == 1) {
                albumOptions(tidalWave);
            }
            if (input == 2) {
                albumOptions(vessel);
            }
        }
    }

    public static void albumOptions(Album albumName) {

        int input = 0;

        while (input != -1) {

            System.out.print(albumName.toString());
            System.out.println("Choose an option:");
            System.out.println("[0] Get Favorite Track");
            System.out.println("[1] Change genre");
            System.out.println("[2] Add song");
            System.out.print("Your choice (or -1 to quit): ");

            input = Integer.parseInt(prompt.nextLine());
            System.out.print("\n");

            if (input == 0) {
                String albumTitle = albumName.getTitle();
                System.out.println("Favorite song on " + albumTitle + ":");
                System.out.println(albumName.getFavoriteTrack() + "\n");
            }
            if (input == 1) {
                System.out.println("New genre:");
                albumName.setGenre(prompt.nextLine());
                System.out.print("\n");
            }
            if (input == 2) {
                System.out.println("Song name?");
                String albumArtist = albumName.getFavoriteTrack().getArtist();
                String albumGenre = albumName.getFavoriteTrack().getGenre();
                String songName = prompt.nextLine();
                Song newSong = new Song(songName, albumArtist, albumGenre);
                System.out.print("\n");
                System.out.print("Is this your favorite song on the album? ");
                System.out.println("(true/false)");
                boolean favSong = Boolean.parseBoolean(prompt.nextLine());
                albumName.addSong(newSong, favSong);
                System.out.print("\n");
            input = 0;
        }
    }
    }
}
public class Album {

    private String title;
    private String artist;
    private String genre;
    private Song[] songs;
    private Song favoriteTrack;

    public Album(String title, Song firstTrack) {
        this.title = title;
        this.artist = firstTrack.getArtist();
        genre = firstTrack.getGenre();
        this.songs = new Song[1];
        songs[0] = firstTrack;
        this.favoriteTrack = firstTrack;
    }

    public String getTitle() {
        return title;
    }

    public Song getFavoriteTrack() {
        return favoriteTrack;
    }

    public void setGenre(String genre) {
        this.genre = genre;
        for (int trackNum = 0; trackNum < songs.length; trackNum++) {
            songs[trackNum].setGenre(genre);
        }
    }

    public void addSong(Song s, boolean isFavorite) {
        boolean doubleArray = false;
        if (isFavorite) {
            this.favoriteTrack = s;
        }
        for (int trackNum = 0; trackNum < songs.length; trackNum++) {
            if (songs[trackNum] == null) {
                songs[trackNum] = s;
                trackNum = songs.length;
                doubleArray = false;
            } else {
                doubleArray = true;
            }
        }
        if (doubleArray) {
            int intNewSize = songs.length * 2;
            Song[] tempSongs = new Song[intNewSize];
            for (int i = 0; i < songs.length; i++) {
                tempSongs[i] = songs[i];
            }
            songs = tempSongs;
            for (int trackNum = 0; trackNum < songs.length; trackNum++) {
                if (songs[trackNum] == null) {
                    songs[trackNum] = s;
                    trackNum = songs.length;
                }
            }
        }

    }

    public String toString() {
        String trackList = title + ", Tracks:\n";
        for (int i = 0; i < songs.length; i++) {
            if (songs[i] != null) {
            trackList = trackList + songs[i].toString() + "\n";
            }
        }
        return trackList;
    }

}
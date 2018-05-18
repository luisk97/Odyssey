/**
 * 
 */
package SongMgmt;

/**
 * @author Daniel Acuña Mora
 *
 */
public class Song {
	private String title, album, genere, artist, lyrics, path;

	public Song(String t, String a, String g) {
		this.setTitle(t);
		this.setAlbum(a);
		this.setGenere(g);

	}

	public Song() {

	}

	public void set(String c, String b) {
		if (c == "Nombre") {
			this.setTitle(b);
		} else if (c == "Album") {
			this.setAlbum(b);
		} else if (c == "Genero") {
			this.setGenere(b);
		} else if (c == "Artista") {
			this.setArtist(b);
		}
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the album
	 */
	public String getAlbum() {
		return album;
	}

	/**
	 * @param album
	 *            the album to set
	 */
	public void setAlbum(String album) {
		this.album = album;
	}

	/**
	 * @return the genere
	 */
	public String getGenere() {
		return genere;
	}

	/**
	 * @param genere
	 *            the genere to set
	 */
	public void setGenere(String genere) {
		this.genere = genere;
	}

	/**
	 * @return the artist
	 */
	public String getArtist() {
		return artist;
	}

	/**
	 * @param artist
	 *            the artist to set
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path
	 *            the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the lyrics
	 */
	public String getLyrics() {
		return lyrics;
	}

	/**
	 * @param lyrics
	 *            the lyrics to set
	 */
	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}

}

package techkids.com.android9_tkmp3_onclass.networks.jsonModels.jsonSearchSong;

/**
 * Created by tungthanh.1497 on 07/22/2017.
 */

public class SearchSongJSONModel {
    private String title;
    private String artist;
    private SourceSong source;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public SourceSong getSource() {
        return source;
    }

    public void setSource(SourceSong source) {
        this.source = source;
    }
}

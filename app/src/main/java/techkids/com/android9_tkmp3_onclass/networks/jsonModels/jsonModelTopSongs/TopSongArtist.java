package techkids.com.android9_tkmp3_onclass.networks.jsonModels.jsonModelTopSongs;

/**
 * Created by tungthanh.1497 on 07/20/2017.
 */

public class TopSongArtist {
    private String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public TopSongArtist(String label) {

        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}

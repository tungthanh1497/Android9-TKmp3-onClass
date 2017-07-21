package techkids.com.android9_tkmp3_onclass.networks.jsonModels.jsonModelTopSongs;

/**
 * Created by tungthanh.1497 on 07/20/2017.
 */

public class TopSongImage {
    private String label;
    private TopSongAttribute attributes;

    public TopSongImage(String label, TopSongAttribute attributes) {
        this.label = label;
        this.attributes = attributes;
    }

    public String getLabel() {

        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public TopSongAttribute getAttributes() {
        return attributes;
    }

    public void setAttributes(TopSongAttribute attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "TopSongImage{" +
                "label='" + label + '\'' +
                ", attributes=" + attributes +
                '}';
    }
}

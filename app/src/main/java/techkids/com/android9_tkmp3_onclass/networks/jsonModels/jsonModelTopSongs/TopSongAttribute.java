package techkids.com.android9_tkmp3_onclass.networks.jsonModels.jsonModelTopSongs;

/**
 * Created by tungthanh.1497 on 07/20/2017.
 */

public class TopSongAttribute {
    private int height;

    public TopSongAttribute(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return height+"";
    }
}

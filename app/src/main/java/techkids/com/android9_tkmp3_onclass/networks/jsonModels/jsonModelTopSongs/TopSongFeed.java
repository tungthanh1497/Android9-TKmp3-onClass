package techkids.com.android9_tkmp3_onclass.networks.jsonModels.jsonModelTopSongs;

import java.util.List;

/**
 * Created by tungthanh.1497 on 07/20/2017.
 */

public class TopSongFeed {
    List<TopSongJSONModel> entry;

    public List<TopSongJSONModel> getEntry() {
        return entry;
    }

    public void setEntry(List<TopSongJSONModel> entry) {
        this.entry = entry;
    }

    public TopSongFeed(List<TopSongJSONModel> entry) {
        this.entry = entry;
    }

}

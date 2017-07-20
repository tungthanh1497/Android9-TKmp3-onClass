package techkids.com.android9_tkmp3_onclass.networks.jsonModels;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tungthanh.1497 on 07/20/2017.
 */

public class Feed {
    List<TopSongJSONModel> entry;

    public List<TopSongJSONModel> getEntry() {
        return entry;
    }

    public void setEntry(List<TopSongJSONModel> entry) {
        this.entry = entry;
    }

    public Feed(List<TopSongJSONModel> entry) {
        this.entry = entry;
    }

}

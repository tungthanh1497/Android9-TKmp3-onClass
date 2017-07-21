package techkids.com.android9_tkmp3_onclass.networks.jsonModels.jsonModelTopSongs;

/**
 * Created by tungthanh.1497 on 07/20/2017.
 */

public class TopSongRespondModel {
    private TopSongFeed feed;

    public TopSongRespondModel(TopSongFeed feed) {
        this.feed = feed;
    }

    public TopSongFeed getFeed() {

        return feed;
    }

    public void setFeed(TopSongFeed feed) {
        this.feed = feed;
    }
}

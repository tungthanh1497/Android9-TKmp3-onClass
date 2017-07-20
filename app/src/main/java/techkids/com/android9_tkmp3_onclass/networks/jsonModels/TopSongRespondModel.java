package techkids.com.android9_tkmp3_onclass.networks.jsonModels;

/**
 * Created by tungthanh.1497 on 07/20/2017.
 */

public class TopSongRespondModel {
    private Feed feed;

    public TopSongRespondModel(Feed feed) {
        this.feed = feed;
    }

    public Feed getFeed() {

        return feed;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
    }
}

package techkids.com.android9_tkmp3_onclass.events;

import techkids.com.android9_tkmp3_onclass.databases.TopSongModel;

/**
 * Created by tungthanh.1497 on 07/28/2017.
 */

public class OnClickSong {
    private TopSongModel topSongModel;

    public OnClickSong(TopSongModel topSongModel) {
        this.topSongModel = topSongModel;
    }

    public TopSongModel getTopSongModel() {

        return topSongModel;
    }

    public void setTopSongModel(TopSongModel topSongModel) {
        this.topSongModel = topSongModel;
    }

    public OnClickSong() {

    }
}

package techkids.com.android9_tkmp3_onclass.events;

import techkids.com.android9_tkmp3_onclass.databases.MusicTypeModel;

/**
 * Created by tungthanh.1497 on 07/22/2017.
 */

public class OnClickMusicType {
    private MusicTypeModel musicTypeModel;

    public OnClickMusicType(MusicTypeModel musicTypeModel) {
        this.musicTypeModel = musicTypeModel;
    }

    public MusicTypeModel getMusicTypeModel() {

        return musicTypeModel;
    }

    public void setMusicTypeModel(MusicTypeModel musicTypeModel) {
        this.musicTypeModel = musicTypeModel;
    }

    public OnClickMusicType() {

    }
}

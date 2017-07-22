package techkids.com.android9_tkmp3_onclass.networks.jsonModels.jsonSearchSong;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tungthanh.1497 on 07/22/2017.
 */

public class SourceSong {
    @SerializedName("128") String linkSrc;

    public String getLinkSrc() {
        return linkSrc;
    }

    public void setLinkSrc(String linkSrc) {
        this.linkSrc = linkSrc;
    }
}

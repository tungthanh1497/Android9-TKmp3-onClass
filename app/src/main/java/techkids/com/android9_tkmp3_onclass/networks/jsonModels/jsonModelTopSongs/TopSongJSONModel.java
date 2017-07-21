package techkids.com.android9_tkmp3_onclass.networks.jsonModels.jsonModelTopSongs;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tungthanh.1497 on 07/20/2017.
 */

public class TopSongJSONModel {
    @SerializedName("im:name")
    private TopSongName songName;
    @SerializedName("im:image")
    private List<TopSongImage> songImage;
    @SerializedName("im:artist")
    private TopSongArtist songArtist;

    @Override
    public String toString() {
        return "TopSongJSONModel{" +
                "songName=" + songName +
                ", songImage=" + songImage +
                ", songArtist=" + songArtist +
                '}';
    }

    public TopSongJSONModel(TopSongName songName, List<TopSongImage> songImage, TopSongArtist songArtist) {
        this.songName = songName;
        this.songImage = songImage;
        this.songArtist = songArtist;
    }

    public List<TopSongImage> getSongImage() {

        return songImage;
    }

    public void setSongImage(List<TopSongImage> songImage) {
        this.songImage = songImage;
    }

    public TopSongName getSongName() {
        return songName;
    }

    public void setSongName(TopSongName songName) {
        this.songName = songName;
    }


    public TopSongArtist getSongArtist() {
        return songArtist;
    }

    public void setSongArtist(TopSongArtist songArtist) {
        this.songArtist = songArtist;
    }

}

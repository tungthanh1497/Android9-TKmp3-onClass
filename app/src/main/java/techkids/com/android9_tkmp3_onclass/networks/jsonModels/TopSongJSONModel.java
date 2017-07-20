package techkids.com.android9_tkmp3_onclass.networks.jsonModels;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tungthanh.1497 on 07/20/2017.
 */

public class TopSongJSONModel {
    @SerializedName("im:name")
    private Name songName;
    @SerializedName("im:image")
    private List<Image> songImage;
    @SerializedName("im:artist")
    private Artist songArtist;

    @Override
    public String toString() {
        return "TopSongJSONModel{" +
                "songName=" + songName +
                ", songImage=" + songImage +
                ", songArtist=" + songArtist +
                '}';
    }

    public TopSongJSONModel(Name songName, List<Image> songImage, Artist songArtist) {
        this.songName = songName;
        this.songImage = songImage;
        this.songArtist = songArtist;
    }

    public List<Image> getSongImage() {

        return songImage;
    }

    public void setSongImage(List<Image> songImage) {
        this.songImage = songImage;
    }

    public Name getSongName() {
        return songName;
    }

    public void setSongName(Name songName) {
        this.songName = songName;
    }


    public Artist getSongArtist() {
        return songArtist;
    }

    public void setSongArtist(Artist songArtist) {
        this.songArtist = songArtist;
    }

}

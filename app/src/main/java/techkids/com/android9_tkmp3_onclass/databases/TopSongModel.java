package techkids.com.android9_tkmp3_onclass.databases;

/**
 * Created by tungthanh.1497 on 07/21/2017.
 */

public class TopSongModel {
    private String name;
    private String image;
    private String author;
    private String linkSrc;

    public String getLinkSrc() {
        return linkSrc;
    }

    public void setLinkSrc(String linkSrc) {
        this.linkSrc = linkSrc;
    }

    public TopSongModel() {
    }

    public TopSongModel(String name, String image, String author) {
        this.name = name;
        this.image = image;
        this.author = author;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}

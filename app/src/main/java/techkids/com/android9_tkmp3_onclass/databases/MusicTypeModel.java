package techkids.com.android9_tkmp3_onclass.databases;

/**
 * Created by tungthanh.1497 on 07/19/2017.
 */

public class MusicTypeModel {
    private String id;
    private String translation_key;
    private int imageID;

    public MusicTypeModel() {
    }

    public MusicTypeModel(String id, String translation_key, int imageID) {
        this.id = id;
        this.translation_key = translation_key;
        this.imageID = imageID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTranslation_key() {
        return translation_key;
    }

    public void setTranslation_key(String translation_key) {
        this.translation_key = translation_key;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }
}

package techkids.com.android9_tkmp3_onclass.networks.jsonModels.jsonModelMusicTypes;

/**
 * Created by tungthanh.1497 on 07/15/2017.
 */

public class MusicTypesJSONModel {
    private String id;
    private String translation_key;

    public String getId() {
        return id;
    }

    public String getTranslation_key() {
        return translation_key;
    }

    public MusicTypesJSONModel(String id, String translation_key) {
        this.id = id;
        this.translation_key = translation_key;
    }

    @Override
    public String toString() {
        return "id: " + getId() + " - translation_key:" + getTranslation_key() + "\n";
    }
}

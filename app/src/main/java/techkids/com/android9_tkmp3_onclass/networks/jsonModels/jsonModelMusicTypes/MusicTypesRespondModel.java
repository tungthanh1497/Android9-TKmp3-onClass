package techkids.com.android9_tkmp3_onclass.networks.jsonModels.jsonModelMusicTypes;

import java.util.List;

/**
 * Created by tungthanh.1497 on 07/15/2017.
 */

public class MusicTypesRespondModel {
    List<MusicTypesJSONModel> subgenres;

    public List<MusicTypesJSONModel> getSubgenres() {
        return subgenres;
    }

    public MusicTypesRespondModel(List<MusicTypesJSONModel> subgenres) {
        this.subgenres = subgenres;
    }
}

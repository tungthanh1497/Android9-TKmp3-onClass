package techkids.com.android9_tkmp3_onclass.networks.jsonModels.jsonSearchSong;

import java.util.List;

/**
 * Created by tungthanh.1497 on 07/22/2017.
 */

public class SearchSongRespondModel {
    private List<SearchSongJSONModel> docs;

    public List<SearchSongJSONModel> getDocs() {
        return docs;
    }

    public void setDocs(List<SearchSongJSONModel> docs) {
        this.docs = docs;
    }
}

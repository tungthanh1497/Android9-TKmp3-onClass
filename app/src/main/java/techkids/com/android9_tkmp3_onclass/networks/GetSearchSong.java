package techkids.com.android9_tkmp3_onclass.networks;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import techkids.com.android9_tkmp3_onclass.networks.jsonModels.jsonModelTopSongs.TopSongRespondModel;
import techkids.com.android9_tkmp3_onclass.networks.jsonModels.jsonSearchSong.SearchSongRespondModel;

/**
 * Created by tungthanh.1497 on 07/22/2017.
 */

public interface GetSearchSong {
    @GET("http://api.mp3.zing.vn/api/mobile/search/song")
    Call<SearchSongRespondModel> getSearchSongs(@Query("requestdata") String data);
}

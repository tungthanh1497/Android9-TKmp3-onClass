package techkids.com.android9_tkmp3_onclass.networks;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import techkids.com.android9_tkmp3_onclass.networks.jsonModels.jsonModelTopSongs.TopSongRespondModel;

/**
 * Created by tungthanh.1497 on 07/20/2017.
 */

public interface GetTopSongs {
    @GET("https://itunes.apple.com/us/rss/topsongs/limit=50/genre={user}/explicit=true/json")
    Call<TopSongRespondModel> getTopSongs(@Path("user") String user);
}

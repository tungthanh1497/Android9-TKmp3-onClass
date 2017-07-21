package techkids.com.android9_tkmp3_onclass.networks;

import retrofit2.Call;
import retrofit2.http.GET;
import techkids.com.android9_tkmp3_onclass.networks.jsonModels.jsonModelMusicTypes.MusicTypesRespondModel;

/**
 * Created by tungthanh.1497 on 07/15/2017.
 */

public interface GetMusicTypes {
    @GET("api")
    Call<MusicTypesRespondModel> getMusicTypes();
}

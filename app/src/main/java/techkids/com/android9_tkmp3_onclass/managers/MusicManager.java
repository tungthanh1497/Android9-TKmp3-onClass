package techkids.com.android9_tkmp3_onclass.managers;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import techkids.com.android9_tkmp3_onclass.FuzzyMatch;
import techkids.com.android9_tkmp3_onclass.databases.TopSongModel;
import techkids.com.android9_tkmp3_onclass.networks.GetSearchSong;
import techkids.com.android9_tkmp3_onclass.networks.RetrofitFactory;
import techkids.com.android9_tkmp3_onclass.networks.jsonModels.jsonSearchSong.SearchSongJSONModel;
import techkids.com.android9_tkmp3_onclass.networks.jsonModels.jsonSearchSong.SearchSongRespondModel;

/**
 * Created by tungthanh.1497 on 07/22/2017.
 */

public class MusicManager {
    static String TAG = MusicManager.class.toString();

    public static void loadSearchSong(final TopSongModel topSongModel, final Context context) {
        GetSearchSong getSearchSong = RetrofitFactory.getInstance().create(GetSearchSong.class);
        getSearchSong.getSearchSongs("{\"q\":\""
                + topSongModel.getName().toLowerCase() + " " + topSongModel.getAuthor().toLowerCase()
                + "\", \"sort\":\"hot\", \"start\":\"0\", \"length\":\"10\"}")
                .enqueue(new Callback<SearchSongRespondModel>() {
                    @Override
                    public void onResponse(Call<SearchSongRespondModel> call, Response<SearchSongRespondModel> response) {
                        if (response.body().getDocs().size() <= 0) {
                            Toast.makeText(context, "Not found", Toast.LENGTH_SHORT).show();
                        } else {
                            List<Integer> ratioList = new ArrayList<Integer>();

                            List<SearchSongJSONModel> docs = response.body().getDocs();
                            Log.d(TAG, "onResponse: " + topSongModel.getName().toLowerCase() + " " + topSongModel.getAuthor().toLowerCase());
                            for (SearchSongJSONModel searchSongJSONModel : docs) {
                                int ratio = FuzzyMatch.getRatio(topSongModel.getName().toLowerCase() + " " + topSongModel.getAuthor().toLowerCase()
                                        , searchSongJSONModel.getTitle().toLowerCase() + " " + searchSongJSONModel.getArtist().toLowerCase(), false);
                                ratioList.add(ratio);
                                Log.d(TAG, "\n"
                                        + "Name: " + searchSongJSONModel.getTitle().toLowerCase() + " " + searchSongJSONModel.getArtist().toLowerCase()
                                        + "\n"
                                        + "Ratio: " + ratio);
                            }


                            for (int i = 0; i < ratioList.size(); i++) {
                                if (Collections.max(ratioList) == ratioList.get(i)) {
                                    String linkResource = docs.get(i).getSource().getLinkSrc();
                                    topSongModel.setLinkSrc(linkResource);
                                    Log.d(TAG, "\n"
                                            + docs.get(i).getTitle());
                                    break;
                                }
                            }

                            Toast.makeText(context, topSongModel.getLinkSrc(), Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<SearchSongRespondModel> call, Throwable t) {
                        Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

    }
}

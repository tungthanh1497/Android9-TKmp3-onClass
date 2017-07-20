package techkids.com.android9_tkmp3_onclass.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import techkids.com.android9_tkmp3_onclass.R;
import techkids.com.android9_tkmp3_onclass.adapters.TopSongsAdapter;
import techkids.com.android9_tkmp3_onclass.managers.ScreenManager;
import techkids.com.android9_tkmp3_onclass.networks.GetTopSongs;
import techkids.com.android9_tkmp3_onclass.networks.RetrofitFactory;
import techkids.com.android9_tkmp3_onclass.networks.jsonModels.Image;
import techkids.com.android9_tkmp3_onclass.networks.jsonModels.TopSongJSONModel;
import techkids.com.android9_tkmp3_onclass.networks.jsonModels.TopSongRespondModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopSongsFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.iv_background_cover)
    ImageView ivBackgroundCover;
    @BindView(R.id.tv_music_type)
    TextView tvMusicType;
    @BindView(R.id.tv_num_of_songs)
    TextView tvNumOfSongs;

    private static final String TAG = MusticTypesFragment.class.toString();
    @BindView(R.id.rv_top_songs)
    RecyclerView rvTopSongs;
    List<TopSongJSONModel> topSongJSONModelList = new ArrayList<>();
    TopSongsAdapter topSongsAdapter;

    public TopSongsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top_songs, container, false);
        setupUI(view);
        loadDatas();
        return view;
    }

    private void loadDatas() {
        final GetTopSongs getTopSongs = RetrofitFactory.getInstance().create(GetTopSongs.class);
        getTopSongs.getTopSongs(ScreenManager.musicTypeClicked.getId()).enqueue(new Callback<TopSongRespondModel>() {
            @Override
            public void onResponse(Call<TopSongRespondModel> call, Response<TopSongRespondModel> response) {
                List<TopSongJSONModel> entry = response.body().getFeed().getEntry();
                Log.d(TAG, "onResponseEntry: " + entry);
                for (TopSongJSONModel topSongJSONModel : entry) {
                    List<Image> images = new ArrayList<>();
                    for (Image image : topSongJSONModel.getSongImage()) {
                        if (image.getAttributes().getHeight() == 170) {
                            images.add(image);
                        }
                    }
                    topSongJSONModel.setSongImage(images);
                    topSongJSONModelList.add(topSongJSONModel);
//                    Log.d(TAG, "onResponse: " + subgenres.get(i));
                }
                tvNumOfSongs.setText(topSongJSONModelList.size() + " songs");
                topSongsAdapter.notifyDataSetChanged();
                Log.d(TAG, "\nOnResponseList:\n" + topSongJSONModelList);
            }

            @Override
            public void onFailure(Call<TopSongRespondModel> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.toString());
                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupUI(View view) {
        ButterKnife.bind(this, view);
        topSongsAdapter = new TopSongsAdapter(topSongJSONModelList, getContext());
        rvTopSongs.setAdapter(topSongsAdapter);
        ivBackgroundCover.setImageResource(ScreenManager.musicTypeClicked.getImageID());
        tvMusicType.setText(ScreenManager.musicTypeClicked.getTranslation_key().toUpperCase());
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvTopSongs.setLayoutManager(manager);
        topSongsAdapter.setOnItemClick(this);
    }

    @Override
    public void onClick(View v) {
        TopSongJSONModel topSongJSONModel = (TopSongJSONModel) v.getTag();
//        ScreenManager.openFragment(getActivity().getSupportFragmentManager(), new DownloadFragment(), R.id.rl_layout_container, this);

    }

}

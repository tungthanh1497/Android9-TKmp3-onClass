package techkids.com.android9_tkmp3_onclass.fragments;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import techkids.com.android9_tkmp3_onclass.R;
import techkids.com.android9_tkmp3_onclass.adapters.TopSongsAdapter;
import techkids.com.android9_tkmp3_onclass.databases.MusicTypeModel;
import techkids.com.android9_tkmp3_onclass.databases.TopSongModel;
import techkids.com.android9_tkmp3_onclass.events.OnClickMusicType;
import techkids.com.android9_tkmp3_onclass.events.OnClickSong;
import techkids.com.android9_tkmp3_onclass.managers.MusicManager;
import techkids.com.android9_tkmp3_onclass.managers.ScreenManager;
import techkids.com.android9_tkmp3_onclass.networks.GetTopSongs;
import techkids.com.android9_tkmp3_onclass.networks.RetrofitFactory;
import techkids.com.android9_tkmp3_onclass.networks.jsonModels.jsonModelTopSongs.TopSongImage;
import techkids.com.android9_tkmp3_onclass.networks.jsonModels.jsonModelTopSongs.TopSongJSONModel;
import techkids.com.android9_tkmp3_onclass.networks.jsonModels.jsonModelTopSongs.TopSongRespondModel;
import techkids.com.android9_tkmp3_onclass.services.PlayMusicNotification;

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
    @BindView(R.id.iv_back_button)
    ImageView ivBackButton;
    @BindView(R.id.tb_top_songs)
    Toolbar tbTopSongs;

    private static final String TAG = MusticTypesFragment.class.toString();
    @BindView(R.id.rv_top_songs)
    RecyclerView rvTopSongs;
    List<TopSongModel> topSongModelList = new ArrayList<>();
    TopSongsAdapter topSongsAdapter;
    MusicTypeModel musicTypeModel;

    public TopSongsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top_songs_old, container, false);
        setupUI(view);
        setupListener();
        loadDatas();
        return view;
    }

    private void setupListener() {
        ivBackButton.setOnClickListener(this);
    }

    private void loadDatas() {
        final GetTopSongs getTopSongs = RetrofitFactory.getInstance().create(GetTopSongs.class);
//        getTopSongs.getTopSongs(ScreenManager.musicTypeClicked.getId()).enqueue(new Callback<TopSongRespondModel>() {
        getTopSongs.getTopSongs(musicTypeModel.getId()).enqueue(new Callback<TopSongRespondModel>() {
            @Override
            public void onResponse(Call<TopSongRespondModel> call, Response<TopSongRespondModel> response) {
                List<TopSongJSONModel> entry = response.body().getFeed().getEntry();
                Log.d(TAG, "onResponseEntry: " + entry);
                for (TopSongJSONModel topSongJSONModel : entry) {
                    TopSongModel topSongModel = new TopSongModel();
                    topSongModel.setName(topSongJSONModel.getSongName().getLabel());
                    topSongModel.setAuthor(topSongJSONModel.getSongArtist().getLabel());
                    List<TopSongImage> images = topSongJSONModel.getSongImage();
                    for (TopSongImage image : images) {
                        if (image.getAttributes().getHeight() == 170) {
                            topSongModel.setImage(image.getLabel());
                        }
                    }
                    topSongModelList.add(topSongModel);
//                    Log.d(TAG, "onResponse: " + subgenres.get(i));
                }
                tvNumOfSongs.setText(topSongModelList.size() + " songs");
                topSongsAdapter.notifyDataSetChanged();
//                Log.d(TAG, "\nOnResponseList:\n" + topSongModelList);
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
        tbTopSongs.setElevation(10);

        EventBus.getDefault().register(this);


        topSongsAdapter = new TopSongsAdapter(topSongModelList, getContext());
        rvTopSongs.setAdapter(topSongsAdapter);

//        ((ActionBarActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        ((ActionBarActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
//        mActionBar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_back));


        ivBackgroundCover.setImageResource(musicTypeModel.getImageID());
        tvMusicType.setText(musicTypeModel.getTranslation_key().toUpperCase());
//        ivBackgroundCover.setImageResource(ScreenManager.musicTypeClicked.getImageID());
//        tvMusicType.setText(ScreenManager.musicTypeClicked.getTranslation_key().toUpperCase());
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        rvTopSongs.addItemDecoration(dividerItemDecoration);

        rvTopSongs.setLayoutManager(manager);
        topSongsAdapter.setOnItemClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RelativeLayout rlMiniPlayer = (RelativeLayout) getActivity().findViewById(R.id.rl_mini_player);
                SeekBar sbSeekbar = (SeekBar) rlMiniPlayer.findViewById(R.id.sb_seekbar);
                ImageView ivAvaSong = (ImageView) rlMiniPlayer.findViewById(R.id.iv_ava_song);
                TextView tvNameSong = (TextView) rlMiniPlayer.findViewById(R.id.tv_name_song);
                TextView tvAuthorSong = (TextView) rlMiniPlayer.findViewById(R.id.tv_author_song);
                FloatingActionButton fabPlayButton = (FloatingActionButton) rlMiniPlayer.findViewById(R.id.fab_play_button);

                TopSongModel topSongModel = (TopSongModel) v.getTag();


                EventBus.getDefault().postSticky(new OnClickSong(topSongModel));
                PlayMusicNotification.setupNotification(getContext(), topSongModel);

                MusicManager.loadSearchSong(topSongModel, getContext(), sbSeekbar, fabPlayButton);

                sbSeekbar.setPadding(0, 0, 0, 0);
//        sbSeekbar.getThumb().mutate().setAlpha(0);
                sbSeekbar.setProgress(0);

                Picasso.with(getContext()).load(topSongModel.getImage()).transform(new CropCircleTransformation()).into(ivAvaSong);
                tvNameSong.setText(topSongModel.getName());
                tvAuthorSong.setText(topSongModel.getAuthor());
                rlMiniPlayer.setVisibility(View.VISIBLE);


//        ScreenManager.openFragment(getActivity().getSupportFragmentManager(), new DownloadFragment(), R.id.rl_layout_container, this);

            }
        });
    }

    @Subscribe(sticky = true)
    public void onReceivedMusicType(OnClickMusicType onClickMusicType) {
        musicTypeModel = onClickMusicType.getMusicTypeModel();
    }

    void onBackPressed() {
        getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    @Override
    public void onClick(View v) {
        if (v == ivBackButton) {
            tbTopSongs.setElevation(0);
            onBackPressed();
            return;
        }


    }

}

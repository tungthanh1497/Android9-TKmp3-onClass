package techkids.com.android9_tkmp3_onclass.fragments;


import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.BlurTransformation;
import techkids.com.android9_tkmp3_onclass.MainActivity;
import techkids.com.android9_tkmp3_onclass.R;
import techkids.com.android9_tkmp3_onclass.databases.TopSongModel;
import techkids.com.android9_tkmp3_onclass.events.OnClickSong;
import techkids.com.android9_tkmp3_onclass.managers.MusicManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainPlayerFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.tv_name_song)
    TextView tvNameSong;
    @BindView(R.id.tv_artist_song)
    TextView tvArtistSong;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.iv_blur_image)
    ImageView ivBlurImage;
    @BindView(R.id.iv_content_image)
    ImageView ivContentImage;
    @BindView(R.id.sb_seekbar_main_player)
    SeekBar sbMainPlayer;
    @BindView(R.id.fab_play_button)
    FloatingActionButton fapPlayButton;
    @BindView(R.id.tv_current_time)
    TextView tvCurrentTime;
    @BindView(R.id.tv_end_time)
    TextView tvEndTime;

    TopSongModel topSongModel;


    public MainPlayerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_player, container, false);
        setupUI(view);
        setupListener();
//        loadDatas();
        return view;
    }

    private void setupListener() {
        tvCancel.setOnClickListener(this);
        fapPlayButton.setOnClickListener(this);
    }

    private void setupUI(View view) {
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);

        tvNameSong.setText(topSongModel.getName());
        tvArtistSong.setText(topSongModel.getAuthor());
        Picasso.with(getContext()).load(topSongModel.getImage()).into(ivContentImage);
        Picasso.with(getContext()).load(topSongModel.getImage()).transform(new BlurTransformation(getContext())).into(ivBlurImage);
//        int totalTime = MusicManager.mediaPlayer.getDuration() / 1000;
//        int minutes = totalTime / 60;
//        int seconds = totalTime % 60;
//        if(minutes<10){
//            tvEndTime.setText("0" + minutes);
//        }else {
//            tvEndTime.setText(minutes);
//        }
//        if(seconds<10){
//            tvEndTime.setText(tvEndTime.getText()+":0"+seconds);
//        }else {
//            tvEndTime.setText(tvEndTime.getText()+":"+seconds);
//        }
//        tvEndTime.setText((minutes < 10) ? ("0" + minutes) : ("" + minutes));
//        tvEndTime.setText((seconds < 10) ? (tvEndTime.getText() + ":0" + seconds) : (tvEndTime.getText() + ":" + seconds));


        MusicManager.updateSongRealtime(sbMainPlayer, fapPlayButton, tvCurrentTime, tvEndTime);
    }

    @Subscribe(sticky = true)
    public void onReceivedMusicType(OnClickSong onClickSong) {
        topSongModel = onClickSong.getTopSongModel();
    }

    @Override
    public void onClick(View v) {
        if (v == tvCancel) {
            onBackPressed();
        }
        if (v == fapPlayButton) {
            MusicManager.playPauseMusic();
        }
    }

    void onBackPressed() {
        getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}

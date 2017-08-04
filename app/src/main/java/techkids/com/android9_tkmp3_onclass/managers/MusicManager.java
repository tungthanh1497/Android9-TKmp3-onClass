package techkids.com.android9_tkmp3_onclass.managers;

import android.content.Context;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hybridmediaplayer.HybridMediaPlayer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import techkids.com.android9_tkmp3_onclass.services.PlayMusicNotification;
import techkids.com.android9_tkmp3_onclass.utils.FuzzyMatch;
import techkids.com.android9_tkmp3_onclass.R;
import techkids.com.android9_tkmp3_onclass.databases.TopSongModel;
import techkids.com.android9_tkmp3_onclass.networks.GetSearchSong;
import techkids.com.android9_tkmp3_onclass.networks.RetrofitFactory;
import techkids.com.android9_tkmp3_onclass.networks.jsonModels.jsonSearchSong.SearchSongJSONModel;
import techkids.com.android9_tkmp3_onclass.networks.jsonModels.jsonSearchSong.SearchSongRespondModel;
import techkids.com.android9_tkmp3_onclass.utils.Utils;

/**
 * Created by tungthanh.1497 on 07/22/2017.
 */

public class MusicManager {
    static String TAG = MusicManager.class.toString();
    public static HybridMediaPlayer mediaPlayer;

    public static void loadSearchSong(final TopSongModel topSongModel, final Context context
            , final SeekBar sbSeekbar, final FloatingActionButton fabPlayButton) {
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
                            setupMusic(topSongModel, context, sbSeekbar, fabPlayButton);
                            Toast.makeText(context, topSongModel.getLinkSrc(), Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<SearchSongRespondModel> call, Throwable t) {
                        Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

    }


    public static void setupMusic(TopSongModel topSongModel, final Context context, final SeekBar sbSeekbar, final FloatingActionButton fabPlayButton) {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        mediaPlayer = HybridMediaPlayer.getInstance(context);
        mediaPlayer.setDataSource(topSongModel.getLinkSrc());
        mediaPlayer.prepare();
        mediaPlayer.setOnPreparedListener(new HybridMediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(HybridMediaPlayer hybridMediaPlayer) {
                Log.d(TAG, "onPrepared-duration: " + mediaPlayer.getDuration());
                mediaPlayer.play();
                updateSongRealtime(sbSeekbar, fabPlayButton);
            }
        });
    }


    static boolean isChangeing = false;

    public static void updateSongRealtime(final SeekBar sbSeekbar, final FloatingActionButton fabPlayButton) {
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                sbSeekbar.setMax(mediaPlayer.getDuration());
                if (!isChangeing) {
                    sbSeekbar.setProgress(mediaPlayer.getCurrentPosition());
                }
                handler.postDelayed(this, 100);
                if (mediaPlayer.isPlaying()) {
                    fabPlayButton.setImageResource(R.drawable.exo_controls_pause);
                } else {
                    fabPlayButton.setImageResource(R.drawable.exo_controls_play);
                }
            }
        };
        runnable.run();

        final int[] currentPosition = new int[1];
        sbSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentPosition[0] = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isChangeing = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(currentPosition[0]);
                isChangeing = false;
            }
        });

    }

    public static void updateSongRealtime(final SeekBar sbSeekbar, final FloatingActionButton fabPlayButton, final TextView tvCurrentTime, final TextView tvEndTime) {
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                sbSeekbar.setMax(mediaPlayer.getDuration());
                if (!isChangeing) {
                    sbSeekbar.setProgress(mediaPlayer.getCurrentPosition());
//                    int totalTime = mediaPlayer.getDuration()/1000;
//                    int minutes = totalTime / 60;
//                    int seconds = totalTime % 60;
//                    if(minutes<10){
//                        tvEndTime.setText("0" + minutes);
//                    }else {
//                        tvEndTime.setText(minutes);
//                    }
//                    if(seconds<10){
//                        tvEndTime.setText(tvEndTime.getText()+":0"+seconds);
//                    }else {
//                        tvEndTime.setText(tvEndTime.getText()+":"+seconds);
//                    }


//                    totalTime = mediaPlayer.getCurrentPosition()/1000;
//                    minutes = totalTime/60;
//                    seconds = totalTime%60;
//                    if(minutes<10){
//                        tvCurrentTime.setText("0" + minutes);
//                    }else {
//                        tvCurrentTime.setText(minutes);
//                    }
//                    if(seconds<10){
//                        tvCurrentTime.setText(tvCurrentTime.getText()+":0"+seconds);
//                    }else {
//                        tvCurrentTime.setText(tvCurrentTime.getText()+":"+seconds);
//                    }

                }
                handler.postDelayed(this, 100);
                if (mediaPlayer.isPlaying()) {
                    fabPlayButton.setImageResource(R.drawable.exo_controls_pause);
                } else {
                    fabPlayButton.setImageResource(R.drawable.exo_controls_play);
                }
                PlayMusicNotification.updateNotification(mediaPlayer.isPlaying());

                tvEndTime.setText(Utils.converTime(mediaPlayer.getDuration()));
                tvCurrentTime.setText(Utils.converTime(mediaPlayer.getCurrentPosition()));

            }
        };
        runnable.run();

        final int[] currentPosition = new int[1];
        sbSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentPosition[0] = progress;
                tvCurrentTime.setText(Utils.converTime(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isChangeing = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(currentPosition[0]);
                isChangeing = false;
            }
        });

    }

    public static void playPauseMusic() {
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            } else {
                mediaPlayer.play();
            }
        }
    }
}

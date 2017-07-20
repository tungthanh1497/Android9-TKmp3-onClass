package techkids.com.android9_tkmp3_onclass.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import techkids.com.android9_tkmp3_onclass.R;
import techkids.com.android9_tkmp3_onclass.networks.jsonModels.TopSongJSONModel;

/**
 * Created by tungthanh.1497 on 07/20/2017.
 */

public class TopSongsAdapter extends RecyclerView.Adapter<TopSongsAdapter.TopSongViewHolder> {

    private List<TopSongJSONModel> musicTypeModelList = new ArrayList<>();
    private Context context;
    private View view;
    private View.OnClickListener onClickListener;


    public void setOnItemClick(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public TopSongsAdapter(List<TopSongJSONModel> musicTypeModelList, Context context) {
        this.musicTypeModelList = musicTypeModelList;
        this.context = context;
    }

    @Override
    public TopSongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_list_songs, parent, false);
        view.setOnClickListener(onClickListener);
        return new TopSongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TopSongViewHolder holder, int position) {
        holder.setDatas(musicTypeModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return musicTypeModelList.size();
    }

    public class TopSongViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_ava_song;
        TextView tv_name_song;
        TextView tv_author_song;

        public TopSongViewHolder(View itemView) {
            super(itemView);
            iv_ava_song = (ImageView) itemView.findViewById(R.id.iv_ava_song);
            tv_name_song = (TextView) itemView.findViewById(R.id.tv_name_song);
            tv_author_song = (TextView) itemView.findViewById(R.id.tv_author_song);
            view = itemView;
        }

        public void setDatas(TopSongJSONModel topSongJSONModel) {
            if (topSongJSONModel == null)
                return;

//            ivMusicTypes.setImageResource(musicTypeModel.getImageID());
            Picasso.with(context).load(topSongJSONModel.getSongImage().get(0).getLabel()).into(iv_ava_song);
            tv_name_song.setText(topSongJSONModel.getSongName().getLabel());
            tv_author_song.setText(topSongJSONModel.getSongArtist().getLabel());
            view.setTag(topSongJSONModel);
        }
    }
}

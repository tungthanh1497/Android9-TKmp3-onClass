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
import java.util.Comparator;
import java.util.List;

import techkids.com.android9_tkmp3_onclass.R;
import techkids.com.android9_tkmp3_onclass.databases.MusicTypeModel;

/**
 * Created by tungthanh.1497 on 07/19/2017.
 */

public class MusicTypesAdapter extends RecyclerView.Adapter<MusicTypesAdapter.MusicTypesViewHolder> {

    private List<MusicTypeModel> musicTypeModelList = new ArrayList<>();
    private Context context;
    private View view;
    private View.OnClickListener onClickListener;

    public void setOnItemClick(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }


    public MusicTypesAdapter(List<MusicTypeModel> musicTypeModelList, Context context) {
        this.musicTypeModelList = musicTypeModelList;
        this.context = context;
    }


    //2. create view
    @Override
    public MusicTypesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_list_music_types, parent, false);
        view.setOnClickListener(onClickListener);
        return new MusicTypesViewHolder(view);
    }

    //when scroll the screen
    @Override
    public void onBindViewHolder(MusicTypesViewHolder holder, int position) {
        holder.setDatas(musicTypeModelList.get(position));
    }


    //1
    @Override
    public int getItemCount() {
        return musicTypeModelList.size();
    }

    public class MusicTypesViewHolder extends RecyclerView.ViewHolder {
        ImageView ivMusicTypes;
        TextView tvMusicTypes;

        public MusicTypesViewHolder(View itemView) {
            super(itemView);
            ivMusicTypes = (ImageView) itemView.findViewById(R.id.iv_item_music_types);
            tvMusicTypes = (TextView) itemView.findViewById(R.id.tv_item_music_types);
            view = itemView;
        }

        public void setDatas(MusicTypeModel musicTypeModel) {
            if (musicTypeModel == null)
                return;

//            ivMusicTypes.setImageResource(musicTypeModel.getImageID());
            Picasso.with(context).load(musicTypeModel.getImageID()).into(ivMusicTypes);
            tvMusicTypes.setText(musicTypeModel.getTranslation_key().toUpperCase());
            view.setTag(musicTypeModel);
        }
    }

}

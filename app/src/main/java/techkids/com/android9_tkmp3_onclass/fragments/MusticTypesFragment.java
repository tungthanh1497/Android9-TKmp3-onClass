package techkids.com.android9_tkmp3_onclass.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import techkids.com.android9_tkmp3_onclass.R;
import techkids.com.android9_tkmp3_onclass.adapters.MusicTypesAdapter;
import techkids.com.android9_tkmp3_onclass.databases.MusicTypeModel;
import techkids.com.android9_tkmp3_onclass.events.OnClickMusicType;
import techkids.com.android9_tkmp3_onclass.managers.ScreenManager;
import techkids.com.android9_tkmp3_onclass.networks.GetMusicTypes;
import techkids.com.android9_tkmp3_onclass.networks.RetrofitFactory;
import techkids.com.android9_tkmp3_onclass.networks.jsonModels.jsonModelMusicTypes.MusicTypesJSONModel;
import techkids.com.android9_tkmp3_onclass.networks.jsonModels.jsonModelMusicTypes.MusicTypesRespondModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class MusticTypesFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = MusticTypesFragment.class.toString();
    @BindView(R.id.rv_music_types)
    RecyclerView rvMusicTypes;
    List<MusicTypeModel> musicTypeModelList = new ArrayList<>();
    MusicTypesAdapter musicTypesAdapter;

    public MusticTypesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music_types_fragment, container, false);

        // activities: ButterKnife.bind(this);
        // fragments: ButterKnife.bind(this, view);
//        ButterKnife.bind(this, view);

        setupUI(view);
        loadDatas();
        return view;
    }

    private void loadDatas() {
        final GetMusicTypes getMusicTypes = RetrofitFactory.getInstance().create(GetMusicTypes.class);
        getMusicTypes.getMusicTypes().enqueue(new Callback<MusicTypesRespondModel>() {
            @Override
            public void onResponse(Call<MusicTypesRespondModel> call, Response<MusicTypesRespondModel> response) {
                List<MusicTypesJSONModel> subgenres = response.body().getSubgenres();
                for (int i = 0; i < subgenres.size(); i++) {
                    MusicTypesJSONModel musicTypesJSONModel = subgenres.get(i);
                    MusicTypeModel musicTypeModel = new MusicTypeModel();
                    musicTypeModel.setId(musicTypesJSONModel.getId());
                    musicTypeModel.setTranslation_key(musicTypesJSONModel.getTranslation_key());
                    musicTypeModel.setImageID(getContext()
                            .getResources()
                            .getIdentifier("genre_x2_" + musicTypesJSONModel.getId(), "raw", getContext().getPackageName()));
                    musicTypeModelList.add(musicTypeModel);
//                    Log.d(TAG, "onResponse: " + subgenres.get(i));
                }
                musicTypesAdapter.notifyDataSetChanged();
//                Log.d(TAG, "\nOnResponse:\n"+response.body().getSubgenres());
            }

            @Override
            public void onFailure(Call<MusicTypesRespondModel> call, Throwable t) {
                Toast.makeText(getContext(), "Where is my internet, bitch???", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupUI(View view) {
        ButterKnife.bind(this, view);
        musicTypesAdapter = new MusicTypesAdapter(musicTypeModelList, getContext());
        rvMusicTypes.setAdapter(musicTypesAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return (position % 3 == 0) ? 2 : 1;
            }
        });
        rvMusicTypes.setLayoutManager(gridLayoutManager);
        musicTypesAdapter.setOnItemClick(this);
    }

    @Override
    public void onClick(View v) {
        MusicTypeModel musicTypeModel = (MusicTypeModel) v.getTag();
        EventBus.getDefault().postSticky(new OnClickMusicType(musicTypeModel));
        ScreenManager.openFragment(getActivity().getSupportFragmentManager(), new TopSongsFragment(), R.id.rl_layout_container);
//        ScreenManager.openFragment(getActivity().getSupportFragmentManager(), new TopSongsFragment(), R.id.rl_layout_container, this, musicTypeModel);
    }
}

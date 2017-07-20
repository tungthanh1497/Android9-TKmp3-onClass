package techkids.com.android9_tkmp3_onclass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import techkids.com.android9_tkmp3_onclass.adapters.PagerAdapter;

public class MainActivity extends AppCompatActivity {
    String TAG = MainActivity.class.toString();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        final GetMusicTypes getMusicTypes = RetrofitFactory.getInstance().create(GetMusicTypes.class);
//        getMusicTypes.getMusicTypes().enqueue(new Callback<MusicTypesRespondModel>() {
//            @Override
//            public void onResponse(Call<MusicTypesRespondModel> call, Response<MusicTypesRespondModel> response) {
//                List<MusicTypesJSONModel> subgenres = response.body().getSubgenres();
//                for (int i = 0; i < subgenres.size(); i++) {
//                    Log.d(TAG, "onResponse: " + subgenres.get(i));
//                }
////                Log.d(TAG, "\nOnResponse:\n"+response.body().getSubgenres());
//            }
//
//            @Override
//            public void onFailure(Call<MusicTypesRespondModel> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "Where is my internet, bitch???", Toast.LENGTH_SHORT).show();
//            }
//        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_view_compact_black_24dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_favorite_black_24dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_file_download_black_24dp));
        tabLayout.getTabAt(0).getIcon().setAlpha(255);
        for (int i = 1; i < tabLayout.getTabCount(); i++) {
            Log.d(TAG, "onCreate: " + tabLayout.getTabCount());
            tabLayout.getTabAt(i).getIcon().setAlpha(125);
        }
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                tab.getIcon().setAlpha(255);
//                tabLayout.getTabAt(tab.getPosition()).getIcon().setAlpha(1000);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

                tab.getIcon().setAlpha(125);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

                tab.getIcon().setAlpha(255);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.layout.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        int count = getFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
            //additional code
        } else {
            getFragmentManager().popBackStack();
        }

    }
}

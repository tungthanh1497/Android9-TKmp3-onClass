package techkids.com.android9_tkmp3_onclass.adapters;

/**
 * Created by tungthanh.1497 on 07/16/2017.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import techkids.com.android9_tkmp3_onclass.fragments.MusticTypesFragment;
import techkids.com.android9_tkmp3_onclass.fragments.FavoriteFragment;
import techkids.com.android9_tkmp3_onclass.fragments.DownloadFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                MusticTypesFragment tab1 = new MusticTypesFragment();
                return tab1;
            case 1:
                FavoriteFragment tab2 = new FavoriteFragment();
                return tab2;
            case 2:
                DownloadFragment tab3 = new DownloadFragment();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
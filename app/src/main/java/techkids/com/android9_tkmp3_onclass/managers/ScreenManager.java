package techkids.com.android9_tkmp3_onclass.managers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import techkids.com.android9_tkmp3_onclass.R;
import techkids.com.android9_tkmp3_onclass.databases.MusicTypeModel;

/**
 * Created by tungthanh.1497 on 07/19/2017.
 */

public class ScreenManager {
//    public static MusicTypeModel musicTypeClicked;
//    public static void openFragment(FragmentManager fragmentManager, Fragment fragment, int layoutID, Fragment currentFragment, MusicTypeModel musicTypeModel){
//        musicTypeClicked = musicTypeModel;
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
////        fragmentTransaction.hide(currentFragment);
//        fragmentTransaction.add(layoutID, fragment);
//        fragmentTransaction.commit();
//    }
    public static void openFragment(FragmentManager fragmentManager, Fragment fragment, int layoutID){
        String fragmentName = fragment.getClass().getName();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(layoutID, fragment);
        fragmentTransaction.addToBackStack(fragmentName);
        fragmentTransaction.commit();
    }
}

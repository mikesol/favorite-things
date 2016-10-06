package com.jongla.favoritethings;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.jongla.favoritethings.view.BrowsableFragment;
import com.jongla.favoritethings.view.FavoriteFragment;
import com.jongla.favoritethings.view.GithubFragment;
import com.jongla.favoritethings.view.OMDBFragment;
import com.jongla.favoritethings.view.TVMazeFragment;

/**
 * Created by mikesolomon on 05/10/16.
 */

public class BrowsableFragmentPagerAdapter extends FragmentStatePagerAdapter {
    public BrowsableFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    BrowsableFragment[] browsableFragments = new BrowsableFragment[]{null,null,null,null};
    String[] pageTitles = new String[]{"Favorites", "Repos", "TV Shows", "Movies"};

    @Override
    public Fragment getItem(int position) {
        if (browsableFragments[position] == null) {
            switch (position) {
                case 0 :
                    browsableFragments[position] = new FavoriteFragment();
                    break;
                case 1 :
                    browsableFragments[position] = new GithubFragment();
                    break;
                case 2 :
                    browsableFragments[position] = new TVMazeFragment();
                    break;
                case 3 :
                    browsableFragments[position] = new OMDBFragment();
                    break;
            }
        }
        return browsableFragments[position];
    }

    @Override
    public String getPageTitle(int position) {
        return pageTitles[position];
    }

    @Override
    public int getCount() {
        return 4;
    }
}

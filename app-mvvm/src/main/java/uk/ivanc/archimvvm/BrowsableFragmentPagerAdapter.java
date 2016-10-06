package uk.ivanc.archimvvm;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import uk.ivanc.archimvvm.view.BrowsableFragment;
import uk.ivanc.archimvvm.view.GithubFragment;
import uk.ivanc.archimvvm.view.OMDBFragment;
import uk.ivanc.archimvvm.view.TVMazeFragment;

/**
 * Created by mikesolomon on 05/10/16.
 */

public class BrowsableFragmentPagerAdapter extends FragmentStatePagerAdapter {
    public BrowsableFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    BrowsableFragment[] browsableFragments = new BrowsableFragment[]{null,null,null,null};
    String[] pageTitles = new String[]{"Repos", "TV Shows", "Movies"};

    @Override
    public Fragment getItem(int position) {
        if (browsableFragments[position] == null) {
            switch (position) {
                case 0 :
                    browsableFragments[position] = new GithubFragment();
                    break;
                case 1 :
                    browsableFragments[position] = new TVMazeFragment();
                    break;
                case 2 :
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
        return 3;
    }
}

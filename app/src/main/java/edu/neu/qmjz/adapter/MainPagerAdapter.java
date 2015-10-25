package edu.neu.qmjz.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import edu.neu.qmjz.fragment.FragmentException;
import edu.neu.qmjz.fragment.MainFragmentManager;

/**
 * Created with Android Studio.
 * Author: Enex Tapper
 * Date: 15/10/21
 * Project: QMJZ
 * Package: edu.neu.qmjz.adapter
 */
public class MainPagerAdapter extends FragmentPagerAdapter {
	public MainPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		Fragment fragment;
		try {
			fragment = MainFragmentManager.getFragment(position);
		} catch (FragmentException e) {
			Log.d("MainPagerAdapter","New fragment is null.");
			return null;
		}
		return fragment;
	}

	@Override
	public int getCount() {
		return 4;
	}

}

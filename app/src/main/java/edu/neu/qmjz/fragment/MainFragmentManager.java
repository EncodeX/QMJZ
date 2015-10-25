package edu.neu.qmjz.fragment;

import android.support.v4.app.Fragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with Android Studio.
 * Author: Enex Tapper
 * Date: 15/10/21
 * Project: QMJZ
 * Package: edu.neu.qmjz.fragment
 */
public class MainFragmentManager {
	private static Map<Integer,Fragment> fragmentMap;

	public static Fragment getFragment(int index) throws FragmentException{
		if(fragmentMap == null){
			fragmentMap = new HashMap<>();
		}

		if(!fragmentMap.containsKey(index)){
			Fragment newFragment = null;
			switch (index){
				case 0:
					newFragment = new GrabFragment();
					break;
				case 1:
					newFragment = new ReceiveFragment();
					break;
				case 2:
					newFragment = new ServiceListFragment();
					break;
				case 3:
					newFragment = new AccountFragment();
					break;
			}
			if(newFragment == null){
				throw new FragmentException("Fragment Manager didn't create new fragment instance.");
			}
			fragmentMap.put(index,newFragment);
		}

		return fragmentMap.get(index);
	}
}

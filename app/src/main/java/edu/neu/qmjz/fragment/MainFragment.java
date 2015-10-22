package edu.neu.qmjz.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.InjectView;
import edu.neu.qmjz.R;
import edu.neu.qmjz.adapter.GrabListAdapter;

/**
 * Created with Android Studio.
 * Author: Enex Tapper
 * Date: 15/10/21
 * Project: QMJZ
 * Package: edu.neu.qmjz.fragment
 */
public class MainFragment extends Fragment {
	@InjectView(R.id.grab_list)
	RecyclerView mGrabList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_main, container, false);
		ButterKnife.inject(this, rootView);

		mGrabList.setLayoutManager(new LinearLayoutManager(inflater.getContext()));
		mGrabList.setAdapter(new GrabListAdapter(inflater.getContext()));

		return rootView;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onPause() {
		super.onPause();
	}
}

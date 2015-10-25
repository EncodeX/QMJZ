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
public class GrabFragment extends Fragment {
	@InjectView(R.id.grab_list)
	RecyclerView mGrabList;

	GrabListAdapter mListAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.d("Fragment Lifecycle","On Create");
		super.onCreate(savedInstanceState);
		mListAdapter = new GrabListAdapter(getContext());
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.d("Fragment Lifecycle", "On Create View");
		ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_grab, container, false);
		ButterKnife.inject(this, rootView);

		mGrabList.setLayoutManager(new LinearLayoutManager(inflater.getContext()));
		mGrabList.setAdapter(mListAdapter);

		return rootView;
	}

	@Override
	public void onResume() {
		Log.d("Fragment Lifecycle","On Resume");
		super.onResume();
	}

	@Override
	public void onPause() {
		Log.d("Fragment Lifecycle","On Pause");
		super.onPause();
	}

	@Override
	public void onStop() {
		Log.d("Fragment Lifecycle","On Stop");
		super.onStop();
	}

	@Override
	public void onDestroy() {
		Log.d("Fragment Lifecycle","On Destroy");
		super.onDestroy();
	}
}

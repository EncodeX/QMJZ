package edu.neu.qmjz.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.neu.qmjz.R;
import edu.neu.qmjz.adapter.ReceiveListAdapter;

/**
 * Created with Android Studio.
 * Author: Enex Tapper
 * Date: 15/10/21
 * Project: QMJZ
 * Package: edu.neu.qmjz.fragment
 */
public class ReceiveFragment extends Fragment {


	@Bind(R.id.receive_list)
	RecyclerView mReceiveList;

	private ReceiveListAdapter mListAdapter;
	private SharedPreferences mSharedPreferences;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mListAdapter = new ReceiveListAdapter(getContext());

		mSharedPreferences = getContext().getSharedPreferences("shareData", 0);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_receive, container, false);

		ButterKnife.bind(this, rootView);

		initView(inflater.getContext());

		return rootView;
	}

	@Override
	public void onResume() {
		super.onResume();
		mListAdapter.refreshList(mSharedPreferences.getString("servantID",""));
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	private void initView(Context context){
		mReceiveList.setLayoutManager(new LinearLayoutManager(context));
		mReceiveList.setAdapter(mListAdapter);

		mListAdapter.setOnRefreshCompleteListener(new ReceiveListAdapter.OnRefreshCompleteListener() {
			@Override
			public void onRefreshComplete() {

			}
		});

		mListAdapter.setActionResultListener(new ReceiveListAdapter.ActionResultListener() {
			@Override
			public void onGrabSucceeded() {
				mListAdapter.refreshList(mSharedPreferences.getString("servantID",""));
				Toast.makeText(getContext(), "抢单成功", Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onGrabFailed() {
				mListAdapter.refreshList(mSharedPreferences.getString("servantID",""));
				Toast.makeText(getContext(),"抢单失败",Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onRefuseSucceeded() {
				mListAdapter.refreshList(mSharedPreferences.getString("servantID",""));
				Toast.makeText(getContext(),"已拒绝订单",Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onRefuseFailed() {
				mListAdapter.refreshList(mSharedPreferences.getString("servantID",""));
				Toast.makeText(getContext(),"网络通信错误",Toast.LENGTH_SHORT).show();
			}
		});
	}
}

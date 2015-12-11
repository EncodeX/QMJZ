package edu.neu.qmjz.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.neu.qmjz.R;
import edu.neu.qmjz.adapter.ServiceListAdapter;

/**
 * Created with Android Studio.
 * Author: Enex Tapper
 * Date: 15/10/21
 * Project: QMJZ
 * Package: edu.neu.qmjz.fragment
 */
public class ServiceListFragment extends Fragment {
	private static final String LIST_INDEX = "qmjz_service_list_index";
	private static final String[] TYPE_TITLE = {"全部","待付款","服务中","服务完成"};

	private int mListSelectedIndex;

	private ServiceListAdapter mListAdapter;
	private SharedPreferences mSharedPreferences;

	@Bind(R.id.service_list)
	RecyclerView mServiceList;
	@Bind(R.id.service_list_type_tab)
	TabLayout mServiceListTypeTab;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mListAdapter = new ServiceListAdapter(getContext());

		mSharedPreferences = getContext().getSharedPreferences("shareData", 0);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_service_list, container, false);

		ButterKnife.bind(this, rootView);

		mListSelectedIndex = 0;
		if(savedInstanceState!=null){
			mListSelectedIndex = savedInstanceState.getInt(LIST_INDEX,0);
		}

		initView(inflater.getContext());

		return rootView;
	}

	@Override
	public void onResume() {
		super.onResume();
		final TabLayout.Tab selectedTab = mServiceListTypeTab.getTabAt(mListSelectedIndex);
		if(selectedTab!=null && selectedTab.getText()!=null){
			mListAdapter.refreshList(mSharedPreferences.getString("servantID",""), selectedTab.getText().toString());
		}
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(LIST_INDEX,mListSelectedIndex);
	}

	private void initView(Context context){
		for (String aTYPE_TITLE : TYPE_TITLE) {
			mServiceListTypeTab.addTab(mServiceListTypeTab.newTab().setText(aTYPE_TITLE));
		}

		TabLayout.Tab selectedTab = mServiceListTypeTab.getTabAt(mListSelectedIndex);
		if(selectedTab!=null){
			selectedTab.select();
		}

		mServiceListTypeTab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
			@Override
			public void onTabSelected(TabLayout.Tab tab) {
				mListSelectedIndex = tab.getPosition();
				if(tab.getText()!=null)
					mListAdapter.refreshList(mSharedPreferences.getString("servantID",""), tab.getText().toString());
			}

			@Override
			public void onTabUnselected(TabLayout.Tab tab) {}

			@Override
			public void onTabReselected(TabLayout.Tab tab) {}
		});

		mServiceList.setLayoutManager(new LinearLayoutManager(context));
		mServiceList.setAdapter(mListAdapter);

		mListAdapter.setOnDealConfirmCompleteListener(new ServiceListAdapter.OnDealConfirmCompleteListener() {
			@Override
			public void onConfirmSuccess() {
				mListAdapter.refreshList(mSharedPreferences.getString("servantID",""), mServiceListTypeTab.getTabAt(mListSelectedIndex).getText().toString());
				Toast.makeText(getContext(),"操作成功",Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onConfirmFailed() {
				mListAdapter.refreshList(mSharedPreferences.getString("servantID",""), mServiceListTypeTab.getTabAt(mListSelectedIndex).getText().toString());
				Toast.makeText(getContext(),"操作失败",Toast.LENGTH_SHORT).show();
			}
		});

		mListAdapter.setOnRefreshCompleteListener(new ServiceListAdapter.OnRefreshCompleteListener() {
			@Override
			public void onRefreshComplete() {

			}
		});
	}
}

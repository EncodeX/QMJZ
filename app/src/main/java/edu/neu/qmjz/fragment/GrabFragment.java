package edu.neu.qmjz.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import butterknife.ButterKnife;
import butterknife.Bind;
import edu.neu.qmjz.R;
import edu.neu.qmjz.adapter.FilterPickerAdapter;
import edu.neu.qmjz.adapter.GrabListAdapter;
import edu.neu.qmjz.view.FilterPickerLayout;

/**
 * Created with Android Studio.
 * Author: Enex Tapper
 * Date: 15/10/21
 * Project: QMJZ
 * Package: edu.neu.qmjz.fragment
 */
public class GrabFragment extends Fragment {
	@Bind(R.id.grab_list)
	RecyclerView mGrabList;
	@Bind(R.id.filter_layout)
	FilterPickerLayout mFilterLayout;
	@Bind(R.id.county_select_button)
	Button mCountySelectButton;
	@Bind(R.id.county_select_arrow)
	ImageView mCountySelectArrow;
	@Bind(R.id.service_type_select_button)
	Button mServiceTypeSelectButton;
	@Bind(R.id.service_type_select_arrow)
	ImageView mServiceTypeSelectArrow;
	@Bind(R.id.grab_list_mask)
	View mGrabListMask;
	@Bind(R.id.filter_picker)
	GridView mFilterPicker;
	@Bind(R.id.grab_list_refresher)
	SwipeRefreshLayout mGrabListRefresher;

	private GrabListAdapter mListAdapter;
	private FilterPickerAdapter mPickerAdapter;
	private PickInitializeListener mInitializeListener;

	private int mFilterLayoutState = FilterPickerLayout.STATE_CLOSE;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.d("Fragment Lifecycle","On Create");
		super.onCreate(savedInstanceState);
		mListAdapter = new GrabListAdapter(getContext());
		mPickerAdapter = new FilterPickerAdapter(getContext());
		mInitializeListener = new PickInitializeListener();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.d("Fragment Lifecycle", "On Create View");
		ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_grab, container, false);
		ButterKnife.bind(this, rootView);

		initView(inflater.getContext());

		return rootView;
	}

	@Override
	public void onResume() {
		Log.d("Fragment Lifecycle", "On Resume");
		super.onResume();

		mPickerAdapter.initialize();
	}

	@Override
	public void onPause() {
		Log.d("Fragment Lifecycle", "On Pause");
		super.onPause();
	}

	@Override
	public void onStop() {
		Log.d("Fragment Lifecycle", "On Stop");
		super.onStop();
	}

	@Override
	public void onDestroy() {
		Log.d("Fragment Lifecycle", "On Destroy");
		super.onDestroy();
	}

	private void initView(Context context){
		mGrabList.setLayoutManager(new LinearLayoutManager(context));
		mGrabList.setAdapter(mListAdapter);

		mFilterPicker.setAdapter(mPickerAdapter);

		mFilterLayout.setMask(mGrabListMask);

		mCountySelectButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (mFilterLayoutState == FilterPickerLayout.STATE_PICKING_SERVICE_TYPE) {
					mFilterLayoutState = FilterPickerLayout.STATE_PICKING_COUNTY;
					mServiceTypeSelectArrow.setImageResource(R.drawable.ic_arrow_drop_up);
					mCountySelectArrow.setImageResource(R.drawable.ic_arrow_drop_down);
					mPickerAdapter.showCountyList();
				} else if (mFilterLayoutState == FilterPickerLayout.STATE_CLOSE) {
					mFilterLayoutState = FilterPickerLayout.STATE_PICKING_COUNTY;
					mCountySelectArrow.setImageResource(R.drawable.ic_arrow_drop_down);
					mFilterLayout.slide();
					mPickerAdapter.showCountyList();
				} else {
					mFilterLayoutState = FilterPickerLayout.STATE_CLOSE;
					mCountySelectArrow.setImageResource(R.drawable.ic_arrow_drop_up);
					mFilterLayout.slide();
				}
			}
		});

		mServiceTypeSelectButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (mFilterLayoutState == FilterPickerLayout.STATE_PICKING_COUNTY) {
					mFilterLayoutState = FilterPickerLayout.STATE_PICKING_SERVICE_TYPE;
					mServiceTypeSelectArrow.setImageResource(R.drawable.ic_arrow_drop_down);
					mCountySelectArrow.setImageResource(R.drawable.ic_arrow_drop_up);
					mPickerAdapter.showServiceTypeList(mPickerAdapter.getServiceTypeNode());
				} else if (mFilterLayoutState == FilterPickerLayout.STATE_CLOSE) {
					mFilterLayoutState = FilterPickerLayout.STATE_PICKING_SERVICE_TYPE;
					mFilterLayout.slide();
					mServiceTypeSelectArrow.setImageResource(R.drawable.ic_arrow_drop_down);
					mPickerAdapter.showServiceTypeList(mPickerAdapter.getServiceTypeNode());
				}  else {
					mFilterLayoutState = FilterPickerLayout.STATE_CLOSE;
					mFilterLayout.slide();
					mServiceTypeSelectArrow.setImageResource(R.drawable.ic_arrow_drop_up);
				}
			}
		});

		mGrabListMask.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mFilterLayout.slide();
				mServiceTypeSelectArrow.setImageResource(R.drawable.ic_arrow_drop_up);
				mCountySelectArrow.setImageResource(R.drawable.ic_arrow_drop_up);
				mFilterLayoutState = FilterPickerLayout.STATE_CLOSE;
			}
		});

		mFilterPicker.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				if (mPickerAdapter.getDisplayType() == FilterPickerAdapter.DISPLAY_COUNTY) {
					mCountySelectButton.setText((String) mPickerAdapter.getItem(i));
					mPickerAdapter.setSelectedCountyIndex(i);
					refreshDeclareList();
				} else {
					if (mPickerAdapter.getServiceTypeNode().equals("")) {
						mPickerAdapter.setServiceTypeNode((String) mPickerAdapter.getItem(i));
						mPickerAdapter.showServiceTypeList(mPickerAdapter.getServiceTypeNode());
						return;
					} else {
						if (i == 0) {
							mPickerAdapter.setServiceTypeNode("");
							mPickerAdapter.showServiceTypeList(mPickerAdapter.getServiceTypeNode());
							return;
						} else {
							mServiceTypeSelectButton.setText((String) mPickerAdapter.getItem(i));
							mPickerAdapter.setSelectedServiceTypeIndex(i - 1);
							refreshDeclareList();
						}
					}
				}
				mServiceTypeSelectArrow.setImageResource(R.drawable.ic_arrow_drop_up);
				mCountySelectArrow.setImageResource(R.drawable.ic_arrow_drop_up);
				mFilterLayoutState = FilterPickerLayout.STATE_CLOSE;
				mFilterLayout.slide();
			}
		});

		mPickerAdapter.setOnInitializedListener(mInitializeListener);

		mGrabListRefresher.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				refreshDeclareList();
			}
		});
		mGrabListRefresher.setColorSchemeResources(R.color.colorPrimary,R.color.colorBlue,R.color.colorAccent);
		mListAdapter.setOnRefreshCompleteListener(new GrabListAdapter.OnRefreshCompleteListener() {
			@Override
			public void onRefreshComplete() {
				try {
					mGrabListRefresher.setRefreshing(false);
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		});
	}

	private void refreshDeclareList(){
		if(!mGrabListRefresher.isRefreshing()) mGrabListRefresher.setRefreshing(true);
		mListAdapter.refreshList(mCountySelectButton.getText().toString(),mServiceTypeSelectButton.getText().toString());
	}

	private class PickInitializeListener implements FilterPickerAdapter.OnInitializedListener{
		private Integer initCounter = 0;

		@Override
		public void onCountyInitialized(String selectedCounty) {
			mCountySelectButton.setText(selectedCounty);
			countAndCheck();
		}

		@Override
		public void onServiceInitialized(String selectedServiceType) {
			mServiceTypeSelectButton.setText(selectedServiceType);
			countAndCheck();
		}

		private synchronized void countAndCheck(){
			if(initCounter < 2) initCounter++; else return;

			if(initCounter == 2){
				refreshDeclareList();
			}
		}
	}
}

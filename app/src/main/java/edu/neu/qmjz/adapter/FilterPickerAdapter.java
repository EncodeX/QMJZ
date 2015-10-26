package edu.neu.qmjz.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.neu.qmjz.R;
import edu.neu.qmjz.utils.NetworkServiceManager;

/**
 * Created with Android Studio.
 * Author: Enex Tapper
 * Date: 15/10/26
 * Project: QMJZ
 * Package: edu.neu.qmjz.adapter
 */
public class FilterPickerAdapter extends BaseAdapter {
	public static final int DISPLAY_COUNTY = 0;
	public static final int DISPLAY_SERVICE_TYPE = 1;

	private int mDisplayList;
	private List<String> mCountyList;
	private List<String> mServiceTypeList;
	private String mServiceTypeNode;
	private Context mContext;

	private boolean mIsCountyInitialized = false;
	private boolean mIsServiceInitialized = false;

	private OnInitializedListener mOnInitializedListener;

	public FilterPickerAdapter(Context context) {
		mDisplayList = DISPLAY_COUNTY;
		mContext = context;
		mCountyList = new ArrayList<>();
		mServiceTypeList = new ArrayList<>();
		mServiceTypeNode = "";
	}

	public void showCountyList(){
		NetworkServiceManager serviceManager =
				new NetworkServiceManager("http://219.216.65.182:8080/NationalService" +
						"/MobileCountyInfoAction?operation=_querycoveredCounty");
		serviceManager.addParameter("cityName","沈阳市");
		serviceManager.setConnectionListener(mCountyRefreshListener);
		serviceManager.sendAction();
	}

	public void showServiceTypeList(String serviceType){
		NetworkServiceManager serviceManager =
				new NetworkServiceManager("http://219.216.65.182:8080/NationalService" +
						"/MobileServiceTypeAction?operation=_query");
		serviceManager.addParameter("typeName", serviceType);
		serviceManager.setConnectionListener(mServiceRefreshListener);
		serviceManager.sendAction();
	}

	@Override
	public int getCount() {
		int serviceTypeCount = mServiceTypeNode.equals("")? mServiceTypeList.size():(mServiceTypeList.size()+1);
		return mDisplayList == DISPLAY_COUNTY ? mCountyList.size():serviceTypeCount;
	}

	@Override
	public Object getItem(int i) {
		String serviceType = mServiceTypeNode.equals("")? mServiceTypeList.get(i):mServiceTypeList.get(i-1);
		return mDisplayList == DISPLAY_COUNTY ? mCountyList.get(i):serviceType;
	}

	@Override
	public long getItemId(int i) {
		return i;
	}

	public String getServiceTypeNode() {
		return mServiceTypeNode;
	}

	public void setServiceTypeNode(String serviceTypeNode) {
		this.mServiceTypeNode = serviceTypeNode;
	}

	@Override
	public View getView(int i, View view, ViewGroup viewGroup) {
		String content;
		if(mDisplayList == DISPLAY_COUNTY){
			content = mCountyList.get(i);
		}else{
			if(mServiceTypeNode.equals("")){
				content = mServiceTypeList.get(i);
			}else {
				if(i==0){
					content = "上一级";
				}else {
					content = mServiceTypeList.get(i-1);
				}
			}
		}

		ViewHolderItem viewHolderItem;
		if(view ==null){
			view = LayoutInflater.from(mContext).inflate(R.layout.filter_picker_item, null);
			view.setLayoutParams(new GridView.LayoutParams(GridView.AUTO_FIT, dip2px(mContext,56)));
			viewHolderItem = new ViewHolderItem(view);
			view.setTag(viewHolderItem);
		}else {
			viewHolderItem = (ViewHolderItem)view.getTag();
		}

		viewHolderItem.itemText.setText(content);

		return view;
	}

	public int getDisplayType(){
		return mDisplayList;
	}

	public void setOnInitializedListener(OnInitializedListener onInitializedListener) {
		this.mOnInitializedListener = onInitializedListener;
	}

	public void initialize(){
		showCountyList();
		showServiceTypeList("");
	}

	static class ViewHolderItem{
		@Bind(R.id.filter_item_text)TextView itemText;

		public ViewHolderItem(View itemView) {
			ButterKnife.bind(this,itemView);
		}
	}

	private NetworkServiceManager.ConnectionListener mCountyRefreshListener = new NetworkServiceManager.ConnectionListener() {
		@Override
		public void onConnectionSucceeded(JSONObject result) {
			try {
				if(result.getString("serverResponse").equals("Success")){
					mCountyList.clear();
					JSONArray data = result.getJSONArray("data");
					for(int i=0; i<data.length(); i++){
						JSONObject temp = (JSONObject)data.get(i);

						mCountyList.add(temp.getString("countyName"));
					}
					if(mIsCountyInitialized){
						mDisplayList = DISPLAY_COUNTY;
					}else{
						mOnInitializedListener.onCountyInitialized(mCountyList.get(0));
						mIsCountyInitialized = true;
					}
					notifyDataSetChanged();
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	};

	private NetworkServiceManager.ConnectionListener mServiceRefreshListener = new NetworkServiceManager.ConnectionListener() {
		@Override
		public void onConnectionSucceeded(JSONObject result) {
			try {
				if(result.getString("serverResponse").equals("Success")){

					mServiceTypeList.clear();
					JSONArray data = result.getJSONArray("data");
					for(int i=0; i<data.length(); i++){
						JSONObject temp = (JSONObject)data.get(i);

						mServiceTypeList.add(temp.getString("typeName"));
					}
					if(mIsServiceInitialized){
						mDisplayList = DISPLAY_SERVICE_TYPE;
					}else {
						mOnInitializedListener.onServiceInitialized(mServiceTypeList.get(0));
						mIsServiceInitialized = true;
					}

					notifyDataSetChanged();
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	};

	private static int dip2px(Context context, float dipValue){
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int)(dipValue * scale + 0.5f);
	}

	public interface OnInitializedListener{
		void onCountyInitialized(String firstCounty);
		void onServiceInitialized(String firstServiceType);
	}
}

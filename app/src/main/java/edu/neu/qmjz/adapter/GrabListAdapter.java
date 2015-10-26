package edu.neu.qmjz.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Bind;
import edu.neu.qmjz.R;
import edu.neu.qmjz.bean.Declare;
import edu.neu.qmjz.utils.NetworkServiceManager;

/**
 * Created with Android Studio.
 * Author: Enex Tapper
 * Date: 15/10/22
 * Project: QMJZ
 * Package: edu.neu.qmjz.adapter
 */
public class GrabListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
	// 用户名 jacob  密码 abc123

	private final LayoutInflater mLayoutInflater;
	private final Context mContext;

	private List<Declare> mDeclareList;

	public GrabListAdapter(Context context) {
		mContext = context;
		mLayoutInflater = LayoutInflater.from(context);
		mDeclareList = new ArrayList<>();
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return new GrabListViewHolder(mLayoutInflater.inflate(R.layout.grab_list_item, parent, false));
	}

	@Override
	public int getItemViewType(int position) {
		return super.getItemViewType(position);
	}

	@Override
	public long getItemId(int position) {
		return super.getItemId(position);
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//		holder.mClassTextView.setText(String.valueOf(position + 1));
	}

	@Override
	public int getItemCount() {
		return mDeclareList.size();
	}

	public void refreshList(String serviceType){
		try {
			NetworkServiceManager serviceManager =
					new NetworkServiceManager("http://219.216.65.182:8080/NationalService" +
							"/MobileServiceDeclareAction?operation=_queryserviceDeclare");
			Log.v("ServiceType","Raw: ' "+serviceType+" '");
			Log.v("ServiceType","Encoded: ' "+URLEncoder.encode(serviceType,"UTF-8")+" '");
			serviceManager.addParameter("serviceType", serviceType);
			serviceManager.setConnectionListener(mRefreshListener);
			serviceManager.sendAction();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	static class GrabListViewHolder extends RecyclerView.ViewHolder {
		@Bind(R.id.service_type_text_view)
		TextView mClassTextView;
		@Bind(R.id.grab_button)
		Button mGrabButton;
		@Bind(R.id.contact_layout)
		RelativeLayout mContactLayout;

		public GrabListViewHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);

			mGrabButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
//					Toast.makeText(view.getContext(), "onClick--> position = " + getLayoutPosition(), Toast.LENGTH_SHORT).show();
					Log.v("Network Connection", "On Click");
					NetworkServiceManager serviceManager =
							new NetworkServiceManager("http://219.216.65.182:8080/NationalService" +
									"/MobileServiceDeclareAction?operation=_queryserviceDeclare");
					serviceManager.addParameter("servantID", "jacob");
					serviceManager.addParameter("loginPassword","abc123");
					serviceManager.setConnectionListener(new NetworkServiceManager.ConnectionListener() {
						@Override
						public void onConnectionSucceeded(JSONObject result) {
							Log.v("Network Connection", "Succeed");
							Log.v("Network Connection", result.toString());
						}
					});
					serviceManager.sendAction();
				}
			});
			mContactLayout.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					Toast.makeText(view.getContext(), "make a call", Toast.LENGTH_SHORT).show();
				}
			});
		}
	}

	private NetworkServiceManager.ConnectionListener mRefreshListener = new NetworkServiceManager.ConnectionListener() {
		@Override
		public void onConnectionSucceeded(JSONObject result) {
			try {
				if(result.getString("serverResponse").equals("Success")){
					Log.v("JSON",result.toString());
//					mServiceTypeList.clear();
//					JSONArray data = result.getJSONArray("data");
//					for(int i=0; i<data.length(); i++){
//						JSONObject temp = (JSONObject)data.get(i);
//
//						mServiceTypeList.add(temp.getString("typeName"));
//					}
//					if(mIsServiceInitialized){
//						mDisplayList = DISPLAY_SERVICE_TYPE;
//					}else {
//						mOnInitializedListener.onServiceInitialized(mServiceTypeList.get(0));
//						mIsServiceInitialized = true;
//					}
//
//					notifyDataSetChanged();
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	};
}

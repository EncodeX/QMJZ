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
	private OnRefreshCompleteListener mOnRefreshCompleteListener;

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
		if(holder instanceof GrabListViewHolder){
			Declare temp = mDeclareList.get(position);
			((GrabListViewHolder) holder).mServiceTypeTextView.setText(temp.getServiceType());
			((GrabListViewHolder)holder).mAccountTextView.setText(temp.getCustomerName());
			((GrabListViewHolder)holder).mTimeTextView.setText(temp.getServiceTime());
			String salary =temp.getSalary() + "元";
			((GrabListViewHolder)holder).mMoneyTextView.setText(salary);
			((GrabListViewHolder) holder).mContactTextView.setText(temp.getPhoneNo());
			((GrabListViewHolder)holder).mAddressTextView.setText(temp.getServiceAddress());
			((GrabListViewHolder)holder).mRemarkTextView.setText(temp.getRemark());
		}
	}

	@Override
	public int getItemCount() {
		return mDeclareList.size();
	}

	public void setOnRefreshCompleteListener(OnRefreshCompleteListener onRefreshCompleteListener) {
		this.mOnRefreshCompleteListener = onRefreshCompleteListener;
	}

	public void refreshList(String countyName,String serviceType){

		NetworkServiceManager serviceManager =
				new NetworkServiceManager("http://219.216.65.182:8080/NationalService" +
						"/MobileServiceDeclareAction?operation=_queryserviceDeclare");
		serviceManager.addParameter("serviceType", serviceType);
		serviceManager.addParameter("serviceCounty", countyName);
		serviceManager.setConnectionListener(mRefreshListener);
		serviceManager.sendAction();
	}

	static class GrabListViewHolder extends RecyclerView.ViewHolder {
		@Bind(R.id.service_type_text_view)
		TextView mServiceTypeTextView;
		@Bind(R.id.account_text_view)
		TextView mAccountTextView;
		@Bind(R.id.time_text_view)
		TextView mTimeTextView;
		@Bind(R.id.money_text_view)
		TextView mMoneyTextView;
		@Bind(R.id.contact_text_view)
		TextView mContactTextView;
		@Bind(R.id.address_text_view)
		TextView mAddressTextView;
		@Bind(R.id.remark_text_view)
		TextView mRemarkTextView;
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
//					Log.v("Network Connection", "On Click");
//					NetworkServiceManager serviceManager =
//							new NetworkServiceManager("http://219.216.65.182:8080/NationalService" +
//									"/MobileServiceDeclareAction?operation=_queryserviceDeclare");
//					serviceManager.addParameter("servantID", "jacob");
//					serviceManager.addParameter("loginPassword","abc123");
//					serviceManager.setConnectionListener(new NetworkServiceManager.ConnectionListener() {
//						@Override
//						public void onConnectionSucceeded(JSONObject result) {
//							Log.v("Network Connection", "Succeed");
//							Log.v("Network Connection", result.toString());
//						}
//					});
//					serviceManager.sendAction();
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
			Log.v("JSON",result.toString());
			try {
				if(result.getString("serverResponse").equals("Success")){
					mDeclareList.clear();
					JSONArray data = result.getJSONArray("data");
					for(int i=0;i<data.length();i++){
						mDeclareList.add(new Declare(data.getJSONObject(i)));
					}
					notifyDataSetChanged();
				}
				mOnRefreshCompleteListener.onRefreshComplete();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onConnectionFailed(String errorMessage) {
			mOnRefreshCompleteListener.onRefreshComplete();
		}
	};

	public interface OnRefreshCompleteListener{
		void onRefreshComplete();
	}
}

package edu.neu.qmjz.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
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
public class ReceiveListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
	// 用户名 jacob  密码 123abc

	private final LayoutInflater mLayoutInflater;
	private final Context mContext;

	private List<Declare> mDeclareList;
	private OnRefreshCompleteListener mOnRefreshCompleteListener;
	private GrabResultListener mGrabResultListener;

	public ReceiveListAdapter(Context context) {
		mContext = context;
		mLayoutInflater = LayoutInflater.from(context);
		mDeclareList = new ArrayList<>();
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return new ReceiveListViewHolder(mLayoutInflater.inflate(R.layout.receive_list_item, parent, false));
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
	public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
//		if(holder instanceof ReceiveListViewHolder){
//			Declare temp = mDeclareList.get(position);
//			String salary =temp.getSalary() + "元";
//			String address= temp.getServiceProvince()+ temp.getServiceCity() + temp.getServiceCounty() + temp.getServiceAddress();
//			((ReceiveListViewHolder) holder).mServiceTypeTextView.setText(temp.getServiceType());
//			((ReceiveListViewHolder)holder).mAccountTextView.setText(temp.getCustomerName());
//			((ReceiveListViewHolder)holder).mTimeTextView.setText(temp.getServiceTime());
//			((ReceiveListViewHolder)holder).mMoneyTextView.setText(salary);
//			((ReceiveListViewHolder) holder).mContactTextView.setText(temp.getPhoneNo());
//			((ReceiveListViewHolder) holder).mAddressTextView.setText(address);
//			((ReceiveListViewHolder)holder).mRemarkTextView.setText(temp.getRemark());
//			((ReceiveListViewHolder)holder).mGrabButton.setOnClickListener(new View.OnClickListener() {
//				@Override
//				public void onClick(View view) {
//					grabDeclare(position);
//				}
//			});
//		}
	}

	@Override
	public int getItemCount() {
//		return mDeclareList.size();
		return 10;
	}

	public void setOnRefreshCompleteListener(OnRefreshCompleteListener onRefreshCompleteListener) {
		this.mOnRefreshCompleteListener = onRefreshCompleteListener;
	}

	public void setGrabResultListener(GrabResultListener grabResultListener) {
		this.mGrabResultListener = grabResultListener;
	}

	public void refreshList(String serviceType){
		NetworkServiceManager serviceManager =
				new NetworkServiceManager("http://219.216.65.182:8080/NationalService" +
						"/MobileServiceDeclareAction?operation=_queyOwnservice");
		serviceManager.addParameter("serviceType", serviceType);
		serviceManager.setConnectionListener(mRefreshListener);
		serviceManager.sendAction();
	}

	public void grabDeclare(int index){
		NetworkServiceManager serviceManager =
				new NetworkServiceManager("http://219.216.65.182:8080/NationalService" +
						"/MobileServiceOrderAction?operation=_add");
		Declare temp = mDeclareList.get(index);
		serviceManager.addParameter("id", String.valueOf(temp.getId()));
		serviceManager.addParameter("customerID", temp.getCustomerId());
		serviceManager.addParameter("customerName", temp.getCustomerName());
		serviceManager.addParameter("servantID", temp.getServantId());
		serviceManager.addParameter("servantName", temp.getServantName());
		serviceManager.addParameter("contactAddress", temp.getServiceAddress());
		serviceManager.addParameter("contactPhone", temp.getPhoneNo());
		serviceManager.addParameter("servicePrice", String.valueOf(temp.getSalary()));
		serviceManager.addParameter("serviceType", temp.getServiceType());
		serviceManager.addParameter("serviceContent", "");
		serviceManager.addParameter("remarks", temp.getRemark());
		serviceManager.setConnectionListener(mGrabDeclareListener);
		serviceManager.sendAction();
	}

	static class ReceiveListViewHolder extends RecyclerView.ViewHolder {
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
		@Bind(R.id.refuse_button)
		Button mRefuseButton;
		@Bind(R.id.contact_layout)
		RelativeLayout mContactLayout;

		public ReceiveListViewHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
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
				mDeclareList.clear();
				if(result.getString("serverResponse").equals("Success")){
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

	private NetworkServiceManager.ConnectionListener mGrabDeclareListener = new NetworkServiceManager.ConnectionListener() {
		@Override
		public void onConnectionSucceeded(JSONObject result) {
			try {
				if(result.getString("serverResponse").equals("Success")){
					mGrabResultListener.onGrabSucceeded();
				}else{
					mGrabResultListener.onGrabFailed();
				}
			} catch (JSONException e) {
				mGrabResultListener.onGrabFailed();
				e.printStackTrace();
			}
		}

		@Override
		public void onConnectionFailed(String errorMessage) {
			mGrabResultListener.onGrabFailed();
		}
	};

	public interface OnRefreshCompleteListener{
		void onRefreshComplete();
	}

	public interface GrabResultListener {
		void onGrabSucceeded();
		void onGrabFailed();
	}
}

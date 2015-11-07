package edu.neu.qmjz.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.neu.qmjz.R;
import edu.neu.qmjz.bean.Declare;
import edu.neu.qmjz.bean.Order;
import edu.neu.qmjz.utils.NetworkServiceManager;

/**
 * Created with Android Studio.
 * Author: Enex Tapper
 * Date: 15/10/22
 * Project: QMJZ
 * Package: edu.neu.qmjz.adapter
 */
public class ServiceListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
	// 用户名 jacob  密码 123abc

	private final LayoutInflater mLayoutInflater;
	private final Context mContext;

	private List<Order> mServiceList;
	private OnRefreshCompleteListener mOnRefreshCompleteListener;

	public ServiceListAdapter(Context context) {
		mContext = context;
		mLayoutInflater = LayoutInflater.from(context);
		mServiceList = new ArrayList<>();
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return new ServiceListViewHolder(mLayoutInflater.inflate(R.layout.service_list_item, parent, false));
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
		if(holder instanceof ServiceListViewHolder){
//			Order temp = mServiceList.get(position);
//			String servicePrice = temp.getServicePrice() + "元";
//			String paidPrice = temp.getPaidAmount() != 0? temp.getPaidAmount() + "元" : "未付款";
//
//			((ServiceListViewHolder)holder).mAccountTextView.setText(temp.getCustomerName());
//			((ServiceListViewHolder)holder).mServiceTypeTextView.setText(temp.getServiceType());
//			((ServiceListViewHolder)holder).mOrderStateTextView.setText(temp.getOrderStatus());
//			((ServiceListViewHolder)holder).mServicePriceTextView.setText(servicePrice);
//			((ServiceListViewHolder)holder).mPaidPriceTextView.setText(paidPrice);
//			((ServiceListViewHolder)holder).mContactTextView.setText(temp.getContactPhone());
//			((ServiceListViewHolder)holder).mOrderTimeTextView.setText(temp.getOrderTime());
//			((ServiceListViewHolder)holder).mCompleteTimeTextView.setText(temp.getConfirmTime());
//			((ServiceListViewHolder)holder).mDealStateTextView.setText(temp.isSettled()?"已结算":"未结算");
		}
	}

	@Override
	public int getItemCount() {
//		return mServiceList.size();
		return 10;
	}

	public void setOnRefreshCompleteListener(OnRefreshCompleteListener onRefreshCompleteListener) {
		this.mOnRefreshCompleteListener = onRefreshCompleteListener;
	}

	public void refreshList(String servantID,String orderStatus){
		NetworkServiceManager serviceManager;
		if(orderStatus.equals("全部")){
			serviceManager =
					new NetworkServiceManager("http://219.216.65.182:8080/NationalService" +
							"/MobileServiceOrderAction?operation=_queryOrder");
			serviceManager.addParameter("servantID", servantID);
			serviceManager.addParameter("servantID", orderStatus);
		}else{
			serviceManager =
					new NetworkServiceManager("http://219.216.65.182:8080/NationalService" +
							"/MobileServiceOrderAction?operation=_queryOrders");
			serviceManager.addParameter("servantID", servantID);
		}
		serviceManager.setConnectionListener(mRefreshListener);
		serviceManager.sendAction();
	}

	static class ServiceListViewHolder extends RecyclerView.ViewHolder {
		@Bind(R.id.service_type_text_view)
		TextView mServiceTypeTextView;
		@Bind(R.id.order_state_text_view)
		TextView mOrderStateTextView;
		@Bind(R.id.account_text_view)
		TextView mAccountTextView;
		@Bind(R.id.service_price_text_view)
		TextView mServicePriceTextView;
		@Bind(R.id.paid_price_text_view)
		TextView mPaidPriceTextView;
		@Bind(R.id.contact_text_view)
		TextView mContactTextView;
		@Bind(R.id.order_time_text_view)
		TextView mOrderTimeTextView;
		@Bind(R.id.complete_time_text_view)
		TextView mCompleteTimeTextView;
		@Bind(R.id.deal_state_text_view)
		TextView mDealStateTextView;

		public ServiceListViewHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
		}
	}

	private NetworkServiceManager.ConnectionListener mRefreshListener = new NetworkServiceManager.ConnectionListener() {
		@Override
		public void onConnectionSucceeded(JSONObject result) {
//			try {
//				mServiceList.clear();
//				if(result.getString("serverResponse").equals("Success")){
//					JSONArray data = result.getJSONArray("data");
//					for(int i=0;i<data.length();i++){
//						mServiceList.add(new Declare(data.getJSONObject(i)));
//					}
//					notifyDataSetChanged();
//				}
//				mOnRefreshCompleteListener.onRefreshComplete();
//			} catch (JSONException e) {
//				e.printStackTrace();
//			}
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

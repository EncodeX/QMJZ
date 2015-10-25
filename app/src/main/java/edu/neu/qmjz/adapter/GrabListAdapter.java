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

import butterknife.ButterKnife;
import butterknife.InjectView;
import edu.neu.qmjz.R;

/**
 * Created with Android Studio.
 * Author: Enex Tapper
 * Date: 15/10/22
 * Project: QMJZ
 * Package: edu.neu.qmjz.adapter
 */
public class GrabListAdapter extends RecyclerView.Adapter<GrabListAdapter.GrabListViewHolder>{
	// 用户名 jacob  密码 abc123

	private final LayoutInflater mLayoutInflater;
	private final Context mContext;


	public GrabListAdapter(Context context) {
		mContext = context;
		mLayoutInflater = LayoutInflater.from(context);
	}

	@Override
	public GrabListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return new GrabListViewHolder(mLayoutInflater.inflate(R.layout.grab_list_item, parent, false));
	}

	@Override
	public void onBindViewHolder(GrabListViewHolder holder, int position) {
//		holder.mClassTextView.setText(String.valueOf(position + 1));
	}

	@Override
	public int getItemCount() {
		return 20;
	}

	public static class GrabListViewHolder extends RecyclerView.ViewHolder {
		@InjectView(R.id.class_text_view)
		TextView mClassTextView;
		@InjectView(R.id.grab_button)
		Button mGrabButton;
		@InjectView(R.id.contact_layout)
		RelativeLayout mContactLayout;

		public GrabListViewHolder(View itemView) {
			super(itemView);
			ButterKnife.inject(this, itemView);

			mGrabButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					Toast.makeText(view.getContext(), "onClick--> position = " + getLayoutPosition(), Toast.LENGTH_SHORT).show();
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
}

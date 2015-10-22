package edu.neu.qmjz.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
		holder.mGrabItemText.setText(String.valueOf(position+1));
	}

	@Override
	public int getItemCount() {
		return 20;
	}

	public static class GrabListViewHolder extends RecyclerView.ViewHolder {
		@InjectView(R.id.grab_item_text)
		TextView mGrabItemText;

		public GrabListViewHolder(View itemView) {
			super(itemView);
			ButterKnife.inject(this, itemView);

			itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
//					Log.d("GrabListViewHolder", "onClick--> position = " + getLayoutPosition());
					Toast.makeText(view.getContext(),"onClick--> position = " + getLayoutPosition(),Toast.LENGTH_SHORT).show();
				}
			});
		}
	}
}

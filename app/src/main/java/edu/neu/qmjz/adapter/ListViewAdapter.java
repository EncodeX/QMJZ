package edu.neu.qmjz.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import edu.neu.qmjz.R;

/**
 * Created by Administrator on 2015/10/25.
 */
public class ListViewAdapter extends SimpleAdapter {
    /**
     * Constructor
     *
     * @param context  The context where the View associated with this SimpleAdapter is running
     * @param data     A List of Maps. Each entry in the List corresponds to one row in the list. The
     *                 Maps contain the data for each row, and should include all the entries specified in
     *                 "from"
     * @param resource Resource identifier of a view layout that defines the views for this list
     *                 item. The layout file should include at least those named views defined in "to"
     * @param from     A list of column names that will be added to the Map associated with each
     *                 item.
     * @param to       The views that should display column in the "from" parameter. These should all be
     *                 TextViews. The first N views in this list are given the values of the first N columns
     */
    public ListViewAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
//        getView()

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        RecyclerView.ViewHolder holder = null;
//        if (convertView == null) {

//            holder=new RecyclerView.ViewHolder();
//
//            convertView = mInflater.inflate(R.layout.vlist2, null);
//            holder.img = (ImageView)convertView.findViewById(R.id.img);
//            holder.title = (TextView)convertView.findViewById(R.id.title);
//            holder.info = (TextView)convertView.findViewById(R.id.info);
//            holder.viewBtn = (Button)convertView.findViewById(R.id.view_btn);
//            convertView.setTag(holder);

//        }else {
//
//            holder = (RecyclerView.ViewHolder)convertView.getTag();
//        }
//
//
//        holder.img.setBackgroundResource((Integer)mData.get(position).get("img"));
//        holder.title.setText((String)mData.get(position).get("title"));
//        holder.info.setText((String)mData.get(position).get("info"));
//
//        holder.viewBtn.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                showInfo();
//            }
//        });


        return convertView;
    }
}

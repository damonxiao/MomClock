package damon.ll.momclock.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import damon.ll.momclock.R;

/**
 * xiaofang
 * 19-1-10
 **/
public class DelayHoursListAdapter extends BaseAdapter {
    private List<SelectDelayHoursPopup.HourItem> mItems;
    private Context mContext;
    public DelayHoursListAdapter(List<SelectDelayHoursPopup.HourItem> items, Context context) {
        this.mItems = items;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public SelectDelayHoursPopup.HourItem getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemHolder holder;
        if(convertView != null) {
            holder = (ItemHolder) convertView.getTag();
        } else {
            holder = new ItemHolder();
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.selectdelayhours_pop_item, null, false);
            holder.delayHours = convertView.findViewById(R.id.delay_hours_tv);
            convertView.setTag(holder);
        }
        holder.delayHours.setText(mItems.get(position).text);
        return convertView;
    }

    private static class ItemHolder {
        TextView delayHours;
    }
}

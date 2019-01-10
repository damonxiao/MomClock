package damon.ll.momclock.view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListPopupWindow;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import damon.ll.momclock.R;


public class SelectDelayHoursPopup {
//    private String[] mItems = {"1小时之后","1小时之后","1小时之后","1小时之后"};
    private List<HourItem> mItems;
    public ListPopupWindow mPopupWindow;
    private SelectDelayHourPopupOnClickListener mListener;

    public ListPopupWindow getmPopupWindow() {
        return mPopupWindow;
    }

    private Context mContext;

    @SuppressWarnings("deprecation")
    public SelectDelayHoursPopup(Context context) {
        mContext = context;
        mPopupWindow = new ListPopupWindow(context);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        mPopupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setAnimationStyle(R.style.AnimBottom);

        mItems = new ArrayList<>();
        mItems.add(new HourItem("1小时之后", 1));
        mItems.add(new HourItem("2小时之后", 2));
        mItems.add(new HourItem("3小时之后", 3));
        mItems.add(new HourItem("4小时之后", 4));
        final DelayHoursListAdapter adapter = new DelayHoursListAdapter(mItems, mContext);
        mPopupWindow.setAdapter(adapter);
        mPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mContext, adapter.getItem(position).text, Toast.LENGTH_LONG).show();
                if(mListener != null) {
                    mListener.onSelect(adapter.getItem(position));
                }
                dismiss();
            }
        });

    }

    public interface SelectDelayHourPopupOnClickListener {
        void onSelect(HourItem item);
    }

    public void setOnSelectRemindWayPopupListener(SelectDelayHourPopupOnClickListener l) {
        this.mListener = l;
    }

    public void dismiss() {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
    }

    public void showPopup(View rootView) {
        // 第一个参数是要将PopupWindow放到的View，第二个参数是位置，第三第四是偏移值
        mPopupWindow.setAnchorView(rootView);
        mPopupWindow.show();
    }

    public static class HourItem{
        public HourItem(String text, int hour) {
            this.text = text;
            this.hour = hour;
        }

        public String text;
        public int hour;
    }
}

package hkc.com.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by DIY on 2016/8/20.
 */
public class MyViewHolder extends RecyclerView.ViewHolder {
    private TextView textView;

    public MyViewHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.id_viewitem);
    }

    public void setTitle(String title){
        textView.setText(title);
    }
}

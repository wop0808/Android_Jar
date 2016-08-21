package hkc.com.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by DIY on 2016/8/20.
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyViewHolder>{
    private Context context;
    private MyViewHolder myViewHolder;

    public MyRecyclerViewAdapter(Context context){
        this.context = context;
    }


    //每一个viewItem
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        /**
         * View.inflate()不能让文字居中
         *
         * inflater.inflate()可以
         *
         * */
//        View view = View.inflate(context , R.layout.view_item ,null);
        View view = LayoutInflater.from(context).inflate(R.layout.view_item,parent,false);

        myViewHolder = new MyViewHolder(view);



        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.setTitle("title"+position);

    }

    @Override
    public int getItemCount() {
        return 50;
    }
}

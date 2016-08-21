package hkc.com.pull_to_refresh;

import android.database.DatabaseUtils;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private PullToRefreshListView pullToRefreshListView;
    private LinkedList datas = new LinkedList();
    private int numberCount = 9;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //控件实例化
        pullToRefreshListView = (PullToRefreshListView) findViewById(R.id.pull_refresh_list);

        initDatas();

        //捆绑适配器
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datas);
        pullToRefreshListView.setAdapter(arrayAdapter);

       /* //下拉监听
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                String currentTime = DateUtils.formatDateTime(getApplicationContext(), System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

                // pullToRefreshListView.getLoadingLayoutProxy():获得加载过程中layout的代理，用以设置各种UI
                pullToRefreshListView.getLoadingLayoutProxy().setLastUpdatedLabel(currentTime);
//                pullToRefreshListView.getLoadingLayoutProxy().setLoadingDrawable();
//                pullToRefreshListView.getLoadingLayoutProxy().setPullLabel();
//                pullToRefreshListView.getLoadingLayoutProxy().setRefreshingLabel();
//                pullToRefreshListView.getLoadingLayoutProxy().setReleaseLabel();
//                pullToRefreshListView.getLoadingLayoutProxy().setTextTypeface();

                new GetDataTask().execute();
            }
        });*/

        //上下拉刷新是的监听
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                //下拉刷新的任务
                new GetDataTask().execute();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                //上拉刷新的任务
                new GetDataTask().execute();
            }
        });



    }

    public void initDatas() {
        for (int i = 0; i < numberCount; i++) {
            datas.add("" + i);
        }
    }

    private class GetDataTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "" + (numberCount++);
        }

        @Override
        protected void onPostExecute(String result)
        {
            datas.add(result);
            arrayAdapter.notifyDataSetChanged();
            // Call onRefreshComplete when the list has been refreshed.
            pullToRefreshListView.onRefreshComplete();
        }
    }


}

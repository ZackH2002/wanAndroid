package com.example.myapplication.view.system;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.SystemLeftAdapter;
import com.example.myapplication.Adapter.SystemRightAdapter;
import com.example.myapplication.R;
import com.example.myapplication.bean.SystemData;
import com.example.myapplication.presenter.SystemPresenter;

import java.util.ArrayList;
import java.util.List;

public class SystemFragment extends Fragment implements ISystemFragment {
    private RecyclerView leftRecyclerView,rightRecyclerView;
    private SystemLeftAdapter systemLeftAdapter;
    private SystemRightAdapter systemRightAdapter;
    private List<SystemData.DataBean> dataBeanList;
    private List<SystemData.DataBean.ChildrenBean> childrenBeanList = new ArrayList<>();
    Context context;
    SystemPresenter systemPresenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_system, container, false);
        initView(view);
        context=view.getContext();
        dataBeanList=new ArrayList<>();
        systemPresenter=new SystemPresenter(SystemFragment.this);
        systemPresenter.getData();
        return view;
    }

    private void initView(View view){
        leftRecyclerView=view.findViewById(R.id.system_leftRecyclerView);
        rightRecyclerView=view.findViewById(R.id.system_rightRecyclerView);
    }
    @Override
    public void onSystemSuccess(SystemData systemData) {
        dataBeanList.addAll(systemData.getData());
        systemLeftAdapter=new SystemLeftAdapter(context,dataBeanList);
        systemRightAdapter=new SystemRightAdapter(context,dataBeanList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(context);
        leftRecyclerView.setLayoutManager(layoutManager);
        rightRecyclerView.setLayoutManager(layoutManager1);
        leftRecyclerView.setAdapter(systemLeftAdapter);
        rightRecyclerView.setAdapter(systemRightAdapter);
        systemLeftAdapter.update(dataBeanList);
        systemRightAdapter.update(dataBeanList);
        systemLeftAdapter.notifyDataSetChanged();
        systemRightAdapter.notifyDataSetChanged();
        systemLeftAdapter.setLeftCheckListener(new SystemLeftAdapter.LeftCheckListener() {
            @Override
            public void onItemClick(int position) {
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager)rightRecyclerView.getLayoutManager();
                linearLayoutManager.scrollToPositionWithOffset(position,0);
            }
        });
        rightRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //获取滚动时的第一条展示的position
                LinearLayoutManager layoutManager2 = (LinearLayoutManager)rightRecyclerView.getLayoutManager();
                int firstVisibleItemPosition = layoutManager2.findFirstVisibleItemPosition();
                int outId = dataBeanList.get(firstVisibleItemPosition).getId();
                int pos=0;
                for (int i = 0; i <dataBeanList.size() ; i++) {
                    int id = dataBeanList.get(i).getId();
                    if (id==outId){
                        pos=i;
                    }
                }
                LinearLayoutManager layoutManager3 = (LinearLayoutManager)leftRecyclerView.getLayoutManager();
                layoutManager3.scrollToPositionWithOffset(pos,0);
            }
        });
    }

    @Override
    public void onSystemFailed(String error) {

    }
}

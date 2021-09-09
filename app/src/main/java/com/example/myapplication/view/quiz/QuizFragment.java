package com.example.myapplication.view.quiz;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.myapplication.Adapter.QuizAdapter;
import com.example.myapplication.R;
import com.example.myapplication.bean.ArticleData;
import com.example.myapplication.presenter.QuizPresenter;

import java.util.ArrayList;
import java.util.List;

public class QuizFragment extends Fragment implements IQuizFragment {
    Context context;
    private List<ArticleData.DataBean.DatasBean> articleList = new ArrayList<>();
    private RecyclerView recyclerView;
    private QuizAdapter quizAdapter;
    private QuizPresenter quizPresenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);
        initView(view);
        context = this.getActivity();
        quizPresenter= new QuizPresenter(QuizFragment.this);
        quizPresenter.getArticleData();
        quizAdapter = new QuizAdapter(context,articleList);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(quizAdapter);
        return view;
    }

    private void initView(View view){
        recyclerView=view.findViewById(R.id.quiz_recyclerview);
    }
    @Override
    public void success(ArticleData articleData) {
        articleList.addAll(articleData.getData().getDatas());
        quizAdapter.notifyDataSetChanged();
    }
}

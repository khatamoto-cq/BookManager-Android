package com.caraquri.hatamoto.bookmanager.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.caraquri.hatamoto.bookmanager.App;
import com.caraquri.hatamoto.bookmanager.R;
import com.caraquri.hatamoto.bookmanager.domain.entity.Book;
import com.caraquri.hatamoto.bookmanager.presentation.adapter.BookAdapter;
import com.caraquri.hatamoto.bookmanager.presentation.contract.BookListContract;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookListFragment extends BaseFragment implements BookListContract.View {

    public static final String EXTRA_BOOK = "BookListFragment.EXTRA_BOOK";

    @Inject
    BookListPresenter bookListPresenter;

    @BindView(R.id.bookListRecyclerView)
    RecyclerView bookListRecyclerView;

    private BookAdapter bookAdapter;

    public BookListFragment() {
        // Required empty public constructor
    }

    public static BookListFragment newInstance() {
        BookListFragment fragment = new BookListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        App.getAppComponent(getActivity()).inject(this);
        bookListPresenter.attachView(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        init();
        bookListPresenter.load();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        bookListPresenter.detachView();
        super.onDestroyView();
    }

    private void init() {
        bookAdapter = new BookAdapter() {
            @Override
            protected void onItemClicked(@NonNull Book book) {
                super.onItemClicked(book);
                Intent intent = new Intent(getActivity(), EditBookActivity.class);
                intent.putExtra(BookListFragment.EXTRA_BOOK, book);
                startActivity(intent);
            }
        };
        bookListRecyclerView.setHasFixedSize(true);
        bookListRecyclerView.setAdapter(bookAdapter);
    }

    @Override
    public void showProgress() {
        if (progress != null) {
            progress.show();
        }
    }

    @Override
    public void hideProgress() {
        if (progress != null && progress.isShowing()) {
            progress.dismiss();
        }
    }

    @Override
    public void showError(String title, String message) {
        showDialog(title, message);
    }

    @Override
    public String getErrorMessage(int resource) {
        return getResources().getString(resource);
    }

    @Override
    public void set(Book book) {
        bookAdapter.set(book);
    }
}

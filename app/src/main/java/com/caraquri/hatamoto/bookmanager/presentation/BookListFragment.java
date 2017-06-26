package com.caraquri.hatamoto.bookmanager.presentation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

    @Inject
    BookListPresenter bookListPresenter;

    @BindView(R.id.book_list_recycler_view)
    RecyclerView bookListRecyclerView;

    private BookAdapter bookAdapter;

    public BookListFragment() {
        // Required empty public constructor
    }

    public static BookListFragment newInstance() {
        return new BookListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bookAdapter = new BookAdapter() {
            @Override
            protected void onItemClicked(@NonNull Book book) {
                super.onItemClicked(book);
                startActivity(EditBookActivity.createIntent(getActivity(), book));
            }
        };
        bookListRecyclerView.setHasFixedSize(true);
        bookListRecyclerView.setAdapter(bookAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        App.getAppComponent(getActivity()).inject(this);
        bookListPresenter.attachView(this);
        bookListPresenter.load();
    }

    @Override
    public void onDestroyView() {
        bookListPresenter.detachView();
        super.onDestroyView();
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
    public void set(Book book) {
        bookAdapter.set(book);
    }
}

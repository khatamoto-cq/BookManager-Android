package com.caraquri.hatamoto.bookmanager.presentation.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.caraquri.hatamoto.bookmanager.R;
import com.caraquri.hatamoto.bookmanager.domain.entity.Book;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    private List<Book> books;

    protected void onItemClicked(@NonNull Book book) {
    }

    @Override
    public BookAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.item_book, parent, false);
        final BookAdapter.ViewHolder holder = new BookAdapter.ViewHolder(view);
        ButterKnife.bind(this, view);

        holder.row.setOnClickListener(v -> {
            final int position = holder.getAdapterPosition();
            final Book book = books.get(position);
            onItemClicked(book);
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(BookAdapter.ViewHolder holder, int position) {
        if (position < books.size()) {
            final Book book = books.get(position);
            holder.name.setText(book.getName());
            holder.price.setText(book.getPrice());
            holder.date.setText(book.getPurchaseDate());
        }
    }

    @Override
    public int getItemCount() {
        return books == null ? 0 : books.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.card_view)
        CardView row;
        @BindView(R.id.imageView)
        ImageView imageView;
        @BindView(R.id.name)
        public TextView name;
        @BindView(R.id.price)
        public TextView price;
        @BindView(R.id.date)
        public TextView date;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void set(List<Book> books) {
        if (!books.isEmpty()) {
            books.clear();
        }
        books.addAll(books);
        notifyDataSetChanged();
    }

    public Book get(int position) {
        return (books != null && !books.isEmpty()) ? books.get(position) : null;
    }
}

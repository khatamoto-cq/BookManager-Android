package com.caraquri.hatamoto.bookmanager.presentation.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.caraquri.hatamoto.bookmanager.R;
import com.caraquri.hatamoto.bookmanager.domain.entity.Book;
import com.caraquri.hatamoto.bookmanager.util.DateUtils;

import org.threeten.bp.LocalDateTime;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    private List<Book> books = new ArrayList<>();
    private final Object lock = new Object();
    private Context context;

    protected void onItemClicked(@NonNull Book book) {
    }

    @Override
    public BookAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.item_book, parent, false);
        final BookAdapter.ViewHolder holder = new BookAdapter.ViewHolder(view);
        this.context = parent.getContext();
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
            if (!TextUtils.isEmpty(book.getImageUrl())) {
                Glide.with(context).load(book.getImageUrl()).into(holder.imageView);
            }

            holder.name.setText(book.getName());

            if (book.getPrice() > 0) {
                holder.price.setText(context.getString(R.string.text_book_price, book.getPrice()));
            }

            if (!TextUtils.isEmpty(book.getPurchaseDate())){
                holder.date.setText(DateUtils.getFormatedDate(book.getPurchaseDate()));
            }
        }
    }

    @Override
    public int getItemCount() {
        return books == null ? 0 : books.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.card_view)
        CardView row;
        @BindView(R.id.image_view)
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

    public void set(@NonNull List<Book> books) {
        synchronized (lock) {
            this.books.addAll(books);
            notifyDataSetChanged();
        }
    }

    public Book get(int position) {
        return (books != null && !books.isEmpty()) ? books.get(position) : null;
    }

    public void set(@NonNull Book book) {
        synchronized (lock) {
            final int position = books.size();
            books.add(book);
            notifyItemInserted(position);
        }
    }

    public void clear() {
        books.clear();
    }
}

package com.demo.mybook;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import com.bumptech.glide.Glide;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    private Context context;
    private List<Book> bookList;
    private String parentActivity;

    public BookAdapter(Context context, List<Book> bookList) {
        this.context = context;
        this.bookList = bookList;
    }

    public BookAdapter(Context context, List<Book> bookList, String parentActivity) {
        this.context = context;
        this.bookList = bookList;
        this.parentActivity = parentActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_book, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Book book = bookList.get(position);
        holder.bookNameTV.setText(book.getName());
        Glide.with(context)
                .asBitmap()
                .load(book.getImageUrl())
                .into(holder.bookImageIV);
        holder.authorNameTV.setText(book.getAuthor());
        holder.shortDescription.setText(book.getShortDesc());

        if (book.isExpended()) {
            TransitionManager.beginDelayedTransition(holder.cardView);
            holder.expendableLayout.setVisibility(View.VISIBLE);
            holder.arrowDownIV.setVisibility(View.GONE);

            if (parentActivity.equals("allBooks")) {
                holder.deleteTV.setVisibility(View.GONE);
            } else if (parentActivity.equals("alreadyReading")) {

                holder.deleteTV.setVisibility(View.VISIBLE);
                holder.deleteTV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /*if (Utils.getInstance().deleteFromAlreadyBook(book)){
                            Toast.makeText(context, book.getName() + " removed", Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();
                        }else {
                            Toast.makeText(context, "Something is wrong, Try again", Toast.LENGTH_SHORT).show();
                        }*/

                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("Are you sure want to delete " + book.getName() + "?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (Utils.getInstance().deleteFromAlreadyBook(book)) {
                                    Toast.makeText(context, book.getName() + " removed", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });

                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        builder.create().show();

                    }
                });

            } else if (parentActivity.equals("wantToRead")) {

                holder.deleteTV.setVisibility(View.VISIBLE);
                holder.deleteTV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /*if (Utils.getInstance().deleteFromAlreadyBook(book)){
                            Toast.makeText(context, book.getName() + " removed", Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();
                        }else {
                            Toast.makeText(context, "Something is wrong, Try again", Toast.LENGTH_SHORT).show();
                        }*/

                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("Are you sure want to delete " + book.getName() + "?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (Utils.getInstance().deleteFromWantToReadBook(book)) {
                                    Toast.makeText(context, book.getName() + " removed", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });

                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        builder.create().show();

                    }
                });

            } else if (parentActivity.equals("currentlyReading")) {

                holder.deleteTV.setVisibility(View.VISIBLE);
                holder.deleteTV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /*if (Utils.getInstance().deleteFromAlreadyBook(book)){
                            Toast.makeText(context, book.getName() + " removed", Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();
                        }else {
                            Toast.makeText(context, "Something is wrong, Try again", Toast.LENGTH_SHORT).show();
                        }*/

                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("Are you sure want to delete " + book.getName() + "?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (Utils.getInstance().deleteFromCurrentlyReadingBook(book)) {
                                    Toast.makeText(context, book.getName() + " removed", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });

                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        builder.create().show();

                    }
                });

            } else if (parentActivity.equals("favouriteBook")){

                holder.deleteTV.setVisibility(View.VISIBLE);
                holder.deleteTV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /*if (Utils.getInstance().deleteFromAlreadyBook(book)){
                            Toast.makeText(context, book.getName() + " removed", Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();
                        }else {
                            Toast.makeText(context, "Something is wrong, Try again", Toast.LENGTH_SHORT).show();
                        }*/

                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("Are you sure want to delete " + book.getName() + "?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (Utils.getInstance().deleteFromFavouriteBook(book)) {
                                    Toast.makeText(context, book.getName() + " removed", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });

                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        builder.create().show();

                    }
                });

            }

        } else {
            TransitionManager.beginDelayedTransition(holder.cardView);
            holder.expendableLayout.setVisibility(View.GONE);
            holder.arrowDownIV.setVisibility(View.VISIBLE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, BookActivity.class);
                intent.putExtra("bookId", book.getId());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private ImageView bookImageIV, arrowUpIV, arrowDownIV;
        private TextView bookNameTV, authorNameTV, shortDescription, deleteTV;
        private LinearLayout expendableLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            bookImageIV = itemView.findViewById(R.id.bookImageTV);
            bookNameTV = itemView.findViewById(R.id.bookNameTV);
            arrowDownIV = itemView.findViewById(R.id.arrowDownIV);
            arrowUpIV = itemView.findViewById(R.id.arrowUpIV);
            authorNameTV = itemView.findViewById(R.id.authorNameTV);
            deleteTV = itemView.findViewById(R.id.deleteTV);
            shortDescription = itemView.findViewById(R.id.shortDescription);
            expendableLayout = itemView.findViewById(R.id.expandableLayout);

            arrowDownIV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Book book = bookList.get(getAdapterPosition());
                    book.setExpended(!book.isExpended());
                    notifyDataSetChanged();
                }
            });
            arrowUpIV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Book book = bookList.get(getAdapterPosition());
                    book.setExpended(!book.isExpended());
                    notifyDataSetChanged();
                }
            });

        }
    }
}

package com.demo.mybook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

public class BookActivity extends AppCompatActivity {

    private ImageView bookImageIV;
    private Button currentlyReadingBtn, wantToReadBtn, alreadyReadBtn, addToFavouritesBtn;
    private TextView bookNameTV, authorNameTV, pageSizeTV,longDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        init();
        Intent intent = getIntent();

        if (null != intent){
            int bookId = intent.getIntExtra("bookId", -1);
            if (bookId != -1){
                Book incomingBook = Utils.getInstance().getBookById(bookId);
                if (null != incomingBook){
                    setBook(incomingBook);

                    handleAlreadyRead(incomingBook);
                    handleWantToReadBooks(incomingBook);
                    handleCurrentlyReadingBooks(incomingBook);
                    handleFavouriteBooks(incomingBook);
                }
            }
        }

    }

    private void handleFavouriteBooks(final Book book) {
        List<Book> favouriteBooks = Utils.getInstance().getFavoriteBooks();

        boolean existFavouriteBooks = false;

        for (Book b: favouriteBooks){
            if (b.getId() == book.getId()){
                existFavouriteBooks = true;
            }
        }

        if (existFavouriteBooks){
            addToFavouritesBtn.setEnabled(false);
        }else {
            addToFavouritesBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (Utils.getInstance().addToFavouriteBook(book)){
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(BookActivity.this, FavouriteBooksActivity.class));
                    } else {
                        Toast.makeText(BookActivity.this, "Something is wrong, try again", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }

    private void handleCurrentlyReadingBooks(final Book book) {

        List<Book> currentlyReadingBook = Utils.getInstance().getCurrentlyReadingBooks();

        boolean existCurrentlyReadingBooks = false;

        for (Book b: currentlyReadingBook){
            if (b.getId() == book.getId()){
                existCurrentlyReadingBooks = true;
            }
        }

        if (existCurrentlyReadingBooks){
            currentlyReadingBtn.setEnabled(false);
        }else {
            currentlyReadingBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (Utils.getInstance().addToCurrentlyReadingBook(book)){
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(BookActivity.this, CurrentlyReadingBooksActivity.class));
                    } else {
                        Toast.makeText(BookActivity.this, "Something is wrong, try again", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }


    }

    private void handleWantToReadBooks(final Book book) {

        List<Book> wantToReadBook = Utils.getInstance().getWantToReadBooks();

        boolean existWantToReadBooks = false;

        for (Book b: wantToReadBook){
            if (b.getId() == book.getId()){
                existWantToReadBooks = true;
            }
        }

        if (existWantToReadBooks){
            wantToReadBtn.setEnabled(false);
        }else {
            wantToReadBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (Utils.getInstance().addToWantToReadBook(book)){
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(BookActivity.this, WantToReadBooksActivity.class));
                    } else {
                        Toast.makeText(BookActivity.this, "Something is wrong, try again", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }


    }

    private void handleAlreadyRead(final Book book) {

        List<Book> alreadyReadBooks = Utils.getInstance().getAlreadyReadBooks();

        boolean existAlreadyReadBooks = false;

        for (Book b : alreadyReadBooks){
            if (b.getId() == book.getId()){
                existAlreadyReadBooks = true;
            }
        }

        if (existAlreadyReadBooks){
            alreadyReadBtn.setEnabled(false);
        }else {
            alreadyReadBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance().addToAlreadyRead(book)){
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(BookActivity.this, AlreadyReadBooksActivity.class));
                    } else {
                        Toast.makeText(BookActivity.this, "Something is wrong, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


    }

    private void setBook(Book book) {

        bookNameTV.setText(book.getName());
        authorNameTV.setText(book.getAuthor());
        pageSizeTV.setText(String.valueOf(book.getPages()));
        longDescription.setText(book.getLongDesc());
        Glide.with(this)
                .asBitmap()
                .load(book.getImageUrl())
                .into(bookImageIV);

    }

    private void init() {

        bookImageIV = findViewById(R.id.bookImageTV);
        currentlyReadingBtn = findViewById(R.id.currentlyReadingBtn);
        wantToReadBtn = findViewById(R.id.wantToReadBtn);
        alreadyReadBtn = findViewById(R.id.alreadyReadBtn);
        addToFavouritesBtn = findViewById(R.id.addToFavouritesBtn);
        bookNameTV = findViewById(R.id.bookNameTV);
        authorNameTV = findViewById(R.id.authorNameTV);
        pageSizeTV = findViewById(R.id.pageSizeTV);
        longDescription = findViewById(R.id.longDescription);

    }
}

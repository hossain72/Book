package com.demo.mybook;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button seeAllBooksBtn, currentReadingBooksBtn, alreadyReadBooksBtn, yourWatchlistBtn, seeYourFavoritesBtn, aboutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seeAllBooksBtn = findViewById(R.id.seeAllBooksBtn);
        currentReadingBooksBtn = findViewById(R.id.currentReadingBooksBtn);
        alreadyReadBooksBtn = findViewById(R.id.alreadyReadBooksBtn);
        yourWatchlistBtn = findViewById(R.id.yourWatchlistBtn);
        seeYourFavoritesBtn = findViewById(R.id.seeYourFavoritesBtn);
        aboutBtn = findViewById(R.id.aboutBtn);

        seeAllBooksBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seeAllBooks();
            }
        });

        alreadyReadBooksBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AlreadyReadBooksActivity.class));
            }
        });

        yourWatchlistBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, WantToReadBooksActivity.class));
            }
        });

        currentReadingBooksBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CurrentlyReadingBooksActivity.class));
            }
        });

        seeYourFavoritesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FavouriteBooksActivity.class));
            }
        });

        aboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(getString(R.string.app_name));
                builder.setMessage("Designed and Developed with Love by Anowar Hoosain\n" +
                        "Check my website for more awesome applications:");

                builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.setPositiveButton("Visit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(MainActivity.this, WebsiteActivity.class));
                    }
                });

                builder.create().show();

            }
        });

    }

    private void seeAllBooks() {
        startActivity(new Intent(MainActivity.this, AllBooksActivity.class));
    }

}

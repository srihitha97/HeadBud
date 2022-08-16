package com.pacman.Headbud.ui.home;

import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.pacman.Headbud.R;
import com.pacman.Headbud.ui.menu.WallpaperFragment;

import java.util.Random;

public class HomeActivity extends MainActivity {
    private int theme;

    private TextView quote;
    private QuotesModel mQuotes = new QuotesModel();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final FrameLayout contentFrameLayout = findViewById(R.id.frag_container);
        WallpaperFragment w = new WallpaperFragment();
        this.theme = w.theme;
        getLayoutInflater().inflate(R.layout.wallpaperlayout, contentFrameLayout);
        quote = (TextView) findViewById(R.id.motivational_quote);
        Random num = new Random();
        int quoteNum = num.nextInt(mQuotes.getLength());
        quote.setText(mQuotes.getQuote(quoteNum));
        if (this.theme != -1) {
            getLayoutInflater().inflate(R.layout.wallpaperlayout, contentFrameLayout);
            AppCompatImageView view = findViewById(R.id.imageView);
            view.setImageResource(this.theme);
        }
        else{
            getLayoutInflater().inflate(R.layout.defaultwallpaperlayout, contentFrameLayout);
        }
    }
}

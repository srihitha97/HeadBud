package com.pacman.Headbud.ui.helpline;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.pacman.Headbud.R;
import com.pacman.Headbud.ui.home.MainActivity;

public class helplineActivity extends MainActivity {

    private static final int REQUEST_CALL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = findViewById(R.id.frag_container);
        getLayoutInflater().inflate(R.layout.activity_helpline, contentFrameLayout);

        findViewById(R.id.kidsHelpCall).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                call("tel:1-866-925-5454");
            }
        });

        findViewById(R.id.kidsHelpVisit).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                visit("https://good2talk.ca/");
            }
        });

        findViewById(R.id.safeHavenCall).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                call("tel:403-223-0483");
            }
        });

        findViewById(R.id.safeHavenVisit).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                visit("https://www.tabersafehaven.ca");
            }
        });

        findViewById(R.id.suicideCall).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                call("tel:988");
            }
        });

        findViewById(R.id.suicideVisit).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                visit("https://988lifeline.org/");
            }
        });
    }





    public void call(String phone) {
        if (ContextCompat.checkSelfPermission(helplineActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(helplineActivity.this, new String[] {Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        }

        else {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse(phone));
            startActivity(callIntent);
        }
    }

    public void visit(String website) {

        Intent viewIntent = new Intent(Intent.ACTION_VIEW);
        viewIntent.setData(Uri.parse(website));
        startActivity(viewIntent);
    }
}

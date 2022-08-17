package com.pacman.Headbud.ui.meditation;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pacman.Headbud.R;
import com.pacman.Headbud.ui.home.MainActivity;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class AmbientActivity extends MainActivity implements Observer {

    private static final String TAG = "Ambient Activity";
    public static final String Broadcast_PLAY_NEW_AUDIO = "com.pacman.MentAlly.ui.ambient.PlayNewAudio";
    public static final String Broadcast_PAUSE_AUDIO = "com.pacman.MentAlly.ui.ambient.PauseAudio";

    private AudioAdapter adapter;
    private ArrayList<AudioFile> audioList = new ArrayList<>();
    private AudioFile nowPlaying = null;

    private MediaPlaybackService audioService;
    private boolean serviceBound = false;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MediaPlaybackService.LocalBinder binder = (MediaPlaybackService.LocalBinder) service;
            audioService = binder.getService();
            serviceBound = true;
//            Toast.makeText(AmbientActivity.this, "Service Bound", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            serviceBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_ambient);
        FrameLayout contentFrameLayout = findViewById(R.id.frag_container);
        getLayoutInflater().inflate(R.layout.activity_ambient, contentFrameLayout);
        loadAudio();
    }

    private void loadAudio() {
        audioList.add(new AudioFile("moonlight_sonata", "Moonlight Sonata", "5:21"));
        audioList.add(new AudioFile("bird_ambience", "Bird Song", "1:55"));
        audioList.add(new AudioFile("campfire", "Campfire", "1:54"));
        audioList.add(new AudioFile("forest1", "Forest1", "2:40"));
        audioList.add(new AudioFile("ocean_waves", "Ocean Waves", "2:30"));

        for (int i=0; i<audioList.size(); i++) {
            audioList.get(i).addObserver(this);
            Log.d(TAG, "loadAudio: "+audioList.get(i).getTitle());
        }
        initRecyclerView();
    }

    private void initRecyclerView() {
        Log.d(TAG, "Initialize recycler view");
        RecyclerView audioListView = findViewById(R.id.audioList);
        adapter = new AudioAdapter(this, audioList);
        audioListView.setAdapter(adapter);
        audioListView.setLayoutManager(new LinearLayoutManager(this));
        adapter.notifyDataSetChanged();
    }

    private void playAudio(AudioFile media) {
        if (!serviceBound) {
            Intent playerIntent = new Intent(this, MediaPlaybackService.class);
            playerIntent.putExtra("media", media.getData());
            startService(playerIntent);
            bindService(playerIntent, serviceConnection, Context.BIND_AUTO_CREATE);
        } else {
            // Service is active
            // Send media with BroadcastReciever and update view
            if (nowPlaying != null) {
                nowPlaying.stopPlaying();
                adapter.notifyDataSetChanged();
            }
            Intent broadcastIntent = new Intent(Broadcast_PLAY_NEW_AUDIO);
            broadcastIntent.putExtra("media", media.getData());
            sendBroadcast(broadcastIntent);
        }
        nowPlaying = media;
    }

    private void pauseAudio() {
        if (! serviceBound) {
            return;
        }
        // Service is active
        // Ask player to stop playing audio with BroadcastReciever
        Intent broadcastIntent = new Intent(Broadcast_PAUSE_AUDIO);
        sendBroadcast(broadcastIntent);
        nowPlaying = null;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBoolean("ServiceState", serviceBound);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        serviceBound = savedInstanceState.getBoolean("ServiceState");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (serviceBound) {
            unbindService(serviceConnection);
            audioService.stopSelf();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        AudioFile audioFile = (AudioFile) arg;
        if (audioFile.isPlaying()) {
            playAudio(audioFile);
        } else {
            pauseAudio();
        }
    }
}

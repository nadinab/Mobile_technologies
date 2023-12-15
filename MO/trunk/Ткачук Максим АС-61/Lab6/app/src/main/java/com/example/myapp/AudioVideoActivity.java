package com.example.myapp;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class AudioVideoActivity extends AppCompatActivity {

    private Button playAudioButton;
    private Button playVideoButton;
    private VideoView videoView;

    private MediaPlayer mediaPlayer;
    private MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_video);

        // Initialize the views
        playAudioButton = findViewById(R.id.play_audio_button);
        playVideoButton = findViewById(R.id.play_video_button);
        videoView = findViewById(R.id.video_view);

        // Create a media controller for the audio
        mediaController = new MediaController(this);

        // Set the listener for the play audio button
        playAudioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a media player for the audio file
                mediaPlayer = MediaPlayer.create(AudioVideoActivity.this, R.raw.audio_file);

                // Set the media controller to the media player
                mediaController.setMediaPlayer(new MediaController.MediaPlayerControl() {
                    @Override
                    public void start() {
                        mediaPlayer.start();
                    }

                    @Override
                    public void pause() {
                        mediaPlayer.pause();
                    }

                    @Override
                    public int getDuration() {
                        return mediaPlayer.getDuration();
                    }

                    @Override
                    public int getCurrentPosition() {
                        return mediaPlayer.getCurrentPosition();
                    }

                    @Override
                    public void seekTo(int pos) {
                        mediaPlayer.seekTo(pos);
                    }

                    @Override
                    public boolean isPlaying() {
                        return mediaPlayer.isPlaying();
                    }

                    @Override
                    public int getBufferPercentage() {
                        return 0;
                    }

                    @Override
                    public boolean canPause() {
                        return true;
                    }

                    @Override
                    public boolean canSeekBackward() {
                        return true;
                    }

                    @Override
                    public boolean canSeekForward() {
                        return true;
                    }

                    @Override
                    public int getAudioSessionId() {
                        return 0;
                    }
                });

                // Set the anchor view to the bottom of the screen
                mediaController.setAnchorView(findViewById(android.R.id.content));

                // Start playing the audio and show the media controller with no timeout
                mediaPlayer.start();
                mediaController.show(0);

                // Show a toast message
                Toast.makeText(AudioVideoActivity.this, "Воспроизвести аудио", Toast.LENGTH_SHORT).show();
            }
        });

        // Set the listener for the play video button
        playVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a media controller for the video view
                MediaController mediaController = new MediaController(AudioVideoActivity.this);
                mediaController.setAnchorView(videoView);
                // Set the media controller to the video view
                videoView.setMediaController(mediaController);
                // Set the video source to the video file
                videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video_file));
                // Start playing the video and show the media controller with no timeout
                videoView.start();
                mediaController.show(0);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Stop and release the media player if it is not null
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}

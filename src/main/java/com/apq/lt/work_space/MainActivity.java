package com.apq.lt.work_space;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button play;
    private HDMIApplication.MyPresentation myPresentation = null;
    private HDMIApplication hdmiApplication = null;
    private String[] filesPath = null;
    private List<String> videoFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play = (Button) findViewById(R.id.button);
        play.setOnClickListener(this);

        this.hdmiApplication = (HDMIApplication) getApplication();
        this.myPresentation = this.hdmiApplication.getPresentation();

        this.filesPath = new File("/storage/emulated/legacy/").list(new FilenameFilter() {
            @Override
            public boolean accept(File file, String s) {
                return (s.endsWith(".mp4"));
            }
        });
        this.videoFile = new ArrayList<>();
    }

    //点击播放按钮监听事件
    @Override
    public void onClick(View view) {
        for (String name : this.filesPath) {
            this.videoFile.add("/storage/emulated/legacy/" + name);
        }
        this.myPresentation.startVideo(this.videoFile.get(0));
    }
}

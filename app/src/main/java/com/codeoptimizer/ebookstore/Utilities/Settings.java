package com.codeoptimizer.ebookstore.Utilities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;

import com.codeoptimizer.ebookstore.R;

public class Settings extends AppCompatActivity {

    Toolbar SystemToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Intent i = new Intent();
        i.getStringExtra("Settings");
        SystemToolbar = (Toolbar)findViewById(R.id.SettingToolbar);
    }
}



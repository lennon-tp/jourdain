package com.example.jourdain.ui.audio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jourdain.R;

public class AudioListActivity extends AppCompatActivity {

    private ListView listView;
    private String[] audioFiles = {
            "cantique2",
            "cantique5",
            "cantique8",
            "cantique9",
            "cantique11",
            "cantique12",
            "cantique16",
            "cantique17",
            "cantique24",
            "cantique28",
            "cantique31",
            "cantique33",
            "cantique35",
            "cantique40",
            "cantique41",
            "cantique43",
            "cantique47",
            "cantique52",
            "cantique58",
            "cantique63",
            "cantique64",
            "cantique65",
            "cantique71",
            "cantique72",
            "cantique84",
            "cantique86",
            "cantique87",
            "cantique96",
            "cantique99",
            "cantique103",
            "cantique108",
            "cantique109",
            "cantique110",
            "cantique112",
            "cantique116",
            "cantique121",
            "cantique125",
            "cantique127",
            "cantique131",
            "cantique140",
            "cantique145",
            "cantique151",
            "cantique161",
            "cantique163",
            "cantique166",
            "cantique178",
            "cantique182",
            "cantique189",
            "cantique190",
            "cantique193",
            "cantique199",
            "cantique202",
            "cantique209",
            "cantique210",
            "cantique212",
            "cantique216",
            "cantique222",
            "cantique224",
            "cantique230",
            "cantique233",
            "cantique234",
            "cantique239",
            "cantique245",
            "cantique255",
            "cantique262",
            "cantique273",
            "cantique279",
            "cantique281",
            "cantique282",
            "cantique284",
            "cantique285",
            "cantique291",
            "cantique293",
            "cantique315",
            "cantique322",
            "cantique325",
            "cantique326",
            "cantique331",
            "cantique347",
            "cantique350",
            "cantique357",
            "cantique359",
            "cantique367",
            "cantique385",
            "cantique388",
            "cantique389",
            "cantique403",
            "cantique404",
            "cantique407",
            "cantique414",
            "cantique423",
            "cantique424",
            "cantique442",
            "cantique443",
            "cantique468",
            "cantique475",
            "cantique484",
            "cantique507",
            "cantique513",
            "cantique518",
            "cantique521",
            "cantique522",
            "cantique524"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_list);

        listView = findViewById(R.id.listViewAudio);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, audioFiles);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(AudioListActivity.this, AudioPlayerActivity.class);
            intent.putExtra("audioFile", audioFiles[position]);
            startActivity(intent);
        });
    }
}

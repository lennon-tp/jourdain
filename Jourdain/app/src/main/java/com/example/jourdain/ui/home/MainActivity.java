package com.example.jourdain.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jourdain.R;
import com.example.jourdain.ui.audio.AudioListActivity;
import com.example.jourdain.ui.pdfviewer.PdfActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView btnCantique, btnLivret;
    private Button btnAudio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisation des boutons
        btnCantique = findViewById(R.id.btnCantique);
        btnLivret = findViewById(R.id.btnLivret);
        btnAudio = findViewById(R.id.btnAudio);

        // Clic sur Cantique -> PdfActivity avec paramètre
        btnCantique.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PdfActivity.class);
            intent.putExtra("pdf_file", "cantique.pdf");
            startActivity(intent);
        });

        // Clic sur Livret -> PdfActivity avec paramètre
        btnLivret.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PdfActivity.class);
            intent.putExtra("pdf_file", "livret.pdf");
            startActivity(intent);
        });

        // Clic sur Audio -> AudioListActivity
        btnAudio.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AudioListActivity.class);
            startActivity(intent);
        });
    }
}

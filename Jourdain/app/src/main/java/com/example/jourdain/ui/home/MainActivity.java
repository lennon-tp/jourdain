package com.example.jourdain.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jourdain.R;
import com.example.jourdain.ui.audio.AudioListActivity;
import com.example.jourdain.ui.pdfviewer.PdfActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView btnCantique, btnLivret, headerImage;
    private Button btnAudio;

    // Tableau de tes images
    private int[] headerImages = {
            R.drawable.header_image1,
            R.drawable.header_image2,
            R.drawable.header_image3
    };

    private int currentImageIndex = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Header
        headerImage = findViewById(R.id.headerImage);

        // Boutons
        btnCantique = findViewById(R.id.btnCantique);
        btnLivret = findViewById(R.id.btnLivret);
        btnAudio = findViewById(R.id.btnAudio);

        // Clic sur Cantique -> PdfActivity
        btnCantique.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PdfActivity.class);
            intent.putExtra("pdfName", "cantique.pdf");
            startActivity(intent);
        });

        // Clic sur Livret -> PdfActivity
        btnLivret.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PdfActivity.class);
            intent.putExtra("pdfName", "livret.pdf");
            startActivity(intent);
        });

        // Clic sur Audio -> AudioListActivity
        btnAudio.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AudioListActivity.class);
            startActivity(intent);
        });

        // Lancer l’alternance automatique du header
        startHeaderImageRotation();
    }

    private void startHeaderImageRotation() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Passe à l'image suivante
                currentImageIndex = (currentImageIndex + 1) % headerImages.length;
                headerImage.setImageResource(headerImages[currentImageIndex]);

                // Relancer dans 10 secondes
                handler.postDelayed(this, 10000);
            }
        }, 5000);
    }

    public void openFacebook(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.facebook.com/profile.php?id=61560971694695"));
        startActivity(intent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Nettoyage pour éviter une fuite mémoire
        handler.removeCallbacksAndMessages(null);
    }
}

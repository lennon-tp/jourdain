package com.example.jourdain.ui.pdfviewer;

import android.os.Bundle;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.jourdain.adapter.PdfPagerAdapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class PdfActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private SeekBar seekBar;
    private PdfPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);

        viewPager = findViewById(R.id.viewPager);
        seekBar = findViewById(R.id.seekBar);

        // Récupérer le nom du PDF passé en Intent
        String pdfName = getIntent().getStringExtra("pdfName"); // "cantique.pdf" ou "livret.pdf"

        try {
            File pdfFile = new File(getCacheDir(), pdfName);
            copyAssetToFile(pdfName, pdfFile);

            adapter = new PdfPagerAdapter(this, pdfFile);
            viewPager.setAdapter(adapter);

            // Configurer SeekBar
            seekBar.setMax(adapter.getItemCount() - 1);
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if (fromUser) {
                        viewPager.setCurrentItem(progress, true);
                    }
                }
                @Override public void onStartTrackingTouch(SeekBar seekBar) {}
                @Override public void onStopTrackingTouch(SeekBar seekBar) {}
            });

            // Sync SeekBar avec swipe
            viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageSelected(int position) {
                    seekBar.setProgress(position);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void copyAssetToFile(String assetName, File outFile) throws IOException {
        if (outFile.exists()) return; // déjà copié
        try (InputStream is = getAssets().open(assetName);
             FileOutputStream os = new FileOutputStream(outFile)) {
            byte[] buffer = new byte[1024];
            int read;
            while ((read = is.read(buffer)) != -1) {
                os.write(buffer, 0, read);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            if (adapter != null) adapter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

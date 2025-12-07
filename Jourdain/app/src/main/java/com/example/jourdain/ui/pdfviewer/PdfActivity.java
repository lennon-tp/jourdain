package com.example.jourdain.ui.pdfviewer;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jourdain.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;

public class PdfActivity extends AppCompatActivity {

    private PDFView pdfView;
    private SeekBar seekBar;
    private TextView pageIndicator;

    private int totalPages = 0;
    private int currentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);

        pdfView = findViewById(R.id.pdfView);
        seekBar = findViewById(R.id.seekBar);
        pageIndicator = findViewById(R.id.pageIndicator);

        // Récupérer le nom du PDF depuis l'intent
        String pdfFileName = getIntent().getStringExtra("pdf_file");

        // Charger le PDF depuis assets
        pdfView.fromAsset(pdfFileName)
                .defaultPage(0)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .onPageChange(new OnPageChangeListener() {
                    @Override
                    public void onPageChanged(int page, int pageCount) {
                        currentPage = page;
                        totalPages = pageCount;
                        seekBar.setMax(pageCount - 1);
                        seekBar.setProgress(page);
                        pageIndicator.setText((page + 1) + " / " + pageCount);
                    }
                })
                .load();

        // SeekBar pour navigation rapide
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser) {
                    pdfView.jumpTo(progress, true);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });
    }
}

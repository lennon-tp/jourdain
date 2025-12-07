package com.example.jourdain.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.os.ParcelFileDescriptor;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.IOException;

public class PdfPagerAdapter extends RecyclerView.Adapter<PdfPagerAdapter.PageViewHolder> {

    private Context context;
    private PdfRenderer pdfRenderer;
    private ParcelFileDescriptor parcelFileDescriptor;

    public PdfPagerAdapter(Context context, File pdfFile) throws IOException {
        this.context = context;
        parcelFileDescriptor = ParcelFileDescriptor.open(pdfFile, ParcelFileDescriptor.MODE_READ_ONLY);
        pdfRenderer = new PdfRenderer(parcelFileDescriptor);
    }

    @NonNull
    @Override
    public PageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        ));
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        return new PageViewHolder(imageView);
    }

    @Override
    public void onBindViewHolder(@NonNull PageViewHolder holder, int position) {
        PdfRenderer.Page page = pdfRenderer.openPage(position);
        Bitmap bitmap = Bitmap.createBitmap(page.getWidth(), page.getHeight(), Bitmap.Config.ARGB_8888);
        page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
        holder.imageView.setImageBitmap(bitmap);
        page.close();
    }

    @Override
    public int getItemCount() {
        return pdfRenderer.getPageCount();
    }

    public void close() throws IOException {
        pdfRenderer.close();
        parcelFileDescriptor.close();
    }

    static class PageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public PageViewHolder(@NonNull ImageView itemView) {
            super(itemView);
            imageView = itemView;
        }
    }
}

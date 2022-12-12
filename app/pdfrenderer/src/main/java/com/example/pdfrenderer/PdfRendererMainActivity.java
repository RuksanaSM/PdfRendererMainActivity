package com.example.pdfrenderer;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.os.Build;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PdfRendererMainActivity extends AppCompatActivity {

    @BindView(R.id.pdf_image)
    ImageView imageViewPdf;

    private static final String FILENAME = "samplepdf.pdf";

    private int pageIndex;
    private PdfRenderer pdfRenderer;
    private PdfRenderer.Page currentPage;
    private ParcelFileDescriptor parcelFileDescriptor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_renderer_main);
        ButterKnife.bind(this);
        pageIndex = 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onStart() {
        super.onStart();
        try {
            openRenderer(getApplicationContext());
            showPage(pageIndex);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onStop() {
        try {
            closeRenderer();
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onStop();
    }

//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    @OnClick(R.id.button_pre_doc)
//    public void onPreviousDocClick(){
//        showPage(currentPage.getIndex() - 1);
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    @OnClick(R.id.button_next_doc)
//    public void onNextDocClick(){
//        showPage(currentPage.getIndex() + 1);
//    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void openRenderer(Context context) throws IOException {
        // In this sample, we read a PDF from the assets directory.
        File file = new File(context.getCacheDir(), FILENAME);
        if (!file.exists()) {
            // Since PdfRenderer cannot handle the compressed asset file directly, we copy it into
            // the cache directory.
            InputStream asset = context.getAssets().open(FILENAME);
            FileOutputStream output = new FileOutputStream(file);
            final byte[] buffer = new byte[1024];
            int size;
            while ((size = asset.read(buffer)) != -1) {
                output.write(buffer, 0, size);
            }
            asset.close();
            output.close();
        }
        parcelFileDescriptor = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY);
        // This is the PdfRenderer we use to render the PDF.
        if (parcelFileDescriptor != null) {
            pdfRenderer = new PdfRenderer(parcelFileDescriptor);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void closeRenderer() throws IOException {
        if (null != currentPage) {
            currentPage.close();
        }
        pdfRenderer.close();
        parcelFileDescriptor.close();
    }


    private void showPage(int index) {
        ArrayList<Bitmap> arraybitmap=new ArrayList<>();
        if (pdfRenderer.getPageCount() <= index) {
            return;
        }
        // Make sure to close the current page before opening another one.
        if (null != currentPage) {
            currentPage.close();
        }
        final  int pageCount = pdfRenderer.getPageCount();
        for (int i = 0; i < pageCount; i++) {
            currentPage = pdfRenderer.openPage(i);
            Bitmap bitmap = Bitmap.createBitmap(currentPage.getWidth(), currentPage.getHeight(),
                    Bitmap.Config.ARGB_8888);
            currentPage.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
            arraybitmap.add(bitmap);
        }
        // Use `openPage` to open a specific page in PDF.
//        currentPage = pdfRenderer.openPage(index);
//        // Important: the destination bitmap must be ARGB (not RGB).
//        Bitmap bitmap = Bitmap.createBitmap(currentPage.getWidth(), currentPage.getHeight(),
//                Bitmap.Config.ARGB_8888);
//        // Here, we render the page onto the Bitmap.
//        // To render a portion of the page, use the second and third parameter. Pass nulls to get
//        // the default result.
//        // Pass either RENDER_MODE_FOR_DISPLAY or RENDER_MODE_FOR_PRINT for the last parameter.
//        currentPage.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
//        arraybitmap.add(bitmap);
        // We are ready to show the Bitmap to user.
        imageViewPdf.setImageBitmap(arraybitmap.get(0));
        //updateUi();
    }






    /**
     * Updates the state of 2 control buttons in response to the current page index.
     */
//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    private void updateUi() {
//        int index = currentPage.getIndex();
//        int pageCount = pdfRenderer.getPageCount();
//
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    public int getPageCount() {
//        return pdfRenderer.getPageCount();
//    }

}

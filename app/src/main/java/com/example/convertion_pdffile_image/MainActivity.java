package com.example.convertion_pdffile_image;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.pdf.PdfRenderer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
//
//import com.itextpdf.text.pdf.PdfReader;
//import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.shockwave.pdfium.PdfDocument;
import com.shockwave.pdfium.PdfiumCore;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

        // creating variables for
        // button and text view.
        private Button extractPDFBtn;
        private TextView extractedTV;
    File pdfFilePth,  DestinationFoldr;
    ImageView imageView;
    private static final String FILENAME = "india.pdf";
Context context;
    ArrayList<Bitmap> b=new ArrayList<>();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            imageView=findViewById(R.id.imgPDFTV);
            pdfFilePth = new File(context.getCacheDir(), FILENAME);

//DestinationFoldr=new File("C:/Users/RuksanaSMundaganur/Videos/Captures/sample_Image");
//            pdfFilePth= new File("/dummy.pdf");
            b = pdfToBitmap(pdfFilePth, this);
//            imageView.setImageBitmap(b.get(0));


//            renderToBitmap();
//            openPdf();
//            try {
////                getImagesFromPDF(pdfFilePth,DestinationFoldr);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }


//            // Getting images from Test.pdf file.
//            File source = new File(Environment.getExternalStorageDirectory() + "/storage/emulated/0/Download/" + "Nature" + ".pdf");
//
//            // Images will be saved in Test folder.
//            File destination = new File(Environment.getExternalStorageDirectory() + "/storage/emulated/0/Download/");
//
//            // Getting images from pdf in png format.
//            try {
//                getImagesFromPDF(source, destination);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            // initializing variables for button and text view.
            extractedTV = findViewById(R.id.idPDFTV);
//            extractPDFBtn = findViewById(R.id.idBtnExtract);


            // adding on click listener for button
//            extractPDFBtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    // calling method to extract
//                    // data from PDF file.
////                    extractPDF();
//                }
//            });
        }

//    private void getImagesFromPDF(File pdfFilePath, File DestinationFolder) throws IOException {
//
//
//        // Check if destination already exists then delete destination folder.
//        if(DestinationFolder.exists()){
//            DestinationFolder.delete();
//        }
//
//        // Create empty directory where images will be saved.
//        DestinationFolder.mkdirs();
//
//        // Reading pdf in READ Only mode.
//        ParcelFileDescriptor fileDescriptor = ParcelFileDescriptor.open(pdfFilePath, ParcelFileDescriptor.MODE_READ_ONLY);
//
//        // Initializing PDFRenderer object.
//        PdfRenderer renderer = new PdfRenderer(fileDescriptor);
//
//        // Getting total pages count.
//        final int pageCount = renderer.getPageCount();
//
//        // Iterating pages
//        for (int i = 0; i < pageCount; i++) {
//
//            // Getting Page object by opening page.
//            PdfRenderer.Page page = renderer.openPage(i);
//
//            // Creating empty bitmap. Bitmap.Config can be changed.
//            Bitmap bitmap = Bitmap.createBitmap(page.getWidth(), page.getHeight(),Bitmap.Config.ARGB_8888);
//
//            // Creating Canvas from bitmap.
//            Canvas canvas = new Canvas(bitmap);
//
//            // Set White background color.
//            canvas.drawColor(Color.WHITE);
//
//            // Draw bitmap.
//            canvas.drawBitmap(bitmap, 0, 0, null);
//
//            // Rednder bitmap and can change mode too.
//            page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
//
//            // closing page
//            page.close();
//
//            // saving image
//            File file = new File(DestinationFolder.getAbsolutePath(), "image"+i + ".png");
//
//            // check if file already exists, then delete it.
//            if (file.exists()) file.delete();
//
//            // Saving image in PNG format with 100% quality.
//            try {
//                FileOutputStream out = new FileOutputStream(file);
//                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
//
//                Log.v("Saved Image - ", file.getAbsolutePath());
//                out.flush();
//                out.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }


//    private void extractPDF() {
//            try {
//                // creating a string for
//                // storing our extracted text.
//                String extractedText = "";
//
//                // creating a variable for pdf reader
//                // and passing our PDF file in it.
//                PdfReader reader = new PdfReader("res/raw/dummy.pdf");
//
//                // below line is for getting number
//                // of pages of PDF file.
//                int n = reader.getNumberOfPages();
//
//                // running a for loop to get the data from PDF
//                // we are storing that data inside our string.
//                for (int i = 0; i < n; i++) {
//                    extractedText = extractedText + PdfTextExtractor.getTextFromPage(reader, i + 1).trim() + "\n";
//                    // to extract the PDF content from the different pages
//                }
//
//                // after extracting all the data we are
//                // setting that string value to our text view.
//                extractedTV.setText(extractedText);
//
//                // below line is used for closing reader.
//                reader.close();
//            } catch (Exception e) {
//                // for handling error while extracting the text file.
//                extractedTV.setText("Error found is : \n" + e);
//            }
//        }
//

//    public  List<Bitmap> renderToBitmap() {
//        List<Bitmap> images = new ArrayList<>();
//        PdfiumCore pdfiumCore = new PdfiumCore(getApplicationContext());
//        try {
//            File f = new File("/res/raw/india.pdf");
//            ParcelFileDescriptor fd = ParcelFileDescriptor.open(f, ParcelFileDescriptor.MODE_READ_ONLY);
//            PdfDocument pdfDocument = pdfiumCore.newDocument(fd);
//            final int pageCount = pdfiumCore.getPageCount(pdfDocument);
//            for (int i = 0; i < pageCount; i++) {
//                pdfiumCore.openPage(pdfDocument, i);
//                int width = pdfiumCore.getPageWidthPoint(pdfDocument, i);
//                int height = pdfiumCore.getPageHeightPoint(pdfDocument, i);
//                Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
//                pdfiumCore.renderPageBitmap(pdfDocument, bmp, i, 0, 0, width, height);
//                images.add(bmp);
//               imageView.setImageBitmap(bmp);
//            }
//            pdfiumCore.closeDocument(pdfDocument);
//        } catch(Exception e) {
//            //todo with exception
//        }
//        return images;
//    }
///////////////////////

    public String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);

        return temp;
    }
    private  static ArrayList<Bitmap> pdfToBitmap(File pdfFile, Context context) {
        ArrayList<Bitmap> bitmaps = new ArrayList<>();

        try {
            PdfRenderer renderer = new PdfRenderer(ParcelFileDescriptor.open(pdfFile, ParcelFileDescriptor.MODE_READ_ONLY));

            Bitmap bitmap;
          final  int pageCount = renderer.getPageCount();
            for (int i = 0; i < pageCount; i++) {
                PdfRenderer.Page page = renderer.openPage(i);

                int width = context.getResources().getDisplayMetrics().densityDpi / 72 * page.getWidth();
                int height = context.getResources().getDisplayMetrics().densityDpi / 72 * page.getHeight();
                bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

                page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);

                bitmaps.add(bitmap);
                page.close();
            }

            renderer.close();
        } catch (Exception e) {
            e.getMessage();
        }
        return bitmaps;
    }

//////////////////////
//   public void openPdf() {
//
//            ImageView imageView=findViewById(R.id.imgPDFTV);
//        ParcelFileDescriptor fd = null;
//        try {
//            fd = ParcelFileDescriptor.open(pdfFilePth, ParcelFileDescriptor.MODE_READ_ONLY);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        int pageNum = 0;
//        PdfiumCore pdfiumCore = new PdfiumCore(this);
//        try {
//            PdfDocument pdfDocument = pdfiumCore.newDocument(fd);
//
//            pdfiumCore.openPage(pdfDocument, pageNum);
//
//            int width = pdfiumCore.getPageWidthPoint(pdfDocument, pageNum);
//            int height = pdfiumCore.getPageHeightPoint(pdfDocument, pageNum);
//
//            // ARGB_8888 - best quality, high memory usage, higher possibility of OutOfMemoryError
//            // RGB_565 - little worse quality, twice less memory usage
//            Bitmap bitmap = Bitmap.createBitmap(width, height,
//                    Bitmap.Config.RGB_565);
//            pdfiumCore.renderPageBitmap(pdfDocument, bitmap, pageNum, 0, 0,
//                    width, height);
//            //if you need to render annotations and form fields, you can use
//            //the same method above adding 'true' as last param
//
//            imageView.setImageBitmap(bitmap);
//
////            printInfo(pdfiumCore, pdfDocument);
//
//            pdfiumCore.closeDocument(pdfDocument); // important!
//        } catch(IOException ex) {
//            ex.printStackTrace();
//        }
//    }

//    public void printInfo(PdfiumCore core, PdfDocument doc) {
//        PdfDocument.Meta meta = core.getDocumentMeta(doc);
//        Log.e(TAG, "title = " + meta.getTitle());
//        Log.e(TAG, "author = " + meta.getAuthor());
//        Log.e(TAG, "subject = " + meta.getSubject());
//        Log.e(TAG, "keywords = " + meta.getKeywords());
//        Log.e(TAG, "creator = " + meta.getCreator());
//        Log.e(TAG, "producer = " + meta.getProducer());
//        Log.e(TAG, "creationDate = " + meta.getCreationDate());
//        Log.e(TAG, "modDate = " + meta.getModDate());
//
//        printBookmarksTree(core.getTableOfContents(doc), "-");
//
//    }

//    public void printBookmarksTree(List<PdfDocument.Bookmark> tree, String sep) {
//        for (PdfDocument.Bookmark b : tree) {
//
//            Log.e("TAG", String.format("%s %s, p %d", sep, b.getTitle(), b.getPageIdx()));
//
//            if (b.hasChildren()) {
//                printBookmarksTree(b.getChildren(), sep + "-");
//            }
//        }
//    }


}

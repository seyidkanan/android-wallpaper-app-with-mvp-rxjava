package com.crocusoft.wallpaperappwithmvp.util;

import android.os.AsyncTask;

import com.crocusoft.wallpaperappwithmvp.ImageDescriptionActivityPackage.FileDowloadingIndicaterInterface;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

public class FileDownloadAsync extends AsyncTask<String, Void, Void> {

    private FileDowloadingIndicaterInterface fileDowloadingIndicaterInterface;

    public FileDownloadAsync(FileDowloadingIndicaterInterface fileDowloadingIndicaterInterface) {
        this.fileDowloadingIndicaterInterface = fileDowloadingIndicaterInterface;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(String... params) {
        int count;
        try {
            File tempFile = File.createTempFile(UUID.randomUUID().toString(), ".png");

            String root = tempFile.getAbsolutePath();

            URL url = new URL(params[0]);

            URLConnection connection = url.openConnection();
            connection.connect();
            // getting file length
            int lenghtOfFile = connection.getContentLength();

            // input stream to read file - with 8k buffer
            InputStream input = new BufferedInputStream(url.openStream(), 8192);

//            String fileName = params[2];

            //Log.e("kanan", "file path=" + root);
            // Output stream to write file
            OutputStream output = new FileOutputStream(root);
            byte data[] = new byte[1024];

            long total = 0;
            while ((count = input.read(data)) != -1) {
                total += count;
                //Log.e("kanan", "total=" + (total * 100) / lenghtOfFile);
                // writing data to file
                output.write(data, 0, count);

                long percent = (total * 100) / lenghtOfFile;
                fileDowloadingIndicaterInterface.onImageDownloadProgress(percent);
            }

            // flushing output
            output.flush();

            // closing streams
            output.close();
            input.close();
            fileDowloadingIndicaterInterface.onImageDownloadSuccess(root);
        } catch (Exception e) {
            fileDowloadingIndicaterInterface.onImageDownloadError(e, null);
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}

package com.crocusoft.wallpaperappwithmvp.ImageDescriptionActivityPackage;

public interface FileDowloadingIndicaterInterface {

    void onImageDownloadProgress(long percent);

    void onImageDownloadSuccess(String filePath);

    void onImageDownloadError(Exception e, String error);

}

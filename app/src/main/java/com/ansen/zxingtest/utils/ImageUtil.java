package com.ansen.zxingtest.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;

public class ImageUtil {
    public static void savePicToLocal(Bitmap bitmap, Context context) {
        if (bitmap == null) return;
        final String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        String filePath = absolutePath + "/screen" + File.separator + System.currentTimeMillis() + ".png";
        Log.d("ggg", "filePath = " + filePath);
        try {
            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            FileOutputStream os = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri uri = Uri.fromFile(new File(filePath));
            intent.setData(uri);
            context.sendBroadcast(intent);
            os.flush();
            os.close();
        } catch (Exception e) {
            Log.d("ggg", "e = ", e);
        }
    }
}

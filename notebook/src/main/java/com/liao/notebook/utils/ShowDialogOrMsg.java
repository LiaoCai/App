package com.liao.notebook.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.widget.Toast;

import com.liao.notebook.presenter.NoteProvider;

/**
 * Created by liao on 2016-5-5 0005.
 */
public class ShowDialogOrMsg
{
    public static void showDialog(final Context context, String title, int iconResId, final int rawId)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage("是否删除该记录？");
        builder.setIcon(iconResId);

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                context.getContentResolver().delete(
                        NoteProvider.URI_NOTE_ALL, NoteDBHelper.ID + " = " + rawId, null);
                showToast(context, "已删除");
            }
        });

        builder.setNeutralButton("取消", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
                showToast(context, "已取消");
            }
        });
        builder.create().show();
    }

    public static void showToast(Context context, int resId)
    {
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(Context context, String str)
    {
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }

    public static Bitmap getImageThumbnail(String uri, int width, int height)
    {
        Bitmap bitmap;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        int beWidth = options.outWidth / width;
        int beHeight = options.outHeight / height;
        int be;
        if (beWidth < beHeight)
        {
            be = beWidth;
        } else
        {
            be = beHeight;
        }
        if (be <= 0)
        {
            be = 1;
        }
        options.inSampleSize = be;
        options.inJustDecodeBounds = false;
        bitmap = BitmapFactory.decodeFile(uri, options);
        bitmap = ThumbnailUtils.extractThumbnail(
                bitmap, width, height, ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        return bitmap;
    }

}

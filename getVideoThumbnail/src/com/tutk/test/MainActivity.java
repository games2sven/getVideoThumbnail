package com.tutk.test;

import wseemann.media.FFmpegMediaMetadataRetriever;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.getvideothumbnail.R;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		ImageView img_show = (ImageView)findViewById(R.id.img_show);
		
		
		  Bitmap bmp  = null;
          FFmpegMediaMetadataRetriever fmmr = new FFmpegMediaMetadataRetriever();
          try {
              fmmr.setDataSource("文件的绝对路径");//请输入你视频的地址
              bmp = fmmr.getFrameAtTime();

              if (bmp != null) {
                  Bitmap b2 = fmmr
                          .getFrameAtTime(
                                  4000000,
                                  FFmpegMediaMetadataRetriever.OPTION_CLOSEST_SYNC);
                  if (b2 != null) {
                      bmp = b2;
                  }
                  if (bmp.getWidth() > 640) {// 如果图片宽度规格超过640px,则进行压缩
                      bmp = ThumbnailUtils.extractThumbnail(bmp,
                              640, 480,
                              ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
                  }
              }
          } catch (IllegalArgumentException ex) {
              ex.printStackTrace();
          } finally {
              fmmr.release();
          }
		
          BitmapDrawable bd = new BitmapDrawable(bmp);
          img_show.setBackground(bd);
	}
}

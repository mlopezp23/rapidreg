package org.unicef.rapidreg.childcase.media;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import org.unicef.rapidreg.R;
import org.unicef.rapidreg.service.cache.CasePhotoCache;
import org.unicef.rapidreg.utils.ImageCompressUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CasePhotoViewActivity extends AppCompatActivity {

    private ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.case_photo_view_slider);

        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        viewPager = (ViewPager) findViewById(R.id.case_photo_view_slider);
        viewPager.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        viewPager.setAdapter(new CasePhotoViewPagerAdapter());
        viewPager.setCurrentItem(getIntent().getIntExtra("position", 0));
    }

    public class CasePhotoViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return CasePhotoCache.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Point size = new Point();
            getWindowManager().getDefaultDisplay().getSize(size);

            List<String> previousPhotoPaths = CasePhotoCache.getPhotosPaths();

            View itemView = LayoutInflater.from(CasePhotoViewActivity.this)
                    .inflate(R.layout.case_photo_view_item, container, false);

            ImageView imageView = (ImageView) itemView.findViewById(R.id.case_photo_item);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            Bitmap image = ImageCompressUtil.compressBySize(previousPhotoPaths.get(position), size.x, size.y);
            imageView.setImageBitmap(image);

            container.addView(itemView);
            return itemView;
        }
    }
}
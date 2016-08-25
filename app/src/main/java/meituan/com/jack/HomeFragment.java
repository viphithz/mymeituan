package meituan.com.jack;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jack on 16-8-19.
 */
public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";
    private ViewPager mBannerViewPager = null;

    class BannerItem {
        private int mImageId = 0;
        private String mName = null;

        public BannerItem(int mImageId, String mName) {
            this.mImageId = mImageId;
            this.mName = mName == null ? "" : mName;
        }

        public BannerItem(int mImageId, int nameResId) {
            this.mImageId = mImageId;
            String str = getResources().getString(nameResId);
            this.mName = str == null ? "" : str;
        }
    }
    private ArrayList<BannerItem> mBannerItems = null;
    private static final int BANNER_CONTENT_ROW = 2;
    private static final int BANNER_CONTENT_COLUMN = 5;
    private int mBannerPagesCount = 0;



    class BannerGridViewAdapter extends BaseAdapter {
        private int mBannerPagePosition = 0;

        @Override
        public int getCount() {
            int count = 0;
            if (mBannerPagePosition == mBannerPagesCount-1){
                count = mBannerItems.size() - BANNER_CONTENT_ROW * BANNER_CONTENT_COLUMN * mBannerPagePosition;
            }else{
                count = BANNER_CONTENT_ROW * BANNER_CONTENT_COLUMN;
            }

//            Log.i(TAG, "BannerGridViewAdapter.getCount(),count is " + count);
            return count;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View convert, ViewGroup viewGroup) {
//            Log.i(TAG, "BannerGridViewAdapter.getView, i is " + i);
            LinearLayout layout = null;

            layout = new LinearLayout(getActivity());
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
            layout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            if (i < BANNER_CONTENT_COLUMN){
                layout.setPadding(0, 50, 0, 20);
            }else{
                layout.setPadding(0, 20, 0, 50);
            }


            ImageView imageView = new ImageView(getActivity());
            int itemIndex = mBannerPagePosition * BANNER_CONTENT_ROW * BANNER_CONTENT_COLUMN + i;
            imageView.setImageResource(mBannerItems.get(itemIndex).mImageId);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(150, 150));
//            imageView.setPadding(0,30,0,5);
            layout.addView(imageView);

            TextView textView = new TextView(getActivity());
            textView.setText(mBannerItems.get(itemIndex).mName);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            layout.addView(textView);

            return layout;
        }
    }


    class BannerFragmentPagerAdapter extends FragmentPagerAdapter{
        private static final String POSITION = "position";

        public BannerFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Log.i(TAG, "BannerFragmentPagerAdapter.getItem, postion is " + position);

            Fragment fragment = new BannerFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(POSITION, position);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return mBannerPagesCount;
        }
    }


    public class BannerFragment extends Fragment {
        private GridView gridView = null;

        public BannerFragment() {
            Log.i(TAG, "BannerFragment  constructor");
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            Bundle bundle = getArguments();
            int position = bundle.getInt(BannerFragmentPagerAdapter.POSITION);

            LinearLayout layout = new LinearLayout(getActivity());
            layout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//            layout.setBackgroundColor(Color.GREEN);

            gridView = new GridView(getActivity());
            gridView.setNumColumns(BANNER_CONTENT_COLUMN);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                                LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 10, 0, 0);
            gridView.setLayoutParams(params);
//            gridView.setBackgroundColor(Color.BLUE);
            Log.i(TAG, "BannerFragment.onCreateView, before setAdapter, this is " + this);

            BannerGridViewAdapter bannerGridViewAdapter = new BannerGridViewAdapter();
            bannerGridViewAdapter.mBannerPagePosition = position;
            gridView.setAdapter(bannerGridViewAdapter);
            layout.addView(gridView);

            return layout;
        }

        @Override
        public void onStart() {
            super.onStart();
        }
    }


    public HomeFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        initBannerItems();

        int count = mBannerItems.size() / (BANNER_CONTENT_ROW * BANNER_CONTENT_COLUMN);
        mBannerPagesCount = (mBannerItems.size() % (BANNER_CONTENT_ROW * BANNER_CONTENT_COLUMN) == 0) ? count : count+1;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, null);
        mBannerViewPager = (ViewPager) view.findViewById(R.id.id_home_banner_viewpager);

        BannerFragmentPagerAdapter mBannerAdapter = new BannerFragmentPagerAdapter(getFragmentManager());
        mBannerViewPager.setAdapter(mBannerAdapter);

        return view;
    }

    private void initBannerItems() {
        mBannerItems = new ArrayList<BannerItem>();
        mBannerItems.add(new BannerItem(R.drawable.meishi, R.string.meishi));
        mBannerItems.add(new BannerItem(R.drawable.dianying, R.string.dianying));
        mBannerItems.add(new BannerItem(R.drawable.jiudian, R.string.jiudian));
        mBannerItems.add(new BannerItem(R.drawable.xiuxian, R.string.xiuxianyule));
        mBannerItems.add(new BannerItem(R.drawable.waimai, R.string.waimai));
        mBannerItems.add(new BannerItem(R.drawable.zizhucan, R.string.zizhucan));
        mBannerItems.add(new BannerItem(R.drawable.ktv, R.string.KTV));
        mBannerItems.add(new BannerItem(R.drawable.huochepiao, R.string.huochepiao_jipiao));
        mBannerItems.add(new BannerItem(R.drawable.zuliao, R.string.zuliaoanmo));
        mBannerItems.add(new BannerItem(R.drawable.zhoubianyou, R.string.zhoubianyou));
        mBannerItems.add(new BannerItem(R.drawable.xiaochikuaican, R.string.xiaochikuaican));
        mBannerItems.add(new BannerItem(R.drawable.chuanxiangcai, R.string.chuanxiangcai));
        mBannerItems.add(new BannerItem(R.drawable.huoguo, R.string.huoguo));
        mBannerItems.add(new BannerItem(R.drawable.meifa, R.string.meifa));
        mBannerItems.add(new BannerItem(R.drawable.zhoubianyou, R.string.jingdian));
        mBannerItems.add(new BannerItem(R.drawable.meifa, R.string.liren));
        mBannerItems.add(new BannerItem(R.drawable.zuliao, R.string.xiyu));
        mBannerItems.add(new BannerItem(R.drawable.xiuxian, R.string.yundong));
        mBannerItems.add(new BannerItem(R.drawable.zhoubianyou, R.string.shuishangleyuan));
        mBannerItems.add(new BannerItem(R.drawable.xiuxian, R.string.shenghuofuwu));
        mBannerItems.add(new BannerItem(R.drawable.xiaochikuaican, R.string.rihanliaoli));
        mBannerItems.add(new BannerItem(R.drawable.meishi, R.string.tiandian));
        mBannerItems.add(new BannerItem(R.drawable.jiudian, R.string.shengridangao));
    }
}

package meituan.com.jack;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;

/**
 * Created by jack on 16-8-20.
 */
public class HomeBannerFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout layout = new LinearLayout(getActivity());

        GridView gridView = new GridView(getActivity());
        //            gridView.setId(R.id.id_home_banner_gridview);
//        gridView.setNumColumns(BANNER_CONTENT_COLUMN);
//        gridView.setAdapter(mBannerGridViewAdapter);

        layout.addView(gridView);

        return layout;
    }
}

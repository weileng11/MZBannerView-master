package com.zhouwei.mzbannerview;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

import static com.zhouwei.mzbannerview.MZModeBannerFragment.RES;

/**
 * Created by zhouwei on 17/5/31.
 */

public class NormalViewPagerFragment extends Fragment {
    private MZBannerView mMZBannerView;
    private MZBannerView mNormalViewPager;
    private static Context mContext;
    public static NormalViewPagerFragment newInstance(Context context){
	    mContext=context;
        return new NormalViewPagerFragment();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.normal_view_pager_layout,null);
        initView(view);
        return view;
    }

    private void initView(View view){
        mMZBannerView = (MZBannerView) view.findViewById(R.id.mz_view_pager);
        mNormalViewPager = (MZBannerView) view.findViewById(R.id.normal_viewPager);
	
        if(mockData().size()>=3){
	        mMZBannerView.setViewPagerCur(1);
        }else{
	        mMZBannerView.setViewPagerCur(0);
        }
	    mMZBannerView.setPages(mockData(), new MZHolderCreator<ViewPagerHolder>() {
		    @Override
		    public ViewPagerHolder createViewHolder() {
			    return new ViewPagerHolder();
		    }
	    });


        mMZBannerView.setPages(mockData(), new MZHolderCreator<ViewPagerHolder>() {
            @Override
            public ViewPagerHolder createViewHolder() {
                return new ViewPagerHolder();
            }
        });

        mNormalViewPager.setIndicatorRes(R.drawable.dot_unselect_image,R.drawable.dot_select_image);
        mNormalViewPager.setPages(mockData(), new MZHolderCreator<ViewPagerHolder>() {
            @Override
            public ViewPagerHolder createViewHolder() {
                return new ViewPagerHolder();
            }
        });

    }


    public static final class ViewPagerHolder implements MZViewHolder<DataEntry> {
        private ImageView mImageView;
        private TextView mTitle;
        private TextView mDesc;
        @Override
        public View createView(Context context) {
            View view = LayoutInflater.from(context).inflate(R.layout.normal_banner_item,null);
            mImageView = (ImageView) view.findViewById(R.id.normal_banner_image);
            mDesc = (TextView) view.findViewById(R.id.page_desc);
            return view;
        }

        @Override
        public void onBind(Context context, final int position, DataEntry data) {
           mImageView.setImageResource(data.resId);
           mDesc.setText(data.desc);
	
	        mImageView.setOnClickListener(new View.OnClickListener(){
		        @Override
		        public void onClick(View v){
			        Toast.makeText(mContext,position+"---",Toast.LENGTH_LONG).show();
		        }
	        });
        }
    }

    private List<DataEntry> mockData(){
        List<DataEntry> list = new ArrayList<>();
        DataEntry dataEntry = null;
        for(int i=0;i<RES.length;i++){
            dataEntry = new DataEntry();
            dataEntry.resId = RES[i];
            dataEntry.desc = "The time you enjoy wasting , is not wasted.";
            list.add(dataEntry);
        }

        return list;
    }
}

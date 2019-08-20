package com.codeoptimizer.ebookstore.Adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.codeoptimizer.ebookstore.Fragments.AddBook;
import com.codeoptimizer.ebookstore.Fragments.DeleteBook;
import com.codeoptimizer.ebookstore.Fragments.UpdateBook;
import com.codeoptimizer.ebookstore.Fragments.ViewFragment;

import java.util.ArrayList;

public class ViewPagerAdapterForAdmin extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public ViewPagerAdapterForAdmin(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                ViewFragment tab1 = new ViewFragment();
                return tab1;
            case 1:
                AddBook tab2 = new AddBook();
                return tab2;
            case 2:
                DeleteBook tab3 = new DeleteBook();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}

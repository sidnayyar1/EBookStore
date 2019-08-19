package com.codeoptimizer.ebookstore.FragmentsForUser;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codeoptimizer.ebookstore.Adapters.AdapterForBook;
import com.codeoptimizer.ebookstore.Adapters.AdapterForDeleteBook;
import com.codeoptimizer.ebookstore.Interfaces.AdapterListenerForDeleteBook;
import com.codeoptimizer.ebookstore.Model.BookData;
import com.codeoptimizer.ebookstore.R;
import com.codeoptimizer.ebookstore.Utilities.BookDataBase;
import com.codeoptimizer.ebookstore.Utilities.WishDataBase;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link WishlistFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link WishlistFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WishlistFragment extends Fragment implements AdapterListenerForDeleteBook {

    RecyclerView wishRecycler;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    List<BookData> listData = new ArrayList<>();
    WishDataBase wdb;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public WishlistFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WishlistFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WishlistFragment newInstance(String param1, String param2) {
        WishlistFragment fragment = new WishlistFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("WishList");
        // Inflate the layout for this fragment
        View layout =  inflater.inflate(R.layout.fragment_wishlist, container, false);
        wishRecycler = (RecyclerView)layout.findViewById(R.id.wishRecycler);
        wdb = new WishDataBase(getContext());

        wishRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        wishRecycler.setNestedScrollingEnabled(false);
        adapter=new AdapterForDeleteBook(listData,getContext(),this);
        wishRecycler.setAdapter(adapter);

        wdb.open();
        listData.addAll(wdb.getBookData());
        BookData bookData = new BookData();
        for (int i = 0; i<wdb.getBookData().size();i++){
            bookData.setBookName(wdb.getBookData().get(i).getBookName());
            bookData.setBookAuthor(wdb.getBookData().get(i).getBookAuthor());
            bookData.setBookDesc(wdb.getBookData().get(i).getBookDesc());
            bookData.setBookUrl(wdb.getBookData().get(i).getBookUrl());
            bookData.setBookCategory(wdb.getBookData().get(i).getBookCategory());

        }
        adapter.notifyDataSetChanged();
        wdb.close();
        return layout;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void clicked(int position) {
        // to delete book from wishlist

        wdb.open();
        String in = wdb.getBookData().get(position).getBookId();
        wdb.delete(in);
        listData.clear();
        listData.addAll(wdb.getBookData());
        adapter.notifyDataSetChanged();
        wdb.close();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

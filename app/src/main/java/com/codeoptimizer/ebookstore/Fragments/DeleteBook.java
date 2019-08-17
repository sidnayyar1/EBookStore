package com.codeoptimizer.ebookstore.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.codeoptimizer.ebookstore.Adapters.AdapterForBook;
import com.codeoptimizer.ebookstore.Adapters.AdapterForDeleteBook;
import com.codeoptimizer.ebookstore.Interfaces.AdapterListenerForDeleteBook;
import com.codeoptimizer.ebookstore.Model.BookData;
import com.codeoptimizer.ebookstore.R;
import com.codeoptimizer.ebookstore.Utilities.BookDataBase;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DeleteBook.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DeleteBook#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DeleteBook extends Fragment implements AdapterListenerForDeleteBook {

    RecyclerView recyclerDeleteBook;
    RecyclerView.Adapter adapter,adapter1;
    RecyclerView.LayoutManager layoutManager;
    List<BookData> listData = new ArrayList<>();
    BookDataBase bdb;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public DeleteBook() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DeleteBook.
     */
    // TODO: Rename and change types and number of parameters
    public static DeleteBook newInstance(String param1, String param2) {
        DeleteBook fragment = new DeleteBook();
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
        // Inflate the layout for this fragment
        View layout =  inflater.inflate(R.layout.fragment_delete_book, container, false);
        recyclerDeleteBook = (RecyclerView)layout.findViewById(R.id.recyclerDelete);
        bdb = new BookDataBase(getContext());
        recyclerDeleteBook.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerDeleteBook.setNestedScrollingEnabled(false);
        adapter=new AdapterForDeleteBook(listData,getContext(),this);
        recyclerDeleteBook.setAdapter(adapter);

        bdb.open();
        listData.addAll(bdb.getBookData());
        BookData bookData = new BookData();
        for (int i = 0; i<bdb.getBookData().size();i++){
            bookData.setBookName(bdb.getBookData().get(i).getBookName());
            bookData.setBookAuthor(bdb.getBookData().get(i).getBookAuthor());
            bookData.setBookDesc(bdb.getBookData().get(i).getBookDesc());
            bookData.setBookUrl(bdb.getBookData().get(i).getBookUrl());
            bookData.setBookCategory(bdb.getBookData().get(i).getBookCategory());

        }
        adapter.notifyDataSetChanged();
        bdb.close();
        return  layout;
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
        // for delete book logic
        Toast.makeText(getContext(), ""+position, Toast.LENGTH_SHORT).show();
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

package com.codeoptimizer.ebookstore.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.codeoptimizer.ebookstore.Adapters.AdapterForBook;
import com.codeoptimizer.ebookstore.LoginScreen;
import com.codeoptimizer.ebookstore.Model.BookData;
import com.codeoptimizer.ebookstore.R;
import com.codeoptimizer.ebookstore.SplashScreen;
import com.codeoptimizer.ebookstore.Utilities.BookDataBase;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ViewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewFragment extends Fragment {

    TextView adminEmail;

    SharedPreferences sharedPreferences;
    RecyclerView recyclerViewBook;
    RecyclerView.Adapter adapter,adapter1;
    RecyclerView.LayoutManager layoutManager;
    List<BookData> listData = new ArrayList<>();
    BookDataBase bdb;
    Button adminLogoutBtn;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewFragment newInstance(String param1, String param2) {
        ViewFragment fragment = new ViewFragment();
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
        View layout =  inflater.inflate(R.layout.fragment_view, container, false);
        adminEmail = (TextView)layout.findViewById(R.id.adminEmail);
        recyclerViewBook = (RecyclerView)layout.findViewById(R.id.recyclerViewBook);
        adminLogoutBtn = (Button)layout.findViewById(R.id.adminLogoutBtn);
        bdb = new BookDataBase(getContext());

        sharedPreferences = this.getActivity().getSharedPreferences("login",MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        if(sharedPreferences.contains("userEmail")){
            adminEmail.setText(sharedPreferences.getString("userEmail",""));
        }

        recyclerViewBook.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerViewBook.setNestedScrollingEnabled(false);
        adapter=new AdapterForBook(listData,getContext());
        recyclerViewBook.setAdapter(adapter);

        bdb.open();
        listData.addAll(bdb.getBookData());
        BookData bookData = new BookData();
        for (int i = 0; i<bdb.getBookData().size();i++){
            bookData.setBookName(bdb.getBookData().get(i).getBookName());
            bookData.setBookAuthor(bdb.getBookData().get(i).getBookAuthor());
            bookData.setBookDesc(bdb.getBookData().get(i).getBookDesc());
            bookData.setBookUrl(bdb.getBookData().get(i).getBookUrl());
            bookData.setBookPrice(bdb.getBookData().get(i).getBookPrice());
            bookData.setBookCategory(bdb.getBookData().get(i).getBookCategory());

        }
        adapter.notifyDataSetChanged();
        bdb.close();

        adminLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.remove("userEmail");
                editor.remove("userPassword");
                editor.apply();

                Intent in = new Intent(getContext(), SplashScreen.class);
                startActivity(in);
                getActivity().finish();
            }
        });
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
    public void onPause() {
        super.onPause();
        adapter.notifyDataSetChanged();
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

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}

package com.codeoptimizer.ebookstore.FragmentsForUser;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.codeoptimizer.ebookstore.DisplayBooksAccToCategory;
import com.codeoptimizer.ebookstore.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    CardView scence,netwoking,multimedia,marketing,programmming,law;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        getActivity().setTitle("E-Book Store");
        // Inflate the layout for this fragment
        View layout =  inflater.inflate(R.layout.fragment_home, container, false);
        scence = (CardView)layout.findViewById(R.id.cardScience);
        netwoking = (CardView)layout.findViewById(R.id.cardNetworking);
        multimedia = (CardView)layout.findViewById(R.id.cardMultimedia);
        marketing = (CardView)layout.findViewById(R.id.cardMarketing);
        programmming = (CardView)layout.findViewById(R.id.cardProgramming);
        law = (CardView)layout.findViewById(R.id.cardLaw);


        scence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Toast.makeText(getContext(), "scence", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), DisplayBooksAccToCategory.class);
                intent.putExtra("category","Science");
                startActivity(intent);
            }
        });
        netwoking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(), "netwoking", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), DisplayBooksAccToCategory.class);
                intent.putExtra("category","Networking");
                startActivity(intent);
            }
        });
        multimedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(getContext(), "multimedia", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), DisplayBooksAccToCategory.class);
                intent.putExtra("category","Multimedia");
                startActivity(intent);
            }
        });
        marketing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(), "marketing", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), DisplayBooksAccToCategory.class);
                intent.putExtra("category","Marketing");
                startActivity(intent);
            }
        });
        programmming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Toast.makeText(getContext(), "programmming", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), DisplayBooksAccToCategory.class);
                intent.putExtra("category","Programming");
                startActivity(intent);
            }
        });
        law.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DisplayBooksAccToCategory.class);
                intent.putExtra("category","Law");
                startActivity(intent);
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

package com.codeoptimizer.ebookstore.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.codeoptimizer.ebookstore.Model.BookData;
import com.codeoptimizer.ebookstore.R;
import com.codeoptimizer.ebookstore.Utilities.BookDataBase;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddBook.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddBook#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddBook extends Fragment {

    Spinner  catSpinner;
    EditText bookName, bookAuthor,bookDesc,bookUrl,bookPrice;
    Button addBook;
    String catBook;
    BookDataBase bdb;
    String[] bookCat = { "Select Book Category","Science", "Networking", "Multimedia", "Programming", "Marketing", "Law"};

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AddBook() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddBook.
     */
    // TODO: Rename and change types and number of parameters
    public static AddBook newInstance(String param1, String param2) {
        AddBook fragment = new AddBook();
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
        View layout = inflater.inflate(R.layout.fragment_add_book, container, false);
        catSpinner = (Spinner)layout.findViewById(R.id.catSpinner);
        bookName = (EditText)layout.findViewById(R.id.adBookName);
        bookAuthor = (EditText)layout.findViewById(R.id.adBookAuthor);
        bookDesc = (EditText)layout.findViewById(R.id.addBookDesc);
        bookUrl = (EditText)layout.findViewById(R.id.adBookUrl);
        addBook = (Button)layout.findViewById(R.id.addBook);
        bookPrice = (EditText)layout.findViewById(R.id.addBookPrice);
        bdb = new BookDataBase(getContext());

        ArrayAdapter aa = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,bookCat);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        catSpinner.setAdapter(aa);

        catSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getContext(),bookCat[position] , Toast.LENGTH_LONG).show();
                catBook = bookCat[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bName = bookName.getText().toString().trim();
                String bAuthor = bookAuthor.getText().toString().trim();
                String bdes = bookDesc.getText().toString().trim();
                String bUrl = bookUrl.getText().toString().trim();
                String bPrice = bookPrice.getText().toString().trim();
                bdb.open();
                if(!catBook.equalsIgnoreCase("Select Book Category")) {
                    if(!bName.equalsIgnoreCase("") && !bAuthor.equalsIgnoreCase("") && !bdes.equalsIgnoreCase("") && !bUrl.equalsIgnoreCase("")) {
                        bdb.save(bName, bAuthor, bdes, bUrl, catBook,bPrice);
                        bdb.close();
                        bookName.setText("");
                        bookAuthor.setText("");
                        bookDesc.setText("");
                        bookUrl.setText("");
                        bookPrice.setText("");
                        Toast.makeText(getContext(), "Book Added", Toast.LENGTH_LONG).show();

                    }
                    else {
                        Toast.makeText(getContext(), "Fill all the Details", Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    Toast.makeText(getContext(), "Select a Valid Category", Toast.LENGTH_SHORT).show();
                }


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

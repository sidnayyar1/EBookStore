package com.codeoptimizer.ebookstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.codeoptimizer.ebookstore.Adapters.AdapterForBook;
import com.codeoptimizer.ebookstore.Adapters.AdapterToDisplayBook;
import com.codeoptimizer.ebookstore.Model.BookData;
import com.codeoptimizer.ebookstore.Utilities.BookDataBase;

import java.util.ArrayList;
import java.util.List;

public class DisplayBooksAccToCategory extends AppCompatActivity {

    String category;
    RecyclerView recyclerDisplayBook;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    List<BookData> listData = new ArrayList<>();
    BookDataBase bdb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_books_acc_to_category);
        recyclerDisplayBook = (RecyclerView)findViewById(R.id.recyclerDisplayBooks);
        Intent intent = getIntent();
        category = intent.getStringExtra("category");

        bdb = new BookDataBase(getApplicationContext());
        BookData bookData = new BookData();

        recyclerDisplayBook.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        recyclerDisplayBook.setNestedScrollingEnabled(false);
        adapter=new AdapterToDisplayBook(listData,getApplicationContext());
        recyclerDisplayBook.setAdapter(adapter);

        switch (category){
            case "science":
                bdb.open();
                listData.clear();
                listData.addAll(bdb.getBookDataByCategory("Science"));

                for (int i = 0; i<bdb.getBookDataByCategory("Science").size();i++){

                        bookData.setBookName(bdb.getBookDataByCategory("Science").get(i).getBookName());
                        bookData.setBookAuthor(bdb.getBookDataByCategory("Science").get(i).getBookAuthor());
                        bookData.setBookDesc(bdb.getBookDataByCategory("Science").get(i).getBookDesc());
                        bookData.setBookUrl(bdb.getBookDataByCategory("Science").get(i).getBookUrl());
                        bookData.setBookCategory(bdb.getBookDataByCategory("Science").get(i).getBookCategory());


                }
                adapter.notifyDataSetChanged();
                bdb.close();
                break;
            case "netwoking":
                bdb.open();
                listData.clear();
                listData.addAll(bdb.getBookDataByCategory("Networking"));

                for (int i = 0; i<bdb.getBookDataByCategory("Networking").size();i++){

                    bookData.setBookName(bdb.getBookDataByCategory("Networking").get(i).getBookName());
                    bookData.setBookAuthor(bdb.getBookDataByCategory("Networking").get(i).getBookAuthor());
                    bookData.setBookDesc(bdb.getBookDataByCategory("Networking").get(i).getBookDesc());
                    bookData.setBookUrl(bdb.getBookDataByCategory("Networking").get(i).getBookUrl());
                    bookData.setBookCategory(bdb.getBookDataByCategory("Networking").get(i).getBookCategory());


                }
                adapter.notifyDataSetChanged();
                bdb.close();
                break;
            case "multimedia":
                bdb.open();
                listData.clear();
                listData.addAll(bdb.getBookDataByCategory("Multimedia"));

                for (int i = 0; i<bdb.getBookDataByCategory("Multimedia").size();i++){

                    bookData.setBookName(bdb.getBookDataByCategory("Multimedia").get(i).getBookName());
                    bookData.setBookAuthor(bdb.getBookDataByCategory("Multimedia").get(i).getBookAuthor());
                    bookData.setBookDesc(bdb.getBookDataByCategory("Multimedia").get(i).getBookDesc());
                    bookData.setBookUrl(bdb.getBookDataByCategory("Multimedia").get(i).getBookUrl());
                    bookData.setBookCategory(bdb.getBookDataByCategory("Multimedia").get(i).getBookCategory());


                }
                adapter.notifyDataSetChanged();
                bdb.close();
                break;
            case "marketing":
                bdb.open();
                listData.clear();
                listData.addAll(bdb.getBookDataByCategory("Marketing"));

                for (int i = 0; i<bdb.getBookDataByCategory("Marketing").size();i++){

                    bookData.setBookName(bdb.getBookDataByCategory("Marketing").get(i).getBookName());
                    bookData.setBookAuthor(bdb.getBookDataByCategory("Marketing").get(i).getBookAuthor());
                    bookData.setBookDesc(bdb.getBookDataByCategory("Marketing").get(i).getBookDesc());
                    bookData.setBookUrl(bdb.getBookDataByCategory("Marketing").get(i).getBookUrl());
                    bookData.setBookCategory(bdb.getBookDataByCategory("Marketing").get(i).getBookCategory());


                }
                adapter.notifyDataSetChanged();
                bdb.close();
                break;
            case "programmming":
                bdb.open();
                listData.clear();
                listData.addAll(bdb.getBookDataByCategory("Programming"));

                for (int i = 0; i<bdb.getBookDataByCategory("Programming").size();i++){

                    bookData.setBookName(bdb.getBookDataByCategory("Programming").get(i).getBookName());
                    bookData.setBookAuthor(bdb.getBookDataByCategory("Programming").get(i).getBookAuthor());
                    bookData.setBookDesc(bdb.getBookDataByCategory("Programming").get(i).getBookDesc());
                    bookData.setBookUrl(bdb.getBookDataByCategory("Programming").get(i).getBookUrl());
                    bookData.setBookCategory(bdb.getBookDataByCategory("Programming").get(i).getBookCategory());


                }
                adapter.notifyDataSetChanged();
                bdb.close();
                break;
            default:
                Toast.makeText(getApplicationContext(),"Some Thing Went Wrong",Toast.LENGTH_LONG).show();
        }

    }
}

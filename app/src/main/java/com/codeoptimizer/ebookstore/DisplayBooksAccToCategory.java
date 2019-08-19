package com.codeoptimizer.ebookstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.codeoptimizer.ebookstore.Adapters.AdapterForBook;
import com.codeoptimizer.ebookstore.Adapters.AdapterForDeleteBook;
import com.codeoptimizer.ebookstore.Adapters.AdapterToDisplayBook;
import com.codeoptimizer.ebookstore.Interfaces.AdapterListenerForDeleteBook;
import com.codeoptimizer.ebookstore.Interfaces.AdapterListenerForUser;
import com.codeoptimizer.ebookstore.Model.BookData;
import com.codeoptimizer.ebookstore.Utilities.BookDataBase;
import com.codeoptimizer.ebookstore.Utilities.CartDataBase;
import com.codeoptimizer.ebookstore.Utilities.WishDataBase;

import java.util.ArrayList;
import java.util.List;

public class DisplayBooksAccToCategory extends AppCompatActivity implements AdapterListenerForUser {

    String category;
    RecyclerView recyclerDisplayBook;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    Toolbar innerToolbar;
    List<BookData> listData = new ArrayList<>();
    BookDataBase bdb;
    WishDataBase wdb;
    CartDataBase cdb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_books_acc_to_category);
        recyclerDisplayBook = (RecyclerView)findViewById(R.id.recyclerDisplayBooks);
        innerToolbar = (Toolbar)findViewById(R.id.innerTool);
        Intent intent = getIntent();
        category = intent.getStringExtra("category");

        bdb = new BookDataBase(getApplicationContext());
        wdb = new WishDataBase(getApplicationContext());
        cdb = new CartDataBase(getApplicationContext());
        BookData bookData = new BookData();

        innerToolbar.setTitle(category);
        setSupportActionBar(innerToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //  tb.setBackgroundColor(getResources().getColor(R.color.white));
        innerToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp));
        innerToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        recyclerDisplayBook.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        recyclerDisplayBook.setNestedScrollingEnabled(false);
        adapter=new AdapterToDisplayBook(listData,getApplicationContext(),this);
        recyclerDisplayBook.setAdapter(adapter);

        switch (category){
            case "Science":
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
            case "Networking":
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
            case "Multimedia":
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
            case "Marketing":
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
            case "Programming":
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
            case "Law":
                bdb.open();
                listData.clear();
                listData.addAll(bdb.getBookDataByCategory("Law"));

                for (int i = 0; i<bdb.getBookDataByCategory("Law").size();i++){

                    bookData.setBookName(bdb.getBookDataByCategory("Law").get(i).getBookName());
                    bookData.setBookAuthor(bdb.getBookDataByCategory("Law").get(i).getBookAuthor());
                    bookData.setBookDesc(bdb.getBookDataByCategory("Law").get(i).getBookDesc());
                    bookData.setBookUrl(bdb.getBookDataByCategory("Law").get(i).getBookUrl());
                    bookData.setBookCategory(bdb.getBookDataByCategory("Law").get(i).getBookCategory());


                }
                adapter.notifyDataSetChanged();
                bdb.close();
                break;
            default:
                Toast.makeText(getApplicationContext(),"Some Thing Went Wrong",Toast.LENGTH_LONG).show();
        }

    }


    @Override
    public void clicked(String bookname, String authorname, String desc, String category, String url,String price, int position, int type) {
       // for cart and wishlist
        if(type == 0){
            wdb.open();
            wdb.save(bookname, authorname, desc, url, category,price);
            wdb.close();
            Toast.makeText(getApplicationContext(), "Added To WishList", Toast.LENGTH_LONG).show();

        }else if(type ==1){
            cdb.open();
            cdb.save(bookname, authorname, desc, url, category,price);
            cdb.close();
            Toast.makeText(getApplicationContext(), "Added To Cart", Toast.LENGTH_LONG).show();

        }
    }
}

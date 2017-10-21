package com.example.android.waitlist;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.example.android.waitlist.data.TestUtil;
import com.example.android.waitlist.data.WaitlistContract;
import com.example.android.waitlist.data.WaitlistDbHelper;


public class MainActivity extends AppCompatActivity {
    // TODO (10) Create a local field member of type SQLiteDatabase called mDb
    private SQLiteDatabase mDb;
    // TODO (32) Create local EditText members for mNewGuestNameEditText and mNewPartySizeEditText
    private EditText mNewGuestNameEditText;
    private EditText mNewPartySizeEditText;



    private GuestListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // TODO (11) Create a WaitlistDbHelper instance, pass "this" to the constructor as context
        WaitlistDbHelper mDbHelper = new WaitlistDbHelper(this);
        // TODO (33) Set the Edit texts to the corresponding views using findViewById
        mNewGuestNameEditText = (EditText)findViewById(R.id.person_name_edit_text);
        mNewPartySizeEditText = (EditText)findViewById(R.id.party_count_edit_text);




        // TODO (12) Get a writable database reference using getWritableDatabase and store it in mDb
        mDb = mDbHelper.getWritableDatabase();

        // TODO (13) call insertFakeData from TestUtil and pass the database reference mDb
        // TODO (34) Remove this fake data call since we will be inserting our own data now

      //  TestUtil.insertFakeData(mDb);

        // TODO (16) Run the getAllGuests function and store the result in a Cursor variable

        Cursor cursor = getAllGuests();

        // TODO (21) Pass the resulting cursor count to the adapter

        RecyclerView waitlistRecyclerView;

        // Set local attributes to corresponding views
        waitlistRecyclerView = (RecyclerView) this.findViewById(R.id.all_guests_list_view);

        // Set layout for the RecyclerView, because it's a list we are using the linear layout
        waitlistRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // TODO (31) Pass the entire cursor to the adapter rather than just the count

        // Create an adapter for that cursor to display the data
        mAdapter = new GuestListAdapter(this , cursor);

        // Link the adapter to the RecyclerView
        waitlistRecyclerView.setAdapter(mAdapter);

    }


    /**
     * This method is called when user clicks on the Add to waitlist button
     *
     * @param view The calling view (button)
     */
    public void addToWaitlist(View view) {
        // TODO (40) First thing, check if any of the EditTexts are empty, return if so
        if (mNewPartySizeEditText.getText().length()== 0 ||
                mNewGuestNameEditText.getText().length() == 0){
            return;
        }

        // TODO (41) Create an integer to store the party size and initialize to 1
        int partySize = 1;
        // TODO (42) Use Integer.parseInt to parse mNewPartySizeEditText.getText to an integer
try {
    // TODO (43) Make sure you surround the Integer.parseInt with a try catch and log any exception

    partySize = Integer.parseInt(mNewPartySizeEditText.getText().toString());

}catch (Exception e){
    e.printStackTrace();
}

        // TODO (45) call addNewGuest with the guest name and party size
        addNewGuest(mNewGuestNameEditText.getText().toString(),partySize);

        // TODO (50) call mAdapter.swapCursor to update the cursor by passing in getAllGuests()
        mAdapter.swapCursor(getAllGuests());

        // TODO (51) To make the UI look nice, call .getText().clear() on both EditTexts, also call clearFocus() on mNewPartySizeEditText
        mNewPartySizeEditText.clearFocus();
        mNewGuestNameEditText.getText().clear();
        mNewPartySizeEditText.getText().clear();

    }

    // TODO (14) Create a private method called getAllGuests that returns a cursor
    private Cursor getAllGuests(){
        // TODO (15) Inside, call query on mDb passing in the table name and projection String [] order by COLUMN_TIMESTAMP
        return mDb.query(WaitlistContract.WaitlistEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                WaitlistContract.WaitlistEntry.COLUMN_TIMESTAMP);
    }
    // TODO (35) Create a new addGuest method
    private long addNewGuest(String name , int partySize){
        // TODO (36) Inside, create a ContentValues instance to pass the values onto the insert query
        ContentValues contentValues = new ContentValues();
        // TODO (37) call put to insert the name value with the key COLUMN_GUEST_NAME

        // TODO (38) call put to insert the party size value with the key COLUMN_PARTY_SIZE

        // TODO (39) call insert to run an insert query on TABLE_NAME with the ContentValues created
        contentValues.put(WaitlistContract.WaitlistEntry.COLUM_GUEST_NAME,name);
        contentValues.put(WaitlistContract.WaitlistEntry.COLUMN_PARTY_SIZE,partySize);
        return mDb.insert(WaitlistContract.WaitlistEntry.TABLE_NAME,null,contentValues);

    }




}




















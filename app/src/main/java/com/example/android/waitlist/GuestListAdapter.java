package com.example.android.waitlist;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.waitlist.data.WaitlistContract;


public class GuestListAdapter extends RecyclerView.Adapter<GuestListAdapter.GuestViewHolder> {

    private Context mContext;
    // TODO (17) Add a new local variable mCount to store the count of items to be displayed in the recycler view
    // TODO (22) Replace the mCount with a Cursor field called mCursor

    private Cursor mCursor;

    /**
     * Constructor using the context and the db cursor
     *
     * @param context the calling context/activity
     */
    // TODO (18) Update the Adapter constructor to accept an integer for the count along with the context
    // TODO (23) Modify the constructor to accept a cursor rather than an integer

    public GuestListAdapter(Context context,Cursor cursor) {
        this.mContext = context;
        // TODO (19) Set the local mCount to be equal to count
        // TODO (24) Set the local mCursor to be equal to cursor

        this.mCursor = cursor;

    }

    @Override
    public GuestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Get the RecyclerView item layout
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.guest_list_item, parent, false);
        return new GuestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GuestViewHolder holder, int position) {
        // TODO (26) Move the cursor to the passed in position, return if moveToPosition returns false
        if (!mCursor.moveToPosition(position))return;

        // TODO (27) Call getString on the cursor to get the guest's name
        String name =mCursor.getString(mCursor.getColumnIndex(WaitlistContract.WaitlistEntry.COLUM_GUEST_NAME));

        // TODO (28) Call getInt on the cursor to get the party size
        int partySize =mCursor.getInt(mCursor.getColumnIndex(WaitlistContract.WaitlistEntry.COLUMN_PARTY_SIZE));
        // TODO (57) Retrieve the id from the cursor and
        long id = mCursor.getLong(mCursor.getColumnIndex(WaitlistContract.WaitlistEntry._ID));


        // TODO (29) Set the holder's nameTextView text to the guest's name
        holder.nameTextView.setText(name);

        // TODO (30) Set the holder's partySizeTextView text to the party size
        holder.partySizeTextView.setText(String.valueOf(partySize));

        // TODO (58) Set the tag of the itemview in the holder to the id
        //when not need to show in  the RecycleView and store indide itemView without show it
        holder.itemView.setTag(id);

    }

    // TODO (20) Modify the getItemCount to return the mCount value rather than 0

    @Override
    public int getItemCount() {
        // TODO (25) Update the getItemCount to return the getCount of mCursor

        return mCursor.getCount();
    }
    // TODO (46) Create a new function called swapCursor that takes the new cursor and returns void
    public void swapCursor(Cursor newCursor){
        // TODO (47) Inside, check if the current cursor is not null, and close it if so
        if (mCursor != null)mCursor.close();
        // TODO (48) Update the local mCursor to be equal to  newCursor
        mCursor = newCursor;
        // TODO (49) Check if the newCursor is not null, and call this.notifyDataSetChanged() if so
        if (newCursor != null){
            this.notifyDataSetChanged();
        }


    }




    /**
     * Inner class to hold the views needed to display a single item in the recycler-view
     */
    class GuestViewHolder extends RecyclerView.ViewHolder {

        // Will display the guest name
        TextView nameTextView;
        // Will display the party size number
        TextView partySizeTextView;

        /**
         * Constructor for our ViewHolder. Within this constructor, we get a reference to our
         * TextViews
         *
         * @param itemView The View that you inflated in
         *                 {@link GuestListAdapter#onCreateViewHolder(ViewGroup, int)}
         */
        public GuestViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.name_text_view);
            partySizeTextView = (TextView) itemView.findViewById(R.id.party_size_text_view);
        }

    }
}

package com.example.myndpsongs;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Song> songsList;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull ArrayList objects) {
        super(context, resource, objects);

        this.parent_context =context;
        layout_id=resource;
        this.songsList =objects;
    }


    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView title=rowView.findViewById(R.id.Title);
        TextView year=rowView.findViewById(R.id.Year);
        TextView singer=rowView.findViewById(R.id.Singers);
        TextView stars=rowView.findViewById(R.id.Stars);



        // Obtain the Android Version information based on the position
        Song song = songsList.get(position);

        // Set values to the TextView to display the corresponding information
        title.setText(song.getTitle());
        year.setText(song.getYear()+"");
        stars.setText(song.displayStars(song.getStar()));
        singer.setText(song.getSingers());

        return rowView;
    }
}


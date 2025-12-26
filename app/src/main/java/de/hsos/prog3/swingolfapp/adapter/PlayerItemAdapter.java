package de.hsos.prog3.swingolfapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import de.hsos.prog3.swingolfapp.R;
import de.hsos.prog3.swingolfapp.model.NameHolder;

public class PlayerItemAdapter extends ArrayAdapter<NameHolder> {
    public PlayerItemAdapter(Context context, int resource, List<NameHolder> nameHolderList) {
        super(context, resource, nameHolderList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NameHolder nameHolder = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.player_listview_item, parent, false
            );
        }

        TextView playerName = convertView.findViewById(R.id.playerItemText);
        playerName.setText(nameHolder.name());

        return convertView;
    }
}

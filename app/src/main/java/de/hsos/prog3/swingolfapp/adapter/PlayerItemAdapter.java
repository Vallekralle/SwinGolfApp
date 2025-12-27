package de.hsos.prog3.swingolfapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import de.hsos.prog3.swingolfapp.R;
import de.hsos.prog3.swingolfapp.model.PlayerInfoHolder;

public class PlayerItemAdapter extends ArrayAdapter<PlayerInfoHolder> {
    public PlayerItemAdapter(Context context, int resource, List<PlayerInfoHolder> playerInfoHolderList) {
        super(context, resource, playerInfoHolderList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PlayerInfoHolder playerInfoHolder = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.player_listview_item, parent, false
            );
        }

        TextView playerName = convertView.findViewById(R.id.playerItemText);
        CheckBox checkBox = convertView.findViewById(R.id.playerItemCheckBox);

        playerName.setText(playerInfoHolder.getName());
        checkBox.setOnClickListener(v -> {
            playerInfoHolder.setChecked();
        });

        return convertView;
    }
}

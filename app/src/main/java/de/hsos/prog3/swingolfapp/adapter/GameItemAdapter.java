package de.hsos.prog3.swingolfapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import java.util.List;
import java.util.stream.Collectors;

import de.hsos.prog3.swingolfapp.R;
import de.hsos.prog3.swingolfapp.logic.TableController;
import de.hsos.prog3.swingolfapp.model.gson.GameGson;
import de.hsos.prog3.swingolfapp.model.gson.PlayerGson;

public class GameItemAdapter extends ArrayAdapter<GameGson> {
    public GameItemAdapter(Context context, int resource, List<GameGson> gameInfoHolderList) {
        super(context, resource, gameInfoHolderList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GameGson gameGson = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.game_list_view_item, parent, false
            );

            convertView.setOnClickListener(v -> showDetailsDialog(gameGson));
        }

        String name = gameGson.courseName();
        int holeCount = gameGson.holeCount();

        TextView gameInfo = convertView.findViewById(R.id.gameInfoTextView);
        if (gameInfo != null && name != null) {
            gameInfo.setText(String.format("%s - Courses: %d", name, holeCount));
            gameInfo.setTextColor(Color.WHITE);
        }

        return convertView;
    }

    private void showDetailsDialog(GameGson gameGson) {
        PlayerGson winner = TableController.findWinner(gameGson);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = LayoutInflater.from(getContext());

        View dialogView = inflater.inflate(R.layout.winner_dialog, null);
        builder.setView(dialogView);

        // Determine the winner
        TextView winnerText = dialogView.findViewById(R.id.winnerTextView);

        if (winner == null) {
            winnerText.setText(getContext().getString(R.string.draw));
        } else {
            winnerText.setText(
                    String.format(
                            "%s %s with %s shots.",
                            getContext().getString(R.string.winner),
                            winner.name(),
                            winner.total()
                    )
            );
        }

        // Display player stats
        TextView playerStats = dialogView.findViewById(R.id.playerStatsTextView);

        playerStats.setText(
                Html.fromHtml(
                        gameGson.players().stream()
                                .map(playerGson -> {
                                    String format = String.format(
                                            "<b>%s</b> - Shoots in total: %d | Average shoot count: %.2f | Lowest: %d | Highest: %d<br><br>",
                                            playerGson.name(),
                                            playerGson.total(),
                                            playerGson.avg(),
                                            playerGson.min(),
                                            playerGson.max()
                                    );
                                    return format;
                                })
                                .collect(Collectors.joining())
                )
        );

        Button leaveBtn = dialogView.findViewById(R.id.leaveBtn);
        leaveBtn.setVisibility(View.GONE);

        AlertDialog dialog = builder.create();

        dialog.show();
    }
}

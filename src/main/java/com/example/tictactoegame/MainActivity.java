package com.example.tictactoegame;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button[][] buttons = new Button[3][3];
    private boolean isXTurn = true;
    private Button resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resetButton = findViewById(R.id.reset_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Button button = (Button) v;
                        if (button.getText().toString().isEmpty()) {
                            if (isXTurn) {
                                button.setText("X");
                            } else {
                                button.setText("O");
                            }
                            isXTurn =!isXTurn;
                            checkWin();
                        }
                    }
                });
            }
        }
    }

    private void checkWin() {
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().toString().equals(buttons[i][1].getText().toString()) &&
                    buttons[i][1].getText().toString().equals(buttons[i][2].getText().toString()) &&
                    !buttons[i][0].getText().toString().isEmpty()) {
                win(buttons[i][0].getText().toString());
                return;
            }
            if (buttons[0][i].getText().toString().equals(buttons[1][i].getText().toString()) &&
                    buttons[1][i].getText().toString().equals(buttons[2][i].getText().toString()) &&
                    !buttons[0][i].getText().toString().isEmpty()) {
                win(buttons[0][i].getText().toString());
                return;
            }
        }
        if (buttons[0][0].getText().toString().equals(buttons[1][1].getText().toString()) &&
                buttons[1][1].getText().toString().equals(buttons[2][2].getText().toString()) &&
                !buttons[0][0].getText().toString().isEmpty()) {
            win(buttons[0][0].getText().toString());
            return;
        }
        if (buttons[0][2].getText().toString().equals(buttons[1][1].getText().toString()) &&
                buttons[1][1].getText().toString().equals(buttons[2][0].getText().toString()) &&
                !buttons[0][2].getText().toString().isEmpty()) {
            win(buttons[0][2].getText().toString());
            return;
        }
        if (isBoardFull()) {
            Toast.makeText(this, "It's a draw!", Toast.LENGTH_SHORT).show();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    buttons[i][j].setEnabled(false);
                }
            }
        }
    }

    private void win(String winner) {
        Toast.makeText(this, "Player " + winner + " wins!", Toast.LENGTH_SHORT).show();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().toString().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetGame() {
        isXTurn = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
            }
        }
    }
}
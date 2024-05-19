package com.example.pokedexls;

import static com.example.pokedexls.R.id.bSend;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;


public class TrainerName extends Fragment {

    private ImageButton bSend;
    private TextView tTextProva;
    private Trainer trainer;

    public TrainerName() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trainer_name, container, false);
        bSend = view.findViewById(R.id.bSend);
        tTextProva = view.findViewById(R.id.tTextProva);
        EditText editNameTrainer = view.findViewById(R.id.editNameTrainer);
        bSend.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // tTextProva.setText(trainer.getName());
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_container, new SecondFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        editNameTrainer.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE ||
                        (keyEvent != null && keyEvent.getAction() == KeyEvent.ACTION_DOWN && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {

                    String nombreEntrenador = textView.getText().toString();
                    trainer.setName(nombreEntrenador);

                    // Establecer el texto del entrenador en el TextView

                    return true;
                }
                return false;
            }
        });





       return view;
    }
}
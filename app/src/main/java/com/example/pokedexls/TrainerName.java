// TrainerName.java
package com.example.pokedexls;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.pokedexls.Entity.Trainer;
import com.example.pokedexls.Entity.TrainerUpdate;

public class TrainerName extends Fragment {
    private TrainerUpdate trainerUpdate;

    private ImageButton bSend;
    private static final String ARG_TRAINER = "trainer";
    private Trainer trainer;

    public static TrainerName newInstance(Trainer trainer) {
        TrainerName fragment = new TrainerName();
        Bundle args = new Bundle();
        args.putSerializable(ARG_TRAINER, trainer);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        trainer = (Trainer) getArguments().getSerializable(ARG_TRAINER);
        trainerUpdate = (TrainerUpdate) getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trainer_name, container, false);
        bSend = view.findViewById(R.id.bSend);
        EditText editNameTrainer = view.findViewById(R.id.editNameTrainer);

        bSend.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                trainerUpdate.actualitzaTrainer(trainer);

                SecondFragment secondFragment = SecondFragment.newInstance(trainer);
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_container, secondFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        editNameTrainer.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE ||
                        (keyEvent != null && keyEvent.getAction() == KeyEvent.ACTION_DOWN && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {

                    trainer.setName(editNameTrainer.getText().toString());

                    return true;
                }
                return false;
            }
        });

        return view;
    }
}

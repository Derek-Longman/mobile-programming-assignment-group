package algonquin.cst2335.DezerSong;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.fragment.app.Fragment;

import algonquin.cst2335.myapplication.databinding.DetailsLayoutBinding;

public class DetailsFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);
        DetailsLayoutBinding binding = DetailsLayoutBinding.inflate(inflater);
        return binding.getRoot();
    }
}

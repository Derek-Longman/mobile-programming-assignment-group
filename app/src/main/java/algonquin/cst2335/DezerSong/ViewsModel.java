package algonquin.cst2335.DezerSong;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class ViewsModel extends ViewModel {
    public MutableLiveData<ArrayList<String>> songs = new MutableLiveData< >();
    public MutableLiveData<String> selectedSong = new MutableLiveData<>();
}

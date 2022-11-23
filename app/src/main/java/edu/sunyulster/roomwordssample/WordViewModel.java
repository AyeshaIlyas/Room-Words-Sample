package edu.sunyulster.roomwordssample;

import android.app.Application;
import android.telephony.SmsManager;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

// survive config changes
// responsible for holding data and processing the data needed for the UI
public class WordViewModel extends AndroidViewModel {

    // Rules:
    // 1. never pass context into ViewModel instances
    // 2. Dont store Activity, Fragment, or View instances or their Context in the ViewModel
    // If you need access to context use AndroidViewModel

    private WordRepository repo;
    private LiveData<List<Word>> words;

    public WordViewModel (Application application) {
        super(application);
        repo = new WordRepository(application);
        words = repo.getAllWords();
    }

    public LiveData<List<Word>> getAllWords() {
        return words;
    }

    public void insert(Word word) {
        repo.insert(word);
    }

}

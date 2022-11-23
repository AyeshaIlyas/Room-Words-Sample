package edu.sunyulster.roomwordssample;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import java.util.List;

// abstracts access to multiple data sources
// manages query threads and allows for multiple backends
// implements the logic for deciding whether to fetch data over a network or use results cached in the local database
public class WordRepository {

    private WordDao wordDao;
    private LiveData<List<Word>> allWords;

    public WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getInstance(application);
        wordDao = db.wordDao();
        allWords = wordDao.getAllWords();
    }

    // wrapper method
    public LiveData<List<Word>> getAllWords() {
        return allWords;
    }

    // calls the doa's insert method in a separate thread
    public void insert(Word word) {
        new InsertAsyncTask(wordDao).execute(word);
    }

    private static class InsertAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao asyncTaskDao;

        public InsertAsyncTask(WordDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... words) {
            asyncTaskDao.insert(words[0]);
            return null;
        }
    }
}



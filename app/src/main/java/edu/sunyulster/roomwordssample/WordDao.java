package edu.sunyulster.roomwordssample;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WordDao {

    @Insert
    void insert(Word word);

    // no convenience annotation for deleting multiple entries
    @Query("DELETE FROM word_table")
    void deleteAll();

    // when methods annotated with @Query are called, they must be performed in a separate thread
    // methods annotated by the convenience annotations are automatically executed in a their own threads
    @Query("SELECT * FROM word_table ORDER BY word ASC")
    LiveData<List<Word>> getAllWords();

}

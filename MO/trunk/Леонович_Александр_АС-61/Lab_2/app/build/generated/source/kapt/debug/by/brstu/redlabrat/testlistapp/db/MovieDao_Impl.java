package by.brstu.redlabrat.testlistapp.db;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import by.brstu.redlabrat.testlistapp.api.OmdbSearchResultMovie;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class MovieDao_Impl implements MovieDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<OmdbSearchResultMovie> __insertionAdapterOfOmdbSearchResultMovie;

  private final EntityDeletionOrUpdateAdapter<OmdbSearchResultMovie> __deletionAdapterOfOmdbSearchResultMovie;

  public MovieDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfOmdbSearchResultMovie = new EntityInsertionAdapter<OmdbSearchResultMovie>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `search_result_movie` (`title`,`year`,`imdbId`,`type`,`posterUrl`) VALUES (?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, OmdbSearchResultMovie value) {
        if (value.title == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.title);
        }
        if (value.year == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.year);
        }
        if (value.imdbId == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.imdbId);
        }
        if (value.type == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.type);
        }
        if (value.posterUrl == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.posterUrl);
        }
      }
    };
    this.__deletionAdapterOfOmdbSearchResultMovie = new EntityDeletionOrUpdateAdapter<OmdbSearchResultMovie>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `search_result_movie` WHERE `imdbId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, OmdbSearchResultMovie value) {
        if (value.imdbId == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.imdbId);
        }
      }
    };
  }

  @Override
  public void addMovieToFavorites(final OmdbSearchResultMovie movie) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfOmdbSearchResultMovie.insert(movie);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void removeFromFavorites(final OmdbSearchResultMovie movie) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfOmdbSearchResultMovie.handle(movie);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<OmdbSearchResultMovie> getAllSavedMovies() {
    final String _sql = "select * from search_result_movie";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfYear = CursorUtil.getColumnIndexOrThrow(_cursor, "year");
      final int _cursorIndexOfImdbId = CursorUtil.getColumnIndexOrThrow(_cursor, "imdbId");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfPosterUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "posterUrl");
      final List<OmdbSearchResultMovie> _result = new ArrayList<OmdbSearchResultMovie>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final OmdbSearchResultMovie _item;
        _item = new OmdbSearchResultMovie();
        if (_cursor.isNull(_cursorIndexOfTitle)) {
          _item.title = null;
        } else {
          _item.title = _cursor.getString(_cursorIndexOfTitle);
        }
        if (_cursor.isNull(_cursorIndexOfYear)) {
          _item.year = null;
        } else {
          _item.year = _cursor.getString(_cursorIndexOfYear);
        }
        if (_cursor.isNull(_cursorIndexOfImdbId)) {
          _item.imdbId = null;
        } else {
          _item.imdbId = _cursor.getString(_cursorIndexOfImdbId);
        }
        if (_cursor.isNull(_cursorIndexOfType)) {
          _item.type = null;
        } else {
          _item.type = _cursor.getString(_cursorIndexOfType);
        }
        if (_cursor.isNull(_cursorIndexOfPosterUrl)) {
          _item.posterUrl = null;
        } else {
          _item.posterUrl = _cursor.getString(_cursorIndexOfPosterUrl);
        }
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public OmdbSearchResultMovie getMovieById(final String id) {
    final String _sql = "select * from search_result_movie where imdbId == ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, id);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfYear = CursorUtil.getColumnIndexOrThrow(_cursor, "year");
      final int _cursorIndexOfImdbId = CursorUtil.getColumnIndexOrThrow(_cursor, "imdbId");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfPosterUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "posterUrl");
      final OmdbSearchResultMovie _result;
      if(_cursor.moveToFirst()) {
        _result = new OmdbSearchResultMovie();
        if (_cursor.isNull(_cursorIndexOfTitle)) {
          _result.title = null;
        } else {
          _result.title = _cursor.getString(_cursorIndexOfTitle);
        }
        if (_cursor.isNull(_cursorIndexOfYear)) {
          _result.year = null;
        } else {
          _result.year = _cursor.getString(_cursorIndexOfYear);
        }
        if (_cursor.isNull(_cursorIndexOfImdbId)) {
          _result.imdbId = null;
        } else {
          _result.imdbId = _cursor.getString(_cursorIndexOfImdbId);
        }
        if (_cursor.isNull(_cursorIndexOfType)) {
          _result.type = null;
        } else {
          _result.type = _cursor.getString(_cursorIndexOfType);
        }
        if (_cursor.isNull(_cursorIndexOfPosterUrl)) {
          _result.posterUrl = null;
        } else {
          _result.posterUrl = _cursor.getString(_cursorIndexOfPosterUrl);
        }
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}

package factory;

import db_connector.Connector;
import db_connector.QueryBuilder;
import helper.SupportMethods;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import oo.Genre;

public class GenreFactory {

  /**
   *
   * @param id
   * @param mockRs
   * @return genre
   */
  public static Genre getGenreById(int id, ResultSet mockRs) {
    Genre genre = null;

    Connection c = Connector.getConnection();
    String sql = QueryBuilder.getGenreByID(id);
    ResultSet rs = null;

    if(mockRs == null)
    {
      rs = Connector.getQueryResult(c, sql);
    }

    else
    {
      rs = mockRs;
    }

    if (rs != null) {

      try {
        rs.next();

        genre = new Genre(rs.getInt("GenreID"),
            rs.getString("Genrebezeichnung"),
            rs.getString("Genrebeschreibung"));
      }catch(SQLException e) {
        e.printStackTrace();
      }

    }
    Connector.closeResultSet(rs);
    Connector.closeConnection(c);
    return genre;
  }

  /**
   *
   * @param id
   * @return Genre
   */
  public static Genre getGenreById(int id)
  {
    return getGenreById(id, null);
  }

  /**
   *
   * @param mockRs
   * @return genres
   */
  public static Genre[] getGenre(ResultSet mockRs){
    Genre[] genres;
    Connection c = Connector.getConnection();
    String sql = QueryBuilder.getGenres();
    ResultSet rs = null;

    if(mockRs == null)
    {
      rs = Connector.getQueryResult(c, sql);
    }

    else
    {
      rs = mockRs;
    }

    if(rs!= null) {
      int rsSize = SupportMethods.getResultSetSize(rs);
      if(rsSize > 0){
        genres = new Genre[rsSize];
        try{
          //int counter = 0;
          //while (rs.next()) {
          for (int i = 0; i < rsSize; i++){
            rs.next();
            genres[i] = new Genre(rs.getInt("GenreID"),
                rs.getString("Genrebezeichnung"),
                rs.getString("Deskriptor"));

            //counter ++;
          }
        }catch(SQLException e){
          e.printStackTrace();
        }
        SupportMethods.close(c, rs);

        return genres;
      } else {
        genres = new Genre[1];
        genres[0] = null;
        SupportMethods.close(c, rs);

        return genres;
      }
    }
    SupportMethods.close(c, rs);

    return null;
  }

  /**
   *
   * @return Genre[]
   */
  public static Genre[] getGenre()
  {
    return getGenre(null);
  }
}

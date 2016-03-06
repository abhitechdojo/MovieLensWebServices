package models

import javassist.runtime.Desc

import com.websudos.phantom.dsl._
import com.websudos.phantom.CassandraTable
import connector.CassandraConnector
import entities.Movie
import scala.concurrent.Future


/**
  * Created by abhishek.srivastava on 3/1/16.
  */
class Movies extends CassandraTable[Movies, Movie]{
  object id extends IntColumn(this) with PartitionKey[Int]
  object title extends StringColumn(this) with Index[String]
  object year extends IntColumn(this) with Index[Int]
  object genre extends SetColumn[Movies, Movie, String](this) with Index[Set[String]]

  def fromRow(row: Row) : Movie = {
    Movie(
      id(row),
      title(row),
      year(row),
      genre(row)
    )
  }
}

object Movies extends Movies with CassandraConnector {
  def store(movie: Movie) : Future[ResultSet] = {
    insert
      .value(_.id, movie.id)
      .value(_.title, movie.title)
      .value(_.year, movie.year)
      .value(_.genre, movie.genre)
      .consistencyLevel_=(ConsistencyLevel.ALL)
      .future()
  }

  def getById(id: Int) : Future[Option[Movie]] = {
    select.where(_.id eqs id).one()
  }

  def getByYear(year: Int) : Future[Seq[Movie]] = {
    select.where(_.year eqs year).fetch()
  }

  def getByGenre(genre: List[String]) : Future[Seq[Movie]] = {
    var criteria = select.where(_.genre contains genre.head)
    criteria = genre.tail.foldLeft(criteria){(accum, i) => accum.and(_.genre contains i)}
    criteria.allowFiltering().fetch()
  }

  def getByGenreAndYear(genre: List[String], year: Int) : Future[Seq[Movie]] = {
    var criteria = select.where(_.genre contains genre.head)
    criteria = genre.tail.foldLeft(criteria){(accum, i) => accum.and(_.genre contains i)}.and(_.year eqs year)
    println(s"${criteria.queryString}")
    criteria.allowFiltering().fetch()
  }
}

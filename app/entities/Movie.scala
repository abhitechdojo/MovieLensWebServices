package entities

/**
  * Created by abhishek.srivastava on 3/1/16.
  */

import play.api.libs.json._

case class Movie(id: Int, title: String, year: Int, genre: Set[String])

object Movie {
  implicit val implicitMovieWrites = new Writes[Movie] {
    def writes(movie: Movie) : JsValue = {
      Json.obj(
        "id" -> movie.id,
        "title" -> movie.title,
        "year" -> movie.year,
        "genre" -> movie.genre
      )
    }
  }
}
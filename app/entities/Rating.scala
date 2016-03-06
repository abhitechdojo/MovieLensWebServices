package entities

import play.api.libs.json._
import org.joda.time._
/**
  * Created by abhishek.srivastava on 3/1/16.
  */

case class Rating(userId: Int, movieId: Int, rating: Int, ratingTimestamp: DateTime, zipCode: String)

object Rating {
  implicit val implicitRatingWrites = new Writes[Rating] {
    def writes(rating: Rating) : JsValue = {
      Json.obj(
        "userId" -> rating.movieId,
        "movieId" -> rating.movieId,
        "rating" -> rating.rating,
        "ratingTimestamp" -> rating.ratingTimestamp
      )
    }
  }
}
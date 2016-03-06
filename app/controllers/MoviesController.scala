package controllers

import entities.Movie
import models._
import play.api._
import play.api.mvc._
import play.api.libs.json.Json._
import play.api.libs.json.{JsError, Json}
import scala.concurrent._
import scala.concurrent.duration._

class MoviesController extends Controller {

  def getByGenre(genre: List[String]) = Action {
    val result = Await.result(Movies.getByGenre(genre), 5 seconds)
    Ok(toJson(result))
  }

  def getByYear(year: Int) = Action {
    val result = Await.result(Movies.getByYear(year), 5 seconds)
    Ok(toJson(result))
  }

  def getById(id: Int) = Action {
    val result = Await.result(Movies.getById(id), 5 seconds)
    Ok(toJson(result))
  }

  def getByGenreAndYear(genre: List[String], year: Int) = Action {
    val result = Await.result(Movies.getByGenreAndYear(genre, year), 5 seconds).sortWith(_.year > _.year)
    Ok(toJson(result))
  }
}

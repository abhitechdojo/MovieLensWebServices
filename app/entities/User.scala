package entities

import play.api.libs.json._

/**
  * Created by abhishek.srivastava on 3/1/16.
  */

case class User(id: Int, age: Int, gender: String, occupration: String, zipCode: String)

object User {
  implicit val implicitUserWrites = new Writes[User] {
    def writes(user: User) : JsValue = {
      Json.obj(
        "id" -> user.id,
        "age" -> user.age,
        "gender" -> user.gender,
        "occupration" -> user.occupration,
        "zipCode" -> user.zipCode
      )
    }
  }
}
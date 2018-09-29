package services

import model.Artist
import play.api.libs.json._
import scalaj.http.{Http, HttpResponse}

object ArtistService {

  def fetchArtists(baseUrl: String): Option[List[Artist]] = {
    Http(s"$baseUrl/artists")
      .asString match {
      case r: HttpResponse[String] if r.is2xx => {
        val artists= Json.parse(r.body).as[List[Artist]]
        Some(artists)
      }
      case _ => None
    }
  }


}

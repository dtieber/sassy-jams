package controllers

import javax.inject._
import model.Artist
import play.api.mvc._
import services.ArtistService

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def index() = Action { implicit request: Request[AnyContent] =>
    val artists: Option[List[Artist]] = ArtistService.fetchArtists("http://localhost:9000")

    artists.fold(
      Ok(views.html.index(List()))
    ) {
      (artists) => Ok(views.html.index(artists))
    }

  }
}

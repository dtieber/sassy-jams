package contracts

import com.itv.scalapact.ScalaPactForger.{bodyArrayMinimumLengthRule, bodyRegexRule, bodyTypeRule, forgePact, interaction}
import org.scalatest.{AsyncFunSpec, Matchers}
import com.itv.scalapact.circe09._
import com.itv.scalapact.http4s18._
import model.Artist
import services.ArtistService

class RocknRollContract  extends AsyncFunSpec with Matchers {

  val LIST_OF_ARTISTS =
    """
      |[
      | {
      |   "id": 1,
      |   "name": "My Artist Name",
      |   "genre": "Rock",
      |   "avatar": "http://images.com/artist.jpg"
      | }, {
      |   "id": 2,
      |   "name": "My Other Name",
      |   "genre": "Blues",
      |   "avatar": "http://images.com/artist2.jpg"
      | }
      |]
    """.stripMargin

  describe("Artist service") {

    it("should return a list of artist") {
      forgePact.between("Sassy Records")
        .and("Rock'n'Roll Service")
        .addInteraction(
          interaction.description("Fetching all artists")
            .uponReceiving("/artists")
            .willRespondWith(status = 200,
              headers = Map.empty,
              body = Some(LIST_OF_ARTISTS),
              matchingRules =
                bodyArrayMinimumLengthRule("", 2)
                ~> bodyRegexRule("[*].id", "[1-9]+")
                ~> bodyRegexRule("[*].name", "[A-Za-z1-9 ]{3,}")
                ~> bodyTypeRule("[*].genre")
                ~> bodyTypeRule("[*].avatar")
            )
        ).runConsumerTest({ config =>
          val result: Option[List[Artist]] = ArtistService.fetchArtists(config.baseUrl)
          result.get should have size 2
        })
    }

  }

}

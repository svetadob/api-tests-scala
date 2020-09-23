package com.example.integration

import com.example.model.SearchResults
import play.api.libs.json.Json
import scalaj.http.Http

class SearchByTermTests extends IntegrationSpec {

  describe("Search results by term (artist name)") {
    val searchEntry = searchTermEntryWithoutLimit
    val response = Http(apiAddress).param(termParam, searchEntry.searchTerm).asString

    describe("without any additional parameters") {
      it("should completed successfully") {
        logger.debug(s"Verify response finished successfully ${response.isSuccess}")
        response.isSuccess shouldBe true
      }

      it("should contain artist id and artist name") {
        logger.debug(s"Response is: ${response.body}")

        val jsonObject = Json.parse(response.body)
        val searchResults = jsonObject.as[SearchResults]

        searchResults.resultCount shouldBe searchEntry.resultsCount
        searchResults.results.foreach(
          track => {
            track.artistId shouldBe searchEntry.artistId
            track.artistName should include(searchEntry.artistName)
          }
        )
      }
    }


    describe("with limit param") {
      it("should contain not more than a given count in results") {
        val searchEntryWithLimit = searchTermWithLimit

        val responseWithCountLimit = Http(apiAddress)
          .param(termParam, searchEntryWithLimit.searchTerm)
          .param(limitParam, searchEntryWithLimit.resultsCount.toString)
          .asString

        logger.debug(s"Response with limit parameter: \n $responseWithCountLimit")
        responseWithCountLimit.isSuccess shouldBe true
        val secondJson = Json.parse(responseWithCountLimit.body)
        val limitedSearchResults = secondJson.as[SearchResults]

        limitedSearchResults.resultCount shouldBe searchEntryWithLimit.resultsCount
      }
    }
  }
}


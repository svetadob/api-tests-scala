package com.example.integration

import com.example.model.SearchResults
import play.api.libs.json.Json
import scalaj.http.Http

class SearchByTermTests extends IntegrationSpec {

  "Verify search by term (artist name)" should "contain relevant result" in {
    val searchEntry = getSearchTermEntryWithoutLimit
    val response = Http(apiAddress).param(termParam, searchEntry.searchTerm).asString

    logger.debug(s"Verify response finished successfully ${response.isSuccess}")
    response.isSuccess shouldBe true

    val jsonObject = Json.parse(response.body)
    val searchResults = jsonObject.as[SearchResults]

    logger.debug(s"Response is: $searchResults")
    searchResults.resultCount shouldBe searchEntry.resultsCount
    searchResults.results.foreach(
      track => {
        track.artistId shouldBe searchEntry.artistId
        track.artistName should include(searchEntry.artistName)
      }
    )
  }

  "Verify search by the same term (artist name)" should "apply count limit" in {
    val searchEntryWithoutLimit = getSearchTermEntryWithoutLimit
    val searchEntryWithLimit = getSearchTermWithLimit

    val responseWithoutCountLimit = Http(apiAddress)
      .param(termParam, searchEntryWithoutLimit.searchTerm)
      .asString

    logger.debug(s"Response without limit parameter: \n $responseWithoutCountLimit")
    responseWithoutCountLimit.isSuccess shouldBe true
    val jsonObject = Json.parse(responseWithoutCountLimit.body)
    val searchResults = jsonObject.as[SearchResults]
    searchResults.resultCount shouldBe searchEntryWithoutLimit.resultsCount

    val responseWithCountLimit = Http(apiAddress)
      .param(termParam, searchEntryWithoutLimit.searchTerm)
      .param(limitParam, searchEntryWithLimit.resultsCount.toString)
      .asString

    logger.debug(s"Response with limit parameter: \n $responseWithCountLimit")
    responseWithCountLimit.isSuccess shouldBe true
    val secondJson = Json.parse(responseWithCountLimit.body)
    val limitedSearchResults = secondJson.as[SearchResults]

    limitedSearchResults.resultCount shouldBe searchEntryWithLimit.resultsCount
  }

}


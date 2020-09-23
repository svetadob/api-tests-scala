package com.example.model

import play.api.libs.json.{Json, Reads}

case class SearchResults(resultCount: Int, results: List[Track])

object SearchResults {
  implicit val resultsReads: Reads[SearchResults] = Json.reads[SearchResults]
}

package com.example.integration

trait TestDataProvider {
  def getSearchTermEntryWithoutLimit: SearchTerm = {
    SearchTerm(50, "jack+johnson", "Jack Johnson", 909253)
  }

  def getSearchTermWithLimit: SearchTerm = {
    SearchTerm(25, "jack+johnson", "Jack Johnson", 909253)
  }
}

case class SearchTerm(resultsCount: Int, searchTerm: String, artistName: String, artistId: Long)
package com.example.model

import play.api.libs.json.{Json, Reads}

case class Track(wrapperType: String, kind: String, artistId: Int, collectionId: Long,
                 trackId: Long, artistName: String, collectionName: String, trackName: String)

object Track {
  implicit val jsonFormat: Reads[Track] = Json.reads[Track]
}

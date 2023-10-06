package gn.domain

import io.circe.Codec
import io.circe.generic.semiauto.deriveCodec

case class SharedData(text: String, number: Int)

object SharedData {
  given Codec[SharedData] = deriveCodec
}
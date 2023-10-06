package gn.backend

import cats.data.Kleisli
import org.slf4j.{Logger, LoggerFactory}
import cats.effect.{ExitCode, IO, IOApp}
import gn.domain.SharedData
import org.http4s.*
import org.http4s.circe.*
import org.http4s.implicits.*
import org.http4s.dsl.io.*
import org.http4s.dsl.impl./
import org.http4s.{HttpRoutes, Request, Response}
import org.http4s.blaze.server.BlazeServerBuilder

import io.circe.*
import io.circe.syntax.*

import scala.concurrent.duration.Duration
object IOMain extends IOApp {

  private val logger: Logger = LoggerFactory.getLogger(getClass.toString)
  given sharedDataDecoder: EntityDecoder[IO, SharedData] = jsonOf[IO, SharedData]

  private val httpRequests: Kleisli[IO, Request[IO], Response[IO]] = HttpRoutes
    .of[IO] {
      case req @ GET -> Root / "backend" =>
        logger.info("GET Request received : " + req.toString())
        Ok("Get result")
      case req @ POST -> Root / "backend" / "query" =>
        for {
          sharedData <- req.as[SharedData]
          resp <- Ok(s"Request: ${sharedData.asJson}")
        } yield resp
    }
    .orNotFound
  override def run(args: List[String]): IO[ExitCode] =
    BlazeServerBuilder[IO]
      .bindHttp(1234, "localhost")
      .withHttpApp(httpRequests)
      .withIdleTimeout(Duration.Inf)
      .withResponseHeaderTimeout(Duration.Inf)
      .resource
      .use(_ => IO.never)
      .as(ExitCode.Success)
}

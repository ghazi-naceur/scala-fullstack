package gn.backend

import cats.data.Kleisli
import cats.effect.{ExitCode, IO, IOApp}
import org.http4s.{HttpRoutes, Request, Response}
import org.http4s.blaze.server.BlazeServerBuilder
import org.http4s.dsl.impl./
import org.http4s.dsl.io.*
import org.slf4j.{Logger, LoggerFactory}
import org.http4s.*

import scala.concurrent.duration.Duration
object IOMain extends IOApp {

  private val logger: Logger = LoggerFactory.getLogger(getClass.toString)

  private val httpRequests: Kleisli[IO, Request[IO], Response[IO]] = HttpRoutes
    .of[IO] {
      case req @ GET -> Root / "backend" =>
        logger.info("GET Request received : " + req.toString())
        Ok("Get result")
      case req @ POST -> Root / "backend" / "query" =>
        Ok(s"Request: $req")
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

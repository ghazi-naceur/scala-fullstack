package gn.frontend

import be.doeraene.webcomponents.ui5.configkeys.InputType
import be.doeraene.webcomponents.ui5.*
import com.raquo.laminar.api.L.*
import gn.domain.SharedData
import org.scalajs.dom
import io.circe.syntax.*

object EntryPoint {

  def main(args: Array[String]): Unit = {

    val clickBus = new EventBus[Unit]
    val inputText = Var("")

    render(
      dom.document.getElementById("root"),
      div(
        Title.h1("Scala fullstack example"),
        p(
          child.text <-- clickBus.events
            .sample(inputText.signal)
            .flatMap(text =>
              FetchStream.post(
                url = "/backend/query",
                _.body(SharedData(text, 1).asJson.noSpaces),
                _.headers(
                  "Content-Type" -> "application/json",
                  "Accept" -> "text/plain"
                )
              )
            )
        ),
        div(
          display := "flex",
          alignItems := "center",
          Label("Enter name:"),
          Input(
            _.tpe := InputType.Text,
            _.events.onChange.mapToValue --> inputText.writer,
            marginRight := "1em",
            marginLeft := "1em"
          ),
          Button("Send", _.events.onClick.mapTo(()) --> clickBus.writer)
        )
      )
    )
  }
}

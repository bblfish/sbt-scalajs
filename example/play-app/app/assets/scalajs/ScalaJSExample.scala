package scalajs.example

import scala.scalajs.js
import org.scalajs.jquery
import js.Dynamic.{ global => g }
import org.scalajs.jquery.JQueryXHR
import scalajs.react

object ScalaJSExample {
  val w = g.document.defaultView
  val jQ = g.jQuery
  val React = g.React
  val document = g.document
  
  val doc = jQ(g.document)
  
  doc.ajaxError({ (ev: js.Any, xhr: JQueryXHR) =>
    if (xhr.status == 401.asInstanceOf[js.Number]) {
      w.alert("AJAX request received 401 status: Unauthorized")
    } else {
      w.alert(s"Unexpected AJAX Error ${xhr.status} ${xhr.statusText}")
    }
  })

  def main(): Unit = {
    val paragraph = g.document.createElement("p")
    paragraph.innerHTML = "<strong>It works from Scala.js in ui module!</strong>"
    g.document.getElementById("playground").appendChild(paragraph)
  }

  def ajaxClickScalaJS(url: String) {
    jQ.getJSON(url, { result: js.Any => 
      w.alert("Received valid JSON from the server") 
    })
  }

  def reactExample {
    var html =""
    
    React.renderComponentToString(
      React.DOM.strong(null, "It works from Scala.js React (no JSX) in ui!"), (h:String) => html = h
    )
    React.renderComponent(
      React.DOM.strong(null, s"It works from Scala.js React (no JSX) in ui!"),
      document.getElementById("playground-ui")
   )	
    React.renderComponent(
      React.DOM.p(null, s"Server Generated code: \n$html"),
	  document.getElementById("playground-ui-code")
   )	
  }

}  


   
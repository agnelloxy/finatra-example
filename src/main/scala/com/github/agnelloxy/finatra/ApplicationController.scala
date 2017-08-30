package com.github.agnelloxy.finatra

import com.twitter.finatra.http.Controller
import com.twitter.finagle.http.Request

case class ExampleCaseClass(id: Option[Int], name: String)

class ApplicationController extends Controller {
  get("/") { request: Request =>
    "Hello, world!"
  }
  get("/foo") {request: Request =>
    ExampleCaseClass(Some(1), "Example Name")
  }
}

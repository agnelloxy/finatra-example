package com.github.agnelloxy.finatra

import com.twitter.finatra.http.Controller
import com.twitter.finagle.http.Request

class ApplicationController extends Controller {
  get("/") { request: Request =>
    "Hello, world!"
  }
}

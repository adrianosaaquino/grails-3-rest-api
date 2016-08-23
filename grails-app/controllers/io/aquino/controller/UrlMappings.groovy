package io.aquino.controller

class UrlMappings {

    static mappings = {

        "/api/$controller" {
            action = [GET: "index", POST: "save"]
            format = 'json'
        }

        "/api/$controller/$id?" {
            action = [GET: "show", PUT: "update", DELETE: "delete", POST: "save"]
            format = 'json'
        }

        "/"(controller: 'application', action: 'index')
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}

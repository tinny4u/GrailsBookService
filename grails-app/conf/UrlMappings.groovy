class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

        "/api/v1/book"(controller: "bookApi", parseRequest: true) {
            action = [GET: "list"]
            /*
                      PUT: "update",
                      DELETE: "delete",
                      POST: "save"]
            */
        }

		"/"(view:"/index")
		"500"(view:'/error')
	}
}

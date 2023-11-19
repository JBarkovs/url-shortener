# URL Shortener

Java:17/Springboot:3 application, that generates short urls and redirects to original original urls.
- To generate short url create a `POST` request to localhost:8080 and pass original url parameter, eg., http://localhost:8080?url=https://github.com. Expected response is http://localhost:8080/mW4f
- To navigate to the original url take the POST response url open it in browser or create a `GET`. If the short link exists in memory, then the response will have status 302 and will redirect to the original url. 

To run on `Docker` run './gradlew runDocker' from project root. 
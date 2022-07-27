(ns notion.api.core
  (:refer-clojure :exclude [get])
  (:require [clj-http.client :as client]
            [clojure.data.json :as json]
            [clojure.string :as string]))

(defonce ^:private notion-version "2022-06-28")
(defonce ^:private user-agent (str "notion.clj - Notion v" notion-version))
(defonce ^:private base-url "https://api.notion.com/v1")

(defn- build-route
  "Builds the complete route needed to make a request.

   path-params is a map containing the keys and the values that needs to be
   	interpolated into the path string.

   Usage.
   	build-route('projects/:project-id/user/:name', {:project-id 5, :name 'Goerge'})"
  ([path path-params]
  	(str base-url (reduce-kv #(string/replace %1 (str %2) (str %3)) path path-params)))
  ([path]
  	(build-route path {})))

(defn- build-request [client method path params]
  "Builds a request to the API"
  {:method method,
   :url path
   :form-params params,
   :user-agent user-agent,
   :headers {:Notion-Version notion-version},
   :oauth-token (.token client),
   :content-type :json,
   :accept :json,
   :cookie-policy :none})

(defn- send-request
  "Sends a request to the API and parse the response.
  	(Returns nil if the response code is not 200)

   client 
   	Client type instance that contains the client's informations.
   method 
   	Key that defines the http method used for the request.
   route 
   	String containing the url of the request
   params
   	Map containing the request's payload"
  ([client method route payload]
    (let [request (build-request client method route payload)
          response (client/request request)]
      (if (= 200 (:status response))
        (json/read-str (:body response) :key-fn keyword))))
  ([client method route]
    (send-request client method route {})))

(defn get
  "Fetches all the resources"
  ([client path]
    (:results (send-request client :get path)))
  ([client path id]
    (let [route (build-route path {:id id})]
      (send-request client :get route))))

(defn post
  "Creates a new resource"
  [client path body]
  (send-request client :post (build-route path) body))

(defn put
  "Replaces the resource with the given id"
  [client path id body]
  (send-request client :put (build-route path) body))

(defn patch
  "Updates the resource with the given id"
  [client path id body]
  (send-request client :patch (build-route path) body))

(defn delete
  "Deletes the resource with the given id"
  [client path id]
  (let [route (build-route path {:id id})]
    (send-request client :delete route)))

(defn search
  "Search and returns page's and/or database's titles that matches the given query.

   options: Map that can includes those keys:
    :sort - Sorts the result by the provided criteria. Only one at the time
            is currently allowed.
      :direction - Direction to sort. Must be :ascending or :descending
      :timestamp - Name of the timestamp to sort. Possible value is :last_edited_time

    :filter - Filters the results based on the provided criteria. filter must
              be a map that include :value and/or :property
      :value - Value of the property. Can be :database or :page. At the moment,
               only :object is available (see notion's doc)
      :property - Name of the property. Can be :database or :page. At the moment
                  only :object is available (see notion's doc)
    :start_cursor - Pagination starting point or the result.
    :page_size - Number or result per page. Maximum is 100.

    Currently limited to :object which search both :page and :database
     https://developers.notion.com/reference/post-search"
  ([client query options]
  	(println "Warning: Due to API limitation, searchs are performed on :object\n(https://developers.notion.com/reference/post-search)")
  	
    (let [params (merge {:query query} options)]
      (post client "/search" params)))
  ([client query]
    (search client query {})))
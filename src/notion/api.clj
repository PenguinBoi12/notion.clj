(ns notion.api
  (:require [clj-http.client :as client]
            [clojure.data.json :as json]
            [clojure.string :as string]))

(defonce ^:private notion-version "2022-02-22")
(defonce ^:private user-agent (str "notion.clj - Notion v" notion-version))
(defonce ^:private base-url "https://api.notion.com/v1")

(defn build-route
  "Builds the complete route needed to make a request.

  path-params is a map containing the keys and the values that needs to be
  interpolated into the path string.

  Ex.
   path        = projects/:project-id/user/:name
   path-params = {:project-id 5, :name 'Goerge'}"
  [path path-params]
  (str base-url
       (reduce-kv #(string/replace %1 (str %2) (str %3)) path path-params)))

(defn- build-request [client method path params]
  {:method method,
   :url path
   :form-params params,
   :user-agent user-agent,
   :headers {:Notion-Version notion-version},
   :oauth-token (.token client),
   :content-type :json,
   :accept :json,
   :cookie-policy :none})

(defn send-request
  ([client method route params]
    (let [request (build-request client method route params)
          response (client/request request)]
      (if (= 200 (:status response))
        (json/read-str (:body response) :key-fn keyword))))
  ([client method route]
    (send-request client method route {})))

(defn fetch
  "Fetch the given model. If an 'id' is given, fetches only one record.
   Otherwise, everthing is fetched."
  ([client path]
    (:results (send-request client :get path)))
  ([client path id]
    (let [route (build-route path {:id id})]
      (send-request client :get route))))

(defn delete!
  "Permanently deletes the given model with the given id"
  [client path id]
  (let [route (build-route path {:id id})]
    (send-request client :delete route)))

; Might need to be moved elsewhere
(defn search
  "Searches pages and databases (including childrens) titles that matches
   the given query.

   params is a map that can includes those keys:
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

    https://developers.notion.com/reference/post-search"
  ([client query params]
    (let [params (merge {:query query} params)]
      (send-request client :post "/search" params))))

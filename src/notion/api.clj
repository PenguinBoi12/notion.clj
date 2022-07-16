(ns notion.api
  (:refer-clojure :exclude [get])
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
   path        = 'projects/:project-id/user/:name'
   path-params = {:project-id 5, :name 'Goerge'}"
  [path path-params]
  (str base-url
       (reduce-kv #(string/replace %1 (str %2) (str %3)) path path-params)))

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

(defn send-request
  "Sends a request and parse the response. Returns nil if status is not 200"
  ([client method route params]
    (let [request (build-request client method route params)
          response (client/request request)]
      (if (= 200 (:status response))
        (json/read-str (:body response) :key-fn keyword))))
  ([client method route]
    (send-request client method route {})))

(defn get
  "Fetch the given model. If an 'id' is given, fetches only one record.
   Otherwise, everthing is fetched."
  ([client path]
    (:results (send-request client :get path)))
  ([client path id]
    (let [route (build-route path {:id id})]
      (send-request client :get route))))

(defn post!
  "Create a new object"
  [client path body]
  (send-request client :get path body))

(defn put!
  "Updates the object with the given with the given id"
  [client path id body])

(defn delete!
  "Permanently deletes the object with the given with the given id"
  [client path id]
  (let [route (build-route path {:id id})]
    (send-request client :delete route)))

(ns notion.api
  (:gen-class)
  (:require [clj-http.client :as client]
            [clojure.data.json :as json]))

(defonce ^:private notion-version "2022-02-22")
(defonce ^:private user-agent (format "notion.clj - Notion v%s" notion-version))
(defonce ^:private base-url "https://api.notion.com/v1")
(defonce ^:private routes {:user "/users"
                           :page "/pages"
                           :database "/databases"
                           :block "/blocks"})

(defn- build-request [method path client params]
  {:method method,
   :url (str base-url path)
   :form-params params,
   :user-agent user-agent,
   :headers {:Notion-Version notion-version},
   :oauth-token (.token client),
   :content-type :json,
   :accept :json,
   :cookie-policy :none})

(defn- send-request
  ([method path client params]
    (let [request (build-request method path client params)
          response (client/request request)]
      (if (= 200 (:status response))
        (json/read-str (:body response) :key-fn keyword))))
  ([method path client]
    (send-request method path client {})))

(defn fetch
  ([client model]
    (:results (send-request :get (get routes model) client)))
  ([client model id]
    (let [path (str (get routes model) "/" id)]
      (send-request :get path client))))

(defn search
  ([client query options]
    (let [options (merge {:query query} options)]
      (send-request :post "/search" client ))))

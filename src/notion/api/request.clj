(ns notion.api.request
  (:gen-class)
  (:require [clj-http.client :as client]
            [clojure.data.json :as json]))

(defonce ^:private api-version 1)
(defonce ^:private notion-version "2022-02-22")
(defonce ^:private user-agent
  (format "notion bot - Clojure %s, API %d" (clojure-version) api-version))
(defonce ^:private base-url (str "https://api.notion.com/v" api-version))

(defn- build-request [method path auth params]
  {:method method,
   :url (str base-url path),
   :form-params params,
   :user-agent user-agent,
   :headers {:Notion-Version notion-version},
   :oauth-token (:token auth),
   :content-type :json,
   :accept :json,
   :cookie-policy :none})

(defn send-request
  ([method path auth params]
    (let [promise (promise)
          request (deliver promise (client/request
            (build-request method path auth params)))]
      (json/read-str (:body @promise) :key-fn keyword)))

  ([method path auth]
    (send-request method path auth {})))

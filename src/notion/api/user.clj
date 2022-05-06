(ns notion.api.user
  (:require [notion.api.request :refer [send-request]]))

(defn get-users [client]
  (:results (send-request :get "/users" client)))

(defn get-user [client id]
  (let [path (str "/users/" id)]
    (send-request :get path client)))

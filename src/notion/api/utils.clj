(ns notion.api.utils
  (:require [notion.api.core :refer [send-request]]))

(defonce ^:private model-routes {:user "/users"
                                 :page "/pages"
                                 :database "/databases"
                                 :block "/blocks"})

(defn fetch
  "Fetch the given model. If an 'id' is given, fetches only one record."
  ([client model]
    (:results (send-request :get (get model-routes model) client)))
  ([client model id]
    (let [path (str (get model-routes model) "/" id)]
      (send-request :get path client))))

(defn search
  ([client query options]
    (let [options (merge {:query query} options)]
      (send-request :post "/search" client ))))

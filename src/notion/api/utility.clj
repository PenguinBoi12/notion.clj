(ns notion.api.utility
  (:gen-class)
  (:require [notion.api.request :refer [send-request]]))

(defn search
  ([notion-bot query options]
    (send-request :post "/search" notion-bot (merge {:query query} options)))
  ([notion-bot query]
    (search notion-bot query {})))

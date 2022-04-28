(ns notion.notion
  (:gen-class)
  (:require [environ.core :refer [env]]
            [notion.api.user :refer :all]))

(defrecord NotionBot [token])

(defn init-bot []
  (->NotionBot (env :notion-token)))

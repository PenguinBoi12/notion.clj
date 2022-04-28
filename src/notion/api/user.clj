(ns notion.api.user
  (:require [notion.api.request :refer [send-request]]))

(defn get-users [notion-bot]
  (:results (send-request :get "/users" notion-bot)))

(defn get-user [notion-bot id]
  (send-request :get (str "/users/" id) notion-bot))

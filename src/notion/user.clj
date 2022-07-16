(ns notion.user
  (:refer-clojure :exclude [find])
  (:require [notion.api :as api]
            [notion.types.user :refer [build-user]]))

(defonce ^:private prefix "/users/")

(defn all
  "Returns all accessible users"
  [client]
  (map build-user (api/get client prefix)))

(defn find [client id]
  "Finds and return the user with the given id"
  (build-user (api/get client (str prefix ":id") id)))

(defn create! [client user])

(defn save! [client user])

(defn delete!
  "Delete the user with the given id"
  [client user]
  (let [user_id (:id user)]
    (api/delete! client prefix user_id)))

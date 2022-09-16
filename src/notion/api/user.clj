(ns notion.api.user
  (:refer-clojure :exclude [find])
  (:require [notion.api.core :as api]
            [notion.types.user :refer [build-user]]))

(defn bot? [user] 
  (= (:type "bot") "bot"))

(defn all
  "Returns all users"
  [client]
  (map build-user (api/get client "/users/")))

(defn find [client id]
  "Finds and return the user with the given id"
  (build-user (api/get client "/users/:id" id)))

(defn me
  "Retrieve's the bot's User"
  [client]
  (build-user "/users/me"))

(defn created-by
  "Returns the user who created the resource"
  [client resource]
  (find client (:created-by-id resource)))

(defn last-edited-by
  "Returns the last user who edited the resource"
  [client resource]
  (find client (:last-edited-by-id resource)))
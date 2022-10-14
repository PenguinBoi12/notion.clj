(ns notion.api.user
  (:refer-clojure :exclude [find])
  (:require [notion.api.core :as api]))

(defn bot? [user] 
  (= (:type "bot") "bot"))

(defn all
  "Returns all users"
  [client]
  (:results (api/get client "/users")))

(defn find [client id]
  "Finds and return the user with the given id"
  (api/get client "/users/:id" id))

(defn me
  "Retrieve's the bot's User"
  [client]
  (api/get client "/users/me"))

(defn created-by
  "Returns the user who created the resource"
  [client resource]
  (find client (:created-by-id resource)))

(defn last-edited-by
  "Returns the last user who edited the resource"
  [client resource]
  (find client (:last-edited-by-id resource)))
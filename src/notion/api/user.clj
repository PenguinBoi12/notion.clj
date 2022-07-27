(ns notion.api.user
  (:refer-clojure :exclude [find])
  (:require [notion.api.core :as api]
            [notion.types.user :refer [build-user]]))

(defonce ^:private prefix "/users/")

(defn all
  "Returns all accessible users"
  [client]
  (map build-user (api/get client prefix)))

(defn find [client id]
  "Finds and return the user with the given id"
  (build-user (api/get client (str prefix ":id") id)))

(defn me
	"Retrieve's the bot's User"
	[client]
	(build-user (str prefix "/me")))

(defn created-by
  "Returns the user who created the object"
  [client object]
  (find client (:created-by-id object)))

(defn last-edited-by
  "Returns the last user who edited the object"
  [client object]
  (find client (:last-edited-by-id object)))
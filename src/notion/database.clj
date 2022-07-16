(ns notion.database
  (:refer-clojure :exclude [find])
  (:require [notion.api :as api]
            [notion.types.database :refer [build-database]]))


(defonce ^:private prefix "/databases/")

(defn all
  "Returns all accessible databases"
  [client]
  (map build-database (api/get client prefix)))

(defn find
  "Finds and return the database with the given id"
  [client id]
  (build-database (api/get client (str prefix ":id") id)))

(defn create! [client database])

(defn save! [client database])

(defn delete!
  "Delete the database with the given id"
  [client database]
  (let [database_id (:id database)]
    (api/delete! client prefix database_id)))

(ns notion.api.database
  (:refer-clojure :exclude [find])
  (:require [notion.api.core :as api]
            [notion.types.database :refer [build-database]]))


(defonce ^:private prefix "/databases/")

(defn all
  "Returns all accessible databases"
  [client]
  (map build-database (api/search client "" {:filter {:value :database}})))

(defn find
  "Finds and return the database with the given id"
  [client id]/
  (build-database (api/get client (str prefix ":id") id)))

(defn create! [client database])

(defn save! [client database])

(defn delete!
  "Delete the database with the given id"
  [client database]
  (let [database_id (:id database)]
    (api/delete! client prefix database_id)))

(defn pages
  "Returns all pages from the database

  https://developers.notion.com/reference/post-database-query"
  [client database]
  (let [database_id (:id database)]
    (api/post! (str prefix ":id/query") database_id)))

(defn search 
	"Searches database's titles that matches the given query.

	 See api/search for the list of options. (Note that filter will be ignored) 

	 Not completly available due to Notion's API limitation.
	 (https://developers.notion.com/reference/post-search)"
	([client query options]
		(let [options (merge options {:filter :database, :property :database})]
  		(map build-database (api/search client query options))))
  ([client query]
  	(search client query {})))
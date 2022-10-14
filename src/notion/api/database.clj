(ns notion.api.database
  (:refer-clojure :exclude [find])
  (:require [notion.api.core :as api]))

(defn find
  "Finds and return the database with the given id"
  [client id]
  (api/get client  "/databases/:id" id))

(defn create! [client database]
  (api/post "/databases/:id" database))

(defn save! [client database]
  (api/patch "/databases/:id" database))

(defn delete!
  "Delete the database with the given id"
  [client database]
  (let [database-id (:id database)]
    (api/delete client "/databases/:id" database-id)))

(defn pages
  "Returns all pages from the database

   params: Map that can includes those keys
    :filter - Limits pages based on the given filter conditions 
    :sorts - Orders the results based on the sort criteria 
    :start_cursor - Set the starting pagination's cursor
    :page_size - Number of elements in each pages
    
   For mor infomations see https://developers.notion.com/reference/post-database-query"
  ([client database params]
    (let [database-id (:id database)]
      (:results (api/post "/databases/:id/query" database-id))))
  ([client database]
    (pages client database {})))

(defn search
  "Searches databases's titles that matches the given query.

   See api/search for the list of options

   Not completly available due to Notion's API limitation
   (https://developers.notion.com/reference/post-search)"
  ([client query options]
    (let [default-options {}]
      (api/search client query (merge options default-options))))
  ([client query]
    (search client query {})))
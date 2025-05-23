(ns notion.api.page
  (:refer-clojure :exclude [find])
  (:require [notion.api.core :as api]
            [notion.api.block :as block]))

(defn find
  "Finds and return the page with the given id"
  [client id]
  (api/get client "/pages/:id" id))

(defn create! [client page]
  (api/post "/pages/:id" page))

(defn save! [client page]
  (api/patch "/pages/:id" page))

(defn archive!
  "Archives (deletes) the page with the given id"
  [client page]
  (block/delete! client page))

(defn property [client page property_id]
  (api/get "/pages/:page_id/properties/:property_id"
    {:page_id (:id page) :property_id property}))

(defn search
  "Searches page's titles that matches the given query.

   See api/search for the list of options

   Not completly available due to Notion's API limitation 
   (https://developers.notion.com/reference/post-search)"
  ([client query options]
    (let [default-options {:filter {:property :object :value :page}}]
      (api/search client query (merge options default-options))))
  ([client query]
    (search client query {})))
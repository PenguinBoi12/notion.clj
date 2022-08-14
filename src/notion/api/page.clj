(ns notion.api.page
  (:refer-clojure :exclude [find])
  (:require [notion.api.core :as api]
            [notion.types.page :refer [build-page]]
            [notion.api.block :as block]))

(defn find
  "Finds and return the page with the given id"
  [client id]
  (build-page (api/get client "/pages/:id" id)))

(defn create! [client page]
	(api/post "/pages/:id" page))

(defn save! [client page]
	(api/patch "/pages/:id" page))

(defn archive!
  "Archives (deletes) the page with the given id"
  [client page]
  (block/delete! client page))

(defn search 
	"Searches page's titles that matches the given query.

	 See api/search for the list of options. (Note that filter will be ignored) 

	 Not completly available due to Notion's API limitation 
	 (https://developers.notion.com/reference/post-search)"
	([client query options]
		(let [options (merge options {:filter :page, :property :object})]
  		(map build-page (api/search client query options))))
  ([client query]
  	(search client query {})))
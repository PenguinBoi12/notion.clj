(ns notion.api.block
  (:refer-clojure :exclude [find])
  (:require [notion.api.core :as api]
            [notion.types.block :refer [build-block]]))

(defn find 
  "Finds and return the block with the given id"
	[client id]
  (build-block (api/get client "/blocks/:id" id)))

(defn create! [client block])

(defn save! [client block])

(defn delete!
  "Delete the block with the given id"
  [client block]
  (api/delete client "/blocks" (:id block)))

(defn children
	"Returns the block's childrens"
	[client block]
	(if (:has_children block)
		(map build-block (api/get "/blocks/:id/children") (:id block))))

(defn add-child!
	"Add a child to the block's childrens"
	[client block]
	(if (:has_children block)
		(build-block (api/patch "/blocks/:id/children" (:id block)))))
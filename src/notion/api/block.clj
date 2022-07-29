(ns notion.api.block
  (:refer-clojure :exclude [find])
  (:require [notion.api.core :as api]
            [notion.api.database :as database]
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
  (let [block_id (:id block)]
    (api/delete client "/blocks" block_id)))

(defn parent
  "Returns the parent of the block"
  [client block]
  (let [id 	 (:parent-id block),
        type (:parent-type block)
        func (type {:page find, :database database/find})]
    (func client id)))

(defn children
	"Returns the block's childrens"
	[client block]
	(if (:has_children block)
		(let [id (:id block)]
			(map build-block (api/get "/blocks/:id/children") id))))

(defn add-child!
	"Add a child to the block's childrens"
	[client block]
	(if (:has_children block)
		(let [id (:id block)]
			(build-block (api/patch "/blocks/:id/children" id)))))
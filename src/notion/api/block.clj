(ns notion.api.block
  (:refer-clojure :exclude [find])
  (:require [notion.api.core :as api]))

(defn find 
  "Finds and return the block with the given id"
  [client id]
  (api/get client "/blocks/:id" id))

(defn save! [client block]
  (api/patch "/blocks/:id" block))

(defn delete!
  "Delete the block with the given id"
  [client block]
  (api/delete client "/blocks" block))

(defn children
  "Returns the block's childrens"
  [client block]
  (if (:has_children block)
    (api/get "/blocks/:id/children") (:id block)))

(defn append!
  "Add a child to the block's childrens"
  [client block]
  (if (:has_children block)
    (api/patch "/blocks/:id/children" (:id block))))
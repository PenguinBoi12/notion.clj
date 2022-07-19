(ns notion.api.block
  (:refer-clojure :exclude [find])
  (:require [notion.api.core :as api]
            [notion.api.database :as database]
            [notion.types.block :refer [build-block]]))

(defonce ^:private prefix "/blocks/")

(defn all
  "Returns all accessible blocks"
  [client]
  (map build-block (api/get client prefix)))

(defn find [client id]
  "Finds and return the block with the given id"
  (build-block (api/get client (str prefix ":id") id)))

(defn create! [client block])

(defn save! [client block])

(defn delete!
  "Delete the block with the given id"
  [client block]
  (let [block_id (:id block)]
    (api/delete! client prefix block_id)))

(defn parent
  "Returns the parent of the given object"
  [client object]
  (let [id (:parent-id object),
        type (:parent-type object)
        func (type {:page find, :database database/find})]
    (func client id)))
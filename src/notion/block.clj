(ns notion.block
  (:refer-clojure :exclude [find])
  (:require [notion.api :as api]
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

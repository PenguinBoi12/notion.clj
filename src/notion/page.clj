(ns notion.page
  (:refer-clojure :exclude [find])
  (:require [notion.api :as api]
            [notion.types.page :refer [build-page]]
            [notion.block :as block]))

(defonce ^:private prefix "/pages/")

(defn all
  "Returns all accessible pages (blocks)"
  [client]
  (map build-page (api/get client "/blocks/")))

(defn find
  "Finds and return the page with the given id"
  [client id]
  (build-page (api/get client (str prefix ":id") id)))

(defn create! [client page]
  (block/create! client page))

(defn save! [client page]
  (block/save! client page))

(defn delete!
  "Delete the page with the given id"
  [client page]
  (block/delete! client page))

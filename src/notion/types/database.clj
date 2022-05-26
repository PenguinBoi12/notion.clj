(ns notion.types.database
  (:require [clojure.set :refer [rename-keys]]))

(defn- format-map [m]
  (let [database-keys {:id :id
                       :created_time :created-time
                       :created_by :created-by-id
                       :last_edited_time :last-edited-time
                       :last_edited_by :last-edited-by-id
                       :title :title
                       :icon :icon
                       :cover :cover
                       ; :properties :properties
                       :parent :parent-id
                       :url :url
                       :archived :archived?}]
    (-> m
      (select-keys (keys database-keys))
      (rename-keys database-keys))))

(defrecord Database [id title icon cover parent-id archived? created-time
                     created-by-id last-edited-time last-edited-by-id url])

; Add the parent, created-by and last-edited-by type?
(defn build-database [m]
  (-> (format-map m)
    (update :title #(:content (:text (first %))))
    (update :icon :emoji)
    (update :created-by-id :id)
    (update :last-edited-by-id :id)
    (update :parent-id :page_id)
    (map->Database)))

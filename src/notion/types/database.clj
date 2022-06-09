(ns notion.types.database
  (:require [notion.utils :refer [format-type-map]]))

(def database-keys {:id :id
                    :created_time :created-time
                    :created_by :created-by-id
                    :last_edited_time :last-edited-time
                    :last_edited_by :last-edited-by-id
                    :title :title
                    :icon :icon
                    :cover :cover
                    :properties :properties ; Needs to implement this correctly
                    :parent :parent-id
                    :parent-type :parent-type
                    :url :url
                    :archived :archived?})

(def types-by-key {:page_id :page,
                   :database_id :database,
                   :workspace_id :workspace})

(defrecord Database [id title icon cover parent-id parent-type archived?
                     created-time created-by-id last-edited-time
                     last-edited-by-id url])

(defn build-database [m]
  (let [parent-type (keyword (:type (:parent m)))]
    (-> (format-type-map m database-keys)
      (update :title #(:content (:text (first %))))
      (update :icon :emoji)
      (update :created-by-id :id)
      (update :last-edited-by-id :id)
      (update :parent-id parent-type)
      (assoc :parent-type (parent-type types-by-key))
      (map->Database))))

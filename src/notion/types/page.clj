(ns notion.types.page
  (:require [notion.utils :refer [format-type-map]]))

(def page-keys {:id :id
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


(defrecord Page [id created-time created-by-id last-edited-time last-edited-by-id
                 archived? icon cover properties parent-id parent-type url])

; Add the parent, created-by and last-edited-by type?
(defn build-page [m]
 (def parent-type (keyword (:type (:parent m))))

 (-> (format-type-map m)
   (update :title #(:content (:text (first %))))
   (update :icon :emoji)
   (update :created-by-id :id)
   (update :last-edited-by-id :id)
   (update :parent-id parent-type)
   (assoc :parent-type (parent-type types-by-key))
   (map->Page)))

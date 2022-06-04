(ns notion.types.block
  (:require [notion.utils :refer [format-type-map]]))

(def block-keys {:id :id
                 :type :type
                 :content :content
                 :created_time :created-time
                 :created_by :created-by-id
                 :last_edited_time :last-edited-time
                 :last_edited_by :last-edited-by-id
                 :has_children :has-children?
                 :annotations :annotations
                 :archived :archived?})

(defrecord Block [id type created-time created-by-id last-edited-time
                  last-edited-by-id archived? has-children? content])

(defn build-block [m]
  (let [type (keyword (:type m)) rich-text (first (:rich_text (type m)))]
    (-> (format-type-map m block-keys)
      (update :created-by-id :id)
      (update :last-edited-by-id :id)
      (assoc :annotations (:annotations rich-text))
      (assoc :content (:content (:text rich-text)))
      (map->Block))))

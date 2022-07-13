(ns notion.block
  (:require
    [notion.api.utils :as api]
    [notion.types.block :refer [build-block]]))

(defn create! [client block])

(defn save! [client block])

(defn delete! [client block]
  (let [block_id (:id block)]
    (api/delete! client :block block_id)))

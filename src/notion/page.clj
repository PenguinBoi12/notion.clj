(ns notion.page
  (:require [notion.api.utils :as api]))

(defn create! [client page])

(defn save! [client page])

(defn delete! [client page]
  (let [page_id (:id page)]
    (api/delete! client :database page_id)))
